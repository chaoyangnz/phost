/**
 * 
 */
package org.inframesh.phost.dao.ibatis;

import org.inframesh.phost.util.dto.TextUtil;


/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 * 
 *         2009-7-7
 */

/**
 * IBM DB2数据库方言的特定实现
 */
public class DB2Dialect implements Dialect {
	
	public final static String TOTAL_COUNT = "TOTAL__COUNT";

	/**
	 * 会有with ur 问题
	 * 
	 */
	public String getLimitSql(String sql, String[] orderColumns, int offset, int limit) {
		int start = offset;
		int end = offset + limit;
		
		String orderSql = null;
	
		if(orderColumns != null && orderColumns.length != 0) {
			orderSql = "order by p.";
			orderSql += TextUtil.join(orderColumns, ", p.");
		}
		
		sql = trim(sql);
		StringBuffer sb = new StringBuffer();

		if (offset >=0) {
			sb.append(" select * from (select p.*, rownumber() over( ");
			if(orderSql!=null && !"".equals(orderSql)) {
				sb.append(orderSql);
			}
			
			sb.append(" ) as row from (");
			sb.append(sql);
			sb.append(" ) as p) as q where q.row > ");
			sb.append(start).append(" and q.row <= ").append(end);
//			sb.append(SQL_END_DELIMITER);
		} else {
//			sb.append(sql).append("  fetch first ").append(limit).append(" rows only ");
			//Ignore
		}
		
		sb.append(" with ur ");
		
		return sb.toString();
	}
	
	public String getCountSql(String sql) {
		
		sql = trim(sql);
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select count(*) as ").append(TOTAL_COUNT).append(" from ( ");
		sb.append(sql);
		sb.append(" ) as p ");
		
		return sb.toString();
	}

	public boolean supportsLimit() {
		return true;
	}

	private String trim(String sql) {
		sql = sql.trim();
//		if (sql.endsWith(SQL_END_DELIMITER)) {
//			sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
//		}
		return sql;
	}

}
