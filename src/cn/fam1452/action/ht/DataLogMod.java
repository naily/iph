/**
 * 描述：
 */
package cn.fam1452.action.ht;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.dao.pojo.Log;
import cn.fam1452.dao.pojo.ProtectDate;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataLogService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

/**
 * 数据表操作日志记录
 * Class DataLogMod
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jul 18, 2012 10:12:18 PM $
 */
@IocBean
public class DataLogMod extends BaseMod{

	@Inject("refer:dataLogService")
	private DataLogService dls ;
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/ht/dlog")
    @Ok("jsp:jsp.ht.dlog")
    public void load(HttpServletRequest req){ }
	
	
	@POST
	@At("/ht/ptalist")
    @Ok("json")
	public JSONObject list(@Param("..")Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;

		List<Log>  list = baseService.dao.query(Log.class, null, page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(Log.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "admin", "logDate"  }); 
		JSONArray array = new JSONArray();
		
		for(Log g : list ){
			JSONObject item = new JSONObject();
			
			item = JSONObject.fromObject(g , cfg) ;
			//item.put("dataSDate", DateUtil.convertDateToString(g.getDataSDate()  , DateUtil.pattern0)) ;
			//item.put("dataEDate", DateUtil.convertDateToString(g.getDataEDate()  , DateUtil.pattern0)) ;
			//item.put("publicDate", DateUtil.convertDateToString(g.getPublicDate()  , DateUtil.pattern0)) ;
			
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	@POST
	@At("/ht/ptadel")
    @Ok("json")
	public JSONObject delete(String ids , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(ids) ){
			String[] idss = ids.split(";") ;
			
			List<Log> igs = new ArrayList<Log>() ;
			for (String id : idss) {
				Log n = new Log();
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
	public JSONObject update(@Param("..")Log params){
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
	@At("/ht/dlogget")
    @Ok("json")
    public Log get(String id){
		Log ig = null ;
		if(StringUtil.checkNotNull(id)){
			ig = baseService.dao.fetch(Log.class, id) ;
		}
		
		return ig ;
	}
}
