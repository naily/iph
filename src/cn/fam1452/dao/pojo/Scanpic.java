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
	
	
}
