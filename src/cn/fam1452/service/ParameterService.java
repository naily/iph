package cn.fam1452.service;

import java.util.List;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import cn.fam1452.action.bo.ParameteDataBo;
import cn.fam1452.dao.pojo.DataService;
import cn.fam1452.utils.StringUtil;

@IocBean(name = "parameterService")
public class ParameterService extends Base{
	
	public List parameterMonthReport(ParameteDataBo pdb){
		/*dao.setSqlManager(new FileSqlManager("all.sqls"));
		Sql sql =dao.sqls().create("insert.data");
		dao.execute(sql);*/
		Sql sql =Sqls.create(getQuerySQL(pdb));
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(ParameteDataBo.class));
		this.dao.execute(sql) ;		
		List<ParameteDataBo> list = sql.getList(ParameteDataBo.class) ;
		 return list;
	}
   public String getQuerySQL(ParameteDataBo pdb){
	   String returnStr=null;
	   StringBuffer sb = new StringBuffer();
	   sb.append("select days,stationID");
	   sb.append(",max(case a.hours when '0'  then ").append(pdb.getParaType()).append(" else 0 end) 'h00'");
	   sb.append(",max(case a.hours when '1'  then ").append(pdb.getParaType()).append(" else 0 end) 'h01'");
	   sb.append(",max(case a.hours when '2'  then ").append(pdb.getParaType()).append(" else 0 end) 'h02'");
	   sb.append(",max(case a.hours when '3'  then ").append(pdb.getParaType()).append(" else 0 end) 'h03'");
	   sb.append(",max(case a.hours when '4'  then ").append(pdb.getParaType()).append(" else 0 end) 'h04'");
	   sb.append(",max(case a.hours when '5'  then ").append(pdb.getParaType()).append(" else 0 end) 'h05'");
	   sb.append(",max(case a.hours when '6'  then ").append(pdb.getParaType()).append(" else 0 end) 'h06'");
	   sb.append(",max(case a.hours when '7'  then ").append(pdb.getParaType()).append(" else 0 end) 'h07'");
	   sb.append(",max(case a.hours when '8'  then ").append(pdb.getParaType()).append(" else 0 end) 'h08'");
	   sb.append(",max(case a.hours when '9'  then ").append(pdb.getParaType()).append(" else 0 end) 'h09'");
	   sb.append(",max(case a.hours when '10' then ").append(pdb.getParaType()).append(" else 0 end) 'h10'");
	   sb.append(",max(case a.hours when '11' then ").append(pdb.getParaType()).append(" else 0 end) 'h11'");
	   sb.append(",max(case a.hours when '12' then ").append(pdb.getParaType()).append(" else 0 end) 'h12'");
	   sb.append(",max(case a.hours when '13' then ").append(pdb.getParaType()).append(" else 0 end) 'h13'");
	   sb.append(",max(case a.hours when '14' then ").append(pdb.getParaType()).append(" else 0 end) 'h14'");
	   sb.append(",max(case a.hours when '15' then ").append(pdb.getParaType()).append(" else 0 end) 'h15'");
	   sb.append(",max(case a.hours when '16' then ").append(pdb.getParaType()).append(" else 0 end) 'h16'");
	   sb.append(",max(case a.hours when '17' then ").append(pdb.getParaType()).append(" else 0 end) 'h17'");
	   sb.append(",max(case a.hours when '18' then ").append(pdb.getParaType()).append(" else 0 end) 'h18'");
	   sb.append(",max(case a.hours when '19' then ").append(pdb.getParaType()).append(" else 0 end) 'h19'");
	   sb.append(",max(case a.hours when '20' then ").append(pdb.getParaType()).append(" else 0 end) 'h20'");
	   sb.append(",max(case a.hours when '21' then ").append(pdb.getParaType()).append(" else 0 end) 'h21'");
	   sb.append(",max(case a.hours when '22' then ").append(pdb.getParaType()).append(" else 0 end) 'h22'");
	   sb.append(",max(case a.hours when '23' then ").append(pdb.getParaType()).append(" else 0 end) 'h23'");
	   sb.append(" from (");
	   sb.append("select stationID,").append(pdb.getParaType()).append(", datepart(dd,createdate) as days,datepart(HH,createdate) as hours from t_parameter");
	  if(StringUtil.checkNotNull(pdb.getYear()) && StringUtil.checkNotNull(pdb.getMonth())){
		  sb.append(" where datepart(YY,createdate)='").append(pdb.getYear()).append("'");
		  sb.append(" and datepart(MM,createdate)='").append(pdb.getMonth()).append("'");
	  }
	   
	   sb.append(") as a");
	   sb.append(" group by a.days,a.stationID");
	    
	   returnStr= sb.toString();
	   //System.out.println("sql="+returnStr);
	   sb.delete(0,sb.length()-1);
	   return returnStr;
   }
}
