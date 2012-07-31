package cn.fam1452.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.action.bo.ParameteDataBo;
import cn.fam1452.utils.StringUtil;

@IocBean(name = "parameterService")
public class ParameterService extends Base{
	
	public List parameterMonthReport(ParameteDataBo pdb){
		/*dao.setSqlManager(new FileSqlManager("all.sqls"));
		Sql sql =dao.sqls().create("insert.data");
		dao.execute(sql);*/
		Sql sql =Sqls.create(getQuerySQL(pdb));
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(ParameteDataBo.class));
		this.dao.execute(sql) ;		
		List<ParameteDataBo> list = sql.getList(ParameteDataBo.class) ;
		 return list;
	}
   public String getQuerySQL(ParameteDataBo pdb){
	   String returnStr=null;
	   StringBuffer sb = new StringBuffer();
	   sb.append("select days,stationID");
	   sb.append(",max(case a.hours when '0'  then ").append(pdb.getParaType()).append(" else ' ' end) 'h00'");
	   sb.append(",max(case a.hours when '1'  then ").append(pdb.getParaType()).append(" else ' ' end) 'h01'");
	   sb.append(",max(case a.hours when '2'  then ").append(pdb.getParaType()).append(" else ' ' end) 'h02'");
	   sb.append(",max(case a.hours when '3'  then ").append(pdb.getParaType()).append(" else ' ' end) 'h03'");
	   sb.append(",max(case a.hours when '4'  then ").append(pdb.getParaType()).append(" else ' ' end) 'h04'");
	   sb.append(",max(case a.hours when '5'  then ").append(pdb.getParaType()).append(" else ' ' end) 'h05'");
	   sb.append(",max(case a.hours when '6'  then ").append(pdb.getParaType()).append(" else ' ' end) 'h06'");
	   sb.append(",max(case a.hours when '7'  then ").append(pdb.getParaType()).append(" else ' ' end) 'h07'");
	   sb.append(",max(case a.hours when '8'  then ").append(pdb.getParaType()).append(" else ' ' end) 'h08'");
	   sb.append(",max(case a.hours when '9'  then ").append(pdb.getParaType()).append(" else ' ' end) 'h09'");
	   sb.append(",max(case a.hours when '10' then ").append(pdb.getParaType()).append(" else ' ' end) 'h10'");
	   sb.append(",max(case a.hours when '11' then ").append(pdb.getParaType()).append(" else ' ' end) 'h11'");
	   sb.append(",max(case a.hours when '12' then ").append(pdb.getParaType()).append(" else ' ' end) 'h12'");
	   sb.append(",max(case a.hours when '13' then ").append(pdb.getParaType()).append(" else ' ' end) 'h13'");
	   sb.append(",max(case a.hours when '14' then ").append(pdb.getParaType()).append(" else ' ' end) 'h14'");
	   sb.append(",max(case a.hours when '15' then ").append(pdb.getParaType()).append(" else ' ' end) 'h15'");
	   sb.append(",max(case a.hours when '16' then ").append(pdb.getParaType()).append(" else ' ' end) 'h16'");
	   sb.append(",max(case a.hours when '17' then ").append(pdb.getParaType()).append(" else ' ' end) 'h17'");
	   sb.append(",max(case a.hours when '18' then ").append(pdb.getParaType()).append(" else ' ' end) 'h18'");
	   sb.append(",max(case a.hours when '19' then ").append(pdb.getParaType()).append(" else ' ' end) 'h19'");
	   sb.append(",max(case a.hours when '20' then ").append(pdb.getParaType()).append(" else ' ' end) 'h20'");
	   sb.append(",max(case a.hours when '21' then ").append(pdb.getParaType()).append(" else ' ' end) 'h21'");
	   sb.append(",max(case a.hours when '22' then ").append(pdb.getParaType()).append(" else ' ' end) 'h22'");
	   sb.append(",max(case a.hours when '23' then ").append(pdb.getParaType()).append(" else ' ' end) 'h23'");
	   sb.append(" from (");
	   sb.append("select stationID,").append(pdb.getParaType()).append(", datepart(dd,createdate) as days,datepart(HH,createdate) as hours from t_parameter");
	  if(StringUtil.checkNotNull(pdb.getStationID()) && StringUtil.checkNotNull(pdb.getYear()) && StringUtil.checkNotNull(pdb.getMonth())){
		  sb.append(" where datepart(YY,createdate)='").append(pdb.getYear()).append("'");
		  sb.append(" and datepart(MM,createdate)='").append(pdb.getMonth()).append("'").append(" and stationID="+pdb.getStationID());
	  }
	   
	   sb.append(") as a");
	   sb.append(" group by a.days,a.stationID");
	    
	   returnStr= sb.toString();
	   //System.out.println("sql="+returnStr);
	   sb.delete(0,sb.length()-1);
	   return returnStr;
   }
   public Workbook exportToHSSFWorkbook( ParameteDataBo pdb){
	   
		Workbook wb = new HSSFWorkbook();
		
		 HSSFCellStyle cellStyle=(HSSFCellStyle) wb.createCellStyle();     //在工作薄的基础上建立一个样式
        // cellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);    //设置边框样式
		 cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
		 cellStyle.setBorderLeft((short)1);     //左边框
		 cellStyle.setBorderRight((short)1);    //右边框
		 cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);    //顶边框
		 cellStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);    //填充的背景颜色
         
       

		
		CreationHelper createHelper = wb.getCreationHelper();
		/*sheet.setColumnWidth( 0,   5*256) ;//.autoSizeColumn(0 ); // 调整第一列宽度  
		sheet.setColumnWidth( 1  , 5*256);  
		sheet.setColumnWidth( 2  , 5*256);  */
		Sheet sheet = wb.createSheet("2012-07-foF2");
		sheet.setColumnWidth( 0,   10*256);
		for(int col=1;col<25;col++){
			sheet.setColumnWidth( col  , 5*256); 
		}	      
        Row row = sheet.createRow((short)1);
	    Cell cellDays = row.createCell(0); 
	    cellDays.setCellValue("days\\month");
	    Cell cellHead;
	    for(int col1=0;col1<=24;col1++){
	    	cellHead=row.createCell(col1+1);
	    	 if(col1<10){
	    		 cellHead.setCellValue("0"+col1);
	    		 
	    	 }else{
	    		 cellHead.setCellValue(col1);
	    	 }
	    	 cellHead.setCellStyle(cellStyle);
	    }
	    cellDays.setCellStyle(cellStyle);
	    List list = parameterMonthReport(pdb) ;
	    if(null != list && list.size() > 0){
	    	Row rw ;
	    	for (int i = 0 ; i < list.size() ; i++) {
	    		ParameteDataBo para = (ParameteDataBo)list.get(i) ;
	    		rw = sheet.createRow(i+2);  //向后偏移2行
	    		Cell cellData;
	    		  for(int col2=0;col2<=25;col2++){
	    			  cellData=rw.createCell(col2);
		    	    	if(col2==0){
		    	    		cellData.setCellValue(para.getDays());
		    	    	}else{
		    	    		cellData.setCellValue(para.getH00());
		    	    	}
		    	    	cellData.setCellStyle(cellStyle);
		    	    }
	    		
	    	}
	    	
	    }else{
	    	Row r1 = sheet.createRow((short)2);
	    	r1.createCell(0).setCellValue(createHelper.createRichTextString("数据为空")) ;
	    }
		
	    return wb ;
	}
}
