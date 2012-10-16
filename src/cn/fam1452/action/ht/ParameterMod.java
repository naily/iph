/**
 * 描述：电离层参数管理
 */
package cn.fam1452.action.ht;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.beanutils.BeanUtils;
import org.nutz.dao.Cnd;
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
import cn.fam1452.dao.pojo.Parameter;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataLogService;
import cn.fam1452.utils.AccessUtil;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.OmFileUploadServletUtil;
import cn.fam1452.utils.StringUtil;

/**
 * Class ParameterMod
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jun 24, 2012 10:04:31 AM $
 */
@IocBean
@Filters(@By(type=AdminFilter.class ))
public class ParameterMod extends BaseMod{
	
	@At("/ht/pam")
    @Ok("jsp:jsp.ht.pam")
	public void loadSavePages(){}
	
	@At("/ht/pamlist")
    @Ok("jsp:jsp.ht.pamlist")
	public void loadListPages(){}
	
	@Inject("refer:dataLogService")
	private DataLogService dls ;
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	private static final String tableName = "T_PARAMETER" ;
	
	@POST
	@At("/ht/pamget")
    @Ok("json")
    public Parameter get(String id){
		Parameter sp = null ;
		if(StringUtil.checkNotNull(id)){
			sp = baseService.dao.fetch(Parameter.class, id) ;
		}
		
		return sp ;
	}
	
