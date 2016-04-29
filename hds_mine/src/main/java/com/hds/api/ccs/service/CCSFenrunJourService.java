package com.hds.api.ccs.service;

import org.springframework.validation.BindingResult;
import com.hds.api.ccs.vo.FenrunJourRequest;
import com.hds.api.ccs.vo.FenrunJourResponse;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.ResponseResult;

/**
* @ClassName:com.hds.api.ccs.service.CCSFenrunJourService
* @Description:分润流水查询service接口
* @author zengli
* @date 创建时间：2016年4月26日 下午3:08:43
 */
public interface CCSFenrunJourService {


	//分润流水查询实现类
	public FenrunJourResponse getFenrunJour(FenrunJourRequest fjVo,
			BindingResult result, ResponseResult rr_req_frequency) throws HDSServiceException;

}
