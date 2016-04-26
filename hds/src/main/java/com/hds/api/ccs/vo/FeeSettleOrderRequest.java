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
 * @ClassName:com.hds.api.ccs.vo.FeeSettleOderRequest
 * @Description:
 * @author zengli
 * @date 创建时间：2016年4月22日 上午11:18:28
 */

@JSONType(orders = { "sysid", "s_order_sn", "s_occur_date", "s_deal_state",
		"s_credit_org_code", "s_debit_org_code", "e_order_sn", "e_occur_date",
		"e_deal_state", "e_credit_org_code", "e_debit_org_code", "direction",
		"limit" }, ignores = { "request_id", "request_ip" })
public class FeeSettleOrderRequest {
	// 系统编号
	private String sysid;
	// startpk
	private String s_order_sn;
	private String s_occur_date;
	private String s_deal_state;
	private String s_credit_org_code;
	private String s_debit_org_code;
	// endpk
	private String e_order_sn;
	private String e_occur_date;
	private String e_deal_state;
	private String e_credit_org_code;
	private String e_debit_org_code;
	// 查询附加条件
	// private String m_cols;
	// private String o_cols;
	private String direction;
	private int limit;
	// 其他传递对象 不被序列化和校验,仅作参数传递
	private String request_id;
	private String request_ip;

	// ------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	@Length(max = RequestValidation.Constant.SYSID_MAX, 
	     min = RequestValidation.Constant.SYSID_MIN, 
	       message = RequestValidation.ValidateMessage.SYSID_LENGTH_ERRMSG)
	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	//S_order_sn------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	public String getS_order_sn() {
		return s_order_sn;
	}

	public void setS_order_sn(String s_order_sn) {
		this.s_order_sn = s_order_sn;
	}

	//S_occur_date ------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	@DateStrValidator(isDateStr = true, message = RequestValidation.ValidateMessage.ISDATESTR_ERRMSG)
	public String getS_occur_date() {
		return s_occur_date;
	}

	public void setS_occur_date(String s_occur_date) {
		this.s_occur_date = s_occur_date;
	}

	//S_deal_state------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	@Length(max = RequestValidation.CCSConstant.DEAL_STATE_MAX, 
	         min = RequestValidation.CCSConstant.DEAL_STATE_MIN, 
	         message = RequestValidation.CCSValidateMessage.DEALSTATE_LENGTH_ERRMSG)
	public String getS_deal_state() {
		return s_deal_state;
	}

	public void setS_deal_state(String s_deal_state) {
		this.s_deal_state = s_deal_state;
	}

	//S_credit_org_code------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	public String getS_credit_org_code() {
		return s_credit_org_code;
	}

	public void setS_credit_org_code(String s_credit_org_code) {
		this.s_credit_org_code = s_credit_org_code;
	}

	//S_debit_org_code ------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	public String getS_debit_org_code() {
		return s_debit_org_code;
	}

	public void setS_debit_org_code(String s_debit_org_code) {
		this.s_debit_org_code = s_debit_org_code;
	}

	//E_order_sn------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	public String getE_order_sn() {
		return e_order_sn;
	}

	public void setE_order_sn(String e_order_sn) {
		this.e_order_sn = e_order_sn;
	}

	// E_occur_date------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	@DateStrValidator(isDateStr = true, message = RequestValidation.ValidateMessage.ISDATESTR_ERRMSG)
	public String getE_occur_date() {
		return e_occur_date;
	}

	public void setE_occur_date(String e_occur_date) {
		this.e_occur_date = e_occur_date;
	}

	// E_deal_state------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	@Length(max = RequestValidation.CCSConstant.DEAL_STATE_MAX, 
	         min = RequestValidation.CCSConstant.DEAL_STATE_MIN, 
	         message = RequestValidation.CCSValidateMessage.DEALSTATE_LENGTH_ERRMSG)
	public String getE_deal_state() {
		return e_deal_state;
	}

	public void setE_deal_state(String e_deal_state) {
		this.e_deal_state = e_deal_state;
	}

	// E_credit_org_code------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	public String getE_credit_org_code() {
		return e_credit_org_code;
	}

	public void setE_credit_org_code(String e_credit_org_code) {
		this.e_credit_org_code = e_credit_org_code;
	}

	// E_debit_org_code------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	public String getE_debit_org_code() {
		return e_debit_org_code;
	}

	public void setE_debit_org_code(String e_debit_org_code) {
		this.e_debit_org_code = e_debit_org_code;
	}

	// public String getM_cols() {
	// return m_cols;
	// }
	// public void setM_cols(String m_cols) {
	// this.m_cols = m_cols;
	// }
	//
	// public String getO_cols() {
	// return o_cols;
	// }
	// public void setO_cols(String o_cols) {
	// this.o_cols = o_cols;
	// }

	// Direction------------------------------------------------------------------------------------------------
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	@Pattern(regexp = RequestValidation.Constant.DIRECTION_REGEXP, 
			message = RequestValidation.ValidateMessage.DIRECTION_ERRMSG)
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	//Limit ------------------------------------------------------------------------------------------------
	// 直接通过binding result的error来判断类型转化错误，错误类型typeMismatch
	@NotNull(message = RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	@Max(value = RequestValidation.Constant.LIMIT_MAX, message = RequestValidation.ValidateMessage.LIMIT_MAX_ERRMSG)
	@Min(value = RequestValidation.Constant.LIMIT_MIN, message = RequestValidation.ValidateMessage.LIMIT_MIN_ERRMSG)
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	// -----------------不需要校验-------------------------------------------------------------------------------
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
	
	

	public String toString(){
		//不添加request_id和request_vo
		String str =  "FeeSettleOrderRequest[sysid="+sysid
				+ ",s_order_sn="+s_order_sn
				+",s_occur_date="+s_occur_date
				+",s_deal_state="+s_deal_state
				+",s_credit_org_code="+s_credit_org_code
				+",s_debit_org_code="+s_debit_org_code
				+",e_order_sn="+e_order_sn
				+",e_occur_date="+e_occur_date
				+",e_deal_state="+e_deal_state
				+",e_credit_org_code="+e_credit_org_code
				+",e_debit_org_code="+e_debit_org_code
				//+",o_cols="+o_cols
				+",direction="+direction
				+",limit="+limit
				+"]";		

		return str;
	}

	
}
