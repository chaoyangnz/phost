/**
 * 
 */
package org.inframesh.phost.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-3-27
 */
public class MapDTO extends HashMap<String, Object> implements DTO {
	
	protected static final long serialVersionUID = 1L;
	
	public MapDTO() {
		super();
	}
 
	public MapDTO(Object... args) {
		put(args);
	}
 
	//int转换，默认整数为Integer
	public int getInt(Object key) {
		return getInt(key, 0);
	}
 
	public int getInt(Object key, int defaultInt) {
		Integer i = (Integer) get(key);
		return i == null ? defaultInt : i;
	}
	
	//long转换，默认整数为Integer,需要再转为Long
	public long getLong(Object key) {
		return getLong(key, 0L);
	}
 
	public long getLong(Object key, long defaultLong) {
		Object value = get(key);
		if(value.getClass() == Long.class) {
			return (Long) value;
		}
		Long i = ((Integer)value).longValue();
		return i == null ? defaultLong : i;
	}
	
	//double转换，默认小数为Double
	public double getDouble(Object key) {
		return getDouble(key, 0.0);
	}
	
	public double getDouble(Object key, double defaultDouble) {
		Double i = (Double) get(key);
		return i == null ? defaultDouble : i;
	}
	
	//float转换，默认小数为Double,需要再转为Float
	public double getFloat(Object key) {
		return getFloat(key, 0.0f);
	}
	
	public float getFloat(Object key, float defaultFloat) {
		Object value = get(key);
		if(value.getClass() == Float.class) {
			return (Float) value;
		}
		Float i = ((Double) get(key)).floatValue();
		return i == null ? defaultFloat : i;
	}
 
	//字符串
	public String getString(Object key) {
		return (String) get(key);
	}
 
	public String getString(Object key, String defaultValue) {
		String value = (String) get(key);
		return value == null ? defaultValue : value;
	}
 
	//Timestamp
	public Timestamp getTimestamp(Object key) {
		return (Timestamp) get(key);
	}
	
	public Date getDate(Object key) {
		return (Date) get(key);
	}
 
	public void put(Object... args) {
		for (int i = 1; i < args.length; i += 2) {
			put(String.valueOf(args[i - 1]), args[i]);
		}
	}
	
	public MapDTO exclude(String key) {
		remove(key);
		return this;
	}
	
	public MapDTO forCamelNaming() {
		MapDTO bean = new MapDTO();
		for(Entry entry : this.entrySet()){
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			
			StringBuffer bf = new StringBuffer();
			String[] segments = key.split("_");
			for(int i=0; i < segments.length; ++i) {
				String segment = segments[i].toLowerCase();
				if(i==0) {
					bf.append(segment);
				} else {
					bf.append(segment.substring(0, 1).toUpperCase());
					bf.append(segment.substring(1));
				}
			}
			bean.put(bf.toString(), value);
		}
		return bean;
	}
	
	public MapDTO forDatabaseNaming() {
		MapDTO bean = new MapDTO();
		for(Entry entry : this.entrySet()){
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			
			StringBuffer bf = new StringBuffer();
			
			char[] seq = key.toCharArray();
			int from = 0;
			for(int i=0; i<seq.length; ++i) {
				if(seq[i]>='A' && seq[i]<='Z') {
					int to = i;
					bf.append(key.substring(from, to).toUpperCase());
					bf.append('_');
					from = to;
				}
			}
			bf.append(key.substring(from, seq.length).toUpperCase());
			bean.put(bf.toString(), value);
		}
		return bean;
	}
}
