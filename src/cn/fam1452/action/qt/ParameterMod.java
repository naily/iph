/**
 * 描述：电离层参数报表
 */
package cn.fam1452.action.qt;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.bo.ParameteDataBo;
import cn.fam1452.service.ParameterService;



@IocBean
public class ParameterMod extends BaseMod{
	
	
	@Inject("refer:parameterService")
	private ParameterService parameterService ;
	
	@At("/qt/report")
    @Ok("jsp:jsp.qt.parameter")
	public void loaddefault(HttpSession session ,HttpServletRequest req,@Param("..")ParameteDataBo parameter){
		//log.info("看看进来没");
	    //直接硬编sql
		//Sql sql = Sqls.create("INSERT INTO test_change (name,subject,result) VALUES('小儿','政治',18)");
		// Dao dao = new NutDao(dataSource,new FileSqlManager("all.sqls")); 
		//dao.execute(sql);
		//文件中编sql
		/*baseService.dao.setSqlManager(new FileSqlManager("all.sqls"));
		Sql sql = baseService.dao.sqls().create("insert.data");
		baseService.dao.execute(sql);*/
		

		
	}
	@At("/qt/loadReport")
    @Ok("json")
	public JSONObject loadReportData(@Param("..")ParameteDataBo parameter,@Param("..")Pages page){		
		parameter.setYear("2012");
		parameter.setMonth("7");
		parameter.setParaType("foF2");
		List list =parameterService.parameterMonthReport(parameter);
		
		JSONObject json = new JSONObject();
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "station"}); 
		
		
		json.put(Constant.SUCCESS, true) ;
		//json.put(Constant.ROWS, JSONArray.fromObject(list));
		json.put(Constant.ROWS, JSONArray.fromObject(list, cfg));
		json.put(Constant.TOTAL,list.size());		
		return json;
	}
	
}
