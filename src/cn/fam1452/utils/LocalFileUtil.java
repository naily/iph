package cn.fam1452.utils;

import java.io.File;

import net.sf.json.JSONObject;
import cn.fam1452.Constant;

/**
 * 本地文件操作工具类
 * Class LocalFileUtil
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Nov 27, 2012 11:43:36 AM $
 */
public class LocalFileUtil {

	/**
	 * @Author Derek
	 * @Date Nov 27, 2012
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 手动频高图 解析方式
	 * @Author Derek
	 * @Date Feb 17, 2013
	 * @param path
	 * @return
	 */
	public static JSONObject testServerFileDirectory(String path){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		if(StringUtil.checkNotNull(path)){
			File file = new File(path) ;
			int fileTotal = 0 ;
			int yearTotal = 0 ;
			StringBuilder yearstr = new StringBuilder()  ;
			if(file.exists() && file.isDirectory()){
				String filter = "Thumbs.db" ;
				File[] years = file.listFiles() ;
				if(null != years && years.length >0){
					
					for (File y : years) {
						if(null != y && y.isDirectory()){
							if(null != y.listFiles())
								fileTotal += y.listFiles().length ;
							yearstr.append(y.getName()).append(",") ;
							yearTotal++ ;
						}
					}
					
					json.put(Constant.SUCCESS, true) ;
					json.put(Constant.INFO, yearstr.toString()) ;
					json.put("fileTotal", fileTotal) ;
					json.put("yearTotal", yearTotal) ;
					
				}else{
					json.put(Constant.INFO, "目录为空没有文件") ;
				}
			}else{
				json.put(Constant.INFO, "路径不存在或不是一个目录") ;
			}
			 
		}else{
			json.put(Constant.INFO, "参数为空") ;
		}
		
		return json ;
	}
	/**
	 * 胶片频高图目录测试
	 * 传入的值：年的上一级目录
	 * @Author Derek
	 * @Date Feb 17, 2013
	 * @param path
	 * @return
	 */
	public static JSONObject testServerFileDirectory2(String path){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		if(StringUtil.checkNotNull(path)){
			File file = new File(path) ;
			int fileTotal = 0 ;
			int yearTotal = 0 ;
			StringBuilder yearstr = new StringBuilder()  ;
			if(file.exists() && file.isDirectory()){
				String filter = "Thumbs.db" ;
				File[] years = file.listFiles() ; //得到全部年份
				
				if(null != years && years.length >0){
					for (File y : years) {
						if(null != y && y.isDirectory()){
							
							File[] months = y.listFiles() ; //得到月份
							for (File m : months) {
								File[] days = m.listFiles() ; //得到天数
								for (File d : days) {
									File[] f = d.listFiles() ;
									fileTotal  += f.length ;
								}
							}
							
							yearstr.append(y.getName()).append(",") ;
							yearTotal++ ;
						}
					}
					
					json.put(Constant.SUCCESS, true) ;
					json.put(Constant.INFO, yearstr.toString()) ;
					json.put("fileTotal", fileTotal) ;
					json.put("yearTotal", yearTotal) ;
					
				}else{
					json.put(Constant.INFO, "目录为空没有文件") ;
				}
			}else{
				json.put(Constant.INFO, "路径不存在或不是一个目录") ;
			}
			
		}else{
			json.put(Constant.INFO, "参数为空") ;
		}
		
		return json ;
	}
	
	/**
	 * 报表扫描图测试
	 * @Author Derek
	 * @Date Feb 18, 2013
	 * @param path
	 * @return
	 */
	public static JSONObject testServerFileDirectory3(String path){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		if(StringUtil.checkNotNull(path)){
			File file = new File(path) ;
			int fileTotal = 0 ;
			int yearTotal = 0 ;
			StringBuilder yearstr = new StringBuilder()  ;
			if(file.exists() && file.isDirectory()){
				String filter = "Thumbs.db" ;
				File[] years = file.listFiles() ;
				if(null != years && years.length >0){
					
					for (File y : years) {
						if(null != y && y.isDirectory()){
							if(null != y.listFiles())
								fileTotal += y.listFiles().length ;
							yearstr.append(y.getName()).append(",") ;
							yearTotal++ ;
						}
					}
					
					json.put(Constant.SUCCESS, true) ;
					json.put(Constant.INFO, yearstr.toString()) ;
					json.put("fileTotal", fileTotal) ;
					json.put("yearTotal", yearTotal) ;
					
				}else{
					json.put(Constant.INFO, "目录为空没有文件") ;
				}
			}else{
				json.put(Constant.INFO, "路径不存在或不是一个目录") ;
			}
			 
		}else{
			json.put(Constant.INFO, "参数为空") ;
		}
		
		return json ;
	}

}
