package cn.fam1452.action.filter;

public class BaseFilter {
	
	/**
	 * 排除那些不登陆即可访问的地址
	 */
	private final static String[] remove_menu = {"/ht/stationlistall","/ht/getstation"} ;
	
	/**
	 * 检查不登陆即可访问列表中是否存在该URL
	 * @param url
	 * @return
	 */
	protected boolean searchRemoveMenuArray(String url){
		boolean r = false ;
		for (String rm : remove_menu) {
			if(rm.equalsIgnoreCase(url)){
				r = true ;
				return r ;
			}
		}
		
		return r ;
	}
}
