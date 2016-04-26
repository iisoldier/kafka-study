package com.hds.api.sys.vo;

/**
* @ClassName:com.hds.api.sys.vo.HDSApi
* @Description: HDS系统  接口  model类
* @author zengli
* @date 创建时间：2016年4月17日 下午4:50:39
 */
public class HDSApi {

	private String api_id;
	private String api_name;
	private int api_flag;
	private String api_url;
	public String getApi_id() {
		return api_id;
	}
	public void setApi_id(String api_id) {
		this.api_id = api_id;
	}
	public String getApi_name() {
		return api_name;
	}
	public void setApi_name(String api_name) {
		this.api_name = api_name;
	}
	public int getApi_flag() {
		return api_flag;
	}
	public void setApi_flag(int api_flag) {
		this.api_flag = api_flag;
	}
	public String getApi_url() {
		return api_url;
	}
	public void setApi_url(String api_url) {
		this.api_url = api_url;
	}
	
	public String toString(){
		String str = " HDSApi(api_id:"+api_id+",api_name:"+api_name+
				",api_flag:"+api_flag+",api_url:"+api_url+")";
		return str;

	}
	
}
