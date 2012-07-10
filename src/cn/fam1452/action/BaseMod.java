package cn.fam1452.action;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.fam1452.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class BaseMod {
	
	protected Logger log = Logger.getLogger(this.getClass()) ;  
	
	//i18n ， 通过key取得语言字符串
	protected String getMsgByKey(HttpServletRequest req , String key){
		Map<String,String> msgmap  =  msgmap = (Map<String,String>)req.getAttribute ("msg") ;
		return msgmap.get(key) ;
	}
	
	//获取当前应用root/ 磁盘绝对路径
	protected String getAppRealPath(ServletContext servletContext) {
		return  servletContext.getRealPath("/");
	}
	
	/**
	 * 初始化freemarker
	 * @param context
	 * @return
	 */
	private Configuration cfg  = new Configuration() ;
	protected Configuration initFreeMarker(ServletContext context){
		try {
//			cfg.setClassForTemplateLoading(clazz, pathPrefix)
			cfg.setDirectoryForTemplateLoading(new File(getAppRealPath(context) + "ftl") ) ;
			cfg.setObjectWrapper(new DefaultObjectWrapper() ) ;
//			cfg.setEncoding(locale, encoding) ;
			cfg.setDefaultEncoding(StringUtil.UTF_8) ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  cfg ;
	}
	
	
	protected final String error1 = "删除失败" ; 
	protected final String error2 = "对象不存在" ; 

	protected final String error3 = "ID为空" ; 
	
	protected final String error4 = "文件已经存在" ; 
	protected final String error5 = "文件保存失败" ; 
	protected final String error6 = "文件名为空" ; 
	
	protected final String error7 = "记录已经存在" ;
	
}
