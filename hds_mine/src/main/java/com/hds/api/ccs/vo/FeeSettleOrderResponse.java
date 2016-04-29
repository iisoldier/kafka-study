/** 
 * @ClassName:FeeSettleOderRequest
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月22日 上午11:19:30
 */
package com.hds.api.ccs.vo;

import java.util.List;
import com.alibaba.fastjson.annotation.JSONType;
import com.hds.api.sys.vo.RecordRow;
import com.hds.api.sys.vo.ResponseResult;

/**
 * @ClassName:FeeSettleOderRequest.FeeSettleOrderResponse
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月22日 上午11:19:30
 */
@JSONType(orders = { "request_id", "sysid", "responsetime", "request_param",
		"response_records", "reponse_result" })
public class FeeSettleOrderResponse {
	private String request_id;
	private String sysid;
	private String responsetime;
	private FeeSettleOrderRequest request_param;
	private List<RecordRow> response_records;
	private List<ResponseResult> reponse_result;
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	public String getResponsetime() {
		return responsetime;
	}
	public void setResponsetime(String responsetime) {
		this.responsetime = responsetime;
	}
	public FeeSettleOrderRequest getRequest_param() {
		return request_param;
	}
	public void setRequest_param(FeeSettleOrderRequest request_param) {
		this.request_param = request_param;
	}
	public List<RecordRow> getResponse_records() {
		return response_records;
	}
	public void setResponse_records(List<RecordRow> response_records) {
		this.response_records = response_records;
	}
	public List<ResponseResult> getReponse_result() {
		return reponse_result;
	}
	public void setReponse_result(List<ResponseResult> reponse_result) {
		this.reponse_result = reponse_result;
	}
	
}
