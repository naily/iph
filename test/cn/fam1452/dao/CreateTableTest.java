package cn.fam1452.dao;

import org.junit.Test;

import cn.fam1452.dao.pojo.*;

public class CreateTableTest extends DataSource{
	
	
	
	@Test
	public void createTab(){
		//this.initDataSource() ;
		
		//this.dao.create(NavDataYear.class, true) ;
		this.dao.create(News.class, true) ;
	}
}
