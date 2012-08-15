package cn.fam1452.action.ht;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

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
import cn.fam1452.dao.pojo.Scanpic;
import cn.fam1452.dao.pojo.Scanpic;
import cn.fam1452.dao.pojo.Scanpic;
import cn.fam1452.dao.pojo.Scanpic;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataLogService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.OmFileUploadServletUtil;
import cn.fam1452.utils.StringUtil;

/**
 * 扫描图管理
 * Class SancpicMod
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jun 24, 2012 5:06:09 PM $
 */
@IocBean
@Filters(@By(type=AdminFilter.class ))
public class ScanpicMod extends BaseMod{
	
	@At("/ht/sac")
    @Ok("jsp:jsp.ht.sac")
	public void loadSavePages(){}
	
	@At("/ht/saclist")
    @Ok("jsp:jsp.ht.saclist")
	public void loadListPages(){}
	
	@Inject("refer:dataLogService")
	private DataLogService dls ;
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	private static final String tableName = "T_SCANPIC" ;
	
	/**
	 * 批量导入报表扫描图，并从文件名解析观测站及日期
	 * @return
	 */
	@POST
	@At("/ht/sacmulti")
    @Ok("json")
	public JSONObject saveMulti(HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		
		try {
			String filepath = fusu.defaultProcessFileUpload(request , false); //存入实际目录
			if( StringUtil.checkNotNull(filepath) && filepath.length() >= 25){
				//log.info(filepath) ;
				int i = filepath.lastIndexOf("/") ;
				//文件名
				String fn = filepath.substring(i+1) ; 
				//去掉扩展名
				String fno = filepath.substring(i+1 , filepath.lastIndexOf("."))  ; 
				
				//解析出观测站（【示例数据】前5位是观测站）,
				String st = filepath.substring(i+1 , i+6)  ; 
				//解析出日期，【示例数据】文件名包含下划线
				String da = filepath.substring(i+7)  ; 
				
				Scanpic sp = new Scanpic() ;
				sp.setScanPicID(fno) ; //用文件名作为数据库ID，去扩展名
				sp.setScanPicFileName(fn) ;
				sp.setGramPath(filepath) ; //文件路径
				sp.setScanPicTitle(fn) ;   //全文件名做标题
				
				sp.setCreateDate( DateUtil.convertStringToDate(da, DateUtil.pattern4) ) ;
				sp.setStationID(st) ;
				json.put("filename", fn) ;
				
				if(baseService.dao.fetch(sp) == null){
					baseService.dao.insert(sp) ;
					json.put(Constant.SUCCESS, true) ;
					dls.insert("03", tableName, "admin") ;
				}else{
					json.put(Constant.INFO, this.error4) ;
					json.put("error", 2) ;
				}
				
				//fusu.clearTmpDirectory() ; //清空临时目录
			}else{
				json.put(Constant.INFO, "文件名太短,无法解析" ) ;
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
	@At("/ht/sacget")
    @Ok("json")
    public Scanpic get(String id){
		Scanpic sp = null ;
		if(StringUtil.checkNotNull(id)){
			sp = baseService.dao.fetch(Scanpic.class, id) ;
		}
		
		return sp ;
	}
	
	@POST
	@At("/ht/saclist")
    @Ok("json")
	public JSONObject list(@Param("..")Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;

		List<Scanpic>  list = baseService.dao.query(Scanpic.class, null, page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(Scanpic.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "station"}); 
		JSONArray array = new JSONArray();
		
		for(Scanpic g : list ){
			JSONObject item = new JSONObject();
			
			Station sa = new Station();
			sa.setId(g.getStationID()) ;
			sa = baseService.dao.fetch(sa) ;
			if(null != sa){
				item.put("stationName", sa.getName()) ;
			}else{
				item.put("stationName", "无") ;
			}
			
			item.put("ID", g.getScanPicID()) ;
			item.put("Title", g.getScanPicTitle()) ;
			item.put("stationID", g.getStationID()) ;
			item.put("createDate",  DateUtil.convertDateToString(g.getCreateDate())) ;
			item.put("FileName", g.getScanPicFileName()) ;
			
			item.put("gramPath", g.getGramPath()) ;
			
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	
	@POST
	@At("/ht/sacdel")
    @Ok("json")
	public JSONObject deleteScanpic(@Param("..")Scanpic params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getIds())){
			String[] ids = params.getIds().split(";") ;
			
			List<Scanpic> igs = new ArrayList<Scanpic>() ;
			for (String id : ids) {
				Scanpic sa = new Scanpic();
				sa.setScanPicID(id) ;
				
				sa = baseService.dao.fetch(sa) ;
				
				if(null != sa){
					igs.add(sa) ;
				}
			}
			
			if(null != igs && igs.size() > 0 ){
				if( baseService.dao.delete(igs) == igs.size() ){
					json.put(Constant.SUCCESS, true) ;
					dls.insert("03", tableName, "admin") ;
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
	@At("/ht/sacupdate")
    @Ok("json")
	public JSONObject updatePgt(@Param("..")Scanpic params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getScanPicID()) && null != baseService.dao.fetch(params)){
			int  i = baseService.dao.update(params) ;
			json.put(Constant.SUCCESS, true ) ;
			dls.insert("02", tableName, "admin") ;
		}else{
			json.put(Constant.INFO, error2) ;
		}
		return json ;
	}
	
	/**
	 * 上传单个文件 
	 */
	@POST
	@At("/ht/sacsingsave")
    @Ok("json")
	public JSONObject saveSingleton (@Param("..")Scanpic sac  ,HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		try {
			if("savedata".equals(sac.getAction())){
				//把临时目录中的对应的文件转存，并在数据库中保存一条记录
				if(StringUtil.checkNotNull(sac.getScanPicFileName() )){
					if(fusu.cloneTmpFile2Other(sac.getScanPicFileName(), this.getAppRealPath(context) + fusu.UPLOAD_PIC_PATH) ){
						sac.setGramPath(fusu.UPLOAD_PIC_PATH + sac.getScanPicFileName()); 
						//去掉文件的扩展名，做数据库记录ID
						sac.setScanPicID(sac.getScanPicFileName().substring(0, sac.getScanPicFileName().lastIndexOf(".")))  ;
						
						if(baseService.dao.fetch(sac) == null){
							baseService.dao.insert(sac) ;
							json.put(Constant.SUCCESS, true) ;
							dls.insert("03", tableName, "admin") ;
						}else{
							json.put(Constant.INFO, "该文件已经存在") ;
						}
						
						fusu.clearTmpDirectory() ; //清空临时目录
					}else{
						json.put(Constant.INFO, "文件保存失败") ;
					}
				}else{
					json.put(Constant.INFO, "文件名错误") ;
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
}
