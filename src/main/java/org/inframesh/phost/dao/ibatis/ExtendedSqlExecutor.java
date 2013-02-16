/**
 * 
 */
package org.inframesh.phost.dao.ibatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.ErrorContext;
import com.ibatis.sqlmap.engine.scope.SessionScope;
import com.ibatis.sqlmap.engine.scope.StatementScope;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 * 
 *         2009-7-7
 */
public class ExtendedSqlExecutor extends SqlExecutor {

	private static final Log log = LogFactory.getLog(ExtendedSqlExecutor.class);

	// 缺省使用DB2
	private Dialect dialect;

	private ThreadLocal<String[]> orderColumns = new ThreadLocal<String[]>();
	
	private ThreadLocal<Integer> count = new ThreadLocal<Integer>();

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	public void setOrderColumns(String[] orderColumns) {
		this.orderColumns.set(orderColumns);
	}
	
	public int getCount() {
		return count.get();
	}
	
	public boolean supportsLimit() {
		if (dialect != null) {
			return dialect.supportsLimit();
		}
		return false;
	}

	@Override
	public void executeQuery(StatementScope statementScope, Connection conn, String sql, Object[] parameters, int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
		if ((skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS) && supportsLimit()) {

			String csql = dialect.getCountSql(sql);
			int totalCount = executeCount(statementScope, parameters, conn, csql, callback);
			
			count.set(totalCount);

			sql = dialect.getLimitSql(sql, orderColumns.get(), skipResults, maxResults);

			skipResults = NO_SKIPPED_RESULTS;
			maxResults = NO_MAXIMUM_RESULTS;
		}

		log.debug(sql);
		super.executeQuery(statementScope, conn, sql, parameters, skipResults, maxResults, callback);
	}

	private int executeCount(StatementScope statementScope, Object[] parameters, Connection conn, String sql, RowHandlerCallback callback) throws SQLException {
		int rowsCount = 0;
		ErrorContext errorContext = statementScope.getErrorContext();
		errorContext.setActivity("executing query procedure");
		errorContext.setObjectId(sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			errorContext.setMoreInfo("Check the SQL Statement (preparation failed).");
			Integer rsType = statementScope.getStatement().getResultSetType(); 
			if (rsType != null) {
		        ps = prepareStatement(statementScope.getSession(), conn, sql, rsType);
		      } else {
		        ps = prepareStatement(statementScope.getSession(), conn, sql, ResultSet.TYPE_FORWARD_ONLY);
		      }

			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++)
					ps.setObject(i + 1, parameters[i]);
			}

			errorContext.setMoreInfo("Check the parameters (set parameters failed).");
			ps.execute();
			errorContext.setMoreInfo("Check the results (failed to retrieve results).");
			rs = ps.getResultSet();
			// Begin ResultSet Handling
			if (rs != null) {
				while (rs.next()) {
					rowsCount = rs.getInt(DB2Dialect.TOTAL_COUNT);
				}
			}

		} finally {
			try {
				closeResultSet(rs);
			} finally {
				closeStatement(statementScope.getSession(), ps);
			}
		}
		return rowsCount;
	}

	private static void closeStatement(SessionScope sessionScope, PreparedStatement ps) {
		if (ps != null) {
			if (!sessionScope.hasPreparedStatement(ps)) {
				try {
					ps.close();
				} catch (SQLException e) {
					// ignore
				}
			}
		}
	}

	/**
	 * @param rs
	 */
	private static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}

	private PreparedStatement prepareStatement(SessionScope sessionScope, Connection conn, String sql, Integer rsType) throws SQLException {
		SqlMapExecutorDelegate delegate = ((SqlMapClientImpl) sessionScope.getSqlMapExecutor()).getDelegate();
		if (sessionScope.hasPreparedStatementFor(sql)) {
			return sessionScope.getPreparedStatement((sql));
		} else {
			PreparedStatement ps = conn.prepareStatement(sql, rsType.intValue(), ResultSet.CONCUR_READ_ONLY);
			sessionScope.putPreparedStatement(delegate, sql, ps);
			return ps;
		}
	}
}
