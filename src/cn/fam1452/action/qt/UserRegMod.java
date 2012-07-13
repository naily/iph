/**
 * 描述：前台用户注册与登录，提供密码找回。
 * 
 */
package cn.fam1452.action.qt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.User;
import cn.fam1452.service.UserService;
import cn.fam1452.util.SendMail;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;
@IocBean
public class UserRegMod extends BaseMod{
	@Inject("refer:userService")
	private UserService userservice ;

	
	@At("/qt/userReg")
	@Ok("json")
	@POST
	public JSONObject userReg(HttpSession session ,HttpServletRequest req,@Param("..")User user){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		String code = (String)session.getAttribute(Constant.LOGIN_VALIDATE_STRING) ;
		if(StringUtil.checkNotNull(user.getCode()) && user.getCode().equalsIgnoreCase(code)){	
		if(StringUtil.checkNotNull(user.getLoginId()) && StringUtil.checkNotNull(user.getPassword())){
			user.setRegDate(DateUtil.getCurrentDate());
			if(userservice.dao.fetch(user) == null){
				userservice.dao.insert(user) ;
				json.put(Constant.SUCCESS, true) ;
			}else{
				json.put(Constant.INFO, "该登录名已经存在") ;
			}
		}else{
			json.put(Constant.INFO, "参数错误") ;
		}
		}else{
			json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_codeerror")) ;
		}
		return json ;
	}
	
	@At("/qt/userLogin")
	@Ok("json")
	@POST
	public JSONObject userLogin(HttpSession session ,HttpServletRequest req,@Param("..")User user){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		//String code = (String)session.getAttribute(Constant.LOGIN_VALIDATE_STRING) ;
		//if(StringUtil.checkNotNull(user.getCode()) && user.getCode().equalsIgnoreCase(code)){
			
			if(StringUtil.checkNotNull(user.getLoginId()) &&
					StringUtil.checkNotNull(user.getPassword())){
				User db = userservice.queryUser(user.getLoginId()) ;
				if(null == db ){
					json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_nameerror")) ;
				} else{
					if(db.getPassword().equals(user.getPassword())){
						json.put(Constant.SUCCESS, true) ;
						db.setLogin(true) ;
						session.setAttribute(Constant.QT_USER_SESSION, db) ;
					}else{
						json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_passerror")) ;
					}
				}
					
			}
		/*}else{
			json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_codeerror")) ;
		}*/
		
		
		return json;
	}
	
	@At("/qt/logout")
	@Ok("redirect:/index.jsp")
	public void logout(HttpSession session) {
		session.removeAttribute(Constant.QT_USER_SESSION);
	}
	
	@At("/qt/getPassword")
	@Ok("json")
	public JSONObject getPassword(@Param("..")User user) {	
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		SendMail themail = new SendMail("smtp.163.com");
		themail.setNeedAuth(true);
		themail.setSubject("找回密码");
		themail.setBody("内容");
		themail.setTo("gelishun2005@163.com");
		themail.setFrom("gelishun2005@163.com");
		themail.setNamePass("gelishun2005", "********");
		if (themail.sendout() == false){
			
		}else{
			
		}
		
		return json;
	}
	
}
