package cn.fam1452.dao.pojo;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("T_FEEDBACK")
public class FeedBack {
	
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String id ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String commentId ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=2000)
	private String feedback ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String adminId ;
	
	@Column
	private Date feedbackDate ;
	
	@One(target = Administrator.class, field = "adminId")
	private Administrator admin ;
	
	@One(target = UserComment.class, field = "commentId")
	private UserComment comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	public UserComment getComment() {
		return comment;
	}

	public void setComment(UserComment comment) {
		this.comment = comment;
	}
	
	
}
