package cn.fam1452.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

public class DataSource {
	
	public DataSource(){
		initDataSource() ;
	}

	protected Dao dao ;
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
	
	protected Dao getDao () {
		return dao ;
	}
}
