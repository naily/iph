package cn.fam1452.service;

import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.dao.pojo.Log;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

/**
 * 对几个关键数据表增、删、改操作日志进行记录
 * 
 * Class DataLogService
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jul 18, 2012 9:01:39 PM $
 */
@IocBean(name = "dataLogService")
public class DataLogService extends Base{
	
	
	public void insert(Log log){
		if(null != log && StringUtil.checkNotNull(log.getId())){
			this.dao.insert(log) ;
		}
		
	}
	
	/**
	 * 
	 * @Author Derek
	 * @Date Jul 18, 2012
	 * @param actionType 01表示数据录入，02表示数据编辑，03表示数据删除
	 * @param dataTable 操作的数据表：T_IRONOGRAM T_PARAMETER T_ REGIONMAP
	 * @param adminId 管理员ID，外键关联T_ADMINISTRATOR.loginID
	 * @return Log object
	 */
	public Log insert(String actionType , String dataTable , String adminId){
		Log log = new Log() ;
		if(StringUtil.checkNotNull(actionType) && 
				StringUtil.checkNotNull(dataTable) && 
				StringUtil.checkNotNull(adminId)){
			
			log.setId(String.valueOf(System.currentTimeMillis())) ;
			log.setLogDate(DateUtil.getCurrentDate()) ;
			log.setAdminId(adminId) ;
			log.setActionType(actionType) ;
			log.setDataTable(dataTable) ;
			
			this.insert(log) ;
		}
		
		
		return log ;
	}
}
