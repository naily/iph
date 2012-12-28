package cn.fam1452.action.qt;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
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
import cn.fam1452.utils.DateJsonValueProcessor;
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
		News news = baseService.dao.fetch(News.class, Cnd.where("isPicNews", "=", true).and("category", "=", "1").desc("newsId")) ;
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
		List<News> list = baseService.dao.query(News.class, Cnd.where("category", "=", "1"), pager); 		
		pager.setRecordCount(baseService.dao.count(News.class)); 
		req.setAttribute("newslist", list);
		req.setAttribute("page", pager);
	}
	
	@At("/qt/newspreview")
    @Ok("jsp:jsp.qt.news")
    /*新闻三级内容*/
    public News loadNewsPreview(HttpServletRequest req,long newsId){
		News news = new News() ;
		if( newsId > 0){
			news = baseService.dao.fetch(News.class, newsId) ;
		}
		return news ;
	}
	
	
	@At("/qt/indexNewsList")
    @Ok("json")
    public JSONObject newsListss(){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		List<News> newslist = baseService.dao.query(News.class, 
				Cnd.where("category", "=", "1").desc("newsId"),
				baseService.dao.createPager(1, Constant.INDEX_NEWS_NUMS)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm")); 
		cfg.setExcludes(new String[] { "content","picture"  }); // "publishDate" , 
		

		if(newslist!=null && newslist.size()>0){
			List<News> list = new ArrayList() ;
			for (News n : newslist) {
				if(n.getTitle().length() > 16){
					n.setTitle(n.getTitle().substring(0, 14) + "....") ;
				}
				list.add(n) ;
			}
			JSONArray jsonAry = JSONArray.fromObject(list , cfg);
			json.put(Constant.SUCCESS, true);
			json.put("newsList", jsonAry);
			json.put("newsnum", newslist.size());
		}else{
			
		}	
		//System.err.println(jsonAry.toString());
		//log.info(json12.toString());
		return json;
	}
	
	/**
	 * 首页展示空间新闻列表
	 * @return
	 */
	@At("/qt/indexshowspacenews")
	@Ok("json")
	public JSONObject indexShowSpaceNews(){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		List<News> newslist = baseService.dao.query(News.class, 
				Cnd.where("category", "=", "2").desc("newsId"),
				baseService.dao.createPager(1, Constant.INDEX_NEWS2_NUMS)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm")); 
		cfg.setExcludes(new String[] { "content","picture"  }); // "publishDate" , 
		
		JSONArray jsonAry = JSONArray.fromObject(newslist , cfg);
		
		if(newslist!=null && newslist.size()>0){
			json.put(Constant.SUCCESS, true);
			json.put("newsList", jsonAry);
			json.put("newsnum", newslist.size());
		}else{ }	
		
		return json;
	}
}
