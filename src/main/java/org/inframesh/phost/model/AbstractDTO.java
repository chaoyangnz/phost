/**
 * 
 */
package org.inframesh.phost.model;


/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-4-21
 */
public abstract class AbstractDTO implements DTO {
	protected static final long serialVersionUID = 1L;
	
	/**
	 * 打印出DTO的内容
	 */
	public void dump() {
//		System.out.println(toJsonString());
	}
	
}
