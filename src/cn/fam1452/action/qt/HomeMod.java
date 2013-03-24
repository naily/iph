package cn.fam1452.action.qt;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.fam1452.action.BaseMod;
import cn.fam1452.dao.pojo.Station;
import cn.fam1452.service.BaseService;

@IocBean
public class HomeMod extends BaseMod{

	
	@At("/qt/lang/?")
	@Ok("redirect:/") 
    public void lang(String lang , HttpSession session ){
		Mvcs.setLocalizationKey(lang) ;
		return  ;
	}
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/qt/about")
	@Ok("jsp:jsp.qt.about") 
    public void about(){}
	
	@At("/qt/smap")
	@Ok("jsp:jsp.qt.stationmap") 
	public List stationMap(){
		List<Station>  list = baseService.dao.query(Station.class, Cnd.where("status", "=", 1).desc("id") ) ;
		return list;
	}
}
