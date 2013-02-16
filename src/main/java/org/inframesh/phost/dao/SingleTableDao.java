/**
 * 
 */
package org.inframesh.phost.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.inframesh.phost.model.DTO;
import org.inframesh.phost.model.MapDTO;
import org.inframesh.phost.model.PageCond;
import org.inframesh.phost.util.annotation.AnnotationUtils;
import org.inframesh.phost.util.dto.DtoUtil;
import org.inframesh.phost.util.dto.SqlUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-4-21
 */

/**
 * 本抽象类实现单表的相关操作，一般来说继承此类，意味着DTO需要Annotaion<br><br>
 * 
 * 双表操作详见 :
 * @see {@link DualTableDao}
 * 
 */
@SuppressWarnings("unchecked")
@Component
public abstract class SingleTableDao<T extends DTO, PK extends Serializable> extends BaseDao implements ISingleTableDao {

	protected Class<T> CLAZZ;
	protected String[] TABLE_NAMES;
	protected String PK_NAME;
	
	public SingleTableDao() {
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.CLAZZ = clazz;
		this.TABLE_NAMES = AnnotationUtils.parseTableAnnotationForTableNames(clazz);
		this.PK_NAME = AnnotationUtils.parseColumnAnnotationForPkName(clazz);
		
//		Assert.hasLength(this.PK_NAME, "{0}中没有指定PK", clazz.getSimpleName());
		Assert.notEmpty(TABLE_NAMES, CLAZZ.getName() + "没有指定表名");
	}
	
	/**
	 * 根据主键查询一行记录，映射到DTO<br><br>
	 * 
	 * SQL举例: SELECT * FROM TABLE WHERE PK=45
	 * 
	 * @param pk
	 * @return T 查询无记录则返回null
	 * @throws DataAccessException 
	 */
	public T queryByPk(PK pk) {
		Assert.notNull(pk, "PK Must be not null");
		String tableName = this.TABLE_NAMES[0];
			
		//分析注解，准备参数
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		params.put("PK_NAME", this.PK_NAME);		
		params.put("PK_VALUE", pk);

		//执行查询
		MapDTO bean = (MapDTO) getSqlMapClientTemplate().queryForObject(NAMESPACE + SELECT, params);
		
		if(bean == null) {
			return null;
		}
//		MapDTO result = SqlUtils.processResultTypeAndFormat(bean);//.forCamelNaming();
		
		if(CLAZZ==MapDTO.class) {
			return (T) bean;
		}
		
		return DtoUtil.assembleDto(bean, CLAZZ);
	}
	
	/**
	 * 根据某列查询，用于除了PK以外的查询情况. <br>
	 * 注意，要保证查询出的结果不多于1条<br>
	 * 本查询不用于查询List, 特别适用于特殊押品情况<br>
	 * 带权限查询List操作，需要自己写SQL实现<br><br>
	 * 
	 * SQL举例: SELECT * FROM TABLE WHERE COL3=89 AND COL_4='SS' FETCH 1 ROW ONLY
	 * 
	 * @param columnValueMap 为hashMap，key为数据库列名，value为查询条件值
	 * @return T 查询无记录，返回为null
	 * @throws DataAccessException 
	 */	
	 public T queryByColumns(MapDTO columnValueMap) {
		Assert.notNull(columnValueMap, "params must be not empty");
		String tableName = this.TABLE_NAMES[0];
		
		//分析注解，准备参数
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		params.put("WHERE_SQL", SqlUtil.buildWhereSql(columnValueMap));
		
		//执行查询
		MapDTO bean = (MapDTO) getSqlMapClientTemplate().queryForObject(NAMESPACE + SELECT_BY_COLUMN, params);
		
		if(bean == null) {
			return null;
		}
		//处理查询结果类型和格式(主要是Date和Timestamp)
//		MapDTO result = SqlUtils.processResultTypeAndFormat(bean);
		
		if(CLAZZ==MapDTO.class) {
			return (T) bean;
		}
		//映射并组装到DTO
		return DtoUtil.assembleDto(bean, CLAZZ);
	}

