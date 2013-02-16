/**
 * 
 */
package org.inframesh.phost.util.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.inframesh.phost.model.DTO;
import org.inframesh.phost.util.dto.TextUtil;
import org.springframework.util.Assert;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-4-22
 */
public class AnnotationUtils {
	
	public static <T extends DTO> String[] parseTableAnnotationForTableNames(Class<T> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		
		String tableName = table.name();
		Assert.hasLength(tableName, clazz.getName() + "的@Table的name属性不能为空字符串");
		
		List<String> list = new ArrayList<String>();
		String[] segments = tableName.split(","); 
		for(String segment : segments) {
			list.add(segment.trim());
		}
		
		Assert.notEmpty(list, "Annotation[Table] not present at " + clazz.getName());
		
		return list.toArray(new String[0]);
	}
	
	/**
	 * 解析主键名，对于视图，没有主键名
	 * @param <T>
	 * @param clazz
	 * @return 如果是视图，返回空；否则不允许为空
	 */
	public static <T extends DTO> String parseColumnAnnotationForPkName(Class<T> clazz) {

		Table table = (Table) clazz.getAnnotation(Table.class);
		
		Assert.notNull(table, "Annotation[Table] not present at " + clazz.getName());
		
		Field[] fields = clazz.getDeclaredFields();
		String pkName = null;
		for(Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			
			if(column != null && column.pk()) {
				pkName = column.name();
				if("".equals(pkName)) {
					pkName = TextUtil.camel2Underline(field.getName());
				}
				break;
			}
		}
		
		//对View可以没有PK
//		Assert.notNull(pkName, "All Annotation[Column] pk attribute no found at " + clazz.getName());
		
		return pkName;
	}
	
	
//	/**
//	 * 分析PK列和普通列<br>
//	 * DYNAMIC_SQL 不包括主键PK的拼装SQL
//	 * 
//	 * <br><br>
//	 * 使用PropertyUtils.getProperty()方法有如下bug: 
//	 * <br>对于这样的命名，如G_RIGHT_VALUE的get方法getGRightValue()会有问题
//	 * 
//	 * @param <T>
//	 * @param dto
//	 * @param operation
//	 * @return PK_NAME, PK_VALUE, DYNAMIC_SQL 
//	 * 
//	 */
//	@SuppressWarnings("unchecked")
//	public static <T extends DTO> MapDTO parseColumnAnnotationForDto(T dto, int operation) {
//		Class<T> clazz = (Class<T>) dto.getClass();
//		MapDTO map = new MapDTO();
//		MapDTO columnMap = DtoUtils.disperseDTO(dto);
//
//		Table table = (Table) clazz.getAnnotation(Table.class);
//		
//		Assert.notNull(table, "Annotation[Table] not present at " + clazz.getName());
//		
//		
//		
//		map.put("DYNAMIC_SQL", SqlUtils.buildSql(columnMap, operation));
//		
//		Assert.notEmpty(map, "PK Annotation no found for " + clazz.getName());
//		
//		return map;
//	}
//	
//	/**
//	 * 分析PK列和普通列，并可以指定update或insert的列
//	 * 
//	 * <br><br>
//	 * 使用PropertyUtils.getProperty()方法有如下bug: 
//	 * <br>对于这样的命名，如G_RIGHT_VALUE的get方法getGRightValue()会有问题
//	 * 
//	 * @param <T>
//	 * @param dto
//	 * @param columnNameSet
//	 * @param operation
//	 * @return
//	 */
//	@SuppressWarnings({ "unchecked" })
//	public
//	static <T extends DTO> MapDTO parseColumnAnnotationForDtoWithColumns(T dto, HashSet<String> columnNameSet, int operation) {
//		Class<T> clazz = (Class<T>) dto.getClass();
//		MapDTO map = new MapDTO();
//		MapDTO columnMap = new MapDTO();
//
//		Table table = (Table) clazz.getAnnotation(Table.class);
//		
//		Assert.notNull(table, "Annotation[Table] not present at " + clazz.getName());
//		
//		Field[] fields = clazz.getDeclaredFields();
//		for(Field field : fields) {
////			field.setAccessible(true);
//			Column column = field.getAnnotation(Column.class);
//			try {
//				if(column != null && column.pk()) {//PK属性
//					String pkName = ValidationUtils.notEmpty(column.name()) 
//								  ? column.name() : NamingUtils.camel2Database(field.getName());
//					Object pkValue = PropertyUtils.getProperty(dto, field.getName());//field.get(dto);
//					if("".equals(pkName)) {
//						pkName = NamingUtils.camel2Database(field.getName());
//					}
//					map.put("PK_NAME", pkName, "PK_VALUE", pkValue);
//				} else {//非PK属性
//					if(column == null) break; //没有@Column,则忽略不update或insert
//					String columnName = ValidationUtils.notEmpty(column.name()) 
//									  ? column.name() : NamingUtils.camel2Database(field.getName()); //无Name属性则默认用命名规则
//					
//					if(columnNameSet.contains(columnName)) { //指定update列表中指定的
//						Object columnValue = PropertyUtils.getProperty(dto, field.getName());//field.get(dto);
//						columnMap.put(columnName, columnValue);
//					}
//				}
//			} catch(IllegalAccessException ex) {
//				throw new ProgramException(ex, "非法访问{0}的私有字段{1}", clazz.getSimpleName(), field.getName());
//			} catch(NoSuchMethodException ex) {
//				throw new ProgramException(ex, "{0}的{1}属性可能不存在get或set访问方法", clazz.getSimpleName(), field.getName());
//			} catch(InvocationTargetException ex) {
//				throw new ProgramException(ex, "调用{0}的{1}属性的get或set访问方法出错", clazz.getSimpleName(), field.getName());
//			}
//		}
//		
//		map.put("DYNAMIC_SQL", SqlUtils.buildSql(columnMap, operation));
//		
//		Assert.notEmpty(map, "PK Annotation no found for " + clazz.getName());
//		
//		return map;
//	}
//	
//	/**
//	 * 分析PK列和普通列，并可以指定排除update或insert的列
//	 * 
//	 * <br><br>
//	 * 使用PropertyUtils.getProperty()方法有如下bug: 
//	 * <br>对于这样的命名，如G_RIGHT_VALUE的get方法getGRightValue()会有问题
//	 * 
//	 * @param <T>
//	 * @param dto
//	 * @param columnNameSet
//	 * @param operation
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public
//	static <T extends DTO> MapDTO parseColumnAnnotationForDtoWithExcludedColumns(T dto, HashSet<String> columnNameSet, int operation) {
//		Class<T> clazz = (Class<T>) dto.getClass();
//		MapDTO map = new MapDTO();
//		MapDTO columnMap = new MapDTO();
//
//		Table table = (Table) clazz.getAnnotation(Table.class);
//		
//		Assert.notNull(table, "Annotation[Table] not present at " + clazz.getName());
//		
//		Field[] fields = clazz.getDeclaredFields();
//		for(Field field : fields) {
////			field.setAccessible(true);
//			Column column = field.getAnnotation(Column.class);
//			try {
//				if(column != null && column.pk()) {//PK属性
//					String pkName = ValidationUtils.notEmpty(column.name()) 
//								  ? column.name() : NamingUtils.camel2Database(field.getName());
//					Object pkValue = PropertyUtils.getProperty(dto, field.getName());//field.get(dto);
//					if(ValidationUtils.isEmpty(pkName)) {
//						pkName = NamingUtils.camel2Database(field.getName());
//					}
//					map.put("PK_NAME", pkName, "PK_VALUE", pkValue);
//				} else {//非PK属性
//					if(column == null) break; //没有@Column,则忽略
//					String columnName = ValidationUtils.notEmpty(column.name()) 
//									  ? column.name() : NamingUtils.camel2Database(field.getName()); //无Name属性则默认用命名规则
//					
//					if(!columnNameSet.contains(columnName)) { //指定update列表中没有指定的
//						Object columnValue = PropertyUtils.getProperty(dto, field.getName());//field.get(dto);
//						columnMap.put(columnName, columnValue);
//					}
//				}
//			} catch(IllegalAccessException ex) {
//				throw new ProgramException(ex, "非法访问{0}的私有字段{1}", clazz.getSimpleName(), field.getName());
//			} catch(NoSuchMethodException ex) {
//				throw new ProgramException(ex, "{0}的{1}属性可能不存在get或set访问方法", clazz.getSimpleName(), field.getName());
//			} catch(InvocationTargetException ex) {
//				throw new ProgramException(ex, "调用{0}的{1}属性的get或set访问方法出错", clazz.getSimpleName(), field.getName());
//			}
//		}
//		
//		map.put("DYNAMIC_SQL", SqlUtils.buildSql(columnMap, operation));
//		
//		Assert.notEmpty(map, "PK Annotation no found for " + clazz.getName());
//		
//		return map;
//	}
	
