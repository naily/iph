/**
 * 
 */
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
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.filter.UserFilter;
import cn.fam1452.dao.pojo.MetaData;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataVisitService;
import cn.fam1452.utils.GetIP;
import cn.fam1452.utils.StringUtil;

/**
 * @author 
 *
 */
@IocBean
//@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
public class QTMetaDataMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@Inject("refer:dataVisitService")
	private DataVisitService dvs ;

	
	@At("/qt/mataDataIndex")
    @Ok("json")
    /*首页三条元数据*/
    public JSONObject loadIndexMetaData(HttpServletRequest req) {
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		List<MetaData> list = baseService.dao.query(MetaData.class, Cnd.orderBy().desc("mdId"), baseService.dao.createPager(1,
				Constant.INDEX_META_NUMS));
		JsonConfig cfg = new JsonConfig();
		cfg.setExcludes(new String[] { "thumbnail", "fullContent", "mdDate" });

		if (list != null && list.size() > 0) {
			String zh_en = this.getMsgByKey(req, "lang");
			JSONArray jsonAry = null;
			if ("en".equals(zh_en)) {
				List<MetaData> listNew = new ArrayList<MetaData>();
				for (MetaData md : list) {
					md.setTitle(md.getTitleEng());
					if(StringUtil.checkNotNull(md.getSummaryEng()) && md.getSummaryEng().length() > 90){
						md.setSummary(md.getSummaryEng().substring(0, 86) + "..." );
						md.setSummaryEng(md.getSummaryEng().substring(0, 86) + "..." );
					}else{
						md.setSummary(md.getSummaryEng());
					}
					listNew.add(md);
				}
				jsonAry = JSONArray.fromObject(listNew, cfg);
			} else {
				List<MetaData> listNew = new ArrayList<MetaData>();
				for (MetaData md : list) {
					if(StringUtil.checkNotNull(md.getSummary()) && md.getSummary().length() > 66){
						md.setSummary(md.getSummary().substring(0, 65) + "..." );
					}else{
						md.setSummary(md.getSummary());
					}
					listNew.add(md);
				}
				
				jsonAry = JSONArray.fromObject(listNew, cfg);
			}

			json.put("metaList", jsonAry);
			json.put(Constant.SUCCESS, true);
		}
		// log.info(json.toString());
		return json;
	}

	@At("/qt/metaDataPriview")
    @Ok("jsp:jsp.qt.metaDataPriview")
      /*元数据详细信息浏览*/
    public MetaData metaDataPriview(HttpServletRequest req,HttpSession session,String mdId){
		MetaData metaData = new MetaData();
		metaData = baseService.dao.fetch(MetaData.class, mdId);
		if(!"".equals(getQTLoginUserID())){
			dvs.insertMetaData("02", 1, getQTLoginUserID(),GetIP.getIpAddr(req),0f);
		}
		return metaData;	
	}
	
	/**
	 * 元数据查询
	 * */
	@At("/qt/metaDataList")
    @Ok("jsp:jsp.qt.metaDataList")
    public void metaDataList(HttpServletRequest req,HttpSession session,@Param("..")Pager page,String title){
		page.setPageSize(Constant.META_DATA_PAGESIZE);//默认分页记录数
		Pager pager = baseService.dao.createPager(page.getPageNumber(), page.getPageSize());   
		
		Cnd cnd = null ;
		if(StringUtil.checkNotNull(title)){
			cnd =  Cnd.where("title", "like", "%"+title+"%").or("keyword","like", "%"+title+"%").or("summary","like", "%"+title+"%") ;
		}else{
			
		}
		
		List<MetaData>  list = baseService.dao.query(MetaData.class, cnd , pager ) ;
		pager.setRecordCount(baseService.dao.count(MetaData.class, cnd ));
		req.setAttribute("metaDataList", list);
		req.setAttribute("page", pager);
		req.setAttribute("keyword", title);
		
		int records = list.size();
		if(!"".equals(getQTLoginUserID())){
			dvs.insertMetaData("01", records, getQTLoginUserID(),GetIP.getIpAddr(req),0f);
		}
		
	}	
}

