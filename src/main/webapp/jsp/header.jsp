<%@ include file="signbar.jsp" %>

<div id="main" style="margin: 5px 10px">
 
 	<!-- 
    <div class="hbox" style="height:60px;">
    	<div class="left">
    		<a href="/hosting/"><img src="${pageContext.request.contextPath}/images/code_sm.png" alt="Google"/></a>
    	</div>
    	<div class="left">
    		<div style="font-size:120%; font-weight: bold">
                <a href="${pageContext.request.contextPath}/p/${project.name}/" style="text-decoration:none; color:#000">${project.name}</a>
            </div>
            <div style="font-size:95%; margin-top:3px">
            	<i><a href="/p/geda/" style="text-decoration:none; color:#000">${project.description}</a></i>
            </div>
    	</div>
    	<div class="right bottom" style="margin-bottom:1px; float: right; padding-top: 30px;">
    		<form action="/hosting/search">
	            <input size=30 name="keyword" value=""/>
	            <input type=submit name=projectsearch value="Search Projects" />
	            <input type=submit name=websearch value="Search Web" />
	        </form>
    	</div>
    </div>
     -->
    
    <table width="100%"  height="60">
    	<tr>
    		<td width="230" style="vertical-align:top;">
    			<div class="left">
		    		<a href="/hosting/"><img src="${pageContext.request.contextPath}/images/code_sm.png" alt="Google"/></a>
		    	</div>
    		</td>
    		<td width="400" style="vertical-align:top;">
		    		<div style="font-size:120%; font-weight: bold">
		                <a href="${pageContext.request.contextPath}/p/${project.name}/" style="text-decoration:none; color:#000">${project.name}</a>
		            </div>
		            <div style="font-size:95%; margin-top:3px">
		            	<i><a href="${pageContext.request.contextPath}/p/${project.name}/" style="text-decoration:none; color:#000">${project.summary}</a></i>
		            </div>

    		</td>
    		<td style="vertical-align:top;">
    			<div class="right bottom" style="margin-bottom:1px; float: right; text-algin:right; padding-top: 30px;">
		    		<form action="/hosting/search">
			            <input size=30 name="keyword" value=""/>
			            <input type=submit name=projectsearch value="Search Projects" />
			            <input type=submit name=websearch value="Search Web" />
			        </form>
		    	</div>
    		
    		</td>
    	</tr>
    </table>
    

    
    