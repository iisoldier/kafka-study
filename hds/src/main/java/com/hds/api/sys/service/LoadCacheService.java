package com.hds.api.sys.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.hds.api.common.constant.AuthValidation;
import com.hds.api.common.constant.R;
import com.hds.api.common.constant.RequestValidation;
import com.hds.api.common.util.DateUtils;
import com.hds.api.common.validate.AuthValidator;
import com.hds.api.common.validate.RequestParamValidator;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.dao.CacheDao;
import com.hds.api.sys.vo.ReloadCacheRequest;
import com.hds.api.sys.vo.ReloadCacheResponse;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.sys.service.StartLoadCacheService
* @Description: 在spring初始化时，加载类中的CacheDao，并实现Dao中的相关参数数据加载
* @Description: 在spring初始化之后，对
* @author zengli
* @date 创建时间：2016年4月17日 下午4:42:19
 */
public class LoadCacheService {

	private static Logger logger = Logger.getLogger(LoadCacheService.class);
	
    @Resource  
    private CacheDao cacheDao ;  
    

	@SuppressWarnings("rawtypes")
	//public static Map<String,Map<String, ArrayList>> main_cacheMap =
	//              Collections.synchronizedMap(new HashMap<String,Map<String, ArrayList>>());
	
	public static Map<String, ArrayList> main_cacheMap =
            Collections.synchronizedMap(new HashMap<String,ArrayList>());
	
	@SuppressWarnings("rawtypes")
	//public static Map<String,Map<String, ArrayList>> bk_cacheMap =
    //        Collections.synchronizedMap(new HashMap<String,Map<String, ArrayList>>());
	public static Map<String, ArrayList> bk_cacheMap =
    Collections.synchronizedMap(new HashMap<String,ArrayList>());
	
	
	public static Map<String, String> current_cachMap = Collections.synchronizedMap(new HashMap<String,String>());
	
	
    private static WebApplicationContext springContext;  
    public static ServletContext servletContext;
    private static LoadCacheService loadCache;   
    

	public LoadCacheService(){  
        super();  
    }  
    
   /**
	* @method  getInstance
	* @Description: 加载缓存到application context
	* @author  zengli
	* @date 2016年4月17日 下午4:48:15
	* @parameter  
	* @return  StartLoadCacheService
	*/
	public static LoadCacheService getInstance()  {  
	     springContext = WebApplicationContextUtils .getWebApplicationContext(servletContext);  
	     if(null == loadCache)  
	       loadCache = (LoadCacheService)springContext.getBean("startLoadCacheService");  
	     return loadCache;  
	 }  
    
