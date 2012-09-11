package cn.fam1452.action.qt;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.fam1452.action.BaseMod;

@IocBean
public class HomeMod extends BaseMod{

	
	@At("/qt/lang/?")
	@Ok("redirect:/") 
    public void lang(String lang , HttpSession session ){
		Mvcs.setLocalizationKey(lang) ;
		return  ;
	}
	
	
	@At("/qt/about")
	@Ok("jsp:jsp.qt.about") 
    public void about(){
	}
}
