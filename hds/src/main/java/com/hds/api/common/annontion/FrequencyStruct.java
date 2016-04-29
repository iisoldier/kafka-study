/** 
 * @ClassName:com.hds.api.common.frequency
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月19日 上午9:56:50
 */
package com.hds.api.common.annontion;

import java.util.ArrayList;
import java.util.List;
import com.hds.api.common.util.DateUtils;

/**
 * @ClassName:com.hds.api.common.frequency.FrequencyStruct
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月19日 上午9:56:50
 */
public class FrequencyStruct {

	String uniqueKey;
	long start;
	long end;
	int time;
	int limit;
	List<Long> accessPoints = new ArrayList<Long>();

	public void reset(long timeMillis) {

		start = end = timeMillis;
		accessPoints.clear();
		accessPoints.add(timeMillis);
	}

	@Override
	public String toString() {
		return "FrequencyStruct [uniqueKey=" + uniqueKey + ", start=" + start
				+ ", end=" + end + ", time=" + time + ", limit=" + limit
				+ ", accessPoints=" + accessPoints + "]";
	}
	

	public String toFormatString() {
		String str  =
				"FrequencyStruct [uniqueKey=" + uniqueKey + ", start=" + DateUtils.getDayStrBySec(start)
				+ ", end=" + DateUtils.getDayStrBySec(end) + ", time=" + time + ", limit=" + limit
				+ ", accessPoints=" + accessPoints + "]";
				
//				uniqueKey + R.SystemConstant.LOG_COL_DELIMITER+ 
//				           DateUtils.getDayStrBySec(start)+R.SystemConstant.LOG_COL_DELIMITER+ 
//				           DateUtils.getDayStrBySec(end)+R.SystemConstant.LOG_COL_DELIMITER+ 
//				           time+R.SystemConstant.LOG_COL_DELIMITER+ 
//				           limit+R.SystemConstant.LOG_COL_DELIMITER+ 
//				           accessPoints;
		return   str;
	}
}
