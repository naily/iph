package cn.fam1452.action.filter;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

import cn.fam1452.Constant;

/**
 * 后台管理员权限过滤器
 * Class AdminFilter
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Aug 12, 2012 11:17:32 AM $
 */
public class AdminFilter implements ActionFilter{
	//菜单栏
	private final static String[] admin_menu_bar = {""} ;
	//仅仅超级管理员可用的菜单
	private final static String[] superadmin_menu = {"admins", "admindel","adminsave"} ;
	private String loginpath = ""; //默认登录地址
	private String lackpath = "" ; //权限不足
	
	public View match(ActionContext actionContext) {
		// TODO Auto-generated method stub
		Object obj = Mvcs.getHttpSession().getAttribute(Constant.HT_USER_SESSION) ;
		if(null == obj){
			return new ServerRedirectView(loginpath) ;
		}else{
			String reqPath = actionContext.getPath() ;
			try {
				Object sp = PropertyUtils.getSimpleProperty(obj, "isSuper") ;
				if(null != sp && "true".equals(sp.toString())){
					return null  ;
				}else{
					for (String sm : superadmin_menu) {
						if(reqPath.endsWith(sm) ){
							return new ServerRedirectView(lackpath) ;
						}
					}
				}
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return null;
	}

}
