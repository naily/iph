package cn.fam1452.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccessUtil {

	
	private final String driver = "sun.jdbc.odbc.JdbcOdbcDriver" ;
	private String dburl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=#mdbpath#;DriverID=22;READONLY=true}" ;
	
//	private final String driver = "com.hxtt.sql.access.AccessDriver" ;
//	private final String dburl = "jdbc:Access:///#mdbpath#" ;
	
	
	public static final String[] fieldsName = new String[]{"foF2","fxF2","fxl","hlF2","foF1","hlF1","hlF","hpF","hpF2","foE","hlE","foEs","hlEs","fbEs","es","fmin","m3000F2","m3000F1","m3000F" ,"mUF3000F1","mUF3000F2"} ;
	
	private String accessFile ;
	public AccessUtil(){} 
	public AccessUtil(String mdbpath){
		this.accessFile = mdbpath ;
		
	} 
	
	public Connection getConnection(){
		if(null != this.accessFile ){
			return getConnection(this.accessFile) ;
		}else{
			return null ;
		}
	}
	
	public Connection getConnection(String mdbpath){
		Connection c = null ;
		try {
			Class.forName(driver) ;
			c =  DriverManager.getConnection( dburl.replace("#mdbpath#", mdbpath) ,"","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return c ;
	}
	/**
	 * 执行一个SQL
	 * @Author Derek
	 * @Date Oct 4, 2012
	 * @param stat
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet execSQL(Statement stat , String sql) throws SQLException{
		ResultSet rs = null ;
		//System.out.println(sql);
		if(stat.execute(sql) ){
			rs = stat.getResultSet();
		}
		return rs ;
	}
	
	/**
	 * 求出总记录条数
	 * @Author Derek
	 * @Date Oct 4, 2012
	 * @param stat
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public static long getTotalRowNumber(Statement stat , String tableName) throws SQLException{
		long  total = 0 ;
		
		String sql = "select count(1) from " + tableName ;
		stat.execute(sql) ;
		
		ResultSet rs = stat.getResultSet();
		if (rs != null && rs.next()) {
			total = rs.getLong(1) ;
		}
		
		return total ;
	}
	
	/**
	 * 计算总页数
	 * @Author Derek
	 * @Date Oct 4, 2012
	 * @param totalRow
	 * @return
	 */
	public static long getTotalPageNumber(long totalRow){
		long pageTotal = 1 ;
		if(totalRow > pageSpeed){
			long y = totalRow % pageSpeed  ;
			pageTotal = totalRow / pageSpeed ;
			if( 0 != y){
				pageTotal = pageTotal + 1;
			}
		}
		
		return  pageTotal ;
	}
	/**
	 * 从ResultSetMetaData对象中，获取数据表所有的列名
	 * @Author Derek
	 * @Date Oct 4, 2012
	 * @param md
	 * @return
	 */
	public static List<String> getAllColumnName(ResultSetMetaData md) throws SQLException{
		List<String> array = new ArrayList<String>() ;
		if(null != md){
			int cc = md.getColumnCount() ;
			for(int i= 1 ; i <= cc ; i++){
				array.add(md.getColumnName(i)) ;
        	}
		}
		
		return array ;
	}
	
	/**
	 * 检查数组中是否存在指定的字符串
	 * @Author Derek
	 * @Date Oct 4, 2012
	 * @param array
	 * @param str
	 */
	public static boolean isExistString(List<String> array , String str){
		boolean r = false ;
		for (String obj : array) {
			if(obj.equalsIgnoreCase(str)){
				r = true ;
				break ;
			}
		}
		
		return r ;
	}
	
	/*
	 * 每页条数100
	 */
	public static final int pageSpeed = 50 ;
	
	public static void main(String[] a){
		//String path = "E:/GHY_____________/xyzWWWWW/数据示例/1946_1956电离层参数.mdb" ;
		String path = "E:/兼/dyy/地理所/1986-1991.mdb" ;
		Connection con = new AccessUtil().getConnection(path) ;
		
		try{
			Statement s = con.createStatement(); //myData
			
			long t = getTotalRowNumber(s , "myData" ) ;
			System.out.println("总记录数 : " + t);
			
			long pageTotal = getTotalPageNumber(t)   ;
			System.out.println("总记页数 : " + pageTotal);
			
			pageTotal = 3 ;
			//一个翻页查询的SQL
			//select top 每页数量 * from 表 where id >(select top 1 max(id) from (select top （页数-1）*每页数量 from 表 order by id,name)) 
			String tmpl = "select top PAGESPEED * from TABLENAME where ID >(select top 1 max(ID) from (select top BEFOREROW ID from TABLENAME order by ID)) " ;
			tmpl = tmpl.replaceAll("ID", "mytime").replaceAll("PAGESPEED", String.valueOf(pageSpeed)).replaceAll("TABLENAME", "myData") ;
			for(int i = 1 ; i <= pageTotal ; i++){
				int ii = (i-1)* pageSpeed ;
				String sql = tmpl.replaceAll("BEFOREROW", String.valueOf(ii)) ;
				
				if(i == 1){
					sql = sql.substring(0 , sql.indexOf("where")) ;
				}
				//sql = "select top 1 max(mytime) from (select top 2 mytime from myData order by mytime)" ;
				//System.out.println(sql);
				
				ResultSet rset = execSQL(s , sql ) ;
				if(null != rset){
					//把当前结果集中存在的fieldsName找出来
					List<String> available = new ArrayList<String>() ;
					List<String> list = getAllColumnName(rset.getMetaData()) ;
					for (String str : fieldsName) {
						if(isExistString(list, str) ){
							available.add(str) ;
						}
					}
					
					while (rset.next()) {
						
						StringBuilder ss = new StringBuilder(rset.getString("mytime")).append("\t");
						for (String fn : available) {
							ss.append(rset.getString(fn)).append("\t") ;
						}
						System.out.println(ss.toString());
					}
				}
			}
			
	        //s.execute("select count(1) from myData ");
	        ResultSet rs = null ;
	        if (rs != null) {
	        	ResultSetMetaData md =  rs.getMetaData() ; 
	        	int cc = md.getColumnCount() ; 
	        	System.out.println("总列数：" + cc);
	        	for(int i= 1 ; i <= cc ; i++){
	        		System.out.print(md.getColumnName(i) + "   ");
	        	}
	        	System.out.println( );
				System.out.println(".." + rs.getType() + "..");
	        	
				/*while (rs.next()) {
					//System.out.println(rs.getRow()); //当前行编号
					//读取一个二进制文件
					InputStream image = rs.getBinaryStream(2);
					FileOutputStream file = null;
					file = new FileOutputStream("c:/picture.jpg");
					int chunk;
					while ((chunk = image.read()) != -1)
						file.write(chunk);
					for(int i= 1 ; i <= cc ; i++){
						System.out.print(rs.getString(i) + "   ");
					}
					System.out.println();
				}
				*/
	        
			}
	        
	        rs.close() ;
	        s.close();
	        con.close();
			System.gc() ;
			
		}catch (Exception e) {
            e.printStackTrace() ;
        }
		
	}
	public String getDburl() {
		return dburl;
	}
	public void setDburl(String dburl) {
		this.dburl = dburl;
	}
}
