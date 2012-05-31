/**
 * 
 */
package cn.fam1452.action.ht;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import cn.fam1452.action.BaseMod;

/**
 * @author zdd
 *
 */
@IocBean
public class PgtMod extends BaseMod{

	@At("/ht/pgt")
    @Ok("jsp:jsp.ht.pgt")
    public void loadPgt(HttpServletRequest req){}
	
	/**
	 * 上传单个高频图文件 
	 */
	@At("/ht/pgtuploads")
    @Ok("json")
	public JSONObject saveSingleton (HttpServletRequest request, HttpServletResponse response){
		JSONObject json = new JSONObject();
		
		return json ;
	}
}
