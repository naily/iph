package cn.fam1452.action.qt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.fam1452.Constant;
import cn.fam1452.dao.pojo.News;
import cn.fam1452.service.BaseService;
import cn.fam1452.utils.StringUtil;

@IocBean
public class QTNewsMod {
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/qt/indexPicNews")
    @Ok("json")
    public JSONObject loadPicNews(HttpServletRequest req){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		return json ;
	}
}
