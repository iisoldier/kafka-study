package com.hds.api.sys.vo;

/**
* @ClassName:com.hds.api.sys.vo.HDSIPWhiteList
* @Description:HDS系统   系统IP白名单  model类
* @author zengli
* @date 创建时间：2016年4月17日 下午4:51:28
 */
public class HDSIPWhiteList {

	private String sys_id;
	private String sys_ip;
	
	public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}
	public String getSys_ip() {
		return sys_ip;
	}
	public void setSys_ip(String sys_ip) {
		this.sys_ip = sys_ip;
	}
	
	public String toString(){
		
		String str = " HDSIPWhiteList(sys_id:"+sys_id+",sys_ip:"+sys_ip+")";
		return str;

	}
  
}
