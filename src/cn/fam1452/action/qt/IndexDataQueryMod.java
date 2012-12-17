package cn.fam1452.action.qt;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.TotalNumBo;
import cn.fam1452.dao.pojo.DataService;
import cn.fam1452.dao.pojo.Log;
import cn.fam1452.dao.pojo.Visit;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.FileDownload;

/**
 * 首页最新数据更新查询
 * @author gls
 * @date 2012-08-24
 * */

@IocBean
public class IndexDataQueryMod extends BaseMod{
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	/**
	 * 最新数据更新
	 * @author gls
	 * @date 2012-08-27
	 * */
	@SuppressWarnings("unchecked")
	@At("/qt/latestDataUpdate")
	@Ok("json")
	public JSONObject latestData(HttpServletRequest req){
		JSONObject json= new JSONObject();
		json.put(Constant.SUCCESS, false);
		Sql sql =Sqls.create("SELECT TOP 5 * FROM T_Log where actionType<>'03' order by logDate desc ");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(baseService.dao.getEntity(Log.class));
		baseService.dao.execute(sql) ;		
		//getMsgByKey("");
		List<Log> list =sql.getList(Log.class);
		
		if(null!=list && list.size()>0){
			json.put(Constant.SUCCESS, true);
			String dataTable,actionType;//数据表名称，数据操作类型(插入，修改),前台显示标题
			Map mapTitle = null;
			List<Map> titleList = new ArrayList<Map>();
			String zh_en=this.getMsgByKey(req, "lang");
			for(Log logs:list){
				mapTitle= new HashMap();
				if("01".endsWith(logs.getActionType())){
					
					if("en".equals(zh_en)){
						actionType=" add ";
					}else{
						actionType="新增了";
					}
					
				}else{
					if("en".equals(zh_en)){
						actionType=" update ";
					}else{
						actionType="更新了";
					}
					
				}
				dataTable=getDataTableName(logs.getDataTable(),zh_en);
				mapTitle.put("title", DateUtil.convertDateToString(logs.getLogDate(), "yyyy-MM-dd HH:mm")+"&nbsp;"+actionType+dataTable);
				titleList.add(mapTitle);
			}
			  
			   json.put("titeList", titleList);
			   json.put("nums", titleList.size());
		}
		
		return json;
		
	}
	/**
	 * 获取数据表的中文名称
	 * */
	public String getDataTableName(String tname,String zh_en){
		String tbChineseName;
		if("T_METADATA".equals(tname)){
			if("en".equals(zh_en)){
				tbChineseName="Metadata";
			}else{
				tbChineseName="元数据";
			}
			
		}else if("T_SCANPIC".equals(tname)){
			if("en".equals(zh_en)){
				tbChineseName="Scanpic";
			}else{
				tbChineseName="报表扫描图";
			}
		}else if("T_PARAMETER".equals(tname)){
			if("en".equals(zh_en)){
				tbChineseName="Parameter";
			}else{
				tbChineseName="电离层参数";
			}
		}else if("T_IRONOGRAM".equals(tname)){
			if("en".equals(zh_en)){
				tbChineseName="Ironogram";
			}else{
				tbChineseName="电离层频高图";
			}
		}else{
			tbChineseName="";
		}
		return tbChineseName;
	}
	
	/**
	 * 数据访问统计
	 * 1、注册用户量
	 * 2、网站访问量
	 * 3、数据查询次数
	 * 4、数据下载次数
	 * 5、数据下载量
	 * @author gls
	 * @date 2012-08-27
	 * */
	@At("/qt/visitData")
	@Ok("json")
	public JSONObject tongjiQuery(){
		JSONObject json= new JSONObject();
		//json.put(Constant.SUCCESS, false);
		//1、注册用户数统计
		Sql sql =Sqls.create("SELECT count(*) as longNum FROM T_User ");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(baseService.dao.getEntity(TotalNumBo.class));
		baseService.dao.execute(sql) ;
		TotalNumBo tn = sql.getObject(TotalNumBo.class);
		if(null!=tn){
			json.put("regUserNum", tn.getLongNum());
		}else{
			json.put("regUserNum", 0);
		}
		//log.info("注册用户："+tn.getLongNum());
		
		//2、网站访问量统计
		Sql sql2 =Sqls.create("SELECT visitNum FROM T_visit ");
		sql2.setCallback(Sqls.callback.entities());
		sql2.setEntity(baseService.dao.getEntity(Visit.class));
		baseService.dao.execute(sql2) ;
		Visit vis = sql2.getObject(Visit.class);
		if(null!=vis){
			json.put("visitNum", vis.getVisitNum());
		}else{
			json.put("visitNum", 0);
		}
		//log.info("网站访问量："+vis.getVisitNum());
		
		
		DataService ds =null;
		//3、数据查询次数
		Sql sql3 =Sqls.create("SELECT count(resultNum1) as resultNum1 FROM T_DATASERVICE where actionType='01'");
		sql3.setCallback(Sqls.callback.entities());
		sql3.setEntity(baseService.dao.getEntity(DataService.class));
		baseService.dao.execute(sql3) ;
	    ds = sql3.getObject(DataService.class);
	    if(null!=ds){
	    	json.put("queryNum", ds.getResultNum1());
	    }else{
			json.put("queryNum", 0);
		}
	    //log.info("查询次数："+ds.getResultNum1());
		
		//4、数据下载次数
		Sql sql4 =Sqls.create("SELECT sum(resultNum3) as resultNum3,sum(resultAmount) as resultAmount FROM T_DATASERVICE where actionType='03'");
		sql4.setCallback(Sqls.callback.entities());
		sql4.setEntity(baseService.dao.getEntity(DataService.class));
		baseService.dao.execute(sql4) ;
		ds = sql4.getObject(DataService.class);
		if(null!=ds){
			float filesize =FileDownload.getFloatNum(ds.getResultAmount()/1024);
			//log.info("filesize="+filesize);
			//log.info("filesize.tostring="+String.valueOf(filesize));
			
	    	json.put("downloadNum", ds.getResultNum3());
	    	json.put("downloadAmount",filesize);//k->M
	    }else{
	    	json.put("downloadNum", 0);
	    	json.put("downloadAmount",0);
		}
		//log.info("下载次数："+ds.getResultNum3()+",下载量:"+FileDownload.getFloatNum(ds.getResultAmount()/1024));
		
		return json;
		
	}
	
	/**
	 * 更新访问量
	 * @author gls
	 * @date 2012-08-27
	 * */
	@At("/qt/updateVisitNum")
	@Ok("json")
	public JSONObject updateVisitNum(){
		JSONObject json= new JSONObject();
		json.put(Constant.SUCCESS, false);
		Sql sql =Sqls.create("update T_visit set visitNum=visitNum+1");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(baseService.dao.getEntity(Log.class));
		baseService.dao.execute(sql) ;				
		return json;
		
	}
}