	/**
	 * 根据某列查询，用于除了PK以外的“简单”查询情况. <br>
	 * 本查询的结果为List, 没有查询到记录返回为空List, 并非null<br>
	 * 注意： 对于带权限List查询操作，需要自己写SQL实现<br><br>
	 * 
	 * @param columnValueMap 为hashMap，key为数据库列名，value为查询条件值
	 * @return List 查询无记录，返回为空List
	 * @throws DataAccessException 
	 */
	public List<T> queryListByColumns(MapDTO columnValueMap) {
		Assert.notNull(columnValueMap, "params must be not empty");
		String tableName = this.TABLE_NAMES[0];
		
		//分析注解，准备参数
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		params.put("WHERE_SQL", SqlUtil.buildWhereSql(columnValueMap));
		
		//执行查询
		List<MapDTO> list = getSqlMapClientTemplate().queryForList(NAMESPACE + SELECT_BY_COLUMN, params);
		
		if(list == null) {
			return new ArrayList();
		}

		List<T> result = DtoUtil.assembleDtoForList(list, CLAZZ);
		
		//映射并组装到DTO的List
		return result;
	}
	
	/**
	 * 内存分页
	 * 
	 * @param columnValueMap
	 * @param pageCond
	 * @return
	 */
	public List<T> queryPaginatedListByColumns(MapDTO columnValueMap, PageCond pageCond) {
		Assert.notNull(columnValueMap, "params must be not empty");
		String tableName = this.TABLE_NAMES[0];
		
		//分析注解，准备参数
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		params.put("WHERE_SQL", SqlUtil.buildWhereSql(columnValueMap));
		
		List<MapDTO> list = super.queryForMemoryPaginatedList(NAMESPACE + SELECT_BY_COLUMN, params, pageCond);
		
		//映射并组装到DTO的List
		return DtoUtil.assembleDtoForList(list, CLAZZ);
	}
	
	/**
	 * 数据库分页
	 * 
	 * @param columnValueMap
	 * @param pageCond
	 * @return
	 */
	public List<T> queryPaginatedListByColumnsEx(MapDTO columnValueMap, PageCond pageCond, String...orderColumns) {
		Assert.notNull(columnValueMap, "params must be not null");
		if(pageCond != null) {
			Assert.isTrue(pageCond.getStart()>=0, "开始页必须不小于0");
			Assert.isTrue(pageCond.getLimit()>0, "每页条数必须大于0");
		}
		
		
		String tableName = this.TABLE_NAMES[0];
		
		//分析注解，准备参数
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
//		params.put("PK_NAME", this.PK_NAME);
		params.put("WHERE_SQL", SqlUtil.buildWhereSql(columnValueMap));
		
		if(orderColumns.length == 0) {
			orderColumns = new String[] {PK_NAME};
		}
		
		List<MapDTO> list = super.queryForDatabasePaginatedList(NAMESPACE + SELECT_BY_COLUMN, params, pageCond, orderColumns);		
		
		//映射并组装到DTO的List
		return DtoUtil.assembleDtoForList(list, CLAZZ);
	}
	
	
	/**
	 * 根据主键删除<br><br>
	 * 
	 * SQL举例: DELETE FROM TABLE WHERE PK=45
	 * 
	 * @param pk
	 * @throws DataAccessException 
	 * @return 返回delete影响的行数
	 */
	public int deleteByPk(PK pk) {
		Assert.notNull(pk, "PK Must not be null");
		String tableName = this.TABLE_NAMES[0];
			
		//准备参数
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		params.put("PK_NAME", this.PK_NAME);
		params.put("PK_VALUE", pk);
		
		//执行删除
		int rows = getSqlMapClientTemplate().delete(NAMESPACE + DELETE, params);
		
		if(rows == 0) {
			log.info("delete successfully, but 0 row effected. [" + CLAZZ.getName() + "] pk=" + pk);
		}
		
		return rows;
	}
	
	/**
	 * 根据多列删除<br><br>
	 * 
	 * SQL举例: DELETE FROM TABLE WHERE COL3=89 AND COL_4='SS'
	 * 
	 * @param columnValueMap
	 * @throws DataAccessException 
	 * @return 返回delete影响的行数
	 */
	public int deleteByColumns(MapDTO columnValueMap) {
		Assert.notNull(columnValueMap, "columnValueMap Must not be empty");
		String tableName = this.TABLE_NAMES[0];
			
		//准备参数
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		params.put("WHERE_SQL", SqlUtil.buildWhereSql(columnValueMap));
		
		//执行删除
		int rows = getSqlMapClientTemplate().delete(NAMESPACE + DELETE_BY_COLUMN, params);
		
		if(rows == 0) {
			log.info("delete successfully, but 0 row effected. [" + CLAZZ.getName() + "]");
		}
		
		return rows;
	}

