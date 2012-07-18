package cn.fam1452.action.qt;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.News;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.StringUtil;

@IocBean
public class QTNewsMod extends BaseMod{
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	
	@At("/qt/indexPicNews")
    @Ok("json")
    /*首页新闻图片*/
    public JSONObject loadPicNews(HttpServletRequest req){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		News news = baseService.dao.fetch(News.class, Cnd.where("isPicNews", "=", true).desc("newsId")) ;
		if(null!=news){
			String dbNewsContent =news.getContent();//
			String  pageNewsContent=StringUtil.splitAndFilterString(dbNewsContent, 20);
			if(StringUtil.checkNotNull(pageNewsContent)){
				pageNewsContent=pageNewsContent.trim();
			}			
			json.put(Constant.SUCCESS, true);
			if(StringUtil.checkNotNull(dbNewsContent)){
				json.put("imgStr", StringUtil.GetFirstImages(dbNewsContent));			
				json.put("newsBrief", pageNewsContent);
				json.put("newsId", news.getNewsId());
			}else{
				json.put("imgStr", "");
				json.put("newsBrief", "");
				json.put("newsId", "");
			}		
		}
		return json ;
	}
	

	@At("/qt/newslist")
    @Ok("jsp:jsp.qt.newslist")
    /*新闻二级列表*/
    public void loadNewsList(HttpSession session ,HttpServletRequest req,@Param("..")Pager page){
		page.setPageSize(Constant.PAGE_SIZE);//默认分页记录数
		Pager pager = baseService.dao.createPager(page.getPageNumber(), page.getPageSize());       
		List<News> list = baseService.dao.query(News.class, null, pager); 		
		pager.setRecordCount(baseService.dao.count(News.class)); 
		req.setAttribute("newslist", list);
		req.setAttribute("page", pager);
	}
	
	@At("/qt/newspreview")
    @Ok("jsp:jsp.qt.news")
    /*新闻三级内容*/
    public News loadNewsPreview(HttpServletRequest req,String newsId){
		News news = new News() ;
		if(StringUtil.checkNotNull(newsId)){
			news = baseService.dao.fetch(News.class, newsId) ;
		}
		return news ;
	}
	
	
	@At("/qt/indexNewsList")
    @Ok("json")
    public String newsListss(){
		JSONObject json12 = new JSONObject();
		json12.put(Constant.SUCCESS, false) ;
		List newslist = baseService.dao.query(News.class, null,baseService.dao.createPager(1, 10)) ;
		JSONArray jsonAry = JSONArray.fromObject(newslist);
		System.err.println(jsonAry.toString());

		if(newslist!=null && newslist.size()>0){
			json12.put(Constant.SUCCESS, true);
			json12.put("newsList", jsonAry.toString());
			json12.put("newsnum", newslist.size());
		}else{
			
		}	
		log.info(json12.toString());
		return json12.toString();
	}
}
