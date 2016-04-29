package com.hds.api.ccs.service;

import org.springframework.validation.BindingResult;
import com.hds.api.ccs.vo.FeeSettleOrderRequest;
import com.hds.api.ccs.vo.FeeSettleOrderResponse;
import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.ResponseResult;

/**
 * @ClassName:com.hds.api.ccs.service.CCSService
 * @Description: 计费查询 service 接口
 * @author zengli
 * @date 创建时间：2016年4月17日 下午3:59:56
 */
public interface CCSFeeSettleOrderService {


	//划付指令查询实现类
	public FeeSettleOrderResponse getFeeSettleOrder(FeeSettleOrderRequest fsoVo,
			BindingResult result, ResponseResult rr_req_frequency) throws HDSServiceException;

}
