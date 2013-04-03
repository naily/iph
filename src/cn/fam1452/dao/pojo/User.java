/**
 * 描述：
 */
package cn.fam1452.dao.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import cn.fam1452.action.bo.Pages;

/**
 * Class User
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 13, 2012 4:42:35 PM $
 */
@Table("T_USER")
public class User extends Pages{
	private String code ; //登录验证码
	private boolean isLogin;
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=12)
	private String loginId ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String password ;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String name ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=2)
	private String gender ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=6)
	private String eduBackground ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String email ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=2)
	private String vocation ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=5)
	private String country ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=8)
	private String region ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=100)
	private String workunit ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=100)
	private String address ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=6)
	private String zipcode ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String telephone ;
	
	@Column
	@ColDefine(type=ColType.DATE)
	private Date regDate ;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEduBackground() {
		return eduBackground;
	}

	public void setEduBackground(String eduBackground) {
		this.eduBackground = eduBackground;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVocation() {
		return vocation;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	
}
