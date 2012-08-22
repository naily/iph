package cn.fam1452.action.qt;

import net.sf.json.JSONObject;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.fam1452.Constant;
import cn.fam1452.action.BaseMod;
import cn.fam1452.service.BaseService;

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
	 * */
	public JSONObject loadLeftPgt(){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false);
		return json;
		
	}
}
