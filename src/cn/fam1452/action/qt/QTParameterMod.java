/**
 * 描述：电离层参数报表
 */
package cn.fam1452.action.qt;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.bo.ParameteDataBo;
import cn.fam1452.action.bo.ParameterMonthDateBo;
import cn.fam1452.dao.pojo.Parameter;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.ParameterService;
import cn.fam1452.utils.DateJsonValueProcessor;
import cn.fam1452.utils.QuartileUtil;
import cn.fam1452.utils.StringUtil;

@IocBean
public class QTParameterMod extends BaseMod {

	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@Inject("refer:parameterService")
	private ParameterService parameterService;

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
		log.info(json.toString());
		return json;
	}
	@At("/qt/downloadReportData")
	@Ok("jsp:jsp.qt.parameter")
	/**
	 * 电离层参数报表下载
	 * 1、根据传入的观测站、年份、月份、电离参数等条件生成报表导出到excel文件中供用户下载
	 * 2、支持全选参数、全选月份多报表生成
	 * */
	public void exportLogAndDownload(HttpServletResponse response,@Param("..")ParameteDataBo parameter){
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
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e, e) ;
		}
	}
	@At("/qt/paraDataChart")
	@Ok("jsp:jsp.qt.parameterChart")
	/**
	 * 电离曲线图图页面跳转*/
	public void loadParaChart(){
		
	}
	@At("/qt/loadParaChartData")
	@Ok("json")
	/**
	 * 电离层曲线图生成*/
	public JSONObject loadParaData(@Param("..")ParameteDataBo parameter) {
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		if (parameter != null && StringUtil.checkNotNull(parameter.getYear())
				&& StringUtil.checkNotNull(parameter.getMonth())) {
			List<ParameterMonthDateBo> list = parameterService.parameterMonthReport(parameter);
			//json.put(Constant.ROWS, JSONArray.fromObject(list));
			
			QuartileUtil quartUtil = new QuartileUtil();
			String[] filterFiled={"days"};//过滤非数据字段
			try {
				list  =	quartUtil.monthIonosphericMedDate(list, filterFiled,filterFiled[0]);
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
		log.info(json.toString());
		return json;
	}
	
	@At("/qt/paraDataQuery")
	@Ok("jsp:jsp.qt.parameterQuery")
	/**
	 * 找数据：电离层数据查询页面跳转
	 * */
	public void loadParaQuery(){
		
	}
	
	@At("/qt/listAllStation")
	@Ok("json")
	/**
	 * 电离参数查询-观测站选择*/
	public JSONArray listAllStation(){		
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		List<Station>  list = baseService.dao.query(Station.class, Cnd.where("status", "=", 1).desc("id")) ;
		if(null != list && list.size() > 0){
			for(Station station:list){	
				json.put("text", station.getName());
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
	public JSONObject doParaDataQuery(@Param("..")Parameter parameter,@Param("..")Pages page,@Param("..")ParameteDataBo paraQuery) {
		JSONObject json = new JSONObject();
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyyMMddHH")); 
		if (parameter != null && StringUtil.checkNotNull(parameter.getIds())) {
			List<Parameter> list = parameterService.parameterDataList(parameter,page,paraQuery);
			int total =this.baseService.dao.count(Parameter.class);
			List<Parameter> listD= new ArrayList<Parameter>();
			for(Parameter para:list){
				Station station = this.baseService.dao.fetch(Station.class, para.getStationID());
				para.setStation(station);
				listD.add(para);
			}
			if(null!=listD && listD.size()>0){
				json.put(Constant.SUCCESS, true);
				json.put(Constant.ROWS, JSONArray.fromObject(listD, cfg));
				json.put(Constant.TOTAL, total);//list.size()
			}else{
				json.put(Constant.ROWS, "[]");
				json.put(Constant.TOTAL, 0);
			}						
		}else{
				json.put(Constant.ROWS, "[]");
				json.put(Constant.TOTAL, 0);
		}
		log.info(json.toString());
		return json;
	}
}
