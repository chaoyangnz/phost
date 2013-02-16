/**
 * 
 */
package org.inframesh.phost.util.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.inframesh.phost.model.MapDTO;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-4-22
 */
public class SqlUtil {
	
	/**
	 * 返回"COL_NAME=value, COLUM_NAME=value2"
	 * 
	 * @param map
	 * @return
	 */
	public static String buildSetSql(Map<String, Object> map) {
		
		if (map == null || map.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();

		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry entry = it.next();
			sb.append(entry.getKey()).append("=");
			Object value = entry.getValue();
			if(value instanceof String) {
				sb.append("'");
			}
			sb.append(value);
			if(value instanceof String) {
				sb.append("'");
			}
			
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 返回(COL1, COL2, COL3) VALUES (1, 'xfdo', '2009-06-29')
	 * 
	 * @param map
	 * @return
	 */
	public static String buildValuesSql(Map<String, Object> map) {

		String str1 = TextUtil.join(map.keySet(), ", ");
		String str2 = null;
		
		if (map.values() == null || map.values().isEmpty()) {
			str2 = "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator it = map.values().iterator();
		while (it.hasNext()) {
			Object item = it.next();
			if(item instanceof String) {
				sb.append("'");
			}
			sb.append(item);
			if(item instanceof String) {
				sb.append("'");
			}
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		str2 = sb.toString();
		
		return " (" + str1 + ") VALUES (" + str2 + ")";
	}
	
	/**
	 * 返回COL1=1 AND COL2='sfdfd' AND COL3='2008-09-20'
	 * 
	 * @param map
	 * @return
	 */
	public static String buildWhereSql(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();

		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry entry = it.next();
			sb.append(entry.getKey());
			
			Object value = entry.getValue();
			if(value == null) {
				sb.append(" IS ");//FIXED
			} else {
				sb.append("=");
			}

			if(value instanceof String) {
				sb.append("'");
			}
			sb.append(value);
			if(value instanceof String) {
				sb.append("'");
			}
			
			if (it.hasNext()) {
				sb.append(" AND ");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 处理自动映射的Result类型，主要用于格式化timestamp类型为字符串
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static Map<String, Object> processResultTypeAndFormat(Map<String, Object> map) {
		Map<String, Object> mapBean = new HashMap<String, Object>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //hh:mm:ss");   
		
		for(Entry entry : map.entrySet()){
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			
			if(value instanceof Timestamp || value instanceof Date) {
				
				value = formatter.format(value);
			}
			mapBean.put(key, value);
		}
		return mapBean;
	}
	
	@SuppressWarnings("unused")
	@Deprecated
	private Map processParameterTypeAndFormat(Map<String, Object> map) {
		return null;
	}
	
	public static void main(String[] args) {
		MapDTO mapdto = new MapDTO("AA_DD", 1, "BB_OO", "SS", "CC_PP", null, "PSO_PSO", "", "ODP", new Date(595909595339L));
		System.out.println(SqlUtil.buildWhereSql(mapdto));
		System.out.println(SqlUtil.buildValuesSql(mapdto));
		System.out.println(SqlUtil.buildSetSql(mapdto));
	}
}
