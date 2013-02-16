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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-22 上午10:05:36 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
public class CommmonExceptionResolver implements HandlerExceptionResolver {


	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		if(ex != null) {
			ModelAndView modelView = new ModelAndView("/error");
			modelView.getModelMap().addAttribute("exception", ex);

			return modelView;
		}
		
		return null;
	}

}
