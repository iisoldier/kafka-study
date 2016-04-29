/** 
* @ClassName:com.hds.api.common.constant
* @Description:
* @author zengli
* @date 创建时间：2016年4月24日 下午7:46:17
*/
package com.hds.api.common.constant;

/** 
 * @ClassName:com.hds.api.common.constant.QueryHandle
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月24日 下午7:46:17
 */
public class QueryHandle {

	
	/**
	* @ClassName:com.hds.api.common.constant.OTSConstant
	* @Description: ots常量设置
	* @author zengli
	* @date 创建时间：2016年4月17日 下午4:05:06
	 */
	public interface OTSConstant {			
			//批量查询重试次数
			public static final  int  BATCHGET_RETRY = 3;
			//批量查询重试等待时间(毫秒)
			public static final  int  BATCHGET_RETRY_WAIT = 100;
		}
	
	/**
	* @ClassName:com.hds.api.common.constant.Field
	* @Description:
	* @author zengli
	* @date 创建时间：2016年4月24日 下午8:02:55
	 */
	public  interface Field{
		  public static final String OTS_INDEX_TABLE = "ots_index_table"; 
		  public static final String OTS_DETAIL_TABLE = "ots_detail_table";  
		  public static final String OTS_ALL_TABLE = "ots_index_detail_table";  
	}
	
	/**
	* @ClassName:com.hds.api.common.constant.ResultCode
	* @Description:
	* @author zengli
	* @date 创建时间：2016年4月24日 下午8:03:07
	 */
	public  interface ResultCode{
		 public static final String QUERY_INDEXTB_ISNULL = "QUERY_INDEXTB_ISNULL"; 
		 
		 public static final String QUERY_BATCHGET_IDXFAIL = "QUERY_BATCHGET_IDXFAIL"; 
		 
		 
		 public static final String QUERY_HANDLE_SUCCESS= "QUERY_HANDLE_SUCCESS"; 
	}
	
	/**
	* @ClassName:com.hds.api.common.constant.ResultMessage
	* @Description:
	* @author zengli
	* @date 创建时间：2016年4月24日 下午8:03:19
	 */
	public  interface ResultMessage{
		 public static final String QUERY_INDEXTB_ISNULL_ERRMSG = "索引表查询为空，返回查询结果"; 
		 public static final String QUERY_BATCHGET_IDXFAIL_ERRMSG = "批量查询索引表获得索引，部分索引批量查询超过重试次数，请发送查询请求"; 
		 
		 public static final String QUERY_HANDLE_SUCCESSMSG = "查询请求成功"; 
	}
	
	
}
