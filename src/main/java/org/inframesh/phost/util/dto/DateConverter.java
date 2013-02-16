/**
 * 
 */
package org.inframesh.phost.util.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-8-25
 */
public class DateConverter implements Converter {

	public Object convert(Class type, Object value) {          
        
        if (value == null) {
            return null;
        }

        if (value instanceof Date) {
            return (value);
        }

        try {
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
            return df.parse(value.toString());   
        } catch (Exception e) {
            throw new ConversionException(e);
        } 
    }   

}
