package cn.fam1452.action.qt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import cn.fam1452.dao.pojo.Scanpic;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.dao.pojo.User;
import cn.fam1452.service.BaseService;
import cn.fam1452.service.DataVisitService;
import cn.fam1452.utils.FileDownload;
import cn.fam1452.utils.GetIP;
import cn.fam1452.utils.StringUtil;

@IocBean
public class QTScanPicMod extends BaseMod{
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@Inject("refer:dataVisitService")
	private DataVisitService dvs ;

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
	@At("/qt/downloadScanpic")
	@Ok("json")
	public void downloadPGT(HttpSession session ,HttpServletRequest req,HttpServletResponse res,@Param("..")Scanpic scp){
		//JSONObject json = new JSONObject();
		//json.put(Constant.SUCCESS, false);
		String scanPicID = scp.getScanPicID();
		Scanpic spic = baseService.dao.fetch(Scanpic.class, scanPicID);
		try {
			float fileSize =FileDownload.fileDownLoads(req,res,spic.getGramPath());//byte
			if(session.getAttribute(Constant.QT_USER_SESSION)!=null){
				User user = (User)session.getAttribute(Constant.QT_USER_SESSION);
				dvs.insert("T_SCANPIC", "03", 1, user.getLoginId(), GetIP.getIpAddr(req), fileSize);
			}
			//return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return json;
		//return null;
	}
}
