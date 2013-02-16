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

import org.inframesh.phost.model.entry.DataDict;
import org.inframesh.phost.model.entry.User;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-21 上午09:29:46 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
public class ProjectInfo {
	private Long id;
	private String name;
	private String summary;
	private String description;
	private DataDict codeLicense;
	private DataDict contentLicense;
	private DataDict versionControl;
	private String logo;
	
	private List<User> owners;
	private List<User> committers;
	private List<User> contributors;
	
	private String[] labels = new String[15];
	
	private Pair[] links = new Pair[5];
	private Pair[] discussGroups = new Pair[5];
	private Pair[] blogs = new Pair[5];
	
	public static class Pair {
		private String text;
		private String url;
		
		public Pair(String text, String url) {
			this.text = text;
			this.url = url;
		}
		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}
		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}
		/**
		 * @param url the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}

		
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the codeLicense
	 */
	public DataDict getCodeLicense() {
		return codeLicense;
	}
	/**
	 * @param codeLicense the codeLicense to set
	 */
	public void setCodeLicense(DataDict codeLicense) {
		this.codeLicense = codeLicense;
	}
	/**
	 * @return the contentLicense
	 */
	public DataDict getContentLicense() {
		return contentLicense;
	}
	/**
	 * @param contentLicense the contentLicense to set
	 */
	public void setContentLicense(DataDict contentLicense) {
		this.contentLicense = contentLicense;
	}
	/**
	 * @return the versionControl
	 */
	public DataDict getVersionControl() {
		return versionControl;
	}
	/**
	 * @param versionControl the versionControl to set
	 */
	public void setVersionControl(DataDict versionControl) {
		this.versionControl = versionControl;
	}
	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * @return the owners
	 */
	public List<User> getOwners() {
		return owners;
	}
	/**
	 * @param owners the owners to set
	 */
	public void setOwners(List<User> owners) {
		this.owners = owners;
	}
	/**
	 * @return the committers
	 */
	public List<User> getCommitters() {
		return committers;
	}
	/**
	 * @param committers the committers to set
	 */
	public void setCommitters(List<User> committers) {
		this.committers = committers;
	}
	/**
	 * @return the contributors
	 */
	public List<User> getContributors() {
		return contributors;
	}
	/**
	 * @param contributors the contributors to set
	 */
	public void setContributors(List<User> contributors) {
		this.contributors = contributors;
	}
	/**
	 * @param labels the labels to set
	 */
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	/**
	 * @return the labels
	 */
	public String[] getLabels() {
		return labels;
	}
	
}
