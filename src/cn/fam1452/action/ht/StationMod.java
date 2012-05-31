/**
 * 描述：观测站
 */
package cn.fam1452.action.ht;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.fam1452.action.BaseMod;

/**
 * Class StationMod
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 31, 2012 9:54:31 PM $
 */
@IocBean
public class StationMod extends BaseMod{
	
	@At("/ht/stationload")
    @Ok("jsp:jsp.ht.stations")
    public void loadlogin(HttpServletRequest req){
		
	}
}
