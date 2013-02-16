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
import org.inframesh.phost.model.entry.ProjectMember;
import org.inframesh.phost.model.entry.ProjectMeta;
import org.inframesh.phost.model.entry.User;
import org.inframesh.phost.util.dto.DtoUtil;
import org.springframework.stereotype.Component;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-19 上午11:45:17 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
@Component
public class ProjectMemberDao extends SingleTableDao<ProjectMember, Long> {
	
	private static final String NS = "org.inframesh.phost.dao";
	
	public List<ProjectMeta> queryProjectByUserId(Long userId, String role) {
		
		MapDTO param = new MapDTO("userId", userId, "role", role);
		List list = super.getSqlMapClientTemplate().queryForList(NS + ".queryProjectByUserId", param);
		
		return DtoUtil.assembleDtoForList(list, ProjectMeta.class);
	}
	
	public List<User> queryUserByProjectName(String projectName, String role) {
		
		MapDTO param = new MapDTO("projectName", projectName, "role", role);
		List list = super.getSqlMapClientTemplate().queryForList(NS + ".queryUserByProjectName", param);
		
		return DtoUtil.assembleDtoForList(list, User.class);
	}
}
