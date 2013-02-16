<%@ include file="../header.jsp" %>

<table class="tabfolder" width="100%" > 
 <tr> 
	 <th class="active"> 
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
	 
	 <th> 
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
            
            	 <span class="inst1"> 
				 <a class="text-like" href="${pageContext.request.contextPath}/p/${project.name}/">Summary</a> 
				 </span> 
				 
				 
				 |
				 <span class="inst2"> 
				 <a href="${pageContext.request.contextPath}/p/${project.name}/updates/list">Updates</a> 
				 </span> 
				 
				 
				 |
				 <span class="inst3"> 
				 <a href="${pageContext.request.contextPath}/p/${project.name}/people/list">People</a> 
				 </span> 

            
            </div>
        
        </td>
    
    </tr>

</table>

<div class="vgap"></div>

<div class="hbox">
	<div class="content-box" style="width: 75%;">
	${project.description}

    </div>
	
    <div class="vbox" style="width: 20%; float:right">
    
    	<% if(login!=null) { %>
    	
    	<div class="basic-bg" >
            <div class="round4"></div>
            <div class="round2"></div>
            <div class="round1"></div>
            
            <div class="content-box">
            
          	<img src="${pageContext.request.contextPath}/images/star_off.gif"/ style="cursor:pointer" onclick="">  
          	<span>Star this project</span>
            </div>
            
            <div class="round1"></div>
            <div class="round2"></div>
            <div class="round4"></div>
        </div>
        
        <div class="vgap"></div>
        <% } %>

        <div class="basic-bg" >
            <div class="round4"></div>
            <div class="round2"></div>
            <div class="round1"></div>
            
            <div class="content-box">
            
           <strong>Activity:</strong>   High<br/>
		   <strong>Code license:</strong> <br/>
		   <a href="${project.codeLicense.note1}">${project.codeLicense.dictHint}<br/></a>
		   <strong>Labels:</strong> <br/>
		   		<c:forEach var="label" items="${project.labels}" varStatus="status">
		   			<c:if test="${not empty label}">
		   				<span><a href="${pageContext.request.contextPath}/?q=label:${label}">${label}</a>,&nbsp;</span>
		   			</c:if>	
		   		</c:forEach>
            </div>
            
            <div class="round1"></div>
            <div class="round2"></div>
            <div class="round4"></div>
        </div>
        
        <div class="vgap"></div>
        
        <div class="featured-bg" >
            <div class="round4"></div>
            <div class="round2"></div>
            <div class="round1"></div>
            
            <div class="content-box">
            
            <strong>Featured downloads:</strong>
            <br/>
            
            <c:forEach var="featuredDownload" items="${featuredDownloads}">
            	<a href="${pageContext.request.contextPath}/p/${project.name}/downloads/detail/${featuredDownload.downloadId}">${featuredDownload.fileName}</a><br/>
            </c:forEach>

            </div>
            
            <div class="round1"></div>
            <div class="round2"></div>
            <div class="round4"></div>
        </div>
        
        
        <div class="vgap"></div>
        
        <div class="basic-bg" >
            <div class="round4"></div>
            <div class="round2"></div>
            <div class="round1"></div>
            
            <div class="content-box">
            
            <strong>External Link:</strong>
            <br/>

            </div>
            
            <div class="round1"></div>
            <div class="round2"></div>
            <div class="round4"></div>
        </div>

        
        <div class="vgap"></div>
        
        <div class="basic-bg" >
            <div class="round4"></div>
            <div class="round2"></div>
            <div class="round1"></div>
            
            <div class="content-box">
            
	           <strong>Owners:</strong><br/>
			   <c:forEach var="owner" items="${project.owners}">
			   		<a href="${pageContext.request.contextPath}/u/${owner.email}/">${owner.email}</a>
			   </c:forEach>
			   <br/><br/>
			   <a href="${pageContext.request.contextPath}/p/${project.name}/people/list">People details &raquo;</a>
		   
		   </div>
            
            <div class="round1"></div>
            <div class="round2"></div>
            <div class="round4"></div>
        </div>
        
     </div>  
        
</div>

<%@ include file="../footer.jsp" %>