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
 *  Inframesh, Dynamo, Plax, Fundus, Geda and Websquare are all reserved
 *  marks or code names.
 */
package org.inframesh.phost.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.inframesh.phost.biz.DataDictBiz;
import org.inframesh.phost.biz.ProjectBiz;
import org.inframesh.phost.dao.misc.DownloadDao;
import org.inframesh.phost.model.MapDTO;
import org.inframesh.phost.model.domain.ProjectInfo;
import org.inframesh.phost.model.entry.DataDict;
import org.inframesh.phost.model.entry.Download;
import org.inframesh.phost.model.entry.ProjectMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @since projecton
 * @version $Revision: 1.0 $Date:2009-1-2 下午07:30:02$
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
@Controller
public class ProjectController extends BaseController {
	
	@Autowired 
	private ProjectBiz projectBiz;
	
	@Autowired 
	private DataDictBiz dataDictBiz;
	
	@RequestMapping("/createProject")
	public String createProject(ModelMap model) {
		
		List<DataDict> versionControls = dataDictBiz.queryAllItems("VERSION_CONTROL");
		List<DataDict> codeLicenses = dataDictBiz.queryAllItems("CODE_LICENSE");
		List<DataDict> contentLicenses = dataDictBiz.queryAllItems("CONTENT_LICENSE");
		
		model.addAttribute("versionControls", versionControls);
		model.addAttribute("codeLicenses", codeLicenses);
		model.addAttribute("contentLicenses", contentLicenses);
		
		return "createProject";
	}
	
	@RequestMapping("/createProject.do")
	public String createProject(ProjectMeta project, String[] label, HttpSession session, ModelMap model) {
		
		projectBiz.saveProject(project, label, (Long)session.getAttribute("userId"));
		
		((Set<String>) session.getAttribute("ownerProjects")).add(project.getName());
		
		return "redirect:/p/" + project.getName() + "/";
	}
	
	@RequestMapping("/p/{projectName}/")
	public String projectHome(@PathVariable String projectName, HttpServletRequest request, HttpSession session, ModelMap model) {

		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		List<Download> featuredDownloads = projectBiz.queryProjectFeaturedDownloads(project.getId());
		
		model.addAttribute("featuredDownloads", featuredDownloads);
		
		return "p/home";
	}

	@RequestMapping("/p/{projectName}/people/list")
	public String peopleList(@PathVariable String projectName, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		return "p/peopleList";
	}
	
	@RequestMapping("/p/{projectName}/downloads/list")
	public String downloadsList(@PathVariable String projectName, ModelMap model) {
		
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		List<Download> downloads = projectBiz.queryProjectDownloads(project.getId());
		
		model.addAttribute("downloads", downloads);
		
		return "p/downloadList";
	}
	
	@RequestMapping("/p/{projectName}/downloads/entry")
	public String downloadsNewEntry(@PathVariable String projectName, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		return "p/downloadEntry";
	}
	
	@RequestMapping("/p/{projectName}/downloads/entry.do")
	public String saveDownloadsNewEntry(@PathVariable String projectName, @RequestParam("uploadFile") MultipartFile uploadFile, Download download, String[] label, HttpServletRequest request, HttpSession session, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		download.setProjectId(project.getId());
		download.setUploaded((String)session.getAttribute("email"));
		download.setFileName(uploadFile.getOriginalFilename());
		download.setFileSize(String.valueOf(uploadFile.getSize()));
		
		projectBiz.saveProjectDownload(download, label);
		
		String path = request.getRealPath("/res/upload/") + File.separator + project.getId() + "-" + uploadFile.getOriginalFilename();  
        File serverFile = new File(path);
        try {
	        if(!serverFile.exists()) {
	        	serverFile.createNewFile();
	        }
	        FileOutputStream fos = new FileOutputStream(path);
			fos.write(uploadFile.getBytes());	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
		return "redirect:/p/" + projectName + "/downloads/list";
	}
	
	@RequestMapping("/p/{projectName}/downloads/files/{downloadId}")
	public String downloadFile(@PathVariable String projectName, @PathVariable long downloadId, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		Download download = projectBiz.queryProjectDownlaod(downloadId);
		
		String realFileName = project.getId() + "-" + download.getFileName();
		
		return "redirect:/upload/" + realFileName;
	}
	
	@RequestMapping("/p/{projectName}/downloads/detail/{downloadId}")
	public String downloadDetail(@PathVariable String projectName, @PathVariable long downloadId, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		Download download = projectBiz.queryProjectDownlaod(downloadId);
		
		model.addAttribute("download", download);
		
//		String realFileName = project.getId() + "-" + download.getFileName();
		
		return "/p/downloadDetail";
	}
	
	@RequestMapping("/p/{projectName}/downloads/detail.do")
	public String downloadEntryUpdate(@PathVariable String projectName, Download download, String[] label, ModelMap model) {
		
		projectBiz.saveProjectDownload(download, label);

		return "redirect:/p/" + projectName + "/downloads/detail/" + download.getDownloadId();
	}
	
	@RequestMapping("/p/{projectName}/w/list")
	public String wikiList(@PathVariable String projectName, ModelMap model) {
		
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		return "p/wikiList";
	}
	
	@RequestMapping("/p/{projectName}/w/entry")
	public String wikiNewEntry(@PathVariable String projectName, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		return "p/wikiEntry";
	}
	
	@RequestMapping("/p/{projectName}/w/entry.do")
	public String saveWikiNewEntry(@PathVariable String projectName, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		return "p/wikiEntry";
	}
	
	@RequestMapping("/p/{projectName}/issues/list")
	public String issuesList(@PathVariable String projectName, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		return "p/issueList";
	}
	
	@RequestMapping("/p/{projectName}/source/checkout")
	public String sourceCheckout(@PathVariable String projectName, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		return "p/sourceCheckout";
	}
	
	@RequestMapping("/p/{projectName}/source/browse")
	public String sourceBrowse(@PathVariable String projectName, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		return "loginView";
	}
	
	@RequestMapping("/p/{projectName}/source/list")
	public String sourceList(@PathVariable String projectName, ModelMap model) {
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		return "loginView";
	}
	
	@RequestMapping("/p/{projectName}/admin")
	public String admin(@PathVariable String projectName, ModelMap model) {
		
		ProjectInfo project = projectBiz.retriveProjectInfo(projectName);
		
		model.addAttribute("project", project);
		
		List<DataDict> versionControls = dataDictBiz.queryAllItems("VERSION_CONTROL");
		List<DataDict> codeLicenses = dataDictBiz.queryAllItems("CODE_LICENSE");
		List<DataDict> contentLicenses = dataDictBiz.queryAllItems("CONTENT_LICENSE");
		
		model.addAttribute("versionControls", versionControls);
		model.addAttribute("codeLicenses", codeLicenses);
		model.addAttribute("contentLicenses", contentLicenses);
		
		return "p/admin";
	}
	
	@RequestMapping("/p/{projectName}/admin.do")
	public String admin(@PathVariable String projectName, ProjectMeta projectMeta, String[] label, ModelMap model) {
		
		projectBiz.saveProject(projectMeta, label);
		
		return "redirect:/p/" + projectName + "/admin";
	}
}
