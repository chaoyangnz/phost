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
package org.inframesh.phost.model.domain;

import java.util.List;

import org.inframesh.phost.model.entry.ProjectMeta;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-21 下午12:44:09 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
public class AccountInfo {
	private Long userId;
	private String email;
	private String password;
	private List<ProjectMeta> ownerProjects;
	private List<ProjectMeta> committerProjects;
	private List<ProjectMeta> contributorProjects;
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the ownerProjects
	 */
	public List<ProjectMeta> getOwnerProjects() {
		return ownerProjects;
	}
	/**
	 * @param ownerProjects the ownerProjects to set
	 */
	public void setOwnerProjects(List<ProjectMeta> ownerProjects) {
		this.ownerProjects = ownerProjects;
	}
	/**
	 * @return the committerProjects
	 */
	public List<ProjectMeta> getCommitterProjects() {
		return committerProjects;
	}
	/**
	 * @param committerProjects the committerProjects to set
	 */
	public void setCommitterProjects(List<ProjectMeta> committerProjects) {
		this.committerProjects = committerProjects;
	}
	/**
	 * @return the contributorProjects
	 */
	public List<ProjectMeta> getContributorProjects() {
		return contributorProjects;
	}
	/**
	 * @param contributorProjects the contributorProjects to set
	 */
	public void setContributorProjects(List<ProjectMeta> contributorProjects) {
		this.contributorProjects = contributorProjects;
	}
}
