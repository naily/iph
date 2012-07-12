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
		log.info("code=="+code);
		log.info("user.getCode="+user.getCode());
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
}
