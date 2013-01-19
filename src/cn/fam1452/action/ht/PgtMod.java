/**
 * 
 */
package cn.fam1452.action.ht;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.filter.AdminFilter;
import cn.fam1452.dao.pojo.IronoGram;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataLogService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.FileUtil;
import cn.fam1452.utils.LocalFileUtil;
import cn.fam1452.utils.OmFileUploadServletUtil;
import cn.fam1452.utils.StationUtil;
import cn.fam1452.utils.StringUtil;

/**
 * @author zdd
 *
 */
@IocBean
@Filters(@By(type=AdminFilter.class ))
public class PgtMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@Inject("refer:dataLogService")
	private DataLogService dls ;

	@At("/ht/pgt")
    @Ok("jsp:jsp.ht.pgt")
    public void loadPgt(HttpServletRequest req){}
	@At("/ht/pgtlist")
    @Ok("jsp:jsp.ht.pgtlist")
    public void loadPgtlist(HttpServletRequest req){}
	
	private static final String tableName = "T_IRONOGRAM" ;
	
	/**
	 * 上传单个高频图文件 
	 */
	@POST
	@At("/ht/pgtuploads")
    @Ok("json")
	public JSONObject saveSingleton (@Param("..")IronoGram gram  ,HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		try {
			if("savedata".equals(gram.getAction())){
				//把临时目录中的对应的文件转存，并在数据库中保存一条记录
				if(StringUtil.checkNotNull(gram.getGramFileName())){
					if(fusu.cloneTmpFile2Other(gram.getGramFileName(), this.getAppRealPath(context) + fusu.UPLOAD_PGT_PATH) ){
						gram.setGramPath(fusu.UPLOAD_PGT_PATH + gram.getGramFileName()); 
						//去掉文件的扩展名，做数据库记录ID
						gram.setGramID(gram.getGramFileName().substring(0, gram.getGramFileName().lastIndexOf(".")))  ;
						
						if(baseService.dao.fetch(gram) == null){
							baseService.dao.insert(gram) ;
							json.put(Constant.SUCCESS, true) ;
							
							dls.insert("01", tableName, getHTLoginUserName()) ;
							dls.insertNDY(tableName, gram.getStationID(), null, gram.getCreateDate()) ;
						}else{
							json.put(Constant.INFO, "该频高图文件已经存在") ;
						}
						
						fusu.clearTmpDirectory() ; //清空临时目录
					}else{
						json.put(Constant.INFO, "频高图文件保存失败") ;
					}
				}else{
					json.put(Constant.INFO, "频高图文件名错误") ;
				}
				
			}else{ //仅仅把图片存储到临时目录
				String file = fusu.defaultProcessFileUpload(request , true) ;
				
				//log.info(file) ;
				json.put("imgpath", file) ;
				json.put(Constant.SUCCESS, true) ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put(Constant.INFO, e.getMessage()) ;
		}finally{
			return json ;
		}
	}
	
	@POST
	@At("/ht/pgtlist")
    @Ok("json")
	public JSONObject list(@Param("..")Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;

		List<IronoGram>  list = baseService.dao.query(IronoGram.class, null, page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(IronoGram.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "station"}); 
		JSONArray array = new JSONArray();
		
		for(IronoGram g : list ){
			JSONObject item = new JSONObject();
			
			Station sa = new Station();
			sa.setId(g.getStationID()) ;
			sa = baseService.dao.fetch(sa) ;
			if(null != sa){
				item.put("stationName", sa.getName()) ;
			}else{
				item.put("stationName", "无") ;
			}
			
			item.put("gramID", g.getGramID()) ;
			item.put("gramTitle", g.getGramTitle()) ;
			item.put("stationID", g.getStationID()) ;
			item.put("type", g.getType()) ;
			item.put("createDate",  null == g.getCreateDate() ? "" :DateUtil.convertDateToString(g.getCreateDate())) ;
			item.put("gramFileName", g.getGramFileName()) ;
			
			item.put("gramPath", g.getGramPath()) ;
			
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	@POST
	@At("/ht/pgtdel")
    @Ok("json")
	public JSONObject deletePgt(@Param("..")IronoGram params , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getIds())){
			String[] ids = params.getIds().split(";") ;
			
			List<IronoGram> igs = new ArrayList<IronoGram>() ;
			for (String id : ids) {
				IronoGram ig = new IronoGram();
				ig.setGramID(id) ;
				
				ig = baseService.dao.fetch(ig) ;
				
				if(null != ig){
					igs.add(ig) ;
					FileUtil.deleteFile(this.getAppRealPath(context) + ig.getGramPath()) ;
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
	@At("/ht/pgtupdate")
    @Ok("json")
	public JSONObject updatePgt(@Param("..")IronoGram params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getGramID()) && null != baseService.dao.fetch(params)){
			int  i = baseService.dao.update(params) ;
			json.put(Constant.SUCCESS, true ) ;
			
			dls.insert("02", tableName, getHTLoginUserName()) ;
		}else{
			json.put(Constant.INFO, error2) ;
		}
		return json ;
	}
	
	/**
	 * 批量导入频高图，并从文件名解析观测站及日期
	 * @return
	 */
	@POST
	@At("/ht/pgtmulti")
    @Ok("json")
	public JSONObject saveMulti(@Param("..")IronoGram gram  ,HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		
		try {
			String filepath = fusu.defaultProcessFileUpload(request , fusu.UPLOAD_PGT_PATH); //存入实际目录
			if( StringUtil.checkNotNull(filepath) && filepath.length() >= 25){
				//log.info(filepath) ;
				int i = filepath.lastIndexOf("/") ;
				//文件名
				String fn = filepath.substring(i+1) ; 
				//log.info(fn) ;
				//去掉扩展名
				String fno = filepath.substring(i+1 , filepath.lastIndexOf("."))  ; 
				
				//解析出观测站
				String st = filepath.substring(i+1 , i+3)  ; 
				st = StationUtil.getStationId(fn) ;
				//解析出日期
				//String da = filepath.substring(i+3 , i+11)  ; 
				
				IronoGram ig = new IronoGram() ;
				ig.setGramID(fno) ;
				ig.setGramFileName(fn) ;
				ig.setGramPath(filepath) ;
				ig.setGramTitle(fn) ;
				
				ig.setCreateDate( StationUtil.getObserveDate(fn) ) ;
				ig.setType(StationUtil.getPgtType(ig.getCreateDate())) ;
				ig.setStationID(st) ;
				json.put("filename", fn) ;
				
				if(baseService.dao.fetch(ig) == null){
					baseService.dao.insert(ig) ;
					json.put(Constant.SUCCESS, true) ;
					
					dls.insert("01", tableName, getHTLoginUserName()) ;
					dls.insertNDY(tableName, ig.getStationID(), null, ig.getCreateDate()) ;
				}else{
					json.put(Constant.INFO, "该频高图文件已经存在") ;
					json.put("error", 2) ;
				}
				
				//fusu.clearTmpDirectory() ; //清空临时目录
			}else{
				json.put(Constant.INFO, "文件名长度小于10,上传失败" ) ;
				json.put("error", 3) ;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put(Constant.INFO, e.getLocalizedMessage() ) ;
			json.put("error", 4) ;
		}finally{
			
			return json ;
		}
		
	}
	
	@POST
	@At("/ht/pgtget")
    @Ok("json")
    public IronoGram get(String id){
		IronoGram ig = null ;
		if(StringUtil.checkNotNull(id)){
			ig = baseService.dao.fetch(IronoGram.class, id) ;
		}
		
		return ig ;
	}
	
	
	/**
	 * 测试服务器目录
	 * @Author Derek
	 * @Date Nov 27, 2012
	 */
	@POST
	@At("/ht/pgttestserverpath")
    @Ok("json")
	public JSONObject testServerFileDirectory(String path){
		JSONObject json = LocalFileUtil.testServerFileDirectory(path) ;
		
		if(null != json && json.getBoolean(Constant.SUCCESS)){
			String fn = json.getString(Constant.INFO) ;
			//log.info(fn) ;
			//去掉扩展名
			//String fno = fn.substring(0 , fn.lastIndexOf("."))  ; 
			
			//解析出观测站
			String st = fn.substring(1 , 3)  ; 
			st = StationUtil.getStationId(fn) ;
			json.put("stationid", st) ; //观测站ID
			
			//解析出日期
			Date date = StationUtil.getObserveDate(fn) ;
			if(null != date){
				json.put("date", DateUtil.DateToString(date, DateUtil.pattern4));
				json.put("year", DateUtil.getYearstrByDate(date));
				json.put("pgttype", StationUtil.getPgtType(date));
			}else{
				json.put(Constant.SUCCESS, false) ;
				json.put(Constant.INFO, "文件异常,无法解析出日期、类型及年份") ;
			}
			//String da = filepath.substring(i+3 , i+11)  ; 
			
			json.put("filename", fn) ;
			json.put("path", path) ;
		}
		
		return json ;
	}
	
	@POST
	@At("/ht/pgtsaveserverpath")
    @Ok("json")
	public JSONObject saveServerFileDirectory(String path , String stationId , String year,ServletContext context){
		long start  = System.currentTimeMillis() ;
		JSONObject json = LocalFileUtil.testServerFileDirectory(path) ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		
		if(null != json && json.getBoolean(Constant.SUCCESS)){
			StringBuilder failFile = new StringBuilder() ;
			List<IronoGram> iglist = new ArrayList() ;
			File file = new File(path) ;
			File[] list = file.listFiles() ;
			if(null != list && list.length >0){
				int fail = 0 ;
				for (File sf : list) { //
					String sfname = sf.getName() ; //文件名
					if(null != StationUtil.getObserveDate(sfname)){
						if(fusu.cloneTmpFile2Other(sf, this.getAppRealPath(context) + fusu.UPLOAD_PGT_PATH )){
							IronoGram ig = new IronoGram() ;
							ig.setGramID(StationUtil.removeSuffix(sfname)) ;
							ig.setGramFileName(sfname) ;
							ig.setGramPath(fusu.UPLOAD_PGT_PATH + sfname ) ;
							ig.setGramTitle(sfname) ;
							
							ig.setCreateDate( StationUtil.getObserveDate(sfname) ) ;
							ig.setType(StationUtil.getPgtType(ig.getCreateDate())) ;
							//ig.setStationID(StationUtil.getStationId(sfname)) ;
							ig.setStationID(stationId) ;
							
							iglist.add(ig) ;
						}else{
							fail++ ; //失败数加1
							failFile.append(sfname).append(",") ;
						}
						
					}else{
						fail++ ; //失败数加1
						failFile.append(sfname).append(",") ;
					}
				}
				json.put("fail", fail) ;
				json.put("total", list.length) ;
				
				json.put("failfile", failFile.toString()) ;
			}
			
			if(iglist.size() > 0){
				baseService.dao.delete(iglist) ;
				baseService.dao.insert(iglist) ;
				json.put(Constant.SUCCESS, true) ;
				
				dls.insert("01", tableName, getHTLoginUserName()) ;
					
				dls.insertNDY(tableName, stationId, null, year) ;
			}
		}
		//log.info("用时毫秒数： " + (System.currentTimeMillis() - start)) ;
		json.put("usedtime", (System.currentTimeMillis() - start)/1000) ;
		return json ;
	}
}
