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
	private int limit = 5; //omGrid
	
	public Pager getNutzPager(){
		Pager p = new Pager();
		if(this.getStart() >= this.getLimit() && this.getLimit() > 0){
			p.setPageNumber( (this.getStart()/this.getLimit()) +1 ) ;
		}
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
