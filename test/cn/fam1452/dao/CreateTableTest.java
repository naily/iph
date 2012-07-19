package cn.fam1452.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import cn.fam1452.dao.pojo.Log;
import cn.fam1452.dao.pojo.ProtectDate;

public class CreateTableTest {
	private Dao dao ;
	private void initDataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ds.setUrl("jdbc:sqlserver://localhost:1433;databaseName=DB_IONOSPHERE;integratedSecurity=true;user=sa;password=123456;");
		ds.setUsername("sa");
		ds.setPassword("123456");
		//ds.close();  // 关闭池内所有连接
		
		dao = new NutDao(ds);
		
		//return dao ;
	}
	
	@Test
	public void createTab(){
		this.initDataSource() ;
		
		this.dao.create(Log.class, true) ;
	}
}