	/**
	 * 根据主键更新, 必须提供pk<br><br>
	 * 
	 * SQL举例: UPDATE TABLE SET COL_1=9, COL_2='33' WHERE PK=45
	 * 
	 * @param dto
	 * @throws DataAccessException 
	 * @return 返回update影响的行数
	 */
	public int update(T dto) {
		Assert.notNull(dto, "dto Must not be null for update");
		String tableName = this.TABLE_NAMES[0];
			
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		params.put("PK_NAME", this.PK_NAME);
		
		MapDTO columnMap = DtoUtil.disperseDTO(dto);
		
		params.put("PK_VALUE", columnMap.get(PK_NAME));
		params.put("SET_SQL", SqlUtil.buildSetSql(columnMap.exclude(PK_NAME)));

		int rows = getSqlMapClientTemplate().update(NAMESPACE + UPDATE, params);
		
		if(rows == 0) {
			log.info("update successfully, but 0 row effected. [" + dto.getClass().getSimpleName() + "]");
		}
		
		return rows;
	}
	
	/**
	 * 根据<font color="red"><strong>主键</strong></font>更新指定列, 必须提供pk<br><br>
	 * 
	 * SQL举例: SELECT COL_1, COL2 WHERE PK=2
	 * 
	 * @param dto
	 * @param columnNameSet
	 * @return 返回update影响的行数
	 * @see {@link #updateExcludedColumns}
	 */
	public int updateColumns(T dto, HashSet<String> columnNameSet) {
		Assert.notNull(dto, "dto Must not be null for update");
		Assert.notEmpty(columnNameSet, "columnNameSet Must not be null for update");
		String tableName = this.TABLE_NAMES[0];
		
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		params.put("PK_NAME", this.PK_NAME);
		
		MapDTO columnMap = DtoUtil.disperseDTO(dto);		
		params.put("PK_VALUE", columnMap.get(PK_NAME));

		MapDTO columnMap1 = new MapDTO();
		for(String key : columnNameSet) {
			if(columnMap.containsKey(key)) {
				columnMap1.put(key, columnMap.get(key));
			}
		}
		
		params.put("SET_SQL", SqlUtil.buildSetSql(columnMap1.exclude(PK_NAME)));
		
		
		int rows = getSqlMapClientTemplate().update(NAMESPACE + UPDATE, params);
		
		return rows;
	}
	
	public int updateExcludedColumns(T dto, HashSet<String> columnNameSet) {
		Assert.notNull(dto, "dto Must be not null for update");
		Assert.notEmpty(columnNameSet, "columnNameSet Must be not null for update");
		String tableName = this.TABLE_NAMES[0];
		
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		params.put("PK_NAME", this.PK_NAME);
		
		MapDTO columnMap = DtoUtil.disperseDTO(dto);
		params.put("PK_VALUE", columnMap.get(PK_NAME));
		
		for(String key : columnNameSet) {
			if(columnMap.containsKey(key)){
				columnMap.exclude(key);
			}
		}
		
		params.put("SET_SQL", SqlUtil.buildSetSql(columnMap.exclude(PK_NAME)));
		
		int rows = getSqlMapClientTemplate().update(NAMESPACE + UPDATE, params);
		
		return rows;
	}
	
	/**
	 * 根据某些列作为限制条件，且更新表中的某些列<br><br>
	 * 
	 * SQL举例: UPDATE TABLE SET COL_1=34, COL2='ldod' WHERE COL3=89 AND COL_4='dd'
	 * 
	 * @param dto
	 * @param updateColumnNameSet 待更新的列名
	 * @param columnValueMap 限制更新条件的列名，Where语句后的列名
	 * @return
	 */
	public int updateColumnsByColumn(T dto, HashSet<String> updateColumnNameSet, MapDTO columnValueMap) {
		Assert.notNull(dto, "dto Must not be null for update");
		Assert.notEmpty(updateColumnNameSet, "updateColumnNameSet Must not be empty for update");
		Assert.notEmpty(columnValueMap, "columnNameSet Must not be empty for update");
		String tableName = this.TABLE_NAMES[0];
		
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		
		MapDTO columnMap = DtoUtil.disperseDTO(dto);		

		MapDTO columnMap1 = new MapDTO();
		for(String key : updateColumnNameSet) {
			if(columnMap.containsKey(key)) {
				columnMap1.put(key, columnMap.get(key));
			}
		}
		
		params.put("SET_SQL", SqlUtil.buildSetSql(columnMap1));
		params.put("WHERE_SQL", SqlUtil.buildWhereSql(columnValueMap));
		
		int rows = getSqlMapClientTemplate().update(NAMESPACE + UPDATE_BY_COLUMN, params);
		
		return rows;
	}
	
