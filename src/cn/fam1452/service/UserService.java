package cn.fam1452.service;

import java.util.List;

import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.dao.pojo.User;

@IocBean(name = "userService")
public class UserService extends Base{
	
	public Administrator queryAdmin(String name){
		return dao.fetch(Administrator.class, name) ;
	}
	
	/**
	 * 查询一个USER
	 * @param loginId
	 * @return
	 */
	public User queryUser(String loginId){
		return dao.fetch(User.class, loginId) ;
	}
	/**
	 * 所有用户
	 * @return
	 */
	public List<User> queryAllUser(){
		return dao.query(User.class, null) ;
	}
}
