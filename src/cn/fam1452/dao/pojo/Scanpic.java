package cn.fam1452.dao.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("T_SCANPIC")
public class Scanpic {
	
	private String action ; //savedata  ,请求类型
	private String ids ; //很多id
	private String queryYear;//查询年份传参
	private String startDate;//查询开始日期
	private String endDate;//查询结束日期
	
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=25)
	private String scanPicID ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=100)
	private String scanPicTitle ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=100)
	private String scanPicFileName ;
	
	@One(target = Station.class, field = "stationID")
    private Station station;
	@Column
	@ColDefine(type=ColType.VARCHAR, width=12)
	private String stationID ;
	
	@Column
	private Date createDate ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=100)
	private String gramPath ;

	public String getScanPicID() {
		return scanPicID;
	}

	public void setScanPicID(String scanPicID) {
		this.scanPicID = scanPicID;
	}

	public String getScanPicTitle() {
		return scanPicTitle;
	}

	public void setScanPicTitle(String scanPicTitle) {
		this.scanPicTitle = scanPicTitle;
	}

	public String getScanPicFileName() {
		return scanPicFileName;
	}

	public void setScanPicFileName(String scanPicFileName) {
		this.scanPicFileName = scanPicFileName;
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

	public String getGramPath() {
		return gramPath;
	}

	public void setGramPath(String gramPath) {
		this.gramPath = gramPath;
	}

	public Station getStation() {
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
