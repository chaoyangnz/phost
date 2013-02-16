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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.inframesh.phost.dao.misc.DataDictDao;
import org.inframesh.phost.dao.misc.DownloadDao;
import org.inframesh.phost.dao.misc.ExtrasDao;
import org.inframesh.phost.dao.misc.ProjectMemberDao;
import org.inframesh.phost.dao.misc.ProjectMetaDao;
import org.inframesh.phost.model.MapDTO;
import org.inframesh.phost.model.domain.ProjectInfo;
import org.inframesh.phost.model.domain.ProjectInfo.Pair;
import org.inframesh.phost.model.entry.DataDict;
import org.inframesh.phost.model.entry.Download;
import org.inframesh.phost.model.entry.Extras;
import org.inframesh.phost.model.entry.ProjectMember;
import org.inframesh.phost.model.entry.ProjectMeta;
import org.inframesh.phost.model.entry.User;
import org.inframesh.phost.web.exception.NoExistedProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-22 上午11:19:54 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
@Component
public class ProjectBiz {
	
	@Autowired
	private ProjectMetaDao projectMetaDao;

	@Autowired
	private ProjectMemberDao projectMemberDao;
	
	@Autowired
	private ExtrasDao extrasDao;
	
	@Autowired
	private DownloadDao downloadDao;
	
	@Autowired
	private DataDictDao dataDictDao;
	
	
	
	/*-----------------------Project Labes------------------------------*/	
	public void saveProjectLabels(Long projectId, String[] label) {
		//先删
		extrasDao.deleteLabels(projectId, Extras.MT_PROJECT);
		
		for(String lbl : label) {
			if(lbl != null && !"".equals(lbl.trim())) {
				extrasDao.insertLabel(projectId, lbl, Extras.MT_PROJECT);
			}
		}
	}
	
	/**
	 * 15个label
	 * @param projectId
	 * @return
	 */
	public String[] queryProjectLabels(Long projectId) {
		List<Extras> list = extrasDao.queryLabels(projectId, Extras.MT_PROJECT);
		List<String> labels = new ArrayList();
		for(Extras extras : list) {
			labels.add(extras.getText());
		}
		
		return labels.toArray(new String[15]);
	}
	
	
	/**************************download labels-*/
	public void saveDownloadLabels(Long downloadId, String[] label) {
		//先删
		extrasDao.deleteLabels(downloadId, Extras.MT_DOWNLOAD);
		
		for(String lbl : label) {
			if(lbl != null && !"".equals(lbl.trim())) {
				extrasDao.insertLabel(downloadId, lbl, Extras.MT_DOWNLOAD);
			}
		}
	}
	
	public String[] queryDownloadLabels(Long downloadId) {
		List<Extras> list = extrasDao.queryLabels(downloadId, Extras.MT_DOWNLOAD);
		List<String> labels = new ArrayList();
		for(Extras extras : list) {
			labels.add(extras.getText());
		}
		
		return labels.toArray(new String[15]);
	}
	
	/**
	 * Insert or update
	 * @param project
	 * @param userId
	 */
	public void saveProjectMeta(ProjectMeta project) {
		if(project.getProjectId() == null || project.getProjectId() == 0L) {		
			Long projectId = projectMetaDao.insert(project);
			project.setProjectId(projectId);
		} else {
			projectMetaDao.update(project);
		}
	}
	
	
	/*-------------------------------------------------*/
	public List<User> queryProjectMembers(String projectName, String role) {
		return projectMemberDao.queryUserByProjectName(projectName, ProjectMember.OWNER);
	}
	
	public void saveProjectMember(Long projectId, Long userId, String role) {
		ProjectMember member = projectMemberDao.queryByColumns(new MapDTO("PROJECT_ID", projectId, "USER_ID", userId));
		if(member != null) {//已存在
			return;
		}
		
		ProjectMember projectMember = new ProjectMember();
		projectMember.setProjectId(projectId);
		projectMember.setUserId(userId);
		projectMember.setRole(role);
		
		projectMemberDao.insert(projectMember);
	}
	
	public Download queryProjectDownlaod(Long downloadId) {
		Download download = downloadDao.queryByPk(downloadId);
		download.setLabels(queryDownloadLabels(downloadId));
		
		return download;
	}
	
