/**
 * 
 */
package org.inframesh.phost.dao;


/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-5-6
 */
public interface ISingleTableDao {
	String NAMESPACE = SingleTableDao.class.getName();
	
	String SELECT = ".select";
	String SELECT_BY_COLUMN = ".selectByColumn";
	
	String DELETE = ".delete";
	String DELETE_BY_COLUMN = ".deleteByColumn";
	
	String UPDATE = ".update";
	String UPDATE_BY_COLUMN = ".updateByColumn";
	
	String INSERT = ".insert";
	
	String COPY = ".copy";
	String MERGE = ".merge";
}