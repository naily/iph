package cn.fam1452.action.ht;

import java.util.HashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.service.AdministratorService;
import cn.fam1452.service.UserService;
import cn.fam1452.utils.StringUtil;

/**
 * 管理员管理
 * @author zdd
 *
 */
@IocBean
public class AdministratorMod {
	
	@Inject("refer:adminService")
	private AdministratorService adminService ;
	public void list(){
		
	}
	
	public void add(Administrator admin){
		Map map = new HashMap<String, String>() ;
		
		if(StringUtil.checkNotNull(admin.getLoginId()) && StringUtil.checkNotNull(admin.getPassword())){
			adminService.save(admin) ;
		}else{
			
		}
	}
	
	public void modifPassword(){
		
	}
	
	public void delete(){
		
	}
}
