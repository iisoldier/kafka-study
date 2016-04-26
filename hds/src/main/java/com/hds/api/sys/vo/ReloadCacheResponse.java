package com.hds.api.sys.vo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONType;
import com.hds.api.sys.vo.ResponseResult;

/**
 * @ClassName:com.hds.api.ccs.vo.CCSDemoResponseVo
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月17日 下午4:01:25
 */
@JSONType(orders = { "request_id", "responsetime", "request_param", "reponse_result" })
public class ReloadCacheResponse {
	
	private String request_id;
	private String responsetime;
	private ReloadCacheRequest request_param;
	private List<ResponseResult> reponse_result;
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getResponsetime() {
		return responsetime;
	}
	public void setResponsetime(String responsetime) {
		this.responsetime = responsetime;
	}
	public ReloadCacheRequest getRequest_param() {
		return request_param;
	}
	public void setRequest_param(ReloadCacheRequest request_param) {
		this.request_param = request_param;
	}
	public List<ResponseResult> getReponse_result() {
		return reponse_result;
	}
	public void setReponse_result(List<ResponseResult> reponse_result) {
		this.reponse_result = reponse_result;
	}

}
