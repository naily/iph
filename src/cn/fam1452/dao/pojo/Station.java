package cn.fam1452.dao.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;


/**
 * 电离层观测站表
 * Class Station
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 12, 2012 7:08:59 PM $
 */
@Table("T_STATION")
public class Station {

	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=12)
	private String id ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=100 , notNull=true)
	private String name ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=200)
	private String location ;
	
	@Column
	private String longitude ;
	
	@Column
	private String latitude ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=10)
	private String timeZone ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=1000)
	private String introduction ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=100)
	private String administrator ;//管理单位名称
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=200)
	private String address ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=6)
	private String zipCode ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String phone ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String email ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=100)
	private String homepage ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=100)
	private String picPath ;
	
	@Column
	private int status ; //该表是个基表，不要物理删除它，需要删除时更新该字段即可。1 == 正常 ，0 == 已删除
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String tableName ;  //观测站表

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
