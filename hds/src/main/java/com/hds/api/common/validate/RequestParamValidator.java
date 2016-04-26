/** 
* @ClassName:com.hds.api.common.validate
* @Description:
* @author zengli
* @date 创建时间：2016年4月18日 上午11:22:26
*/
package com.hds.api.common.validate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.hds.api.common.constant.R;
import com.hds.api.common.constant.RequestValidation;
import com.hds.api.sys.vo.ResponseResult;

/** 
 * @ClassName:com.hds.api.common.validate.RequestParamValidate
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月18日 上午11:22:26
 */
public class RequestParamValidator {

	private static Logger logger = Logger.getLogger(RequestParamValidator.class);
	
	/**
	* @method  requestParamValidate
	* @Description:
	* @author  zengli
	* @date 2016年4月18日 下午4:06:37
	* @parameter  BindingResult result  参数校验后的数据校验结果对象，基于注解的参数校验返回结果都在其中
	* @parameter  String request_id       请求id，用于记录请求日志
	* @return  List<ResponseResult>
	 */
	public static List<ResponseResult>  requestParamValidate(BindingResult result, String request_id){
	
	    //获得错误信息列表
		List<FieldError>  lst_fr = result.getFieldErrors();
		//初始化返回信息结果
		List<ResponseResult> Ist_rr  = new ArrayList<ResponseResult>();
		
		//输出校验信息
		logger.info("request_id:"+request_id+",校验的整体错误信息:"+result.getFieldErrors()
				+",校验的总错误个数:" + result.getErrorCount());

		for (int i = 0; i < lst_fr.size(); i++) {
			//debug日志(可以删除)
			logger.error("打印校验错误信息-request_id:"+request_id+"|Object_Name:"+lst_fr.get(i).getObjectName()+
					"|Field:"+lst_fr.get(i).getField()+"|FieldType:"+result.getFieldType(lst_fr.get(i).getField())+
					"|Rejected_Value:"+lst_fr.get(i).getRejectedValue()+
					"|Default_Message:"+lst_fr.get(i).getDefaultMessage()+
					"|ErrorCode:"+lst_fr.get(i).getCode());

			ResponseResult rr = new ResponseResult();
			//添加返回信息结果
			rr.setType(R.SystemConstant.RESTYPE_PARAMTER);
			rr.setResult_code(lst_fr.get(i).getCode());
			rr.setField(lst_fr.get(i).getField());
			rr.setMessage("错误注解类型:"+lst_fr.get(i).getCode()+"错误信息:"+lst_fr.get(i).getDefaultMessage()+
					",校验出错误的参数值为:"+lst_fr.get(i).getRejectedValue());
			
			//添加到列表当中
			Ist_rr.add(rr);
		}

		if(!result.hasFieldErrors()){
			ResponseResult rr = new ResponseResult();
			rr.setType(R.SystemConstant.RESTYPE_PARAMTER);
			rr.setResult_code(RequestValidation.ResultCode.REQUEST_PARAM_VALID);
			rr.setField(RequestValidation.Field.FREQUENCY_IS_VALID);
			rr.setMessage(RequestValidation.ValidateMessage.REQUEST_PARAM_SUCCESSMSG);
			Ist_rr.add(rr);
			//debug日志(可以删除)
			logger.info("request_id:"+request_id+",提交参数校验成功!");
		}
	
		return Ist_rr;		
	}
}
