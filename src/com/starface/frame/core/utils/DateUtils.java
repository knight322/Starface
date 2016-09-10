package com.starface.frame.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	
	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public static String DateToStr(Date date,String format) {
	  
	   SimpleDateFormat sdf = new SimpleDateFormat(format);
	   String str = sdf.format(date);
	   return str;
	}
	
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str,String format) {
	  
	   SimpleDateFormat sdf = new SimpleDateFormat(format);
	   Date date = null;
	   try {
	    date = sdf.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	/**
	 * 获取星期几
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 传入星期几，根据当前时间得到星期几的日期
	 * @param week
	 * @return
	 */
	public static String getDayForWeek(int week){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		Calendar calendar=Calendar.getInstance(Locale.CHINA);
		//当前时间，貌似多余，其实是为了所有可能的系统一致
		calendar.setTimeInMillis(System.currentTimeMillis());
		int w = calendar.get(Calendar.DAY_OF_WEEK) -1;
		int dayc = 0;
		if(week < w){
			dayc = Math.abs(w-7)+week;
		}else{
			dayc = week - w;
		}
		System.out.println(dayc);
		Date date = calendar.getTime();
		Date toDate = org.apache.commons.lang.time.DateUtils.addDays(date, dayc);
		String dd  = sdf.format(toDate);
		return dd;
	}

	public static String addDate(String theDay,int amount){
		Date date = StrToDate(theDay, "yyyy-MM-dd");
		Date toDate = org.apache.commons.lang.time.DateUtils.addDays(date, amount);
		String dd  = DateToStr(toDate, "yyyy-MM-dd");
		return dd;
	}
	
	public static String addMonth(String theMonth,int amount){
		Date theM = StrToDate(theMonth, "yyyyMM");
		Date toDate = org.apache.commons.lang.time.DateUtils.addMonths(theM, amount);
		String dd  = DateToStr(toDate, "yyyyMM");
		return dd;
	}
	
	public static Date getLastDayOfMonth(Date   sDate1)   {  
        Calendar   cDay1   =   Calendar.getInstance();  
        cDay1.setTime(sDate1);  
        final   int   lastDay   =   cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);  
        Date   lastDate   =   cDay1.getTime();  
        lastDate.setDate(lastDay);  
        return   lastDate;  
	}
	
	public static Date getFirstDayOfMonth(Date sDate1)   {
        Calendar cDay1 = Calendar.getInstance();  
        cDay1.setTime(sDate1);  
        final int firstDay = cDay1.getActualMinimum(Calendar.DAY_OF_MONTH);
        Date firstDate = cDay1.getTime();  
        firstDate.setDate(firstDay);
        return firstDate;
	}
	
	public static Date getLastDay(Date   sDate1)   {  
        Calendar   cDay1   =   Calendar.getInstance();  
        cDay1.setTime(sDate1);  
        final   int   lastDay   =   cDay1.getActualMaximum(Calendar.DATE);  
        Date   lastDate   =   cDay1.getTime();  
        lastDate.setDate(lastDay);  
        return   lastDate;  
	}
	
	public static Date getFirstDay(Date sDate1)   {
        Calendar cDay1 = Calendar.getInstance();  
        cDay1.setTime(sDate1);  
        final int firstDay = cDay1.getActualMinimum(Calendar.DATE);
        Date firstDate = cDay1.getTime();  
        firstDate.setDate(firstDay);
        return firstDate;
	}
}