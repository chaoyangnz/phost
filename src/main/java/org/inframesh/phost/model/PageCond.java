/**
 * 
 */
package org.inframesh.phost.model;

/**
 * @author 杨超 ychao@bankcomm.com 交通银行
 *
 * 2009-2-5 
 */
public class PageCond {

    /**
     * 表示当前页中的数据在结果集中的起始位置。缺省为"0"。 
     */
    private int start = 0;
    
    /**
     * 表示查询结果的总条数。缺省统计。 
     */
    private int count = 0;
    
    
    /**
     * 表示每页显示数据的条数。缺省为“10”。 
     */
    private int limit = 10;


	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}


   
}
