/**
 * 
 */
package cn.fam1452.action.ht;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
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
import cn.fam1452.dao.pojo.IronoGram;
import cn.fam1452.utils.OmFileUploadServletUtil;

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
	@POST
	@At("/ht/pgtuploads")
    @Ok("json")
	public JSONObject saveSingleton (@Param("..")IronoGram gram  ,HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		try {
			fusu.defaultProcessFileUpload(request) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json ;
	}
}
