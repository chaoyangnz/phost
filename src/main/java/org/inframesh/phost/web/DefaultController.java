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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2009-1-9 上午09:29:24$
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
@Controller
public class DefaultController extends BaseController {

	@RequestMapping("/{path}")
	public String defaultHandle(@PathVariable String path, ModelMap response) {
		return path;
	}
}