	@POST
	@At("/ht/pamlist")
    @Ok("json")
	public JSONObject list(@Param("..")Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;

		List<Parameter>  list = baseService.dao.query(Parameter.class, null, page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(Parameter.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "station" , "createDate"}); 
		JSONArray array = new JSONArray();
		
		for(Parameter pm : list ){
			JSONObject item = JSONObject.fromObject(pm , cfg);
			
			Station sa = new Station();
			sa.setId(pm.getStationID()) ;
			sa = baseService.dao.fetch(sa) ;
			if(null != sa){
				item.put("stationName", sa.getName()) ;
			}else{
				item.put("stationName", "无") ;
			}
			
			item.put("ID", pm.getParameterID()) ;
			
			item.put("createDate" , DateUtil.convertDateToString(pm.getCreateDate(), DateUtil.pattern2));
			
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	
	@POST
	@At("/ht/pamdel")
    @Ok("json")
	public JSONObject deleteParameter(@Param("..")Parameter params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getIds())){
			String[] ids = params.getIds().split(";") ;
			
			List<Parameter> igs = new ArrayList<Parameter>() ;
			for (String id : ids) {
				Parameter sa = new Parameter();
				sa.setParameterID(id) ;
				
				sa = baseService.dao.fetch(sa) ;
				
				if(null != sa){
					igs.add(sa) ;
				}
			}
			
			if(null != igs && igs.size() > 0 ){
				if( baseService.dao.delete(igs) == igs.size() ){
					json.put(Constant.SUCCESS, true) ;
					
					dls.insert("03", tableName, getHTLoginUserName()) ;
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
	
	
	@POST
	@At("/ht/pamupdate")
    @Ok("json")
	public JSONObject updatePgt(@Param("..")Parameter params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getParameterID()) && null != baseService.dao.fetch(params)){
			int  i = baseService.dao.update(params) ;
			json.put(Constant.SUCCESS, true ) ;
			
			dls.insert("02", tableName, getHTLoginUserName()) ;
		}else{
			json.put(Constant.INFO, error2) ;
		}
		return json ;
	}
	
	@POST
	@At("/ht/pamsave")
    @Ok("json")
	public JSONObject save(@Param("..")Parameter params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		try{
			if(StringUtil.checkNotNull(params.getStationID()) ){
				
				StringBuilder id = new StringBuilder();
				id.append(params.getStationID());
				id.append(DateUtil.convertDateToString(params.getCreateDate(), "yyyyMMddHH") );
				
				int s = baseService.dao.count(Parameter.class, Cnd.where("parameterID", "like",  id.toString()+"%") ) ;
				
				String patt = "00" ;  
				DecimalFormat nf  =  new DecimalFormat(patt);
				id.append(nf.format(s)) ;
				
				params.setParameterID(id.toString()) ;
			}else{
				json.put(Constant.INFO, "请选择观测站") ;
			}
			
			if(StringUtil.checkNotNull(params.getParameterID()) && null == baseService.dao.fetch(params)){
				baseService.dao.insert(params) ;
				json.put(Constant.SUCCESS, true ) ;
				
				dls.insert("01", tableName, getHTLoginUserName()) ;
				dls.insertNDY(tableName, params.getStationID(), null, params.getCreateDate()) ;
			}else{
				json.put(Constant.INFO, error7) ;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace() ;
			json.put(Constant.INFO, e.getMessage()) ;
		}finally{
			return json ;
		}
	}
	
	@POST
	@At("/ht/uploadaccessdata")
    @Ok("json")
	public JSONObject uploadMultiDataFromAccess(HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil(context);
		try {
			String mdb = fusu.defaultProcessFileUpload(request, this.getAppRealPath(context) + "data/access/") ;
			
			//Connection con = new AccessUtil(mdb).getConnection() ;
			//log.info(mdb) ;
			json.put(Constant.INFO, mdb) ;
			json.put(Constant.SUCCESS, true) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put(Constant.INFO, e.getMessage()) ;
		}finally{
			return json ;
		}
	}
	
	@POST
	@At("/ht/savepamdata")
    @Ok("json")
	public JSONObject saveParameterDataFromAccess(String mdbPath , String stationId , String mdbTableName , String dateField){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		//log.info("tableName:"+ mdbTableName + " timefield:" + dateField + " station:" +stationId) ;
		
		try {
			long start = System.currentTimeMillis() ;
			AccessUtil au = new AccessUtil(mdbPath) ;
			Connection con = au.getConnection() ;
			
			Statement stat = con.createStatement(); 
			long total = au.getTotalRowNumber(stat, mdbTableName) ;
			
			long pageTotal = au.getTotalPageNumber(total)  ;
			
			long insertdb  = 0 ;
			String tmpl = "select top PAGESPEED * from TABLENAME where ID >(select top 1 max(ID) from (select top BEFOREROW ID from TABLENAME order by ID)) " ;
			tmpl = tmpl.replaceAll("ID", dateField).replaceAll("PAGESPEED", String.valueOf(au.pageSpeed)).replaceAll("TABLENAME", mdbTableName) ;
			for(int i = 1 ; i <= pageTotal ; i++){
				int ii = (i-1)* au.pageSpeed ;
				String sql = tmpl.replaceAll("BEFOREROW", String.valueOf(ii)) ;
				
				if(i == 1){
					sql = sql.substring(0 , sql.indexOf("where")) ;
				}
				
				ResultSet rset = au.execSQL(stat , sql ) ;
				if(null != rset){
					//把当前结果集中存在的fieldsName找出来
					List<String> available = new ArrayList<String>() ;
					List<String> list = au.getAllColumnName(rset.getMetaData()) ;
					for (String str : au.fieldsName) {
						if(au.isExistString(list, str) ){
							available.add(str) ;
						}
					}
					
					List<Parameter> data = new ArrayList<Parameter>() ;
					while (rset.next()) {
						Parameter p = new Parameter() ;
						String time = rset.getString(dateField) ;
						p.setParameterID(time) ;
						p.setCreateDate(DateUtil.convertStringToDate(time, DateUtil.pattern5)) ;
						p.setStationID(stationId) ;
						//StringBuilder ss = new StringBuilder(rset.getString("mytime")).append("\t");
						//遍历全部字段并存到java object 中
						for (String fn : available) {
							//ss.append(rset.getString(fn)).append("\t") ;
							BeanUtils.setProperty(p, fn, String.valueOf(rset.getString(fn))) ;
						}
						//System.out.println(ss.toString());
						
						if(baseService.dao.fetch(p) == null){
							data.add(p) ;
							dls.insertNDY(tableName, p.getStationID(), null, p.getCreateDate()) ;
						}
					}
					//log.info("得到: " + data.size()) ;
					insertdb += data.size() ;
					baseService.dao.insert(data) ;
					dls.insert("01", tableName, getHTLoginUserName()) ;
				}
			}
			//log.info("共得到: " + insertdb) ;
			long end = System.currentTimeMillis() ;
			
			json.put(Constant.SUCCESS, true) ;
			json.put("usetime", (end-start)/1000 + " 秒" ) ;
			json.put("insertRow", insertdb + " 条") ;
			//log.debug(json.get("usetime")) ;
		} catch (Exception e) {
			//e.printStackTrace();
			json.put(Constant.INFO, e.getMessage()) ;
		}finally{
			return json ;
		}
	}
	
}
