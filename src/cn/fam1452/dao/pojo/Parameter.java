package cn.fam1452.dao.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("T_PARAMETER")
public class Parameter {
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=25)
	private String parameterID ;
	
	@One(target = Station.class, field = "stationID")
    private Station station;
	@Column
	@ColDefine(type=ColType.VARCHAR, width=12)
	private String stationID ; //观测站点编号
	
	@Column
	private Date createDate ;
	
	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String foF2 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String h1F2 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String foF1 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String h1F1 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String hlF ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String hpF ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String foE ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String hlE ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String foEs ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String hlEs ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String fbEs ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String Fmin ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String M3000F2 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String M1500F2 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String M3000F1 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String M3000F ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String para20 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String para21 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String para22 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String para23 ;

	@Column
	@ColDefine(type=ColType.VARCHAR, width=8)
	private String para24 ;

	public String getParameterID() {
		return parameterID;
	}

	public void setParameterID(String parameterID) {
		this.parameterID = parameterID;
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

	public String getH1F2() {
		return h1F2;
	}

	public void setH1F2(String h1f2) {
		h1F2 = h1f2;
	}

	public String getFoF1() {
		return foF1;
	}

	public void setFoF1(String foF1) {
		this.foF1 = foF1;
	}

	public String getH1F1() {
		return h1F1;
	}

	public void setH1F1(String h1f1) {
		h1F1 = h1f1;
	}

	public String getHlF() {
		return hlF;
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

	public String getPara20() {
		return para20;
	}

	public void setPara20(String para20) {
		this.para20 = para20;
	}

	public String getPara21() {
		return para21;
	}

	public void setPara21(String para21) {
		this.para21 = para21;
	}

	public String getPara22() {
		return para22;
	}

	public void setPara22(String para22) {
		this.para22 = para22;
	}

	public String getPara23() {
		return para23;
	}

	public void setPara23(String para23) {
		this.para23 = para23;
	}

	public String getPara24() {
		return para24;
	}

	public void setPara24(String para24) {
		this.para24 = para24;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
	
	
}
