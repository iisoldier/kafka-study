package com.hds.api.sys.vo;

import java.io.Serializable;


/**
* @ClassName:com.hds.api.sys.vo.OTSConfig
* @Description:HDS系统   ots配置信息   model类
* @author zengli
* @date 创建时间：2016年4月17日 下午4:53:02
 */
public class OTSConfig implements Serializable {

	private static final long serialVersionUID = -2055674628624266800L;

	private String aliyun_accesskey_id;
	private String aliyun_accesskey_secret;
	private String aliyun_ots_endpoint;
	private String aliyun_ots_instance;

	public String getAliyun_accesskey_id() {
		return aliyun_accesskey_id;
	}

	public void setAliyun_accesskey_id(String aliyun_accesskey_id) {
		this.aliyun_accesskey_id = aliyun_accesskey_id;
	}

	public String getAliyun_accesskey_secret() {
		return aliyun_accesskey_secret;
	}

	public void setAliyun_accesskey_secret(String aliyun_accesskey_secret) {
		this.aliyun_accesskey_secret = aliyun_accesskey_secret;
	}

	public String getAliyun_ots_endpoint() {
		return aliyun_ots_endpoint;
	}

	public void setAliyun_ots_endpoint(String aliyun_ots_endpoint) {
		this.aliyun_ots_endpoint = aliyun_ots_endpoint;
	}

	public String getAliyun_ots_instance() {
		return aliyun_ots_instance;
	}

	public void setAliyun_ots_instance(String aliyun_ots_instance) {
		this.aliyun_ots_instance = aliyun_ots_instance;
	}

	public String toString(){
		
		String str = " Aliyun OTSConfig(aliyun_accesskey_id:"+aliyun_accesskey_id+
				   ",aliyun_accesskey_secret:"+aliyun_accesskey_secret+
				   ",aliyun_ots_endpoint:"+aliyun_ots_endpoint+
				   ",aliyun_ots_instance:"+aliyun_ots_instance+")";
		return str;

	}
	
}
