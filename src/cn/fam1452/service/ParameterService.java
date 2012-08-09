package cn.fam1452.service;

import java.sql.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.Constant;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.bo.ParameteDataBo;
import cn.fam1452.action.bo.ParameterMonthDateBo;
import cn.fam1452.dao.pojo.Parameter;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

@IocBean(name = "parameterService")
public class ParameterService extends Base{
/**
 * 电离参数月报报表生成
 * 
 * */
	public List<ParameterMonthDateBo> parameterMonthReport(ParameteDataBo pdb){
		/*dao.setSqlManager(new FileSqlManager("all.sqls"));
		Sql sql =dao.sqls().create("insert.data");
		dao.execute(sql);*/
		Sql sql =Sqls.create(getQuerySQL(pdb));
		sql.setCallback(Sqls.callback.entities());
		//sql.setEntity(dao.getEntity(ParameteDataBo.class));
		sql.setEntity(dao.getEntity(ParameterMonthDateBo.class));
		this.dao.execute(sql) ;		
		List<ParameterMonthDateBo> list = sql.getList(ParameterMonthDateBo.class) ;
		return list;
	}
	/**
	 * 电离月报报表查询SQL
	 * */
   public String getQuerySQL(ParameteDataBo pdb){
	   String returnStr=null;
	   StringBuffer sb = new StringBuffer();
	   //sb.append("select days,stationID");
	   sb.append("select days");
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
	   //sb.append("select stationID,")
	   sb.append("select ").append(pdb.getParaType()).append(", datepart(dd,createdate) as days,datepart(HH,createdate) as hours from t_parameter");
	  if(StringUtil.checkNotNull(pdb.getStationID()) && StringUtil.checkNotNull(pdb.getYear()) && StringUtil.checkNotNull(pdb.getMonth())){
		  sb.append(" where datepart(YY,createdate)='").append(pdb.getYear()).append("'");
		  sb.append(" and datepart(MM,createdate)='").append(pdb.getMonth()).append("'").append(" and stationID="+pdb.getStationID());
	  }
	   
	   sb.append(") as a");
	   sb.append(" group by a.days");//,a.stationID
	    
	   returnStr= sb.toString();
	   System.out.println("sql="+returnStr);
	   sb.delete(0,sb.length()-1);
	   return returnStr;
   }
   /*
    *生成excel报表 
    *1、生成一个工作薄：HSSFWorkbook
    *2、选择某一个参数时，生成一个工作表(Sheet)；全选参数时生成多个工作表(多个Sheet)
    *3、全选月份时，一个工作表中包含1-12月份的所有参数表
    * */
   public Workbook exportToHSSFWorkbook( ParameteDataBo pdb){
	    String defaultTitle="IONOSPHERIC DATA";//报表名称
	    String year= pdb.getYear();
	    String month=pdb.getMonth();
	    String paraType=pdb.getParaType();
	    Station station = pdb.getStation();
	   
	    String[] monthAry=null;
	    String[] paraAry=null;
	    if("all".equals(month)){//全部月份
	    	monthAry=Constant.monthAry;
	    }else{
	    	monthAry = new String[1];
	    	monthAry[0]=month;
	    }
        if("all".equals(paraType)){//全部参数
        	paraAry=Constant.paraAry;
	    }else{
	    	paraAry= new String[1];
	    	paraAry[0]=paraType;
	    }
        HSSFWorkbook wb = new HSSFWorkbook();// 创建新的Excel 工作簿     	
   		CreationHelper createHelper = wb.getCreationHelper();	
   		Sheet sheet =null;//在Excel工作簿中建一工作表
   		Row  row;//定义行
   		Cell cell;//定义单元格
   	    List listData =null;//参数list
   	    ParameterMonthDateBo para =null;//参数bean
   		int rowSatrt=0;//开始行
   		//遍历参数
	    for(String paraArys:paraAry){
	    	rowSatrt=0;
			pdb.setParaType(paraArys);
			sheet = wb.createSheet(year+"-"+paraArys);
			//遍历月份
			for(String months:monthAry){
				pdb.setMonth(months);
				try{
				 listData = parameterMonthReport(pdb) ;
				 if(listData==null || listData.size()<=0){
						continue;
					}
				}catch(Exception e){
					 continue;
				}
				
				if(rowSatrt!=0){//第一次遍历从0行开始
					rowSatrt++;//向下移动一行
				}
	    		
	    		//报表名称行（顶行）合并为一列
	    		sheet.addMergedRegion(new CellRangeAddress(rowSatrt,rowSatrt,0,25));//合并单元格  CellRangeAddress(起始行,结束行,起始列,结束列)	    		
	    		row = sheet.createRow((short)rowSatrt);// 在索引0的位置创建行（最顶端的行）
	    		cell = row.createCell(0);
	    		cell.setCellValue(defaultTitle);
	    		cell.setCellStyle(setAlign(wb));
	    		//报表基础参数行（第二行）合并为四列	    		
	    		sheet.addMergedRegion(new CellRangeAddress(rowSatrt+1,rowSatrt+1,0,3));//合并单元格  CellRangeAddress(起始行,结束行,起始列,结束列)
		   		sheet.addMergedRegion(new CellRangeAddress(rowSatrt+1,rowSatrt+1,4,8));
		   		sheet.addMergedRegion(new CellRangeAddress(rowSatrt+1,rowSatrt+1,9,15));
		   		sheet.addMergedRegion(new CellRangeAddress(rowSatrt+1,rowSatrt+1,16,25));
		   		//创建一行四列,并为四列赋值
		   		row = sheet.createRow((short)rowSatrt+1);
		   		
		   		cell = row.createCell(0); 
		   		cell.setCellValue(paraArys);
		   		cell.setCellStyle(setAlign(wb));
		   		
		   		cell = row.createCell(4); 
		   		cell.setCellValue(months+"."+year);
		   		cell.setCellStyle(setAlign(wb));
		   		
		   		cell = row.createCell(9); 
		   		cell.setCellValue(station.getLocation());
		   		cell.setCellStyle(setAlign(wb));	
		   		
		   		cell = row.createCell(16); 
		   		cell.setCellValue(station.getName()+"("+station.getLongitude()+"  "+station.getLatitude()+")");
		   		cell.setCellStyle(setAlign(wb));
		   		//设置单元格宽度
		   		sheet.setColumnWidth( 0,10*256);//.autoSizeColumn(0 )
		   		for(int col=1;col<=25;col++){
		   			sheet.setColumnWidth( col  , 5*256); 
		   		}
		   		//创建报表表头（第三行）（日期及0-23小时行）
		        row = sheet.createRow((short)rowSatrt+2);
		   	    cell = row.createCell(0); 		   	    
		   	    cell.setCellValue("Day\\Month");//此处应该设为斜线，暂未解决
		   	    cell.setCellStyle(setHeadStyle(wb));
		   	    for(int col1=0;col1<=24;col1++){
		   	    	cell=row.createCell(col1+1);
		   	    	 if(col1<10){
		   	    		cell.setCellValue("0"+col1);		   	    		 
		   	    	   }else{
		   	    		cell.setCellValue(col1);
		   	    	  }
		   	    	 	cell.setCellStyle(setHeadStyle(wb));	   	   
		   	    }
		   	    //参数报表内容开始（第四行）-------------------------------------------------------------------------
		   	    				   	    
					    if(null != listData && listData.size() > 0){					    
					    	for (int i = 0 ; i < listData.size() ; i++) {
					    		para = (ParameterMonthDateBo)listData.get(i) ;
					    		row = sheet.createRow(rowSatrt+3); 				    		
					    		  for(int col2=0;col2<=25;col2++){
					    			  cell=row.createCell(col2);
						    	    	if(col2==0){
						    	    		cell.setCellValue(para.getDays());
						    	    	}else{
						    	    		cell.setCellValue(getParaValue(para,col2));
						    	    	}
						    	    	cell.setCellStyle(setContentStyle(wb));
						    	    }
					    		  rowSatrt++;
					    	}
					    	
					    }else{
					    	row = sheet.createRow((short)rowSatrt+3);
					    	row.createCell(0).setCellValue(createHelper.createRichTextString("数据为空")) ;
					    }
		   	    //参数报表内容结束--------------------------------------------------------------------------
	    		
	    	}//end for 月份  for(String months:monthAry){
	    	
	    }//end for 参数(sheet) for(String paraArys:paraAry){
			
	    return wb ;
	}
   /*
    * 返回相应列的电离参数
    * */
   public String getParaValue(ParameterMonthDateBo pdb,int idx){
	   String retValue="";
	   switch(idx){
	   case 1: retValue=pdb.getH00();
	   break;
	   case 2: retValue=pdb.getH01();
	   break;
	   case 3: retValue=pdb.getH02();
	   break;
	   case 4: retValue=pdb.getH03();
	   break;
	   case 5: retValue=pdb.getH04();
	   break;
	   case 6: retValue=pdb.getH05();
	   break;
	   case 7: retValue=pdb.getH06();
	   break;
	   case 8: retValue=pdb.getH07();
	   break;
	   case 9: retValue=pdb.getH08();
	   break;
	   case 10: retValue=pdb.getH09();
	   break;
	   case 11: retValue=pdb.getH10();
	   break;
	   case 12: retValue=pdb.getH11();
	   break;
	   case 13: retValue=pdb.getH12();
	   break;
	   case 14: retValue=pdb.getH13();
	   break;
	   case 15: retValue=pdb.getH14();
	   break;
	   case 16: retValue=pdb.getH15();
	   break;
	   case 17: retValue=pdb.getH16();
	   break;
	   case 18: retValue=pdb.getH17();
	   break;
	   case 19: retValue=pdb.getH18();
	   break;
	   case 20: retValue=pdb.getH19();
	   break;
	   case 21: retValue=pdb.getH20();
	   break;
	   case 22: retValue=pdb.getH21();
	   break;
	   case 23: retValue=pdb.getH22();
	   break;
	   case 24: retValue=pdb.getH23();
	   break;
	   default: ;
	   break;
	   }
   	return retValue;
   }
   /*对齐方式
    * */
    public HSSFCellStyle setAlign(HSSFWorkbook wb){
    	 HSSFCellStyle cellStyle=(HSSFCellStyle) wb.createCellStyle(); 
		 cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
	     cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
    	return cellStyle;
    } 
   /*设置边框与对齐方式
    * */
    public HSSFCellStyle setBorderAndAlign(HSSFWorkbook wb){
    	 HSSFCellStyle cellStyle=(HSSFCellStyle) wb.createCellStyle(); 
    	 cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);    //设置边框样式
		 cellStyle.setBorderLeft((short)1);     //左边框
		 cellStyle.setBorderRight((short)1);    //右边框
		 cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);    //顶边框
		 cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
	     cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
    	return cellStyle;
    }
    /*设置字体*/
    public HSSFFont setFont(HSSFWorkbook wb){  
    	 HSSFFont font = wb.createFont();   //设置字体的样式
         font.setFontHeightInPoints((short)8);   //字体大小
         font.setFontName("宋体");   //什么字体
         font.setItalic(false);                 //是不倾斜
         font.setStrikeout(false);         //是不是划掉
         //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   //字体加粗
    	 return font;
    }

    /*
     * 设置表头样式（有背景色）
     * */
    public HSSFCellStyle setHeadStyle(HSSFWorkbook wb){
    	HSSFCellStyle cellStyle=setBorderAndAlign(wb);
    	cellStyle.setFont(setFont(wb));//字体
    	cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    	cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
    	return cellStyle;
    }
    /*
     * 设置内容样式（无背景色）
     * */
    public HSSFCellStyle setContentStyle(HSSFWorkbook wb){
    	HSSFCellStyle cellStyle=setBorderAndAlign(wb);
    	cellStyle.setFont(setFont(wb));//字体
    	return cellStyle;
    }
    public List<Parameter> parameterDataList(Parameter params,Pages page,ParameteDataBo paraQuery){
		
		Condition cnd;
		if(StringUtil.checkNull(paraQuery.getOrderBy())){
			paraQuery.setOrderBy("stationID");//默认排序方式：观测站
		}
		if(StringUtil.checkNotNull(paraQuery.getStartDate()) && StringUtil.checkNotNull(paraQuery.getEndDate())){
			Date start = DateUtil.convertStringToSqlDate(paraQuery.getStartDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			Date end = DateUtil.convertStringToSqlDate(paraQuery.getEndDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			cnd= Cnd.where("stationID", "in", params.getIds()).and("createDate", ">=",start).and("createDate","<=",end).asc(paraQuery.getOrderBy());
		}else{//不选择日期区间时，查询所有日期的数据
		    cnd = Cnd.where("stationID", "in", params.getIds()).asc(paraQuery.getOrderBy());
		}	
		page.setLimit(Integer.parseInt(paraQuery.getPageSize()));
		List<Parameter> list = this.dao.query(Parameter.class,cnd,page.getNutzPager()) ;
		return list;
	}
    

}