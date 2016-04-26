package com.hds.api.common.constant;

import com.aliyun.openservices.ots.ClientErrorCode;
import com.aliyun.openservices.ots.OTSErrorCode;

/**
* @ClassName:com.hds.api.common.constant.R
* @Description:  系统常量/异常参数
* @author zengli
* @date 创建时间：2016年4月17日 下午4:04:03
 */
public final class R {

   /**
   * @ClassName:com.hds.api.common.constant.SystemConstant
   * @Description: 系统常量/缓存常量
   * @author zengli
   * @date 创建时间：2016年4月17日 下午4:04:28
    */
	public interface SystemConstant {
		/** ----- 系统常量 --------------- **/
		// UTF-8
		public static final String UTF8 = "UTF-8";
		// MD5
		public static final String MD5 = "MD5";
		//日志域分隔符
		public static final String LOG_OBJECT_DELIMITER ="#";
		//对象字段域分隔符
		public static final String LOG_COL_DELIMITER ="|";
		
		
		
		/** ----- 缓存常量 --------------- **/
		/** ----- 主/备 cache map --------------- **/
		//缓存当前数据map名称
		public static final String CACHE_CURRENTMAP = "current_cachemap";
		//缓存数据 main map名称
		public static final String CACHE_MAINMAP  = "main_cacheMap";
		//缓存数据 bk map名称
		public static final String CACHE_BKMAP = "bk_cacheMap";
		

		/** ----- 权限常量，一级key --------------- **/
		//缓存数据 bk map名称
		public static final String  CACHE_AUTH= "bk_cacheMap";
		//hdsOTS必选列名
		public static final String CACHE_OTS_MCOLS = "CACHE_OTS_MCOLS";
		//hdsOTS可选列名
		public static final String CACHE_OTS_OCOLS = "CACHE_OTS_OCOLS";
		
		
		/** ----- 权限常量，二级key --------------- **/
		//hds接入系统list名称常量
		public static final String CACHE_SYSLIST = "CACHE_SYSLIST";
		//hds接口list名称常量
		public static final String CACHE_APILIST = "CACHE_APILIST";
		//hds接入系统ip白名单名称常量(二级key为api_id)
		public static final String CACHE_IPWHITELIST = "CACHE_IPWHITELIST";
		//hds系统接口权限常量(二级key为api_id)
		public static final String CACHE_APISYSLIST = "CACHE_APISYSLIST";

		

		/**--   反馈消息对象 中的反馈信息类型    --**/
		//权限校验反馈
		public static final String RESTYPE_AUTH="VALID_AUTH";
		//请求参数校验反馈
		public static final String RESTYPE_PARAMTER="VALID_PARAMTER";
		//业务逻辑校验反馈
		public static final String RESTYPE_LOGIC="VALID_LOGIC";
		//request 校验反馈
		public static final String RESTYPE_FREQUENCY="RESTYPE_FREQUENCY";
		//ots校验反馈
		public static final String REQUEST_HANDLE="REQUEST_HANDLE";
		//全部校验成功(Type)
		public static final String RESTYPE_ALL_VALID="VALID_ALL";


	}
	
	/**
	* @ClassName:com.hds.api.common.constant.RequestSucess
	* @Description:
	* @author zengli
	* @date 创建时间：2016年4月23日 下午3:41:49
	 */
	public interface RequestSucess{
		/** 全部校验成功  **/

		//全部校验成功返回码(code)
		public static final String ALL_IS_VALID="ALL_IS_VALID";
		//全部校验成功返回信息(message)
		public static final String ALL_VALID_SUCCESSMSG="请求校验成功";
		//请求成功域(field)
		public static final String FIELD_ALL_VALID="param_auth_frquency_logic";
		
		/** 请求执行成功  **/

		//请求成功返回码(code)
		public static final String REQUEST_HANDLE_SUCCESS="REQUEST_HANDLE_SUCCESS";
		//请求成功返回信息(message)
		public static final String REQUEST_HANDLE_SUCCESSMSG="请求执行成功";
		//请求成功域(field)
		public static final String REQUEST_HANDLE_FIELD="";
		
		
		
