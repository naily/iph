package cn.fam1452.action.qt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.bo.ParameteDataBo;
import cn.fam1452.action.filter.UserFilter;
import cn.fam1452.dao.pojo.IronoGram;
import cn.fam1452.dao.pojo.NavDataYear;
import cn.fam1452.dao.pojo.Parameter;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataVisitService;
import cn.fam1452.service.PGTService;
import cn.fam1452.service.ParameterService;
import cn.fam1452.utils.DateJsonValueProcessor;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.FileDownload;
import cn.fam1452.utils.GetIP;
import cn.fam1452.utils.StringUtil;

@IocBean
//@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
public class QTPGTMod extends BaseMod{
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@Inject("refer:dataVisitService")
	private DataVisitService dvs ;
	
	@Inject("refer:parameterService")
	private ParameterService parameterService;
	
	@Inject("refer:pgtService")
	private PGTService pgtService ;
	
	@At("/qt/indexLeftTree")
	@Ok("json")
	/**
	 * 左侧导航-(频高图、电离参数、扫描图)之观测站及年份显示
	 * @author gls
	 * @date 2012-08-22 
	 * 前台所需数据格式
	 * [{
						"text" : "武汉观测站",
						"expanded" : true,
						"children" : [{
									"text" : "1946年"
								}, {
									"text" : "1947年"
								}]
					}];
	 * */
	public JSONObject loadLeftTree(@Param("..")NavDataYear navData){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		List<Station> stationList = baseService.dao.query(Station.class, Cnd.where("status", "=", "1"));//查询观测站
		int listSize=stationList.size();
		if(listSize>0){
			json.put(Constant.SUCCESS, true);
			List<NavDataYear> queryListStaYear;//根据观测站查询年份
			List<Map<String, Object>> jsonAllList = new ArrayList<Map<String, Object>>();//
			List<Map> yearList =null;
			Map<String, Object> mapAll,mapYear;
			//for(Station station:stationList){		
			for(int i=0;i<listSize;i++){
				Station station = (Station)stationList.get(i);
				mapAll = new HashMap<String, Object>(); 
				mapAll.put("text", station.getName());
				mapAll.put("pid", station.getId());
			    if(i==0){
			    	mapAll.put("expanded", true);
			    }
				queryListStaYear =baseService.dao.query(NavDataYear.class, Cnd.where("stationId", "=",station.getId() ).and("dataTable", "=", navData.getDataTable()).asc("year"));			
				 yearList = new ArrayList<Map>();
				for(NavDataYear ndy:queryListStaYear){
					mapYear = new HashMap<String, Object>(); 			
					mapYear.put("text", ndy.getYear());
					mapYear.put("id", station.getId());
					yearList.add(mapYear);			
				}
				mapAll.put("children", yearList);
				jsonAllList.add(mapAll);
			}
			json.put("data", jsonAllList);
		}
	    //log.info(json.toString());
		return json;
		
		
	}
	@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
	@At("/qt/listPGT")
	@Ok("jsp:jsp.qt.pgtlist")
	/**
	 * 导航频高图查询
	 * */
	public void pgtList(HttpSession session ,HttpServletRequest req, @Param("..")Pager page,@Param("..")IronoGram irg){
		page.setPageSize(Constant.PAGE_SIZE);//默认分页记录数
		Pager pager = baseService.dao.createPager(page.getPageNumber(), page.getPageSize());    				
		//IronoGram is =baseService.dao.fetchLinks(baseService.dao.fetch(IronoGram.class), "station");	
		String queryYear ="";
		if(null!=irg && StringUtil.checkNotNull(irg.getQueryYear())){
			queryYear =irg.getQueryYear();
		}
		//List<IronoGram> list =  baseService.dao.query(IronoGram.class, Cnd.where("createDate","like","%"+queryYear+"%").desc("createDate"), pager); 
		List<IronoGram> list =  baseService.dao.query(IronoGram.class, getQueryCnd(irg), pager); 
		
		List<IronoGram> showList = new ArrayList<IronoGram>();//or("station.name","like","%"+queryKey+"%").
		String id=null;
		for(IronoGram iro:list){
			id=iro.getStationID();
			Station station =baseService.dao.fetch(Station.class,id );
			iro.setStation(station);			
			showList.add(iro);
		}
		pager.setRecordCount(baseService.dao.count(IronoGram.class, getQueryCnd(irg))); 
		/**
		 * 生成查询记录
		 * */
		if(!"".equals(getQTLoginUserID())){
			dvs.insert("T_IRONOGRAM", "01", showList.size(), getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
		}
		//pager.setRecordCount(showList.size()); 
		req.setAttribute("pgtlist", showList);
		req.setAttribute("irg", irg);
		req.setAttribute("queryYear", queryYear);
		req.setAttribute("page", pager);
		
	}
    public Condition getQueryCnd(IronoGram irg){
    	Cnd cnd=null;
		if(null!=irg){
			if(StringUtil.checkNotNull(irg.getQueryYear())){
				cnd = Cnd.where("createDate","like","%"+irg.getQueryYear()+"%");
				
			}else{
				if(StringUtil.checkNotNull(irg.getStationID())){
					cnd = Cnd.where("stationID", "=", irg.getStationID());
				}
				if(StringUtil.checkNotNull(irg.getStartDate()) && StringUtil.checkNotNull(irg.getStartDate())){
					Date start = DateUtil.convertStringToSqlDate(irg.getStartDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
					Date end = DateUtil.convertStringToSqlDate(irg.getEndDate()+" 23:59:00","yyyy-MM-dd HH:mm:ss");
					cnd.and("createDate", ">=",start).and("createDate","<=",end);
				}
			}
			//log.info(cnd.toString());
		}
	    
    	return cnd;
    }
	@At("/qt/queryPGT")
	@Ok("json")
	/**
	 * 找数据：频高图查询，根据观测站及日期查询
	 * */
	public JSONObject querytList(HttpServletRequest req,@Param("..")IronoGram irg,@Param("..")Pages page,@Param("..")ParameteDataBo paraQuery){
		JSONObject json = new JSONObject();
		JsonConfig cfg = new JsonConfig();  				
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss")); 
		cfg.setExcludes(new String[] { "address" , "administrator","email","homepage","introduction","latitude","location","longitude","phone","picPath","timeZone","zipCode"}); 
		if (irg != null && StringUtil.checkNotNull(irg.getIds())) {	
			List<IronoGram> list =null;
			int total =0;
			//if(parameterService.isProtectDate("T_IRONOGRAM")){//判断频高图表是否设置了保护期
			String tableName ="T_IRONOGRAM";
			if(!parameterService.isProtectDateOpen(tableName,paraQuery.getStartDate(),paraQuery.getEndDate())){//判断频高图表是否设置了保护期
				list=pgtService.top50PGTDataList(irg, tableName, paraQuery);
				if(null!=list && list.size()>0)total=list.size();
			}else{
				if(null!=paraQuery && StringUtil.checkNotNull(paraQuery.getPageSize()))
					page.setLimit(Integer.parseInt(paraQuery.getPageSize()));
				 list = pgtService.pgtDataList(irg,page,paraQuery);
				 total =this.baseService.dao.count(IronoGram.class,pgtService.getPGTQuery(irg, paraQuery));
			}	
			
		
				List<IronoGram> showList = new ArrayList<IronoGram>();
				String id=null;
				for(IronoGram iro:list){
					id=iro.getStationID();
					Station station =baseService.dao.fetch(Station.class,id );
					iro.setStation(station);			
					showList.add(iro);
				}
				/**
				 * 生成查询记录
				 * */
				if(!"".equals(getQTLoginUserID())){
					dvs.insert("T_IRONOGRAM", "01", showList.size(), getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
				}
				if(null!=showList && showList.size()>0){
					json.put(Constant.SUCCESS, true);
					json.put(Constant.ROWS, JSONArray.fromObject(showList, cfg));
					json.put(Constant.TOTAL, total);//list.size()
					
				}else{
					json.put(Constant.ROWS, "[]");
					json.put(Constant.TOTAL, 0);
				}						
		}else{
			json.put(Constant.ROWS, "[]");
			json.put(Constant.TOTAL, 0);
		}
		//log.info(json.toString());
		return json;
		
	}
	@At("/qt/downloadPGT")
	@Ok("json")
	public JSONObject downloadPGT(HttpSession session ,HttpServletRequest req,HttpServletResponse res,@Param("..")IronoGram irg){
		//JSONObject json = new JSONObject();
		//json.put(Constant.SUCCESS, false);
		String gramID = irg.getGramID();
		IronoGram idd = baseService.dao.fetch(IronoGram.class, gramID);
		try {
			float fileSize =	FileDownload.fileDownLoads(req,res,idd.getGramPath());	
			if(!"".equals(getQTLoginUserID())){
				dvs.insert("T_IRONOGRAM", "03", 1, getQTLoginUserID(), GetIP.getIpAddr(req), fileSize);
			}
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return json;
		return null;
	}
	/**
	 * 频高图查看
	 *  根据观测站及日期查询电离层频高图
	 * **/
	@At("/qt/queryPGTByDate")
	@Ok("json")
	public JSONObject queryPGTBYDate(@Param("..")IronoGram irg){
		JSONObject json = new JSONObject();
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss")); 
		cfg.setExcludes(new String[] {"address" , "administrator","email","homepage","introduction","latitude","location","longitude","phone","picPath","timeZone","zipCode"}); 
	   //String createDate= DateUtil.convertDateToString(irg.getCreateDate());
		String createDate= DateUtil.convertDateToString(irg.getCreateDate(),"yyyy-MM-dd HH");
	   // createDate = DateUtil.convertStringToDate(inputDate, "yyyy-MM-dd HH:MM:SS");	
	    String startTime =createDate+":00:00";
	    String endTime =createDate+":59:59";
		Sql sql =Sqls.create("select * from T_IRONOGRAM where stationID='"+irg.getStationID()+"' and createDate between '"+startTime+"' and '"+endTime+"'");
		//log.info(sql.toString());
		
		sql.setCallback(Sqls.callback.entities());
		//sql.setEntity(dao.getEntity(ParameteDataBo.class));
		sql.setEntity(baseService.dao.getEntity(IronoGram.class));
		baseService.dao.execute(sql) ;		
		List<IronoGram> list = sql.getList(IronoGram.class) ;
		List<IronoGram> listV = new ArrayList<IronoGram>();
		for(IronoGram para:list){
			Station station = this.baseService.dao.fetch(Station.class, para.getStationID());
			para.setStation(station);
			listV.add(para);
		}
		if(null!=listV && listV.size()>0){
			json.put(Constant.SUCCESS, true);
			json.put(Constant.ROWS, JSONArray.fromObject(listV, cfg));
			json.put(Constant.TOTAL, 0);
		}else{
			json.put(Constant.SUCCESS, false);
		}
		
		log.info(json.toString());
		return json;
	}
		
	/**
	 * 频高图查看
	 *  根据观测站及日期查询电离层频高图
	 * **/
	@At("/qt/showPGT")
	@Ok("json")
	public JSONObject showPGT(HttpSession session ,HttpServletRequest req,HttpServletResponse res,@Param("..")IronoGram irg){
		JSONObject json = new JSONObject();
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:MM:SS")); 
		cfg.setExcludes(new String[] {"station"}); 
		json.put(Constant.SUCCESS, false);
		Date createDate =null;	
		String inputDate=null;
		if(null!=irg && StringUtil.checkNotNull(irg.getGramTitle())){
			//inputDate = irg.getGramTitle().substring(0,8);
			createDate = DateUtil.convertStringToDate(inputDate, "yyyyMMdd");	
			inputDate = irg.getGramTitle();
			createDate = DateUtil.convertStringToDate(inputDate, "yyyy-MM-dd HH:MM:SS");	
		}else{
			createDate= DateUtil.getCurrentDate();
		}
			
		IronoGram idd = baseService.dao.fetch(IronoGram.class, Cnd.where("stationID","=",irg.getStationID()).and("createDate","=",createDate));
		
		if(null!=idd){
			json.put(Constant.SUCCESS, true);
			json.put("data", JSONObject.fromObject(idd,cfg)) ;
		}
		//log.info(json.toString());
		return json;
	}
	@At("/qt/insertBrowser")
	@Ok("json")
	public JSONObject insertBrowser(@Param("..")String tableNames,HttpSession session ,HttpServletRequest req){
		//JSONObject json = new JSONObject();
		//json.put(Constant.SUCCESS, false);
		 tableNames = req.getParameter("tableNames");
			if(!"".equals(getQTLoginUserID())){
				dvs.insert(tableNames, "02", 1, getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
			}					
		//return json;
		return null;
	}
}
