package com.hds.api.sys.vo;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.alibaba.fastjson.annotation.JSONType;
import com.hds.api.common.constant.RequestValidation;

/**
* @ClassName:com.hds.api.ccs.vo.CCSDemoRequestVo
* @Description: ccs查询demo  反馈消息 model类
* @author zengli
* @date 创建时间：2016年4月17日 下午3:58:14
 */
@JSONType(orders={"sysid"})
public class ReloadCacheRequest {

    private String sysid;
    
    //其他传递对象 不被序列化和校验,仅作参数传递
    private String request_id;
    private String request_ip;
	

	@NotNull(message=RequestValidation.ValidateMessage.NOTNULL_ERRMSG)
	@Length(max=RequestValidation.Constant.SYSID_MAX,min=RequestValidation.Constant.SYSID_MIN,
	message=RequestValidation.ValidateMessage.SYSID_LENGTH_ERRMSG)
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

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
		String str =  sysid ;
		return str;
	}
}
