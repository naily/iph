package cn.fam1452.dao.pojo;

import java.sql.Blob;
import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_METADATA")
public class MetaData {
	
	
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=11)
	private String mdId ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String title ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String keyword ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=300)
	private String  summary; //原abstract ,s是java关键字 
	
	@Column
	@ColDefine(customType="binary(2000)")
	private Blob thumbnail ;
	
	@Column
    @ColDefine(customType="binary(2000)")
	private Blob fullContent ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=100)
	private String fullContentFilePath ;
	
	@Column
    @ColDefine(type=ColType.DATE)
	private Date mdDate ;

	public String getMdId() {
		return mdId;
	}

	public void setMdId(String mdId) {
		this.mdId = mdId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Blob getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Blob thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Blob getFullContent() {
		return fullContent;
	}

	public void setFullContent(Blob fullContent) {
		this.fullContent = fullContent;
	}

	public Date getMdDate() {
		return mdDate;
	}

	public void setMdDate(Date mdDate) {
		this.mdDate = mdDate;
	}

	public String getFullContentFilePath() {
		return fullContentFilePath;
	}

	public void setFullContentFilePath(String fullContentFilePath) {
		this.fullContentFilePath = fullContentFilePath;
	}

	
	
	
}
