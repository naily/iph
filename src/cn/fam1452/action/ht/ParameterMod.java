/**
 * 描述：电离层参数管理
 */
package cn.fam1452.action.ht;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.fam1452.action.BaseMod;

/**
 * Class ParameterMod
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jun 24, 2012 10:04:31 AM $
 */
@IocBean
public class ParameterMod extends BaseMod{
	
	@At("/ht/pam")
    @Ok("jsp:jsp.ht.pam")
	public void loadSavePages(){}
	
	@At("/ht/pamlist")
    @Ok("jsp:jsp.ht.pamlist")
	public void loadListPages(){}
}
