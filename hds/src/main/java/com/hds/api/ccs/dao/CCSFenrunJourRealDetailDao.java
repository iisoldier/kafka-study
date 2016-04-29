/** 
* @ClassName:com.hds.api.ccs.dao
* @Description:
* @author jinmeng
* @date 创建时间：2016年4月28日 下午3:55:07
*/
package com.hds.api.ccs.dao;

import com.hds.api.ccs.vo.FenrunJourRealDetailRequest;
import com.hds.api.ccs.vo.FenrunJourRealDetailResponse;
import com.hds.api.sys.vo.OTSConfig;

/**
* @ClassName:com.hds.api.ccs.dao.CCSFenrunJourRealDetailDao
* @Description:
* @author jinmeng
* @date 创建时间：2016年4月28日 下午3:00:12
 */
public interface CCSFenrunJourRealDetailDao {

	
	public  FenrunJourRealDetailResponse  getFenrunJourRealDetail(FenrunJourRealDetailRequest fjrdVo ,OTSConfig ots_config);

	
	
	
}
