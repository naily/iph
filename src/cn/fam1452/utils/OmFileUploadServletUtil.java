package cn.fam1452.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.math.RandomUtils;

import cn.fam1452.action.BaseMod;

public class OmFileUploadServletUtil  extends BaseMod{
	private static final long serialVersionUID = 1L;

	// 上传文件的保存路径，相对于应用的根目录
	/**
	 * 频高图文件目录
	 */
	public static final String UPLOAD_PGT_PATH = "/data/pgt_file/";
	/**
	 * 扫描图文件目录
	 */
	public static final String UPLOAD_SAC_PATH = "/data/sac_file/";
	/**
	 * 相关文档上传图片
	 */
	public static final String UPLOAD_NEWSDOC_PATH = "/data/editor/doc/";
	/**
	 * 临时文件目录
	 */
	public static final String UPLOAD_PIC_PATH_TMP = "/data/tmp/"; 

	byte[] imgBufTemp = new byte[102401];

	private ServletContext servletContext;
	
	public OmFileUploadServletUtil(){}
	
	public OmFileUploadServletUtil (ServletContext sc){
		this.servletContext = sc  ;
	}

	/*public void init(ServletConfig config) throws ServletException {
		this.servletContext = config.getServletContext();
	}*/

	/*protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取客户端回调函数名
		response.setContentType("text/html;charset=UTF-8");
		defaultProcessFileUpload(request, response);
		if("onerror".equals(request.getParameter("testcase")))
		throw new IOException();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}*/
	/**
	 * 获得应用绝对路径
	 */
	public String getContextRealPath(){
		return servletContext.getRealPath("/"); 
	}
	
	/**
	 * 清空临时目录
	 */
	public void clearTmpDirectory(){
		String td = getContextRealPath() + UPLOAD_PIC_PATH_TMP  ;
		File tmp = new File(td) ;
		if(null != tmp && tmp.isDirectory()){
			File[] fs = tmp.listFiles() ;
			
			for (File item : fs) {
				if( item.isFile() && item.canWrite()){
					item.delete();
				}
			}
		}else{
			log.info("不是一个有效路径或目录: "+ td ) ;
		}
	}
	
	/**
	 * 从临时目录中检索指定的文件名，若存在，克隆到指定的其它目录
	 * @param tmpFileName
	 * @param otherDir
	 */
	public boolean cloneTmpFile2Other(String tmpFileName , String otherDir){
		boolean st = false ;
		String tmpfile = getContextRealPath() + UPLOAD_PIC_PATH_TMP +  tmpFileName ; 
		File tf = new File(tmpfile) ;
		
		st = cloneTmpFile2Other(tf , otherDir) ;
		
		return st ;
	}
	private File targetFile  ;
	public File getTargetFile(){
		return this.targetFile ;
	}
	public boolean cloneTmpFile2Other(File src , String otherDir){
		boolean st = false ;
		
		if(null != src && src.isFile()){
			File ot = new File(otherDir) ;
			if(ot.isDirectory()){
				//String suffix = this.getFileSuffix(src) ;
				//File dest = new File(otherDir + System.currentTimeMillis() + suffix ) ;
				File dest = new File(otherDir + src.getName() ) ;
				if(!dest.exists()){
					st = src.renameTo(dest) ;
				}else{
					st = true ; //文件已经存在
				}
				targetFile = dest ;
			}
		}
		
		return st ;
	}
	/**
	 * 
	 * @param src 源文件
	 * @param otherDir 目标目录
	 * @param rename   是否重命名
	 * @return         克隆成功返回true
	 */
	public boolean cloneTmpFile2Other(File src , String otherDir , boolean rename){
		boolean st = false ;
		
		if(null != src && src.isFile()){
			File ot = new File(otherDir) ;
			if(ot.isDirectory()){
				File dest = null ;
				if(rename){
					String suffix = this.getFileSuffix(src) ;
					dest = new File(otherDir + System.currentTimeMillis() + suffix ) ;
					
				}else{
					
					dest = new File(otherDir + src.getName() ) ;
				}
				if(!dest.exists()){
					st = src.renameTo(dest) ;
				}else{
					st = true ; //文件已经存在
				}
				targetFile = dest ;
			}
		}
		
		return st ;
	}
	/**
	 * 获取文件后缀名（带.）
	 * @param f
	 * @return
	 */
	public String getFileSuffix(File f){
		String fn = "" ;
		if(null != f && f.exists()){
			fn = f.getName() ;
			int i = fn.lastIndexOf(".") ;
			if(-1 != i){
				fn = fn.substring(i) ; //取到带点的扩展名
			}
		}
		
		return fn ;
	}
	
	
	/**
	 * 生成保存上传文件的磁盘路径 
	 * 
	 * @param fileName
	 * @return
	 */
	public String getSavePath(String fileName) {
		String realPath = getContextRealPath() ;
		return realPath + UPLOAD_PIC_PATH_TMP + fileName;
	}
	/**
	 * 生成保存上传文件的磁盘路径(保存在临时目录)
	 * @param fileName
	 * @return
	 */
	public String getSaveTempPath(String fileName) {
		return getContextRealPath() + UPLOAD_PIC_PATH_TMP + fileName;
	}

