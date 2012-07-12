package cn.fam1452.service;

import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.dao.pojo.User;

@IocBean(name = "userService")
public class UserService extends Base{
	
	public Administrator queryAdmin(String name){
		return dao.fetch(Administrator.class, name) ;
	}
	public User queryUser(String loginId){
		return dao.fetch(User.class, loginId) ;
	}
}
