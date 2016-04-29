package com.hds.api.ccs.service;

import org.springframework.validation.BindingResult;
import com.hds.api.ccs.vo.FeeJourDetailRequest;
import com.hds.api.ccs.vo.FeeJourDetailResponse;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.ResponseResult;

/** 
 * @ClassName:com.hds.api.ccs.service.CCSFeeJourDetailService
 * @Description:费用流水明细数据查询 service接口
 * @author zengli
 * @date 创建时间：2016年4月27日 下午7:28:04
 */
public interface CCSFeeJourDetailService {

	//费用流水明细查询实现类
	public FeeJourDetailResponse getFeeJourDetail(FeeJourDetailRequest fjdVo,
			BindingResult result, ResponseResult rr_req_frequency) throws HDSServiceException;
	
	
}
