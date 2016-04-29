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

import com.hds.api.ccs.service.CCSFenrunJourRealDetailService;
import com.hds.api.ccs.vo.FenrunJourRealDetailRequest;
import com.hds.api.ccs.vo.FenrunJourRealDetailResponse;
import com.hds.api.common.annontion.Frequency;
import com.hds.api.common.constant.R;
import com.hds.api.common.util.DateUtils;
import com.hds.api.common.util.NetworkUtil;
import com.hds.api.common.util.UUIDUtil;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.ccs.controller.CCSFenrunJourRealDetailController
* @Description:分润明细查询控制器
* @author jinmeng
* @date 创建时间：2016年4月26日 下午3:00:39
 */
@Controller
@RequestMapping("/ccs")
public class CCSFenrunJourRealDetailController {

	
	private static Logger logger = Logger.getLogger(CCSFenrunJourRealDetailController.class);
		
	//计费系统服务Service
	@Resource
	CCSFenrunJourRealDetailService  ccsFenrunJourRealDetailService;


	/**
	* @method  getFenrunJourRealDetail
	* @Description:  分润明细查询
	* @author  jinmeng
	* @date 2016年4月2日8 下午2:33:33
	* @parameter  HttpServletRequest request  http请求
	* @parameter  FenrunJourRealDetailRequest fjVo  请求参数包成的对象，并进行校验
	* @parameter  BindingResult result             绑定的校验结果
	* @return  FeeSettleOrderResponse
	 */
	@RequestMapping(value="/getFenrunJourRealDetail")
	@Frequency(name=R.APIConstant.API_CCS_GetFenrunJourRealDetail,limit=1,time=3)
	public @ResponseBody FenrunJourRealDetailResponse getFenrunJour(HttpServletRequest request,
			 @ModelAttribute("fjrdVo")@Valid FenrunJourRealDetailRequest fjrdVo,
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
		fjrdVo.setRequest_id(request_id);
		fjrdVo.setRequest_ip(request_ip);

		//日志记录请求(请求id|请求时间|请求ip|请求接口|请求参数对象|请求拦截结果)
		logger.info(request_id+R.SystemConstant.LOG_OBJECT_DELIMITER+ 
				     DateUtils.toString(R.DateConstant.DATETIME_FULL)+
				    R.SystemConstant.LOG_OBJECT_DELIMITER+
				    request_ip+R.SystemConstant.LOG_OBJECT_DELIMITER+
				    R.APIConstant.API_CCS_GetFenrunJourRealDetail
				    +R.SystemConstant.LOG_OBJECT_DELIMITER+fjrdVo.toString()
				    +R.SystemConstant.LOG_OBJECT_DELIMITER+rr_req_frequency.toString());
		
		
		//调用Service服务
		FenrunJourRealDetailResponse  fjrdrVo = ccsFenrunJourRealDetailService.getFenrunJourRealDetail(fjrdVo, result, rr_req_frequency);
		
		return fjrdrVo;
		
	}
	


}
