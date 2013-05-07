package cn.fam1452.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.nutz.mvc.Mvcs;

import cn.fam1452.Constant;
import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.dao.pojo.User;
import cn.fam1452.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class BaseMod {
	
	protected Logger log = Logger.getLogger(this.getClass()) ;  
	
	//i18n ， 通过key取得语言字符串
	protected String getMsgByKey(HttpServletRequest req , String key){
		Map<String,String> msgmap  = (Map<String,String>)req.getAttribute ("msg") ;
//		Map<String,String> msgmap  =  msgmap = (Map<String,String>)req.getAttribute ("msg") ;
		return msgmap.get(key) ;
	}
	
	//获取当前应用root/ 磁盘绝对路径
	protected String getAppRealPath(ServletContext servletContext) {
		return  servletContext.getRealPath("/");
	}
	
	/**
	 * 获取后台登陆用户的用户名
	 * @return
	 */
	protected String getHTLoginUserName() {
		HttpSession session = Mvcs.getHttpSession() ;
		String name = "" ;
		if(null != session){
			Administrator admin = (Administrator)session.getAttribute(Constant.HT_USER_SESSION)  ;
			if(null != admin){
				name = admin.getLoginId() ;
			}
		}
		return  name;
	}
	/**
	 * 获取前台登陆用户的用户id
	 * @return
	 */
	protected String getQTLoginUserID() {
		HttpSession session = Mvcs.getHttpSession() ;
		String userId = "" ;
		if(null != session){

			User user = (User)session.getAttribute(Constant.QT_USER_SESSION);
			if(null != user){
				userId = user.getLoginId() ;
			}
		}
		return  userId;
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
	
	/**
	 * 删除失败
	 */
	protected final String error1 = "删除失败" ; 
	/**
	 * 对象不存在
	 */
	protected final String error2 = "对象不存在" ; 

	/**
	 * ID为空
	 */
	protected final String error3 = "ID为空" ; 
	
	/**
	 * 文件已经存在
	 */
	protected final String error4 = "文件已经存在" ; 
	/**
	 * 文件保存失败
	 */
	protected final String error5 = "文件保存失败" ; 
	/**
	 * 文件名为空
	 */
	protected final String error6 = "文件名为空" ; 
	
	/**
	 * 记录已经存在
	 */
	protected final String error7 = "记录已经存在" ;
	
	/**
	 * 参数错误
	 */
	protected final String error8 = "参数错误" ;
	
	public static Map<String, String> tableMap = new HashMap<String, String>()  ;
	public BaseMod(){
		tableMap.put("T_IRONOGRAM", "频高图表(T_IRONOGRAM)") ;
		tableMap.put("T_PARAMETER", "电离参数数据表") ;
		tableMap.put("T_SCANPIC", "原始观测报表(T_SCANPIC)") ;
		tableMap.put("T_METADATA", "元数据表(T_METADATA)") ;
	}
}
