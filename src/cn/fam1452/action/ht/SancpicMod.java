package cn.fam1452.action.ht;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.fam1452.action.BaseMod;

/**
 * 扫描图管理
 * Class SancpicMod
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jun 24, 2012 5:06:09 PM $
 */
@IocBean
public class SancpicMod extends BaseMod{
	
	@At("/ht/sac")
    @Ok("jsp:jsp.ht.sac")
	public void loadSavePages(){}
	
	@At("/ht/saclist")
    @Ok("jsp:jsp.ht.saclist")
	public void loadListPages(){}
}
