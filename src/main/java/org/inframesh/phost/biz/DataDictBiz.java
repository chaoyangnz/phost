/*
 *  Copyright 2009-2010 The Inframesh Software Foundation (ISF)
 *
 *  Licensed under the Inframesh Free Software License (the "License"), 
 *	Version 1.0 ; you may obtain a copy of the license at
 *
 *  	http://www.inframesh.org/licenses/LICENSE-1.0
 *
 *  Software distributed under the License is distributed  on an "AS IS" 
 *  BASIS but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the License 
 *  for more details.
 *
 *  Inframesh, Websquare, Jex are all reserved marks.
 */
package org.inframesh.phost.biz;

import java.util.List;

import org.inframesh.phost.dao.misc.DataDictDao;
import org.inframesh.phost.model.entry.DataDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-22 上午11:24:22 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
@Component
public class DataDictBiz {
	@Autowired
	private DataDictDao dataDictDao;
	
	public List<DataDict> queryAllItems(String dictType){
		
		return dataDictDao.queryAllItems(dictType);
	}
	
	public DataDict queryItem(String dictType, String dictCode) {
		
		return dataDictDao.queryItem(dictType, dictCode);
	}

}
