/** 
* @ClassName:com.hds.api.common.constant
* @Description:
* @author zengli
* @date 创建时间：2016年4月18日 上午11:27:17
*/
package com.hds.api.common.constant;

/** 
 * @ClassName:com.hds.api.common.constant.LogicValidation
 * @Description: 业务逻辑常量
 * @author zengli
 * @date 创建时间：2016年4月18日 上午11:27:17
 */
public class LogicValidation {

	
	/**
	* @ClassName:com.hds.api.common.constant.FieldConstant
	* @Description: 逻辑常量校验
	* @author zengli
	* @date 创建时间：2016年4月18日 上午11:28:11
	 */
	public interface Constant{
		
		//测试用例接口日期差
		public static final int   CCSDEMO_DAYS_LAG=3;
		
		//划付指令接口日期差
		public static final int  CCSFEESETTLEORDER_DAYS_LAG=30;
		
		
	}
	
	/**
	* @ClassName:com.hds.api.common.constant.Field
	* @Description: 校验的字段域
	* @author zengli
	* @date 创建时间：2016年4月20日 下午4:50:46
	 */
	public interface Field{
		
		//逻辑校验成功
		public static final String LOGIC_IS_VALID  = "all_logic_fields";
		
		
		//开始日期，结束日期
	    public static final String LOGIC_SDAY_EDAY = "startday_endday";
	    //费用大类
	    public static final String CCSFEE_CATEGORY1 = "ccs_fee_category1";
	    //费用小类
	    public static final String CCSFEE_CATEGORY2 = "ccs_fee_category2";
	    

	}
	
	/**
	* @ClassName:com.hds.api.common.constant.ResultCode
	* @Description: 消息返回码
	* @author zengli
	* @date 创建时间：2016年4月20日 下午4:52:02
	 */
	public interface ResultCode{
		
		//校验成功
		public static final String LOGIC_IS_VALID  = "LOGIC_IS_VALID";
		
		
		//开始日期，结束日期范围校验
		public static final String LOGIC_DAYS_LAG  = "DAYS_LAG_INVALID";
		//开始时间大于结束时间
		public static final String LOGIC_DAYS_COMPARE ="DAYS_COMPARE_INVALID";
		//费用大类校验
		public static final String LOGIC_CCSFEECI_NOTEXIST   = "CCS_FEECI_NOTEXIST";
		//费用小类校验
		public static final String LOGIC_CCSFEEC2_NOTEXIST   = "CCS_FEEC2_NOTEXIST";
		
		//参数校验位为空
		public static final String LOGIC_2PARAM_BOTHNULL   = "LOGIC_2PARAM_BOTHNULL";
		
	}
	
	
	
	/**
	* @ClassName:com.hds.api.common.constant.ValidateMessage
	* @Description:逻辑权限校验消息
	* @author zengli
	* @date 创建时间：2016年4月18日 上午11:28:31
	 */
	public interface ValidateMessage {
		//起始结束时间时间差大于限制
		public static final String DAYS_LAG_ERRMSG ="起始日期/结束日期时间差过大";
		//起始时间大于结束时间
		public static final String DAYS_COMPARE_ERRMSG ="起始日期大于结束日期";
		//提交的费用大类不存在
		public static final String CCSFEEC1_NOTEXIST_ERRMSG ="提交的计费平台费用大类不存在";
		//提交的费用小类不存在
		public static final String CCSFEEC2_NOTACCESS_ERRMSG ="提交的计费平台费用小类不存在";
		//提交的业务逻辑校验成功
		public static final String LOGIC_VALID_SUCESSMSG ="业务逻辑校验成功";
		
	}
	
	
}
