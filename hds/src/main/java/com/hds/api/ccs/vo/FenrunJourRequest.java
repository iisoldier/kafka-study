/** 
 * @ClassName:com.hds.api.ccs.vo
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月22日 上午11:18:28
 */
package com.hds.api.ccs.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONType;
import com.hds.api.common.annontion.DateStrValidator;
import com.hds.api.common.constant.RequestValidation;

/**
 * @ClassName:com.hds.api.ccs.vo.FenrunJourRequest
 * @Description: 分润流水查询请求类
 * @author zengli
 * @date 创建时间：2016年4月26日 下午3:15:01
 */
@JSONType(orders = { "sysid", "s_fee_sn", "s_occur_date", "s_org_code",
		"s_fee_category1", "s_fee_category2", "e_fee_sn", "e_occur_date",
		"e_org_code", "e_fee_category1", "e_fee_category2", "direction",
		"limit" }, ignores = { "request_id", "request_ip" })
public class FenrunJourRequest {
	// 系统编号
	private String sysid;
	// startpk
	private String s_fee_sn;
	private String s_occur_date;
	private String s_org_code;
	private String s_fee_category1;
	private String s_fee_category2;
	// endpk
	private String e_fee_sn;
	private String e_occur_date;
	private String e_org_code;
	private String e_fee_category1;
	private String e_fee_category2;
	// 查询附加条件
	// private String m_cols;
	// private String o_cols;
	private String direction;
	private int limit;
	// 其他传递对象 不被序列化和校验,仅作参数传递
	private String request_id;
	private String request_ip;

	
	//-----------------------------  Sysid  --------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	@Length(max = RequestValidation.Constant.SYSID_MAX, 
	     min = RequestValidation.Constant.SYSID_MIN, 
	       message = RequestValidation.ValidateMessage.PRARM_SYSID_LENGTH_ERRMSG)
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	//------------------------------S_fee_sn -------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public String getS_fee_sn() {
		return s_fee_sn;
	}
	public void setS_fee_sn(String s_fee_sn) {
		this.s_fee_sn = s_fee_sn;
	}

	//-------------------------------S_occur_date  ------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	@DateStrValidator(isDateStr = true, message = RequestValidation.ValidateMessage.PRARM_ISDATESTR_ERRMSG)
	public String getS_occur_date() {
		return s_occur_date;
	}
	public void setS_occur_date(String s_occur_date) {
		this.s_occur_date = s_occur_date;
	}

	//-------------------------------S_org_code  ------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public String getS_org_code() {
		return s_org_code;
	}
	public void setS_org_code(String s_org_code) {
		this.s_org_code = s_org_code;
	}

	//-------------------------------S_fee_category1  ------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public String getS_fee_category1() {
		return s_fee_category1;
	}
	public void setS_fee_category1(String s_fee_category1) {
		this.s_fee_category1 = s_fee_category1;
	}

	//-------------------------------S_fee_category2 ------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public String getS_fee_category2() {
		return s_fee_category2;
	}
	public void setS_fee_category2(String s_fee_category2) {
		this.s_fee_category2 = s_fee_category2;
	}

	
	//-------------------------------E_fee_sn-----------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public String getE_fee_sn() {
		return e_fee_sn;
	}
	public void setE_fee_sn(String e_fee_sn) {
		this.e_fee_sn = e_fee_sn;
	}

	//-------------------------------E_occur_date-----------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	@DateStrValidator(isDateStr = true, message = RequestValidation.ValidateMessage.PRARM_ISDATESTR_ERRMSG)
	public String getE_occur_date() {
		return e_occur_date;
	}
	public void setE_occur_date(String e_occur_date) {
		this.e_occur_date = e_occur_date;
	}

	//-------------------------------E_org_code-----------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public String getE_org_code() {
		return e_org_code;
	}
	public void setE_org_code(String e_org_code) {
		this.e_org_code = e_org_code;
	}

	//-------------------------------E_fee_category1-----------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public String getE_fee_category1() {
		return e_fee_category1;
	}
	public void setE_fee_category1(String e_fee_category1) {
		this.e_fee_category1 = e_fee_category1;
	}

	//-------------------------------E_fee_category2-----------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public String getE_fee_category2() {
		return e_fee_category2;
	}
	public void setE_fee_category2(String e_fee_category2) {
		this.e_fee_category2 = e_fee_category2;
	}

	
	
	//--------------------Direction  -------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	@Pattern(regexp = RequestValidation.Constant.DIRECTION_REGEXP, 
			message = RequestValidation.ValidateMessage.PRARM_DIRECTION_ERRMSG)
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	//--------------------Limit  -------------------------------------------
	// 直接通过binding result的error来判断类型转化错误，错误类型typeMismatch
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	@Max(value = RequestValidation.Constant.LIMIT_MAX, 
	           message = RequestValidation.ValidateMessage.PRARM_LIMIT_MAX_ERRMSG)
	@Min(value = RequestValidation.Constant.LIMIT_MIN, 
	           message = RequestValidation.ValidateMessage.PRARM_LIMIT_MIN_ERRMSG)
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

	
	//不需要序列化 
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getRequest_ip() {
		return request_ip;
	}
	public void setRequest_ip(String request_ip) {
		this.request_ip = request_ip;
	}

	public String toString() {
		// 不添加request_id和request_vo
		String str = "FeeSettleOrderRequest[sysid=" + sysid + ",s_order_sn="
				+ s_fee_sn + ",s_occur_date=" + s_occur_date + ",s_deal_state="
				+ s_org_code + ",s_credit_org_code=" + s_fee_category1
				+ ",s_debit_org_code=" + s_fee_category2 + ",e_order_sn="
				+ e_fee_sn + ",e_occur_date=" + e_occur_date + ",e_deal_state="
				+ e_org_code + ",e_credit_org_code=" + e_fee_category1
				+ ",e_debit_org_code=" + e_fee_category2
				// +",o_cols="+o_cols
				+ ",direction=" + direction + ",limit=" + limit + "]";

		return str;
	}

}
