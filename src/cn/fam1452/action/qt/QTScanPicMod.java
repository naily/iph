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
	@Filters(@By(type=UserFilter.class , args={ "/index.do" }))
	@At("/qt/listScanPic")
	@Ok("jsp:jsp.qt.scanPiclist")
	public void listScanPic(HttpSession session ,HttpServletRequest req,@Param("..")Pager page,@Param("..")Scanpic scp){
		page.setPageSize(Constant.PAGE_SIZE);//默认分页记录数
		Pager pager = baseService.dao.createPager(page.getPageNumber(), page.getPageSize());    				
		String queryKey ="";
		if(null!=scp && StringUtil.checkNotNull(scp.getScanPicTitle())){
			queryKey =scp.getScanPicTitle();
		}
		
		List<Scanpic> list =  baseService.dao.query(Scanpic.class, Cnd.where("scanPicTitle","like","%"+queryKey+"%").or("createDate","like","%"+queryKey+"%").desc("createDate"), pager); 
		List<Scanpic> showList = new ArrayList<Scanpic>();//or("station.name","like","%"+queryKey+"%").
		String id=null;
		for(Scanpic scps:list){
			id=scps.getStationID();
			Station station =baseService.dao.fetch(Station.class,id );
			scps.setStation(station);			
			showList.add(scps);
		}
		pager.setRecordCount(baseService.dao.count(Scanpic.class)); 
		req.setAttribute("smtlist", showList);
		req.setAttribute("page", pager);
		
	}
	
	@At("/qt/queryScanpic")
	@Ok("json")
	/**
	 * 找数据：报表扫描图查询，根据观测站及日期查询
	 * */
	public JSONObject queryScanpicList(@Param("..")Scanpic irg,@Param("..")Pages page,@Param("..")ParameteDataBo paraQuery){
		JSONObject json = new JSONObject();
		JsonConfig cfg = new JsonConfig();  				
		cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); 
		cfg.setExcludes(new String[] { "address" , "administrator","email","homepage","introduction","latitude","location","longitude","phone","picPath","timeZone","zipCode"}); 
		if (irg != null && StringUtil.checkNotNull(irg.getIds())) {	
			List<Scanpic> list =null;
			int total =0;
			//if(parameterService.isProtectDate("T_SCANPIC")){//判断频高图表是否设置了保护期
			if(!parameterService.isProtectDateOpen("T_SCANPIC",paraQuery.getStartDate(),paraQuery.getEndDate())){//判断频高图表是否设置了保护期
				list=scanPicService.top50ScanpicDataList(irg, page, paraQuery);
				if(null!=list && list.size()>0)total=list.size();
			}else{
				 list = scanPicService.ScanpicDataList(irg,page,paraQuery);
				 total =this.baseService.dao.count(Scanpic.class,scanPicService.getScanpicQuery(irg, paraQuery));
			}	
			
		
				List<Scanpic> showList = new ArrayList<Scanpic>();
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
		log.info(json.toString());
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
			inputDate = scp.getScanPicTitle().substring(0,8);
			createDate = DateUtil.convertStringToDate(inputDate, "yyyyMMdd");	
		}else{
			createDate= DateUtil.getCurrentDate();
		}			
		Scanpic idd = baseService.dao.fetch(Scanpic.class, Cnd.where("stationID","=",scp.getStationID()).and("createDate","=",createDate));		
		if(null!=idd){
			json.put(Constant.SUCCESS, true);
			json.put("data", JSONObject.fromObject(idd,cfg)) ;
		}
		//log.info(json.toString());
		return json;
	}
}
