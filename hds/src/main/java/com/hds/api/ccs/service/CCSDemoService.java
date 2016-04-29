package com.hds.api.ccs.service;

import org.springframework.validation.BindingResult;

import com.hds.api.ccs.vo.CCSDemoRequestVo;
import com.hds.api.ccs.vo.CCSDemoResponseVo;
import com.hds.api.sys.vo.ResponseResult;

/**
 * @ClassName:com.hds.api.ccs.service.CCSService
 * @Description: 计费查询 service 接口
 * @author zengli
 * @date 创建时间：2016年4月17日 下午3:59:56
 */
public interface CCSDemoService {

	//查询校验demo
	public CCSDemoResponseVo getCCSDemo(CCSDemoRequestVo ccsVo,
			BindingResult result, ResponseResult rr_req_frequency);


}