		/** 重载请求执行失败  **/
		//请求成功返回码(code)
		public static final String REQUEST_HANDLE_ERROR="REQUEST_HANDLE_ERROR";
		//请求成功返回信息(message)
		public static final String REQUEST_CAHCEMAP_ERRORMSG="当前无缓存map/或者map名称不正确";
		//请求成功域(field)
		public static final String REQUEST_CAHCEMAP_FIELD="current_cachemap";
		
	}

	

	
	/**
	* @ClassName:com.hds.api.common.constant.DateConstant
	* @Description:日期常量
	* @author zengli
	* @date 创建时间：2016年4月17日 下午4:05:28
	 */
	public interface DateConstant{
			//日期格式 yyyy-MM-dd
			public static final String DATE_FULL = "yyyy-MM-dd";
			// 时间格式 yyyy-MM-dd HH:mm:ss 
			public static final String DATETIME_FULL = "yyyy-MM-dd HH:mm:ss";
			//日期格式（无连接符）yyyyMMdd
			public static final String DATE_SHORT = "yyyyMMdd";
			//时间格式（无连接符） yyyyMMddHHmmss
			public static final String DATETIME_SHORT = "yyyyMMddHHmmss";
			//时间格式（无连接符） yyyyMMddHH
			public static final String DATETIME_HOUR = "yyyyMMddHH";
			//时间格式（无连接符）HHmmss
			public static final String TIME_SHORT = "HHmmss";
			//时间格式 HH:mm:ss 
			public static final String TIME_FULL = "HH:mm:ss";
			//时间格式 yyyy-MM-dd HH:mm:ss.SSS 
			public static final String DATETIME_FULL_S = "yyyy-MM-dd HH:mm:ss.SSS";
			public static final int ONE_DAY = 1;
		}


	/**
	* @ClassName:com.hds.api.common.constant.ServiceExceptionEnum
	* @Description: OTS ServiceException ErrorCode枚举
	* @author zengli
	* @date 创建时间：2016年4月17日 下午4:05:42
	 */
    public enum ServiceExceptionEnum {
			//用户身份验证失败
			AUTHORIZATION_FAILURE(OTSErrorCode.AUTHORIZATION_FAILURE, "用户身份验证失败"),
			//预查条件检查失败
			CONDITION_CHECK_FAIL(OTSErrorCode.CONDITION_CHECK_FAIL, "预查条件检查失败"),
			//服务器内部错误
			INTERNAL_SERVER_ERROR(OTSErrorCode.INTERNAL_SERVER_ERROR, "服务器内部错误"),
			//参数错误
			INVALID_PARAMETER(OTSErrorCode.INVALID_PARAMETER, "参数错误"),
			//主键不匹配
			INVALID_PK(OTSErrorCode.INVALID_PK, "主键不匹配"),
			//剩余预留读写能力不足
			NOT_ENOUGH_CAPACITY_UNIT(OTSErrorCode.NOT_ENOUGH_CAPACITY_UNIT, "剩余预留读写能力不足"),
			//请求创建的表已经存在
			OBJECT_ALREADY_EXIST(OTSErrorCode.OBJECT_ALREADY_EXIST, "请求创建的表已经存在"),
			//请求的表不存在
			OBJECT_NOT_EXIST(OTSErrorCode.OBJECT_NOT_EXIST, "请求的表不存在"),
			//该行总列数超出限制
			OUT_OF_COLUMN_COUNT_LIMIT(OTSErrorCode.OUT_OF_COLUMN_COUNT_LIMIT, "该行总列数超出限制"),
			//该行所有列数据大小总和超出限制
			OUT_OF_ROW_SIZE_LIMIT(OTSErrorCode.OUT_OF_ROW_SIZE_LIMIT, "该行所有列数据大小总和超出限制"),
			//内部服务器发生failover，导致表的部分分区不可服务。
			PARTITION_UNAVAILABLE(OTSErrorCode.PARTITION_UNAVAILABLE, "内部服务器发生failover，导致表的部分分区不可服务"),
			//用户的配额已经用满
			QUOTA_EXHAUSTED(OTSErrorCode.QUOTA_EXHAUSTED, "用户的配额已经用满"),
			//客户端请求超时
			REQUEST_TIMEOUT(OTSErrorCode.REQUEST_TIMEOUT, "客户端请求超时"),
			//整个请求过大
			REQUEST_TOO_LARGE(OTSErrorCode.REQUEST_TOO_LARGE, "整个请求过大"),
			//多个并发的请求写同一行数据，导致冲突
			ROW_OPERATION_CONFLICT(OTSErrorCode.ROW_OPERATION_CONFLICT, "多个并发的请求写同一行数据，导致冲突"),
			//OTS内部服务器繁忙
			SERVER_BUSY(OTSErrorCode.SERVER_BUSY, "OTS内部服务器繁忙"),
			//在OTS内部有服务器不可访问
			SERVER_UNAVAILABLE(OTSErrorCode.SERVER_UNAVAILABLE, "在OTS内部有服务器不可访问"),
			//在OTS内部操作超时
			STORAGE_TIMEOUT(OTSErrorCode.STORAGE_TIMEOUT, "在OTS内部操作超时"),
			//表刚被创建还无法立马提供服务
			TABLE_NOT_READY(OTSErrorCode.TABLE_NOT_READY, "表刚被创建还无法立马提供服务"),
			//读写能力调整过于频繁
			TOO_FREQUENT_RESERVED_THROUGHPUT_ADJUSTMENT(
					OTSErrorCode.TOO_FREQUENT_RESERVED_THROUGHPUT_ADJUSTMENT, "读写能力调整过于频繁"),
		    //其他
			OTHER("OTHER", "其他错误");

