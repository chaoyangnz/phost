<%@ include file="../header.jsp" %>

<table class="tabfolder" width="100%" > 
 <tr> 
	 <th> 
	     <div class="round4"></div> 
	     <div class="round2"></div> 
	     <div class="round1"></div> 
	     <div class="content-box"> 
	        <a href="${pageContext.request.contextPath}/p/${project.name}/">Project&nbsp;Home</a> 
	     </div> 
	 </th>
	 
	 <td class="seperator"></td>
	 
	 <th> 
	     <div class="round4"></div> 
	     <div class="round2"></div> 
	     <div class="round1"></div> 
	     <div class="content-box"> 
	        <a href="${pageContext.request.contextPath}/p/${project.name}/downloads/list">Downloads</a>
	     </div> 
	 </th>
	 
	 <td class="seperator"></td>
	
	
	 <th> 
		 <div class="round4"></div> 
		 <div class="round2"></div> 
		 <div class="round1"></div> 
		 <div class="content-box"> 
			<a href="${pageContext.request.contextPath}/p/${project.name}/w/list">Wiki</a>	
	     </div> 
	 </th>
	 
	 <td class="seperator"></td> 
	 
	 <th class="active"> 
		 <div class="round4"></div> 
		 <div class="round2"></div> 
	     <div class="round1"></div>
		 <div class="content-box"> 
			<a href="${pageContext.request.contextPath}/p/${project.name}/issues/list">Issues</a>	 
	     </div> 
	 </th>
	 
	 <td class="seperator"></td> 
	 
	 <th> 
		  <div class="round4"></div> 
		  <div class="round2"></div> 
	      <div class="round1"></div>
		  <div class="content-box"> 
			<a href="${pageContext.request.contextPath}/p/${project.name}/source/checkout">Source</a>	 
	      </div> 
	 </th>
	 
	 <c:if test="${isOwner}">
		 <td class="seperator"></td> 
		 <th> 
			  <div class="round4"></div> 
			  <div class="round2"></div> 
		      <div class="round1"></div>
			  <div class="content-box"> 
				<a href="${pageContext.request.contextPath}/p/${project.name}/admin">Administer</a>	 
		      </div> 
		 </th>
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
            
            <div class="content-box">
            	
            	<c:if test="${isLogin}">
            		<c:if test="${isOwner}">
            			<a href="${pageContext.request.contextPath}/p/${project.name}/issues/entry">New issue</a> | 
            		</c:if>
            	</c:if>
            
            	 <span>Search</span>
                 <form action="list" method="GET" style="display:inline">
                 <select id="can" name="can">
                 <option disabled="disabled">Search within:</option>
                 
                 <option value="1" >&nbsp;All issues</option>
                 <option value="3" >&nbsp;Open issues</option>
                 <option value="2" selected="selected">&nbsp;New issues</option>
                 
                 
                 <option value="4" >&nbsp;Deprecated pages</option>
                 
                 </select>
                 <span>for</span>
                 <span id="qq"><input type="text" size="38" id="q" name="q" value=""/></span>
                 
                 <span id="search_colspec"><input type="hidden" name="colspec" value="Filename Summary Uploaded Size DownloadCount" /></span>
                 <input type="submit" value="Search" />
                 </form>

            
            </div>
        
        </td>
    
    </tr>

</table>

<div class="vgap"></div>

Not implementation


<%@ include file="../footer.jsp" %>