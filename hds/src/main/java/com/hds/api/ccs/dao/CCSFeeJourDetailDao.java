/** 
* @ClassName:com.hds.api.ccs.dao
* @Description:
* @author zengli
* @date 创建时间：2016年4月27日 下午7:57:46
*/
package com.hds.api.ccs.dao;

import com.hds.api.ccs.vo.FeeJourDetailRequest;
import com.hds.api.ccs.vo.FeeJourDetailResponse;
import com.hds.api.sys.vo.OTSConfig;

/** 
 * @ClassName:com.hds.api.ccs.dao.CCSFeeJourDetailDao
 * @Description:费用流水明细数据查询 dao层接口类
 * @author zengli
 * @date 创建时间：2016年4月27日 下午7:57:46
 */
public interface CCSFeeJourDetailDao {
	
	public  FeeJourDetailResponse  getFeeJourDetail(FeeJourDetailRequest fjdVo ,OTSConfig ots_config);
		
}