			private String value;
			private String label;

			private ServiceExceptionEnum(String value, String label) {
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
    * @ClassName:com.hds.api.common.constant.ClientExceptionEnum
    * @Description:OTS ClientExceptionEnum ErrorCode枚举
    * @author zengli
    * @date 创建时间：2016年4月17日 下午4:06:05
     */
	public enum ClientExceptionEnum {
			//远程服务连接超时
			CONNECTION_TIMEOUT(ClientErrorCode.CONNECTION_TIMEOUT, "远程服务连接超时"),
			//返回结果无法解析
			INVALID_RESPONSE(ClientErrorCode.INVALID_RESPONSE, "返回结果无法解析"),
			//远程服务socket读写超时
			SOCKET_TIMEOUT(ClientErrorCode.SOCKET_TIMEOUT, "远程服务socket读写超时"),
			//未知错误
			UNKNOWN(ClientErrorCode.UNKNOWN, "未知错误"),
		    //其他
			OTHER("OTHER", "其他错误");

			private String value;
			private String label;

			private ClientExceptionEnum(String value, String label) {
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
	* @ClassName:com.hds.api.common.constant.HDSErrorEnum
	* @Description:  HDSExceptionEnum ErrorCode枚举
	* @author zengli
	* @date 创建时间：2016年4月17日 下午4:06:21
	 */
	public enum HDSErrorEnum {			
		//------------------------        基础方法类     begin    -------------------------------------------------------
		//日期格式不正确
		ERROR_DATE_FORMAT("ERROR_DATE_FORMAT","日期格式不正确"),
		//------------------------        基础方法类     end   -----------------------------------------------------------


		//------------------------        查询校验类     begin ----------------------------------------------------------
		//请求参数校验结果返回的list为空
		ERROR_REQPRM_RESRESULT("ERROR_REQPRM_RESRESULT","请求参数格式校验结果返回的list为空"),
		ERROR_REQAUTH_RESRESULT("ERROR_REQAUTH_RESRESULT","请求权限校验结果返回的list为空"),
		//------------------------        查询校验类     begin ----------------------------------------------------------
		
		
		
	    //------------------------        OTS类    begin     -----------------------------------------------------------
		ERROR_OTSCFG_NULL("ERROR_OTSCFG_NULL","OTS参数存储加载为空"),
		//------------------------        OTS类    end     --------------------------------------------------------------
	  
		//其他
		OTHER("OTHER", "其他错误");
		
		private String value;
		private String label;
		private HDSErrorEnum(String value, String label) {
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

}
