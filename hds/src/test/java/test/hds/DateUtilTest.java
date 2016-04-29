package test.hds;

import com.hds.api.exception.HDSServiceException;
import com.hds.api.sys.vo.ResponseResult;

public class DateUtilTest {
	
	
	public static void main(String args[]) throws HDSServiceException{
		//日期差
		//int between_days  =  DateUtils.getDaysBetween("2016-0101","20161010");
		//System.out.println(between_days);
		
		//String start_date = DateUtils.getDayStrBySec(Long.valueOf("1461121191"));		
		//String end_date = DateUtils.getDayStrBySec(Long.valueOf("1461121192"));
		
         //System.out.println(start_date +"|" +end_date);
		
		ResponseResult rr_req_frequency = new ResponseResult();
		rr_req_frequency.setMessage(null);
		rr_req_frequency.setResult_code(null);
		rr_req_frequency.setField(null);
		rr_req_frequency.setType(null);
		
		if(rr_req_frequency.equals("1")){
			System.out.println("1");
		}
		else{
			System.out.println("2");
		}
	}
	
	
}
