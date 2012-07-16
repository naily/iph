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
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.MetaDataBo;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.dao.pojo.MetaData;
import cn.fam1452.dao.pojo.News;
import cn.fam1452.dao.pojo.Parameter;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.DateUtil;
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
public class NewsMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/ht/news")
    @Ok("jsp:jsp.ht.news")
    public void loadnews(HttpServletRequest req){}
	@At("/ht/newslist")
    @Ok("jsp:jsp.ht.newslist")
    public void loadnewslist(HttpServletRequest req){}
	
	@POST
	@At("/ht/newsSave")
    @Ok("json")
	public JSONObject save(@Param("..")News news  ,HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		try {
			//姑且只有js验证
			if(null != news){
				news.setNewsId(String.valueOf(System.currentTimeMillis())) ;
				log.info(news.isPicNews()) ;
				log.info(news.getContent()) ;
				
				if(StringUtil.checkNotNull(news.getNewsId()) ){ //&& baseService.dao.fetch(news) == null
					baseService.dao.insert(news) ;
					json.put(Constant.SUCCESS, true) ;
				}else{
					json.put(Constant.INFO, "该记录已存在") ;
				}
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

		List<MetaData>  list = baseService.dao.query(MetaData.class, null, page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(MetaData.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "thumbnail", "fullContent" , "mdDate"  }); 
		JSONArray array = new JSONArray();
		
		for(MetaData g : list ){
			JSONObject item = new JSONObject();
			
			item = JSONObject.fromObject(g , cfg) ;
			item.put("mdDate", DateUtil.convertDateToString(g.getMdDate(), DateUtil.pattern2)) ;
			
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
			
			List<MetaData> igs = new ArrayList<MetaData>() ;
			for (String id : idss) {
				MetaData ig = new MetaData();
				ig.setMdId (id) ;
				
				ig = baseService.dao.fetch(ig) ;
				
				if(null != ig){
					igs.add(ig) ;
					
					String fp = this.getAppRealPath(context) + ig.getFullContentFilePath() ;
					File f = new File(fp) ;
					if(f.exists() && f.isFile()){
						f.delete() ;
					}
				}
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
	public JSONObject updatenews(@Param("..")MetaData params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getMdId()) && null != baseService.dao.fetch(params)){
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
    public MetaData get(String id){
		MetaData ig = null ;
		if(StringUtil.checkNotNull(id)){
			ig = baseService.dao.fetch(MetaData.class, id) ;
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
    public String getFileUrl(String fileName){
    	return "userfiles/images/" + fileName;
    }
    
    private static final String UPLOAD_PIC_PATH = "/data/editor/images/";
  	private static final long MAX_SIZE = 100000;// 设置上传文件最大为 100KB
  	private byte[] imgBufTemp = new byte[102401];
  	private String OMEditorFuncNum ;
  	
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
                    	fileUrl = getFileUrl(fileName);
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
            	script.append("图片大小不能超过100KB");
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
}
