/**
 * 描述：电离层参数管理
 */
package cn.fam1452.action.ht;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
import cn.fam1452.dao.pojo.Parameter;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataLogService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

/**
 * Class ParameterMod
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jun 24, 2012 10:04:31 AM $
 */
@IocBean
public class ParameterMod extends BaseMod{
	
	@At("/ht/pam")
    @Ok("jsp:jsp.ht.pam")
	public void loadSavePages(){}
	
	@At("/ht/pamlist")
    @Ok("jsp:jsp.ht.pamlist")
	public void loadListPages(){}
	
	@Inject("refer:dataLogService")
	private DataLogService dls ;
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	private static final String tableName = "T_PARAMETER" ;
	
	@POST
	@At("/ht/pamget")
    @Ok("json")
    public Parameter get(String id){
		Parameter sp = null ;
		if(StringUtil.checkNotNull(id)){
			sp = baseService.dao.fetch(Parameter.class, id) ;
		}
		
		return sp ;
	}
	
	@POST
	@At("/ht/pamlist")
    @Ok("json")
	public JSONObject list(@Param("..")Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;

		List<Parameter>  list = baseService.dao.query(Parameter.class, null, page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(Parameter.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "station" , "createDate"}); 
		JSONArray array = new JSONArray();
		
		for(Parameter pm : list ){
			JSONObject item = JSONObject.fromObject(pm , cfg);
			
			Station sa = new Station();
			sa.setId(pm.getStationID()) ;
			sa = baseService.dao.fetch(sa) ;
			if(null != sa){
				item.put("stationName", sa.getName()) ;
			}else{
				item.put("stationName", "无") ;
			}
			
			item.put("ID", pm.getParameterID()) ;
			
			item.put("createDate" , DateUtil.convertDateToString(pm.getCreateDate(), DateUtil.pattern2));
			
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	
	@POST
	@At("/ht/pamdel")
    @Ok("json")
	public JSONObject deleteParameter(@Param("..")Parameter params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getIds())){
			String[] ids = params.getIds().split(";") ;
			
			List<Parameter> igs = new ArrayList<Parameter>() ;
			for (String id : ids) {
				Parameter sa = new Parameter();
				sa.setParameterID(id) ;
				
				sa = baseService.dao.fetch(sa) ;
				
				if(null != sa){
					igs.add(sa) ;
				}
			}
			
			if(null != igs && igs.size() > 0 ){
				if( baseService.dao.delete(igs) == igs.size() ){
					json.put(Constant.SUCCESS, true) ;
					
					dls.insert("03", tableName, "admin") ;
				}else{
					json.put(Constant.INFO, error1) ;
				}
			}else{
				json.put(Constant.INFO, error2) ;
			}
			
		}else{
			json.put(Constant.INFO, error3) ;
		}
		
		return json ;
	}
	
	
	@POST
	@At("/ht/pamupdate")
    @Ok("json")
	public JSONObject updatePgt(@Param("..")Parameter params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getParameterID()) && null != baseService.dao.fetch(params)){
			int  i = baseService.dao.update(params) ;
			json.put(Constant.SUCCESS, true ) ;
			
			dls.insert("02", tableName, "admin") ;
		}else{
			json.put(Constant.INFO, error2) ;
		}
		return json ;
	}
	
	@POST
	@At("/ht/pamsave")
    @Ok("json")
	public JSONObject save(@Param("..")Parameter params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		try{
			if(StringUtil.checkNotNull(params.getStationID()) ){
				int s = baseService.dao.count(Parameter.class, Cnd.where("stationID", "=", params.getStationID()).and("createDate", "=", params.getCreateDate()) ) ;
				
				StringBuilder id = new StringBuilder();
				id.append(params.getStationID());
				id.append(DateUtil.convertDateToString(params.getCreateDate(), "yyyyMMddHH") );
				
				String patt = "00" ;  
				DecimalFormat nf  =  new DecimalFormat(patt);
				id.append(nf.format(s)) ;
				
				params.setParameterID(id.toString()) ;
			}else{
				json.put(Constant.INFO, "请选择观测站") ;
			}
			
			if(StringUtil.checkNotNull(params.getParameterID()) && null == baseService.dao.fetch(params)){
				baseService.dao.insert(params) ;
				json.put(Constant.SUCCESS, true ) ;
				
				dls.insert("01", tableName, "admin") ;
			}else{
				json.put(Constant.INFO, error7) ;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			json.put(Constant.INFO, e.getMessage()) ;
		}finally{
			return json ;
		}
	}
}
