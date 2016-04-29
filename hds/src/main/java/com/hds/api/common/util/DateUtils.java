package com.hds.api.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.hds.api.common.constant.R;
import com.hds.api.exception.HDSServiceException;

/**
* @ClassName:com.hds.api.common.util.DateUtils
* @Description:  日期方法类
* @author zengli
* @date 创建时间：2016年4月17日 下午4:10:26
 */
public final class DateUtils {
	
	private static Logger logger = Logger.getLogger(DateUtils.class);

/**
* @method  toString
* @Description: 当前日期转为String 
* @author  zengli
* @date 2016年4月17日 下午4:12:33
* @parameter   pattern
* @return  String
 */
	public static String toString(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}
	
	/**
	* @method  toString
	* @Description: 指定的Date转为String 
	* @author  zengli
	* @date 2016年4月17日 下午4:15:45
	* @parameter   date,pattern
	* @return  String
	 */
	public static String toString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	* @method  toDate
	* @Description:Data to Date转换
	* @author  zengli
	* @date 2016年4月17日 下午4:16:20
	* @parameter  date,pattern
	* @return  Date
	 */
	public static Date toDate(String source, String pattern)
			throws HDSServiceException {
		try {
			return new SimpleDateFormat(pattern).parse(source);
		} catch (Exception e) {
			logger.error(R.HDSErrorEnum.ERROR_DATE_FORMAT.getLabel()+"，错误的值为:"+source);
			throw new HDSServiceException(
					R.HDSErrorEnum.ERROR_DATE_FORMAT.getValue(),
					R.HDSErrorEnum.ERROR_DATE_FORMAT.getLabel()
					);
		}
	}
	
	/**
	* @method  getNextDay
	* @Description: 获取下一天
	* @author  zengli
	* @date 2016年4月17日 下午4:16:42
	* @parameter  date
	* @return  Date
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, R.DateConstant.ONE_DAY);
		return calendar.getTime();
	}
	
	/**
	* @method  getDaysBetween
	* @Description: 计算两个String类型数据的日期时间差 (通过joda-time快速实现)
	* @author  zengli
	* @date 2016年4月17日 下午4:17:04
	* @parameter  sdate edate
	* @return  int
	 */
	public static int getDaysBetween(String sdate, String edate){ // throws HDSServiceException{
		//try{
			LocalDate ls_date = new LocalDate(Integer.parseInt(sdate.substring(0,4)),
					Integer.parseInt(sdate.substring(4,6)),Integer.parseInt(sdate.substring(6,8)));
			LocalDate le_date = new LocalDate(Integer.parseInt(edate.substring(0,4)),
					Integer.parseInt(edate.substring(4,6)),Integer.parseInt(edate.substring(6,8)));
				int between_days  =  Days.daysBetween(ls_date,le_date).getDays();
				return between_days;
	}
	
	
	
	public static String getDayStrBySec(Long sec){
         GregorianCalendar gc = new GregorianCalendar(); 
         gc.setTimeInMillis(sec * 1000);
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         String formatStr = format.format(gc.getTime());
		return formatStr;
	}
	
	
}
