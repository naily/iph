/**
 * 
 */
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
	 * 保存文件的绝对路径到数据库中
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
						gram.setGramPath(getAppRealPath(context) + fusu.UPLOAD_PGT_PATH + gram.getGramFileName()); 
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
			String filepath = getAppRealPath(context) + fusu.defaultProcessFileUpload(request , fusu.UPLOAD_PGT_PATH , false); //存入实际目录
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
	public JSONObject testServerFileDirectory(String path  , String datatype){
		JSONObject json = new JSONObject() ; 
		if("1".equals(datatype)){
			json = LocalFileUtil.testServerFileDirectory(path) ;
		}
		if("2".equals(datatype)){
			json = LocalFileUtil.testServerFileDirectory2(path) ;
		}
		
		/*
		if(null != json && json.getBoolean(Constant.SUCCESS)){
			String fn = json.getString(Constant.INFO) ;
			//log.info(fn) ;l
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
		}
		*/
		
		json.put("path", path) ;
		json.put("pgttype", datatype);
		return json ;
	}
	/**
	 * 获取文件处理进度
	 * @Author Derek
	 * @Date Feb 17, 2013
	 * @return
	 */
	@POST
	@At("/ht/getpgtprogress")
    @Ok("json")
	public JSONObject getPgtProgress( int total){
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
	 * 从服务器扫描文件，保存到应用程序目录内
	 * @Author Derek
	 * @Date Mar 14, 2013
	 * @param path
	 * @param stationId
	 * @param fileway 文件处理方式：复制、剪切 ？
	 * @param fileprefix
	 * @param datatype
	 * @param context
	 * @return
	 */
	@POST
	@At("/ht/pgtsaveserverpath")
    @Ok("json")
	public JSONObject saveServerFileDirectory(String path , String stationId , String fileway , 
			String fileprefix , String datatype , ServletContext context){
		long start  = System.currentTimeMillis() ;
		JSONObject json = LocalFileUtil.testServerFileDirectory(path) ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		
		progress = 0 ; //重置
		
		if(null != json && json.getBoolean(Constant.SUCCESS)){
			StringBuilder failFile = new StringBuilder() ;
			List<IronoGram> iglist = new ArrayList() ;
			File root = new File(path) ;
			
			File[] years = root.listFiles() ;
			if(null != years && years.length >0){
				int fail = 0 ;
				
				String filter = "Thumbs.db" ;
				//手动频高图解析
				if("1".equals(datatype)){
					HashSet<String> ndyYear = new HashSet<String>() ;
					for (File y : years) {
						if(null != y && y.isDirectory()){
							String year = y.getName() ;
							this.createDirectory(this.getAppRealPath(context) + fusu.UPLOAD_PGT_PATH + year) ;
							
							File[] yearFiles = y.listFiles() ;
							if(null != yearFiles){
								for (File f : yearFiles) {
									//处理文件复制或者是剪切
									progress++ ;
									String fn = f.getName() ;
									Date date = null ;
									if(!filter.equals(fn)){
										fn = fn.replace(fileprefix, "") ;
										date = DateUtil.convertStringToDate(StationUtil.removeSuffix(fn) , DateUtil.pattern5) ;
									}
									if(null != date){
										boolean ok = false ;
										if("copy".equals(fileway)){
											ok = this.copyFile(f, this.getAppRealPath(context) + fusu.UPLOAD_PGT_PATH + year +"/" ) ;
										}else if("cut".equals(fileway)){
											ok = this.cutFile(f, this.getAppRealPath(context) + fusu.UPLOAD_PGT_PATH + year +"/") ;
										}
										
										if(ok){
											IronoGram ig = this.createIronoGram(f.getName(), datatype, stationId, date, fusu.UPLOAD_PGT_PATH + year + "/" + f.getName()) ;
											iglist.add(ig) ;
											
											ndyYear.add(DateUtil.getYearstrByDate(ig.getCreateDate())) ;
										}
									}else{
										fail++ ;
										failFile.append(fn).append(",") ;
									}
								}
							}
							
							
						}
					}
					//向ndy中插入
					for (String ye : ndyYear) {
						dls.insertNDY(tableName, stationId, null, ye) ;
					}
				}
				//胶片频高图解析路径不一样
				if("2".equals(datatype)){
					for (File y : years) {
						String year = y.getName() ;
						if(null != y && y.isDirectory()){
							this.createDirectory(this.getAppRealPath(context) + fusu.UPLOAD_PGT_PATH + year) ;
							
							File[] months = y.listFiles() ; //得到月份
							for (File m : months) {
								String month = m.getName() ;
								this.createDirectory(this.getAppRealPath(context) + fusu.UPLOAD_PGT_PATH + year + "/" +month) ;
								
								File[] days = m.listFiles() ; //得到天数
								for (File d : days) {
									String day = d.getName() ;
									this.createDirectory(this.getAppRealPath(context) + fusu.UPLOAD_PGT_PATH + year + "/" +month + "/" + day) ;
									File[] dayFiles = d.listFiles() ;
									for (File f : dayFiles) {
										//处理文件复制或者是剪切
										progress++ ;
										String fn = f.getName() ;
										fn = fn.replace(fileprefix, "") ;
										Date date = null ;
										if(!filter.equals(fn)){
											date = DateUtil.convertStringToDate(StationUtil.removeSuffix(fn) , DateUtil.pattern4) ;
										}
										if(null != date){
											boolean ok = false ;
											if("copy".equals(fileway)){
												ok = this.copyFile(f, this.getAppRealPath(context) + fusu.UPLOAD_PGT_PATH + year + "/" +month + "/" + day +"/" ) ;
											}else if("cut".equals(fileway)){
												ok = this.cutFile(f, this.getAppRealPath(context) + fusu.UPLOAD_PGT_PATH + year + "/" +month + "/" + day +"/") ;
											}
											if(ok){
												IronoGram ig = this.createIronoGram(f.getName() , datatype, stationId, date, fusu.UPLOAD_PGT_PATH + year + "/" +month + "/" + day + "/" + f.getName()) ;
												iglist.add(ig) ;
											}
										}else{
											fail++ ;
											failFile.append(fn).append(",") ;
										}
									}
								}
							}
							
							dls.insertNDY(tableName, stationId, null, year) ;
						}
					}
				}
				
				
				/*for (File sf : years) { //
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
				}*/
				json.put("fail", fail) ;
				json.put("total", iglist.size() ) ;
				
				json.put("failfile", failFile.toString()) ;
			}
			
			if(iglist.size() > 0){
				baseService.dao.delete(iglist) ;
				baseService.dao.insert(iglist) ;
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
	private IronoGram createIronoGram(String filename , String pgttype , String sid , Date cdate , String filepath){
		IronoGram ig = new IronoGram() ;
		ig.setGramID(StationUtil.removeSuffix(filename)) ;
		ig.setGramFileName(filename) ;
		ig.setGramPath(filepath ) ;
		ig.setGramTitle(filename) ;
		
		ig.setCreateDate( cdate) ;
		ig.setType( pgttype ) ;
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
	 * @param path
	 * @param stationId
	 * @param fileprefix
	 * @param datatype
	 * @param context
	 * @return
	 */
	@POST
	@At("/ht/pgtsaveserverrealpath")
    @Ok("json")
	public JSONObject saveServerFileDirectory(String path , String stationId , 
			String fileprefix , String datatype , ServletContext context){
		long start  = System.currentTimeMillis() ;
		//JSONObject json = LocalFileUtil.testServerFileDirectory(path) ;
		JSONObject json = new JSONObject() ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		
		progress = 0 ; //重置进度条值
		
		if(true){
			List<IronoGram> iglist = new ArrayList() ;           //存储解析成功的对象
			String filter = "Thumbs.db" ;//排除不解析的文件
			HashSet<String> ndyYear = new HashSet<String>() ;    //缓存往NDY表插入的年份值
			StringBuilder failFile = new StringBuilder() ; //存储解析失败的文件名
			Integer fail = 0 ;
			
			File root = new File(path) ;
			File[] years = root.listFiles() ;              //根目录下得年份目录
			int successTotal = 0 ;
			if(null != years && years.length >0){
				
				for (File y : years) {
					fail = this.parserDirectory(y, filter, fileprefix, stationId, datatype, iglist, ndyYear, failFile, fail);
					
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
				/*
				//手动频高图解析
				if("1".equals(datatype)){
				}
				//胶片频高图解析路径不一样
				if("2".equals(datatype)){
				*/
				
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
			String datatype , List<IronoGram> iglist , HashSet<String> ndyYear,StringBuilder failFile ,
			Integer fail){
		if(null != directory && directory.isDirectory()){
			File[] yearFiles = directory.listFiles() ;
			for (File f : yearFiles) {
				String fn = f.getName() ;
				
				if(f.isFile() ){ //处理文件
					progress++ ;
					IronoGram ig = parserFile(f , filters, fileprefix, stationId, datatype) ;
					if(null != ig) {
						iglist.add(ig) ;
						ndyYear.add(DateUtil.getYearstrByDate(ig.getCreateDate())) ;
						//log.info( ig.getGramPath() ) ;
						baseService.dao.delete(ig) ;
						baseService.dao.insert(ig) ;
					}else{
						fail++ ;
						failFile.append(fn).append(",") ;
					}
					
				}else{ //处理目录
					this.parserDirectory(f, filters, fileprefix, stationId, datatype, iglist, ndyYear, failFile, fail) ;
				}
			}
		}
		
		return fail ;
	}
	/**
	 * 解析文件得到IronoGram对象
	 * @param fileprefix 文件名中的前缀
	 * @return
	 */
	public static IronoGram parserFile(File file , String filters , String fileprefix , String stationId , String datatype){
		IronoGram ig = null ;
		Date date = null ;
		String fn = file.getName() ;
		if(filters.indexOf(fn) == -1 ){
			fn = fn.replaceAll(fileprefix, "") ;
			//手动频高图解析
			if("1".equals(datatype)){
				date = DateUtil.convertStringToDate(StationUtil.removeSuffix(fn) , DateUtil.pattern5) ;
			}
			//胶片频高图解析路径不一样
			else if("2".equals(datatype)){
				date = DateUtil.convertStringToDate(StationUtil.removeSuffix(fn) , DateUtil.pattern4) ;
			}
			
			if(null != date){
				String rp = file.getAbsolutePath();
				ig = new IronoGram() ;
				ig.setGramID(StationUtil.removeSuffix(fn)) ;
				ig.setGramFileName(fn) ;
				ig.setGramPath(rp ) ;
				ig.setGramTitle(fn) ;
				
				ig.setCreateDate( date ) ;
				ig.setType( datatype ) ;
				ig.setStationID(stationId) ;
				//ig = this.createIronoGram(file.getName(), datatype, stationId, date,  rp ) ;
			}
		}
		
		return ig ;
	}
}
