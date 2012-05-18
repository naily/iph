package cn.fam1452.dao.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_DATASERVICE")
public class DataService {
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=12)
	private String id ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=2 , notNull = true)
	private String actionType ; //01 = 查询 02= 浏览 03=下载
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String searchTable  ;
	
	@Column
    @ColDefine(type=ColType.INT, width=10)
	private int resultNum1 ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String browseTable ; 
	
	@Column
    @ColDefine(type=ColType.INT, width=10)
	private int resultNum2 ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String downloadTable ;
	
	@Column
    @ColDefine(type=ColType.INT, width=10)
	private int resultNum3 ; 
	
	@Column
	private float resultAmount ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String userId ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String userIP ;
	
	@Column
	private Date serviceDate ;

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

	public String getSearchTable() {
		return searchTable;
	}

	public void setSearchTable(String searchTable) {
		this.searchTable = searchTable;
	}

	public int getResultNum1() {
		return resultNum1;
	}

	public void setResultNum1(int resultNum1) {
		this.resultNum1 = resultNum1;
	}

	public String getBrowseTable() {
		return browseTable;
	}

	public void setBrowseTable(String browseTable) {
		this.browseTable = browseTable;
	}

	public int getResultNum2() {
		return resultNum2;
	}

	public void setResultNum2(int resultNum2) {
		this.resultNum2 = resultNum2;
	}

	public String getDownloadTable() {
		return downloadTable;
	}

	public void setDownloadTable(String downloadTable) {
		this.downloadTable = downloadTable;
	}

	public int getResultNum3() {
		return resultNum3;
	}

	public void setResultNum3(int resultNum3) {
		this.resultNum3 = resultNum3;
	}

	public float getResultAmount() {
		return resultAmount;
	}

	public void setResultAmount(float resultAmount) {
		this.resultAmount = resultAmount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	
	
}
