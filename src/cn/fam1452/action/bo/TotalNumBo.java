package cn.fam1452.action.bo;
/**
 * 前台访问统计数据
 * */
public class TotalNumBo {
    private Long longNum;
    private Double  doubleNum;
    private Float  floatNum;
	public Double getDoubleNum() {
		return doubleNum;
	}
	public void setDoubleNum(Double doubleNum) {
		this.doubleNum = doubleNum;
	}
	public Float getFloatNum() {
		return floatNum;
	}
	public void setFloatNum(Float floatNum) {
		this.floatNum = floatNum;
	}
	public Long getLongNum() {
		return longNum;
	}
	public void setLongNum(Long longNum) {
		this.longNum = longNum;
	}
}
