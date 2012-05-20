package cn.fam1452.service;

import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.dao.pojo.Administrator;

@IocBean(name = "userService")
public class UserService extends Base{
	
	public Administrator queryAdmin(String name){
		return dao.fetch(Administrator.class, name) ;
	}
}
