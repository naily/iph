package cn.fam1452.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.dao.pojo.Log;
import cn.fam1452.dao.pojo.NavDataYear;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

/**
 * 对几个关键数据表增、删、改操作日志进行记录
 * 
 * Class DataLogService
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jul 18, 2012 9:01:39 PM $
 */
@IocBean(name = "dataLogService")
public class DataLogService extends Base{
	
	
	public void insert(Log log){
		if(null != log && StringUtil.checkNotNull(log.getId())){
			this.dao.insert(log) ;
		}
		
	}
	
	public void insertNDY(NavDataYear n){
		if(null != n && StringUtil.checkNotNull(n.getId())){
			this.dao.insert(n) ;
		}
	}
	
	/**
	 * 把数据的年份及观测站缓存起来
	 * @param dataTable
	 * @param stationId
	 * @param stationName
	 * @param year
	 * @return
	 */
	public NavDataYear insertNDY(String dataTable , String stationId , String stationName , Date yearDate){
		Calendar c = Calendar.getInstance() ;
		c.setTime(yearDate) ;
		String year = String.valueOf( c.get(Calendar.YEAR) ) ;
		
		return this.insertNDY(dataTable, stationId, stationName, year) ;
	}
	/**
	 * 把数据的年份及观测站缓存起来
	 * @param dataTable
	 * @param stationId
	 * @param stationName
	 * @param year
	 * @return
	 */
	public NavDataYear insertNDY(String dataTable , String stationId , String stationName , String year){
		NavDataYear ndy = new NavDataYear() ;
		
		if(StringUtil.checkNotNull(dataTable) &&
				StringUtil.checkNotNull(stationId) &&
				StringUtil.checkNotNull(year) ){
			
			ndy.setId(String.valueOf(System.currentTimeMillis())) ;
			ndy.setDataTable(dataTable) ;
			ndy.setStationId(stationId) ;
			ndy.setStationName(String.valueOf(stationName)) ;
			ndy.setYear(year) ;
			
			Cnd cri = Cnd.where ("stationId", "=", stationId)
			.and("dataTable", "=", dataTable)
			.and("year", "=", year);
			
			if( 0 == this.dao.count(NavDataYear.class, cri) ){
				this.insertNDY(ndy) ;
			}
		}
		return ndy ;
	}
	
	/**
	 * 
	 * @Author Derek
	 * @Date Jul 18, 2012
	 * @param actionType 01表示数据录入，02表示数据编辑，03表示数据删除
	 * @param dataTable 操作的数据表：T_IRONOGRAM T_PARAMETER T_ REGIONMAP
	 * @param adminId 管理员ID，外键关联T_ADMINISTRATOR.loginID
	 * @return Log object
	 */
	public Log insert(String actionType , String dataTable , String adminId){
		Log log = new Log() ;
		if(StringUtil.checkNotNull(actionType) && 
				StringUtil.checkNotNull(dataTable) && 
				StringUtil.checkNotNull(adminId)){
			
			log.setId(String.valueOf(System.currentTimeMillis())) ;
			log.setLogDate(DateUtil.getCurrentDate()) ;
			log.setAdminId(adminId) ;
			log.setActionType(actionType) ;
			log.setDataTable(dataTable) ;
			
			this.insert(log) ;
		}
		
		
		return log ;
	}
	
	public Workbook exportToHSSFWorkbook(){
		
		Workbook wb = new HSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		
		Sheet sheet = wb.createSheet("数据日志");
		sheet.setColumnWidth( 0,   15*256) ;//.autoSizeColumn(0 ); // 调整第一列宽度  
        sheet.setColumnWidth( 1  , 17*256);  
        sheet.setColumnWidth( 3  , 17*256);  
        sheet.autoSizeColumn( 2 );
        
        Row row = sheet.createRow((short)1);
	    Cell cell = row.createCell(0); 
	    cell.setCellValue("ID");
	    row.createCell(1).setCellValue("表名");
	    row.createCell(2).setCellValue( createHelper.createRichTextString("操作类型"));
	    row.createCell(3).setCellValue("操作日期");
	    row.createCell(4).setCellValue("操作者");
	    
	    List<Log> list = this.logList() ;
	    if(null != list && list.size() > 0){
	    	Row rw ;
	    	for (int i = 0 ; i < list.size() ; i++) {
	    		Log log = list.get(i) ;
	    		rw = sheet.createRow(i+2);  //向后偏移2行
	    		
	    		rw.createCell(0).setCellValue(createHelper.createRichTextString(log.getId())) ;
	    		rw.createCell(1).setCellValue(log.getDataTable()) ;
	    		rw.createCell(2).setCellValue(this.convertActionType( log.getActionType() )) ;
	    		rw.createCell(3).setCellValue(DateUtil.convertDateToString(log.getLogDate(), DateUtil.pattern2) ) ;
	    		rw.createCell(4).setCellValue(log.getAdminId()) ;
	    		
	    	}
	    	
	    }else{
	    	Row r1 = sheet.createRow((short)2);
	    	r1.createCell(0).setCellValue(createHelper.createRichTextString("日志表为空")) ;
	    }
		
	    return wb ;
	}
	
	private List<Log> logList(){
		List<Log> list = this.dao.query(Log.class, null) ;
		
		return list  ;
	}
	
	private String convertActionType(String s){
		if("01".equals(s)){
			s = "录入" ;
		}else if("02".equals(s)){
			s = "编辑" ;
		}else if("03".equals(s)){
			s = "删除" ;
		}
		
		return s ;
	}
}
