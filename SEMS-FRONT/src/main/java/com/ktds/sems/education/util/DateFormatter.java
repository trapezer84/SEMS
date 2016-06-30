package com.ktds.sems.education.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

	// 2016년 3월 3일 오전 12:02
	// Ex : "yyyy-MM-dd HH:mm:ss"
	public final static String morning = "yyyy년 MM월 dd일 오전 HH:mm";
	public final static String night = "yyyy년 MM월 dd일 오후 HH:mm";
	public static final String FORMAT_DATE_DEFAULT = "yyyy-MM-dd";
	public static final String FORMAT_DATE_DEFAULT_NOTIME = "yy/MM/dd";
	public static final String FORMAT_CALDATE = "dd/MM/yy";
	
	
	public static String strToCalDateTime(String str_date) throws ParseException {
		if (str_date == null)
		    return null;
		  SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_DEFAULT);
    	  Date date = formatter.parse(str_date);
		  return parseCalDateTime(date);
	}
	public static String parseCalDateTime(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime().toGMTString();
	}
	
	public static Date strToDateTime(String format, String str_date) throws ParseException {
		 
		if (str_date == null)
		    return null;
		    
		  SimpleDateFormat formatter = new SimpleDateFormat(format);
		 
		  return formatter.parse(str_date);
	}
	
	
	public static String changeFormat(Date date) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_DEFAULT);
		String format_date = formatter.format(date);
		
		return format_date;
		//return formatter.parse(format_date);
	}
	public static String changeFormatNoTime(Date date) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_DEFAULT_NOTIME);
		String format_date = formatter.format(date);
		
		return format_date;
		//return formatter.parse(format_date);
	}
	
	public static String parseDate( String str_date ) throws ParseException {
		
		Date date = null;
		if( str_date.contains("오전") ){
			date = strToDateTime(morning, str_date);
		} else if ( str_date.contains("오후")) {
			date = strToDateTime(night, str_date);
		} else{
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if( str_date.contains("오전")){
			if(cal.getTime().getHours()==12 ){
				cal.add(cal.HOUR_OF_DAY, -12);
			}
		}
		if( str_date.contains("오후")){
			if(cal.getTime().getHours()!=12 ){
				cal.add(cal.HOUR_OF_DAY, 12);
			}
		}
		return  changeFormat(cal.getTime());
	}

	
	 public static String parseNoticeDate( String str_date ) throws ParseException {
		
		Date date = null;
		if( str_date.contains("오전") ){
			date = strToDateTime(morning, str_date);
		} else if ( str_date.contains("오후")) {
			date = strToDateTime(night, str_date);
		} else{
			return null;
		}
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if( str_date.contains("오전")){
			if(cal.getTime().getHours()==12 ){
				cal.add(cal.HOUR_OF_DAY, -12);
			}
		}
		if( str_date.contains("오후")){
			if(cal.getTime().getHours()!=12 ){
				cal.add(cal.HOUR_OF_DAY, 12);
			}
		}
		return  cal.getTime().toGMTString();
	}
	
}
