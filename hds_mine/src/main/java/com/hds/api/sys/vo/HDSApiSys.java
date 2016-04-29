package com.hds.api.sys.vo;

/**
* @ClassName:com.hds.api.sys.vo.HDSApiSys
* @Description:HDS系统   接口权限   model类
* @author zengli
* @date 创建时间：2016年4月17日 下午4:51:05
 */
public class HDSApiSys {

	private String sys_id;
	private String api_id;

	
	public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}
	public String getApi_id() {
		return api_id;
	}
	public void setApi_id(String api_id) {
		this.api_id = api_id;
	}
	
	public String toString(){
		String str = " HDSApiSys(sys_id:"+sys_id+",api_id:"+api_id+")";		
		return str;
	}
	
	
}
