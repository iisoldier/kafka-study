package com.hds.api.common.validate;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hds.api.common.constant.AuthValidation;
import com.hds.api.common.constant.R;
import com.hds.api.sys.service.LoadCacheService;
import com.hds.api.sys.vo.HDSApi;
import com.hds.api.sys.vo.HDSApiSys;
import com.hds.api.sys.vo.HDSIPWhiteList;
import com.hds.api.sys.vo.HDSSys;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.common.validate.AuthValidateUtil
* @Description: 统一接口权限校验类
* @author zengli
* @date 创建时间：2016年4月17日 下午4:56:44
 */
public class AuthValidator {

	
	private static Logger logger = Logger.getLogger(AuthValidator.class);
	
	/**
	* @method  authValidate
	* @Description:  若请求参数校验完成,且业务逻辑校验完成，则进入权限校验
	* @author  zengli
	* @date 2016年4月17日 下午5:08:20
	* @parameter  String  sys_id            系统编号     校验系统是否为准入系统，配合检查系统ip白名单、系统接口权限等
	* @parameter  String request_ip     请求ip        校验接入系统的ip白名单
	* @parameter  String api_id            接口id        在seviceimpl中通过系统常量类获得，校验接口是否处于开启状态，配合检查系统接口权限
	* @parameter  String request_id     查询请求id  用于日志记录
	* @parameter 
	* @return  List<ResponseResult>
	 */
	@SuppressWarnings("rawtypes")
	public  static List<ResponseResult> authValidate(String  sys_id , String api_id ,String request_ip ,String request_id) {
		
        //------------------------   确定需要加载的缓存map(减少中断时间)  ----------------------------------------------
        //初始化当前map
		Map<String, ArrayList> current_cacheMap = new HashMap<String,ArrayList>();
		//校验需要查询的map的名称
		String  current_cacheMapName = LoadCacheService.
				getCurrent_cachMap().get(R.SystemConstant.CACHE_CURRENTMAP);
		if(current_cacheMapName.equals(R.SystemConstant.CACHE_MAINMAP)){
			current_cacheMap = LoadCacheService.main_cacheMap;
		}
		if(current_cacheMapName.equals(R.SystemConstant.CACHE_BKMAP)){
			current_cacheMap = LoadCacheService.bk_cacheMap;
		}
        //------------------------   确定需要加载的缓存map(减少中断时间)  ----------------------------------------------
		
		//初始化返回信息结果
		List<ResponseResult> Ist_rr  = new ArrayList<ResponseResult>();

		//------------------------------------接入系统校验  开始    --------------------------------------------------------------
		//从内存中获取接入系统列表
		@SuppressWarnings("unchecked")
		List<HDSSys> syslist = current_cacheMap.get(R.SystemConstant.CACHE_SYSLIST);
		int count_syslist = 0;
		for (int i = 0; i < syslist.size(); i++) {
			//校验sysid 是否存在，且系统接入状态为成功
			if(sys_id.equals(syslist.get(i).getSys_id())&&
					AuthValidation.Constant.SYSACCESS_OPEN_FLAG==syslist.get(i).getSys_flag()){
				//debug日志(可删除)
				logger.info("request_id:"+request_id+",校验成功的接入系统:"+syslist.get(i).toString());
				count_syslist ++;
				break;
			}
		}
		//插入接入系统校验错误
		if(count_syslist==0){		
			ResponseResult rr = new ResponseResult();
			rr.setType(R.SystemConstant.RESTYPE_AUTH);
			rr.setResult_code(AuthValidation.ResultCode.AUTH_SYS_INVALID);
			rr.setField(AuthValidation.Field.AUTH_SYS_IDFLAGE);
			rr.setMessage(AuthValidation.ValidateMessage.SYS_NOTEXIST_ERRMSG+"后台常量设置为:"+sys_id);
			Ist_rr.add(rr);
			//debug日志(可删除)
			logger.error("request_id:"+request_id+",接入系统校验错误,反馈信息:"+rr.toString());
			
			return Ist_rr;
		}
		//------------------------------------接入系统校验  结束   --------------------------------------------------------------
		
	
		//------------------------------------API校验  开始    --------------------------------------------------------------
		//从内存中获取api列表
		@SuppressWarnings("unchecked")
		List<HDSApi> apilist = current_cacheMap.get(R.SystemConstant.CACHE_APILIST);
		int count_apilist = 0;
		for (int i = 0; i < apilist.size(); i++) {
			//校验api开关是否处于开启状态
			if(api_id.equals(AuthValidation.APIEnum.valueOf(api_id).getValue())&&
					AuthValidation.Constant.API_OPEN_FLAG == apilist.get(i).getApi_flag()){
				//debug日志(可删除)
				logger.info("request_id:"+request_id+",校验成功的api:"+apilist.get(i).toString());
				count_apilist ++;
				break;
			}
		}	
		//插入接口校验错误
		if(count_syslist==1&&count_apilist==0){
			ResponseResult rr = new ResponseResult();			
			rr.setType(R.SystemConstant.RESTYPE_AUTH);
			rr.setResult_code(AuthValidation.ResultCode.AUTH_API_INVALID);
			rr.setField(AuthValidation.Field.AUTH_API_IDFLAG);
			rr.setMessage(AuthValidation.ValidateMessage.API_NOTEXIST_ERRMSG+",后台常量设置为:"+api_id);
			Ist_rr.add(rr);
			//debug日志(可删除)
			logger.error("request_id:"+request_id+",接口校验错误,反馈信息:"+rr.toString());
			
			return Ist_rr;
		}
		//------------------------------------API校验 结束    --------------------------------------------------------------
		
		
		//------------------------------------IP白名单校验  开始    --------------------------------------------------------------
		//从内存中获取ip白名单列表
		@SuppressWarnings("unchecked")
		List<HDSIPWhiteList> ipwhitelist = current_cacheMap.get(R.SystemConstant.CACHE_IPWHITELIST);
		int count_ipwhitelist = 0;
		for (int i = 0; i < ipwhitelist.size(); i++) {
			//校验接入系统ip白名单
			if(sys_id.equals(ipwhitelist.get(i).getSys_id())&&
					request_ip.equals(ipwhitelist.get(i).getSys_ip())){
				//debug日志(可删除)
				logger.info("request_id:"+request_id+",校验成功的接入系统ip白名单:"+ipwhitelist.get(i).toString());
				count_ipwhitelist ++;
				break;
			}
		}		
		//插入接入系统ip白名单校验错误
		if(count_syslist==1&&count_apilist==1&&count_ipwhitelist==0){
			ResponseResult rr = new ResponseResult();
			rr.setType(R.SystemConstant.RESTYPE_AUTH);
			rr.setResult_code(AuthValidation.ResultCode.AUTH_SYSIP_INVALID);
			rr.setField(AuthValidation.Field.AUTH_SYS_IP);
			rr.setMessage(AuthValidation.ValidateMessage.SYSIP_NOTACCESS_ERRMSG+",后台常量设置为:"+sys_id+"|"+request_ip);
			Ist_rr.add(rr);
			//debug日志(可删除)
			logger.error("request_id:"+request_id+",系统白名单校验错误,反馈信息:"+rr.toString());
			
			return Ist_rr;
		}
		//------------------------------------IP白名单  结束 --------------------------------------------------------------
		
		//------------------------------------系统接口权限校验  开始    --------------------------------------------------------------
		//从内存中获取系统接口权限列表
		@SuppressWarnings("unchecked")
		List<HDSApiSys> apisyslist = current_cacheMap.get(R.SystemConstant.CACHE_APISYSLIST);
		int count_apisyslist = 0;
		for (int i = 0; i < apisyslist.size(); i++) {
			//校验api开关是否处于开启状态
			if(sys_id.equals(apisyslist.get(i).getSys_id()) &&api_id.equals(apisyslist.get(i).getApi_id()) ){
				//debug日志(可删除)
				logger.info("request_id:"+request_id+",校验成功的接入系统接口权限:"+apisyslist.get(i).toString());
				count_apisyslist ++;
				break;
			}
		}
		//插入接入系统接口权限校验错误
	    if(count_syslist==1&&count_apilist==1&&count_ipwhitelist==1&&count_apisyslist==0){
			ResponseResult rr = new ResponseResult();
			rr.setType(R.SystemConstant.RESTYPE_AUTH);
			rr.setResult_code(AuthValidation.ResultCode.AUTH_APISYS_INVALID);
			rr.setField(AuthValidation.Field.AUTH_API_SYS);
			rr.setMessage(AuthValidation.ValidateMessage.SYSAPI_NOTACCESS_ERRMSG+",后台常量设置为:"+sys_id+"|"+api_id);
			Ist_rr.add(rr);
			//debug日志(可删除)
			logger.error("request_id:"+request_id+",接入系统接口权限校验错误,反馈信息:"+rr.toString());
			
			return Ist_rr;
		}
		//------------------------------------系统接口权限校验  结束  --------------------------------------------------------------
		
		
	   //------------------------------------系统权限校验成功 开始 --------------------------------------------------------------
	   if(count_syslist ==1 && count_apilist ==1 && count_ipwhitelist==1 && count_apisyslist==1){
			ResponseResult rr = new ResponseResult();
			rr.setType(R.SystemConstant.RESTYPE_AUTH);
			rr.setResult_code(AuthValidation.ResultCode.AUTH_IS_VALID);
			rr.setField(AuthValidation.Field.AUTH_IS_VALID);
			rr.setMessage(AuthValidation.ValidateMessage.AUTH_VALID_SUCESSMSG);
			Ist_rr.add(rr);
			//debug日志(可删除)
			logger.info("request_id:"+request_id+",请求权限校验成功,反馈信息:"+rr.toString());
			return Ist_rr;	
	   }
       //------------------------------------系统权限校验成功 结束--------------------------------------------------------------	
		return Ist_rr;		
		
	}
	
	
	
	
}
