package cn.fam1452.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 观测站工具，用于java解析文件名中得观测站信息
 * Class StationUtil
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Oct 3, 2012 11:29:22 AM $
 */
public class StationUtil {
	/**
	 * 解析观测站ID
	 * @Author Derek
	 * @Date Oct 3, 2012
	 * @param fileName
	 * @return
	 */
	public static String getStationId(String fileName){
		String sid = "WU430" ;
		if(fileName.indexOf("WU430_") != -1){
			sid = "WU430" ;
    	}else if(fileName.indexOf("wh") != -1){
    		
    	}
		
		return sid ;
	}
	
	/**
	 * 从文件名中解析观测日期
	 * @Author Derek
	 * @Date Oct 3, 2012
	 * @param fileName
	 * @return
	 */
	public static Date getObserveDate(String fileName){
		Date d = DateUtil.getCurrentDate() ;
		if(fileName.indexOf("WU430_") != -1){
			int i =  6;
			int l = fileName.lastIndexOf(".") ;
			
			d = DateUtil.convertStringToDate(fileName.substring(i, l), DateUtil.pattern4) ;
    	}else if(fileName.indexOf("wh") != -1){
    		int i =  2;
    		int l = fileName.lastIndexOf(".") ;
    		
    		d = DateUtil.convertStringToDate(fileName.substring(i, l), DateUtil.pattern5) ;
    	}
		
		return d ;
	}
	
	public static String getPgtType(Date date){
		String type = "0" ;
		Calendar calendar = Calendar.getInstance() ;
		
		//手动频高图日期范围
		calendar.set(1946, 0, 1) ; //1946-01-01
        Date pt1min = calendar.getTime() ; 
        
        calendar.set(1956, 11, 1) ;//1956-12-01
        Date pt1max = calendar.getTime() ; 
        
        //胶片类型
        calendar.set(1957, 5, 1) ;//1957-06-01
        Date pt2min = calendar.getTime() ; 
        
        calendar.set(1991, 9, 1) ;//1991-10-01
        Date pt2max = calendar.getTime() ; 
        
        if(pt1max.after(date)  && pt1min.before(date) ){ 
        	type = "1" ;
        }
        else if(pt2max.after(date) && pt2min.before(date) ){
        	type = "2" ;
        }
        
        return type ;
	}
	
	/**
	 * 参数报表图文件名中解析出Date
	 * @Author Derek
	 * @Date Oct 3, 2012
	 * @param fileName
	 * @return
	 */
	public static Date getSacDate(String fileName){
		Date d = new Date() ;
		if(StringUtil.checkNotNull(fileName)){
			fileName = fileName.substring(0, 8) ;
			d = DateUtil.convertStringToDate(fileName, DateUtil.pattern3)  ;
		}
		
		return d ;
	}
}
