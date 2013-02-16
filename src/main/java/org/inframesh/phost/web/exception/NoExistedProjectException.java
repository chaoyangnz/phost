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
package org.inframesh.phost.web.exception;

import java.text.MessageFormat;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-21 上午09:42:35 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
public class NoExistedProjectException extends RuntimeException {

	private static final long serialVersionUID = -894107381529745808L;
	
	public NoExistedProjectException(Throwable ex, String message, Object...args) {
		super(MessageFormat.format(message, args), ex);
	}
	
	public NoExistedProjectException(String message, Object...args) {
		super(MessageFormat.format(message, args));
	}

}
