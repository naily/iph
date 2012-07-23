/**
 * 
 */
package cn.fam1452.action.qt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.dao.util.blob.SimpleBlob;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.service.BaseService;

/**
 * @author 
 *
 */
@IocBean
public class MetaDataMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	

	@At("/qt/mataDataList")
    @Ok("json")
    public JSONObject loadIndexMetaData(HttpServletRequest req){
		
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		return json;
	}
}