    /**
    * @method  loadAllData
    * @Description: 重启时加载数据到内存，重启时内存map已经为空，故直接加载即可
    * @author  zengli
    * @date 2016年4月17日 下午4:44:33
    * @parameter  
    * @return  void
     */
	@SuppressWarnings({ "rawtypes", "unused" })
	public void startLoadData() {
		// TODO Auto-generated method stub        
		logger.info("-----------------启动参数初始化加载程序 ---------------------------");
		logger.info("-----------------从数据库中加载参数        ----------------------------");
		ArrayList syslist = (ArrayList) cacheDao.getHDSSysList();
		logger.info("加载了"+syslist.size()+"条机构信息");
		ArrayList apilist = (ArrayList) cacheDao.getHDSAPIList();
		logger.info("加载了"+apilist.size()+"条接口信息");
		ArrayList ipwhitelist = (ArrayList) cacheDao.getHDSIPWhiteList();
		logger.info("加载了"+ipwhitelist.size()+"条系统IP白名单信息");
		ArrayList apisyslist = (ArrayList) cacheDao.getHDSApiSysList();
		logger.info("加载了"+apisyslist.size()+"条系统接口权限信息");
        logger.info("---------------- 初始化参数全部从数据库中取出  ----------------------");
		  
        //Map<String, ArrayList> map = new HashMap<String, ArrayList>();
      
        logger.info("---------------- 数据打包开始，准备放入man-cachemap  ----------------------");
        main_cacheMap.put(R.SystemConstant.CACHE_SYSLIST, syslist);
	    main_cacheMap.put(R.SystemConstant.CACHE_APILIST, apilist);
		main_cacheMap.put(R.SystemConstant.CACHE_IPWHITELIST, ipwhitelist);
	    main_cacheMap.put(R.SystemConstant.CACHE_APISYSLIST, apisyslist);
	  //  main_cacheMap.put(R.SystemConstant., map);
	    
		logger.info("---------------- 数据打包完毕，全部放入main-cachemap  ----------------------");
		 
		  
        logger.info("---------------- 数据打包开始，准备放入bk-cachemap  ----------------------");
        bk_cacheMap.put(R.SystemConstant.CACHE_SYSLIST, syslist);
        bk_cacheMap.put(R.SystemConstant.CACHE_APILIST, apilist);
        bk_cacheMap.put(R.SystemConstant.CACHE_IPWHITELIST, ipwhitelist);
        bk_cacheMap.put(R.SystemConstant.CACHE_APISYSLIST, apisyslist);
		logger.info("---------------- 数据打包完毕，全部放入bk-cachemap  ----------------------");
		  
		  
		logger.info("---------------- 设置默认的使用的cachemap  ----------------------");
		current_cachMap.put(R.SystemConstant.CACHE_CURRENTMAP, R.SystemConstant.CACHE_MAINMAP);
		 logger.error("当前加载的cachemap为:"
  				 +current_cachMap.get(R.SystemConstant.CACHE_CURRENTMAP));
	}

/**
* @method  loadMainCache
* @Description:更新主缓存 具体步骤如下：
*                         1.清空 主缓存
*                         2.加载数据库信息
*                         3.将数据库信息加载到主缓存中
*                        // 4.清空记录当前缓存名称map   
*                        // 5.进行map的切换
* @author  zengli
* @date 2016年4月20日 下午3:27:08
* @parameter  
* @return  void
 */
	public void reloadMainCache() {
		// TODO Auto-generated method stub       
		logger.info("--------------------   清空备用缓存数据       ----------------------------");
		main_cacheMap.clear();
		logger.info("--------------------   加载数据库信息       ----------------------------");
		@SuppressWarnings("rawtypes")
		ArrayList syslist = (ArrayList) cacheDao.getHDSSysList();
		logger.info("加载了"+syslist.size()+"条机构信息");
		@SuppressWarnings("rawtypes")
		ArrayList apilist = (ArrayList) cacheDao.getHDSAPIList();
		logger.info("加载了"+apilist.size()+"条接口信息");
		@SuppressWarnings("rawtypes")
		ArrayList ipwhitelist = (ArrayList) cacheDao.getHDSIPWhiteList();
		logger.info("加载了"+ipwhitelist.size()+"条系统IP白名单信息");
		@SuppressWarnings("rawtypes")
		ArrayList apisyslist = (ArrayList) cacheDao.getHDSApiSysList();
		logger.info("加载了"+apisyslist.size()+"条系统接口权限信息");
        logger.info("--------------------   数据库信息加载完毕  ----------------------");
		  
        logger.info("---------------- 数据打包开始，准备放入main-cachemap  ----------------------");
        main_cacheMap.put(R.SystemConstant.CACHE_SYSLIST, syslist);
	    main_cacheMap.put(R.SystemConstant.CACHE_APILIST, apilist);
		main_cacheMap.put(R.SystemConstant.CACHE_IPWHITELIST, ipwhitelist);
	    main_cacheMap.put(R.SystemConstant.CACHE_APISYSLIST, apisyslist);
		logger.info("---------------- 数据打包完毕，全部放入main-cachemap  ----------------------");
	  
	//	logger.info("---------------- 设置默认的使用的cachemap  ----------------------");
	//	current_cachMap.clear();
	//	current_cachMap.put("current_cachemap", "main_cacheMap");
		  
	}
	
