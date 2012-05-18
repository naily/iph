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

/**
 * Class UserComment
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 13, 2012 4:59:02 PM $
 */

@Table("T_USERCOMMENT")
public class UserComment {
	
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=12)
	private String id ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String userId ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String uerIP ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=2000)
	private String content ;
	
	@Column
	private Date commentDate ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUerIP() {
		return uerIP;
	}

	public void setUerIP(String uerIP) {
		this.uerIP = uerIP;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	
	
}
