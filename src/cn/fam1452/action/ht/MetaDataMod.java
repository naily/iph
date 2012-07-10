/**
 * 
 */
package cn.fam1452.action.ht;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Blob;
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
import org.nutz.dao.impl.jdbc.BlobValueAdaptor;
import org.nutz.dao.util.blob.SimpleBlob;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.MetaDataBo;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.dao.pojo.MetaData;
import cn.fam1452.dao.pojo.Parameter;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.OmFileUploadServletUtil;
import cn.fam1452.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @author zdd
 *
 */
@IocBean
public class MetaDataMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/ht/med")
    @Ok("jsp:jsp.ht.med")
    public void loadmed(HttpServletRequest req){}
	@At("/ht/medlist")
    @Ok("jsp:jsp.ht.medlist")
    public void loadmedlist(HttpServletRequest req){}
	
	

	/**
	 * 保存元数据
	 * 1,生成元数据xml
	 * 2,保存blob至数据库记录
	 * @Author Derek
	 * @Date Jul 8, 2012
	 * @param mdb
	 * @param request
	 * @param response
	 * @param context
	 * @return
	 */
	@POST
	@At("/ht/medSave")
    @Ok("json")
	public JSONObject save(@Param("..")MetaDataBo mdb  ,HttpServletRequest request, HttpServletResponse response , ServletContext context){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		try {
			//姑且只有js验证
			if(null != mdb){
				String xmldir = "data/metadata/xml/" ;
				
				//freemarker 准备
				Configuration cfg  = this.initFreeMarker( context) ;
				Template t = cfg.getTemplate("MetaDataXMLTemplates.ftl") ;
				t.setEncoding(StringUtil.UTF_8) ;
				
				MetaData med = new MetaData() ;
				
				//创建一个空的xml文件
				String fileName = System.currentTimeMillis() + ".xml" ;
				File medxml = new File(this.getAppRealPath(context) + xmldir + fileName) ;
				OutputStream outFile = new FileOutputStream(medxml ) ;
				
				//通过ftl模板输出xml文件
				Writer out = new OutputStreamWriter(outFile , StringUtil.UTF_8) ;
				t.process(mdb, out) ;
				out.flush() ;

				//存储
				SimpleBlob sb = new SimpleBlob(medxml);
				med.setFullContent(sb) ; 
				med.setFullContentFilePath(xmldir + fileName) ;
				med.setMdDate(DateUtil.getCurrentDate()) ;
				String dateStr = DateUtil.convertDateToString(med.getMdDate(), DateUtil.pattern3) ;
				
				//构造ID
				int ct = baseService.dao.count(MetaData.class, Cnd.where("mdId", "like", dateStr+"%" ) ) ;
				StringBuilder id = new StringBuilder();
				id.append(dateStr );
				
				String patt = "00" ;
				DecimalFormat nf  =  new DecimalFormat(patt);
				id.append(nf.format(ct)) ;
				
				//存值
				med.setMdId(id.toString()) ;
				med.setTitle(mdb.getName()) ;
				med.setKeyword(mdb.getKeys()) ;
				med.setSummary(mdb.getZhaiyao()) ;
				
				if(baseService.dao.fetch(med) == null){
					baseService.dao.insert(med) ;
					json.put(Constant.SUCCESS, true) ;
				}else{
					json.put(Constant.INFO, "该记录已存在") ;
				}
			}else{
				json.put(Constant.INFO, "参数为空") ;
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
	@At("/ht/medlist")
    @Ok("json")
	public JSONObject list(@Param("..")Pages page){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, true) ;

		List<MetaData>  list = baseService.dao.query(MetaData.class, null, page.getNutzPager()) ;
		
		json.put(Constant.TOTAL, baseService.dao.count(MetaData.class)) ;
		
		JsonConfig cfg = new JsonConfig(); 
		cfg.setExcludes(new String[] { "station"}); 
		JSONArray array = new JSONArray();
		
		for(MetaData g : list ){
			JSONObject item = new JSONObject();
			
			Station sa = new Station();
			sa.setId(g.getMdId()) ;
			sa = baseService.dao.fetch(sa) ;
			if(null != sa){
				item.put("stationName", sa.getName()) ;
			}else{
				item.put("stationName", "无") ;
			}
			
			item.put("gramID", g.getMdId()) ;
			
			
			array.add(item) ;
		}
		json.put(Constant.ROWS, array) ;
		return json ;
	}
	
	@POST
	@At("/ht/meddel")
    @Ok("json")
	public JSONObject deletemed(String ids){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(ids) ){
			String[] idss = ids.split(";") ;
			
			List<MetaData> igs = new ArrayList<MetaData>() ;
			for (String id : idss) {
				MetaData ig = new MetaData();
				ig.setMdId (id) ;
				
				ig = baseService.dao.fetch(ig) ;
				
				if(null != ig){
					igs.add(ig) ;
				}
			}
			
			if(null != igs && igs.size() > 0 ){
				if( baseService.dao.delete(igs) == igs.size() ){
					json.put(Constant.SUCCESS, true) ;
				}else{
					json.put(Constant.INFO, error1) ;
				}
			}else{
				json.put(Constant.INFO, error2) ;
			}
			
		}else{
			json.put(Constant.INFO, error3) ;
		}
		
		return json ;
	}
	
	@POST
	@At("/ht/medupdate")
    @Ok("json")
	public JSONObject updatemed(@Param("..")MetaData params){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		
		if(StringUtil.checkNotNull(params.getMdId()) && null != baseService.dao.fetch(params)){
			int  i = baseService.dao.update(params) ;
			json.put(Constant.SUCCESS, true ) ;
		}else{
			json.put(Constant.INFO, error2) ;
		}
		return json ;
	}
	
	
	
	@POST
	@At("/ht/medget")
    @Ok("json")
    public MetaData get(String id){
		MetaData ig = null ;
		if(StringUtil.checkNotNull(id)){
			ig = baseService.dao.fetch(MetaData.class, id) ;
		}
		
		return ig ;
	}
	
	
	
}
