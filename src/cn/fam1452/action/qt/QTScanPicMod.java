package cn.fam1452.action.qt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
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
import cn.fam1452.dao.pojo.ProtectDate;
import cn.fam1452.dao.pojo.Scanpic;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataVisitService;
import cn.fam1452.service.ParameterService;
import cn.fam1452.service.ScanPicService;
import cn.fam1452.utils.DateJsonValueProcessor;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.FileDownload;
import cn.fam1452.utils.GetIP;
import cn.fam1452.utils.StringUtil;

/**
 * 报表扫描图操作：列表显示，扫描图浏览，扫描图下载
 * */

@IocBean
//@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
public class QTScanPicMod extends BaseMod{
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@Inject("refer:dataVisitService")
	private DataVisitService dvs ;

	@Inject("refer:parameterService")
	private ParameterService parameterService;
	
	@Inject("refer:scanPicService")
	private ScanPicService scanPicService;
	@At("/qt/listScanPic")
	@Ok("jsp:jsp.qt.scanPiclist")
	@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
	public void listScanPic(HttpSession session ,HttpServletRequest req,@Param("..")Pager page,@Param("..")Scanpic scp,@Param("..")ParameteDataBo paraQuery){
		page.setPageSize(Constant.PAGE_SIZE);//默认分页记录数
		Pager pager = baseService.dao.createPager(page.getPageNumber(), page.getPageSize());    				
		String queryYear ="";
		String tableName="T_SCANPIC";
		if(null!=scp && StringUtil.checkNotNull(scp.getQueryYear())){
			queryYear =scp.getQueryYear();
		}
		
		//List<Scanpic> list =  baseService.dao.query(Scanpic.class, Cnd.where("createDate","like","%"+queryYear+"%").desc("createDate"), pager); 
		//List<Scanpic> list =  baseService.dao.query(Scanpic.class,getQueryCnd(scp), pager); 
		
		List<Scanpic> list =  new ArrayList<Scanpic>();
		if(!parameterService.isProtectDateOpen(tableName,paraQuery.getStartDate(),paraQuery.getEndDate())){//判断频高图表是否设置了保护期
			//scp.setIds(scp.getStationID());
			list=scanPicService.top50ScanpicDataList(scp, tableName, paraQuery);
			pager.setRecordCount(list.size()); 
		}else{
			list =  baseService.dao.query(Scanpic.class,getQueryCnd(scp), pager); 
			pager.setRecordCount(baseService.dao.count(Scanpic.class, getQueryCnd(scp))); 
		}
		
		
		List<Scanpic> showList = new ArrayList<Scanpic>();//or("station.name","like","%"+queryKey+"%").
		String id=null;
		for(Scanpic scps:list){
			id=scps.getStationID();
			Station station =baseService.dao.fetch(Station.class,id );
			scps.setStation(station);			
			showList.add(scps);
		}
		//pager.setRecordCount(baseService.dao.count(Scanpic.class, Cnd.where("createDate","like","%"+queryYear+"%"))); 
		//pager.setRecordCount(baseService.dao.count(Scanpic.class, getQueryCnd(scp))); 
		/**
		 * 生成查询记录
		 * */
		if(!"".equals(getQTLoginUserID())){
			dvs.insert("T_SCANPIC", "01", showList.size(), getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
		}
		//pager.setRecordCount(showList.size());
		req.setAttribute("smtlist", showList);
		req.setAttribute("scp", scp);
		req.setAttribute("queryYear", queryYear);
		req.setAttribute("page", pager);
		
	}
	 public Condition getQueryCnd(Scanpic scp){
	    	Cnd cnd=null;
			if(null!=scp){
				if(StringUtil.checkNotNull(scp.getQueryYear())){
					cnd = Cnd.where("createDate","like","%"+scp.getQueryYear()+"%");
					
				}else{
					if(StringUtil.checkNotNull(scp.getIds())){
						cnd = Cnd.where("stationID", "=", scp.getIds());
					}
					if(StringUtil.checkNotNull(scp.getStartDate()) && StringUtil.checkNotNull(scp.getStartDate())){
						Date start = DateUtil.convertStringToSqlDate(scp.getStartDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
						Date end = DateUtil.convertStringToSqlDate(scp.getEndDate()+" 23:59:00","yyyy-MM-dd HH:mm:ss");
						cnd.and("createDate", ">=",start).and("createDate","<=",end);
					}
				}
				//log.info(cnd.toString());
			}
		    
	    	return cnd;
	    }
	@At("/qt/queryScanpic")
	@Ok("json")
	/**
	 * 找数据：报表扫描图查询，根据观测站及日期查询
	 * */
	public JSONObject queryScanpicList(HttpServletRequest req,@Param("..")Scanpic scp,@Param("..")Pages page,@Param("..")ParameteDataBo paraQuery){
		JSONObject json = new JSONObject();
		JsonConfig cfg = new JsonConfig();  				
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss")); 
		cfg.setExcludes(new String[] { "address" , "administrator","email","homepage","introduction","latitude","location","longitude","phone","picPath","timeZone","zipCode"}); 
		String protectArea=null;//保护期区间
		if (scp != null && StringUtil.checkNotNull(scp.getIds())) {	
			List<Scanpic> list =null;
			int total =0;
			//if(parameterService.isProtectDate("T_SCANPIC")){//判断频高图表是否设置了保护期
			String tableName ="T_SCANPIC";
			
			if(!parameterService.isProtectDateOpen(tableName,paraQuery.getStartDate(),paraQuery.getEndDate())){//判断频高图表是否设置了保护期
				list=scanPicService.top50ScanpicDataList(scp, tableName, paraQuery);
				if(null!=list && list.size()>0)total=list.size();
				ProtectDate proD =parameterService.getProtectDateByTableName(tableName);
				protectArea =DateUtil.convertDateToString(proD.getDataSDate())+","+DateUtil.convertDateToString(proD.getDataEDate());
			}else{
				if(null!=paraQuery && StringUtil.checkNotNull(paraQuery.getPageSize()))
					page.setLimit(Integer.parseInt(paraQuery.getPageSize()));
				 list = scanPicService.ScanpicDataList(scp,page,paraQuery);
				 total =this.baseService.dao.count(Scanpic.class,scanPicService.getScanpicQuery(scp, paraQuery));
			}	
			
		
				List<Scanpic> showList = new ArrayList<Scanpic>();
				/**
				 * 生成查询记录
				 * */
				if(!"".equals(getQTLoginUserID())){
					dvs.insert("T_SCANPIC", "01", showList.size(), getQTLoginUserID(), GetIP.getIpAddr(req), 0f);
				}
				String id=null;
				for(Scanpic iro:list){
					id=iro.getStationID();
					Station station =baseService.dao.fetch(Station.class,id );
					iro.setStation(station);			
					showList.add(iro);
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
		json.put(Constant.PROTECTDATA_AREA, protectArea);
		//log.info(json.toString());
		return json;
		
	}
	
	/**
	 * 下载报表扫描图
	 * */
	@At("/qt/downloadScanpic")
	@Ok("json")
	public void downloadPGT(HttpSession session ,HttpServletRequest req,HttpServletResponse res,@Param("..")Scanpic scp){
		//JSONObject json = new JSONObject();
		//json.put(Constant.SUCCESS, false);
		String scanPicID = scp.getScanPicID();
		Scanpic spic = baseService.dao.fetch(Scanpic.class, scanPicID);
		try {
			float fileSize =FileDownload.fileDownLoads(req,res,spic.getGramPath());//byte
			if(!"".equals(getQTLoginUserID())){
				dvs.insert("T_SCANPIC", "03", 1, getQTLoginUserID(), GetIP.getIpAddr(req), fileSize);
			}
			//return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return json;
		//return null;
	}
	/**
	 * 扫描图查看
	 *  根据观测站及日期查询报表扫描图
	 * **/
	@At("/qt/showScanpic")
	@Ok("json")
	public JSONObject showPGT(HttpSession session ,HttpServletRequest req,HttpServletResponse res,@Param("..")Scanpic scp){
		JSONObject json = new JSONObject();
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); 
		cfg.setExcludes(new String[] {"station"}); 
		json.put(Constant.SUCCESS, false);
		Date createDate =null;	
		String inputDate=null;
		if(null!=scp && StringUtil.checkNotNull(scp.getScanPicTitle())){
			//inputDate = scp.getScanPicTitle().substring(0,8);
			inputDate = scp.getScanPicTitle();
			createDate = DateUtil.convertStringToDate(inputDate, "yyyy-MM-dd");	
		}else{
			createDate= DateUtil.getCurrentDate();
		}	
		//log.info(createDate);
		Scanpic idd = baseService.dao.fetch(Scanpic.class, Cnd.where("stationID","=",scp.getStationID()).and("createDate","=",createDate));		
		if(null!=idd){
			json.put(Constant.SUCCESS, true);
			json.put("data", JSONObject.fromObject(idd,cfg)) ;
		}
		//log.info(json.toString());
		return json;
	}
}
