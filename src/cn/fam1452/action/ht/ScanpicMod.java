package cn.fam1452.action.ht;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import cn.fam1452.dao.pojo.IronoGram;
import cn.fam1452.dao.pojo.Scanpic;
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
			String filepath = this.getAppRealPath(context) + fusu.defaultProcessFileUpload(request , fusu.UPLOAD_SAC_PATH ,false ); //存入实际目录
			if( StringUtil.checkNotNull(filepath) && filepath.length() >= 25){
				//log.info(filepath) ;
				int i = filepath.lastIndexOf("/") ;
				//文件名
				String fn = filepath.substring(i+1) ; 
				//去掉扩展名
				String fno = filepath.substring(i+1 , filepath.lastIndexOf("."))  ; 
				
				//解析出观测站（【示例数据】前5位是观测站）,
				//String st = filepath.substring(i+1 , i+6)  ; 
				//解析出日期，【示例数据】文件名包含下划线
				//String da = filepath.substring(i+7)  ; 
				
				Scanpic sp = new Scanpic() ;
				sp.setScanPicID(fno) ; //用文件名作为数据库ID，去扩展名
				sp.setScanPicFileName(fn) ;
				sp.setGramPath(filepath) ; //文件路径
				sp.setScanPicTitle(fn) ;   //全文件名做标题
				
				sp.setCreateDate( StationUtil.getSacDate(fn) ) ;
				sp.setStationID(StationUtil.getStationId(fn)) ;
				json.put("filename", fn) ;
				
				if(baseService.dao.fetch(sp) == null){
					baseService.dao.insert(sp) ;
					json.put(Constant.SUCCESS, true) ;
					
					dls.insert("01", tableName, getHTLoginUserName()) ;
					dls.insertNDY(tableName, sp.getStationID(), null, sp.getCreateDate()) ;
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
			item.put("createDate",  null == g.getCreateDate() ? "" : DateUtil.convertDateToString(g.getCreateDate())) ;
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
	public JSONObject deleteScanpic(@Param("..")Scanpic params , ServletContext context){
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
					FileUtil.deleteFile(this.getAppRealPath(context) + sa.getGramPath()) ;
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
	@At("/ht/sacupdate")
    @Ok("json")
	public JSONObject updatePgt(@Param("..")Scanpic params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getScanPicID()) && null != baseService.dao.fetch(params)){
			int  i = baseService.dao.update(params) ;
			json.put(Constant.SUCCESS, true ) ;
			dls.insert("02", tableName, getHTLoginUserName()) ;
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
					if(fusu.cloneTmpFile2Other(sac.getScanPicFileName(), this.getAppRealPath(context) + fusu.UPLOAD_SAC_PATH) ){
						sac.setGramPath(this.getAppRealPath(context) + fusu.UPLOAD_SAC_PATH + fusu.getTargetFile().getName()); 
						//去掉文件的扩展名，做数据库记录ID
						sac.setScanPicID(sac.getScanPicFileName().substring(0, sac.getScanPicFileName().lastIndexOf(".")))  ;
						
						if(baseService.dao.fetch(sac) == null){
							baseService.dao.insert(sac) ;
							json.put(Constant.SUCCESS, true) ;
							dls.insert("01", tableName, getHTLoginUserName()) ;
							
							dls.insertNDY(tableName, sac.getStationID(), null, sac.getCreateDate()) ;
							
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
	
	/**
	 * 获取文件处理进度
	 * @Author Derek
	 * @Date Feb 17, 2013
	 * @return
	 */
	@POST
	@At("/ht/getsacprogress")
    @Ok("json")
	public JSONObject getSacProgress( int total){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;
		
		json.put(Constant.INFO, progress) ;
		json.put("total", total) ;
		if(0 != total){
			json.put("percentage", progress*100 / total) ;
		}
		
		if(progress > 0 && total == progress){
			json.put(Constant.SUCCESS, false) ;
			progress = 0 ;
			total = 0 ;
		}
		//System.out.println(json.get("percentage"));
		
		return json ;
	}
	
	private int progress  = 0  ; //文件解析进度值
	
	/**
	 * 测试服务器目录
	 * @Author Derek
	 * @Date Nov 27, 2012
	 */
	@POST
	@At("/ht/sactestserverpath")
    @Ok("json")
	public JSONObject testServerFileDirectory(String path ){
		JSONObject json = LocalFileUtil.testServerFileDirectory3(path) ;
		
		/*if(null != json && json.getBoolean(Constant.SUCCESS)){
			String fn = json.getString(Constant.INFO) ;
			//解析出观测站
			String st = fn.substring(1 , 3)  ; 
			st = StationUtil.getStationId(fn) ;
			json.put("stationid", st) ; //观测站ID
			
			//解析出日期
			Date date = StationUtil.getSacDate(fn) ;
			if(null != date){
				json.put("date", DateUtil.DateToString(date, DateUtil.pattern4));
				json.put("year", DateUtil.getYearstrByDate(date));
			}else{
				json.put(Constant.SUCCESS, false) ;
				json.put(Constant.INFO, "文件异常,无法解析出日期、类型及年份") ;
			}
			
			json.put("filename", fn) ;
		}*/
		json.put("path", path) ;
		
		return json ;
	}
	
	@POST
	@At("/ht/sacsaveserverpath")
    @Ok("json")
	public JSONObject saveServerFileDirectory(String path , String stationId , String fileway , 
			String fileprefix , ServletContext context){
		long start  = System.currentTimeMillis() ;
		JSONObject json = LocalFileUtil.testServerFileDirectory3(path) ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		
		if(null != json && json.getBoolean(Constant.SUCCESS)){
			StringBuilder failFile = new StringBuilder() ;
			List<Scanpic> salist = new ArrayList() ;
			
			File root = new File(path) ;
			File[] years = root.listFiles() ;
			if(null != years && years.length >0){
				int fail = 0 ;
				String filter = "Thumbs.db" ;
				HashSet<String> ndyYear = new HashSet<String>() ;
				//手动频高图解析
				for(File y : years) {
					if(null != y && y.isDirectory()){
						String year = y.getName() ;
						this.createDirectory(this.getAppRealPath(context) + fusu.UPLOAD_SAC_PATH + year) ;
						
						File[] yearFiles = y.listFiles() ;
						if(null != yearFiles){
							for (File f : yearFiles) {
								//处理文件复制或者是剪切
								progress++ ;
								String fn = f.getName() ;
								Date date = null ;
								if(!filter.equals(fn)){
									fn = fn.replace(fileprefix, "") ;
									date = DateUtil.convertStringToDate(StationUtil.removeSuffix(fn) , DateUtil.pattern3) ;
								}
								if(null != date){
									boolean ok = false ;
									if("copy".equals(fileway)){
										ok = this.copyFile(f, this.getAppRealPath(context) + fusu.UPLOAD_SAC_PATH + year +"/" ) ;
									}else if("cut".equals(fileway)){
										ok = this.cutFile(f, this.getAppRealPath(context) + fusu.UPLOAD_SAC_PATH + year +"/") ;
									}
									
									if(ok){
										Scanpic sac = this.createScanpic(f.getName(), stationId, date, fusu.UPLOAD_SAC_PATH + year + "/" + f.getName()) ;
										salist.add(sac) ;
										
										ndyYear.add(DateUtil.getYearstrByDate(sac.getCreateDate())) ;
									}
								}else{
									fail++ ;
									failFile.append(fn).append(",") ;
								}
							}
						}
						//dls.insertNDY(tableName, stationId, null, year) ;
					}
				}
				
				//向ndy中插入
				for (String ye : ndyYear) {
					dls.insertNDY(tableName, stationId, null, ye) ;
				}
				
				json.put("fail", fail) ;
				json.put("total", salist.size() ) ;
				
				json.put("failfile", failFile.toString()) ;
			}
			
			if(salist.size() > 0){
				baseService.dao.delete(salist) ;
				baseService.dao.insert(salist) ;
				json.put(Constant.SUCCESS, true) ;
				
				dls.insert("01", tableName, getHTLoginUserName()) ;
					
			}
		}
		//log.info("用时毫秒数： " + (System.currentTimeMillis() - start)) ;
		json.put("usedtime", (System.currentTimeMillis() - start)/1000) ;
		return json ;
	}
	
	private boolean copyFile(File src , String otherDir){
		boolean st = false ;
        
        if(null != src && src.isFile()){
			File ot = new File(otherDir) ;
			if(ot.isDirectory()){
				File dest = new File(otherDir + src.getName() ) ;
				BufferedInputStream inBuff = null;
				BufferedOutputStream outBuff = null;
				try {
					// 新建文件输入流并对它进行缓冲
					inBuff = new BufferedInputStream(new FileInputStream(src));
					// 新建文件输出流并对它进行缓冲
					outBuff = new BufferedOutputStream(new FileOutputStream(dest));
					
					// 缓冲数组
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = inBuff.read(b)) != -1) {
						outBuff.write(b, 0, len);
					}
					// 刷新此缓冲的输出流
					outBuff.flush();
					st = true ;
				}catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					// 关闭流
					if (inBuff != null)
						try {
							inBuff.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					if (outBuff != null)
						try {
							outBuff.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		}
		
		return st ;
	}
	private boolean cutFile(File src , String otherDir){
		boolean st = false ;
		
		if(null != src && src.isFile()){
			File ot = new File(otherDir) ;
			if(ot.isDirectory()){
				File dest = new File(otherDir + src.getName() ) ;
				if(!dest.exists()){
					st = src.renameTo(dest) ;
				}else{
					st = true ; //文件已经存在
				}
			}
		}
		
		return st ;
	}
	
	/**
	 * 
	 * @Author Derek
	 * @Date Feb 17, 2013
	 * @param filename 文件名
	 * @param pgttype  频高图类型
	 * @param sid      观测站ID
	 * @param cdate    创建日期
	 * @param filepath 文件存储路径
	 * @return
	 */
	private Scanpic createScanpic(String filename , String sid , Date cdate , String filepath){
		Scanpic ig = new Scanpic() ;
		ig.setScanPicID(StationUtil.removeSuffix(filename)) ;
		ig.setScanPicFileName(filename) ;
		ig.setGramPath(filepath ) ;
		ig.setScanPicTitle(filename) ;
		
		ig.setCreateDate( cdate) ;
		//ig.setStationID(StationUtil.getStationId(sfname)) ;
		ig.setStationID(sid) ;
		return ig ;
	}
	
	private boolean createDirectory(String dir ){
		boolean st = false ;
		if(StringUtil.checkNotNull(dir)){
			File dirf = new File(dir) ;
			st = dirf.mkdirs() ;
		}
		return st ;
	}
	
	/**
	 * 从服务器中扫描文件，保存文件路径绝对路径
	 * @Author Derek
	 * @Date Mar 14, 2013
	 * @param path     		要扫描的目录
	 * @param stationId 	观测站ID
	 * @param fileprefix  	图像文件名中的前缀
	 * @param context
	 * @return
	 */
	@POST
	@At("/ht/sapsaveserverrealpath")
    @Ok("json")
	public JSONObject saveServerFileDirectory(String path , String stationId , 
			String fileprefix , ServletContext context){
		long start  = System.currentTimeMillis() ;
		//JSONObject json = LocalFileUtil.testServerFileDirectory(path) ;
		JSONObject json = new JSONObject() ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		
		progress = 0 ; //重置进度条值
		
		if(true){
			List<Scanpic> iglist = new ArrayList() ;           //存储解析成功的对象
			String filter = "Thumbs.db" ;//排除不解析的文件
			HashSet<String> ndyYear = new HashSet<String>() ;    //缓存往NDY表插入的年份值
			StringBuilder failFile = new StringBuilder() ; //存储解析失败的文件名
			Integer fail = 0 ;
			
			File root = new File(path) ;
			File[] years = root.listFiles() ;              //根目录下得年份目录
			int successTotal = 0 ;
			if(null != years && years.length >0){
				
				for (File y : years) {
					fail = this.parserDirectory(y, filter, fileprefix, stationId, iglist, ndyYear, failFile, fail);
					
					//每年的数据插入一次到数据库中
					/*if(iglist.size() > 0){
						successTotal += iglist.size() ;
						baseService.dao.delete(iglist) ;
						baseService.dao.insert(iglist) ;
						iglist.clear() ;
						log.info("iglist cleared size: " + iglist.size() ) ;
					}*/
					successTotal += iglist.size() ;
					iglist.clear() ;
				}
				//向ndy中插入
				for (String ye : ndyYear) {
					dls.insertNDY(tableName, stationId, null, ye) ;
				}
				
				json.put("fail", fail) ;
				json.put("failfile", failFile.toString()) ;
			}
			
			if(successTotal > 0){
				json.put("total", successTotal ) ;
				json.put(Constant.SUCCESS, true) ;
				//向日志表插入记录
				dls.insert("01", tableName, getHTLoginUserName()) ;
			}
		}
		//log.info("用时毫秒数： " + (System.currentTimeMillis() - start)) ;
		json.put("usedtime", (System.currentTimeMillis() - start)/1000) ;
		return json ;
	}
	
	private synchronized Integer parserDirectory(File directory , String filters , String fileprefix , String stationId , 
			List<Scanpic> iglist , HashSet<String> ndyYear,StringBuilder failFile ,
			Integer fail){
		if(null != directory && directory.isDirectory()){
			File[] yearFiles = directory.listFiles() ;
			for (File f : yearFiles) {
				String fn = f.getName() ;
				
				if(f.isFile() ){ //处理文件
					progress++ ;
					Scanpic sap = parserFile(f , filters, fileprefix, stationId) ;
					if(null != sap) {
						iglist.add(sap) ;
						ndyYear.add(DateUtil.getYearstrByDate(sap.getCreateDate())) ;
						//log.info( ig.getGramPath() ) ;
						baseService.dao.delete(sap) ;
						baseService.dao.insert(sap) ;
					}else{
						fail++ ;
						failFile.append(fn).append(",") ;
					}
					
				}else{ //处理目录
					this.parserDirectory(f, filters, fileprefix, stationId, iglist, ndyYear, failFile, fail) ;
				}
			}
		}
		
		return fail ;
	}
	
	public static Scanpic parserFile(File file , String filters , String fileprefix , String stationId ){
		Scanpic sac = null ;
		Date date = null ;
		String fn = file.getName() ;
		if(filters.indexOf(fn) == -1 ){
			fn = fn.replaceAll(fileprefix, "") ;
			date = DateUtil.convertStringToDate(StationUtil.removeSuffix(fn) , DateUtil.pattern3) ;
			
			if(null != date){
				String rp = file.getAbsolutePath();
				sac = new Scanpic() ;
				sac.setScanPicID(StationUtil.removeSuffix(fn)) ;
				sac.setScanPicFileName(fn) ;
				sac.setGramPath(rp ) ;
				sac.setScanPicTitle(fn) ;
				sac.setCreateDate( date ) ;
				sac.setStationID(stationId) ;
			}
		}
		
		return sac ;
	}
}
