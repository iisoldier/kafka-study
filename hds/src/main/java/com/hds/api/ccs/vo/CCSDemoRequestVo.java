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
* @ClassName:com.hds.api.ccs.vo.CCSDemoRequestVo
* @Description: ccs查询demo  反馈消息 model类
* @author zengli
* @date 创建时间：2016年4月17日 下午3:58:14
 */
@JSONType(orders={"sysid","spkpk1","spkpk2","epkpk1","epkpk2","direction","limit","startday","endday"},
    ignores={"request_id","request_ip"})
public class CCSDemoRequestVo {

    private String sysid;
    private int   spkpk1;
    private int   spkpk2;
    private int   epkpk1;
    private int   epkpk2;
    private String direction;
    private int    limit;
    //测试字段
	private String startday;
    private String endday;
    
    //其他传递对象 不被序列化和校验,仅作参数传递
    private String request_id;
    private String request_ip;
	

	@NotNull(message=RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	@Length(max=RequestValidation.Constant.SYSID_MAX,min=RequestValidation.Constant.SYSID_MIN,
	message=RequestValidation.ValidateMessage.PRARM_SYSID_LENGTH_ERRMSG)
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	@NotNull(message=RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public int getSpkpk1() {
		return spkpk1;
	}
	public void setSpkpk1(int spkpk1) {
		this.spkpk1 = spkpk1;
	}
	@NotNull(message=RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public int getSpkpk2() {
		return spkpk2;
	}
	public void setSpkpk2(int spkpk2) {
		this.spkpk2 = spkpk2;
	}
	@NotNull(message=RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public int getEpkpk1() {
		return epkpk1;
	}
	public void setEpkpk1(int epkpk1) {
		this.epkpk1 = epkpk1;
	}
	@NotNull(message=RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	public int getEpkpk2() {
		return epkpk2;
	}
	public void setEpkpk2(int epkpk2) {
		this.epkpk2 = epkpk2;
	}
	
	@NotNull(message = RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	@Pattern(regexp=RequestValidation.Constant.DIRECTION_REGEXP,
			  message=RequestValidation.ValidateMessage.PRARM_DIRECTION_ERRMSG)
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	   //直接通过binding result的error来判断类型转化错误，错误类型typeMismatch
	@NotNull(message=RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
    @Max(value=RequestValidation.Constant.LIMIT_MAX,
            message=RequestValidation.ValidateMessage.PRARM_LIMIT_MAX_ERRMSG)
    @Min(value=RequestValidation.Constant.LIMIT_MIN,
            message=RequestValidation.ValidateMessage.PRARM_LIMIT_MIN_ERRMSG)
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}



	@NotNull(message=RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	@DateStrValidator(isDateStr=true,message=RequestValidation.ValidateMessage.PRARM_ISDATESTR_ERRMSG)
    public String getStartday() {
		return startday;
	}
	public void setStartday(String startday) {
		this.startday = startday;
	}
	
	@NotNull(message=RequestValidation.ValidateMessage.PRARM_NOTNULL_ERRMSG)
	@DateStrValidator(isDateStr=true,message=RequestValidation.ValidateMessage.PRARM_ISDATESTR_ERRMSG)
	public String getEndday() {
		return endday;
	}
	public void setEndday(String endday) {
		this.endday = endday;
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
//		String str =  sysid +R.SystemConstant.LOG_COL_DELIMITER+
//				spkpk1+R.SystemConstant.LOG_COL_DELIMITER+
//				spkpk2+R.SystemConstant.LOG_COL_DELIMITER+
//				epkpk1+R.SystemConstant.LOG_COL_DELIMITER+
//				epkpk2+R.SystemConstant.LOG_COL_DELIMITER+
//				direction+R.SystemConstant.LOG_COL_DELIMITER+
//				limit+R.SystemConstant.LOG_COL_DELIMITER+
//				startday+R.SystemConstant.LOG_COL_DELIMITER+
//				endday;
		
		
		String str =  "CCSDemoRequestVo[sysid="+sysid
				+ ",spkpk1="+spkpk1
				+",spkpk2="+spkpk2
				+",epkpk1="+epkpk1
				+",epkpk2="+epkpk2
				+",startday="+startday
				+",endday="+endday
				//+",o_cols="+o_cols
				+",direction="+direction
				+",limit="+limit
				+"]";		
		
		return str;
	}
}
