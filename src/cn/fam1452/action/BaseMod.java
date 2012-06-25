package cn.fam1452.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class BaseMod {
	
	protected Logger log = Logger.getLogger(this.getClass()) ;  
	
	//i18n ， 通过key取得语言字符串
	protected String getMsgByKey(HttpServletRequest req , String key){
		Map<String,String> msgmap  =  msgmap = (Map<String,String>)req.getAttribute ("msg") ;
		return msgmap.get(key) ;
	}
	
	//获取当前应用root/ 磁盘绝对路径
	protected String getSavePath(ServletContext servletContext) {
		return  servletContext.getRealPath("/");
	}
	
	protected final String error1 = "删除失败" ; 
	protected final String error2 = "对象不存在" ; 

	protected final String error3 = "ID为空" ; 
	
	protected final String error4 = "文件已经存在" ; 
	protected final String error5 = "文件保存失败" ; 
	protected final String error6 = "文件名为空" ; 
	
}
