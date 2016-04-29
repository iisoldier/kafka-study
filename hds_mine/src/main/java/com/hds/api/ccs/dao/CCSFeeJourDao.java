/** 
* @ClassName:com.hds.api.ccs.dao
* @Description:
* @author zengli
* @date 创建时间：2016年4月18日 下午3:55:07
*/
package com.hds.api.ccs.dao;

import com.hds.api.ccs.vo.FeeJourRequest;
import com.hds.api.ccs.vo.FeeJourResponse;
import com.hds.api.sys.vo.OTSConfig;

/**
* @ClassName:com.hds.api.ccs.dao.CCSFeeJourDao
* @Description:
* @author jinmeng
* @date 创建时间：2016年4月26日 下午5:00:12
 */
public interface CCSFeeJourDao {

	
	public  FeeJourResponse  getFeeJour(FeeJourRequest fjVo ,OTSConfig ots_config);

	
	
	
}