	/**
	 * 从DTO class得到表名和主键名 <br><br>
	 * 
	 * 前置约束 - 参数clazz 不能为null <br>
	 * 后置约束 - 返回MapBean不为null <br>
	 * 
	 * @param clazz 带注解的DTO的Class
	 * 
	 */
//	public static <T extends DTO> MapDTO parseAnnotation(Class<T> clazz) {
//		MapDTO bean = new MapDTO();
//		bean.put("TABLE_NAME", parseTableAnnotationForTableName(clazz));
//		bean.put("PK_NAME", parseColumnAnnotationForPkName(clazz));
//		
//		return bean;
//	}
	
	/**
	 * 从DTO 实例得到表名、主键名/主键值、列名/列值<br><br>
	 * 
	 * 前置约束 - 参数dto不能为null, operation必须<br>
	 * 后置约束 - 返回MapBean不为null
	 * 
	 * @param dto DTO对象
	 * @param operation 操作标识, 取值为 {@link SqlUtils#INSERT} 和 {@link SqlUtils#UPDATE}
	 * @return 返回hashMap, 其中key: "TABLE_NAME", "PK_NAME", "DYNAMIC_SQL"
	 */
//	@SuppressWarnings("unchecked")
//	public static <T extends DTO> MapDTO parseAnnotatioForDto(T dto, int operation) {
//		Assert.notNull(dto);
//		Assert.isTrue(operation==SqlUtils.INSERT || operation==SqlUtils.UPDATE);
//		
//		MapDTO bean = new MapDTO();
//		Class<T> clazz = (Class<T>) dto.getClass();
//		bean.put("TABLE_NAME", parseTableAnnotationForTableName(clazz));
//		bean.putAll(parseColumnAnnotationForDto(dto, operation));
//		
//		return bean;
//	}
	
