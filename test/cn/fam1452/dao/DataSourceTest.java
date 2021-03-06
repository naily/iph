package cn.fam1452.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import cn.fam1452.dao.pojo.FeedBack;
import cn.fam1452.dao.pojo.IronoGram;
import cn.fam1452.dao.pojo.MetaData;
import cn.fam1452.dao.pojo.News;
import cn.fam1452.dao.pojo.UserComment;

public class DataSourceTest {
	
	public static void main(String[] a){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ds.setUrl("jdbc:sqlserver://localhost:1433;databaseName=DB_IONOSPHERE;integratedSecurity=true;user=sa;password=123456;");
		ds.setUsername("sa");
		ds.setPassword("123456");
		//ds.close();  // 关闭池内所有连接
		
		Dao dao = new NutDao(ds);
		/*if(!dao.exists("T_IRONOGRAM")){
    		dao.create(IronoGram.class, false) ;
    	}*/
		
		//dao.create(MetaData.class, true) ;
		dao.create(UserComment.class, true) ;
		//dao.create(FeedBack.class, true) ;
		/*News n = dao.fetch(News.class, Cnd.where("isPicNews", "=", true).desc("newsId")) ;
		System.out.println(n.getTitle());*/
	}
	
	
}