	/**
	* @method  reloadBkCache
	* @Description:更新备用缓存 具体步骤如下：
	*                         1.清空 备用缓存
	*                         2.加载数据库信息
	*                         3.将数据库信息加载到备用缓存中
	*                        // 4.清空记录当前缓存名称map   
	*                        // 5.进行map的切换
	* @author  zengli
	* @date 2016年4月20日 下午3:27:08
	* @parameter  
	* @return  void
	 */
		@SuppressWarnings({ "rawtypes" })
		public void reloadBkCache() {
	
			// TODO Auto-generated method stub    
			logger.info("--------------------   清空备用缓存数据       ----------------------------");
			bk_cacheMap.clear();
			logger.info("--------------------   加载数据库信息           ----------------------------");
			ArrayList syslist = (ArrayList) cacheDao.getHDSSysList();
			logger.info("加载了"+syslist.size()+"条机构信息");
			ArrayList apilist = (ArrayList) cacheDao.getHDSAPIList();
			logger.info("加载了"+apilist.size()+"条接口信息");
			ArrayList ipwhitelist = (ArrayList) cacheDao.getHDSIPWhiteList();
			logger.info("加载了"+ipwhitelist.size()+"条系统IP白名单信息");
			ArrayList apisyslist = (ArrayList) cacheDao.getHDSApiSysList();
			logger.info("加载了"+apisyslist.size()+"条系统接口权限信息");
	        logger.info("--------------------   数据库信息加载完毕  ----------------------");
			 
	        logger.info("---------------- 数据打包开始，准备放入bk-cachemap  ----------------------");
	        bk_cacheMap.put(R.SystemConstant.CACHE_SYSLIST, syslist);
	        bk_cacheMap.put(R.SystemConstant.CACHE_APILIST, apilist);
	        bk_cacheMap.put(R.SystemConstant.CACHE_IPWHITELIST, ipwhitelist);
	        bk_cacheMap.put(R.SystemConstant.CACHE_APISYSLIST, apisyslist);
			logger.info("---------------- 数据打包完毕，全部放入bk-cachemap  ----------------------");

			//current_cachMap.clear();
			//current_cachMap.put("current_cachemap", "bk_cacheMap");
		}
    
    /**
    * @method  reloadCacheMap
    * @Description: 判断当前缓存存储的map，
    *                          数据加载到当前缓存map相对的备用缓存map(例如，当前缓存为bk_cachemap，则将数据加载到main_cachemap上)
    *                          将加载后的map作为当前的缓存存储map，供authValidation查询，authValidation只需在意当前的缓存map即可
    * @author  zengli
    * @date 2016年4月17日 下午4:46:33
    * @parameter  
    * @return  void
     */
    public   int  reloadCacheMap()  {  
    	logger.info("---------------- 系统开始校验需要清理的map----------------------");
    	//获得当前的map名称
    	String currentMap = current_cachMap.get(R.SystemConstant.CACHE_CURRENTMAP);
    	//如果当前使用的缓存map为main_map，则清空并加载bk_map
    	//bk_map数据加载完成前   当前使用的缓存map为main_map
    	//bk_map数据加载完成后  当前使用的缓存map为bk_map
    	if(currentMap.equals(R.SystemConstant.CACHE_MAINMAP)){
    		//加载备用缓存数据
    		reloadBkCache() ;
    		//清空记录当前缓存名称的map
			current_cachMap.clear();
			//清空记录当前缓存名称的map
			current_cachMap.put(R.SystemConstant.CACHE_CURRENTMAP, 
					R.SystemConstant.CACHE_BKMAP);
			logger.info("当前缓存map为:"+R.SystemConstant.CACHE_BKMAP);
			return 1;
    	}
    	//如果当前使用的缓存map为bk_map，则清空并加载main_map
    	//main_map数据加载完成前   当前使用的缓存map为bk_map
    	//main_map数据加载完成后  当前使用的缓存map为main_map
      	if(currentMap.equals(R.SystemConstant.CACHE_BKMAP)){
    		//加载备用缓存数据
    		reloadBkCache() ;
			current_cachMap.clear();
			current_cachMap.put(R.SystemConstant.CACHE_CURRENTMAP, 
					R.SystemConstant.CACHE_MAINMAP);
			logger.info("当前缓存map为:"+R.SystemConstant.CACHE_MAINMAP);
			return 2;
    	}
      	else{
      		
      		 logger.error("当前无缓存map/或者map名称不正确,map大小为："+current_cachMap.size()+'|'
      				 +current_cachMap.get(R.SystemConstant.CACHE_CURRENTMAP));
      		 return 0;
      	}
    
    }

	@SuppressWarnings("rawtypes")
	public static Map<String, ArrayList> getMain_cacheMap() {
		return main_cacheMap;
	}

	@SuppressWarnings("rawtypes")
	public static void setMain_cacheMap(Map<String, ArrayList> main_cacheMap) {
		LoadCacheService.main_cacheMap = main_cacheMap;
	}

