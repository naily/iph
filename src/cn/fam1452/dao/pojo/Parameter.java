package cn.fam1452.dao.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

import sun.security.util.BigInt;

@Table("T_PARAMETER")
public class Parameter {
	private String ids ; //很多id
	
	/*@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=25)
	private String parameterID ;*/
	
	@Column
    @Id
    @ColDefine(customType="bigint")
	private long parameterID ;
	
	@One(target = Station.class, field = "stationID")
    private Station station;
	@Column
	@ColDefine(type=ColType.VARCHAR, width=12)
	private String stationID ; //观测站点编号
	
	@Column
	private Date createDate ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String foF2 ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String fxF2 ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String fxl ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String hlF2 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String foF1 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String hlF1 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String hlF ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String hpF ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String hpF2 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String foE ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String hlE ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String foEs ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String hlEs ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String fbEs ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String Es ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String Fmin ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String M1500F2 ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String M3000F2 ;


	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String M3000F1 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String M3000F ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String MUF3000F1 ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=10)
	private String MUF3000F2 ;


	

	public String getFxF2() {
		return fxF2;
	}

	public void setFxF2(String fxF2) {
		this.fxF2 = fxF2;
	}

	public String getFxl() {
		return fxl;
	}

	public void setFxl(String fxl) {
		this.fxl = fxl;
	}

	public String getHpF2() {
		return hpF2;
	}

	public void setHpF2(String hpF2) {
		this.hpF2 = hpF2;
	}

	public String getEs() {
		return Es;
	}

	public void setEs(String es) {
		Es = es;
	}

	public String getMUF3000F1() {
		return MUF3000F1;
	}

	public void setMUF3000F1(String muf3000f1) {
		MUF3000F1 = muf3000f1;
	}

	public String getMUF3000F2() {
		return MUF3000F2;
	}

	public void setMUF3000F2(String muf3000f2) {
		MUF3000F2 = muf3000f2;
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

	public String getFoF2() {
		return foF2;
	}

	public void setFoF2(String foF2) {
		this.foF2 = foF2;
	}


	public void setFoF1(String foF1) {
		this.foF1 = foF1;
	}


	public String getHlF() {
		return hlF;
	}

	public String getHlF2() {
		return hlF2;
	}

	public void setHlF2(String hlF2) {
		this.hlF2 = hlF2;
	}

	public String getHlF1() {
		return hlF1;
	}

	public void setHlF1(String hlF1) {
		this.hlF1 = hlF1;
	}

	public String getFoF1() {
		return foF1;
	}

	public void setHlF(String hlF) {
		this.hlF = hlF;
	}

	public String getHpF() {
		return hpF;
	}

	public void setHpF(String hpF) {
		this.hpF = hpF;
	}

	public String getFoE() {
		return foE;
	}

	public void setFoE(String foE) {
		this.foE = foE;
	}

	public String getHlE() {
		return hlE;
	}

	public void setHlE(String hlE) {
		this.hlE = hlE;
	}

	public String getFoEs() {
		return foEs;
	}

	public void setFoEs(String foEs) {
		this.foEs = foEs;
	}

	public String getHlEs() {
		return hlEs;
	}

	public void setHlEs(String hlEs) {
		this.hlEs = hlEs;
	}

	public String getFbEs() {
		return fbEs;
	}

	public void setFbEs(String fbEs) {
		this.fbEs = fbEs;
	}

	public String getFmin() {
		return Fmin;
	}

	public void setFmin(String fmin) {
		Fmin = fmin;
	}

	public String getM3000F2() {
		return M3000F2;
	}

	public void setM3000F2(String m3000f2) {
		M3000F2 = m3000f2;
	}

	public String getM1500F2() {
		return M1500F2;
	}

	public void setM1500F2(String m1500f2) {
		M1500F2 = m1500f2;
	}

	public String getM3000F1() {
		return M3000F1;
	}

	public void setM3000F1(String m3000f1) {
		M3000F1 = m3000f1;
	}

	public String getM3000F() {
		return M3000F;
	}

	public void setM3000F(String m3000f) {
		M3000F = m3000f;
	}

	

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public long getParameterID() {
		return parameterID;
	}

	public void setParameterID(long parameterID) {
		this.parameterID = parameterID;
	}

	
	
	
}
