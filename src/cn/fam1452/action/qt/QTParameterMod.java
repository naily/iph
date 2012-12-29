/**
 * 描述：电离层参数报表
 */
package cn.fam1452.action.qt;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.bo.ParameteDataBo;
import cn.fam1452.action.bo.ParameterMonthDateBo;
import cn.fam1452.action.filter.UserFilter;
import cn.fam1452.dao.pojo.Parameter;
import cn.fam1452.dao.pojo.ProtectDate;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataVisitService;
import cn.fam1452.service.ParameterService;
import cn.fam1452.utils.DateJsonValueProcessor;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.GetIP;
import cn.fam1452.utils.QuartileUtil;
import cn.fam1452.utils.StringUtil;

@IocBean
//@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
public class QTParameterMod extends BaseMod {

	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@Inject("refer:parameterService")
	private ParameterService parameterService;
	
	@Inject("refer:dataVisitService")
	private DataVisitService dataVisitService;
	
/*	@Filters(@By(type=UserFilter.class ))   
	@At("/yousay")   
	  public String tellMore(){  
	            return "yousay";  
	     }  
*/
	
	@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
	@At("/qt/report")
	@Ok("jsp:jsp.qt.parameter")
	/**
	 * 跳转进入报表页面
	 * */
	public void loaddefault(HttpSession session, HttpServletRequest req,
			@Param("..")
			ParameteDataBo parameter) {
		// log.info("看看进来没");
		// 直接硬编sql
		// Sql sql = Sqls.create("INSERT INTO test_change (name,subject,result)
		// VALUES('小儿','政治',18)");
		// Dao dao = new NutDao(dataSource,new FileSqlManager("all.sqls"));
		// dao.execute(sql);
		// 文件中编sql
		/*
		 * baseService.dao.setSqlManager(new FileSqlManager("all.sqls")); Sql
		 * sql = baseService.dao.sqls().create("insert.data");
		 * baseService.dao.execute(sql);
		 */

	}

