package cn.fam1452.action.qt;


import java.util.List;

import net.sf.json.JSONObject;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.Log;
import cn.fam1452.service.BaseService;

/**
 * 首页最新数据更新查询
 * @author gls
 * @date 2012-08-24
 * */

@IocBean
public class IndexDataQueryMod extends BaseMod{
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	
	@At("/qt/latestDataUpdae")
	@Ok("json")
	public JSONObject latestData(){
		JSONObject json= new JSONObject();
		Sql sql =Sqls.create("SELECT TOP 5 * FROM T_Log");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(baseService.dao.getEntity(Log.class));
		baseService.dao.execute(sql) ;		
		
		List<Log> list =sql.getList(Log.class);
		for(Log logs:list){
			log.info(""+logs.getDataTable());
		}
		return json;
		
	}
}
