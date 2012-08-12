package cn.fam1452.action.filter;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.filter.CheckSession;

/**
 * 前台用户权限过滤器
 * Class UserFilter
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Aug 12, 2012 11:16:47 AM $
 */
public class UserFilter extends CheckSession{

	public UserFilter(String name, String path) {
		super(name, path);
		// TODO Auto-generated constructor stub
	}


}
