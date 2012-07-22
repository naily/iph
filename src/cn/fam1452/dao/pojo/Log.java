package cn.fam1452.dao.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

import cn.fam1452.action.bo.Pages;

@Table("T_LOG")
public class Log extends Pages{
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String id ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=2)
	private String actionType ; //01 = 录入 02 =编辑 03 = 删除
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=30)
	private String dataTable ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String adminId ;
	
	@Column
	private Date logDate ;
	
	@One(target = Administrator.class , field= "adminId")
	private Administrator admin ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getDataTable() {
		return dataTable;
	}

	public void setDataTable(String dataTable) {
		this.dataTable = dataTable;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}
	
	
}
