/** 
* @ClassName:com.hds.api.ccs.dao
* @Description:
* @author zengli
* @date 创建时间：2016年4月18日 下午3:55:07
*/
package com.hds.api.ccs.dao;

import com.hds.api.ccs.vo.FenrunJourRequest;
import com.hds.api.ccs.vo.FenrunJourResponse;
import com.hds.api.sys.vo.OTSConfig;

/**
* @ClassName:com.hds.api.ccs.dao.CCSFenrunJourDao
* @Description: 分润流水明细查询
* @author zengli
* @date 创建时间：2016年4月26日 下午5:00:12
 */
public interface CCSFenrunJourDao {

	
	public  FenrunJourResponse  getFenrunJour(FenrunJourRequest fjVo ,OTSConfig ots_config);

	
	
	
}
