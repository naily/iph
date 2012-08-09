package cn.fam1452.action.bo;

import cn.fam1452.dao.pojo.Station;
/**
 * 电离报表查询参数（用于映射前台传参）
 * @author gls
 * @date 2012-08-09
 * */
public class ParameteDataBo {
	private Station station;//观测站
	private String stationID ; 
	private String paraType;//电离参数
	private String year ; //年度
	private String month ;//月份
	
	private String startDate;//观测开始日期
	private String endDate;//观测结束日期
	private String orderBy;//排序方式
	private String selectAllDate;//全选日期
	private String pageSize;//每页显示记录
	
	/*private String days;	
	private String h00 ;
	private String h01 ;
	private String h02 ;
	private String h03 ;
	private String h04 ;
	private String h05 ;
	private String h06 ;
	private String h07 ;
	private String h08 ;
	private String h09 ;
	private String h10 ;
	private String h11 ;
	private String h12 ;
	private String h13 ;
	private String h14 ;
	private String h15 ;
	private String h16 ;
	private String h17 ;
	private String h18 ;
	private String h19 ;
	private String h20 ;
	private String h21 ;
	private String h22 ;
	private String h23 ;*/
	
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getSelectAllDate() {
		return selectAllDate;
	}
	public void setSelectAllDate(String selectAllDate) {
		this.selectAllDate = selectAllDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public String getStationID() {
		return stationID;
	}
	public void setStationID(String stationID) {
		this.stationID = stationID;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
}
