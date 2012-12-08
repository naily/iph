/**
 * 描述：
 */
package cn.fam1452.utils;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.MappingField;
import org.nutz.dao.entity.PkType;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.jdbc.sqlserver2005.Sqlserver2005JdbcExpert;
import org.nutz.dao.jdbc.JdbcExpert;
import org.nutz.dao.jdbc.JdbcExpertConfigFile;
import org.nutz.dao.sql.Sql;

import cn.fam1452.dao.pojo.Parameter;

/**
 * Class DataBaseUtil
 *
 * @author <a href="mailto:zhagndingding@cyanway.com">Derek</a>
 * @version $Revision:1.0.0, $Date:Dec 8, 2012 5:19:41 PM $
 */

public class DataBaseUtil extends Sqlserver2005JdbcExpert{

	public DataBaseUtil(JdbcExpertConfigFile conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Author Derek
	 * @Date Dec 8, 2012
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 获取所需配置文件
	 * @Author Derek
	 * @Date Dec 8, 2012
	 * @param dao
	 * @return
	 */
	public static JdbcExpertConfigFile getConfig(NutDao dao){
		JdbcExpert exp  = dao.getJdbcExpert() ;
		JdbcExpertConfigFile jecf = new JdbcExpertConfigFile();
		jecf.setConfig(exp.getConf()) ;
		return jecf ;
	}
	
	/**
	 * 创建观测站ID为表名的电离层参数表
	 * @Author Derek
	 * @Date Dec 8, 2012
	 * @param dao
	 * @param table
	 */
	public  boolean nutCreateParameterTable(NutDao dao ,  String table){
		boolean bn = false ;
		if(StringUtil.checkNotNull(table)){
			if(!dao.exists(table)){
				Entity<?> en = dao.getEntity(Parameter.class) ;
				bn = this.createEntity(dao, en, table) ;
				//DataBaseUtil dbu = new DataBaseUtil(exp) ;
				//dao.create(classOfT, dropIfExists)
			}
		}
		
		return bn ;
	}
	
	private static String COMMENT_COLUMN = "EXECUTE sp_addextendedproperty N'Description', '$columnComment', N'user', N'dbo', N'table', N'$table', N'column', N'$column'";
	public boolean createEntity(Dao dao, Entity<?> en , String tablename) {
		StringBuilder sb = new StringBuilder("CREATE TABLE " + tablename + "(");
		// 创建字段
		for (MappingField mf : en.getMappingFields()) {
			sb.append('\n').append(mf.getColumnName());
			sb.append(' ').append(evalFieldType(mf));
			// 非主键的 @Name，应该加入唯一性约束
			if (mf.isName() && en.getPkType() != PkType.NAME) {
				sb.append(" UNIQUE NOT NULL");
			}
			// 普通字段
			else {
				if (mf.isUnsigned())
					sb.append(" UNSIGNED");
				if (mf.isNotNull())
					sb.append(" NOT NULL");
				if (mf.isAutoIncreasement())
					sb.append(" IDENTITY");
				if (mf.hasDefaultValue())
					sb.append(" DEFAULT '").append(getDefaultValue(mf)).append('\'');
			}
			sb.append(',');
		}
		// 创建主键
		List<MappingField> pks = en.getPks();
		if (!pks.isEmpty()) {
			sb.append('\n');
			sb.append("PRIMARY KEY (");
			for (MappingField pk : pks) {
				sb.append(pk.getColumnName()).append(',');
			}
			sb.setCharAt(sb.length() - 1, ')');
			sb.append("\n ");
		}

		// 结束表字段设置
		sb.setCharAt(sb.length() - 1, ')');

		// 执行创建语句
		dao.execute(Sqls.create(sb.toString()));
		// 创建索引
		dao.execute(createIndexs(en).toArray(new Sql[0]));
		// 创建关联表
		createRelation(dao, en);
		// 添加注释(表注释与字段注释)
		addComment(dao, en, COMMENT_COLUMN);

		return true;
	}
	private void addComment(Dao dao, Entity<?> en, String commentColumn) {
		// TODO 表注释 SQLServer2005中貌似不行
		// 字段注释
		if (en.hasColumnComment()) {
			List<Sql> sqls = new ArrayList<Sql>();
			for (MappingField mf : en.getMappingFields()) {
				if (mf.hasColumnComment()) {
					Sql columnCommentSQL = Sqls.create(commentColumn);
					columnCommentSQL.vars()
									.set("table", en.getTableName())
									.set("column", mf.getColumnName())
									.set("columnComment", mf.getColumnComment());
					sqls.add(columnCommentSQL);
				}
			}
			// 执行创建语句
			dao.execute(sqls.toArray(new Sql[sqls.size()]));
		}
	}
}
