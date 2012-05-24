package cn.fam1452.action.ht;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.service.AdministratorService;
import cn.fam1452.service.UserService;
import cn.fam1452.utils.StringUtil;

/**
 * 管理员管理
 * @author zdd
 *
 */
@IocBean
public class AdministratorMod {
	
	@Inject("refer:adminService")
	private AdministratorService adminService ;
	
	@At("/ht/admins")
	@Ok("jsp:jsp.ht.admins")
	public void load(){ }
	
	@At("/ht/adminlist")
	@Ok("json")
	public JSONObject list( @Param("..")Pages page ){
		JSONObject json  = new JSONObject();
		List<Administrator>  list = adminService.dao.query(Administrator.class, Cnd.orderBy().desc("loginId"), page.getNutzPager()) ;
		json.put(Constant.TOTAL, adminService.dao.count(Administrator.class)) ;
		
		JsonConfig cfg = new JsonConfig();  		
		cfg.setExcludes(new String[] { "code", "login","password" }); 
		json.put(Constant.ROWS, JSONArray.fromObject(list, cfg)) ;
		
		System.out.println(json.toString());
		return json ;
		
	}
	
	@At("/ht/adminsave")
	@Ok("json")
	public Map add(Administrator admin){
		Map map = new HashMap<String, String>() ;
		map.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(admin.getLoginId()) && StringUtil.checkNotNull(admin.getPassword())){
			adminService.dao.insert(admin) ;
			map.put(Constant.SUCCESS, true) ;
		}else{
			map.put(Constant.INFO, "参数错误") ;
		}
		
		return map ;
	}
	
	public void modifPassword(){
		
	}
	
	public void delete(){
		
	}
}
