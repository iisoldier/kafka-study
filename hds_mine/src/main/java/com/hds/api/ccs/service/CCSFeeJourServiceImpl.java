package com.hds.api.ccs.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.alibaba.fastjson.JSON;
import com.hds.api.ccs.dao.CCSFeeJourDao;
import com.hds.api.ccs.vo.FeeJourRequest;
import com.hds.api.ccs.vo.FeeJourResponse;
import com.hds.api.common.constant.R;
import com.hds.api.common.constant.RequestValidation;
import com.hds.api.common.util.ApplicationContextHelper;
import com.hds.api.common.util.DateUtils;
import com.hds.api.common.validate.AuthValidator;
import com.hds.api.common.validate.LogicValidator;
import com.hds.api.common.validate.RequestParamValidator;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.OTSConfig;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.ccs.service.CCSFeeJourServiceImpl
* @Description:费用流水查询接口
* @author jinmeng
* @date 创建时间：2016年4月27日 下午8:10:29
 */
@Service("CCSFeeJourService")
public class CCSFeeJourServiceImpl implements CCSFeeJourService {

	private static Logger logger = Logger.getLogger(CCSFeeJourServiceImpl.class);

	
  @Autowired
   private CCSFeeJourDao ccsFeeJourDao;
	
	

	/**
	* @method  getCCSCCSFeeJour
	* @Description: getCCSFeeJour 接口实现方法  费用流水查询
	* @author  jinmeng
	* @date  2016年4月17日 下午3:41:23
	* @parameter  FeeJourRequest fsoVo                 查询请求对象
	* @parameter  BindingResult result                           绑定的校验结果
	* @parameter  ResponseResult  rr_req_frequency    频率校验请求结果
	* @return  FeeJourResponse
	 * @throws HDSServiceException 
	 */
  
