/**
 * 管理前台注册用户的模块
 */
package cn.fam1452.action.ht;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.User;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.UserService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

/**
 * @author zdd
 *
 */
@IocBean
public class QTUserMgeMod extends BaseMod{
	
	@Inject("refer:userService")
	private UserService userservice ;
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/ht/qtum")
    @Ok("jsp:jsp.ht.qtum")
    public void loadJsp(HttpServletRequest req){}
	
	public JSONObject list( @Param("..")User params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;
		
		Criteria cri = Cnd.cri();
		if(StringUtil.checkNotNull(params.getName())){
			cri.where().and( "name", "=", params.getName()) ;
		}
		/*if(StringUtil.checkNotNull(params.getAdminId())) {
			cri.where().and( "adminId", "=", params.getAdminId()) ;
		}*/
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
}
