/** 
* @ClassName:com.hds.api.common.validate
* @Description:
* @author zengli
* @date 创建时间：2016年4月18日 上午10:52:28
*/
package com.hds.api.common.validate;

import org.apache.log4j.Logger;
import com.hds.api.sys.vo.ResponseResult;
import com.hds.api.common.constant.R;
import com.hds.api.common.constant.RequestValidation;
import com.hds.api.common.util.DateUtils;

/** 
 * @ClassName:com.hds.api.common.validate.CommonLogicValidate
 * @Description: 统一逻辑校验
 * @author zengli
 * @date 创建时间：2016年4月18日 上午10:52:28
 */
public class LogicValidator {

	private static Logger logger = Logger.getLogger(LogicValidator.class);
	
	/**
	* @method  logicDateValidate
	* @Description: 校验输入的起始日期和结束日期 ，校验起始结束日期差和大小
	* @author  zengli
	* @date 2016年4月18日 下午2:21:45
	* @parameter  startdate起始日期  enddate结束日期
	* @return  ResponseResult
	 */
	public static ResponseResult DateValidate(String startdate,String enddate,int day_lag, String request_id){
	   //求时间差
	   int days_between=DateUtils.getDaysBetween(startdate,enddate);
	   //时间差大于日期限制(默认一期为3天)
	   if(days_between>day_lag){
		  
		   //设置返回信息
		   ResponseResult rr = new ResponseResult();
		   rr.setType(R.SystemConstant.RESTYPE_LOGIC);
		   rr.setResult_code(RequestValidation.ResultCode.LOGIC_DAYS_LAG);
		   rr.setField(RequestValidation.Field.LOGIC_SDAY_EDAY);
		   rr.setMessage(RequestValidation.ValidateMessage.LOGIC_DAYS_LAG_ERRMSG+
				   ",输入参数为|"+startdate+"|"+enddate+"|"+days_between);
		   
		   //debug日志(可以删除)
		   logger.error("request_id:"+request_id+",输入的起始/结束日期差过大,反馈信息:"+rr.toString());
		   
		   return rr;
	   }
	   //开始日期大于结束日期
	   if(days_between<0){
		   //设置返回信息
		  ResponseResult rr = new ResponseResult();
		  rr.setType(R.SystemConstant.RESTYPE_LOGIC);
		  rr.setResult_code(RequestValidation.ResultCode.LOGIC_DAYS_COMPARE);
		   rr.setField(RequestValidation.Field.LOGIC_SDAY_EDAY);
		   rr.setMessage(RequestValidation.ValidateMessage.LOGIC_DAYS_COMPARE_ERRMSG+
				  ",输入参数为|"+startdate+"|"+enddate+"|"+days_between);
		   
		   //debug日志(可以删除)
		   logger.error("request_id:"+request_id+",输入的起始日期大于结束日期,反馈信息:"+rr.toString());
		   return rr;
	   }
      
	    //日期参数业务逻辑校验成功
	    //设置返回信息
		ResponseResult rr = new ResponseResult();
		rr.setType(R.SystemConstant.RESTYPE_LOGIC);
		rr.setResult_code(RequestValidation.ResultCode.LOGIC_IS_VALID);
		rr.setField(RequestValidation.Field.LOGIC_SDAY_EDAY);
		rr.setMessage(RequestValidation.ValidateMessage.LOGIC_VALID_SUCESSMSG);
	    //debug日志(可以删除)
	   logger.info("request_id:"+request_id+",日期参数业务逻辑校验成功:"+rr.toString());
       return rr;

	}
		
}