	/**
	 * 从DTO 实例得到表名、主键名/主键值、列名/列值, 可以指定update或insert的列<br><br>
	 * 
	 * 前置约束 - 参数dto不能为null, operation必须<br>
	 * 后置约束 - 返回MapBean不为null
	 * 
	 * @param dto DTO对象
	 * @param operation 操作标识, 取值为 {@link SqlUtils#INSERT}  {@link SqlUtils#UPDATE}
	 */
//	@SuppressWarnings("unchecked")
//	public static <T extends DTO> MapDTO parseAnnotatioForDtoWithColumns(T dto, HashSet<String> columnNameSet, int operation) {
//		Assert.notNull(dto);
//		Assert.isTrue(operation==SqlUtils.INSERT || operation==SqlUtils.UPDATE);
//		
//		MapDTO bean = new MapDTO();
//		Class<T> clazz = (Class<T>) dto.getClass();
//		bean.put("TABLE_NAME", parseTableAnnotationForTableName(clazz));
//		bean.putAll(parseColumnAnnotationForDtoWithColumns(dto, columnNameSet, operation));
//		
//		return bean;
//	}
	
	/**
	 * 从DTO 实例得到表名、主键名/主键值、列名/列值, 可以指定排除update或insert的列<br><br>
	 * 
	 * 前置约束 - 参数dto不能为null, operation必须<br>
	 * 后置约束 - 返回MapBean不为null
	 * 
	 * @param dto DTO对象
	 * @param operation 操作标识, 取值为 {@link SqlUtils#INSERT}  {@link SqlUtils#UPDATE}
	 */
//	@SuppressWarnings("unchecked")
//	public static <T extends DTO> MapDTO parseAnnotatioForDtoWithExcludedColumns(T dto, HashSet<String> columnNameSet, int operation) {
//		Assert.notNull(dto);
//		Assert.isTrue(operation==SqlUtils.INSERT || operation==SqlUtils.UPDATE);
//		
//		MapDTO bean = new MapDTO();
//		Class<T> clazz = (Class<T>) dto.getClass();
//		bean.put("TABLE_NAME", parseTableAnnotationForTableName(clazz));
//		bean.putAll(parseColumnAnnotationForDtoWithExcludedColumns(dto, columnNameSet, operation));
//		
//		return bean;
//	}
}
