package cn.fam1452.dao.pojo;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 统计网站访问量
 * @author gls
 * @date 2012-08-27
 */
@Table("T_Visit")
public class Visit {
	@Column
    @Name(casesensitive=false)
    @ColDefine(type=ColType.VARCHAR, width=20)
	private String id ;
	
	@Column
	private Long visitNum ;//访问量
	
	@Column
	private String areaID ;//地区

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(Long visitNum) {
		this.visitNum = visitNum;
	}
}
