package cn.fam1452.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessUtil {

	
	private final String dirver = "sun.jdbc.odbc.JdbcOdbcDriver" ;
	private final String dburl = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=#mdbpath#;DriverID=22;READONLY=true}" ;


	public Connection getConnection(String mdbpath){
		Connection c = null ;
		try {
			Class.forName(dirver) ;
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
	
	public static void main(String[] a){
		Connection con = new AccessUtil().getConnection("E:/GHY_____________/xyzWWWWW/数据示例/1946_1956电离层参数.mdb") ;
		
		try{
			Statement s = con.createStatement();
	        s.execute("select * from wio ");
	        StringBuffer results = new StringBuffer();
	        ResultSet rs = s.getResultSet();

	        if (rs != null) {
	        	ResultSetMetaData md =  rs.getMetaData() ;
	        	int total = md.getColumnCount() ; 
	        	System.out.println("记录总行数：" + total);
				while (rs.next()) {
					/*
					 * 读取一个二进制文件
					InputStream image = rs.getBinaryStream(2);
					FileOutputStream file = null;
					file = new FileOutputStream("c:/picture.jpg");
					int chunk;
					while ((chunk = image.read()) != -1)
						file.write(chunk);
					 */
					
					System.out.println(rs.getString(2));
				}
			}
	        s.close();
	        con.close();
			System.gc() ;
		}catch (Exception e) {
            e.printStackTrace() ;
        }
		
	}
}
