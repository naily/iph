/**
 * 描述：电离层参数管理
 */
package cn.fam1452.action.ht;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.beanutils.BeanUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.FieldMatcher;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
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
    public Parameter get(String table , long id){
		Parameter para = null ;
		if(id > 0 && StringUtil.checkNotNull(table)){
			Record rd = baseService.dao.fetch(table, Cnd.where("parameterID", "=", id)) ;
			para = this.record2Object(rd) ;
			para.setStationID(table) ;
			para.setParameterID(id) ;
		}
		
		return para ;
	}
	public Parameter get(String table , String id){
		if(Integer.parseInt(id) > 0){
			return this.get(table, Integer.parseInt(id)) ;
		}else{
			return null ;
		}
	}
	
	
	@POST
	@At("/ht/pamlist")
    @Ok("json")
	public JSONObject list(@Param("..")Pages page , String sid){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;

		List<Record>  list = baseService.dao.query(sid+":parameterID", null, page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(sid)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "station" , "createDate"}); 
		JSONArray array = new JSONArray();
		
		Station sa = new Station();
		sa.setId(sid) ;
		sa = baseService.dao.fetch(sa) ;
		for(Record rd : list ){
			Parameter pm = this.record2Object(rd) ;
			JSONObject item = JSONObject.fromObject(pm , cfg);
			
			if(null != sa){
				item.put("stationName", sa.getName()) ;
			}else{
				item.put("stationName", "无") ;
			}
			
			item.put("ID", pm.getParameterID()) ;
			item.put("createDate" , null != pm.getCreateDate() ? DateUtil.convertDateToString(pm.getCreateDate(), DateUtil.pattern2) : "");
			
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
			
			//List<Parameter> igs = new ArrayList<Parameter>() ;
			for (String id : ids) {
				if(null != this.get(params.getStationID(), id)) {
					baseService.dao.clear(params.getStationID(), Cnd.where("parameterID", "=", id )) ;
				}
				//Parameter sa = new Parameter();
				//sa.setParameterID(id) ;
				
				/*sa = baseService.dao.fetch(sa) ;
				
				if(null != sa){
					igs.add(sa) ;
				}*/
			}
			
			json.put(Constant.SUCCESS, true) ;
			dls.insert("03", tableName, getHTLoginUserName()) ;
			
			/*if(null != igs && igs.size() > 0 ){
				if( baseService.dao.delete(igs) == igs.size() ){
					
				}else{
					json.put(Constant.INFO, error1) ;
				}
			}else{
				json.put(Constant.INFO, error2) ;
			}*/
			
		}else{
			json.put(Constant.INFO, error3) ;
		}
		
		return json ;
	}
	
	
	@POST
	@At("/ht/pamupdate")
    @Ok("json")
	public JSONObject update(@Param("..")Parameter params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(params.getParameterID() > 0 && null != this.get(params.getStationID(), params.getParameterID())){
			int i = baseService.dao.update(params.getStationID(), cov(params , false), Cnd.where("parameterID", "=", params.getParameterID())) ;
//			int  i = baseService.dao.update(params) ;
			
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
				baseService.dao.insert(params.getStationID(), cov(params , true)) ;
				//baseService.dao.insert(params) ;
				json.put(Constant.SUCCESS, true ) ;
				
				dls.insert("01", tableName, getHTLoginUserName()) ;
				dls.insertNDY(tableName, params.getStationID(), null, params.getCreateDate()) ;
				json.put(Constant.INFO, "操作成功") ;
			}else{
				json.put(Constant.INFO, "请选择观测站或观测站ID错误") ;
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
			String mdb = fusu.defaultProcessFileUpload(request, "data/access/") ;
			
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
	public JSONObject saveParameterDataFromAccess(String mdbPath , String stationId , String mdbTableName , String dateField ,ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		//log.info("tableName:"+ mdbTableName + " timefield:" + dateField + " station:" +stationId) ;
		
		try {
			long start = System.currentTimeMillis() ;
			AccessUtil au = new AccessUtil(this.getAppRealPath(context) + mdbPath) ;
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
						//p.setParameterID(time) ;
						p.setCreateDate(DateUtil.convertStringToDate(time, DateUtil.pattern5)) ;
						p.setStationID(stationId) ;
						//StringBuilder ss = new StringBuilder(rset.getString("mytime")).append("\t");
						//遍历全部字段并存到java object 中
						for (String fn : available) {
							//ss.append(rset.getString(fn)).append("\t") ;
							BeanUtils.setProperty(p, fn, String.valueOf(rset.getString(fn))) ;
						}
						
						data.add(p) ;
						dls.insertNDY(tableName, p.getStationID(), null, p.getCreateDate()) ;
					}
					//log.info("得到: " + data.size()) ;
					//把已经存在的对象删除掉
					//baseService.dao.delete(data) ;
					if(null != data && data.size() > 0){
						insertdb += data.size() ;
						for (Parameter pa : data) {
							baseService.dao.insert(stationId, cov(pa,true)) ;
						}
						//baseService.dao.insert(data) ;
						dls.insert("01", tableName, getHTLoginUserName()) ;
					}
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
			json.put(Constant.INFO, e.getLocalizedMessage() ) ;
		}finally{
			return json ;
		}
	}
	
	private Chain cov(Parameter pa , boolean ignoreNull){
		FieldMatcher fm = FieldMatcher.make(null, "parameterID|ids|station$", ignoreNull) ;
		Chain ch = Chain.from(pa , fm) ;
		
		return ch ;
	}
	
	//"parameterID" , "stationID","createDate",
	private final String[] paField = {"foF2","fxF2","fxl","hlF2","foF1","hlF1","hlF","hpF","hpF2","foE","hlE","foEs","hlEs","fbEs","es"} ;
	private Parameter record2Object(Record rd){
		Parameter p = new Parameter() ;
		p.setParameterID((Long)rd.get("parameterID")) ;
		p.setCreateDate((Date)rd.get("createDate")) ;
		p.setFmin(rd.getString("Fmin")) ;
		p.setM3000F(rd.getString("M3000F")) ;
		p.setM3000F1(rd.getString("M3000F1")) ;
		p.setM3000F2(rd.getString("M3000F2")) ;
		p.setM1500F2(rd.getString("M1500F2")) ;
		p.setMUF3000F1(rd.getString("MUF3000F1")) ;
		p.setMUF3000F2(rd.getString("MUF3000F2")) ;
		for (String fie : paField) {
			try {
				BeanUtils.setProperty(p, fie, rd.get(fie)) ;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p ;
	}
	
	
}
