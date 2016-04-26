package com.hds.api.sys.vo;

/**
* @ClassName:com.hds.api.sys.vo.HDSSys
* @Description:HDS系统   接入系统  model类
* @author zengli
* @date 创建时间：2016年4月17日 下午4:52:36
 */
public class HDSSys {

	private String sys_id;
	private String sys_bfname;
	private int sys_flag;
	

	public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}
	public String getSys_bfname() {
		return sys_bfname;
	}
	public void setSys_bfname(String sys_bfname) {
		this.sys_bfname = sys_bfname;
	}
	public int getSys_flag() {
		return sys_flag;
	}
	public void setSys_flag(int sys_flag) {
		this.sys_flag = sys_flag;
	}
	public String toString(){
		String str = " HDSSys(sys_id:"+sys_id+",sys_bfname:"+sys_bfname+",sys_flag:"+sys_flag+")";
		return str;
	}
	
	
}
