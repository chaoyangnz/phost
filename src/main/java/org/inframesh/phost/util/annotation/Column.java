/**
 * 
 */
package org.inframesh.phost.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-4-22
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
	/**
	 * 数据库列名
	 * @return
	 */
	String name() default "";
	
	/**
	 * 显示名，一般为中文
	 * @return
	 */
	String vname() default "";
	
	/**
	 * JDBC Type (DB2 Support Only)
	 * @return
	 */
	JdbcType type() default JdbcType.UNDEFINED;
	
	/**
	 * 是否可以为空
	 * @return
	 */
	boolean nullable() default true;
	
	/**
	 * 是否为主键
	 * @return
	 */
	boolean pk() default false;
}
