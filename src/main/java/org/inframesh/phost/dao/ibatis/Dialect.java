/**
 * 
 */
package org.inframesh.phost.dao.ibatis;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-7-7
 */
public interface Dialect {
	public boolean supportsLimit();   

    public String getLimitSql(String sql, String[] orderColumns, int offset, int limit);

	public String getCountSql(String sql); 
}
