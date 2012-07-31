/**
 * 管理前台注册用户的模块
 */
package cn.fam1452.action.ht;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.LoopException;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.dao.pojo.User;
import cn.fam1452.dao.pojo.User;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.UserService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

/**
 * @author zdd
 *
 */
@At("/ht")
@IocBean
public class QTUserMgeMod extends BaseMod{
	
	@Inject("refer:userService")
	private UserService userservice ;
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/qtum")
    @Ok("jsp:jsp.ht.qtum")
    public void loadJsp(HttpServletRequest req){}
	
	@POST
	@At("/qtumlist")
	@Ok("json")
	public JSONObject list( @Param("..")User params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;
		
		Criteria cri = Cnd.cri();
		if(StringUtil.checkNotNull(params.getName())){
			cri.where().and( "loginId", "=", params.getName()) ;
		}
		if(null != params.getRegDate()) {
			cri.where().and( "regDate", ">=", params.getRegDate()) ;
		}
		//log.info(cri.toString()) ;

		List<User>  list = baseService.dao.query(User.class, cri, params.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(User.class , cri)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] {  "regDate"  }); 
		JSONArray array = new JSONArray();
		
		for(User u : list ){
			JSONObject item = new JSONObject();
			
			item = JSONObject.fromObject(u , cfg) ;
			item.put("regDate", DateUtil.convertDateToString(u.getRegDate() , DateUtil.pattern2)) ;
			//item.put("dataEDate", DateUtil.convertDateToString(g.getDataEDate()  , DateUtil.pattern0)) ;
			//item.put("publicDate", DateUtil.convertDateToString(g.getPublicDate()  , DateUtil.pattern0)) ;
			
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	@At("/allusername")
	@Ok("json")
	public JSONArray listAllNameId(){
		final JSONArray array = new JSONArray();
		
		int t = baseService.dao.each(User.class, null, new Each<User>(){
			public void invoke(int index, User ele, int length) throws ExitLoop, ContinueLoop, LoopException {
				// TODO Auto-generated method stub
				JSONObject i = new JSONObject() ;
				i.put("id", ele.getLoginId()) ;
				i.put("name", ele.getName() ) ;
				
				array.add(i) ;
			}
			
		})  ;
		
		return array ;
	}
	
	@POST
	@At("/delqtuser")
    @Ok("json")
	public JSONObject deleteUser(String ids , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(ids) ){
			String[] idss = ids.split(";") ;
			
			List<User> us = new ArrayList<User>() ;
			for (String id : idss) {
				User u = new User();
				u.setLoginId (id) ;
				
				u = baseService.dao.fetch(u) ;
				us.add(u) ;
			}
			
			if(null != us && us.size() > 0 ){
				//log.info("delete user: " + us.size()) ; 
				if( baseService.dao.delete(us) == us.size() ){
					json.put(Constant.SUCCESS, true) ;
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
	@At("/updateqtuser")
    @Ok("json")
	public JSONObject updateUser(@Param("..")User params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getLoginId()) && null != baseService.dao.fetch(params)){
			int  i = baseService.dao.update(params) ;
			json.put(Constant.SUCCESS, true ) ;
		}else{
			json.put(Constant.INFO, error2) ;
		}
		return json ;
	}
	
	
	@At("/downloadAllUser")
	@Ok("raw")
	public void exportUserAndDownload(HttpServletResponse response){
		
		Workbook wb = userservice.exportToHSSFWorkbook() ;
		
		try {
			if(null != wb){
				OutputStream out = response.getOutputStream();
				response.setContentType("application/x-msdownload");
				
				StringBuffer fileName = new StringBuffer().append("user_all.xls") ;
				response.setHeader("Content-Disposition", "attachment; filename=" + new String( fileName.toString().getBytes("GBK"), "ISO8859-1" ));
				
				wb.write(out) ;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e, e) ;
		}
	}
	
	@At("/getuser")
	@Ok("json")
	@POST
	public JSONObject getUser(@Param("..")User obj){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(null != obj && StringUtil.checkNotNull(obj.getLoginId())){
			User u = baseService.dao.fetch(obj) ;
			json.put("user", JSONObject.fromObject(u)) ;
			json.put(Constant.SUCCESS, true) ;
		}else{
			json.put(Constant.INFO, "参数错误") ;
		}
		
		return json ;
	} 
}
