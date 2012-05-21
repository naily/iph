/**
 * 描述：
 */
package cn.fam1452.action.ht;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.service.UserService;
import cn.fam1452.utils.StringUtil;

/**
 * Class User
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 18, 2012 10:59:23 PM $
 */
@IocBean
public class UserMod  extends BaseMod{
	
	@Inject("refer:userService")
	private UserService userservice ;

	@At("/ht/index")
    @Ok("jsp:jsp.ht.login")
    public void loadlogin(HttpServletRequest req){
		
	}
	
	@At("/ht/lang")
	@Ok("jsp:jsp.ht.login")
    public void lang(HttpSession session, String lang){
		Mvcs.setLocaleName(session, "en_US") ;
		Mvcs.setLocale(session , Mvcs.MSG) ;
		return  ;
	}
	/**
	 * 登录验证码
	 */
	@At("/logincode")
	@Ok("jsp:jsp.code")
	public void loginCode(){}
	/**
	 * 用户登出 : http://localhost:8080/hellomvc/pet/logout.nut
	 */
	@At
	@Ok("redirect:/ht/index.do")
	public void logout(HttpSession session) {
		session.removeAttribute(Constant.HT_USER_SESSION);
	}
	
	@At("/ht/login")
	@POST
	@Ok("json")
	public JSONObject login(HttpSession session ,HttpServletRequest req, @Param("..")Administrator admin){
		//Map<String,String> map = (Map<String,String>)req.getAttribute ("msg") ;
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		if(StringUtil.checkNotNull(admin.getCode())){
			
			if(StringUtil.checkNotNull(admin.getLoginId()) &&
					StringUtil.checkNotNull(admin.getPassword())){
				Administrator db = userservice.queryAdmin(admin.getLoginId()) ;
				if(null == db ){
					json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_nameerror")) ;
				} else{
					if(db.getPassword().equals(admin.getPassword())){
						json.put(Constant.SUCCESS, true) ;
						session.setAttribute(Constant.HT_USER_SESSION, db) ;
					}else{
						json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_passerror")) ;
					}
				}
					
			}
		}else{
			json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_codeerror")) ;
		}
		
		
		return json;
	}
}
