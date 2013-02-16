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

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
	/**
	 * 数据库表名或视图名
	 * @return
	 */
	String name();
	
	/**
	 * 表显示名
	 * @return
	 */
	String vname() default "";
	
	/**
	 * 模式名
	 * @return
	 */
	String schema() default "DB2ADMIN";
}
