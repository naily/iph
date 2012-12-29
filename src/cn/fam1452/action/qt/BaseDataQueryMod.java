package cn.fam1452.action.qt;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import cn.fam1452.dao.pojo.NavDataYear;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.StringUtil;


@IocBean
public class BaseDataQueryMod {
	@Inject("refer:baseService")
	private BaseService baseService ;
	/**
	 * 前台页面中年份下拉列表
	 * @author gls
	 * @date 2012-09-11
	 * */
	@At("/qt/yearList")
	@Ok("json")
	public JSONArray yearListData(HttpServletRequest req ){
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		String stationId = req.getParameter("stationID");
		String querySql ="select distinct(year) as year from T_NDY ";
		if(StringUtil.checkNotNull(stationId)){
			querySql+=" where stationId='"+stationId+"'";
		}
		querySql+=" order by year ";		 
		Sql sql =Sqls.create(querySql);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(baseService.dao.getEntity(NavDataYear.class));
		baseService.dao.execute(sql) ;				
		List<NavDataYear> list =sql.getList(NavDataYear.class);
		
		if(null!=list && list.size()>0){					
			for(NavDataYear ndy:list){				
				json.put("text", ndy.getYear());
				json.put("value", ndy.getYear());
				array.add(json);
			}			
		}		
		return array;
		
	}
}
