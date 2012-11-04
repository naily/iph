/**
 * 描述：
 */
package cn.fam1452.action.ht;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Entity;
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
import cn.fam1452.action.filter.AdminFilter;
import cn.fam1452.dao.pojo.Log;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataLogService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

/**
 * 数据表操作日志记录
 * Class DataLogMod
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jul 18, 2012 10:12:18 PM $
 */
@IocBean
@Filters(@By(type=AdminFilter.class ))
public class DataLogMod extends BaseMod{

	@Inject("refer:dataLogService")
	private DataLogService dls ;
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/ht/dlog")
    @Ok("jsp:jsp.ht.dLog")
    public void load(HttpServletRequest req){ }
	
	
	@POST
	@At("/ht/dataLogList")
    @Ok("json")
	public JSONObject list( @Param("..")Log params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;
		
		Criteria cri = Cnd.cri();
		if(StringUtil.checkNotNull(params.getActionType())){
			cri.where().and( "actionType", "=", params.getActionType()) ;
		}
		if(StringUtil.checkNotNull(params.getAdminId())) {
			cri.where().and( "adminId", "=", params.getAdminId()) ;
		}
		cri.getOrderBy().desc("logDate") ;
		
		List<Log>  list = baseService.dao.query(Log.class, cri, params.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(Log.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "admin", "logDate"  }); 
		JSONArray array = new JSONArray();
		
		for(Log g : list ){
			JSONObject item = new JSONObject();
			
			item = JSONObject.fromObject(g , cfg) ;
			item.put("logDate", DateUtil.convertDateToString(g.getLogDate()  , DateUtil.pattern2)) ;
			//item.put("dataEDate", DateUtil.convertDateToString(g.getDataEDate()  , DateUtil.pattern0)) ;
			//item.put("publicDate", DateUtil.convertDateToString(g.getPublicDate()  , DateUtil.pattern0)) ;
			item.put("dataTable", this.tableMap.get(g.getDataTable())) ;
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	@POST
	@At("/ht/dLogDelAll")
    @Ok("json")
	public JSONObject deleteLogAll(String ids , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		try{
			int i = baseService.dao.clear(Log.class) ;
			json.put(Constant.SUCCESS, true) ;
			json.put("total", i) ;
		}catch (Exception e) {
			// TODO: handle exception
			json.put(Constant.INFO, e.getMessage()) ;
		}finally{
			return json ;
		}
		
	}
	
	@POST
	@At("/ht/dlogget")
    @Ok("json")
    public Log get(String id){
		Log ig = null ;
		if(StringUtil.checkNotNull(id)){
			ig = baseService.dao.fetch(Log.class, id) ;
		}
		
		return ig ;
	}
	
	@At("/ht/downloadAllLog")
	@Ok("raw")
	public void exportLogAndDownload(HttpServletResponse response){
		
		Workbook wb = dls.exportToHSSFWorkbook() ;
		
		try {
			if(null != wb){
				OutputStream out = response.getOutputStream();
				response.setContentType("application/x-msdownload");
				
				StringBuffer fileName = new StringBuffer().append("DataLog_all.xls") ;
				response.setHeader("Content-Disposition", "attachment; filename=" + new String( fileName.toString().getBytes("GBK"), "ISO8859-1" ));
				
				//BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tmp));
				//byte[] buffer = IOUtils.toByteArray(bis);
				//os.write(buffer);
				wb.write(out) ;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error(e, e) ;
		}
	}
}
