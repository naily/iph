package cn.fam1452.action.qt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.IronoGram;
import cn.fam1452.dao.pojo.NavDataYear;
import cn.fam1452.dao.pojo.News;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.FileDownload;

@IocBean
public class QTPGTMod extends BaseMod{
	@Inject("refer:baseService")
	private BaseService baseService ;
	@At("/qt/indexLeftPGT")
	@Ok("json")
	/**
	 * 左侧导航-频高图之观测站及年份显示
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
	public JSONObject loadLeftPgt(@Param("..")NavDataYear navData){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		List<Station> stationList = baseService.dao.query(Station.class, null);//查询观测站
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
			    if(i==0){
			    	mapAll.put("expanded", true);
			    }
				queryListStaYear =baseService.dao.query(NavDataYear.class, Cnd.where("stationId", "=",station.getId() ).and("dataTable", "=", navData.getDataTable()).asc("year"));			
				 yearList = new ArrayList<Map>();
				for(NavDataYear ndy:queryListStaYear){
					mapYear = new HashMap<String, Object>(); 			
					mapYear.put("text", ndy.getYear());
					yearList.add(mapYear);			
				}
				mapAll.put("children", yearList);
				jsonAllList.add(mapAll);
			}
			json.put("data", jsonAllList);
		}
//	    log.info(json.toString());
		return json;
		
		
	}
	@At("/qt/listPGT")
	@Ok("jsp:jsp.qt.pgtlist")
	public void pgtList(@Param("..")NavDataYear navData,HttpSession session ,HttpServletRequest req,@Param("..")Pager page){
		page.setPageSize(Constant.PAGE_SIZE);//默认分页记录数
		Pager pager = baseService.dao.createPager(page.getPageNumber(), page.getPageSize());    				
		//IronoGram is =baseService.dao.fetchLinks(baseService.dao.fetch(IronoGram.class), "station");		
		List<IronoGram> list =  baseService.dao.query(IronoGram.class, Cnd.orderBy().desc("createDate"), pager); 
		List showList = new ArrayList();
		String id=null;
		for(IronoGram iro:list){
			id=iro.getStationID();
			Station station =baseService.dao.fetch(Station.class,id );
			iro.setStation(station);			
			showList.add(iro);
		}
		pager.setRecordCount(baseService.dao.count(IronoGram.class)); 
		req.setAttribute("pgtlist", showList);
		req.setAttribute("page", pager);
		
	}
	
	@At("/qt/downloadPGT")
	@Ok("jsp:jsp.qt.pgtlist")
	public void downloadPGT(@Param("..")String ids,HttpSession session ,HttpServletRequest req,HttpServletResponse res){
		IronoGram idd = baseService.dao.fetch(IronoGram.class, ids);
		try {
			FileDownload.fileDownLoad(req,res,idd.getGramPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
