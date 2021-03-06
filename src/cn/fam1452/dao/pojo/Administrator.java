package cn.fam1452.dao.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_ADMINISTRATOR")
public class Administrator {
	private String code ; //登录验证码
	private boolean isLogin ;
	private String oldPass ;
	
	@Column
	@Name(casesensitive=false)
	@ColDefine(type = ColType.VARCHAR , width = 12)
	private String loginId ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String password ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=10)
	private String name ;
	
	@Column
	@ColDefine(type = ColType.BOOLEAN)
	private boolean isSuper ;

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

	public boolean isSuper() {
		return isSuper;
	}

	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	
	
}