	public List<Download> queryProjectDownloads(Long projectId) {
		List<Download> downloads = downloadDao.queryListByColumns(new MapDTO("PROJECT_ID", projectId));
		for(Download download : downloads) {
			String[] labels = queryDownloadLabels(download.getDownloadId());
			download.setLabels(labels);
		}
		
		return downloads;
	}
	
	public List<Download> queryProjectFeaturedDownloads(Long projectId) {
		List<Download> downloads = downloadDao.queryListByColumns(new MapDTO("PROJECT_ID", projectId));
		
		List<Download> featureDownloads = new ArrayList();
		for(Download download : downloads) {
			String[] labels = queryDownloadLabels(download.getDownloadId());
			download.setLabels(labels);
			
			
				for(String label : labels) {
					if(label != null && label.equals("featured")) {
						featureDownloads.add(download);
					}
			}
			
		}
		
		return featureDownloads;
	}
	
	public void saveProjectDownload(Download download) {
		if(download.getDownloadId() == null || download.getDownloadId() == 0L) {
			Long downloadId = downloadDao.insert(download);
			download.setDownloadId(downloadId);
		} else {
			HashSet<String> columns = new HashSet();
			columns.add("SUMMARY");
			downloadDao.updateColumns(download, columns);
		}
	}
	
	/*----------------------------------------------------------------*/
	public ProjectInfo retriveProjectInfo(String projectName) {
		ProjectMeta projectMeta = projectMetaDao.queryByColumns(new MapDTO("NAME", projectName));
		if(projectMeta == null) {
			throw new NoExistedProjectException("{0} not existed", projectName);
		}
		DataDict codeLicense = dataDictDao.queryItem("CODE_LICENSE",projectMeta.getCodeLicense());
		DataDict contentLicense = dataDictDao.queryItem("CONTENT_LICENSE", projectMeta.getContentLicense());
		DataDict versionControl = dataDictDao.queryItem("VERSION_CONTROL", projectMeta.getVersionControl());
		
		List<User> ownerMembers = queryProjectMembers(projectName, ProjectMember.OWNER);
		List<User> committerMembers = queryProjectMembers(projectName, ProjectMember.COMMITTER);
		List<User> contributorMembers = queryProjectMembers(projectName, ProjectMember.CONTRIBUTOR);
		
		ProjectInfo project = new ProjectInfo();
		project.setId(projectMeta.getProjectId());
		project.setName(projectName);
		project.setSummary(projectMeta.getSummary());
		project.setDescription(projectMeta.getDescription());
		project.setCodeLicense(codeLicense);
		project.setContentLicense(contentLicense);
		project.setVersionControl(versionControl);
		project.setOwners(ownerMembers);
		project.setCommitters(committerMembers);
		project.setContributors(contributorMembers);
		
		//labels
		project.setLabels(queryProjectLabels(projectMeta.getProjectId()));

		return project;
	}
	
	/**
	 * create new project
	 * @param project
	 * @param labels
	 * @param userId
	 */
	public void saveProject(ProjectMeta project, String[] labels, Long userId) {
		ProjectMeta proj = projectMetaDao.queryByColumns(new MapDTO("NAME", project.getName()));
		if(proj != null) {
			throw new RuntimeException("Project name existed");
		}
		saveProjectMeta(project);
		saveProjectMember(project.getProjectId(), userId, ProjectMember.OWNER);
		saveProjectLabels(project.getProjectId(), labels);
	}
	
	/**
	 * administrate existed project
	 * @param project
	 * @param labels
	 * @param links
	 */
	public void saveProject(ProjectMeta project, String[] labels) {
		ProjectMeta proj = projectMetaDao.queryByColumns(new MapDTO("NAME", project.getName()));
		if(proj == null) {
			throw new RuntimeException("Project no existed");
		}
		
		saveProjectMeta(project);
		saveProjectLabels(project.getProjectId(), labels);
	}
	
	/**
	 * Save project download and download label
	 * @param download
	 * @param label
	 */
	public void saveProjectDownload(Download download, String[] label) {
		saveProjectDownload(download);
		saveDownloadLabels(download.getDownloadId(), label);
	}
}
