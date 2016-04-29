/** 
* @ClassName:com.hds.api.ccs.dao
* @Description:
* @author zengli
* @date 创建时间：2016年4月18日 下午3:55:07
*/
package com.hds.api.ccs.dao;

import com.hds.api.ccs.vo.FeeSettleOrderRequest;
import com.hds.api.ccs.vo.FeeSettleOrderResponse;
import com.hds.api.sys.vo.OTSConfig;

/**
* @ClassName:com.hds.api.ccs.dao.CCSFeeSettleOrderDao
* @Description:
* @author zengli
* @date 创建时间：2016年4月26日 下午5:00:22
 */
public interface CCSFeeSettleOrderDao {

	
	public  FeeSettleOrderResponse  getFeeSettleOrder(FeeSettleOrderRequest fsoVo ,OTSConfig ots_config);

	
	
	
}
