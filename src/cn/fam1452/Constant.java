/**
 * 描述：项目常量定义
 */
package cn.fam1452;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFHeader;

/**
 * Class Constant
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 12, 2012 9:04:44 AM $
 */

public class Constant {
	
	public static String INDEXPAGE = "../index.jsp" ;
	
	public static String HT_USER_SESSION = "ht_account" ;
	
	public static final String LOGIN_VALIDATE_STRING="LOGIN_VALIDATE_STRING" ; //验证码的Key
	
	
	public static String QT_USER_SESSION = "qt_account" ;
	
	public static String INFO = "info" ;
	public static String SUCCESS = "success" ;
	
	public static String TOTAL = "total" ;
	public static String ROWS = "rows" ;
	public static int PAGE_SIZE=15;//默认分页数量
	
	public static int INDEX_NEWS_NUMS=5;//首页新闻条数
	public static int INDEX_NEWS2_NUMS=3;//首页展示空间新闻条数
	public static int INDEX_META_NUMS=2;//首页元数据条数
	public static int META_DATA_PAGESIZE=4;//元数据查询分页大小
	//public static String[] paraAry = {"foF2","hlF2","foF1","hlF1","hlF","hpF","foE","hlE","foes","hlEs","fbEs","Fmin","M3000F2","M1500F2","M3000F1","M3000F"};
	//数据库查询字段名
	//public static String[] paraAry = {"foF2","h\'F2","foF1","h\'F","foE","h\'E","fmin","foEs","fbEs","h\'Es","Es-Type","M3000F1","M3000F2"};
	public static String[] paraAry = {"foF2","hlF2","foF1","hlF","foE","hlE","fmin","foEs","fbEs","hlEs","Es","M3000F1","M3000F2"};

	public static String[] monthAry = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	
	/**
	 * 后台报表扫描图存放路径
	 */
	public static final String sacnpicSavePath = "/data/sac_file/" ;
	
	public static int PROTECTDATA_SHOWNUM = 50;//数据保护期内，显示的数据条数
	public static final String PROTECTDATA_AREA="protectArea";//保护期区间
	/**
	 * 保护期查询时电离参数，频高图，扫描图三类数据的类型（表名称）
	 * */
	public static final String DATATYPE_PARAMETER="T_PARAMETER";
	public static final String DATATYPE_IRONOGRAM="T_IRONOGRAM";
	public static final String DATATYPE_SCANPIC="T_SCANPIC";
	
	
}
