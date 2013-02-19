/**
 * 数据访问权限以数据表为粒度，对每张数据表的访问权限进行设置。缺省情况下，不设置访问限制。元数据查询访问不受访问权限设置的影响。
 * 数据访问权限分两种情况：一是所有数据的查询访问都要求用户登录；二设置保护期的数据，在保护期内登录用户只能查看前50条记录。
 * 对指定的数据表进行保护期的设置，缺省情况下数据表的访问不受保护期的影响。提供翻页或查询数据表的功能，以便快速查找和定位数据表。设置保护期的数据，在保护期内登录用户只能查看前50条记录。用户可通过离线申请的形式向管理员申请获取此数据
 */
package cn.fam1452.action.ht;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.filter.AdminFilter;
import cn.fam1452.dao.pojo.ProtectDate;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

/**
 * @author zdd
 *
 */
@IocBean
@Filters(@By(type=AdminFilter.class ))
public class ProtectDataMod extends BaseMod{
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/ht/pta")
    @Ok("jsp:jsp.ht.pta")
    public void load(HttpServletRequest req){
		
		
	}
	
	@POST
	@At("/ht/ptaSave")
    @Ok("json")
	public JSONObject save(@Param("..")ProtectDate pd  ,HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		try {
			//姑且只有js验证
			if(null != pd){
				
				if(StringUtil.checkNotNull(pd.getDataTable() ) ){ 
					if(StringUtil.checkNotNull(pd.getId())){
						//System.out.println(pd.getId() + " <");
						baseService.dao.update(pd) ;
					}else{
						//System.out.println(pd.getId() + " <<");
						pd.setId(String.valueOf(System.currentTimeMillis())) ;
						baseService.dao.insert(pd) ;
					}
					json.put(Constant.SUCCESS, true) ;
				}else{
					json.put(Constant.INFO, "DataTable字段为空") ;
				}
			}else{
				json.put(Constant.INFO, "参数为空") ;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put(Constant.INFO, e.getMessage()) ;
		}finally{
			return json ;
		}
	}
	
	@POST
	@At("/ht/ptalist")
    @Ok("json")
	public JSONObject list(@Param("..")Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;

		List<ProtectDate>  list = baseService.dao.query(ProtectDate.class, null, page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(ProtectDate.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "station", "dataSDate" , "dataEDate" ,"publicDate" }); 
		JSONArray array = new JSONArray();
		
		for(ProtectDate g : list ){
			JSONObject item = new JSONObject();
			
			item = JSONObject.fromObject(g , cfg) ;
			item.put("dataSDate", null == g.getDataSDate() ? "" : DateUtil.convertDateToString(g.getDataSDate()  , DateUtil.pattern0)) ;
			item.put("dataEDate", null == g.getDataEDate() ? "" : DateUtil.convertDateToString(g.getDataEDate()  , DateUtil.pattern0)) ;
			item.put("publicDate", null == g.getPublicDate() ? "" : DateUtil.convertDateToString(g.getPublicDate()  , DateUtil.pattern0)) ;
			
			//获取观测站名
			Station sa = new Station();
			sa.setId(g.getDataStation()) ;
			sa = baseService.dao.fetch(sa) ;
			if(null != sa){
				item.put("stationName", sa.getName()) ;
			}else{
				item.put("stationName", "无") ;
			}
			
			item.put("dataTableFull", this.tableMap.get(g.getDataTable())) ;
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	@POST
	@At("/ht/ptadel")
    @Ok("json")
	public JSONObject deletePta(String ids , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(ids) ){
			String[] idss = ids.split(";") ;
			
			List<ProtectDate> igs = new ArrayList<ProtectDate>() ;
			for (String id : idss) {
				ProtectDate n = new ProtectDate();
				n.setId(id) ;
				
				n = baseService.dao.fetch(n) ;
				igs.add(n) ;
			}
			
			if(null != igs && igs.size() > 0 ){
				if( baseService.dao.delete(igs) == igs.size() ){
					json.put(Constant.SUCCESS, true) ;
				}else{
					json.put(Constant.INFO, error1) ;
				}
				//log.info(igs.size()) ;
			}else{
				json.put(Constant.INFO, error2) ;
			}
			
		}else{
			json.put(Constant.INFO, error3) ;
		}
		
		return json ;
	}
	
	@POST
	@At("/ht/ptaupdate")
    @Ok("json")
	public JSONObject updatePta(@Param("..")ProtectDate params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getId() ) && null != baseService.dao.fetch(params)){
			int  i = baseService.dao.update(params) ;
			json.put(Constant.SUCCESS, true ) ;
		}else{
			json.put(Constant.INFO, error2) ;
		}
		return json ;
	}
	
	
	
	@POST
	@At("/ht/ptaget")
    @Ok("json")
    public ProtectDate get(String id){
		ProtectDate ig = null ;
		if(StringUtil.checkNotNull(id)){
			ig = baseService.dao.fetch(ProtectDate.class, id) ;
		}
		
		return ig ;
	}
}
