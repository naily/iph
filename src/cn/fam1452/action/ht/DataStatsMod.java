/**
 * 描述：数据服务统计
 */
package cn.fam1452.action.ht;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.filter.AdminFilter;
import cn.fam1452.dao.pojo.DataService;
import cn.fam1452.dao.pojo.Log;
import cn.fam1452.dao.pojo.ProtectDate;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataLogService;
import cn.fam1452.service.DataVisitService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

/**
 * 
 * Class DataStatsMod
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jul 22, 2012 12:39:42 PM $
 */
@IocBean
@Filters(@By(type=AdminFilter.class ))
public class DataStatsMod extends BaseMod{

	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@Inject("refer:dataVisitService")
	private DataVisitService dvs  ;
	
	@At("/ht/dstats")
    @Ok("jsp:jsp.ht.dStats")
    public void load(HttpServletRequest req){ }
	
	//详细数据
	@POST
	@At("/ht/dataStatsList")
    @Ok("json")
	public JSONObject list( @Param("..")DataService params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;
		
		Criteria cri = Cnd.cri();
		if( StringUtil.checkNotNull(params.getActionType())){
			//params.setActionType("01") ; //设置默认类别为 '查询'
			cri.where().and( "actionType", "=", params.getActionType()) ;
		} 
		
		/*if(StringUtil.checkNotNull(params.getAdminId())) {
			cri.where().and( "adminId", "=", params.getAdminId()) ;
		}*/
		
		//log.info(cri.toString()) ;

		cri.getOrderBy().desc("serviceDate") ;
		List<DataService>  list = baseService.dao.query(DataService.class, cri, params.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(DataService.class , cri)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "admin", "serviceDate"  }); 
		JSONArray array = new JSONArray();
		
		if(null != list && list.size() > 0){
			
			for(DataService g : list ){
				JSONObject item = new JSONObject();
				
				item = JSONObject.fromObject(g , cfg) ;
				item.put("serviceDate", null == g.getServiceDate() ? "" : DateUtil.convertDateToString( g.getServiceDate(), DateUtil.pattern2)) ;
				
				if("01".equals(g.getActionType())){ //查询
					item.put("dataTable", this.tableMap.get(g.getSearchTable())) ;
					item.put("resultNum", g.getResultNum1()) ;
				}else if("02".equals(g.getActionType())){ //浏览
					item.put("dataTable", this.tableMap.get(g.getBrowseTable())) ;
					item.put("resultNum", g.getResultNum2()) ;
				}else if("03".equals(g.getActionType())){ //下载
					item.put("dataTable", this.tableMap.get(g.getDownloadTable())) ;
					item.put("resultNum", g.getResultNum3()) ;
				}
				
				array.add(item) ;
			}
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	/**
	 * 访问统计
	 */
	@POST
	@At("/ht/statsVisitList")
    @Ok("json")
	public JSONObject statsSearchTable( @Param("..") Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;
		
		List<DataService>  list = dvs.statsSearchTable();
		
		JSONArray array = new JSONArray();
		if(null != list && list.size() > 0){
			
			for (DataService ds : list) {
				JSONObject t = new JSONObject() ;
				t.put("searchTable", this.tableMap.get(ds.getSearchTable())) ;
				t.put("dbResultNum", ds.getResultNum1()) ;  //影响数据库记录数
				t.put("actionNum", ds.getResultNum2()) ;  //查询次数
				t.put("actionType", "01") ;
				
				array.add(t) ;
			}
		}
		
		//浏览
		List<DataService>  list2 = dvs.statsBrowseTable();
		for (DataService dataService : list2) {
			JSONObject t = new JSONObject() ;
			t.put("searchTable", this.tableMap.get(dataService.getBrowseTable())) ;
			t.put("dbResultNum", dataService.getResultNum1()) ;  //影响数据库记录数
			t.put("actionNum", dataService.getResultNum2()) ;  //查询次数
			t.put("actionType", "02") ;
			
			array.add(t) ;
		}
		
		
		json.put(Constant.TOTAL, array.size()) ;
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	/**
	 * 下载统计
	 */
	@POST
	@At("/ht/statsDownList")
    @Ok("json")
	public JSONObject statsDownloadTable( @Param("..") Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;
		
		List<DataService>  list = dvs.statsDownloadTable() ;
		
		JSONArray array = new JSONArray();
		if(null != list && list.size() > 0){
			
			for (DataService ds : list) {
				JSONObject t = new JSONObject() ;
				t.put("downloadTable", this.tableMap.get(ds.getDownloadTable())) ;
				t.put("dbResultNum", ds.getResultNum1()) ;  //影响数据库记录数
				t.put("actionNum", ds.getResultNum2()) ;  //查询次数
				t.put("actionType", "03") ;
				
				array.add(t) ;
			}
		}
		
		json.put(Constant.TOTAL, array.size()) ;
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	
	
	
	
	
	
	
}
