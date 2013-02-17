/**
 * 描述：
 */
package cn.fam1452.dao.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * Class Ironogram
 *	电离层频高图
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 12, 2012 2:52:29 PM $
 */
@Table("T_IRONOGRAM")
public class IronoGram {
	
	private String action ; //savedata  ,请求类型
	private String ids ; //很多id
	private String queryYear;//查询年份传参
	private String startDate;//查询开始日期
	private String endDate;//查询结束日期
	
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=100)
	private  String gramID ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=100)
	private String gramTitle ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=100)
	private String gramFileName ;
	
	@One(target = Station.class, field = "stationID")
    private Station station;
	@Column
	@ColDefine(type=ColType.VARCHAR, width=12)
	private String stationID ;  //观测站点ID
	
	@Column
	private Date createDate ; //频高图观测日期
	
	@Column
	private String type ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=200)
	private String gramPath ;

	public String getGramID() {
		return gramID;
	}

	public void setGramID(String gramID) {
		this.gramID = gramID;
	}

	public String getGramTitle() {
		return gramTitle;
	}

	public void setGramTitle(String gramTitle) {
		this.gramTitle = gramTitle;
	}

	public String getGramFileName() {
		return gramFileName;
	}

	public void setGramFileName(String gramFileName) {
		this.gramFileName = gramFileName;
	}

	public String getStationID() {
		return stationID;
	}

	public void setStationID(String stationID) {
		this.stationID = stationID;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGramPath() {
		return gramPath;
	}

	public void setGramPath(String gramPath) {
		this.gramPath = gramPath;
	}

	public Station getStation() {
		/*if(null!=station){
			station = new Station();
		}*/
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(String queryYear) {
		this.queryYear = queryYear;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
}
