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
package org.inframesh.phost.dao.misc;

import java.util.List;

import org.inframesh.phost.dao.SingleTableDao;
import org.inframesh.phost.model.MapDTO;
import org.inframesh.phost.model.domain.ProjectInfo.Pair;
import org.inframesh.phost.model.entry.Extras;
import org.springframework.stereotype.Component;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-21 下午01:33:18 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
@Component
public class ExtrasDao extends SingleTableDao<Extras, Long> {
	
	
	
	public long insertLabel(Long id, String label, String moduleType) {
		Extras extras = new Extras();
		extras.setModuleType(moduleType);
		extras.setCategory(Extras.CT_LABEL);
		extras.setText(label);
		extras.setModuleId(id);
		
		return super.insert(extras);
	}
	
	public List<Extras> queryLabels(Long id, String moduleType) {
		return super.queryListByColumns(new MapDTO("MODULE_ID", id, "MODULE_TYPE", moduleType, "CATEGORY", Extras.CT_LABEL));
	}


	public void deleteLabels(Long id, String moduleType) {
		super.deleteByColumns(new MapDTO("MODULE_ID", id, "MODULE_TYPE", moduleType, "CATEGORY", Extras.CT_LABEL));
	}
	
	
	/***************************************/
	public long insertLink(Long id, Pair link, String moduleType) {
		Extras extras = new Extras();
		extras.setModuleType(moduleType);
		extras.setCategory(Extras.CT_LINK);
		extras.setText(link.getText());
		extras.setContent(link.getUrl());
		extras.setModuleId(id);
		
		return super.insert(extras);
	}
	
	public List<Extras> queryLinks(Long id, String moduleType) {
		return super.queryListByColumns(new MapDTO("MODULE_ID", id, "MODULE_TYPE", moduleType, "CATEGORY", Extras.CT_LINK));
	}


	public void deleteLinks(Long id, String moduleType) {
		super.deleteByColumns(new MapDTO("MODULE_ID", id, "MODULE_TYPE", moduleType, "CATEGORY", Extras.CT_LINK));
	}
	
	/****************************************/
	
	public long insertDiscussGroup(Long id, Pair discussGroup, String moduleType) {
		Extras extras = new Extras();
		extras.setModuleType(moduleType);
		extras.setCategory(Extras.CT_DISCUSSGROUP);
		extras.setText(discussGroup.getText());
		extras.setContent(discussGroup.getUrl());
		extras.setModuleId(id);
		
		return super.insert(extras);
	}
	
	public List<Extras> queryDiscussGroups(Long id, String moduleType) {
		return super.queryListByColumns(new MapDTO("MODULE_ID", id, "MODULE_TYPE", moduleType, "CATEGORY", Extras.CT_DISCUSSGROUP));
	}


	public void deleteDiscussGroups(Long id, String moduleType) {
		super.deleteByColumns(new MapDTO("MODULE_ID", id, "MODULE_TYPE", moduleType, "CATEGORY", Extras.CT_DISCUSSGROUP));
	}
	
	/*********************************************/
	public long insertBlog(Long id, Pair blog, String moduleType) {
		Extras extras = new Extras();
		extras.setModuleType(moduleType);
		extras.setCategory(Extras.CT_BLOG);
		extras.setText(blog.getText());
		extras.setContent(blog.getUrl());
		extras.setModuleId(id);
		
		return super.insert(extras);
	}
	
	public List<Extras> queryBlogs(Long id, String moduleType) {
		return super.queryListByColumns(new MapDTO("MODULE_ID", id, "MODULE_TYPE", moduleType, "CATEGORY", Extras.CT_BLOG));
	}


	public void deleteBlogs(Long id, String moduleType) {
		super.deleteByColumns(new MapDTO("MODULE_ID", id, "MODULE_TYPE", moduleType, "CATEGORY", Extras.CT_BLOG));
	}
}
