package cn.fam1452.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.dao.pojo.DataService;
import cn.fam1452.dao.pojo.Log;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;

/**
 * 数据服务记录表T_DATASERVICE
 * 
 * Class DataLogService
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Jul 18, 2012 9:01:39 PM $
 */
@IocBean(name = "dataVisitService")
public class DataVisitService extends Base{
	
	
	public void insert(DataService obj){
		if(null != obj && StringUtil.checkNotNull(obj.getId())){
			this.dao.insert(obj) ;
		}
		
	}
	/**
	 * 元数据操作时记录其操作信息
	 * @param actionType 数据服务类别，01查询（含导航），02浏览，03下载
	 * @param resultNum  该次操作影响数据库 影响记录数
	 * @param userId     用户登录名，匿名访问时不记录。外键关联：T_ USER.loginID
	 * @param userIP     用户IP，自动记录
	 * @param resultAmount 下载量，单位（M）[actionType为03的时候必须输入该值，其余可填空值]
	 */
	public void insertMetaData(String actionType ,int resultNum ,  String userId , String userIP , Float resultAmount){
		this.insert("T_METADATA", actionType, resultNum, userId, userIP, resultAmount) ;
		
	}
	
	
	/**
	 * 记录前台对数据的操作信息
	 * @param tableName  [T_METADATA ; T_IRONOGRAM ;T_PARAMETER]
	 * @param actionType 数据服务类别，01查询（含导航），02浏览，03下载
	 * @param resultNum  该次操作影响数据库 影响记录数
	 * @param userId     用户登录名，匿名访问时不记录。外键关联：T_ USER.loginID
	 * @param userIP     用户IP，自动记录
	 * @param resultAmount 下载量，单位（M）[actionType为03的时候必须输入该值，其余可填空值]
	 */
	public void insert(String tableName , String actionType ,int resultNum ,  String userId , String userIP , Float resultAmount){
		DataService ds = new DataService() ;
		ds.setId(String.valueOf(System.currentTimeMillis()) ) ;
		ds.setUserId(userId) ;
		ds.setUserIP(userIP) ;
		ds.setServiceDate(DateUtil.getCurrentDate()) ;
		ds.setActionType(actionType) ;
		
		if("01".equals(actionType)){
			ds.setSearchTable(tableName) ;
			ds.setResultNum1(resultNum) ;
		}else if("02".equals(actionType)){
			ds.setResultNum2(resultNum) ;
			ds.setBrowseTable(tableName) ;
		}else if("03".equals(actionType)){
			ds.setResultNum3(resultNum) ;
			ds.setDownloadTable(tableName) ;
			ds.setResultAmount(resultAmount) ;
		} else{
			return ;
		}
		
		this.insert(ds) ;
	}
	/**
	 * 查询
	 * @return
	 */
	public List<DataService> statsSearchTable(){
		Sql sql = Sqls.create("select searchTable , sum(resultNum1) resultNum1 , count(searchTable) resultNum2 from T_DATASERVICE where actionType='01' group by searchTable") ;
		/*sql.setCallback(new SqlCallback(){
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				// TODO Auto-generated method stub
				while (rs.next()) {
					log.info(rs.getString("searchTable" )) ;
				}
				return null;
			}
			
		}) ;*/
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(DataService.class)) ;
		
		this.dao.execute(sql) ;
		
		List<DataService> list = sql.getList(DataService.class) ;
		return list ;
	}
	/**
	 *下载
	 * @return
	 */
	public List<DataService> statsDownloadTable(){
		Sql sql = Sqls.create("select downloadTable , sum(resultNum3) resultNum1 , count(downloadTable) resultNum2 from T_DATASERVICE where actionType='03' group by downloadTable ") ;
		sql.setEntity(dao.getEntity(DataService.class)) ;
		
		this.dao.execute(sql) ;
		
		List<DataService> list = sql.getList(DataService.class) ;
		return list ;
	}
	
	/**
	 *浏览
	 * @return
	 */
	public List<DataService> statsBrowseTable(){
		Sql sql = Sqls.create("select browseTable , sum(resultNum2) resultNum1 , count(browseTable) resultNum2 from T_DATASERVICE where actionType='02' group by browseTable ") ;
		sql.setEntity(dao.getEntity(DataService.class)) ;
		
		this.dao.execute(sql) ;
		
		List<DataService> list = sql.getList(DataService.class) ;
		return list ;
	}
	
	public void statsv(){
		Sql sql = Sqls.create("") ;
		sql.setCallback(new SqlCallback(){
			public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
		}) ;
		
		this.dao.execute(sql) ;
	}
}
