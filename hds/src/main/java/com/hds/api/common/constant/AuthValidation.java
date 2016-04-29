package com.hds.api.common.constant;

/**
* @ClassName:com.hds.api.common.constant.ReqValidate
* @Description: 权限校验参数校验常量/错误消息类
* @author zengli
* @date 创建时间：2016年4月17日 下午4:07:15
 */
public class AuthValidation {

	/**
	* @ClassName:com.hds.api.common.constant.APIConstant
	* @Description: 接口常量列表
	* @author zengli
	* @date 创建时间：2016年4月17日 下午7:44:02
	 */
	public interface APIConstant{

		//------------------API接口列表 ------------------------------
		public static final String API_RELOAD_CACHE  = "SYS_ReloadCache";
		//CCS-GetCCSDemo
		public static final String API_CCS_GETCCSDEMO  = "CCS_GetCCSDemo";
		//CCS_GetFeeSettleOrder
		public static final String API_CCS_GETFEESETTLEORDER  = "CCS_GetFeeSettleOrder";
		
	}
	
/**
* @ClassName:com.hds.api.common.constant.APIEnum
* @Description:
* @author zengli
* @date 创建时间：2016年4月21日 下午9:56:50
 */
	public enum APIEnum {			

		SYS_ReloadCache("SYS_ReloadCache","缓存重新加载接口"),
		CCS_GetCCSDemo("CCS_GetCCSDemo","CCS测试用例"),
		CCS_GetFeeSettleOrder("CCS_GetFeeSettleOrder","划付指令调用接口");
		
		private String value;
		private String label;
		private APIEnum(String value, String label) {
			this.value = value;
			this.label = label;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
	}
	
	
	
	
	
	
	
	
	/**
	* @ClassName:com.hds.api.common.constant.FieldConstant
	* @Description: 权限校验的常量
	* @author zengli
	* @date 创建时间：2016年4月17日 下午4:08:07
	 */
	public interface Constant{

		// --------------------权限校验域常量---------------------------
		public static final int   API_OPEN_FLAG=1;
		public static final int   API_CLOSE_FLAG=0;
		
		public static final int   SYSACCESS_OPEN_FLAG=1;
		public static final int   SYSACCESS_CLOSE_FLAG=0;

	}
	
	/**
	* @ClassName:com.hds.api.common.constant.Field
	* @Description:
	* @author zengli
	* @date 创建时间：2016年4月21日 下午7:02:23
	 */
	public interface Field{
		//-------------------权限通用数据域--------------------------
		//系统编号+系统状态
		public static final String AUTH_SYS_IDFLAGE  = "sys_idflag";
		//接口编号+接口状态
		public static final String AUTH_API_IDFLAG    = "api_idflag";
		//系统IP白名单
		public static final String AUTH_SYS_IP= "sysid_ipid";
		//系统接口权限
		public static final String AUTH_API_SYS = "sysid_apiid";
		
		public static final String AUTH_IS_VALID ="auth_all";
	}
	
	
	/**
	* @ClassName:com.hds.api.common.constant.ResultCode
	* @Description:结果返回码
	* @author zengli
	* @date 创建时间：2016年4月21日 下午6:57:23
	 */
	public interface ResultCode{
		//-------------------权限通用数据域--------------------------
		//系统编号+系统状态
		public static final String AUTH_SYS_INVALID = "SYS_STATUS_INVALID";
		//接口编号+接口状态
		public static final String AUTH_API_INVALID    = "API_STATUS_INVALID";
		//系统IP白名单
		public static final String AUTH_SYSIP_INVALID= "SYS_IPWHITELIST_INVALID";
		//系统接口权限
		public static final String AUTH_APISYS_INVALID = "SYSAPI_AUTH_INVALID";
		
		public static final String AUTH_IS_VALID ="AUTH_IS_VALID";
		
	}
	
	
	
  /**
  * @ClassName:com.hds.api.common.constant.ValidateMessage
  * @Description: 权限校验的错误消息
  * @author zengli
  * @date 创建时间：2016年4月17日 下午4:07:37
   */
	public interface ValidateMessage {
		// --------------------通用消息--------------------------
		//系统编号不存在或者系统接入服务已停止
		public static final String SYS_NOTEXIST_ERRMSG ="提交的系统编号不存在，或对应的接入系统已停止服务";
		//API接口ID校验失败或接口处于关闭装状态
		public static final String API_NOTEXIST_ERRMSG ="接口处于停用状态";
		//系统无权访问接口
		public static final String SYSIP_NOTACCESS_ERRMSG ="请求IP不在系统IP白名单当中";
		//系统无权访问接口
		public static final String SYSAPI_NOTACCESS_ERRMSG ="本系统无权访问该接口";
		
		// --------------------特殊消息--------------------------
		public static final String  AUTH_VALID_SUCESSMSG = "查询请求的权限校验成功！";
		
		
	}
	


}

