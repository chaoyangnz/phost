/**
 * 
 */
package org.inframesh.phost.dao.ibatis;

import java.util.Map;

import com.ibatis.sqlmap.engine.exchange.DataExchangeFactory;
import com.ibatis.sqlmap.engine.exchange.JavaBeanDataExchange;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-8-14
 */
public class AnnotationJavaBeanDataExchange extends JavaBeanDataExchange {

	protected AnnotationJavaBeanDataExchange(DataExchangeFactory dataExchangeFactory) {
		super(dataExchangeFactory);
	}
	
	public void initialize(Map properties) {
		super.initialize(properties);
		
//		Object map = properties.get("map");
//		if (map instanceof ResultMap) {
//		      ResultMap resultMap = (ResultMap) map;
//		      if (resultMap != null) {
//		        ResultMapping[] resultMappings = resultMap.getResultMappings();
//		        String[] resultPropNames = new String[resultMappings.length];
//		        for (int i = 0; i < resultPropNames.length; i++) {
//		          resultPropNames[i] = resultMappings[i].getPropertyName();
//		        }
//		        resultPlan = AccessPlanFactory.getAccessPlan(resultMap.getResultClass(), resultPropNames);
//		      }
//		    }
	}

}
