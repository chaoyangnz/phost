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
package org.inframesh.phost.model;

/**
 * 
 * @since phost
 * @version $Revision: 1.0 $Date:2009-1-8 下午03:28:27$
 * 
 * @author <a href="mailto:josh.yoah@gmail.com">杨超</a>
 */
public class MyBean {
	private String foo = "Default Foo";

	public String getFoo() {
		return (this.foo);
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	private int bar = 0;

	public int getBar() {
		return (this.bar);
	}

	public void setBar(int bar) {
		this.bar = bar;
	}
}