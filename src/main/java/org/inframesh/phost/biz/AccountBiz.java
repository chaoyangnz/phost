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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.inframesh.phost.dao.misc.ProjectMemberDao;
import org.inframesh.phost.dao.misc.UserDao;
import org.inframesh.phost.model.MapDTO;
import org.inframesh.phost.model.domain.AccountInfo;
import org.inframesh.phost.model.entry.ProjectMember;
import org.inframesh.phost.model.entry.ProjectMeta;
import org.inframesh.phost.model.entry.User;
import org.inframesh.phost.web.exception.NoExistedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-22 上午11:41:10 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
@Component
public class AccountBiz {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProjectMemberDao projectMemberDao;
	

	public boolean authenize(User user) {
		User usr = userDao.queryByColumns(new MapDTO("email", user.getEmail()));
		if(usr == null || !user.getEmail().equals(usr.getEmail())) {
			return false;
		}
		
		user.setUserId(usr.getUserId());
		return true;
	}
	
	public boolean uniqueValidate(User user) {
		List<User> users = userDao.queryListByColumns(new MapDTO("email", user.getEmail()));
		return users.size() > 0 ? false : true;
	}
	

	public Long saveAccount(User user) {
		Long userId = userDao.insert(user);
		user.setUserId(userId);
		
		return userId;
	}
	
	public AccountInfo retrieveAccountInfo(String email) {
		User user = userDao.queryByColumns(new MapDTO("EMAIL", email));
		if(user == null) {
			throw new NoExistedAccountException("{0} not existed", email);
		}
		
		//查询所属project，成员，等
		List<ProjectMeta> ownerProjects =  projectMemberDao.queryProjectByUserId(user.getUserId(), ProjectMember.OWNER);
		List<ProjectMeta> committerProjects =  projectMemberDao.queryProjectByUserId(user.getUserId(), ProjectMember.COMMITTER);
		List<ProjectMeta> contributorProjects =  projectMemberDao.queryProjectByUserId(user.getUserId(), ProjectMember.CONTRIBUTOR);
		
		AccountInfo account = new AccountInfo();
		account.setUserId(user.getUserId());
		account.setEmail(user.getEmail());
		account.setPassword(user.getPassword());
		account.setOwnerProjects(ownerProjects);
		account.setCommitterProjects(committerProjects);
		account.setContributorProjects(contributorProjects);

		return account;
	}
	
	public Set<String> queryAllOwnerProject(Long userId) {
		List<ProjectMeta> projects = projectMemberDao.queryProjectByUserId(userId, ProjectMember.OWNER);
		
		Set<String> set = new HashSet<String>();
		for(ProjectMeta project : projects) {
			set.add(project.getName());
		}
		
		return set;
	}
	
	public Set<String> queryAllCommitterProject(Long userId) {
		List<ProjectMeta> projects = projectMemberDao.queryProjectByUserId(userId, ProjectMember.COMMITTER);
		
		Set<String> set = new HashSet<String>();
		for(ProjectMeta project : projects) {
			set.add(project.getName());
		}
		
		return set;
	}
	
	public Set<String> queryAllContributorProject(Long userId) {
		List<ProjectMeta> projects = projectMemberDao.queryProjectByUserId(userId, ProjectMember.CONTRIBUTOR);
		
		Set<String> set = new HashSet<String>();
		for(ProjectMeta project : projects) {
			set.add(project.getName());
		}
		
		return set;
	}


}
