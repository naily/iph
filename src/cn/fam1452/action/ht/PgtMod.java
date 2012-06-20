/**
 * 
 */
package cn.fam1452.action.ht;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.dao.pojo.IronoGram;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.OmFileUploadServletUtil;
import cn.fam1452.utils.StringUtil;

/**
 * @author zdd
 *
 */
@IocBean
public class PgtMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;

	@At("/ht/pgt")
    @Ok("jsp:jsp.ht.pgt")
    public void loadPgt(HttpServletRequest req){}
	@At("/ht/pgtlist")
    @Ok("jsp:jsp.ht.pgtlist")
    public void loadPgtlist(HttpServletRequest req){}
	
	/**
	 * 上传单个高频图文件 
	 */
	@POST
	@At("/ht/pgtuploads")
    @Ok("json")
	public JSONObject saveSingleton (@Param("..")IronoGram gram  ,HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		OmFileUploadServletUtil fusu = new OmFileUploadServletUtil();
		fusu.setServletContext(context) ;
		try {
			if("savedata".equals(gram.getAction())){//把临时目录中的对应的文件转存，并在数据库中保存一条记录
				if(StringUtil.checkNotNull(gram.getGramFileName())){
					if(fusu.cloneTmpFile2Other(gram.getGramFileName(), this.getSavePath(context) + fusu.UPLOAD_PIC_PATH) ){
						gram.setGramPath(fusu.UPLOAD_PIC_PATH + gram.getGramFileName()); 
						//去掉文件的扩展名，做数据库记录ID
						gram.setGramID(gram.getGramFileName().substring(0, gram.getGramFileName().lastIndexOf(".")))  ;
						
						if(baseService.dao.fetch(gram) == null){
							baseService.dao.insert(gram) ;
							json.put(Constant.SUCCESS, true) ;
						}else{
							json.put(Constant.INFO, "该频高图文件已经存在") ;
						}
						
						fusu.clearTmpDirectory() ; //清空临时目录
					}else{
						json.put(Constant.INFO, "频高图文件保存失败") ;
					}
				}else{
					json.put(Constant.INFO, "频高图文件名错误") ;
				}
				
			}else{ //仅仅把图片存储到临时目录
				String file = fusu.defaultProcessFileUpload(request , true) ;
				
				log.info(file) ;
				json.put("imgpath", file) ;
				json.put(Constant.SUCCESS, true) ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put(Constant.INFO, e.getMessage()) ;
		}finally{
			return json ;
		}
	}
	
	@POST
	@At("/ht/pgtlist")
    @Ok("json")
	public JSONObject list(@Param("..")Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;

		List<IronoGram>  list = baseService.dao.query(IronoGram.class, null, page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(IronoGram.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "station"}); 
		JSONArray array = new JSONArray();
		
		for(IronoGram g : list ){
			if(null != g.getStation()){
				System.out.println(g.getStation().getName());
			}else{
				log.info("station is null ") ;
			}
			JSONObject item = new JSONObject();
			item.put("gramID", g.getGramID()) ;
			item.put("gramTitle", g.getGramTitle()) ;
			item.put("stationID", g.getStationID()) ;
			item.put("type", g.getType()) ;
			item.put("createDate",  DateUtil.convertDateToString(g.getCreateDate())) ;
			item.put("gramFileName", g.getGramFileName()) ;
			
			item.put("gramPath", g.getGramPath()) ;
			
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
}
