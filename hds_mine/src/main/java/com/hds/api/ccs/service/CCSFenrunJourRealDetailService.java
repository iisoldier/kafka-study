package com.hds.api.ccs.service;

import org.springframework.validation.BindingResult;

import com.hds.api.ccs.vo.FenrunJourRealDetailRequest;
import com.hds.api.ccs.vo.FenrunJourRealDetailResponse;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.ccs.service.CCSFenrunJourRealDetailService
* @Description:分润明细查询service接口
* @author jinmeng
* @date 创建时间：2016年4月28日 下午3:48:43
 */
public interface CCSFenrunJourRealDetailService {


	//分润流水查询实现类
	public FenrunJourRealDetailResponse getFenrunJourRealDetail(FenrunJourRealDetailRequest fjrdVo,
			BindingResult result, ResponseResult rr_req_frequency) throws HDSServiceException;

}
