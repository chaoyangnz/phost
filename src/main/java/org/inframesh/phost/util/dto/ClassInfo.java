/**
 * 
 */
package org.inframesh.phost.util.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.inframesh.phost.util.annotation.Column;


/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-8-14
 */
public class ClassInfo {
	
	private static Map<Class, ClassInfo> DEFAULT = new HashMap<Class, ClassInfo>();

	private Map<String, String> nameMapping = new HashMap<String, String>();
	
	public Map<String, String> getNameMapping() {
		return nameMapping;
	}

	private ClassInfo(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			
			if(column == null) { //无@Column则不赋值
				continue;
			}
			
			String fieldName = field.getName();
			String columnName = column.name();
			if(column.name() == null || "".equals(column.name())) {
				columnName = TextUtil.camel2Underline(field.getName());
			}
			
			nameMapping.put(fieldName, columnName);
		}
	}
	
	public static ClassInfo getInstance(Class clazz) {
		synchronized (clazz) {
			if(! DEFAULT.containsKey(clazz)) {
				DEFAULT.put(clazz, new ClassInfo(clazz));
			}
		}
		
		return DEFAULT.get(clazz);
	}

}


