package cn.fam1452.dao.pojo;

import java.sql.Blob;
import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("T_NEWS")
public class News {
	
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String newsId ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String title ;
	
	@Column
    @ColDefine(type=ColType.TEXT, width=8000)
	private String content ;
	
	@Column
    @ColDefine(type=ColType.DATE)
	private Date publishDate ;
	
	@Column
    @ColDefine(type=ColType.BOOLEAN)
	private boolean isPicNews ;
	
	@Column
	@ColDefine(customType="binary(1000)")
	private Blob picture ;

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public boolean isPicNews() {
		return isPicNews;
	}

	public void setPicNews(boolean isPicNews) {
		this.isPicNews = isPicNews;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}
	
}
