package com.hds.api.ccs.service;

import org.springframework.validation.BindingResult;

import com.hds.api.ccs.vo.FeeJourRequest;
import com.hds.api.ccs.vo.FeeJourResponse;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.ccs.service.CCSFeeJourService
* @Description:费用流水查询service接口
* @author jinmeng
* @date 创建时间：2016年4月27日 下午8:08:43
 */
public interface CCSFeeJourService {


	//分润流水查询实现类
	public FeeJourResponse getFeeJour(FeeJourRequest fjVo,
			BindingResult result, ResponseResult rr_req_frequency) throws HDSServiceException;

}
