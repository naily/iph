package cn.fam1452.action.qt;


import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.News;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.StringUtil;

@IocBean
public class QTNewsMod extends BaseMod{
	@Inject("refer:baseService")
	private BaseService baseService ;
	

	@At("/qt/news")
    @Ok("jsp:jsp.qt.news")
    public void loadNews(HttpServletRequest req){}
	@At("/qt/newslist")
    @Ok("jsp:jsp.qt.newslist")
    public void loadNewsList(HttpServletRequest req){}
	
	
	@At("/qt/indexPicNews")
    @Ok("json")
    public JSONObject loadPicNews(HttpServletRequest req){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		News news = baseService.dao.fetch(News.class, Cnd.where("isPicNews", "=", true).desc("newsId")) ;
		if(null!=news){
			String dbNewsContent =news.getContent();//
			String  pageNewsContent=StringUtil.splitAndFilterString(dbNewsContent, 30);
			if(StringUtil.checkNotNull(pageNewsContent)){
				pageNewsContent=pageNewsContent.trim();
			}			
			json.put(Constant.SUCCESS, true);
			if(StringUtil.checkNotNull(dbNewsContent)){
				json.put("imgStr", StringUtil.GetFirstImages(dbNewsContent));
				
				json.put("newsBrief", pageNewsContent);
			}else{
				json.put("imgStr", "");
				json.put("newsBrief", "");
			}
			log.info("img="+StringUtil.GetFirstImages(dbNewsContent));
			log.info("cont="+pageNewsContent);
		}
		return json ;
	}
	
	@At("/qt/qtNewsList")
    @Ok("json")
    public JSONObject newsList(HttpServletRequest req){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		News news = baseService.dao.fetch(News.class, Cnd.where("isPicNews", "=", true).desc("newsId")) ;
		if(null!=news){
			String dbNewsContent =news.getContent();//
			String  pageNewsContent=StringUtil.splitAndFilterString(dbNewsContent, 30);
			if(StringUtil.checkNotNull(pageNewsContent)){
				pageNewsContent=pageNewsContent.trim();
			}			
			json.put(Constant.SUCCESS, true);
			if(StringUtil.checkNotNull(dbNewsContent)){
				json.put("imgStr", StringUtil.GetFirstImages(dbNewsContent));
				
				json.put("newsBrief", pageNewsContent);
			}else{
				json.put("imgStr", "");
				json.put("newsBrief", "");
			}
			log.info("img="+StringUtil.GetFirstImages(dbNewsContent));
			log.info("cont="+pageNewsContent);
		}
		return json ;
	}
}
