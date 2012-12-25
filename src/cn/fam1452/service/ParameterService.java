package cn.fam1452.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.Constant;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.bo.ParameteDataBo;
import cn.fam1452.action.bo.ParameterMonthDateBo;
import cn.fam1452.dao.pojo.Log;
import cn.fam1452.dao.pojo.Parameter;
import cn.fam1452.dao.pojo.ProtectDate;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.QuartileUtil;
import cn.fam1452.utils.StringUtil;
/**
 * 电离参数相关操作
 * 
 * Class ParameterService
 *
 *
 */

@IocBean(name = "parameterService")
public class ParameterService extends Base{
		
	@Inject("refer:dataVisitService")
	private DataVisitService dataVisitService;
	
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
	   sb.append("select d.days,b.* from t_days  d left join(");// 2012-08-29 add by gls for show 1-31
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
	  // sb.append("select ").append(pdb.getParaType()).append(", datepart(dd,createdate) as days,datepart(HH,createdate) as hours from t_parameter");
	   sb.append("select ").append(pdb.getParaType()).append(", datepart(dd,createdate) as days,datepart(HH,createdate) as hours from ").append(pdb.getStationID());
	  if(StringUtil.checkNotNull(pdb.getStationID()) && StringUtil.checkNotNull(pdb.getYear()) && StringUtil.checkNotNull(pdb.getMonth())){
		  sb.append(" where datepart(YY,createdate)='").append(pdb.getYear()).append("'");
		  //sb.append(" and datepart(MM,createdate)='").append(pdb.getMonth()).append("'").append(" and stationID='"+pdb.getStationID()).append("' ");
		  sb.append(" and datepart(MM,createdate)='").append(pdb.getMonth()).append("' ");
	  }
	   
	   sb.append(") as a");
	   sb.append(" group by a.days ");//,a.stationID
	   
