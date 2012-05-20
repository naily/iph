package cn.fam1452.action;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.resource.Scans;

import cn.fam1452.dao.pojo.Administrator;
import cn.fam1452.dao.pojo.IronoGram;

public class MvcSetup implements Setup{
	private Logger log = Logger.getLogger(this.getClass()) ;
	/**
     * 当服务启动的时候，自动检查数据库，如果必要的数据表不存在，创建它们 并创建一个默认的 master 记录
     */
    public void init(NutConfig config) {
    	ServletContext web = config.getServletContext() ;
    	Ioc ioc = config.getIoc();
    	
    	PropertiesProxy prop = ioc.get(PropertiesProxy.class, "config") ; 
    	//System.out.println(prop.get("db-url"));
    	
    	NutDao dao = ioc.get(NutDao.class, "dao");
    	
    	//initAppTables(web , dao) ;
    	/*if(!dao.exists("T_IRONOGRAM")){
    		dao.create(IronoGram.class, false) ;
    	}*/
    	
    	//Mvcs.getLocaleName(HttpSession) ;
        /*
        if (!dao.exists("t_pet")) {
                // Create tables
                Tables.define(dao, Tables.loadFrom("tables.dod"));

                // Create master account
                Master m = new Master();
                m.setName("peter");
                m.setPassword("123456");
                m.setBirthday(new Timestamp(System.currentTimeMillis()));
                dao.insert(m);
        }*/
    	
    	if(prop.getInt("isinit")  == 1){
    		Administrator admin = new Administrator();
    		admin.setLoginId("admin") ;
    		admin.setPassword("123456") ;
    		admin.setName("superadmin") ;
    		admin.setSuper(true) ;
    		dao.insert(admin) ;
    	}
    	
    }

    public void destroy(NutConfig config) {}
    
    /**
     * 检查并自动建表
     * @Author Derek
     * @Date May 13, 2012
     */
    private void initAppTables(ServletContext context , NutDao dao){
    	Scans scans = Scans.me();
    	scans.init(context) ;
    	
		List<Class<?>> list = scans.scanPackage("cn.fam1452.dao.pojo") ;
		log.info("scanPackage size: "+list.size() ) ;
  		Class [] remove = null;// {MenuInfo.class,Area.class , Chanpin.class};  //排除不创建的实体
		if(null != dao && list.size() > 0){
			for (Class cla : list) {
				if(this.isRemove(remove, cla.getSimpleName())){
					Annotation [] a = cla.getAnnotations() ;
					if(a.length > 0 && "Table".equals(a[0].annotationType().getSimpleName())){
						dao.create(cla, false) ;  //删除旧表创建;
						
						//log.info("no create: "+cla.getSimpleName()) ;
					}
				}else{
					log.info("not create: "+cla.getSimpleName()) ;
					
				}
			}
		}
    }
    
    private boolean isRemove(Class [] remove , String name){
		if(null == remove){
			return true ;
		}
		for (Class c1 : remove) {
			if(c1.getSimpleName().equals(name))
				return false ;
		}
		return true ;
	}
}
