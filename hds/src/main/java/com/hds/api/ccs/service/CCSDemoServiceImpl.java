package com.hds.api.ccs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.hds.api.ccs.vo.CCSDemoRequestVo;
import com.hds.api.ccs.vo.CCSDemoResponseVo;
import com.hds.api.common.constant.AuthValidation;
import com.hds.api.common.constant.LogicValidation;
import com.hds.api.common.constant.R;
import com.hds.api.common.constant.RequestValidation;
import com.hds.api.common.util.DateUtils;
import com.hds.api.common.validate.AuthValidator;
import com.hds.api.common.validate.LogicValidator;
import com.hds.api.common.validate.RequestParamValidator;
import com.hds.api.sys.vo.RecordRow;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.ccs.service.impl.CCSServiceImpl
* @Description: ccs查询  service接口实现类
* @author zengli
* @date 创建时间：2016年4月17日 下午3:52:49
 */
@Service("CCSDemoService")
public class CCSDemoServiceImpl implements CCSDemoService {

	private static Logger logger = Logger.getLogger(CCSDemoServiceImpl.class);

	
	
	/**
	* @method  getCCSDemo
	* @Description: getCCSDemo 接口实现方法
	* @author  zengli
	* @date  2016年4月17日 下午3:41:23
	* @parameter  CCSDemoRequestVo ccsVo                 查询请求对象
	* @parameter  BindingResult result                           绑定的校验结果
	* @parameter  ResponseResult  rr_req_frequency    频率校验请求结果
	* @return  CCSDemoResponseVo
	 */
	@Override
	public CCSDemoResponseVo getCCSDemo(CCSDemoRequestVo ccsVo,BindingResult result,
			ResponseResult  rr_req_frequency) {
		// TODO Auto-generated method stub

		// 获取本接口API 标志常量
		String api_id = AuthValidation.APIConstant.API_CCS_GETCCSDEMO;

		// 从内存中获取阿里云OTS配置常量对象
		//OTSConfig ots_config = (OTSConfig) ApplicationContextHelper
			//	.getObject("OTSConfig");

		// --------------------------参数格式/参数校验
		// 开始--------------------------------------------------------------------------------
		// 参数校验
		List<ResponseResult> list_rprr = RequestParamValidator
				.requestParamValidate(result, ccsVo.getRequest_id());
		logger.info("参数校验返回结果数量:" + list_rprr.size());

		// 参数校验异常处理
		// 下列代表参数校验/逻辑校验为错误
		if (!list_rprr.get(0).getResult_code()
				.equals(RequestValidation.ResultCode.REQUEST_PARAM_VALID)) {
			// 创建查询返回对象
			CCSDemoResponseVo respvo = new CCSDemoResponseVo();
			respvo.setRequest_id(ccsVo.getRequest_id());
			respvo.setSysid(ccsVo.getSysid());
			respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(ccsVo);
			respvo.setResponse_records(null);
			respvo.setReponse_result(list_rprr);

			for (int i = 0; i < list_rprr.size(); i++) {
				logger.info(list_rprr.get(i).toString());
			}
			logger.info("------------------------参数格式校验失败-----------------------------");
			return respvo;
		}
		// --------------------------参数格式
		// 结束--------------------------------------------------------------------------------

		// --------------------------权限逻辑校验
		// 开始--------------------------------------------------------------------------------
		// 权限校验
		List<ResponseResult> list_authrr = AuthValidator.authValidate(
				ccsVo.getSysid(), api_id, ccsVo.getRequest_ip(),
				ccsVo.getRequest_id());
		logger.info("权限校验返回结果数量:" + list_authrr.size());

		// 参数校验成功，权限校验失败
		if (list_rprr.get(0).getResult_code()
				.equals(RequestValidation.ResultCode.REQUEST_PARAM_VALID)
				&& !list_authrr.get(0).getResult_code()
						.equals(AuthValidation.ResultCode.AUTH_IS_VALID)) {

			// 创建查询返回对象
			CCSDemoResponseVo respvo = new CCSDemoResponseVo();
			respvo.setRequest_id(ccsVo.getRequest_id());
			respvo.setSysid(ccsVo.getSysid());
			respvo.setResponsetime(DateUtils
					.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(ccsVo);
			respvo.setResponse_records(null);
			respvo.setReponse_result(list_authrr); // 返回权限校验错误对象
			logger.info("------------------------参数格式校验成功 权限校验失败-----------------------------");
			return respvo;
		}
		// --------------------------权限逻辑校验
		// 结束--------------------------------------------------------------------------------

		// --------------------------ip访问频率校验
		// 开始--------------------------------------------------------------------------------
		if (list_rprr.get(0).getResult_code()
				.equals(RequestValidation.ResultCode.REQUEST_PARAM_VALID)
				&& list_authrr.get(0).getResult_code()
						.equals(AuthValidation.ResultCode.AUTH_IS_VALID)
				&& !rr_req_frequency.getResult_code().equals(
						RequestValidation.ResultCode.FREQUENCY_IS_VALID)) {

			List<ResponseResult> list_reqrr = new ArrayList<ResponseResult>();
			list_reqrr.add(rr_req_frequency);

			// 创建查询返回对象
			CCSDemoResponseVo respvo = new CCSDemoResponseVo();
			respvo.setRequest_id(ccsVo.getRequest_id());
			respvo.setSysid(ccsVo.getSysid());
			respvo.setResponsetime(DateUtils
					.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(ccsVo);
			respvo.setResponse_records(null);
			respvo.setReponse_result(list_reqrr); // 返回权限校验错误对象
			logger.info("------------------------参数格式 权限校验成功    请求频率校验失败----------------------------");
			return respvo;
		}
		// --------------------------ip访问频率校验
		// 结束--------------------------------------------------------------------------------

		// --------------------------参数逻辑校验
		// 开始--------------------------------------------------------------------------------
		// 时间逻辑校验
		ResponseResult rr_date = LogicValidator.DateValidate(
				ccsVo.getStartday(), ccsVo.getEndday(),
				LogicValidation.Constant.CCSDEMO_DAYS_LAG,
				ccsVo.getRequest_id());
		// 参数校验成功，参数逻辑校验失败
		if (list_authrr.get(0).getResult_code()
				.equals(AuthValidation.ResultCode.AUTH_IS_VALID)
				&& rr_req_frequency.getResult_code().equals(
						RequestValidation.ResultCode.FREQUENCY_IS_VALID)
				&& list_rprr
						.get(0)
						.getResult_code()
						.equals(RequestValidation.ResultCode.REQUEST_PARAM_VALID)
				&& !rr_date.getResult_code().equals(
						LogicValidation.ResultCode.LOGIC_IS_VALID)) {

			List<ResponseResult> list_logicrr = new ArrayList<ResponseResult>();
			list_logicrr.add(rr_date);

			// 创建查询返回对象
			CCSDemoResponseVo respvo = new CCSDemoResponseVo();
			respvo.setRequest_id(ccsVo.getRequest_id());
			respvo.setSysid(ccsVo.getSysid());
			respvo.setResponsetime(DateUtils
					.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(ccsVo);
			respvo.setResponse_records(null);
			respvo.setReponse_result(list_rprr);
			logger.info("------------------------权限、请求频率、参数格式校验成功   参数逻辑校验失败------------------------------");
			return respvo;
		}
		// --------------------------参数逻辑校验
		// 结束--------------------------------------------------------------------------------

		// --------------------------全部校验成功
		// 开始--------------------------------------------------------------------------------
		// 全部校验成功
		if (list_authrr.get(0).getResult_code()
				.equals(AuthValidation.ResultCode.AUTH_IS_VALID)
				&& rr_req_frequency.getResult_code().equals(
						RequestValidation.ResultCode.FREQUENCY_IS_VALID)
				&& list_rprr
						.get(0)
						.getResult_code()
						.equals(RequestValidation.ResultCode.REQUEST_PARAM_VALID)
				&& rr_date.getResult_code().equals(
						LogicValidation.ResultCode.LOGIC_IS_VALID)) {

			// 查询ots并封装返回对象

			// 系列待定，可能直接全部从传入到ots方法中获得最终结果
			List<ResponseResult> list_rr = new ArrayList<ResponseResult>();
			ResponseResult rr = new ResponseResult();
			rr.setType(R.SystemConstant.RESTYPE_ALL_VALID);
			rr.setResult_code(R.RequestSucess.ALL_IS_VALID);
			rr.setField(R.RequestSucess.FIELD_ALL_VALID);
			rr.setMessage(R.RequestSucess.ALL_VALID_SUCCESSMSG);
			list_rr.add(rr);

			CCSDemoResponseVo respvo = new CCSDemoResponseVo();
			respvo.setRequest_id(ccsVo.getRequest_id());
			respvo.setSysid(ccsVo.getSysid());
			respvo.setResponsetime(DateUtils
					.toString(R.DateConstant.DATETIME_FULL));
			respvo.setRequest_param(ccsVo);

			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("startday", ccsVo.getStartday());
			map1.put("endday", ccsVo.getEndday());
			map1.put("testno", "00001");
			RecordRow row1 = new RecordRow();
			row1.setRow(map1);

			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("startday", ccsVo.getStartday());
			map2.put("endday", ccsVo.getEndday());
			map2.put("testno", "00002");
			RecordRow row2 = new RecordRow();
			row2.setRow(map2);

			List<RecordRow> list = new ArrayList<RecordRow>();
			list.add(row1);
			list.add(row2);
			respvo.setResponse_records(list);

			respvo.setResponse_records(null);
			respvo.setReponse_result(list_rr);
			logger.info("------------------------参数、参数逻辑、权限校验成功-----------------------------");
			return respvo;
		}
		// --------------------------全部校验成功
		// 结束--------------------------------------------------------------------------------

		// --------------------------异常状处理
		// 开始--------------------------------------------------------------------------------

		// 若不符合上述各种状态，则添加所有的校验信息，并打印出数据
		// 添加逻辑校验结果
		list_rprr.add(rr_date);
		list_rprr.add(rr_req_frequency);
		// 拼接所有的查询条件
		for (int i = 0; i < list_authrr.size(); i++) {
			ResponseResult rr_autherr = list_authrr.get(i);
			list_rprr.add(rr_autherr);
		}
		CCSDemoResponseVo respvo = new CCSDemoResponseVo();
		respvo.setRequest_id(ccsVo.getRequest_id());
		respvo.setSysid(ccsVo.getSysid());
		respvo.setResponsetime(DateUtils.toString(R.DateConstant.DATETIME_FULL));
		respvo.setRequest_param(ccsVo);
		respvo.setResponse_records(null);
		respvo.setReponse_result(list_rprr);
		return respvo;
		// --------------------------异常状处理
		// 结束--------------------------------------------------------------------------------
		}

}
