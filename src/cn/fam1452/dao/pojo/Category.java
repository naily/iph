/**
 * 描述：目录表
 */
package cn.fam1452.dao.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * Class Category
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:May 13, 2012 1:06:32 PM $
 */
@Table("T_CATEGORY")
public class Category {
	
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=11)
	private String id ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String title ;
	
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=50)
	private String parentId ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=2)
	private String type ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=300)
	private String childNodeRule ;
	
	@Column
    @ColDefine(type=ColType.VARCHAR, width=300)
	private String dataURL ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChildNodeRule() {
		return childNodeRule;
	}

	public void setChildNodeRule(String childNodeRule) {
		this.childNodeRule = childNodeRule;
	}

	public String getDataURL() {
		return dataURL;
	}

	public void setDataURL(String dataURL) {
		this.dataURL = dataURL;
	}
	
	
}
