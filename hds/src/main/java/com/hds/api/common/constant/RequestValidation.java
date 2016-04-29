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
	* @Description: 请求参数校验的常量
	* @author zengli
	* @date 创建时间：2016年4月17日 下午4:08:07
	 */
	public interface Constant{
		
		// --------------------参数格式校验   开始--------------------------
		//分页范围校验参数
		public static final int LIMIT_MIN = 5;
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
		//交易状态字段长度范围(CCS)
		public static final int CCS_DEALSTATE_MIN = 1;
		public static final int CCS_DEALSTATE_MAX= 1;
		// --------------------参数格式校验   结束---------------------------
		
		// --------------------请求次数校验   开始----------------------------
		
		//用于@frequency 注释中的name ，与ip+name作为接口访问的频率次数的控制字段
	    public static final String DEFAULT_API_NAME ="all";
	    //用于@frequency 注释中的limit,time校验
        public static final int  DEFAULT_FREQUENCY_LIMIT =0;
        public static final int  DEFAULT_FREQUENCY_TIME =0;
		// --------------------请求次数校验   结束----------------------------

	    // --------------------  权限校验域常量   开始---------------------------
	   public static final int   API_OPEN_FLAG=1;
	   public static final int   API_CLOSE_FLAG=0;	
	   public static final int   SYSACCESS_OPEN_FLAG=1;
	   public static final int   SYSACCESS_CLOSE_FLAG=0;
	 	 // --------------------  权限校验域常量   结束---------------------------
  
	   // --------------------     参数逻辑校验域常量   开始---------------------------
		//测试用例接口日期差
		public static final int   DAYS_LAG_CCSDEMO=3;
		//划付指令接口日期差
		public static final int  DAYS_LAG_CCSFEESETTLEORDER=30;
		//分润流水接口日期差
		public static final int  DAYS_LAG_CCSFENRUNJOUR=30;
		
		//分润流水接口日期差
		public static final int  DAYS_LAG_CCSFEEJOURDETAIL=30;
		
		//费用流水接口日期差
		public static final int  DAYS_LAG_CCSFEEJOUR=30;
		//分润明细接口日期差
		public static final int  DAYS_LAG_CCSFENRUNJOURREALDETAIL=30;
		// --------------------  参数逻辑校验域常量   开始---------------------------
	}
	
	/**
	* @ClassName:com.hds.api.common.constant.Field
	* @Description: 请求参数校验域
	* @author zengli
	* @date 创建时间：2016年4月22日 下午4:27:50
	 */
	public interface  Field{
		
		//-------------------请求频率校验  数据域  开始--------------------------
		// @frequency参数校验域
		public static final String FREQUENCY_PARAM_ZERO ="frequency_annontion_param";
		public static final String FREQUENCY_TIMES_OVERLIMITS= "request_frequency_limits";
		
		//校验成功
		public static final String FREQUENCY_IS_VALID= "request_frequency_all";
		//-------------------请求频率校验  数据域  结束--------------------------
		
		//-------------------权限校验   数据域  开始--------------------------
		//系统编号+系统状态
		public static final String AUTH_SYS_IDFLAGE  = "sys_idflag";
		//接口编号+接口状态
		public static final String AUTH_API_IDFLAG    = "api_idflag";
		//系统IP白名单
		public static final String AUTH_SYS_IP= "sysid_ipid";
		//系统接口权限
		public static final String AUTH_API_SYS = "sysid_apiid";
		
		//校验成功
		public static final String AUTH_IS_VALID ="auth_all";
		//-------------------权限校验   数据域  结束--------------------------
		
		//-------------------逻辑校验   数据域  开始--------------------------
		//开始日期，结束日期
	    public static final String LOGIC_SDAY_EDAY = "startday_endday";
	    //费用大类
	    public static final String CCSFEE_CATEGORY1 = "ccs_fee_category1";
	    //费用小类
	    public static final String CCSFEE_CATEGORY2 = "ccs_fee_category2";
	    
		//逻辑校验成功
		public static final String LOGIC_IS_VALID  = "all_logic_fields";
		//-------------------逻辑校验   数据域  结束--------------------------
		
		
		//请求成功域(field)
		public static final String FIELD_ALL_VALID="param_auth_frquency_logic";
		
	}
	
	/**
	* @ClassName:com.hds.api.common.constant.ResultCode
	* @Description: 请求校验返回码
	* @author zengli
	* @date 创建时间：2016年4月22日 下午4:26:07
	 */
	public interface ResultCode{
		
		//参数校验成功返回值
		public static final String PARAM_IS_VALID = "PARAM_IS_VALID";
		
		//-------------------请求频率校验  返回码  开始--------------------------
		// @frequency校验返回
		public static final String FREQUENCY_PARAM_ZERO ="FREQUENCY_PRARM_ZERO";
		public static final String FREQUENCY_TIMES_OVERLIMITS= "FREQUENCY_TIMES_OVERLIMITS";
		
		public static final String FREQUENCY_IS_VALID= "FREQUENCY_IS_VALID";
		//-------------------请求频率校验  返回码 结束-------------------------
		

		//-------------------权限校验  返回码开始-------------------------
		//系统编号+系统状态
		public static final String AUTH_SYS_INVALID = "SYS_STATUS_INVALID";
		//接口编号+接口状态
		public static final String AUTH_API_INVALID    = "API_STATUS_INVALID";
		//系统IP白名单
		public static final String AUTH_SYSIP_INVALID= "SYS_IPWHITELIST_INVALID";
		//系统接口权限
		public static final String AUTH_APISYS_INVALID = "SYSAPI_AUTH_INVALID";
		
		//校验成功
		public static final String AUTH_IS_VALID ="AUTH_IS_VALID";
		//-------------------权限校验  返回码 结束-------------------------
		
		//-------------------逻辑校验  返回码 开始------------------------
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

		//校验成功
		public static final String LOGIC_IS_VALID  = "LOGIC_IS_VALID";
		//-------------------逻辑校验  返回码 结束-------------------------
		
		
		//全部校验成功返回码(code)
		public static final String ALL_IS_VALID="ALL_IS_VALID";
	}
	
  /**
  * @ClassName:com.hds.api.common.constant.ValidateMessage
  * @Description: 通用校验信息
  * @author zengli
  * @date 创建时间：2016年4月17日 下午4:07:37
   */
	public interface ValidateMessage {
		
		//-------------------------------参数格式 校验返回信息 开始  --------------------------------
		//错误信息
		//空参数消息
		public static final String  PRARM_NOTNULL_ERRMSG ="参数值不能为空";
		//参数没有被提交
		public static final String PRARM_NOTEMPTY_ERRMSG ="参数未被提交";
		//参数超过最大值
		public static final String PRARM_MAX_ERRMSG ="参数值超过最大值限定";
		//参数小于最小值
		public static final String PRARM_MIN_ERRMSG ="参数值小于最小值限定";
		//参数不为数字
		public static final String PRARM_ISNUMBER_ERRMSG ="参数值必须为数字";
		//系统编号参数超过字符串长度范围
		public static final String PRARM_SYSID_LENGTH_ERRMSG ="系统编号参数值不在字符串长度限定范围之内("
		          +Constant.SYSID_MIN+"-"+Constant.SYSID_MAX+")";
		//分页数超过最大值
		public static final String PRARM_LIMIT_MAX_ERRMSG ="分页参数值超过最大值限定值"+Constant.LIMIT_MAX;
		//分页参数小于最小值
		public static final String PRARM_LIMIT_MIN_ERRMSG ="分页参数值小于最小值限定值"+Constant.LIMIT_MIN;
	    //查询方向参数值必须为
		public static final String PRARM_DIRECTION_ERRMSG ="查询方向参数错误，应该为"+Constant.DIRECTION_BACKWARD+
				"或者"+Constant.DIRECTION_FORWARD;
		//查询日期字符串检验失败
		public static final String PRARM_ISDATESTR_ERRMSG ="日期参数值不符合规定的日期格式("+R.DateConstant.DATE_SHORT+")";
		//ccs错误返回信息
		//交易状态参数超过字符串长度范围
		public static final String PARAM_DEALSTATE_LENGTH_ERRMSG ="交易状态参数值不在字符串长度限定范围之内("
		         +Constant.CCS_DEALSTATE_MIN+"-"+Constant.CCS_DEALSTATE_MAX+")";
		
		//成功信息
		//查询日期字符串检查成功
		public static final String PARAM_VALID_SUCCESSMSG ="传入参数的格式校验成功";
		//-------------------------------参数格式 校验错误返回信息 结束  --------------------------------
		

		//-------------------------------请求次数 校验返回信息 开始  --------------------------------
		public static final String FREQUENCY_OVERLIMITS_ERRMSG  =
				"ip请求次数超过限制,请后重试！";
		public static final String FREQUENCY_PARAMZERO_ERRMSG =
				"@Frequency注解 time或limit参数设置异常，数值为0";
		public static final String FREQUENCY_VALID_SUCCESSMSG =
				"@Frequency请求频率校验正常";
		//-------------------------------请求次数 校验返回信息 结束 --------------------------------
		
		//-------------------------------权限  校验返回信息 开始 --------------------------------
		//系统编号不存在或者系统接入服务已停止
		public static final String AUTH_SYS_NOTEXIST_ERRMSG ="提交的系统编号不存在，或对应的接入系统已停止服务";
		//API接口ID校验失败或接口处于关闭装状态
		public static final String AUTH_API_NOTEXIST_ERRMSG ="接口处于停用状态";
		//系统无权访问接口
		public static final String AUTH_SYSIP_NOTACCESS_ERRMSG ="请求IP不在系统IP白名单当中";
		//系统无权访问接口
		public static final String AUTH_SYSAPI_NOTACCESS_ERRMSG ="本系统无权访问该接口";
		//成功消息
		public static final String  AUTH_VALID_SUCESSMSG = "查询请求的权限校验成功！";
		//-------------------------------权限  校验返回信息 结束 --------------------------------
		
		
		//-------------------------------逻辑  校验返回信息  开始 --------------------------------
		//起始结束时间时间差大于限制
		public static final String LOGIC_DAYS_LAG_ERRMSG ="起始日期/结束日期时间差过大";
		//起始时间大于结束时间
		public static final String LOGIC_DAYS_COMPARE_ERRMSG ="起始日期大于结束日期";
		//提交的费用大类不存在
		public static final String LOGIC_CCSFEEC1_NOTEXIST_ERRMSG ="提交的计费平台费用大类不存在";
		//提交的费用小类不存在
		public static final String LOGIC_CCSFEEC2_NOTACCESS_ERRMSG ="提交的计费平台费用小类不存在";
		//提交的业务逻辑校验成功
		public static final String LOGIC_VALID_SUCESSMSG ="业务逻辑校验成功";
		//-------------------------------逻辑  校验返回信息 结束 --------------------------------
		
		
		//全部校验成功返回信息(message)
		public static final String ALL_VALID_SUCCESSMSG="请求校验成功";
		
	}
		
}