	@Override
	public FeeJourResponse getFeeJour(FeeJourRequest fjVo,
			BindingResult result, ResponseResult rr_req_frequency) throws HDSServiceException {
		// TODO Auto-generated method stub
		//获取本接口API 标志常量
		String api_id = R.APIConstant.API_CCS_GetFeeJour;

		// 从内存中获取阿里云OTS配置常量对象
		OTSConfig ots_config = (OTSConfig) ApplicationContextHelper
				.getObject("OTSConfig");
       //如果常量信息错误，则说明有有问题
		if(ots_config.equals(null)){
			throw new HDSServiceException(
					R.HDSErrorEnum.ERROR_OTSCFG_NULL.getValue(),
					R.HDSErrorEnum.ERROR_OTSCFG_NULL.getLabel());	
		}

		// -------------------------请求参数格式校验开始---------------------
		// 获取请求参数校验结果
		List<ResponseResult> list_rprr = RequestParamValidator
				.requestParamValidate(result, fjVo.getRequest_id());
		//debug日志(可去除)
		logger.info("参数格式校验返回结果数量:" + list_rprr.size());
		//若返回结果为空,抛出异常,避免空指针
		if (list_rprr.size() == 0) {
			logger.error(fjVo.getRequest_id()+"|"+fjVo.getRequest_ip()+"|"+
					R.HDSErrorEnum.ERROR_REQPRM_RESRESULT.getLabel());	
			throw new HDSServiceException(
					R.HDSErrorEnum.ERROR_REQPRM_RESRESULT.getValue(),
					R.HDSErrorEnum.ERROR_REQPRM_RESRESULT.getLabel());
		}
		
		// 参数校验异常处理 (下列代表参数格式校验为错误)
		if (!list_rprr.get(0).getResult_code().equals(RequestValidation.ResultCode.PARAM_IS_VALID)) {
		
		
			// 创建查询返回对象
			FeeJourResponse respvo = new FeeJourResponse();
			respvo.setRequest_id(fjVo.getRequest_id());
			respvo.setSysid(fjVo.getSysid());
			respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(fjVo);
			respvo.setResponse_records(null);
			respvo.setReponse_result(list_rprr);
			//打印日志
			logger.info("request_id:"+fjVo.getRequest_id()+"请求校验失败,"
					+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
			return respvo;
		}
		// -------------------------参数参数格式校验结束---------------------

		// -------------------------请求权限校验开始---------------------------
		// 获取请求权限校验结果
		List<ResponseResult> list_authrr = AuthValidator.authValidate(
				fjVo.getSysid(), api_id, fjVo.getRequest_ip(),
				fjVo.getRequest_id());
		//debug日志(可去除)
		logger.info("请求权限校验返回结果数量:" + list_authrr.size());
		//若返回结果为空,抛出异常,避免空指针
		if (list_authrr.size() == 0) {
			logger.error(fjVo.getRequest_id()+"|"+fjVo.getRequest_ip()+"|"+
					R.HDSErrorEnum.ERROR_REQAUTH_RESRESULT.getLabel());	
			throw new HDSServiceException(
					R.HDSErrorEnum.ERROR_REQAUTH_RESRESULT.getValue(),
					R.HDSErrorEnum.ERROR_REQAUTH_RESRESULT.getLabel());
		}
		
		// 请求参数格式校验成功，请求权限校验失败
		if (list_rprr.get(0).getResult_code().equals(RequestValidation.ResultCode.PARAM_IS_VALID)
				&& !list_authrr.get(0).getResult_code().equals(RequestValidation.ResultCode.AUTH_IS_VALID)) {
			// 创建查询返回对象
			FeeJourResponse respvo = new FeeJourResponse();
			respvo.setRequest_id(fjVo.getRequest_id());
			respvo.setSysid(fjVo.getSysid());
			respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(fjVo);
			respvo.setResponse_records(null);
			respvo.setReponse_result(list_authrr); // 返回权限校验错误对象
			//打印日志
			logger.info("request_id:"+fjVo.getRequest_id()+"请求校验失败,"
					+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
			return respvo;
		}
		// -------------------------请求权限校验结束---------------------------

		// --------------------  请求ip访问频率校验开始------------------------
		if (list_rprr.get(0).getResult_code().equals(RequestValidation.ResultCode.PARAM_IS_VALID)
				&& list_authrr.get(0).getResult_code().equals(RequestValidation.ResultCode.AUTH_IS_VALID)
				&& !rr_req_frequency.getResult_code().equals(RequestValidation.ResultCode.FREQUENCY_IS_VALID)) {
			//拼装list返回对象
			List<ResponseResult> list_reqrr = new ArrayList<ResponseResult>();
			list_reqrr.add(rr_req_frequency);

			// 创建查询返回对象
			FeeJourResponse respvo = new FeeJourResponse();
			respvo.setRequest_id(fjVo.getRequest_id());
			respvo.setSysid(fjVo.getSysid());
			respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(fjVo);
			respvo.setResponse_records(null);
			respvo.setReponse_result(list_reqrr); // 返回权限校验错误对象
			//打印日志
			logger.info("request_id:"+fjVo.getRequest_id()+"请求校验失败,"
					+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
			return respvo;
		}
		// --------------------  请求ip访问频率校验开始------------------------

		// --------------------  请求参数逻辑校验开始------------------------
		// 时间逻辑校验
		ResponseResult rr_date = LogicValidator.
				DateValidate(fjVo.getS_occur_date(),fjVo.getE_occur_date(),
						RequestValidation.Constant.DAYS_LAG_CCSFEEJOUR,
						fjVo.getRequest_id());
		// 请求参数格式-请求权限-请求频率校验成功，请求参数逻辑校验失败
		if (list_rprr.get(0).getResult_code().equals(RequestValidation.ResultCode.PARAM_IS_VALID)
			&&list_authrr.get(0).getResult_code().equals(RequestValidation.ResultCode.AUTH_IS_VALID)
			&& rr_req_frequency.getResult_code().equals(RequestValidation.ResultCode.FREQUENCY_IS_VALID)
            && !rr_date.getResult_code().equals(RequestValidation.ResultCode.LOGIC_IS_VALID)){

			List<ResponseResult> list_logicrr = new ArrayList<ResponseResult>();
			list_logicrr.add(rr_date);

			// 创建查询返回对象
			FeeJourResponse respvo = new FeeJourResponse();
			respvo.setRequest_id(fjVo.getRequest_id());
			respvo.setSysid(fjVo.getSysid());
			respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(fjVo);
			respvo.setResponse_records(null);
			respvo.setReponse_result(list_logicrr);
			//打印日志
			logger.info("request_id:"+fjVo.getRequest_id()+"请求校验失败,"
					+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
			return respvo;
		}
		// --------------------  请求参数逻辑校验结束------------------------

		// --------------------  请求校验全部成功开始-----------------------
		// 全部校验成功
		if ( list_rprr.get(0).getResult_code().equals(RequestValidation.ResultCode.PARAM_IS_VALID)
		    && list_authrr.get(0).getResult_code().equals(RequestValidation.ResultCode.AUTH_IS_VALID)
			&& rr_req_frequency.getResult_code().equals(RequestValidation.ResultCode.FREQUENCY_IS_VALID)
			&& rr_date.getResult_code().equals(RequestValidation.ResultCode.LOGIC_IS_VALID)) {

			// 查询ots并封装返回对象

			FeeJourResponse respvo = ccsFeeJourDao.getFeeJour(fjVo, ots_config);
			
			
			// 系列待定，可能直接全部从传入到ots方法中获得最终结果
			// List<ResponseResult> list_rr = new ArrayList<ResponseResult>();
			// ResponseResult rr = new ResponseResult();
			// rr.setType(R.SystemConstant.RESTYPE_ALL_VALID);
			// rr.setResult_code(R.RequestSucess.ALL_IS_VALID);
			// rr.setField(R.RequestSucess.FIELD_ALL_VALID);
			// rr.setMessage(R.RequestSucess.ALL_VALID_SUCCESSMSG);
			// list_rr.add(rr);
			//
			// FeeSettleOrderResponse respvo = new FeeSettleOrderResponse();
			// respvo.setRequest_id(fsoVo.getRequest_id());
			// respvo.setSysid(fsoVo.getSysid());
			// respvo.setResponsetime(DateUtils
			// .toString(R.DateConstant.DATETIME_FULL));
			// respvo.setRequest_param(fsoVo);
			// respvo.setResponse_records(null);
			// respvo.setReponse_result(list_rr);
			//打印日志
			logger.info("request_id:"+fjVo.getRequest_id()+"请求校验成功,"
					+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
			return respvo;
		}
		// --------------------  请求校验全部成功结束------------------------

		// --------------------  异常校验状态处理开始------------------------
        // 若不符合上述各种状态，则添加所有的校验信息，并打印出数据
		//添加逻辑校验结果
		list_rprr.add(rr_date);
		list_rprr.add(rr_req_frequency);
		// 拼接所有的查询条件
		for (int i = 0; i < list_authrr.size(); i++) {
			ResponseResult rr_autherr = list_authrr.get(i);
			list_rprr.add(rr_autherr);
		}
		FeeJourResponse respvo = new FeeJourResponse();
		respvo.setRequest_id(fjVo.getRequest_id());
		respvo.setSysid(fjVo.getSysid());
		respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
		respvo.setRequest_param(fjVo);
		respvo.setResponse_records(null);
		respvo.setReponse_result(list_rprr);
		//异常处理情况
		logger.error("request_id:"+fjVo.getRequest_id()+"请求校验失败,"
				+"返回信息为:["+ JSON.toJSONString(respvo)+"]");
		return respvo;
		// --------------------  异常校验状态处理 结束 ------------------------
	}


		


}
