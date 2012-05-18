/**
 * 描述：
 */
package cn.fam1452.action.ht;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

/**
 * Class User
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 18, 2012 10:59:23 PM $
 */

public class User {
	
	
	@At("/ht/index")
    @Ok("jsp:jsp.ht.login")
    public void loadlogin(){
		
	}
}
