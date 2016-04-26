package com.hds.api.sys.controller;
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
import com.hds.api.common.annontion.Frequency;
import com.hds.api.common.constant.AuthValidation;
import com.hds.api.common.constant.R;
import com.hds.api.common.util.DateUtils;
import com.hds.api.common.util.NetworkUtil;
import com.hds.api.common.util.UUIDUtil;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.service.LoadCacheService;
import com.hds.api.sys.vo.ReloadCacheRequest;
import com.hds.api.sys.vo.ReloadCacheResponse;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.ccs.controller.CCSDemoController
* @Description: 计费系统查询控制器
* @author zengli
* @date 创建时间：2016年4月17日 下午3:42:21
 */
@Controller
@RequestMapping("/sys")
public class SysController {

	
	private static Logger logger = Logger.getLogger(SysController.class.getName());
		

	//缓存信息的数据加载Service
	@Resource
	LoadCacheService  reloadCacheService;

	@RequestMapping(value="/reloadCache")
	@Frequency(name=AuthValidation.APIConstant.API_RELOAD_CACHE,limit=1,time=10)
	public @ResponseBody ReloadCacheResponse reloadCache(HttpServletRequest request,
			   @ModelAttribute("rcrqVo")@Valid ReloadCacheRequest rcrqVo,
			 BindingResult result) 
			throws IOException, HDSServiceException{
		
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
		rcrqVo.setRequest_id(request_id);
		rcrqVo.setRequest_ip(request_ip);

		//日志记录请求(请求id|请求时间|请求ip|请求接口|请求参数对象|请求拦截结果)
		logger.info(request_id+R.SystemConstant.LOG_OBJECT_DELIMITER+ 
				     DateUtils.toString(R.DateConstant.DATETIME_FULL)+
				    R.SystemConstant.LOG_OBJECT_DELIMITER+
				    request_ip+R.SystemConstant.LOG_OBJECT_DELIMITER+
				    AuthValidation.APIConstant.API_CCS_GETCCSDEMO
				    +R.SystemConstant.LOG_OBJECT_DELIMITER+rcrqVo.toString()
				    +R.SystemConstant.LOG_OBJECT_DELIMITER+rr_req_frequency.toString());
		
		
		ReloadCacheResponse  rcrpVo = reloadCacheService.reloadCache(rcrqVo, result, rr_req_frequency);
	     
		return rcrpVo;
		

	}
	
	
	
}
