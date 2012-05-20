package cn.fam1452.utils;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



/**
 * 日期处理公共类
 * Class DateUtil
 *
 * @author <a href="mailto:zhagndingding1@cyanway.com">Derek</a>
 * @version $Revision: 1.1 $Date: 2011/08/22 08:39:53 $
 */
public class DateUtil {

	public static long daylyMills = 86400000;//1 * 24 * 60 * 60 * 1000;  //一天的毫秒数
	
	private static SimpleDateFormat dateformater = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat timeformater = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat dateTimeformater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat timestampformater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	/**
	 * 时间字符串转成毫秒数,Ex:"2011-03-21 00:00:00"
	 */
	public static long getMillis4Str(String date){
		return getMillis4Str(date,dateTimeformater);
	}
	/**
	 * 自定义格式的时间字符串转成毫秒数,Ex:"2011-03-21 00:00:00"
	 */
	public static long getMillis4Str(String date,String patt){
			return getMillis4Str(date,new SimpleDateFormat(patt));
	}
	/**
	 * 自定义格式的时间字符串转成毫秒数,Ex:"2011-03-21 00:00:00"
	 */
	public static long getMillis4Str(String date,SimpleDateFormat sdf){
		try {
			return sdf.parse(date).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
}
	/**
	 * 获得上月第一天“yyyy-mm-dd”
	 * @author:Derek
	 * @date:2010-6-8
	 * @return
	 */
	static public String getPreviousMonthFirstDayStr(){
		Date currDate  = getCurrentDate();
		Date previousMonth   = getPreviousMonth(currDate);
		return convertDateToString(getMonthFirstDay(previousMonth));
	}
	/**
	 * 上月最后一天
	 * @author:Derek
	 * @date:2010-6-8
	 * @return
	 */
	static public String getPreviousMonthLastDayStr(){
		Date currDate  = getCurrentDate();	
		Date previousMonth   = getPreviousMonth(currDate);
		return convertDateToString(getMonthLastDay(previousMonth));
	}
	
	/**
	 * 当月后一天
	 * @author:Derek
	 * @date:2010-6-8
	 * @return
	 */
	static public String getCurrentMonthLastDayStr(){
		Date currDate  = getCurrentDate();
		return convertDateToString(getMonthLastDay(currDate));
	}
	/**
	 * 当月最第一天
	 * @author:Derek
	 * @date:2010-6-8
	 * @return
	 */
	static public String getCurrentMonthFirstDayStr(){
		Date currDate  = getCurrentDate();
		return convertDateToString(getMonthFirstDay(currDate));
	}
	
	/**
	 * 返回一个当前日期对象 java.util.Date
	 * @author:Derek
	 * @date:2010-6-8
	 * @return
	 */
	/*static public Date getCurrentDate(){
		long curr  = System.currentTimeMillis();
		Date currDate  = getCurrentDate(curr);
		return currDate;
	}*/
	static public Date getCurrentDate(long timeMillis){
		Date currDate  = new Date(timeMillis);
		return currDate;
	}
	
	/**
	 * 获取日期所在月的第一天
	 * @param month 日期 
	 * @return
	 */
	static public Date getMonthFirstDay(Date month) {
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(month);
		int year = ca.get(1);
		int mon = ca.get(2);
		GregorianCalendar gCal = new GregorianCalendar(year, mon, 1);
		return gCal.getTime();
	}
	/**
	 * 获取日期所在月份最后一天
	 * @param month
	 * @return
	 */
	static public Date getMonthLastDay(Date month) {
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTime(month);
		int year = ca.get(1);
		int mon = ca.get(2);
		int lastDay = ca.getActualMaximum(5);
		GregorianCalendar gCal = new GregorianCalendar(year, mon, lastDay);
		return gCal.getTime();
	}
	
	/**
	 * date转string "yyyy-MM-dd"
	 * @author:Derek
	 * @date:2010-6-8
	 * @param Date
	 * @return Date
	 */
	public static String convertDateToString(Date d)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", new DateFormatSymbols());
		return df.format(d);
	}
	
	public static String convertDateToString(Date d , String patt)
	{
		SimpleDateFormat df = new SimpleDateFormat(patt, new DateFormatSymbols());
		return df.format(d);
	}
	
	public static java.sql.Date convertStringToSqlDate(String s)
	{
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", new DateFormatSymbols());
		try {
			Date d = df.parse(s);
			return new java.sql.Date(d.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}*/
		
		return convertStringToSqlDate(s , pattern0) ;
	}
	
	public static java.sql.Date convertStringToSqlDate(String s ,String patt) {
		
		SimpleDateFormat df = new SimpleDateFormat(patt, new DateFormatSymbols());
		try {
			Date d = df.parse(s);
			return new java.sql.Date(d.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static Date convertStringToDate(String s ,String patt) {
		
		SimpleDateFormat df = new SimpleDateFormat(patt, new DateFormatSymbols());
		try {
			Date d = df.parse(s);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 返回上一月
	 * @author:Derek
	 * @date:2010-6-8
	 * @param Date
	 * @return Date
	 */
	public static Date getPreviousMonth(Date d){
		return new Date(getMonthFirstDay(d).getTime() - daylyMills);
	}
	
	//ITM Date Util ...
	public static final String pattern0 = "yyyy-MM-dd";
	public static final String pattern1 = "yyyy-MM-dd HH:mm:ss";
	public static final String pattern2 = "yyyy-MM-dd HH:mm";
	public static final String pattern3 = "yyyyMMdd";
	

	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}
	
	public static Date getDate(long timeMillis){
		return new Date(timeMillis); 
	}
	
	public static String DateToString(Date date ,String p){
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		String d = sdf.format(date);
		return d;
	}
	
	public static Date getCurrentDate(){
		return getDate(getCurrentTimeMillis());
	}
	/**
	 * 返回当前日期，格式 "yyyy-MM-dd" ; $pattern0
	 */
	public static String getCurrentDateStr(){
		return DateToString(getCurrentDate() , pattern0);
	}
	/**
	 * 返回当前日期，格式 "yyyyMMdd" ; $pattern3
	 */
	public static String getCurrentDateStr8Bit(){
		return DateToString(getCurrentDate() , pattern3);
	}
	/**
	 * 返回当前日期，格式 "yyyy-MM-dd HH:mm:ss" ; @link pattern0
	 */
	public static String getCurrentDateTimeStr(){
		return DateToString(getCurrentDate() , pattern1);
	}
	
	public static Timestamp getCurrentTimestamp(){
		return new Timestamp(getCurrentTimeMillis());
	}
	/**
	 *  7天前日期，格式  "yyyy-MM-dd"
	 */
	public static String getAfter7DateStr(){
		//1000*60*60*24*7 = 604800000; 7天的毫秒数
		//1000*60*60*24*6 = 518400000; 6天的毫秒数
		return DateToString(getDate(getCurrentTimeMillis()-518400000) , pattern0);
	}
	
	public static long getMinuteMillis(int minutes){
		
		return 1000*60*minutes;
	}
	
	public static void main(String[] args) {
		StringUtil.print(getMillis4Str("2011-03-21 00:00:00")) ;
		System.out.println(DateToString(getDate(getMillis4Str("2011-03-21 00:00:00")), pattern1));
		
	}
}
