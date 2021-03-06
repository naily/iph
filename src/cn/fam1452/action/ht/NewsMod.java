/**
 * 
 */
package cn.fam1452.action.ht;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Blob;
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

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.jdbc.BlobValueAdaptor;
import org.nutz.dao.util.blob.SimpleBlob;
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
import cn.fam1452.action.bo.MetaDataBo;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.filter.AdminFilter;
import cn.fam1452.dao.pojo.IronoGram;
import cn.fam1452.dao.pojo.MetaData;
import cn.fam1452.dao.pojo.News;
import cn.fam1452.dao.pojo.Parameter;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.FileDownload;
import cn.fam1452.utils.OmFileUploadServletUtil;
import cn.fam1452.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @author zdd
 *
 */
@IocBean
@Filters(@By(type=AdminFilter.class ))
public class NewsMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/ht/news")
    @Ok("jsp:jsp.ht.news")
    public void loadNews(HttpServletRequest req){}
	@At("/ht/newslist")
    @Ok("jsp:jsp.ht.newslist")
    public void loadNewsList(HttpServletRequest req){}
	
	@At("/ht/preview")
    @Ok("jsp:jsp.ht.newstempl")
    public News loadNewsPreview(HttpServletRequest req,long nid){
		News n = new News() ;
		if( nid > 0 ){
			n = baseService.dao.fetch(News.class, nid) ;
		}
		return n ;
	}
	
	@POST
	@At("/ht/newsSave")
    @Ok("json")
	public JSONObject save(@Param("..")News news  ,HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		try {
			//姑且只有js验证
			if(null != news){
				//news.setNewsId(String.valueOf(System.currentTimeMillis())) ;
				news.setPublishDate(DateUtil.getCurrentDate()) ;
//				log.info(news.isPicNews()) ;
//				log.info(news.getContent()) ;
				if(!StringUtil.checkNotNull(news.getCategory()) ){
					news.setCategory("1") ; //默认类别
				}
				baseService.dao.insert(news) ;
				json.put(Constant.SUCCESS, true) ;
				/*if( news.getNewsId() > 0){ //&& baseService.dao.fetch(news) == null
				}else{
					json.put(Constant.INFO, "该记录已存在") ;
				}*/
			}else{
				json.put(Constant.INFO, "参数为空") ;
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
	@At("/ht/newslist")
    @Ok("json")
	public JSONObject list(@Param("..")Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;

		List<News>  list = baseService.dao.query(News.class, Cnd.orderBy().desc("publishDate"), page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(News.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "content", "publishDate" , "picture"  }); 
		JSONArray array = new JSONArray();
		
		for(News g : list ){
			JSONObject item = new JSONObject();
			
			item = JSONObject.fromObject(g , cfg) ;
			item.put("publishDate", DateUtil.convertDateToString(g.getPublishDate() == null  ? DateUtil.getCurrentDate() : g.getPublishDate() , DateUtil.pattern2)) ;
			
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	@POST
	@At("/ht/newsdel")
    @Ok("json")
	public JSONObject deletenews(String ids , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(ids) ){
			String[] idss = ids.split(";") ;
			
			List<News> igs = new ArrayList<News>() ;
			for (String id : idss) {
				News n = new News();
				n.setNewsId(Long.parseLong(id)) ;
				
				n = baseService.dao.fetch(n) ;
				igs.add(n) ;
			}
			
			if(null != igs && igs.size() > 0 ){
				if( baseService.dao.delete(igs) == igs.size() ){
					json.put(Constant.SUCCESS, true) ;
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
	@At("/ht/newsupdate")
    @Ok("json")
	public JSONObject updatenews(@Param("..")News params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if( params.getNewsId() > 0 && null != baseService.dao.fetch(params)){
			int  i = baseService.dao.update(params) ;
			json.put(Constant.SUCCESS, true ) ;
		}else{
			json.put(Constant.INFO, error2) ;
		}
		return json ;
	}
	
	
	
	@POST
	@At("/ht/newsget")
    @Ok("json")
    public News get(long id){
		News ig = null ;
		if( id > 0 ){
			ig = baseService.dao.fetch(News.class, id) ;
		}
		
		return ig ;
	}
	
	
	/**
     * 生成保存上传文件的磁盘路径
     * @param fileName
     * @return
     */
    public String getSavePath(String fileName , ServletContext servletContext) {
        String realPath = this.getAppRealPath(servletContext);
        return realPath + UPLOAD_PIC_PATH + fileName;
    }
    /**
     * 生成访问上传成功后的文件的URL地址
     * @param fileName
     * @return
     */
    public String getFileUrl(String fileName,HttpServletRequest request){
    	return request.getContextPath() + UPLOAD_PIC_PATH + fileName; 
    }
    
    private static final String UPLOAD_PIC_PATH = "/data/editor/images/";
  	private static final long MAX_SIZE = 300000;// 设置上传文件最大为 100KB
  	private byte[] imgBufTemp = new byte[102401];
  	private String OMEditorFuncNum ;
  	
	@At("/ht/omEditorImageUpload")
  	public void uploadEditorImages(String OMEditorFuncNum ,
  			HttpServletRequest request, HttpServletResponse response , ServletContext servletContext){
  		this.OMEditorFuncNum = OMEditorFuncNum ;
  		if(StringUtil.checkNotNull(this.OMEditorFuncNum)){
  			try {
				this.defaultProcessFileUpload(request, response, servletContext) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error(e.getMessage()) ;
			}
  		}
  	}
  	                                 
  	
    private void defaultProcessFileUpload(HttpServletRequest request, HttpServletResponse response , ServletContext servletContext)
            throws IOException {
        ServletFileUpload upload = new ServletFileUpload();
        upload.setHeaderEncoding("UTF-8");
        InputStream stream = null;
        BufferedOutputStream bos = null;
        String fileUrl = "";
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
            	// 设置上传文件大小的最大限制为100KB
                upload.setSizeMax(MAX_SIZE);
                FileItemIterator iter = upload.getItemIterator(request);
                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                    stream = item.openStream();
                    if (!item.isFormField()) {
                        String prefix = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                        // 得到文件的扩展名(无扩展名时将得到全名)  
                        String ext = item.getName().substring(item.getName().lastIndexOf(".") + 1);
                        String fileName = prefix + "." + ext;
                    	String savePath = getSavePath(fileName , servletContext );
                    	fileUrl = getFileUrl(fileName , request);
                        bos = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
                        int length;
                        while ((length = stream.read(imgBufTemp)) != -1) {
                            bos.write(imgBufTemp, 0, length);
                        }
                    }
                }
                StringBuilder script = new StringBuilder();
                // 执行客户端回调函数
                script.append("<script type=\"text/javascript\">");
                script.append("window.parent.OMEDITOR.tools.callFunction(");
                script.append(OMEditorFuncNum);
                script.append(", '");
                script.append(fileUrl);
                script.append("', '')");
                script.append("</script>");
                
                response.getWriter().write(script.toString());
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            StringBuilder script = new StringBuilder();
            script.append("<script type=\"text/javascript\">");
            script.append("alert('");
            if(e instanceof SizeLimitExceededException){
            	script.append("Photo size can't more than 300 KB");
            }else{
            	script.append(e.getMessage());
            }
            script.append("');");
            script.append("history.go(-1);");
            script.append("</script>");
            response.getWriter().write(script.toString());
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                }
            }
        }
    }
    
    
    /**
     * 上传相关文档页面
     * @Author Derek
     * @Date Mar 17, 2013
     * @param req
     */
    @Filters
    @At("/ht/news/docdown")
    @Ok("raw")
    public void docDownloadFile(HttpServletRequest req ,HttpServletResponse response, String fn , String dn){
    	
    	if(StringUtil.checkNotNull(fn) && StringUtil.checkNotNull(dn)){
			try {
				//byte[] l_b_Proc = dn.getBytes("ISO-8859-1");
				dn = new String(dn.getBytes("ISO-8859-1"), StringUtil.UTF_8);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		//System.out.println(dn);
    		
    		String file = OmFileUploadServletUtil.UPLOAD_NEWSDOC_PATH + fn ;
    		//String downloadfile = req.getSession().getServletContext().getRealPath(file);
    		try {
				FileDownload.fileDownLoad(req, response ,file , dn) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    /**
	 * 上传一个文件 
	 */
	@POST
	@At("/ht/docfileuploads")
    @Ok("json")
	public JSONObject saveNewsDoc (HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		
		try {
			String file = fusu.defaultProcessFileUpload(request , fusu.UPLOAD_NEWSDOC_PATH , true) ;
			if(StringUtil.checkNotNull(file)){
				file = file.replace(fusu.UPLOAD_NEWSDOC_PATH, "") ;
			}
			//log.info(file) ;
			json.put("path", file) ;
			json.put(Constant.SUCCESS, true) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put(Constant.INFO, e.getMessage()) ;
		}finally{
			return json ;
		}
	}
    
}
