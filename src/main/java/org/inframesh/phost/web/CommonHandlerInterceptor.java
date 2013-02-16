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
package org.inframesh.phost.web;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inframesh.phost.model.domain.ProjectInfo;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-22 上午09:03:30 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
public class CommonHandlerInterceptor extends HandlerInterceptorAdapter {
	 
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	  String regex = "^" + request.getContextPath() + "((/createProject)|(/p/[^/]+/admin)|(/u/[^/]+/settings))$";
	  if(request.getRequestURI().matches(regex)) {
		  if(request.getSession().getAttribute("email") == null) {
			  response.sendRedirect(request.getContextPath() + "/login?followup="+request.getRequestURL());
			  return false;
		  }
	  }
	  
	  return true;
	 }
	 
	 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		 if(modelAndView.getView() instanceof RedirectView ||
		    modelAndView.getViewName().startsWith("redirect:")) {//redirect的则自己负责加followup
			 return;
		 }
		 
		 String followup = null;
		 if(request.getParameter("followup") != null) {//url参数上followup优先级高
			 followup = (String) request.getParameter("followup");
		 } else {
			 followup = request.getRequestURL().toString();
		 }

		 boolean isLogin = request.getSession().getAttribute("email") != null ? true : false;
		 modelAndView.getModelMap().addAttribute("isLogin", isLogin);
		 modelAndView.getModelMap().addAttribute("followup", followup);
		 
		 boolean isOwner = false;
		 if(isLogin) {
			 String regex = "^" + request.getContextPath() + "/p/[^/]+/.*";
			 if(request.getRequestURI().matches(regex)) {
				 ProjectInfo project = (ProjectInfo) modelAndView.getModelMap().get("project");
				 Set<String> ownerProjects = (Set<String>) request.getSession().getAttribute("ownerProjects");
				 
				 //设置isOwner
				 if(project != null || ownerProjects != null) {
					 for(String ownerProject : ownerProjects) {
						 if(ownerProject.equals(project.getName())) {
							 isOwner = true;
							 break;
						 }
					 }
				 }
				 
				 
			 }
		 }
		 modelAndView.getModelMap().addAttribute("isOwner", isOwner);
		 
	 }
	 
	 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		 if(ex != null) {

//			 ex.printStackTrace();
		 }
	 }
	 
}

