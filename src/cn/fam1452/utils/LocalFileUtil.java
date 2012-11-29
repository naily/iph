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
	
	public static JSONObject testServerFileDirectory(String path){
		JSONObject json = new JSONObject();
		json.put(Constant.SUCCESS, false) ;
		if(StringUtil.checkNotNull(path)){
			File file = new File(path) ;
			if(file.exists() && file.isDirectory()){
				String filter = "Thumbs.db" ;
				File[] list = file.listFiles() ;
				if(null != list && list.length >0){
					File f = list[0] ;
					while(filter.equalsIgnoreCase(f.getName())){
						f = list[1] ;
					}
					
					json.put(Constant.SUCCESS, true) ;
					json.put(Constant.INFO, f.getName()) ;
					
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
