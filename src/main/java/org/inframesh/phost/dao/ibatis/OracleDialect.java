package org.inframesh.phost.dao.ibatis;

public class OracleDialect implements Dialect {

	public String getCountSql(String sql) {
		return null;
	}

	public String getLimitString(String sql, boolean hasOffset) {
		return null;
	}

	public String getLimitSql(String sql, String[] orderColumns, int offset, int limit) {
		return null;
	}

	public boolean supportsLimit() {
		return false;
	}

}
