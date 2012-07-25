/**
 * 
 */
package cn.fam1452.action.qt;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.MetaData;
import cn.fam1452.service.BaseService;

/**
 * @author 
 *
 */
@IocBean
public class QTMetaDataMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	

	@At("/qt/mataDataIndex")
    @Ok("json")
    public JSONObject loadIndexMetaData(){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		List<MetaData>  list = baseService.dao.query(MetaData.class,  Cnd.orderBy().desc("mdId"), baseService.dao.createPager(1, Constant.INDEX_META_NUMS)) ;
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "thumbnail", "fullContent" , "mdDate"  }); 
		JSONArray jsonAry = JSONArray.fromObject(list , cfg);
		log.info("metaData="+jsonAry.toString());
		if(list!=null && list.size()>0){
			json.put("metaList", jsonAry);
			json.put(Constant.SUCCESS, true);
		}		
		return json ;
	}

	@At("/qt/metaDataPriview")
    @Ok("jsp:jsp.qt.metaDataPriview")
    public MetaData metaDataPriview(HttpServletRequest req,String mdId){
		MetaData metaData = new MetaData();
		metaData = baseService.dao.fetch(MetaData.class, mdId);
		return metaData;	
	}
	@At("/qt/metaDataList")
    @Ok("jsp:jsp.qt.metaDataList")
    public void metaDataList(HttpServletRequest req,HttpServletResponse res,String title){
		try {
			req.setCharacterEncoding("UTF-8");
			res.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<MetaData>  list = baseService.dao.query(MetaData.class,  Cnd.where("title", "like", "%"+title+"%")) ;
		req.setAttribute("metaDataList", list);
		
	}
//	
}

