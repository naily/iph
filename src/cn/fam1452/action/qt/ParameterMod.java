/**
 * 描述：电离层参数报表
 */
package cn.fam1452.action.qt;


import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import cn.fam1452.action.BaseMod;
import cn.fam1452.service.BaseService;



@IocBean
public class ParameterMod extends BaseMod{
	
	@Inject("refer:baseService")
	private BaseService baseService ;
	
	@At("/qt/report")
    @Ok("jsp:jsp.qt.parameter")
	public void loaddefault(){
		baseService.dao.run(null);
	}
	
	
}
