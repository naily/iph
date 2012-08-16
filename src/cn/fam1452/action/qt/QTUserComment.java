package cn.fam1452.action.qt;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.User;
import cn.fam1452.dao.pojo.UserComment;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.GetIP;
import org.apache.commons.beanutils.PropertyUtils;
@IocBean
public class QTUserComment extends BaseMod{
	@Inject("refer:baseService")
	private BaseService baseService ;
	@At("/qt/userComment")
    @Ok("json")
    /*首页三条元数据*/
    public JSONObject userCommnet(@Param("..")UserComment userComment,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		UserComment dbComment = new UserComment();
		try {
			PropertyUtils.copyProperties(dbComment, userComment);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(session.getAttribute(Constant.QT_USER_SESSION)!=null){
			User user = (User)session.getAttribute(Constant.QT_USER_SESSION);
			dbComment.setId(String.valueOf(System.currentTimeMillis()));//
			dbComment.setUserId(user.getLoginId());
			dbComment.setCommentDate(DateUtil.getCurrentDate());
			dbComment.setUerIP(GetIP.getIpAddr(request));
			log.info(dbComment.getId());
			baseService.dao.insert(dbComment);
			json.put(Constant.SUCCESS, true);
		}
		
		return json ;
	}

}
