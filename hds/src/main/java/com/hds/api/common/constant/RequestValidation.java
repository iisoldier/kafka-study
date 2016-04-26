package com.hds.api.common.constant;

/**
* @ClassName:com.hds.api.common.constant.ReqValidate
* @Description: 请求参数校验常量/错误消息类
* @author zengli
* @date 创建时间：2016年4月17日 下午4:07:15
 */
public class RequestValidation {

	/**
	* @ClassName:com.hds.api.common.constant.FieldConstant
	* @Description: 通用请求参数校验的常量
	* @author zengli
	* @date 创建时间：2016年4月17日 下午4:08:07
	 */
	public interface Constant{
		
		// --------------------通用数据域常量---------------------------
		//分页范围校验参数
		public static final int LIMIT_MIN = 10;
		public static final int LIMIT_MAX = 100;
		//系统编号字段长度范围
		public static final int SYSID_MIN = 32;
		public static final int SYSID_MAX= 32;
		//查询方向变量   -----有变化了请注意修改
		public static final String DIRECTION_REGEXP="^(FORWARD|BACKWARD)$";
		public static final String DIRECTION_FORWARD = "FORWARD";
		public static final String DIRECTION_BACKWARD = "BACKWARD";
		//输入日期字符串的长度
		public static final int DATESTR_LENGTH =8;
		
		//用于@frequency 注释中的name ，与ip+name作为接口访问的频率次数的控制字段
		public static final String DEFAULT_API_NAME ="ALL";
	
		
		// --------------------特殊数据域常量---------------------------
        public static final int  DEFAULT_FREQUENCY_LIMIT =0;
        public static final int  DEFAULT_FREQUENCY_TIME =0;
	}
	
	/**
	* @ClassName:com.hds.api.common.constant.Field
	* @Description: 通用校验域
	* @author zengli
	* @date 创建时间：2016年4月22日 下午4:27:50
	 */
	public interface  Field{
		//-------------------通用数据域(自动校验)--------------------------
/*		//分页参数
		public static final String FIELD_LIMIT  = "limit";
		//系统编号
		public static final String FIELD_SYSID  = "sys_id";
		//查询方向
		public static final String FIELD_DIRECTION = "direction";
		//开始日期
		public static final String FIELD_STARTDATE = "startday";
		//结束日期
		public static final String FIELD_ENDDATE = "endday";*/
		
		// --------------------特殊数据域---------------------------
		public static final String FREQUENCY_IS_VALID= "request_frequency_all";
		public static final String FREQUENCY_PARAM_ZERO ="frequency_annontion_param";
		public static final String FREQUENCY_TIMES_OVERLIMITS= "request_frequency_limits";

	}
	
	/**
	* @ClassName:com.hds.api.common.constant.ResultCode
	* @Description:通用信息返回码
	* @author zengli
	* @date 创建时间：2016年4月22日 下午4:26:07
	 */
	public interface ResultCode{
		
		// @frequency校验返回码
		public static final String FREQUENCY_IS_VALID= "FREQUENCY_IS_VALID";
		public static final String FREQUENCY_PARAM_ZERO ="FREQUENCY_PRARM_ZERO";
		public static final String FREQUENCY_TIMES_OVERLIMITS= "FREQUENCY_TIMES_OVERLIMITS";
		
		//参数校验成功返回值
		public static final String REQUEST_PARAM_VALID = "REQUEST_PARAM_VALID";
	}
	
  /**
  * @ClassName:com.hds.api.common.constant.ValidateMessage
  * @Description: 通用校验信息
  * @author zengli
  * @date 创建时间：2016年4月17日 下午4:07:37
   */
	public interface ValidateMessage {
		
		//查询日期字符串检查成功
		public static final String REQUEST_PARAM_SUCCESSMSG ="传入参数的格式校验成功！";
				
		// --------------------通用消息--------------------------
		//空参数消息
		public static final String  NOTNULL_ERRMSG ="参数值不能为空";
		//参数没有被提交
		public static final String NOTEMPTY_ERRMSG ="参数未被提交";
		//参数超过最大值
		public static final String MAX_ERRMSG ="参数值超过最大值限定";
		//参数小于最小值
		public static final String MIN_ERRMSG ="参数值小于最小值限定";
		//参数不为数字
		public static final String ISNUMBER_ERRMSG ="参数值必须为数字";

		// --------------------特殊消息--------------------------
		//系统编号参数超过字符串长度范围
		public static final String SYSID_LENGTH_ERRMSG =
				"系统编号参数值不在字符串长度限定范围之内("+Constant.SYSID_MIN+
				"-"+Constant.SYSID_MAX+")";
		
		//分页数超过最大值
		public static final String LIMIT_MAX_ERRMSG =
				"分页参数值超过最大值限定值"+Constant.LIMIT_MAX;
		//分页参数小于最小值
		public static final String LIMIT_MIN_ERRMSG =
				"分页参数值小于最小值限定值"+Constant.LIMIT_MIN;
	    //分页参数必须为数字
		public static final String LIMIT_ISNUMBER_ERRMSG =
				"分页参数值必须为数字";
		
	    //查询方向参数必须为数字
		public static final String DIRECTION_ERRMSG =
				"查询方向参数错误，应该为"+RequestValidation.Constant.DIRECTION_BACKWARD+"或者"
				+RequestValidation.Constant.DIRECTION_FORWARD;
		
		//查询日期字符串检验失败
		public static final String ISDATESTR_ERRMSG =
				"日期参数值不符合规定的日期格式("+R.DateConstant.DATE_SHORT+")";
		
		
		//---------------------------------------请求次数控制统计  -------------------------------------------------------------------------
		//请求次数
		public static final String FREQUENCY_OVERLIMITS_ERRMSG  =
				"ip请求次数超过限制,请后重试！";
		public static final String FREQUENCY_PARAMZERO_ERRMSG =
				"@Frequency注解 time或limit参数设置异常，数值为0";
		public static final String FREQUENCY_VALID_SUCCESSMSG =
				"@Frequency请求频率校验正常";
		
	}
	
	/**
	* @ClassName:com.hds.api.common.constant.CCSConstant
	* @Description:CCS校验常量
	* @author zengli
	* @date 创建时间：2016年4月22日 下午4:28:36
	 */
	public interface CCSConstant{
	
		//系统编号字段长度范围
		public static final int ODER_SN_MIN = 0;
		public static final int ODER_SN_MAX= 32;

		//交易状态字段长度范围
		public static final int DEAL_STATE_MIN = 1;
		public static final int DEAL_STATE_MAX= 1;
		
		
	}
	
	/**
	* @ClassName:com.hds.api.common.constant.CCSField
	* @Description:CCS校验域
	* @author zengli
	* @date 创建时间：2016年4月22日 下午4:29:08
	 */
	public interface CCSValidateMessage{
		
		//系统编号参数超过字符串长度范围
		public static final String DEALSTATE_LENGTH_ERRMSG =
				"交易状态参数值不在字符串长度限定范围之内("+CCSConstant.DEAL_STATE_MIN+
				"-"+CCSConstant.DEAL_STATE_MAX+")";

	}
	
	
}

