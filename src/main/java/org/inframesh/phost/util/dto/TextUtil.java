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
package org.inframesh.phost.util.dto;

import java.util.Collection;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2010-4-7 下午02:18:23 $
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
public final class TextUtil {
	public static String join(String[] arr, String seperator) {
		StringBuffer sbf = new StringBuffer();
		for(String element : arr) {
			sbf.append(seperator).append(element);
		}
		sbf.delete(0, seperator.length());
		
		return sbf.toString();
	}
	
	public static String join(Collection<String> collection, String seperator) {
		StringBuffer sbf = new StringBuffer();
		for(String element : collection) {
			sbf.append(seperator).append(element);
		}
		sbf.delete(0, seperator.length());
		
		return sbf.toString();
	}
	
	public static String camel2Underline(String name) {
		StringBuffer bf = new StringBuffer();
		
		char[] seq = name.toCharArray();
		int from = 0;
		for(int i=0; i<seq.length; ++i) {
			if(seq[i]>='A' && seq[i]<='Z') {
				int to = i;
				bf.append(name.substring(from, to).toUpperCase());
				bf.append('_');
				from = to;
			}
		}
		bf.append(name.substring(from, seq.length).toUpperCase());
		
		return bf.toString();
	}
	
	public static String underline2Camel(String name) {
		StringBuffer bf = new StringBuffer();
		String[] segments = name.split("_");
		for(int i=0; i < segments.length; ++i) {
			String segment = segments[i].toLowerCase();
			if(i==0) {
				bf.append(segment);
			} else {
				bf.append(segment.substring(0, 1).toUpperCase());
				bf.append(segment.substring(1));
			}
		}
		
		return bf.toString();
	}
}