	@SuppressWarnings("rawtypes")
	public static Map<String, ArrayList> getBk_cacheMap() {
		return bk_cacheMap;
	}

	@SuppressWarnings("rawtypes")
	public static void setBk_cacheMap(Map<String, ArrayList> bk_cacheMap) {
		LoadCacheService.bk_cacheMap = bk_cacheMap;
	}


	public static Map<String, String> getCurrent_cachMap() {
		return current_cachMap;
	}


	public static void setCurrent_cachMap(Map<String, String> current_cachMap) {
		LoadCacheService.current_cachMap = current_cachMap;
	}
	
	
	
	public ReloadCacheResponse reloadCache(ReloadCacheRequest rcrqVo,BindingResult result,
			ResponseResult  rr_req_frequency) throws HDSServiceException {

		//获取本接口API 标志常量
	    String api_id = AuthValidation.APIConstant.API_RELOAD_CACHE;

		//--------------------------参数格式/参数校验   开始--------------------------------------------------------------------------------
		// 获取请求参数校验结果
		List<ResponseResult> list_rprr = RequestParamValidator
				.requestParamValidate(result, rcrqVo.getRequest_id());
		//debug日志(可去除)
		logger.info("参数格式校验返回结果数量:" + list_rprr.size());
		//若返回结果为空,抛出异常,避免空指针
		if (list_rprr.size() == 0) {
			logger.error(rcrqVo.getRequest_id()+"|"+rcrqVo.getRequest_ip()+"|"+
					R.HDSErrorEnum.ERROR_REQPRM_RESRESULT.getLabel());	
			throw new HDSServiceException(
					R.HDSErrorEnum.ERROR_REQPRM_RESRESULT.getValue(),
					R.HDSErrorEnum.ERROR_REQPRM_RESRESULT.getLabel());
		}

		//参数校验异常处理
		//下列代表参数校验/逻辑校验为错误
		if(!list_rprr.get(0).getResult_code().equals(RequestValidation.ResultCode.REQUEST_PARAM_VALID)){
			//创建查询返回对象
			ReloadCacheResponse  respvo = new ReloadCacheResponse();
			respvo.setRequest_id(rcrqVo.getRequest_id());
			respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(rcrqVo);
			respvo.setReponse_result(list_rprr);
			//打印日志
			logger.info("request_id:"+rcrqVo.getRequest_id()+"请求校验失败,"
					+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
			return respvo;
		}
		//--------------------------参数格式     结束--------------------------------------------------------------------------------		
		//--------------------------权限逻辑校验     开始--------------------------------------------------------------------------------
		// 获取请求权限校验结果
		List<ResponseResult> list_authrr = AuthValidator.authValidate(
				rcrqVo.getSysid(), api_id, rcrqVo.getRequest_ip(),
				rcrqVo.getRequest_id());
		//debug日志(可去除)
		logger.info("请求权限校验返回结果数量:" + list_authrr.size());
		//若返回结果为空,抛出异常,避免空指针
		if (list_authrr.size() == 0) {
			logger.error(rcrqVo.getRequest_id()+"|"+rcrqVo.getRequest_ip()+"|"+
					R.HDSErrorEnum.ERROR_REQAUTH_RESRESULT.getLabel());	
			throw new HDSServiceException(
					R.HDSErrorEnum.ERROR_REQAUTH_RESRESULT.getValue(),
					R.HDSErrorEnum.ERROR_REQAUTH_RESRESULT.getLabel());
		}
		
		//参数校验成功，权限校验失败
		if(list_rprr.get(0).getResult_code().equals(RequestValidation.ResultCode.REQUEST_PARAM_VALID)&&
				!list_authrr.get(0).getResult_code().equals(AuthValidation.ResultCode.AUTH_IS_VALID)){

			//创建查询返回对象
			ReloadCacheResponse  respvo = new ReloadCacheResponse();
			respvo.setRequest_id(rcrqVo.getRequest_id());
			respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(rcrqVo);
			respvo.setReponse_result(list_authrr); 
			//打印日志
			logger.info("request_id:"+rcrqVo.getRequest_id()+"请求校验失败,"
					+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
			return respvo;
		}
        //--------------------------权限逻辑校验        结束--------------------------------------------------------------------------------			

        //--------------------------ip访问频率校验    开始--------------------------------------------------------------------------------
		if(list_rprr.get(0).getResult_code().equals(RequestValidation.ResultCode.REQUEST_PARAM_VALID)&&
				list_authrr.get(0).getResult_code().equals(AuthValidation.ResultCode.AUTH_IS_VALID)&&
				!rr_req_frequency.getResult_code().equals(RequestValidation.ResultCode.FREQUENCY_IS_VALID)){
			
			List<ResponseResult> list_reqrr= new ArrayList<ResponseResult>();
			list_reqrr.add(rr_req_frequency);
			
			//创建查询返回对象
			ReloadCacheResponse  respvo = new ReloadCacheResponse();
			respvo.setRequest_id(rcrqVo.getRequest_id());
			respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(rcrqVo);
			respvo.setReponse_result(list_reqrr); //返回权限校验错误对象
			//打印日志
			logger.info("request_id:"+rcrqVo.getRequest_id()+"请求校验失败,"
					+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
			return respvo;
		}
        //--------------------------ip访问频率校验    结束------------	

	    //--------------------------异常状处理     开始----------------
		//全部校验成功
		if(list_authrr.get(0).getResult_code().equals(AuthValidation.ResultCode.AUTH_IS_VALID)&&
				rr_req_frequency.getResult_code().equals(RequestValidation.ResultCode.FREQUENCY_IS_VALID)&&
				list_rprr.get(0).getResult_code().equals(RequestValidation.ResultCode.REQUEST_PARAM_VALID)){	

			    //重新加载
			   int code =  reloadCacheMap();
				List<ResponseResult> list_rr = new ArrayList<ResponseResult>();
			   
			   if(code == 1){
					ResponseResult rr = new ResponseResult();
					rr.setType(R.SystemConstant.REQUEST_HANDLE);
					rr.setResult_code(R.RequestSucess.REQUEST_HANDLE_SUCCESS);
					rr.setField(R.RequestSucess.REQUEST_HANDLE_FIELD);
					rr.setMessage(R.RequestSucess.REQUEST_HANDLE_SUCCESSMSG);
					list_rr.add(rr);
			   }
			   if(code ==2){
					ResponseResult rr = new ResponseResult();
					rr.setType(R.SystemConstant.REQUEST_HANDLE);
					rr.setResult_code(R.RequestSucess.REQUEST_HANDLE_SUCCESS);
					rr.setField(R.RequestSucess.REQUEST_HANDLE_FIELD);
					rr.setMessage(R.RequestSucess.REQUEST_HANDLE_SUCCESSMSG);
					list_rr.add(rr);
			   }
			   if(code ==0 ){
					ResponseResult rr = new ResponseResult();
					rr.setType(R.SystemConstant.REQUEST_HANDLE);
					rr.setResult_code(R.RequestSucess.REQUEST_HANDLE_ERROR);
					rr.setField(R.RequestSucess.REQUEST_CAHCEMAP_FIELD);
					rr.setMessage(R.RequestSucess.REQUEST_CAHCEMAP_ERRORMSG);
					list_rr.add(rr);
			   }
			   
	            ReloadCacheResponse  respvo = new ReloadCacheResponse();
				respvo.setRequest_id(rcrqVo.getRequest_id());
				respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
				respvo.setRequest_param(rcrqVo);
				respvo.setReponse_result(list_rr);
				//打印日志
				logger.info("request_id:"+rcrqVo.getRequest_id()+"請求校驗成功,"
						+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
			   
				return respvo;
			}
	    //--------------------------全部校验成功  结束----------------
		     
		
	   //--------------------------异常状处理     开始----------------
	   //若不符合上述各种状态，则添加所有的校验信息，并打印出数据
	   //添加逻辑校验结果
     	list_rprr.add(rr_req_frequency);
	     //拼接所有的查询条件
	    for (int i = 0; i < list_authrr.size(); i++) {
               ResponseResult rr_autherr = list_authrr.get(i);
               list_rprr.add(rr_autherr);
		}
	    ReloadCacheResponse  respvo = new ReloadCacheResponse();
		respvo.setRequest_id(rcrqVo.getRequest_id());
		respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
		respvo.setRequest_param(rcrqVo);
		respvo.setReponse_result(list_rprr);
		//打印日志
		logger.info("request_id:"+rcrqVo.getRequest_id()+"请求校验失败,"
				+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
	    return respvo;
	    //--------------------------异常状处理     結束----------------
		}		


		

	
    
}
