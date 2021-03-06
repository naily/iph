/**
 * 管理前台前台用户发的评论、或者恢复评论
 */
package cn.fam1452.action.ht;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.filter.AdminFilter;
import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.dao.pojo.FeedBack;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.dao.pojo.User;
import cn.fam1452.dao.pojo.User;
import cn.fam1452.dao.pojo.UserComment;
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
@Filters(@By(type=AdminFilter.class ))
public class QTCommentMgeMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/qtcm")
    @Ok("jsp:jsp.ht.qtcm")
    public void loadJsp(HttpServletRequest req){}
	
	@POST
	@At("/qtcmlist")
	@Ok("json")
	public JSONObject list( @Param("..")UserComment params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;
		
		Criteria cri = Cnd.cri();
		/*if(StringUtil.checkNotNull(params.getName())){
			cri.where().and( "loginId", "=", params.getName()) ;
		}
		if(null != params.getRegDate()) {
			cri.where().and( "regDate", ">=", params.getRegDate()) ;
		}*/
		//log.info(cri.toString()) ;

		List<UserComment>  list = baseService.dao.query(UserComment.class, cri, params.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(UserComment.class , cri)) ;
		
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
		return json ;
	}
	
	
	@POST
	@At("/delqtcm")
    @Ok("json")
	public JSONObject deleteComment(String ids , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(ids) ){
			String[] idss = ids.split(";") ;
			
			List<UserComment> ucs = new ArrayList<UserComment>() ;
			for (String id : idss) {
				UserComment uc = new UserComment();
				uc.setId(id) ;
				
				uc = baseService.dao.fetch(uc) ;
				ucs.add(uc) ;
			}
			
			if(null != ucs && ucs.size() > 0 ){
				//log.info("delete user: " + us.size()) ; 
				if( baseService.dao.delete(ucs) == ucs.size() ){
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
	
	@At("/getqtcm")
	@Ok("json")
	@POST
	public JSONObject getComment(@Param("..")UserComment obj){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(null != obj && StringUtil.checkNotNull(obj.getId())){
			UserComment u = baseService.dao.fetch(obj) ;
			JsonConfig cfg = new JsonConfig(); 
			cfg.setExcludes(new String[] {  "commentDate"  }); 
			
			json.put("obj", JSONObject.fromObject(u , cfg)) ;
			if(StringUtil.checkNotNull(u.getId())){
				List<FeedBack> list = baseService.dao.query(FeedBack.class, Cnd.where("commentId", "=", u.getId())) ;
			
				if(null != list && list.size() > 0){
					JsonConfig cfg1 = new JsonConfig(); 
					cfg1.setExcludes(new String[] {  "comment","admin","feedbackDate"  }); 
					json.put("feedbackarray", JSONArray.fromObject(list , cfg1)) ;
				}
			}
			
			json.put(Constant.SUCCESS, true) ;
		}else{
			json.put(Constant.INFO, "参数错误") ;
		}
		
		return json ;
	} 
	
	@At("/savefeedback")
	@Ok("json")
	@POST
	public JSONObject saveFeedBack(@Param("..")FeedBack obj , HttpSession session){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(null != obj ){
			Administrator admin = (Administrator)session.getAttribute(Constant.HT_USER_SESSION) ;
			if(null != admin){
				obj.setAdminId(admin.getLoginId()) ;
			}
			
			obj.setId(String.valueOf(System.currentTimeMillis())) ;
			obj.setFeedbackDate(DateUtil.getCurrentDate()) ;
			
			FeedBack f = baseService.dao.insert(obj) ;
			json.put(Constant.SUCCESS, true) ;
		}else{
			json.put(Constant.INFO, error8) ;
		}
		
		return json ;
	} 
	
	/**
	 * 查询评论的所有回复
	 * @param cid 评论的ID
	 * @return
	 */
	public JSONObject getCommentFeedBackList(String cid){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		
		return json ;
	}
	
	/**
	 * 
	 * @param fid 按id查看每条回复详情
	 * @return
	 */
	public JSONObject getFeedBackById(String fid){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		
		return json ;
	}
}
