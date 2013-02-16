<%@ include file="../header.jsp" %>

<table class="tabfolder" width="100%" > 
 <tr> 
	 <th> 
	     <div class="round4"></div> 
	     <div class="round2"></div> 
	     <div class="round1"></div> 
	     <div class="content-box"> 
	        <a onclick="cancelBubble=true;" href="${pageContext.request.contextPath}/p/${project.name}/">Project&nbsp;Home</a> 
	     </div> 
	 </th>
	 
	 <td class="seperator"></td>
	 
	 <th class="active"> 
	     <div class="round4"></div> 
	     <div class="round2"></div> 
	     <div class="round1"></div> 
	     <div class="content-box"> 
	        <a onclick="cancelBubble=true;" href="${pageContext.request.contextPath}/p/${project.name}/downloads/list">Downloads</a>
	     </div> 
	 </th>
	 
	 <td class="seperator"></td>
	
	
	 <th> 
		 <div class="round4"></div> 
		 <div class="round2"></div> 
		 <div class="round1"></div> 
		 <div class="content-box"> 
			<a onclick="cancelBubble=true;" href="${pageContext.request.contextPath}/p/${project.name}/w/list">Wiki</a>	
	     </div> 
	 </th>
	 
	 <td class="seperator"></td> 
	 
	 <th> 
		 <div class="round4"></div> 
		 <div class="round2"></div> 
	     <div class="round1"></div>
		 <div class="content-box"> 
			<a onclick="cancelBubble=true;" href="${pageContext.request.contextPath}/p/${project.name}/issues/list">Issues</a>	 
	     </div> 
	 </th>
	 
	 <td class="seperator"></td> 
	 
	 <th> 
		  <div class="round4"></div> 
		  <div class="round2"></div> 
	      <div class="round1"></div>
		  <div class="content-box"> 
			<a onclick="cancelBubble=true;" href="${pageContext.request.contextPath}/p/${project.name}/source/checkout">Source</a>	 
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
            			<a href="${pageContext.request.contextPath}/p/${project.name}/downloads/entry">New download</a> |  
            		</c:if>
            	</c:if>
            
            	 <span>Search</span>
                 <form action="list" method="GET" style="display:inline">
                 <select id="can" name="can">
                 <option disabled="disabled">Search within:</option>
                 
                 <option value="1" >&nbsp;All downloads</option>
                 <option value="3" >&nbsp;Featured downloads</option>
                 <option value="2" selected="selected">&nbsp;Current downloads</option>
                 
                 
                 <option value="4" >&nbsp;Deprecated downloads</option>
                 
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

<div class="section-title" style="background: #c3d9ff">
	Download: <span style="font-size: 0.8em; font-weight: normal">${download.summary} </span>
</div>

<div>
	<div style="border-right: solid 2px #c3d9ff; width: 30%;float: left">
	<br/>
	<strong>Uploaded by: </strong> ${download.uploaded} <br/>
	<strong>Uploaded:</strong> 	Jan 23, 2010 <br/>
	<strong>Downloads: </strong> ${download.downloadCount}<br/>
	<c:forEach var="label" items="${download.labels}">
		<a href="${pageContext.request.contextPath}/p/${project.name}/downloads/list?q=label:${label}">${label}</a>
	</c:forEach>
	</div>
	<div style="width: 68%;float: right">
	
		<div class="vgap"></div>
        
        <div class="featured-bg" style="width: 40%; font-size: 1.2em; line-height: 2.5em;" >
            <div class="round4"></div>
            <div class="round2"></div>
            <div class="round1"></div>
            
            <div class="content-box">
            
            <strong>
            <img alt="" src="${pageContext.request.contextPath}/images/dl_arrow.gif"/>&nbsp;
            <a href="${pageContext.request.contextPath}/p/${project.name}/downloads/files/${download.downloadId}">${download.fileName}</a>
            </strong>
            <br/>

            </div>
            
            <div class="round1"></div>
            <div class="round2"></div>
            <div class="round4"></div>
        </div>
	</div>

</div>

<div class="vgap"></div>

<c:if test="${isOwner}">

<div class="section-title" style="margin-top: 40px;">
	<span>Edit download </span>
</div>

<div>
<br/>
<form action="${pageContext.request.contextPath}/p/${project.name}/downloads/detail.do" method="POST" enctype="multipart/form-data"> 
 <input type="hidden" name="downloadId" value="${download.downloadId}" > 
 <table cellpadding="0" cellspacing="0" border="0"> 
 <tr> 
 <td class="vt"> 
 <table cellspacing="2" cellpadding="2" border="0" class="rowmajor" width="100%"> 
 <tr><th>Summary:</th><td class="inplace" colspan="2"> 
 <input name="summary" value="${download.summary}" size="75"/></td> 
 </tr> 
 <tr><th class="vt">Labels:<br> 
 </th> 
 <td class="labelediting" colspan="2"> 
 	<c:forEach var="label" items="${download.labels}" varStatus="status">
	 	<c:if test="${status.index%3==0}">
	 		 <div id="editrow1"> 
	 	</c:if>
			 	<input id="labeledit1" size="20" name="label" value="${label}" /> 
		<c:if test="${(status.index+1)%3==0}">
			 </div> 
	 	</c:if>
 	
 	</c:forEach>
 </td> 
 </tr> 
 
 <tr> 
 <td colspan="3"><span id="confirmarea" class="novel" style="padding-top:5px; margin:0"> 
 <span id="confirmmsg"></span>&nbsp;
 
 </span> 
 </td> 
 </tr> 
 </table> 
 <input type="submit" id="submit" name="btn" value="Save changes" onfocus="_acrob(null)" > 

 <input type="button" id="delete" onclick="location.href='delete?name=maven-fat-jar-1.0.0.zip'" name="nobtn" value="Delete download" onfocus="_acrob(null)" > 
 
 </td> 

 </tr> 
 </table> 
 </form>
</div>


</c:if>

<%@ include file="../footer.jsp" %>