	/**
	 * 生成访问上传成功后的文件的URL地址
	 * 
	 * @param fileName
	 * @return
	 */
	public String getFileUrl(String fileName) {
		return UPLOAD_PIC_PATH_TMP + fileName;
	}
	
	/**
	 * 从request中取出文件
	 * @param request
	 * @param temp --> true表示把文件存入临时目录
	 * @return
	 * @throws IOException
	 */
	public String defaultProcessFileUpload(HttpServletRequest request , boolean temp) throws IOException {
		ServletFileUpload sfu = new ServletFileUpload();
		sfu.setHeaderEncoding("UTF-8");
		
		InputStream stream = null;
		BufferedOutputStream bos = null;
		String fileUrl = "";
		String savePath = ""; //文件保存路径
		
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				FileItemIterator iter = sfu.getItemIterator(request);
				
				int i = 0;
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					stream = item.openStream();
					if (!item.isFormField()) {
						//String prefix = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" +RandomUtils.nextInt();
						// 得到文件的扩展名(无扩展名时将得到全名)
						//String ext = item.getName().substring(item.getName().lastIndexOf(".") + 1);
						//String fileName = prefix + "." + ext;
						
						String fileName = item.getName() ;
						if(temp){
							savePath = getSaveTempPath(fileName);
							fileUrl = UPLOAD_PIC_PATH_TMP + fileName;
							
							bos = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
							int length;
							while ((length = stream.read(imgBufTemp)) != -1) {
								bos.write(imgBufTemp, 0, length);
							}
							i++;
						}
						
						
					}
				}
				
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
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
		
		return fileUrl ;
	}
	
	/**
	 * request中取出文件并存储到指定目录
	 * @param request
	 * @param dir
	 * @param rename 是否重命名文件
	 * @return 返回文件真实路径
	 * @throws IOException
	 */
	public String defaultProcessFileUpload(HttpServletRequest request , String  dir , boolean rename) throws IOException {
		ServletFileUpload sfu = new ServletFileUpload();
		sfu.setHeaderEncoding("UTF-8");
		
		InputStream stream = null;
		BufferedOutputStream bos = null;
		String savePath = ""; //文件保存路径
		
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				
				FileItemIterator iter = sfu.getItemIterator(request);
				
				int i = 0;
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					stream = item.openStream();
					if (!item.isFormField()) {
						
						String fileName = item.getName() ;
						if(rename){
							fileName = System.currentTimeMillis() + FileUtil.getFileSuffix(fileName) ;
						}
						
						savePath = dir + fileName ;
						
						bos = new BufferedOutputStream(new FileOutputStream(new File(getContextRealPath() + savePath)));
						int length;
						while ((length = stream.read(imgBufTemp)) != -1) {
							bos.write(imgBufTemp, 0, length);
						}
						i++;
					}
				}
				
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
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
		
		return savePath ;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
