/**
 * 
 */
package org.inframesh.phost.util.annotation;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-4-22
 */

/**
 * For DB2 Only
 */
public enum JdbcType {
	UNDEFINED,
	
	SMALLINT,
	INTEGER,
	BIGINT,
	
	REAL,
	DOUBLE,
	FLOAT,
	
	DECIMAL,
	NUMERIC,
	DECFLOAT,
	
	CHARACTER,
	CHAR,
	VARCHAR,
	LONG_VARCHAR,
	CLOB,
	
	GRAPHIC,
	VARGRAPHIC,
	LONG_VARGRAPHIC,
	
	BLOB,
	
	DATE,
	TIME,
	TIMESTAMP,
	
	DATALINK,
	XML
	
}
