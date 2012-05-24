/**
 * 
 */
package cn.fam1452.action.bo;

import org.nutz.dao.pager.Pager;

/**
 * @author zdd
 *	分页参数类
 */
public class Pages {

	private int start = 1; //omGrid
	private int limit ;    //omGrid
	
	public Pager getNutzPager(){
		Pager p = new Pager();
		p.setPageNumber(start <1 ? 1 : start) ;
		p.setPageSize(limit) ;
		return p ;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