	/**
	 * 根据某些列作为限制条件，且更新表中的排除某些列以外的其他列<br><br>
	 * 
	 * SQL举例: UPDATE TABLE SET COL_1=1, COL2='orere' WHERE COL3=89 AND COL_4='SS' AND COL_5 IS NULL
	 * 
	 * @param updateColumnNameSet 待更新的列名
	 * @param columnValueMap 限制更新条件的列名，Where语句后的列名
	 * @return 更新影响的行数
	 */
	public int updateExcludedColumnsByColumn(T dto, HashSet<String> updateColumnNameSet, MapDTO columnValueMap) {
		Assert.notNull(dto, "dto Must be not null for update");
		Assert.notEmpty(updateColumnNameSet, "updateColumnNameSet Must not be empty for update");
		Assert.notEmpty(columnValueMap, "columnNameSet Must not be empty for update");
		String tableName = this.TABLE_NAMES[0];
		
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		
		MapDTO columnMap = DtoUtil.disperseDTO(dto);

		for(String key : updateColumnNameSet) {
			if(columnMap.containsKey(key)){
				columnMap.exclude(key);
			}
		}
		
		params.put("SET_SQL", SqlUtil.buildSetSql(columnMap.exclude(PK_NAME)));
		params.put("WHERE_SQL", SqlUtil.buildWhereSql(columnValueMap));
		
		int rows = getSqlMapClientTemplate().update(NAMESPACE + UPDATE_BY_COLUMN, params);
		
		return rows;
	}
	

	/**
	 * 插入单表
	 * 
	 * @param dto 不必提供主键值
	 * @throws DataAccessException 
	 * @return 返回insert后自增的PK
	 */
	public PK insert(T dto) {
		Assert.notNull(dto, "dto Must not be null for insert");
		String tableName = this.TABLE_NAMES[0];
		
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);

		MapDTO columnMap = DtoUtil.disperseDTO(dto);
		
		params.put("VALUES_SQL", SqlUtil.buildValuesSql(columnMap.exclude(PK_NAME)));

		PK pk = (PK) getSqlMapClientTemplate().insert(NAMESPACE + INSERT, params);

		return pk;
	}
	
	/**
	 * 插入时排除某些列
	 * 
	 * @param dto
	 * @param columnNameSet
	 * @return
	 */
	public PK insertExcludedColumns(T dto, HashSet<String> columnNameSet) {
		Assert.notNull(dto, "dto Must be not null for update");
		Assert.notEmpty(columnNameSet, "columnNameSet Must be not null for update");
		String tableName = this.TABLE_NAMES[0];
		
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);
		
		MapDTO columnMap = DtoUtil.disperseDTO(dto);
		
		for(String key : columnNameSet) {
			if(columnMap.containsKey(key)){
				columnMap.exclude(key);
			}
		}
		
		params.put("VALUES_SQL", SqlUtil.buildValuesSql(columnMap.exclude(PK_NAME)));
		
		PK pk = (PK) getSqlMapClientTemplate().insert(NAMESPACE + INSERT, params);
		
		return pk;
	}
	
	/**
	 * 不需要依靠自增主键，而是指定主键值
	 * 
	 * @param dto
	 * @return
	 */
	public void insertWithPk(T dto) {
		Assert.notNull(dto, "dto Must not be null for insert");
		String tableName = this.TABLE_NAMES[0];
		
		MapDTO params = new MapDTO();
		params.put("TABLE_NAME", tableName);

		MapDTO columnMap = DtoUtil.disperseDTO(dto);
		
		params.put("VALUES_SQL", SqlUtil.buildValuesSql(columnMap));

		getSqlMapClientTemplate().insert(NAMESPACE + INSERT, params);
	}
	
//	/** 可以对参数进行拦截，进行额外处理，方便扩展
//	 * @param params
//	 */
//	protected void intercept(MapDTO params) {
//
//	}

}
