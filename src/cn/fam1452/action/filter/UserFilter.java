package cn.fam1452.action.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.json.JsonFormat;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.UTF8JsonView;
import org.nutz.mvc.view.ViewWrapper;

import cn.fam1452.Constant;
import cn.fam1452.utils.StringUtil;

/**
 * 前台用户权限过滤器
 * Class UserFilter
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Aug 12, 2012 11:16:47 AM $
 */
public class UserFilter  implements ActionFilter {
	 /*public View match(ActionContext actionContext) {
		HttpServletRequest request = Mvcs.getReq();
		// Mvcs
		// 可用，直接加入HttpServletRequest参数，nutz能传值给它
		// return new View() {
		// @Override
		// public void render(HttpServletRequest req, HttpServletResponse resp,
		// Object obj)
		// throws Exception {
		// resp.sendRedirect("index.jsp");
		// resp.flushBuffer();
		// }
		// };

		String go = request.getParameter("go");
		if (!StringUtil.checkNotNull(go))
			return new ServerRedirectView("/index.jsp");
		if ("no".equals(go)) {
			UTF8JsonView v = new org.nutz.mvc.view.UTF8JsonView(new JsonFormat());
			v.setData("dd");// json 格式
			return v;
		}
		return null;
	}  */

	/**
	 * 
	 * @param path :
	 * seesion检查失败的跳转路径
	 */
	public UserFilter(String path) {
		 //(Constant.QT_USER_SESSION , Constant.INDEXPAGE);
		// TODO Auto-generated constructor stub
	}

	public UserFilter() {
		// (Constant.QT_USER_SESSION , Constant.INDEXPAGE);
		// TODO Auto-generated constructor stub
	}
	
	public View match(ActionContext actionContext) {
		// TODO Auto-generated method stub
		Object obj = Mvcs.getHttpSession().getAttribute(Constant.QT_USER_SESSION) ;
		if(null == obj){
			HttpServletRequest request = actionContext.getRequest();
			request.setAttribute("login", "no") ;
			View view = new ServerRedirectView(Constant.INDEXPAGE +"?login=no");
			return view; 
		}
		
		return null;
	}
	
	

}
