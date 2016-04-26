package com.hds.api.exception;



/**
* @ClassName:com.hds.api.exception.HDSServiceException
* @Description: HDSException定义
* @author zengli
* @date 创建时间：2016年4月17日 下午4:30:40
 */
public class HDSServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	private String code;
	private String msg;

	public HDSServiceException() {
	}
	public HDSServiceException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
