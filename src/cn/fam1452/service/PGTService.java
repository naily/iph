package cn.fam1452.service;

import java.sql.Date;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.Constant;
import cn.fam1452.action.bo.Pages;
import cn.fam1452.action.bo.ParameteDataBo;
import cn.fam1452.dao.pojo.IronoGram;
import cn.fam1452.dao.pojo.ProtectDate;
import cn.fam1452.dao.pojo.Scanpic;
import cn.fam1452.utils.DateUtil;
import cn.fam1452.utils.StringUtil;
/**
 * 频高图相关操作
 * 
 * Class ParameterService
 *
 */


@IocBean(name = "pgtService")
public class PGTService extends Base{
	@Inject("refer:parameterService")
	private ParameterService parameterService ;
	
	
	/**
	 * 频高图查询
	 * */
	   public List<IronoGram> pgtDataList(IronoGram irg,Pages page,ParameteDataBo paraQuery){
		   	
		   Condition cnd=getPGTQuery(irg,paraQuery);			
			List<IronoGram> list =  this.dao.query(IronoGram.class, cnd, page.getNutzPager()); 
			return list;
		}
	   public Condition getPGTQuery(IronoGram irg,ParameteDataBo paraQuery){
		   Condition cnd;
			String[] stationIDS =null;
			if(StringUtil.checkNull(paraQuery.getOrderBy())){
				paraQuery.setOrderBy("stationID");//默认排序方式：观测站
			}
			if(StringUtil.checkNotNull(irg.getIds())){
				stationIDS= irg.getIds().split(",");
			}
			if(StringUtil.checkNotNull(paraQuery.getStartDate()) && StringUtil.checkNotNull(paraQuery.getEndDate()) && StringUtil.checkNull(paraQuery.getSelectAllDate())){
				Date start = DateUtil.convertStringToSqlDate(paraQuery.getStartDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
				Date end = DateUtil.convertStringToSqlDate(paraQuery.getEndDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
				cnd= Cnd.where("stationID", "in", stationIDS).and("createDate", ">=",start).and("createDate","<=",end).asc(paraQuery.getOrderBy());
			}else{//不选择日期区间时，查询所有日期的数据
			    cnd = Cnd.where("stationID", "in", stationIDS).asc(paraQuery.getOrderBy());
			}	
		   return cnd;
	   }
   	/**
   	 * 有保护期的数据查询
   	 * */
    public List<IronoGram> top50PGTDataList(IronoGram irg,Pages page,ParameteDataBo paraQuery){	
    	Sql sql =Sqls.create(getQueryPGTSQL(irg,paraQuery));
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(IronoGram.class));
		this.dao.execute(sql) ;		
		List<IronoGram> list = sql.getList(IronoGram.class) ;  
		return list;
		
	}
	/**
	 * 查询保护期内的频高图（前50条数据）
	 * */
 public String getQueryPGTSQL(IronoGram irg,ParameteDataBo paraQuery){
	 int shownums =Constant.PROTECTDATA_SHOWNUM;//默认显示记录数
	 String[] stationIDS =null;//观测站数组
		 if(StringUtil.checkNull(paraQuery.getOrderBy())){
			paraQuery.setOrderBy("stationID");//默认排序方式：观测站
		 }
		 if(StringUtil.checkNotNull(irg.getIds())){
			stationIDS= irg.getIds().split(",");
		 }
		 String queryStationArry="";
		 for(String s:stationIDS){
			if(!"".equals(queryStationArry)){
				queryStationArry+=",";
			 }
			 queryStationArry+="\'"+s+"\'";
		 }
		// log.info("stationIDS=="+stationIDS.toString());
	 StringBuffer sb = new StringBuffer("select top "); 
	 sb.append(shownums);
	 sb.append(" * from T_IRONOGRAM");
	 sb.append(" where stationID in (").append(queryStationArry).append(")");//params.getIds()
	 if(StringUtil.checkNotNull(paraQuery.getStartDate()) && StringUtil.checkNotNull(paraQuery.getEndDate())){//前台查询日期区间
			Date start = DateUtil.convertStringToSqlDate(paraQuery.getStartDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			Date end = DateUtil.convertStringToSqlDate(paraQuery.getEndDate()+" 00:00:00","yyyy-MM-dd HH:mm:ss");
			 sb.append(" and createDate >='").append(start).append("' and createDate <='").append(end).append("'");
		 }
	/* List<ProtectDate> protectDateList = parameterService.getProtectDate("T_IRONOGRAM");
	 if(null!=protectDateList && protectDateList.size()>0){
		 sb.append(" and ( ");
//		 for(ProtectDate proDate:protectDateList){
		 for(int i=0;i<protectDateList.size();i++){
			 ProtectDate proDate =(ProtectDate)protectDateList.get(i);
			 if(null!=proDate.getDataSDate() && null!=proDate.getDataEDate()){
				
				if(i>0)sb.append(" or ");
				sb.append(" createDate between '").append(DateUtil.convertDateToString(proDate.getDataSDate())).append("' and '").append(DateUtil.convertDateToString(proDate.getDataEDate())).append("' "); 
			 }
		 }
		 sb.append(" ) ");
	 }*/
	 sb.append(" order by ").append(paraQuery.getOrderBy());
     log.info(sb.toString());
	 return sb.toString();
 }
	
}
