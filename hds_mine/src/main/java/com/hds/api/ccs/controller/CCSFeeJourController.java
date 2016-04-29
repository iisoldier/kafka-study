package com.hds.api.ccs.controller;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hds.api.ccs.service.CCSFeeJourService;
import com.hds.api.ccs.vo.FeeJourRequest;
import com.hds.api.ccs.vo.FeeJourResponse;
import com.hds.api.common.annontion.Frequency;
import com.hds.api.common.constant.R;
import com.hds.api.common.util.DateUtils;
import com.hds.api.common.util.NetworkUtil;
import com.hds.api.common.util.UUIDUtil;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.ccs.controller.CCSFeeJourController
* @Description:费用流水查询控制器
* @author jinmeng
* @date 创建时间：2016年4月27日 下午8:00:39
 */
@Controller
@RequestMapping("/ccs")
public class CCSFeeJourController {

	
	private static Logger logger = Logger.getLogger(CCSFeeJourController.class);
		
	//计费系统服务Service
	@Resource
	CCSFeeJourService  ccsFeeJourService;


	/**
	* @method  getFeeSettleOrder
	* @Description:  分润流水查询
	* @author  zengli
	* @date 2016年4月26日 下午3:33:33
	* @parameter  HttpServletRequest request  http请求
	* @parameter  CCSDemoRequestVo ccsVo   请求参数包成的对象，并进行校验
	* @parameter  BindingResult result             绑定的校验结果
	* @return  FeeSettleOrderResponse
	 */
	@RequestMapping(value="/getFeeJour")
	@Frequency(name=R.APIConstant.API_CCS_GetFeeJour,limit=1,time=3)
	public @ResponseBody FeeJourResponse getFeeJour(HttpServletRequest request,
			 @ModelAttribute("fjVo")@Valid FeeJourRequest fjVo,
			 BindingResult result) throws IOException, HDSServiceException{
				
		 //频率控制器反馈结果 
		ResponseResult rr_req_frequency =
				(ResponseResult) request.getAttribute(R.SystemConstant.RESTYPE_FREQUENCY);
		logger.info(request.getAttribute(R.SystemConstant.RESTYPE_FREQUENCY).toString());
		
		//设置请求request_id
		String request_id = UUIDUtil.get32BitUUIDString();
		
		//获得请求IP
		String request_ip = NetworkUtil.getRemoteAddr(request);
		//logger.info("Request_ip:"+request_ip+"|request URL:"+request.getRequestURL()+"|request URI:"+request.getRequestURI());

		//拼装请求对象
		fjVo.setRequest_id(request_id);
		fjVo.setRequest_ip(request_ip);

		//日志记录请求(请求id|请求时间|请求ip|请求接口|请求参数对象|请求拦截结果)
		logger.info(request_id+R.SystemConstant.LOG_OBJECT_DELIMITER+ 
				     DateUtils.toString(R.DateConstant.DATETIME_FULL)+
				    R.SystemConstant.LOG_OBJECT_DELIMITER+
				    request_ip+R.SystemConstant.LOG_OBJECT_DELIMITER+
				    R.APIConstant.API_CCS_GetFeeJour
				    +R.SystemConstant.LOG_OBJECT_DELIMITER+fjVo.toString()
				    +R.SystemConstant.LOG_OBJECT_DELIMITER+rr_req_frequency.toString());
		
		
		//调用Service服务
		FeeJourResponse  fjrVo = ccsFeeJourService.getFeeJour(fjVo, result, rr_req_frequency);
		
		return fjrVo;
		
	}
	


}

