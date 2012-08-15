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
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String mdId ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=100)
	private String title ;  //元数据标题
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=100)
	private String keyword ;
	
	@Column
    @ColDefine(type=ColType.TEXT )
	private String  summary; //元数据摘要原abstract ,s是java关键字 
	
	@Column
	@ColDefine(customType="varbinary(MAX)")
	private Blob thumbnail ; //元数据缩略图
	
	@Column
    @ColDefine(customType="varbinary(MAX)")
	private Blob fullContent ; //详细元数据XML
	
	@Column
	private String fullContentFilePath ; //XML文件路径
	
	@Column
	private String thumbnailFilePath ; //元数据缩略图文件路径
	
	@Column
    @ColDefine(type=ColType.DATE)
	private Date mdDate ;//元数据创建日期

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

	public String getThumbnailFilePath() {
		return thumbnailFilePath;
	}

	public void setThumbnailFilePath(String thumbnailFilePath) {
		this.thumbnailFilePath = thumbnailFilePath;
	}

	
	
	
}