	   sb.append(" ) as b on d.days=b.days order by CAST(d.days AS int) ASC");// add by 2012-08-29
	   returnStr= sb.toString();
	   //System.out.println("sql="+returnStr);
	   sb.delete(0,sb.length()-1);
	   return returnStr;
   }
   /*
    *生成excel报表 
    *1、生成一个工作薄：HSSFWorkbook
    *2、选择某一个参数时，生成一个工作表(Sheet)；全选参数时生成多个工作表(多个Sheet)
    *3、全选月份时，一个工作表中包含1-12月份的所有参数表
    * */
   @SuppressWarnings("unchecked")
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
   		
   		
   		CellStyle styleAlign = setAlign(wb); 
   		CellStyle styleHead =setHeadStyle(wb);
   		CellStyle styleContent =setContentStyle(wb);
   		
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
				   /*合并四分位数*/
				    QuartileUtil quartUtil = new QuartileUtil();
					String[] filterFiled={"days"};//过滤非数据字段
					try {
						listData  =	quartUtil.monthIonosphericDate(listData, filterFiled,filterFiled[0]);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
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
	    		sheet.addMergedRegion(new CellRangeAddress(rowSatrt,rowSatrt,0,24));//合并单元格  CellRangeAddress(起始行,结束行,起始列,结束列)	    		
	    		row = sheet.createRow((short)rowSatrt);// 在索引0的位置创建行（最顶端的行）
	    		cell = row.createCell(0);
	    		cell.setCellValue(defaultTitle);
	    		//cell.setCellStyle(setAlign(wb));
	    		cell.setCellStyle(styleAlign);
	    		//报表基础参数行（第二行）合并为四列	    		
	    		sheet.addMergedRegion(new CellRangeAddress(rowSatrt+1,rowSatrt+1,0,3));//合并单元格  CellRangeAddress(起始行,结束行,起始列,结束列)
		   		sheet.addMergedRegion(new CellRangeAddress(rowSatrt+1,rowSatrt+1,4,8));
		   		sheet.addMergedRegion(new CellRangeAddress(rowSatrt+1,rowSatrt+1,9,15));
		   		sheet.addMergedRegion(new CellRangeAddress(rowSatrt+1,rowSatrt+1,16,24));
		   		//创建一行四列,并为四列赋值
		   		row = sheet.createRow((short)rowSatrt+1);
		   		
		   		cell = row.createCell(0); 
		   		cell.setCellValue(paraArys+QuartileUtil.getUnit(paraArys));
		   		//cell.setCellStyle(setAlign(wb));
		   		cell.setCellStyle(styleAlign);
		   		
		   		cell = row.createCell(4); 
		   		cell.setCellValue(DateUtil.getMonthEn(Integer.parseInt(months))+"  "+year);
		   		//cell.setCellStyle(setAlign(wb));
		   		cell.setCellStyle(styleAlign);
		   		
		   		cell = row.createCell(9); 
		   		cell.setCellValue(station.getTimeZone());//timeZone//.getLocation()
		   		cell.setCellStyle(styleAlign);	
		   		
		   		cell = row.createCell(16); 
		   		cell.setCellValue(station.getName()+"("+station.getLongitude()+"  "+station.getLatitude()+")");
		   		cell.setCellStyle(styleAlign);
		   		//设置单元格宽度
		   		sheet.setColumnWidth( 0,10*256);//.autoSizeColumn(0 )
		   		//for(int col=1;col<=25;col++){
		   		for(int col=1;col<=24;col++){
		   			sheet.setColumnWidth( col  , 5*256); 
		   		}
		   		//创建报表表头（第三行）（日期及0-23小时行）
		        row = sheet.createRow((short)rowSatrt+2);
		   	    cell = row.createCell(0); 		   	    
		   	    cell.setCellValue("Day\\Hour");//此处应该设为斜线，暂未解决
		   	    cell.setCellStyle(styleHead);
		   	    //for(int col1=0;col1<=24;col1++){
		   	    for(int col1=0;col1<24;col1++){
		   	    	cell=row.createCell(col1+1);
		   	    	 if(col1<10){
		   	    		cell.setCellValue("0"+col1);		   	    		 
		   	    	   }else{
		   	    		cell.setCellValue(col1);
		   	    	  }
		   	    	 	cell.setCellStyle(styleHead);	   	   
		   	    }
		   	    //参数报表内容开始（第四行）-------------------------------------------------------------------------
		   	    				   	    
					    if(null != listData && listData.size() > 0){					    
					    	for (int i = 0 ; i < listData.size() ; i++) {
					    		para = (ParameterMonthDateBo)listData.get(i) ;
					    		row = sheet.createRow(rowSatrt+3); 				    		
					    		  for(int col2=0;col2<25;col2++){
					    			  cell=row.createCell(col2);
						    	    	if(col2==0){
						    	    		cell.setCellValue(para.getDays());
						    	    	}else{
						    	    		cell.setCellValue(getParaValue(para,col2));
						    	    	}
						    	    	cell.setCellStyle(styleContent);
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
    /**
     * 电离参数查询
     * */
    public List<Parameter> parameterDataList(Parameter params,Pages page,ParameteDataBo paraQuery){
   	
    	/*Condition cnd;
		String[] stationIDS =null;
		if(StringUtil.checkNull(paraQuery.getOrderBy())){
			paraQuery.setOrderBy("stationID");//默认排序方式：观测站
		}
		if(StringUtil.checkNotNull(params.getIds())){
			stationIDS= params.getIds().split(",");
		}
		if(StringUtil.checkNotNull(paraQuery.getStartDate()) && StringUtil.checkNotNull(paraQuery.getEndDate())){
			Date start = DateUtil.convertStringToSqlDate(paraQuery.getStartDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			Date end = DateUtil.convertStringToSqlDate(paraQuery.getEndDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			cnd= Cnd.where("stationID", "in", stationIDS).and("createDate", ">=",start).and("createDate","<=",end).asc(paraQuery.getOrderBy());
		}else{//不选择日期区间时，查询所有日期的数据
		    cnd = Cnd.where("stationID", "in", stationIDS).asc(paraQuery.getOrderBy());
		}*/	
    	Condition cnd =getParamenterCnd(params,paraQuery);
		log.info(cnd.toString());
		if(null!=paraQuery && StringUtil.checkNotNull(paraQuery.getPageSize()))
		page.setLimit(Integer.parseInt(paraQuery.getPageSize()));
		String tableName =params.getIds();
		List<Record> lists = dao.query(tableName, cnd, page.getNutzPager()); 
		List<Parameter> list =  new ArrayList<Parameter>();
		for(Record r:lists){			
			Parameter paramss =record2Object(r);
			paramss.setStationID(tableName);
			//para = sp.toPojo(Parameter.class) ;
			list.add(paramss);
		}
		
		//List<Parameter> list = this.dao.query(Parameter.class,cnd,page.getNutzPager()) ;
		
		
		//log.info("page.size="+page.getLimit());
		//log.info("page.start="+page.getStart());
		//log.info("list.size="+list.size());
		return list;
	}
    private final String[] paField = {"foF2","fxF2","fxl","hlF2","foF1","hlF1","hlF","hpF","hpF2","foE","hlE","foEs","hlEs","fbEs","es"} ;
    private Parameter record2Object(Record rd){
		Parameter p = new Parameter() ;
		p.setParameterID((Long)rd.get("parameterID")) ;
		p.setCreateDate((Date)rd.get("createDate")) ;
		p.setFmin(rd.getString("Fmin")) ;
		p.setM3000F(rd.getString("M3000F")) ;
		p.setM3000F1(rd.getString("M3000F1")) ;
		p.setM3000F2(rd.getString("M3000F2")) ;
		p.setM1500F2(rd.getString("M1500F2")) ;
		p.setMUF3000F1(rd.getString("MUF3000F1")) ;
		p.setMUF3000F2(rd.getString("MUF3000F2")) ;
		for (String fie : paField) {
			try {
				BeanUtils.setProperty(p, fie, rd.get(fie)) ;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p ;
	}
    public Condition getParamenterCnd(Parameter params,ParameteDataBo paraQuery){
    	Condition cnd;
		String[] stationIDS =null;
		if(StringUtil.checkNull(paraQuery.getOrderBy())){
			//paraQuery.setOrderBy("stationID");//默认排序方式：观测站
			paraQuery.setOrderBy("createDate");//默认排序方式：时间
		}
		if(StringUtil.checkNotNull(params.getIds())){
			stationIDS= params.getIds().split(",");
		}
		String start = paraQuery.getStartDate();
		String end = paraQuery.getEndDate();
		if(StringUtil.checkNotNull(start) && StringUtil.checkNotNull(end)){
			//Date start = DateUtil.convertStringToSqlDate(paraQuery.getStartDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			//Date end = DateUtil.convertStringToSqlDate(paraQuery.getEndDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			
			//cnd= Cnd.where("stationID", "in", stationIDS).and("createDate", ">=",start).and("createDate","<=",end).asc("createDate").asc(paraQuery.getOrderBy());
			cnd= Cnd.where("createDate", ">=",start).and("createDate","<=",end).asc(paraQuery.getOrderBy());
		}else{//不选择日期区间时，查询所有日期的数据
		    //cnd = Cnd.where("createDate", "<>",null).asc(paraQuery.getOrderBy());
		    cnd =Cnd.orderBy().asc(paraQuery.getOrderBy());
		}	
    	return cnd;
    }
/*    public Condition getParamenterCnd(Parameter params,ParameteDataBo paraQuery){
    	Condition cnd;
		String[] stationIDS =null;
		if(StringUtil.checkNull(paraQuery.getOrderBy())){
			paraQuery.setOrderBy("stationID");//默认排序方式：观测站
		}
		if(StringUtil.checkNotNull(params.getIds())){
			stationIDS= params.getIds().split(",");
		}
		if(StringUtil.checkNotNull(paraQuery.getStartDate()) && StringUtil.checkNotNull(paraQuery.getEndDate())){
			Date start = DateUtil.convertStringToSqlDate(paraQuery.getStartDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			Date end = DateUtil.convertStringToSqlDate(paraQuery.getEndDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			cnd= Cnd.where("stationID", "in", stationIDS).and("createDate", ">=",start).and("createDate","<=",end).asc("createDate").asc(paraQuery.getOrderBy());
		}else{//不选择日期区间时，查询所有日期的数据
		    cnd = Cnd.where("stationID", "in", stationIDS).asc(paraQuery.getOrderBy());
		}	
    	return cnd;
    }*/
    /**
     * 根据表名查询改表的数据保护期（可能包含多个时间段）
     * */
    public List<ProtectDate> getProtectDate(String dataTable){
    	List<ProtectDate> list=null;
    	list = this.dao.query(ProtectDate.class, Cnd.where("dataTable","=",dataTable));
    	return list;
    }
    /**
     * 根据表名查询改表的数据保护期（可能包含多个时间段）
     * */
    public ProtectDate getProtectDateByTableName(String dataTable){
    	ProtectDate prodata=null;
    	//prodata = this.dao.fetch(ProtectDate.class, Cnd.where("dataTable","=",dataTable).desc("id"));
    	prodata = this.dao.fetch(ProtectDate.class, Cnd.where("dataStation","=",dataTable).desc("id"));
    	return prodata;
    }
    /**
     * 查询某个表是否设置了保护期
     * */
    public boolean isProtectDate(String dataTable){
    	List<ProtectDate> list=getProtectDate(dataTable);
    	if(null!=list && list.size()>0)
    		return true;
    	return false;
    }
    	/**
    	 * 有保护期的数据查询
    	 * 1、若保护期区间是查询时间段的子集则只显示50条数据
    	 * 2、若保护期区间与查询时间段有交集，则进行数据拼装（保护期内的前50条记录+保护期外的记录）
    	 * */
     public List top50ParameterDataListNew(Object obj,Parameter params,String tableName,ParameteDataBo paraQuery){	
    	//Sql sql =Sqls.create(getQueryParameterSQL(params,paraQuery));
    	Sql sql =Sqls.create(getProtectDateSql(params.getIds(),tableName,paraQuery));
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(Parameter.class));;
		sql.setEntity(dao.getEntity(obj.getClass()));
		this.dao.execute(sql) ;		
		//List<Parameter> list = sql.getList(Parameter.class) ;  
		List list = sql.getList(obj.getClass()) ;  
		return list;
		
	}
   	/**
 	 * 有保护期的数据查询
 	 * 1、若保护期区间是查询时间段的子集则只显示50条数据
 	 * 2、若保护期区间与查询时间段有交集，则进行数据拼装（保护期内的前50条记录+保护期外的记录）
 	 * */
  public List<Parameter> top50ParameterDataList(Parameter params,String tableName,ParameteDataBo paraQuery){	
 	//Sql sql =Sqls.create(getQueryParameterSQL(params,paraQuery));
	  	Sql sql =Sqls.create(getProtectDateSql(params.getIds(),tableName,paraQuery));
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(Parameter.class));
		this.dao.execute(sql) ;		
		List<Parameter> list = sql.getList(Parameter.class) ;  
		return list;
		
	}
     /**
      * 保护期判断
      * isProtectDateOpen(数据表名，查询开始日期，查询结束日期)
      * 1、根据数据表名查询数据是否有保护期
      * 2、通过当前日期与开放日期的比较，判断保护期是否有效
      * 3、若保护期无效或没有保护期则正常查询并分页显示数据
      * 4、若保护期有效且与页面查询日期区间有重叠则按保护期规则显示数据，如只显示前50条数据等，若查询时间段与保护期没有重叠亦正常查询并显示数据
      * 5、返回值true表示保护期已开放，返回值false表示当前数据有保护期
      * 
      * 
      * 6、注：最新修改（2012-10-18 16：30）---只取某种受保护数据的最新的保护期
      * */
     public boolean isProtectDateOpen(String dataTable,String startDate,String endDate){
     	/*List<ProtectDate> list=getProtectDate(dataTable);
     	
     	if(null!=list && list.size()>0){
     		Date today = (Date) DateUtil.getCurrentDate();//当前日期
     		if(StringUtil.checkNull(startDate) && StringUtil.checkNull(endDate)){//都为空时，表示查询所有数据
     			for(ProtectDate protect:list){
         			if(protect.getPublicDate().getTime()>today.getTime()){//查询所有数据时，只要有保护期则查询受限       			
         				return false;
         			}
         		}
	     	}else{
	     		Date queryStart = (Date) DateUtil.convertStringToDate(startDate, "yyyy-MM-dd");
	     		Date queryEnd  =  (Date) DateUtil.convertStringToDate(endDate, "yyyy-MM-dd");
	     		for(ProtectDate protect:list){
	     			if(protect.getPublicDate().getTime()>today.getTime()){//当前的保护期未开放
	     				if(dateCompare(queryStart,protect.getDataSDate(),protect.getDataEDate()) || dateCompare(queryEnd,protect.getDataSDate(),protect.getDataEDate())){//查询区间与保护期有重叠
	     					return false;
	     				}
	     			}
	     		}	
	     	}// end 查询区间
     	}*/
    	
    	 ProtectDate prodata= getProtectDateByTableName(dataTable);	
    	 if(null!=prodata && null!=prodata.getId()){
    		  Date today = (Date) DateUtil.getCurrentDate();//当前日期
    		  Date queryStart = null;
    		  Date queryEnd  = null;
    		  if(StringUtil.checkNull(startDate) || StringUtil.checkNull(endDate)){
    			  ParameteDataBo pdb =getMinAndMaxDate(dataTable);
    			  queryStart= (Date) DateUtil.convertStringToDate(pdb.getStartDate(), "yyyy-MM-dd");
    			  queryEnd  = (Date) DateUtil.convertStringToDate(pdb.getEndDate(), "yyyy-MM-dd");
     		  }else{
     			 queryStart = (Date) DateUtil.convertStringToDate(startDate, "yyyy-MM-dd");
  			     queryEnd  =  (Date) DateUtil.convertStringToDate(endDate, "yyyy-MM-dd");
     		  }   	     	
        		 if(prodata.getPublicDate().getTime()>today.getTime()){//当前的保护期未开放
      				if(dateCompare(queryStart,prodata.getDataSDate(),prodata.getDataEDate()) 
      					|| dateCompare(queryEnd,prodata.getDataSDate(),prodata.getDataEDate())
      				    || dateCompare(prodata.getDataSDate(),queryStart,queryEnd)	
      				    || dateCompare(prodata.getDataEDate(),queryStart,queryEnd)	
      				){//查询区间与保护期有重叠
      					return false;
      				}
        		 }
    		 
    	 }   	
     	return true;
     }
     /**
      * inputDate  待比较的日期
      * startDate  比较区间下限
      * endDate    比较区间上限
      * */
     public boolean dateCompare(Date date,Date startDate,Date endDate){
    	 if(date.getTime()>=startDate.getTime() && date.getTime()<=endDate.getTime()){
    		 return true; 
    	 }else{
    		 return false; 
    	 }
    	
     }

     
     /**
    	 * 查询保护期内的电离层参数（前50条数据）
    	 * */
     public String getQueryParameterSQL(Parameter params,ParameteDataBo paraQuery){
    	 int shownums =Constant.PROTECTDATA_SHOWNUM;//默认显示记录数
    	 String[] stationIDS =null;//观测站数组
  		 if(StringUtil.checkNull(paraQuery.getOrderBy())){
  			paraQuery.setOrderBy("stationID");//默认排序方式：观测站
  		 }
  		 if(StringUtil.checkNotNull(params.getIds())){
  			stationIDS= params.getIds().split(",");
  		 }
  		 String queryStationArry="";
  		 for(String s:stationIDS){
  			if(!"".equals(queryStationArry)){
  				queryStationArry+=",";
  			 }
  			 queryStationArry+="\'"+s+"\'";
  		 }
  		// log.info("stationIDS=="+stationIDS.toString());
    	 StringBuffer sb = new StringBuffer("select top "); 
    	 sb.append(shownums);
    	 sb.append(" * from T_PARAMETER");
    	 sb.append(" where stationID in (").append(queryStationArry).append(")");//params.getIds()
    	 if(StringUtil.checkNotNull(paraQuery.getStartDate()) && StringUtil.checkNotNull(paraQuery.getEndDate()) && StringUtil.checkNull(paraQuery.getSelectAllDate())){//前台查询日期区间
  			Date start = DateUtil.convertStringToSqlDate(paraQuery.getStartDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
  			Date end = DateUtil.convertStringToSqlDate(paraQuery.getEndDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
  			 sb.append(" and createDate >='").append(start).append("' and createDate <='").append(end).append("'");
  		 }
    	/* List<ProtectDate> protectDateList = getProtectDate("T_PARAMETER");
    	 if(null!=protectDateList && protectDateList.size()>0){
    		 sb.append(" and ( ");
//    		 for(ProtectDate proDate:protectDateList){
    		 for(int i=0;i<protectDateList.size();i++){
    			 ProtectDate proDate =(ProtectDate)protectDateList.get(i);
    			 if(null!=proDate.getDataSDate() && null!=proDate.getDataEDate()){
    				
    				if(i>0)sb.append(" or ");
    				sb.append(" createDate between '").append(DateUtil.convertDateToString(proDate.getDataSDate())).append("' and '").append(DateUtil.convertDateToString(proDate.getDataEDate())).append("' "); 
    			 }
    		 }
    		 sb.append(" ) ");
    	 }*/
    	 sb.append(" order by ").append(paraQuery.getOrderBy());
         //log.info(sb.toString());
    	 return sb.toString();
     }
     
     /**
      *电离参数生成excel 
      *
      * */
     @SuppressWarnings("unchecked")
  public Workbook exportParaDataToHSSFWorkbook(List<Parameter> list){
  	   
  	    String[] paraAry=Constant.paraAry;;
  	   
            HSSFWorkbook wb = new HSSFWorkbook();// 创建新的Excel 工作簿     	
     		CreationHelper createHelper = wb.getCreationHelper();	
     		Sheet sheet = wb.createSheet("ParameterData");//在Excel工作簿中建一工作表
     		Row  row;//定义行
     		Cell cell;//定义单元格
     		int rowSatrt=0;//开始行

    		sheet.setColumnWidth( 0,   15*256) ;//.autoSizeColumn(0 ); // 调整第一列宽度  
            sheet.setColumnWidth( 1  , 17*256);  
            sheet.setColumnWidth( 3  , 17*256);  
            sheet.autoSizeColumn( 2 );
     		row = sheet.createRow((short)rowSatrt);
     		cell=row.createCell(0);
     		//遍历参数
    	    cell.setCellValue("所属观测站");
    	    row.createCell(1).setCellValue("观测日期");
    	    int paraDataCellNum=2;
    	    for(String paras:paraAry){
    	    	row.createCell(paraDataCellNum).setCellValue(paras);
    	    	paraDataCellNum++;
    	    }
    	
    	   
    	    if(null != list && list.size() > 0){
    	    	Row rw ;
    	    	for (int i = 0 ; i < list.size() ; i++) {
    	    		Parameter parat = list.get(i) ;
    	    		rw = sheet.createRow(i+2);  //向后偏移2行
    	    		rw.createCell(0).setCellValue(parat.getStation().getName()) ;
    	    		rw.createCell(1).setCellValue(DateUtil.convertDateToString(parat.getCreateDate()  , "yyyyMMDDHH")) ;//DateUtil.convertDateToString(g.getLogDate()  , DateUtil.pattern2)
    	    		rw.createCell(2).setCellValue(parat.getFoF2()) ;
    	    		rw.createCell(3).setCellValue(parat.getHlF2()) ;
    	    		rw.createCell(4).setCellValue(parat.getFoF1()) ;
    	    		rw.createCell(5).setCellValue(parat.getHlF1()) ;
    	    		rw.createCell(6).setCellValue(parat.getHlF()) ;
    	    		rw.createCell(7).setCellValue(parat.getHpF()) ;
    	    		rw.createCell(8).setCellValue(parat.getFoE()) ;
    	    		rw.createCell(9).setCellValue(parat.getHlE()) ;
    	    		rw.createCell(10).setCellValue(parat.getFoEs()) ;
    	    		rw.createCell(11).setCellValue(parat.getFmin()) ;
    	    		rw.createCell(12).setCellValue(parat.getM3000F2()) ;
    	    		rw.createCell(13).setCellValue(parat.getM1500F2()) ;
    	    		rw.createCell(14).setCellValue(parat.getM3000F1()) ;
    	    		rw.createCell(15).setCellValue(parat.getM3000F()) ;
    	    		rw.createCell(16).setCellValue(parat.getHlEs()) ;
    	    		rw.createCell(17).setCellValue(parat.getFbEs());    	    		
    	    		
    	    	}
    	    	
    	    }else{
    	    	Row r1 = sheet.createRow((short)2);
    	    	r1.createCell(0).setCellValue(createHelper.createRichTextString("查询数据为空")) ;
    	    }
    		
    	    return wb ;
  	}
     /**
      * 查询保护期的情况
      *a) 参数定义
      *   保护期开始时间：B1
      *   保护期结束时间：B2
      *   查询日期开始：Q1
      *   查询日期结束：Q2
      *b) 返回值说明：
      * 0-无保护期\保护期失效\两区间无交集,1-查询日期区间是保护期的子集,2-保护期区间是查询区间的子集,3-保护期区间与查询区间有交集(前部分交集),4-保护期区间与查询区间有交集(后部分交集)
      *c) 时间值分布情况
      *  1(B1<Q1<Q2<B2)
      *  2(Q1<B1<B2<Q2)
      *  3(Q1<B1<Q2<B2)
      *  4(B1<Q1<B2<Q2)
      * 
      * */
     public int getProtectDateType(String tableName,ParameteDataBo paraQuery){
    	 ProtectDate prodata= getProtectDateByTableName(tableName);//保护期
    	 int retValue=0;
    	 if(null!=prodata && null!=prodata.getId()){
    		 Date today = (Date) DateUtil.getCurrentDate();//当前日期
    		 if(StringUtil.checkNull(paraQuery.getStartDate()) || StringUtil.checkNull(paraQuery.getEndDate()) ){
    			 paraQuery= getMinAndMaxDate(tableName);
    			 
    		 }
    		 Date Q1  =  (Date) DateUtil.convertStringToDate(paraQuery.getStartDate(), "yyyy-MM-dd");
	     	 Date Q2  =  (Date) DateUtil.convertStringToDate(paraQuery.getEndDate(), "yyyy-MM-dd");
	     	 Date B1  =  prodata.getDataSDate();
	     	 Date B2  =  prodata.getDataEDate();
    		 if(prodata.getPublicDate().getTime()>today.getTime()){//当前的保护期未开放
  				if(dateCompare(Q1,B1,B2) && dateCompare(Q2,B1,B2)){
  					retValue=1;
  				}
  				if(dateCompare(	B1,Q1,Q2) && dateCompare(B2,Q1,Q2)){
  					retValue=2;
  				}
  				if(dateCompare(	B1,Q1,Q2) && dateCompare(Q2,B1,B2)){
  					retValue=3;
  				}
  				if(dateCompare(Q1,B1,B2) && dateCompare(B2,Q1,Q2)){
  					retValue=4;
  				}
  			}
    	 }   	
    	 return retValue;
     }
     /**
      * 获取保护期查询sql(观测站id字符串，查询表名，查询bean)
      * 
      * */
     public String getProtectDateSql(String stationIdAry, String tableName,ParameteDataBo paraQuery){
    	// public String getProtectDateSql(Parameter params,ParameteDataBo paraQuery){
    	// String tableName="T_PARAMETER";
    	 ProtectDate prodata= getProtectDateByTableName(tableName);//保护期
    	 int shownums =Constant.PROTECTDATA_SHOWNUM;//默认显示记录数
    	 String[] stationIDS =null;//观测站数组
    	 StringBuffer sb = new StringBuffer(""); 
  		 if(StringUtil.checkNull(paraQuery.getOrderBy())){
  			//paraQuery.setOrderBy("stationID");//默认排序方式：观测站
  			paraQuery.setOrderBy("createDate");//默认排序方式：观测站
  		 }
  		 /*if(StringUtil.checkNotNull(params.getIds())){
  			stationIDS= params.getIds().split(",");
  		 }*/
  		if(StringUtil.checkNotNull(stationIdAry)){
			stationIDS= stationIdAry.split(",");
		 }
  		 String queryStationArry="";
  		 for(String s:stationIDS){
  			if(!"".equals(queryStationArry)){
  				queryStationArry+=",";
  			 }
  			 queryStationArry+="\'"+s+"\'";
  		 }    	 
         //log.info(sb.toString());   	    	   	 
    	 if(null!=prodata && null!=prodata.getId()){   	
    	
	    	// if(StringUtil.checkNotNull(paraQuery.getStartDate()) && StringUtil.checkNotNull(paraQuery.getEndDate()) && StringUtil.checkNull(paraQuery.getSelectAllDate())){//前台查询日期区间
	    		// Date dateQ1 = DateUtil.convertStringToSqlDate(paraQuery.getStartDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
		  		// Date dateQ2 = DateUtil.convertStringToSqlDate(paraQuery.getEndDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
		  		 Date B1  =  prodata.getDataSDate();
		     	 Date B2  =  prodata.getDataEDate(); 		     	 
		    	
		    	 String dateQ1 = null;
		    	 String dateQ2 = null;
		    	 if(StringUtil.checkNull(paraQuery.getStartDate()) || StringUtil.checkNull(paraQuery.getEndDate()) ){
	    			 ParameteDataBo pdb = getMinAndMaxDate(tableName);
	    			// paraQuery.setStartDate(pdb.getStartDate());
	    			 //paraQuery.setEndDate(pdb.getEndDate());
	    			 if(null!=pdb){
	    				  dateQ1 = pdb.getStartDate();
				     	  dateQ2 = pdb.getEndDate();
	    			 }
	    			 
	    		 }else{
	    			  dateQ1 = paraQuery.getStartDate()+" 00:00:00";
			     	  dateQ2 = paraQuery.getEndDate()+" 23:59:00";
	    		 }
		     	 String dateB1 = DateUtil.convertDateToString(B1, "yyyy-MM-dd HH:mm:ss");
		     	 String dateB2 = DateUtil.convertDateToString(B2, "yyyy-MM-dd HH:mm:ss");		     	
	    		 if(getProtectDateType(tableName,paraQuery)>=1){
	    			 sb.append("select top "); 
	    	    	 sb.append(shownums);
	    	    	 sb.append(" * from "+tableName);
	    	    	 sb.append(" where stationID in (").append(queryStationArry).append(")");//params.getIds()	    	    	
	    			 sb.append(" and createDate >='").append(dateB1).append("' and createDate <='").append(dateB2).append("'");
	    			 //sb.append(" where createDate >='").append(dateB1).append("' and createDate <='").append(dateB2).append("'");
		     	  if(getProtectDateType(tableName,paraQuery)>=2){
	    			 sb.append(" union ");
	    	    	 sb.append("  select * from "+tableName);// T_PARAMETER");
	    	    	 sb.append("  where stationID in (").append(queryStationArry).append(")");//params.getIds()
	    	    	 //sb.append("  where parameterID>0 ");//params.getIds()
	    			 if(getProtectDateType(tableName,paraQuery)==2){			    	
				    	 sb.append(" and createDate >='").append(dateQ1).append("' and createDate <'").append(dateB1).append("'");
				    	 sb.append(" and createDate >='").append(dateB2).append("' and createDate <'").append(dateQ2).append("'");
			     	} 
	    			 if(getProtectDateType(tableName,paraQuery)==3){			    	
				    	 sb.append(" and createDate >='").append(dateQ1).append("' and createDate <'").append(dateB1).append("'");				    	 
			     	} 
	    			 if(getProtectDateType(tableName,paraQuery)==4){			    					    	
				    	 sb.append(" and createDate >='").append(dateB2).append("' and createDate <'").append(dateQ2).append("'");
			     	} 
	    		 } 			
	  		  }	
	    	 }
	    	 //sb.append(" order by ").append(paraQuery.getOrderBy());	     	
    	 //} 
    	 log.info(sb.toString());
    	 return sb.toString();
     }
     /**
      * 获取查询数据表的日期最大值与最小值
      * 
      * */
     	public ParameteDataBo getMinAndMaxDate(String tabelName){
     		//public List<ParameterMonthDateBo> getMinAndMaxDate(String tabelName){
     		Sql sql =Sqls.create("SELECT min(createDate)  startDate,max(createDate) endDate FROM "+tabelName);
     		sql.setCallback(Sqls.callback.entities());
     		//sql.setEntity(dao.getEntity(ParameteDataBo.class));
     		sql.setEntity(dao.getEntity(ParameteDataBo.class));
     		this.dao.execute(sql) ;		
     		//List<ParameterMonthDateBo> list = sql.getList(ParameterMonthDateBo.class) ;
     		//return list;
     		ParameteDataBo pmb = sql.getObject(ParameteDataBo.class);
     		//log.info(pmb.getStartDate());
     		//log.info(pmb.getEndDate());
     		return pmb;
     	}
     	
     	/**
     	 * 电离层曲线图按时间段查询（）
     	 * 修改不符合条件的值：将空格，字母等转化为数字，空=0，含字母的去掉字母
     	 * */
     		public List<Parameter> parameterCharByQujian(ParameteDataBo pdb){
     			Sql sql =Sqls.create(queryParaDataByQujian(pdb));
     			sql.setCallback(Sqls.callback.entities());
     			sql.setEntity(dao.getEntity(Parameter.class));
     			this.dao.execute(sql) ;		
     			List<Parameter> list = sql.getList(Parameter.class) ;
     			return list;
     		}
     		public String queryParaDataByQujian(ParameteDataBo pdb){
     		     String	tableName = pdb.getStationID();//表名：观测站ID
     		     String dateQ1 = null;
		    	 String dateQ2 = null;		
    			 dateQ1 = pdb.getStartDate()+" 00:00:00";
		     	 dateQ2 = pdb.getEndDate()+" 23:59:00";
		     	 String sql =" select "+pdb.getParaType()+",datepart(dd,createdate) as parameterID from "+tableName+" where parameterID>0 and createDate >='"+dateQ1+"' and createDate <'"+dateQ2+"' and datepart(HH,createdate)='"+pdb.getMonth()+"' order by  createdate";		 	
     			 return sql;
     		}
     		public List<Number> getValueArrayByField(List list , String fieldName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
     			List arry = new ArrayList() ;
     			for (Object t : list) {
     				Object va = PropertyUtils.getSimpleProperty(t, fieldName) ;   				
     				if(va instanceof String){
     					String s = StringUtil.replaceLetter(va.toString()) ;
     					if(StringUtil.checkNotNull(s) ){
     						arry.add(Double.parseDouble(s) );
     					}else{
     						arry.add(0);
     					}
     				}else if(va instanceof Number){
     						arry.add(va) ;
     				}
     			}
     			//System.out.println(arry.toString());
     			//Arrays.sort(arry.toArray()) ;
     			// Collections.sort(arry);
     			//System.out.println(arry.toString());
     			return arry ;
     		}
}