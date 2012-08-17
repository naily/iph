package cn.fam1452.action.qt;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.FeedBack;
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
	@At("/qt/userCommentList")
    @Ok("jsp:jsp.qt.usercomment")
    public void loadJsp(HttpServletRequest req){}
	
	@POST
	@At("/qt/loadUserCommentList")
	@Ok("json")
	public JSONObject list( @Param("..")UserComment params,HttpSession session){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;
		if(session.getAttribute(Constant.QT_USER_SESSION)!=null){
			User user = (User)session.getAttribute(Constant.QT_USER_SESSION);
		
		List<UserComment>  list = baseService.dao.query(UserComment.class, Cnd.where("userId", "==", user.getLoginId()), params.getNutzPager()) ;		
		json.put(Constant.TOTAL, baseService.dao.count(UserComment.class ,Cnd.where("userId", "==", user.getLoginId()))) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] {  "commentDate"  }); 
		JSONArray array = new JSONArray();		
		for(UserComment u : list ){
			JSONObject item = new JSONObject();
			
			item = JSONObject.fromObject(u , cfg) ;
			item.put("commentDate", null == u.getCommentDate() ? "" :DateUtil.convertDateToString(u.getCommentDate() , DateUtil.pattern2)) ;
			if(baseService.dao.count(FeedBack.class, Cnd.where("commentId", "=", u.getId())) > 0){
				item.put("cmtstatus", 1) ;
			}else{
				item.put("cmtstatus", 0) ;
			}
			
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		}
		return json ;
	}
	
}
