package cn.fam1452.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import cn.fam1452.dao.pojo.Log;
import cn.fam1452.dao.pojo.ProtectDate;

public class CreateTableTest extends DataSource{
	
	
	
	@Test
	public void createTab(){
		//this.initDataSource() ;
		
		this.dao.create(Log.class, true) ;
	}
}
