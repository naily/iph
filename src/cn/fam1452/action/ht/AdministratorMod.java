package cn.fam1452.action.ht;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
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
import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.AdministratorService;
import cn.fam1452.service.UserService;
import cn.fam1452.utils.StringUtil;

/**
 * 管理员管理
 * @author zdd
 *
 */
@IocBean
@Filters(@By(type=AdminFilter.class ))
public class AdministratorMod extends BaseMod{
	
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
		
		return json ;
		
	}
	
	@At("/ht/adminsave")
	@Ok("json")
	@POST
	public Map add(@Param("..")Administrator admin){
		Map map = new HashMap<String, String>() ;
		map.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(admin.getLoginId()) && StringUtil.checkNotNull(admin.getPassword())){
			if(adminService.dao.fetch(admin) == null){
				adminService.dao.insert(admin) ;
				map.put(Constant.SUCCESS, true) ;
			}else{
				map.put(Constant.INFO, "该登录名已经存在") ;
			}
		}else{
			map.put(Constant.INFO, "参数错误") ;
		}
		
		return map ;
	}
	
	
	@At("/ht/adminmodifpass")
	@Ok("json")
	@POST
	public JSONObject modifPassword(@Param("..")Administrator admin , HttpSession session){
		JSONObject j = new JSONObject () ;
		j.put(Constant.SUCCESS, false) ;
		
		Administrator user = (Administrator)session.getAttribute(Constant.HT_USER_SESSION) ;
		
		if(null != user && 
				StringUtil.checkNotNull(user.getLoginId()) && 
				StringUtil.checkNotNull(admin.getPassword())){
			
			user.setPassword(admin.getPassword()) ;
			adminService.dao.update(user, "password") ;
			
			j.put(Constant.SUCCESS, true) ;
			j.put(Constant.INFO, "密码修改成功") ;
		}else{
			j.put(Constant.INFO, "请登录") ;
		}
		
		return j ;
	}
	
	@At("/ht/admindel")
	@Ok("json")
	@POST
	public JSONObject delete(@Param("..")Administrator admin){
		JSONObject j = new JSONObject () ;
		j.put(Constant.SUCCESS, false) ;
		
		if(null != admin && StringUtil.checkNotNull(admin.getLoginId())){
			admin = adminService.dao.fetch(admin) ;
			if(!admin.isSuper()){ //超级管理员不可删除
				adminService.dao.delete(admin) ;
			}
			j.put(Constant.SUCCESS, true) ;
		}else{
			j.put(Constant.INFO, "参数错误") ;
		}
		
		return j ;
	}
	
	
	@At("/ht/adminlistall")
	@Ok("json")
	public JSONArray listAllNameId(){
		JSONArray array = new JSONArray();
		
		List<Administrator>  list = adminService.dao.query(Administrator.class, null) ;
		if(null != list && list.size() > 0){
			
			for (Administrator administrator : list) {
				JSONObject i = new JSONObject() ;
				i.put("id", administrator.getLoginId()) ;
				i.put("name", administrator.getLoginId()) ;
				
				array.add(i) ;
			}
		}
		
		return array ;
	}
}
