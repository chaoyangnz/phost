/**
 * 
 */
package org.inframesh.phost.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inframesh.phost.dao.ibatis.ExtendedSqlExecutor;
import org.inframesh.phost.model.PageCond;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 * 
 *         2009-4-21
 */
public abstract class BaseDao extends SqlMapClientDaoSupport implements InitializingBean {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	@Qualifier("extendedSqlExecutor")
	private SqlExecutor extendedSqlExecutor;

	public void setExtendedSqlExecutor(SqlExecutor extendedSqlExecutor) {
		this.extendedSqlExecutor = extendedSqlExecutor;
	}

	public void initialize() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (extendedSqlExecutor == null) return;
		
		SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
		if (sqlMapClient instanceof SqlMapClientImpl) {
			SqlMapClient client = (SqlMapClient) sqlMapClient;
			SqlMapExecutorDelegate delegate = (SqlMapExecutorDelegate) PropertyUtils.getProperty(client, "delegate");
//			PropertyUtils.setProperty(delegate, "sqlExecutor", extendedSqlExecutor);
		}
	}

	/**
	 * 对于任意的SQL查询语句执行内存分页
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param pageCond 如果为null, 不分页
	 * @return
	 */
	protected List queryForMemoryPaginatedList(String statementName, Object parameterObject, PageCond pageCond) {
		List list = getSqlMapClientTemplate().queryForList(statementName, parameterObject);
		
		return paginateList(list, pageCond);
	}

	/**
	 * 对于任意的SQL查询语句执行数据库分页, 默认开启with ur<br>
	 * 请在select remapResults="true" ..加上那句remapResults="true" <br>
	 * SqlMap文件中只需要关注正常查询的那部分sql，分页查询的sql会自动生成
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param pageCond 如果为null，不分页
	 * @param orderColumns 排序的列. 非常重要！！强烈建议传入参数，不然分页出的数据可能混乱
	 * @return
	 */
	protected List queryForDatabasePaginatedList(String statementName, Object parameterObject, PageCond pageCond, String...orderColumns) {
		//如果为null，不分页
		if(pageCond == null) {
			return getSqlMapClientTemplate().queryForList(statementName, parameterObject);
		}
		
		int skipResults = pageCond.getStart();
		int maxResults = pageCond.getLimit();
		
		setOrderColumns(orderColumns);
		List list = getSqlMapClientTemplate().queryForList(statementName, parameterObject, skipResults, maxResults);

		int count = getCount();
		log.debug("查询总数：" + count);
			
		pageCond.setCount(count);
		
		return list;
	}
	
	/**
	 * 
	 * @param pagerFlag
	 */
	private int getCount() {
		int count = 0;
		if (extendedSqlExecutor instanceof ExtendedSqlExecutor) {
			count = ((ExtendedSqlExecutor) extendedSqlExecutor).getCount();
		}
		return count;
	}
	
	private void setOrderColumns(String[] orderColumns) {
		if (extendedSqlExecutor instanceof ExtendedSqlExecutor) {
			((ExtendedSqlExecutor) extendedSqlExecutor).setOrderColumns(orderColumns);
		}
	}
	
	private static List paginateList(List list, PageCond pageCond) {
		
		if (pageCond == null)  return list;

		int size = list.size();
		// 保存总数
		pageCond.setCount(size);

		// Pager
		int start = pageCond.getStart();
		int end = pageCond.getStart() + pageCond.getLimit();

		start = start < size ? start : 0;
		end = end < size ? end : size;
		
//		log.info("分页条件：[start:" + start + ", end:" + end + "]");
			
		List sublist = new ArrayList();
		for(int i= start; i < end; ++i) {
			sublist.add(list.get(i));
		}
		
		return sublist;
	}

}