	@SuppressWarnings("unchecked")
	@At("/qt/loadReport")
	@Ok("json")
	/**
	 * 报表：电离层参数月报动态生成
	 * 根据传入的观测站、年份、月份、电离参数等条件生成月报报表
	 * */
	public JSONObject loadReportData(@Param("..")ParameteDataBo parameter) {
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		if (parameter != null && StringUtil.checkNotNull(parameter.getYear())
				&& StringUtil.checkNotNull(parameter.getMonth())) {
			List<ParameterMonthDateBo> list = parameterService.parameterMonthReport(parameter);
			//json.put(Constant.ROWS, JSONArray.fromObject(list));
			
			QuartileUtil quartUtil = new QuartileUtil();
			String[] filterFiled={"days"};//过滤非数据字段
			try {
				list  =	quartUtil.monthIonosphericDate(list, filterFiled,filterFiled[0]);
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
			if(null!=list && list.size()>0){
				json.put(Constant.SUCCESS, true);
				//json.put(Constant.ROWS, JSONArray.fromObject(list, cfg));
				json.put(Constant.ROWS, list);
				json.put(Constant.TOTAL, list.size());
			}		
		}	
		//log.info(json.toString());
		return json;
	}
	@At("/qt/downloadReportData")
	@Ok("raw")
	/**
	 * 电离层参数报表下载
	 * 1、根据传入的观测站、年份、月份、电离参数等条件生成报表导出到excel文件中供用户下载
	 * 2、支持全选参数、全选月份多报表生成
	 * */
	public void exportLogAndDownload(HttpServletRequest req ,  HttpServletResponse response,@Param("..")ParameteDataBo parameter){
		String id = parameter.getStationID();
		Station station = baseService.dao.fetch(Station.class, id);
		parameter.setStation(station);
		Workbook wb = parameterService.exportToHSSFWorkbook(parameter) ;
		
		try {
			if(null != wb){ 
				OutputStream out = response.getOutputStream();
				response.setContentType("application/x-msdownload");
				
				StringBuffer fileName = new StringBuffer().append("month_reportData.xls") ;
				response.setHeader("Content-Disposition", "attachment; filename=" + new String( fileName.toString().getBytes("GBK"), "ISO8859-1" ));
				
				//BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tmp));
				//byte[] buffer = IOUtils.toByteArray(bis);
				//os.write(buffer);
				wb.write(out) ;
				out.close();
				
				String[] monthAry=null;
			    String[] paraAry=null;
			    if("all".equals(parameter.getMonth())){//全部月份
			    	monthAry=Constant.monthAry;
			    }else{
			    	monthAry = new String[1];
			    	monthAry[0]=parameter.getMonth();
			    }
		        if("all".equals(parameter.getParaType())){//全部参数
		        	paraAry=Constant.paraAry;
			    }else{
			    	paraAry= new String[1];
			    	paraAry[0]=parameter.getParaType();
			    }
		        
		        int total = monthAry.length * paraAry.length ;
		        //单因子文件常量 (M)
		        float fs = 21;
				dataVisitService.insert(dataVisitService.T_PARAMETER, "03", 1, getQTLoginUserID(), GetIP.getIpAddr(req), (total*fs) );
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e, e) ;
		}
	}
	/**
	 * 电离曲线图图页面跳转--按区间查询*/
	@At("/qt/paraDataChartByQujian")
	@Ok("jsp:jsp.qt.parameterChart2")
	@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
	/**
	 * 电离曲线图图页面跳转*/
	public void loadParaChartByQuJian(){
		
	}
	@At("/qt/paraDataChart")
	@Ok("jsp:jsp.qt.parameterChart")
	@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
	/**
	 * 电离曲线图图页面跳转*/
	public void loadParaChart(){
		
	}
	@SuppressWarnings("unchecked")
	@At("/qt/loadParaChartDataNew")
	@Ok("json")
	/**
	 * 电离层曲线图生成(多站点单参数月中值曲线，多站点单参数长期变化曲线
	 * 1、单站点单因子时显示3个四分位数（UQ、LQ、MED）--单因子三条线
	 * 2、多站点单因子时显示各个因子的中位数的值（MED值）--几个站点几个曲线
	 *   
	 * */
	public JSONObject loadParaDataNew(@Param("..")ParameteDataBo parameter , HttpSession session ,HttpServletRequest req) {
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		json.put("showSanDianPic", false);
		if (parameter != null && StringUtil.checkNotNull(parameter.getYear())&& StringUtil.checkNotNull(parameter.getMonth())) {
			List<ParameterMonthDateBo> list =null;//电离月报报表(不含四分位数)
			List medList= new ArrayList();//四分位数列表（单因子list=1，多因子list=4）
			if(null!=parameter.getParaType()){				
				QuartileUtil quartUtil=null;
				String[] filterFiled={"days"};//过滤非数据字段
				String[] stationAry = null;
				String stationStr = req.getParameter("stationIDs");
				/*if(null!=parameter.getStationID()){
					stationAry =parameter.getStationID().split(",");
				}*/
				if(null!=stationStr){
					stationAry =stationStr.split(",");
				}
				String hourStr = req.getParameter("hourStr");//小时
				//String[] paraAry = parameter.getParaType().split(",");//电离参数处理，多因子用逗号隔开
				String paraName = parameter.getParaType();//电离参数(单参数)
				int[]  monthAry =StringUtil.getIntArrayBySplitString(parameter.getMonth(),",");
				   if(null!=stationAry && stationAry.length==1){//单站点时
						json.put("paraFlag", 1);//曲线图类型：单因子
						//json.put("chartTitle", "Monthly median values and its distribution of "+paraAry[0]);
						json.put("yAxis", "Critical Frequency ");
						json.put("paraName", paraName);
						quartUtil = new QuartileUtil();	
						parameter.setParaType(paraName);
						String tableName =stationAry[0];
						Station station = this.baseService.dao.fetch(Station.class,tableName);
						if(monthAry.length>0){
						  List medListOne = new ArrayList();
						  Map map ;
						  for(int m:monthAry){//遍历月份
							map = new HashMap();
							parameter.setMonth(String.valueOf(m));	
							//parameter.setStationID("WU430");
							parameter.setStationID(stationAry[0]);
							list = parameterService.parameterMonthReport(parameter);
							if(null!=list && list.size()>0){
								medListOne=quartUtil.monthIonosphericMedDateOne(list, filterFiled,filterFiled[0]);	
							}
							map.put("mutiMonth", medListOne);
							map.put("fusionCharts",quartUtil.getFushionChartData(list, parameter.getStationID()+"  "+parameter.getYear()+"."+parameter.getMonth()+" of "+paraName));//生成散点图
							//map.put("chartTitle", parameter.getStationID()+"  "+parameter.getYear()+"."+parameter.getMonth()+" of "+paraName);
							map.put("chartTitle",DateUtil.getMonthEn(m)+"."+ parameter.getYear()+" 120°E.M.T ( G.M.T + 8h ) &nbsp;&nbsp; "+tableName+"("+station.getLongitude()+"°E  "+station.getLatitude()+"°N)");
							map.put("topTitle", "Monthly median values and its distribution of "+paraName);
							json.put("showSanDianPic", true);
							medList.add(map);
						  }
						}
						json.put("SingleFactor", medList);										
				   }
				   if(null!=stationAry && stationAry.length>1){//多观测站时
					   json.put("paraFlag", 1);//曲线图类型：单因子
						//json.put("chartTitle", "Monthly median values and its distribution of "+paraAry[0]);
						json.put("yAxis", "Critical Frequency ");
						json.put("paraName", paraName);
						
						 /**
						  * 不选小时：按小时连续显示00-23的曲线值（x轴 小时）【多个曲线图（每个月一个），每个图中显示各观测站的参数值）】
						  * 选小时：按月份显示电离曲线（x轴 月份）【只有一个图，显示1-12月中各个观测站在某个时刻的电离值，】
						  * */
						if(null!=hourStr && !"".equals(hourStr)){
							//hourStr="h"+hourStr;//前台传值：00-23，类中的属性：h01-h23
							quartUtil = new QuartileUtil();	
							parameter.setParaType(paraName);
							//if(monthAry.length>0){
							  List medListOne =null;
							  Map map = new HashMap();	
							  Map medMap = null;	
							  for(String stationID:stationAry){
								medMap = new HashMap();
								parameter.setStationID(stationID);
								medListOne = new ArrayList();//月报电离曲线																														 
								 float[] pValueAry =  new float[12];//
								 float paraValue=0;
								 //for(int m:monthAry){//遍历月份
								   for(int m=1;m<=12;m++){//遍历月份									
									parameter.setMonth(String.valueOf(m));
									list = parameterService.parameterMonthReport(parameter);									
									 if(null!=list && list.size()>0){									
										paraValue = quartUtil.monthIonosphericMedDateForHour(list, filterFiled,filterFiled[0],"h"+hourStr);																				
									 }
									pValueAry[m-1] =paraValue;								
								  }//end for month
								    medMap.put("name", stationID);
									medMap.put("data", pValueAry);
									medListOne.add(medMap);
							  }//end for  station
							    json.put("showSanDianPic", false);
								map.put("mutiMonth", medListOne);
								//map.put("fusionCharts",quartUtil.getFushionChartData(list, parameter.getStationID()+"  "+parameter.getYear()+"."+parameter.getMonth()+" of "+paraName));//生成散点图
								//map.put("chartTitle", parameter.getStationID()+"  "+parameter.getYear()+"."+parameter.getMonth()+" of "+paraName);
								map.put("chartTitle", DateUtil.getMonthEn(1)+"."+parameter.getYear()+" -> "+DateUtil.getMonthEn(12)+"."+parameter.getYear()+" ("+hourStr+")");
								//map.put("chartTitle", parameter.getYear()+" 1->"+parameter.getYear()+" 12("+hourStr+") of "+paraName);
								map.put("topTitle", "Monthly median values and its distribution of "+paraName);
								medList.add(map);
								
								
							//}
					      }else{//
					    	  quartUtil = new QuartileUtil();	
								parameter.setParaType(paraName);
								if(monthAry.length>0){
								  List medListOne =null;
								  Map map ;
								  for(int m:monthAry){//遍历月份
									medListOne = new ArrayList();//月报电离曲线
									map = new HashMap();
									parameter.setMonth(String.valueOf(m));	
									Map medMap;
									 for(String stationID:stationAry){
										parameter.setStationID(stationID);
										list = parameterService.parameterMonthReport(parameter);							
										if(null!=list && list.size()>0){
											medMap = new HashMap();
											//medMap = quartUtil.monthIonosphericMedDateForMutiStation(list, filterFiled,filterFiled[0],stationID);								
											medMap = quartUtil.monthIonosphericMedDate(list, filterFiled,filterFiled[0]);
											medMap.put("name", stationID);
											medListOne.add(medMap);
										 }
									  }
									map.put("mutiMonth", medListOne);
									map.put("fusionCharts",quartUtil.getFushionChartData(list, parameter.getStationID()+"  "+parameter.getYear()+"."+parameter.getMonth()+" of "+paraName));//生成散点图
									json.put("showSanDianPic", false);
									//map.put("chartTitle", parameter.getStationID()+"  "+parameter.getYear()+"."+parameter.getMonth()+" of "+paraName);
									//map.put("chartTitle", parameter.getYear()+"."+parameter.getMonth()+" of "+paraName);
									map.put("chartTitle", DateUtil.getMonthEn(m)+"."+parameter.getYear());
									map.put("topTitle", "Monthly median values and its distribution of "+paraName);
									medList.add(map);
								  }//end for  month
								}
					   }
					   
					   
		
						
						
						json.put("SingleFactor", medList);//单因子	
				   }
				   
				}//end  if(null!=parameter.getParaType()){
							
			if (null != medList) {
				json.put(Constant.SUCCESS, true);
				dataVisitService.insert(dataVisitService.T_PARAMETER, "01", medList.size(), getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
			} else {
				dataVisitService.insert(dataVisitService.T_PARAMETER, "01", 0, getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
			}
			}//end if
			
		//log.info(json.toString());
		return json;
	}
	/**
	 * 按区间查询电离层参数
	 * 多站点，单参数，日期区间选择，小时选项
	 * X轴数值：天（日期区间中的所有天，当天数大于N时X轴不显示,N：待定）
	 * */
	@At("/qt/loadParaChartDataByQujian")
	@Ok("json")
	public JSONObject loadParaChartDataZByQujian(@Param("..")ParameteDataBo parameter , HttpSession session ,HttpServletRequest req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		if (parameter != null && null!=parameter.getParaType() && StringUtil.checkNotNull(parameter.getStartDate())&& StringUtil.checkNotNull(parameter.getEndDate())) {
			String[] stationAry = null;
			String stationStr = req.getParameter("stationIDs");//观测站（多站点）
			String paraName = parameter.getParaType();//电离参数(单参数)
			String hourStr = req.getParameter("hourStr");//小时		 
			String startDate = parameter.getStartDate();//起始日期
			String endDate = parameter.getEndDate();//结束日期
			if(null!=stationStr){
				stationAry =stationStr.split(",");
			}
			List<Parameter> list =	null;//查询某观测站，某时间段内，某时刻的电离值列表。		  
		   if(null!=stationAry && stationAry.length>=1){//多观测站时
			  parameter.setParaType(paraName);
			  parameter.setMonth(hourStr);//利用month字段传小时的值
			  List<Map> medList = new ArrayList<Map>();
			  List<Number> paraValueList =null;//某观测站，电离值
			  //List<String> paraXvalue=null;//
			  List<Number> paraXvalue=null;//
			  Map<String, Object> map = null;		
				  for(String stationID:stationAry){
					    map = new HashMap<String, Object>();
						parameter.setStationID(stationID);
						list=parameterService.parameterCharByQujian(parameter);				
						paraValueList = parameterService.getValueArrayByField(list, parameter.getParaType());	
						//paraXvalue = parameterService.getYearValueArrayByField(list, "createDate");	
						paraXvalue = parameterService.getValueArrayByField(list, "parameterID");	
						map.put("name",stationID );//parameter.getParaType()
						//map.put("xPara", paraXvalue);
						map.put("data", paraValueList);
						medList.add(map);
				   }//end for  station	
				json.put(Constant.SUCCESS, true);				
				json.put("paraName", paraName);
				json.put("title",parameter.getStartDate()+" -> "+parameter.getEndDate()+" ("+hourStr+")");//主标题
				json.put("subtitle", "Monthly median values and its distribution of "+paraName);//副标题
				json.put("xAxis", paraXvalue);//x轴显示
				json.put("yAxis", paraName+"("+QuartileUtil.getUnit(paraName)+")");//y轴显示				
				json.put("series", medList);//单因子					
				if (null != medList) {
					json.put(Constant.SUCCESS, true);
					dataVisitService.insert(dataVisitService.T_PARAMETER, "01", medList.size(), getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
				} else {
					dataVisitService.insert(dataVisitService.T_PARAMETER, "01", 0, getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
				}
			 }							
		}//end if
		//log.info(json.toString());
		return json;
	}
	@SuppressWarnings("unchecked")
	@At("/qt/loadParaChartData")
	@Ok("json")
	/**
	 * ==============================带组合因子的方法（保留以备参数需要重新改回来）=========================================
	 * 电离层曲线图生成(单因子或者多因子电离图)
	 * 1、单因子时显示3个四分位数（UQ、LQ、MED）--单因子三条线
	 * 2、多因子时显示各个因子的中位数的值（MED值）--多因子四条线（目前固定两种组合都是四个因子）
	 *   两种多因子组合  foF2.foF1.foEs.foE    h'F2.h'Es.h'E.h'F1
	 * */
	public JSONObject loadParaData(@Param("..")ParameteDataBo parameter , HttpSession session ,HttpServletRequest req) {
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		if (parameter != null && StringUtil.checkNotNull(parameter.getYear())&& StringUtil.checkNotNull(parameter.getMonth())) {
			List<ParameterMonthDateBo> list =null;//电离月报报表(不含四分位数)
			List medList= new ArrayList();//四分位数列表（单因子list=1，多因子list=4）
			if(null!=parameter.getParaType()){
				//log.info("parameter.getParaType()="+parameter.getParaType());
				QuartileUtil quartUtil=null;
				String[] filterFiled={"days"};//过滤非数据字段
				String[] paraAry = parameter.getParaType().split(",");//电离参数处理，多因子用逗号隔开
				int[]  monthAry =StringUtil.getIntArrayBySplitString(parameter.getMonth(),",");
				if(paraAry.length==1){//单因子
						json.put("paraFlag", 1);//曲线图类型：单因子
						//json.put("chartTitle", "Monthly median values and its distribution of "+paraAry[0]);
						json.put("yAxis", "Critical Frequency ");
						json.put("paraName", paraAry[0]);
						quartUtil = new QuartileUtil();	
						parameter.setParaType(paraAry[0]);
						if(monthAry.length>0){
						  List medListOne = new ArrayList();
						  Map map ;
						  for(int m:monthAry){//遍历月份
							map = new HashMap();
							parameter.setMonth(String.valueOf(m));			
							list = parameterService.parameterMonthReport(parameter);
							if(null!=list && list.size()>0){
								medListOne=quartUtil.monthIonosphericMedDateOne(list, filterFiled,filterFiled[0]);	
							}
							map.put("mutiMonth", medListOne);
							map.put("fusionCharts",quartUtil.getFushionChartData(list, parameter.getStationID()+"  "+parameter.getYear()+"."+parameter.getMonth()+" of "+paraAry[0]));//生成散点图
							map.put("chartTitle", parameter.getStationID()+"  "+parameter.getYear()+"."+parameter.getMonth()+" of "+paraAry[0]);
							medList.add(map);
						  }
						}
						json.put("SingleFactor", medList);//单因子
										
				}else{//多因子：两种组合 组合1：（foF2,foF1,foEs,foE），组合2：（M3000F2,P(foEs),hiEs）
					Map map =null;
					//根据多因子数组的首因子判断是何种组合，从而生成主曲线图上方的曲线图
					if("foF2".equals(paraAry[0])){//多因子组合1：
						//======上方曲线图（h'E,h'F2,h'F1）============hlE,hlF2,hlF1
						json.put("paraFlag", 2);//曲线图类型：多因子组合1
						json.put("top_chartTitle", "Monthly ionospheric data plot");
						json.put("top_yAxis", "Virtual Height(Km)");
						json.put("top_paraName", "hlE");
						String[] topPara ={"hlF2","hlF1","hlE"};
						for(String topP:topPara){
							 parameter.setParaType(topP);
							 list = parameterService.parameterMonthReport(parameter);
							 if(null!=list && list.size()>0){
								 quartUtil = new QuartileUtil();	
								 map=quartUtil.monthIonosphericMedDate(list, filterFiled, filterFiled[0]);//
							 }						 
							 map.put("name", topP);
							 medList.add(map);
						}
						 json.put("topChart", medList);
						 medList.clear();
						//======下方曲线图(foF2,foF1,foEs,foE)============
						json.put("chartTitle", "Monthly ionospheric data plot");
						json.put("yAxis", "Critical Frequency(Km)");
						json.put("paraName", paraAry[0]);
						
						for(String paraValue:paraAry){//遍历因子，通过生成的单因子电离月报数据，计算单因子四分位数
							 parameter.setParaType(paraValue);						 
							 list = parameterService.parameterMonthReport(parameter);	
							 if(null!=list && list.size()>0){
								 quartUtil = new QuartileUtil();
								 map=quartUtil.monthIonosphericMedDate(list, filterFiled, filterFiled[0]);//
							 }
							 map.put("name", paraValue);
							 medList.add(map);								 
						 }	
						  json.put("MultiFactor1", medList);//
						}else{//组合2
						  //==============上方曲线（M3000F2）==================
							json.put("paraFlag", 3);//曲线图类型：多因子组合2
							json.put("top_chartTitle", "Monthly ionospheric data plot");
							json.put("top_yAxis", "");
							json.put("top_paraName", "M3000F2");
							parameter.setParaType("M3000F2");
							list = parameterService.parameterMonthReport(parameter);
							 if(null!=list && list.size()>0){
								 quartUtil = new QuartileUtil();	
								 map=quartUtil.monthIonosphericMedDate(list, filterFiled, filterFiled[0]);//
							 }
							 
							 map.put("name", "M3000F2");
							 medList.add(map);
							 json.put("topChart", medList);
							 medList.clear();
						  
						  //==============下方柱图（h'Es）==================
							  //Map<String,String> mapCnt = new HashMap();
							    parameter.setParaType("hlEs");
								list = parameterService.parameterMonthReport(parameter);
								 if(null!=list && list.size()>0){
									 quartUtil = new QuartileUtil();																			 
									 map =quartUtil.monthIonosphericCntDate(list, filterFiled, filterFiled[0]);																		
								 }							 
								 map.put("name", "h'Es");
								 medList.add(map);
								 json.put("downChart", medList);
								 medList.clear();
							 
						  //==============中间曲线（P（foEs））==================
								json.put("chartTitle", "Monthly ionospheric data plot");
								json.put("yAxis", "P(foEs)");
								json.put("paraName", "P(foEs)");
								 parameter.setParaType("foEs");
								 list = parameterService.parameterMonthReport(parameter);
									 if(null!=list && list.size()>0){
										 quartUtil = new QuartileUtil();																			 
										 try {
											map =quartUtil.getPFoEs(list, 3);
											map.put("name", "P(foEs>3)");
											medList.add(map);
											map =quartUtil.getPFoEs(list, 5);
											map.put("name", "P(foEs>5)");
											medList.add(map);
											map =quartUtil.getPFoEs(list, 7);
											map.put("name", "P(foEs>7)");
											medList.add(map);
										} catch (IllegalAccessException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (NoSuchMethodException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}																		
									 }							 
								/* map.put("name", "h'Es");
								 medList.add(map);
								 json.put("downChart", medList);*/
									
							json.put("MultiFactor2", medList);//
						 }//end 组合2				
					
					}//end 多因子
				}//end  if(null!=parameter.getParaType()){
							
			if (null != medList) {
				json.put(Constant.SUCCESS, true);
				dataVisitService.insert(dataVisitService.T_PARAMETER, "01", medList.size(), getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
			} else {
				dataVisitService.insert(dataVisitService.T_PARAMETER, "01", 0, getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
			}
			}//end if
			
		//log.info(json.toString());
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@At("/qt/loadParaChartData_bak")
	@Ok("json")
	/**
	 * 电离层曲线图生成(单因子或者多因子电离图)
	 * 1、单因子时显示3个四分位数（UQ、LQ、MED）--单因子三条线
	 * 2、多因子时显示各个因子的中位数的值（MED值）--多因子四条线（目前固定两种组合都是四个因子）
	 *   两种多因子组合  foF2.foF1.foEs.foE    h'F2.h'Es.h'E.h'F1
	 * */
	public JSONObject loadParaData_Bak(@Param("..")ParameteDataBo parameter) {
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		if (parameter != null && StringUtil.checkNotNull(parameter.getYear())&& StringUtil.checkNotNull(parameter.getMonth())) {
			List<ParameterMonthDateBo> list =null;//电离月报报表(不含四分位数)
			List medList= new ArrayList();//四分位数列表（单因子list=1，多因子list=4）
			if(null!=parameter.getParaType()){
				//log.info("parameter.getParaType()="+parameter.getParaType());
				QuartileUtil quartUtil=null;
				String[] filterFiled={"days"};//过滤非数据字段
				String[] paraAry = parameter.getParaType().split(",");//电离参数处理，多因子用逗号隔开
				if(paraAry.length==1){//单因子				
						json.put("chartTitle", "Monthly median values and its distribution of "+paraAry[0]);
						json.put("yAxis", "Critical Frequency ");
						json.put("paraName", paraAry[0]);
						quartUtil = new QuartileUtil();	
						parameter.setParaType(paraAry[0]);
						list = parameterService.parameterMonthReport(parameter);
						if(null!=list && list.size()>0){
							medList=quartUtil.monthIonosphericMedDateOne(list, filterFiled,filterFiled[0]);	
						}									
										
				}else{//多因子：两种组合 组合1：（foF2,foF1,foEs,foE），组合2：（M3000F2,P(foEs),hiEs）
					Map map =null;
					//根据多因子数组的首因子判断是何种组合，从而生成主曲线图上方的曲线图
					if("foF2".equals(paraAry[0])){//多因子组合1：
						//======上方曲线图（h'E,h'F2,h'F1）============hlE,hlF2,hlF1
						json.put("top_chartTitle", "Monthly ionospheric data plot");
						json.put("top_yAxis", "Virtual Height(Km)");
						json.put("top_paraName", "hlE");
						String[] topPara ={"hlF2","hlF1","hlE"};
						for(String topP:topPara){
							 parameter.setParaType(topP);
							 list = parameterService.parameterMonthReport(parameter);
							 if(null!=list && list.size()>0){
								 quartUtil = new QuartileUtil();	
								 map=quartUtil.monthIonosphericMedDate(list, filterFiled, filterFiled[0]);//
							 }						 
							 map.put("name", topP);
							 medList.add(map);
						}
						 json.put("topChart", medList);
						 medList.clear();
						//======下方曲线图(foF2,foF1,foEs,foE)============
						json.put("chartTitle", "Monthly ionospheric data plot");
						json.put("yAxis", "Critical Frequency(Km)");
						json.put("paraName", paraAry[0]);
						
						for(String paraValue:paraAry){//遍历因子，通过生成的单因子电离月报数据，计算单因子四分位数
							 parameter.setParaType(paraValue);						 
							 list = parameterService.parameterMonthReport(parameter);	
							 if(null!=list && list.size()>0){
								 quartUtil = new QuartileUtil();
								 map=quartUtil.monthIonosphericMedDate(list, filterFiled, filterFiled[0]);//
							 }
							 map.put("name", paraValue);
							 medList.add(map);								 
						 }	
						}else{//组合2
						  //==============上方曲线（M3000F2）==================
							json.put("top_chartTitle", "Monthly ionospheric data plot");
							json.put("top_yAxis", "");
							json.put("top_paraName", "M3000F2");
							parameter.setParaType("M3000F2");
							list = parameterService.parameterMonthReport(parameter);
							 if(null!=list && list.size()>0){
								 quartUtil = new QuartileUtil();	
								 map=quartUtil.monthIonosphericMedDate(list, filterFiled, filterFiled[0]);//
							 }
							 
							 map.put("name", "M3000F2");
							 medList.add(map);
							 json.put("topChart", medList);
							 medList.clear();
						  
						  //==============下方柱图（h'Es）==================
							  //Map<String,String> mapCnt = new HashMap();
							    parameter.setParaType("hlEs");
								list = parameterService.parameterMonthReport(parameter);
								 if(null!=list && list.size()>0){
									 quartUtil = new QuartileUtil();																			 
									 map =quartUtil.monthIonosphericCntDate(list, filterFiled, filterFiled[0]);																		
								 }							 
								 map.put("name", "h'Es");
								 medList.add(map);
								 json.put("downChart", medList);
								 medList.clear();
							 
						  //==============中间曲线（P（foEs））==================
								json.put("chartTitle", "Monthly ionospheric data plot");
								json.put("yAxis", "P(foEs)");
								json.put("paraName", "P(foEs)");
								 parameter.setParaType("foEs");
								 list = parameterService.parameterMonthReport(parameter);
									 if(null!=list && list.size()>0){
										 quartUtil = new QuartileUtil();																			 
										 try {
											map =quartUtil.getPFoEs(list, 3);
											map.put("name", "P(foEs>3)");
											medList.add(map);
											map =quartUtil.getPFoEs(list, 5);
											map.put("name", "P(foEs>5)");
											medList.add(map);
											map =quartUtil.getPFoEs(list, 7);
											map.put("name", "P(foEs>7)");
											medList.add(map);
										} catch (IllegalAccessException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (NoSuchMethodException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}																		
									 }							 
								/* map.put("name", "h'Es");
								 medList.add(map);
								 json.put("downChart", medList);*/
									
							
						 }//end 组合2				
					
					}//end 多因子
				}//end  if(null!=parameter.getParaType()){
							
			if(null!=medList ){
				json.put(Constant.SUCCESS, true);
				json.put(Constant.ROWS, medList);
			  }		
			}//end if
			
		//log.info(json.toString());
		return json;
	}
	
	
	@At("/qt/paraDataQuery")
	@Ok("jsp:jsp.qt.parameterQuery")
	@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
	/**
	 * 找数据：电离层数据查询页面跳转
	 * */
	public void loadParaQuery(HttpServletRequest req,@Param("..")Parameter parameter,@Param("..")ParameteDataBo paraQuery){
		//log.info("year="+paraQuery.getYear());
		//log.info("station="+paraQuery.getStationID());
		if(null!=paraQuery && StringUtil.checkNotNull(paraQuery.getYear())){
			paraQuery.setStartDate(paraQuery.getYear()+"-01-01");
			paraQuery.setEndDate(paraQuery.getYear()+"-12-31");
			
		}
		req.setAttribute("para", paraQuery);
	}
	
	@At("/qt/listAllStation")
	@Ok("json")
	/**
	 * 电离参数查询-观测站选择*/
	public JSONArray listAllStation(HttpServletRequest req){		
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		List<Station>  list = baseService.dao.query(Station.class, Cnd.where("status", "=", 1).desc("id")) ;
		String zh_en=this.getMsgByKey(req, "lang");
		if(null != list && list.size() > 0){
			for(Station station:list){	
				if("en".equals(zh_en)){
					json.put("text", station.getNameEng());
				}else{
					json.put("text", station.getName());
				}
				json.put("value", station.getId());
				array.add(json);
			}		
			
		}
		//log.info(array.toString());
		return array ;
	}
	
	@At("/qt/doParaDataQuery")
	@Ok("json")
	/**
	 * 找数据模块：电离层参数查询
	 * */
	public JSONObject doParaDataQuery(@Param("..")Parameter parameter,@Param("..")Pages page,@Param("..")ParameteDataBo paraQuery,HttpServletRequest req) {
		JSONObject json = new JSONObject();
		JsonConfig cfg = new JsonConfig();
		//cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyyMMddHH")); 
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:MM:SS"));
		cfg.setExcludes(new String[] { "address" , "administrator","email","homepage","introduction","latitude","location","longitude","phone","picPath","timeZone","zipCode"}); 
		if (parameter != null && StringUtil.checkNotNull(parameter.getIds())) {
			List<Parameter> list =null;
			int total =0;
			//if(parameterService.isProtectDate("T_PARAMETER")){//判断电离参数表是否设置了保护期
			//判断电离参数表是否设置了保护期,若保护期存在则进行数据拼装（保护期内的前50条数据+保护期外的数据）
			//String tableName ="T_PARAMETER";
			String tableName =parameter.getIds();//
			//boolean existProtect =false;//是否有保护期
			String protectArea=null;//保护期区间
			if(!parameterService.isProtectDateOpen(tableName,paraQuery.getStartDate(),paraQuery.getEndDate())){
				list=parameterService.top50ParameterDataList(parameter,tableName,paraQuery);
				if(null!=list && list.size()>0)total=list.size();
				//existProtect =true;
				ProtectDate proD =parameterService.getProtectDateByTableName(tableName);
				protectArea =DateUtil.convertDateToString(proD.getDataSDate())+","+DateUtil.convertDateToString(proD.getDataEDate());
				
			}else{//无保护期,正常显示数据
				list = parameterService.parameterDataList(parameter,page,paraQuery);
				
				 //total =this.baseService.dao.count(Parameter.class);
				 //total =this.baseService.dao.count(Parameter.class,parameterService.getParamenterCnd(parameter, paraQuery));
				 total =this.baseService.dao.count(parameter.getIds());
			}
		
			List<Parameter> listD= new ArrayList<Parameter>();
			
			/*for(Parameter para:list){
				Station station = this.baseService.dao.fetch(Station.class, para.getStationID());
				para.setStation(station);
				listD.add(para);
			}*/
			String zh_en=this.getMsgByKey(req, "lang");
			Station station = this.baseService.dao.fetch(Station.class,tableName);
			if("en".equals(zh_en)){
				station.setName(station.getNameEng());
			}
			for(Parameter para:list){					
				para.setStation(station);
				listD.add(para);
			}
			
			if(null!=listD && listD.size()>0){
				json.put(Constant.SUCCESS, true);
				json.put(Constant.ROWS, JSONArray.fromObject(listD, cfg));
				json.put(Constant.TOTAL, total);//list.size()
				json.put(Constant.PROTECTDATA_AREA, protectArea);
			}else{
				json.put(Constant.ROWS, "[]");
				json.put(Constant.TOTAL, 0);
				json.put(Constant.PROTECTDATA_AREA, protectArea);
			}						
		}else{
				json.put(Constant.ROWS, "[]");
				json.put(Constant.TOTAL, 0);
				json.put(Constant.PROTECTDATA_AREA, null);
		}
		//log.info(json.toString());
		return json;
	}
	
	@At("/qt/showParaData")
	@Ok("json")
	/**
	 * 找数据模块：电离层参数查询
	 * */
	public JSONObject showParaData(@Param("..")Parameter parameter,@Param("..")Pages page,@Param("..")ParameteDataBo paraQuery,HttpServletRequest req) {
		JSONObject json = new JSONObject();
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyyMMddHH")); 
		cfg.setExcludes(new String[] {"address" , "administrator","email","homepage","introduction","latitude","location","longitude","phone","picPath","timeZone","zipCode"}); 
	    //log.info(parameter.getCreateDate());//"station",
	    //log.info(DateUtil.convertDateToString(parameter.getCreateDate()));
	    String createDate= DateUtil.convertDateToString(parameter.getCreateDate());
	    String startTime =createDate+" 00:00:00";
	    String endTime =createDate+" 23:59:59";
		//Sql sql =Sqls.create("select * from T_PARAMETER where stationID='"+parameter.getStationID()+"' and createDate between '"+startTime+"' and '"+endTime+"'");
		Sql sql =Sqls.create("select * from T_PARAMETER where stationID='"+parameter.getStationID()+"' and createDate >= '"+startTime+"' and createDate<= '"+endTime+"'");
		//log.info(sql.toString());
		
		sql.setCallback(Sqls.callback.entities());
		//sql.setEntity(dao.getEntity(ParameteDataBo.class));
		sql.setEntity(baseService.dao.getEntity(Parameter.class));
		baseService.dao.execute(sql) ;		
		List<Parameter> list = sql.getList(Parameter.class) ;
		//log.info("list.size="+list.size());
		List<Parameter> listV = new ArrayList<Parameter>();
		String zh_en=this.getMsgByKey(req, "lang");
		for(Parameter para:list){
			Station station = this.baseService.dao.fetch(Station.class, para.getStationID());
			if("en".equals(zh_en)){
				station.setName(station.getNameEng());
			}
			para.setStation(station);
			listV.add(para);
		}
		if(null!=listV && listV.size()>0){
			json.put(Constant.SUCCESS, true);
			json.put(Constant.ROWS, JSONArray.fromObject(listV, cfg));
			json.put(Constant.TOTAL, listV.size());
		}else{
			json.put(Constant.SUCCESS, false);
		}
		//log.info("listV.size="+listV.size());
		//log.info(json.toString());
		return json;
	}
	@At("/qt/downloadParaData")
	@Ok("raw")
	/**
	 * 找数据：电离层参数导出excel
	 * */
	public void exportParaData(@Param("..")Parameter parameter,@Param("..")Pages page,@Param("..")ParameteDataBo paraQuery,HttpServletResponse response,HttpServletRequest req){
		if (parameter != null && StringUtil.checkNotNull(parameter.getIds())) {
			page.setLimit(Constant.PAGE_SIZE);
			List<Parameter> list =null;
			//if(parameterService.isProtectDate("T_PARAMETER")){//判断电离参数表是否设置了保护期
			String tableName="T_PARAMETER";
			if(!parameterService.isProtectDateOpen(tableName,paraQuery.getStartDate(),paraQuery.getEndDate())){//判断电离参数表是否设置了保护期
				list=parameterService.top50ParameterDataList(parameter,tableName,paraQuery);				
			}else{
				list = parameterService.parameterDataList(parameter,page,paraQuery);
			}		
			List<Parameter> listD= new ArrayList<Parameter>();
			String zh_en=this.getMsgByKey(req, "lang");
			for(Parameter para:list){
				Station station = this.baseService.dao.fetch(Station.class, para.getStationID());
				if("en".equals(zh_en)){
					station.setName(station.getNameEng());
				}
				para.setStation(station);
				listD.add(para);
			}
			Workbook wb = parameterService.exportParaDataToHSSFWorkbook(listD) ;
			
			try {
				if(null != wb){
					OutputStream out = response.getOutputStream();
					response.setContentType("application/x-msdownload");
					
					StringBuffer fileName = new StringBuffer().append("parameter_data.xls") ;
					response.setHeader("Content-Disposition", "attachment; filename=" + new String( fileName.toString().getBytes("GBK"), "ISO8859-1" ));
					
					//BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tmp));
					//byte[] buffer = IOUtils.toByteArray(bis);
					//os.write(buffer);
					
					wb.write(out) ;
					out.close();
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				log.error(e, e) ;
			}
		}
		
		
	}
}
