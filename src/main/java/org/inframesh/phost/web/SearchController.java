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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @since phost-web
 * @version $Revision: 1.0 $Date:2010-4-7 下午04:51:37 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
@Controller
public class SearchController extends BaseController {
	
	@RequestMapping("/")
	public String index(String followup, ModelMap response) {
		return "search";
	}
	
	@RequestMapping("/search")
	public String search(String keyword, ModelMap response) {
		return "search";
	}
}
