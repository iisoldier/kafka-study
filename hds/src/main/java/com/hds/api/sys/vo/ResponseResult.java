package com.hds.api.sys.vo;

import com.alibaba.fastjson.annotation.JSONType;



/**
* @ClassName:com.hds.api.sys.vo.ResponseResult
* @Description: 查询请求反馈结果域 ,下列情况会产生返回码
* @Description: 请求参数基础格式校验
* @Description: 请求参数业务逻辑校验，传入的时间范围进行时间差校验
* @Description:  ots 触发的Service Exception/Client Exception  
* @Description:  系统内部错误
* @Description:  正常完成查询
* @author zengli
* @date 创建时间：2016年4月17日 下午4:53:41
 */
@JSONType(orders={"type","result_code","field","message"})
public class ResponseResult{


	private  String type;
	private  String result_code;
	private  String field;
	private  String message;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String toString(){
		String str = " ResponseResult(type:"+type+",result_code:"+result_code+
				",field:"+field+",message:"+message+")";
		return str;
	}
	
	
}
