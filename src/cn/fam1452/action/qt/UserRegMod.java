/**
 * 描述：前台用户注册与登录，提供密码找回。
 * 
 */
package cn.fam1452.action.qt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.nutz.ioc.impl.PropertiesProxy;
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
import cn.fam1452.utils.SendMail;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;
@IocBean
public class UserRegMod extends BaseMod{
	@Inject("refer:userService")
	private UserService userservice ;

	@Inject("refer:config")
	private PropertiesProxy config ;
	
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
	public JSONObject getPassword(HttpServletRequest req,@Param("..")User user) {	
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		
		if(StringUtil.checkNotNull(user.getLoginId()) &&
				StringUtil.checkNotNull(user.getEmail())){
			User db = userservice.queryUser(user.getLoginId()) ;
			if(null == db ){
				json.put(Constant.INFO, this.getMsgByKey(req, "ht_login_nameerror")) ;
			} else{

				String smtp =config.get("smtp");//邮件服务器
				String subjectTitle=config.get("subjectTitle");//邮件标题
				String subjectBody =config.get("subjectBody");//邮件内容
				String mailTo =db.getEmail();//收件人
				String mailForm=config.get("mailAdderss");//发件邮箱
				String userName=config.get("userName");//发件邮箱用户名
				String passowrd=config.get("password");//发件邮箱密码
				
				
				SendMail themail = new SendMail(smtp);
				themail.setNeedAuth(true);
				themail.setSubject(subjectTitle);
				themail.setBody(subjectBody);
				themail.setTo(mailTo);
				themail.setFrom(mailForm);
				themail.setNamePass(userName, passowrd);
				if (themail.sendout() == false){
					json.put(Constant.INFO, "密码找回失败,请确认邮箱是否正确") ;
					log.info("false");
				}else{
					json.put(Constant.SUCCESS, true);
					log.info("success");
					json.put(Constant.INFO, "密码已发送至您的邮箱，请查收") ;
				}			
			}
				
		}
	
		return json;
	}
	
}