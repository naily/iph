/**
 * 描述：观测站
 */
package cn.fam1452.action.ht;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.UserService;
import cn.fam1452.utils.StringUtil;

/**
 * Class StationMod
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 31, 2012 9:54:31 PM $
 */
@IocBean
public class StationMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/ht/stationload")
    @Ok("jsp:jsp.ht.stations")
    public void init(HttpServletRequest req){ }
	
	@At("/ht/stationsave")
	@Ok("json")
	@POST
	public JSONObject save(@Param("..")Station obj , String action){
		//log.info(action) ;
		
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if("save".equals(action)){
			//obj.setId(String.valueOf(System.currentTimeMillis()).substring(6) ) ;
			if(StringUtil.checkNotNull(obj.getId())){
				if(null != baseService.dao.insert(obj) ){
					json.put(Constant.SUCCESS, true) ;
				}else{
					json.put(Constant.INFO, "保存失败") ;
				}
			}else{
				json.put(Constant.INFO, "保存失败,观测站编码不能为空") ;
			}
			
			
		}else{
			obj.setId(action) ;
			if(1 == baseService.dao.updateIgnoreNull(obj) ){
				json.put(Constant.SUCCESS, true) ;
			}else{
				json.put(Constant.INFO, "更新失败") ;
			}
		}
		
		return json ;
	}
	
	@At("/ht/stationupdate")
	@Ok("json")
	@POST
	public JSONObject update(@Param("..")Station obj){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		baseService.dao.updateIgnoreNull(obj) ;
		
		return json ;
	}
	
	@At("/ht/stationlist")
	@Ok("json")
	public JSONObject list(@Param("..")Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;
		List<Station>  list = baseService.dao.query(Station.class, Cnd.orderBy().desc("id"), page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(Station.class)) ;
		
		JsonConfig cfg = new JsonConfig();  		
		cfg.setExcludes(new String[] { "code"}); 
		json.put(Constant.ROWS, JSONArray.fromObject(list)) ;
		return json ;
	}
	
	@At("/ht/stationdel")
	@Ok("json")
	@POST
	public JSONObject delete(@Param("..")Station obj){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(null != obj && StringUtil.checkNotNull(obj.getId())){
			baseService.dao.delete(obj) ;
		}else{
			json.put(Constant.INFO, "参数错误") ;
		}
		
		return json ;
	}
	
	@At("/ht/getstation")
	@Ok("json")
	@POST
	public JSONObject get(@Param("..")Station obj){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(null != obj && StringUtil.checkNotNull(obj.getId())){
			Station st = baseService.dao.fetch(obj) ;
			json.put("data", JSONObject.fromObject(st)) ;
			json.put(Constant.SUCCESS, true) ;
		}else{
			json.put(Constant.INFO, "参数错误") ;
		}
		
		return json ;
	}
	
	/**
	 * 所有观测站名字和Id对
	 * @Author Derek
	 * @Date Jun 10, 2012
	 * @return
	 */
	@At("/ht/stationlistall")
	@Ok("json")
	public JSONArray listAllNameId(){
		JSONArray array = new JSONArray();
		
		List<Station>  list = baseService.dao.query(Station.class, Cnd.orderBy().desc("id")) ;
		if(null != list && list.size() > 0){
			JsonConfig cfg = new JsonConfig();  		
			cfg.setExcludes(new String[] { "location" , "longitude","latitude","timeZone","introduction","administrator","address","zipCode","phone","email","homepage","picPath"}); 
			array = JSONArray.fromObject(list , cfg) ;
		}
		
		return array ;
	}
}
