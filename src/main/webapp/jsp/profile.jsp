<%@ include file="signbar.jsp" %>

<div id="main" style="margin: 5px 10px">

	<!-- 
    <div class="hbox" style="height:60px;">
    	<div class="span-6">
    		<a href="/hosting/"><img src="${pageContext.request.contextPath}/images/code_sm.png" alt="Google"/></a>
    	</div>
    	<div class="span-5">
    		<div style="font-size:120%; font-weight: bold">
               ${account.email}
            </div>
    	</div>
    	<div class="span-12 right bottom" style="margin-bottom:1px; float: right; padding-top: 30px;">
    		<form action="${pageContext.request.contextPath}/search">
	            <input size=30 name="keyword" value=""/>
	            <input type=submit name=projectsearch value="Search Projects" />
	            <input type=submit name=websearch value="Search Web" />
	        </form>
    	</div>
    </div>
    -->
    
    <table width="100%"  height="60">
    	<tr>
    		<td width="230">
    			<div class="left">
		    		<a href="/hosting/"><img src="${pageContext.request.contextPath}/images/code_sm.png" alt="Google"/></a>
		    	</div>
    		</td>
    		<td width="400">
		    		<div class="span-5">
			    		<div style="font-size:120%; font-weight: bold">
			               ${account.email}
			            </div>
			    	</div>

    		</td>
    		<td>
    			<div class="span-12 right bottom" style="margin-bottom:1px; float: right; padding-top: 30px;">
		    		<form action="${pageContext.request.contextPath}/search">
			            <input size=30 name="keyword" value=""/>
			            <input type=submit name=projectsearch value="Search Projects" />
			            <input type=submit name=websearch value="Search Web" />
			        </form>
		    	</div>
    		</td>
    	</tr>
    </table>
    
    <table class="tabfolder" width="100%" > 
		 <tr> 
			 <th class="active"> 
			     <div class="round4"></div> 
			     <div class="round2"></div> 
			     <div class="round1"></div> 
			     <div class="content-box"> 
			        <a onclick="cancelBubble=true;" href="${pageContext.request.contextPath}/u/${account.email}/">Account&nbsp;Profile</a> 
			     </div> 
			 </th>

			 <c:if test="${isLogin}">
			 	<c:if test="${email eq sessionScope.email}">
			 		<td class="seperator"></td>
			 
					 <th> 
					     <div class="round4"></div> 
					     <div class="round2"></div> 
					     <div class="round1"></div> 
					     <div class="content-box"> 
					        <a onclick="cancelBubble=true;" href="${pageContext.request.contextPath}/u/${account.email}/settings">Settings</a>
					     </div> 
					 </th>
					 
			 	</c:if>
			 </c:if>

			 
			 <td class="spacer"></td> 
		
		 </tr> 
	</table> 
	
	<table class="breadcrumb" width="100%">
		<tr>
	    	<td >
	        	<div class="rround4"></div>
	            <div class="rround2"></div>
	            <div class="rround1"></div>
	        
	        </td>
	    
	    </tr>
	
	</table>
	
	<div class="vgap"></div>
	
	<table>
		<tr>
			<td><strong>Username: </strong></td>
			<td>${account.email}</td>
		</tr>
		<tr>
			<td><strong>Owner role: </strong></td>
			<td>
				<c:forEach var="ownerProject" items="${account.ownerProjects}">
		        	<a href="${pageContext.request.contextPath}/p/${ownerProject.name}/">${ownerProject.name}</a><br/>
		        </c:forEach>
			</td>
		</tr>
		
		<tr>
			<td><strong>Committer role: </strong></td>
			<td>
				<c:forEach var="committerProject" items="${account.committerProjects}">
		        	<a href="${pageContext.request.contextPath}/p/${committerProject.name}/">${committerProject.name}</a><br/>
		        </c:forEach>
			</td>
		</tr>
		
		<tr>
			<td><strong>Contributer role: </strong></td>
			<td>
				<c:forEach var="contributorProject" items="${account.contributorProjects}">
		        	<a href="${pageContext.request.contextPath}/p/${contributorProject.name}/">${contributerProject.name}</a><br/>
		        </c:forEach>
			</td>
		</tr>
	
	</table>

<%@ include file="footer.jsp" %>