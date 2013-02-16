/**
 * 
 */
package org.inframesh.phost.util.dto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.inframesh.phost.model.MapDTO;
import org.inframesh.phost.util.annotation.Column;
import org.springframework.util.Assert;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-4-22
 */

/**
 * 针对带@Column的DTO的组装/拆装工具类
 */
public class DtoUtil {

	/**
	 * 注册BeanUtils的BigDecimal转换默认值
	 */
	private static final BigDecimal BIGDECIMAL_ZERO = new BigDecimal("0");     
	static {     
	    // 这里一定要注册默认值，使用null也可以     
	    BigDecimalConverter bd = new BigDecimalConverter(BIGDECIMAL_ZERO);
	    DateConverter dc = new DateConverter();
	    
	    ConvertUtils.register(dc, java.util.Date.class); 
	    ConvertUtils.register(bd, java.math.BigDecimal.class);     
	}   
	
	/**
	 * 组装到List
	 * 
	 * @param <T>
	 * @param list
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> assembleDtoForList(List<MapDTO> list, Class<T> clazz) {
		Assert.notNull(list);
		
		List<T> result = new ArrayList<T>();
		for(MapDTO dto : list) {
			result.add(assembleDto(dto, clazz));
		}
		
		return result;
	}
	
	/**
	 * 将HashMap装配成需要的DTO, 按照DTO中的@Column映射关系进行填充JavaBean<br>
	 * 当在HashMap中找不到对应字段时，DTO相应属性为null<br>
	 * 无@Column时，相应属性不赋值<br>
	 * 
	 * 其中，BeanUtils会自动进行类型转换，如Timestamp->String
	 * 
	 * @param mapdto 以数据库列名为Key的hashMap
	 * @return dto
	 * @throws ProgramException 
	 */
	public static <T> T assembleDto(MapDTO mapdto, Class<T> clazz) {
		Assert.notNull(mapdto);
		
		T dto = null;

		try {
			dto = clazz.newInstance();
		} catch (InstantiationException ex) {
			throw new RuntimeException("不能实例化" + clazz.getName(), ex);
		} catch (IllegalAccessException ex) {
			throw new RuntimeException("不能实例化"+clazz.getName()+",可能默认构造函数私有", ex);
		}
		
		ClassInfo info = ClassInfo.getInstance(clazz);
		Map<String, String> nameMapping = info.getNameMapping();
		
		for(Entry<String, String> entry : nameMapping.entrySet()) {
			String fieldName = entry.getKey();
			String columnName = entry.getValue(); 
			
			try {
				BeanUtils.setProperty(dto, fieldName, mapdto.get(columnName));
			} catch(IllegalAccessException ex) {
				throw new RuntimeException("非法访问" + clazz.getSimpleName() + "的属性"+fieldName, ex);
			} catch(InvocationTargetException ex) {
				throw new RuntimeException("调用"+clazz.getSimpleName()+"的属性"+fieldName+"的set方法出错", ex);
			} 
		}
		
//		Field[] fields = clazz.getDeclaredFields();
//		for(Field field : fields) {
//			Column column = field.getAnnotation(Column.class);
//			
//			if(column == null) { //无@Column则不赋值
//				continue;
//			}
//			
//			try {
//				if(Check.hasLength(column.name())) {
//					BeanUtils.setProperty(dto, field.getName(), mapdto.get(column.name()));
//				} else {
//					String defaultName = NamingUtils.camel2Database(field.getName());
//					BeanUtils.setProperty(dto, field.getName(), mapdto.get(defaultName));
//				}
//			} catch(IllegalAccessException ex) {
//				throw new ProgramException(ex, "非法访问{0}的属性{1}", clazz.getSimpleName(), field.getName());
//			} catch(InvocationTargetException ex) {
//				throw new ProgramException(ex, "调用{0}的属性{1}的set方法出错", clazz.getSimpleName(), field.getName());
//			} 
//			catch(NoSuchMethodException ex) {
//				throw new ProgramException(ex, "{0}的属性{1}不存在set方法", clazz.getSimpleName(), field.getName());
//			}
//		}
		return dto;		
	}
	
	/**
	 * 拆散DTO成一个以@Column(name)为Key的HashMap<br>
	 * 不进行任何类型转换和格式校验
	 * 
	 * <br><br>
	 * 使用PropertyUtils.getProperty()方法有如下bug: 
	 * <br>对于这样的命名，如G_RIGHT_VALUE的get方法getGRightValue()会有问题
	 * 
	 * @param dto
	 * @return 以数据库列名为Key的hashMap,不排除任何有@Column的属性
	 */
	public static <T> MapDTO disperseDTO(T dto) {
		Assert.notNull(dto);
		
		MapDTO map = new MapDTO();
		
		Class clazz = dto.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			Column column = field.getAnnotation(Column.class);
			
			if(column == null) { //无@Column则不赋值
				continue;
			}
			
			try {
				String columnName = column.name()!=null&& !"".equals(column.name()) 
				  				  ? column.name() : TextUtil.camel2Underline(field.getName());
				map.put(columnName, field.get(dto));//PropertyUtils.getProperty(dto, defaultName));//??

			} catch(IllegalAccessException ex) {
				throw new RuntimeException("非法访问"+clazz.getSimpleName()+"的属性"+field.getName(), ex);
			} 
//			catch(InvocationTargetException ex) {
//				throw new ProgramException(ex, "调用{0}的属性{1}的set方法出错", clazz.getSimpleName(), field.getName());
//			} catch(NoSuchMethodException ex) {
//				throw new ProgramException(ex, "{0}的属性{1}不存在get方法", clazz.getSimpleName(), field.getName());
//			}
		}
		
		return map;

	}
	
	public static MapDTO processDateFormat(MapDTO mapdto) {
		
		MapDTO result = new MapDTO();
		
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		
		for(Entry<String, Object> entry : mapdto.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if(value != null) {
				Class clazz = value.getClass();
				if(clazz == Date.class) {
					value = dateFormater.format(value);
				} else if(clazz == Timestamp.class) {
					value = timeFormater.format(value);
				}
			}
			
			result.put(key, value);
		}
		return result;
	}
	
	/**
	 * 处理命名和日期时间格式，主要供前台显示方便
	 * 
	 * @param mapdto
	 * @return
	 */
	public static MapDTO processNamingAndDateFormat(MapDTO mapdto) {
		MapDTO result = new MapDTO();
		
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		
		for(Entry<String, Object> entry : mapdto.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if(value != null) {
				Class clazz = value.getClass();
				if(clazz == Date.class || clazz == java.util.Date.class) {
					value = dateFormater.format(value);
				} else if(clazz == Timestamp.class) {
					value = timeFormater.format(value);
				}
			}
			
			result.put(TextUtil.underline2Camel(key), value);
		}
		
		return result;
	}
	
/*	public static void main(String[] args) {
		AerocraftColDTO dto = new AerocraftColDTO();
		dto.setAerocraftId(1L);
		dto.setBegUseDate("2009-10-18");
		dto.setColBasId(19L);
		
		MapDTO map = DtoUtils.disperseDTO(dto);
		for(Entry entry : map.entrySet()){
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			if(value != null) {
				System.out.println(key + ": " + value.getClass().getName());
			}
		}
	}*/

}
