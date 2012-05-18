package cn.fam1452.dao.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

/**
 * 数据保护期表
 * Class Protectdate
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 13, 2012 11:55:32 AM $
 */
@Table("T_PROTECTDATE")
public class Protectdate {
	
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=8)
	private String id ;
	
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=30)
	private String dataTable ;
	
	@One(target = Station.class, field = "dataStation")
    private Station station;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=12)
	private String dataStation ;
	
	@Column
	private Date dataSDate ;
	
	@Column
	private Date dataEDate ;
	
	@Column
	private Date publicDate ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataTable() {
		return dataTable;
	}

	public void setDataTable(String dataTable) {
		this.dataTable = dataTable;
	}

	

	public String getDataStation() {
		return dataStation;
	}

	public void setDataStation(String dataStation) {
		this.dataStation = dataStation;
	}

	public Date getDataSDate() {
		return dataSDate;
	}

	public void setDataSDate(Date dataSDate) {
		this.dataSDate = dataSDate;
	}

	public Date getDataEDate() {
		return dataEDate;
	}

	public void setDataEDate(Date dataEDate) {
		this.dataEDate = dataEDate;
	}

	public Date getPublicDate() {
		return publicDate;
	}

	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
}
