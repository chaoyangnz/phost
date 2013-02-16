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

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.inframesh.phost.biz.AccountBiz;
import org.inframesh.phost.model.domain.AccountInfo;
import org.inframesh.phost.model.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @since projecton
 * @version $Revision: 1.0 $Date:2009-1-2 下午03:19:08$
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
@Controller
public class AccountController extends BaseController {
	
	@Autowired
	private AccountBiz accountBiz;
	
	@RequestMapping("/login.do")
	public ModelAndView login(User user, HttpServletRequest request, HttpSession session, ModelMap model, String followup) {
		if(!accountBiz.authenize(user)) {
			model.addAttribute("errorMessage", "User not exist!");
			return new ModelAndView("login");
		}
		
		Set<String> ownerProjects = accountBiz.queryAllOwnerProject(user.getUserId());
		Set<String> committerProjects = accountBiz.queryAllCommitterProject(user.getUserId());
		Set<String> contributorProjects = accountBiz.queryAllContributorProject(user.getUserId());

		//设置session
		session.setAttribute("login", true);
		session.setAttribute("email", user.getEmail());
		session.setAttribute("userId", user.getUserId());

		session.setAttribute("ownerProjects", ownerProjects);
		session.setAttribute("committerProjects", committerProjects);
		session.setAttribute("contributorProjects", contributorProjects);
		
		if(followup != null) {
			return new ModelAndView(new RedirectView(followup));
		}
		
		return new ModelAndView("redirect:search");
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(String followup, HttpSession session, ModelMap model) {
		session.invalidate();
		
		if(followup != null) {
			return new ModelAndView(new RedirectView(followup));
		}
		
		return new ModelAndView(new RedirectView("search"));
	}
	
	@RequestMapping("/createAccount.do")
	public String createAccount(User user, String password) {
		if(!accountBiz.uniqueValidate(user)) {
			return "createAccount";
		}
		
		accountBiz.saveAccount(user);
		
		return "redirect:search";
	}
	
	@RequestMapping("/u/{email}/")
	public String showProfile(@PathVariable String email, Model model) {
		AccountInfo account = accountBiz.retrieveAccountInfo(email);
		
		model.addAttribute("account", account);
		
		return "profile";
	}
	
	@RequestMapping("/u/{email}/settings")
	public ModelAndView settings(@PathVariable String email, Model model) {
		
		AccountInfo account = accountBiz.retrieveAccountInfo(email);
		
		model.addAttribute("account", account);
		
		return new ModelAndView("settings");
	}
	
	
	

}
