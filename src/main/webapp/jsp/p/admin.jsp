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
		 <th class="active"> 
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
            	
            	 <span class="inst1"><a class="text-like" href="${pageContext.request.contextPath}/p/${project.name}/admin">Project&nbsp;Summary</a></span> |
				 <span class="inst2"><a href="${pageContext.request.contextPath}/p/${project.name}/adminMembers">Project&nbsp;Members</a></span> |
				 <span class="inst5"><a href="${pageContext.request.contextPath}/p/${project.name}/adminDownloads">Downloads</a></span> |
				 <span class="inst6"><a href="${pageContext.request.contextPath}/p/${project.name}/adminWiki">Wiki</a></span> |
				 <span class="inst3"><a href="${pageContext.request.contextPath}/p/${project.name}/adminIssues">Issue&nbsp;Tracking</a></span> |
				 <span class="inst8"><a href="${pageContext.request.contextPath}/p/${project.name}/adminSource">Source</a></span> |
				 <span class="inst7"><a href="${pageContext.request.contextPath}/p/${project.name}/adminTabs">Tabs</a></span> |
				 <span class="inst4"><a href="${pageContext.request.contextPath}/p/${project.name}/adminAdvanced">Advanced</a></span>

            
            </div>
        
        </td>
    
    </tr>

</table>

<div class="vgap"></div>

<div class="section-title">
	<span>Project metadata </span>
</div>

<form action="${pageContext.request.contextPath}/p/${project.name}/admin.do" method="POST" autocomplete="off" enctype="multipart/form-data"> 
 <input type="hidden" name="name" value="${project.name}" >
 <input type="hidden" name="projectId" value="${project.id}" > 

<div class="section"> 
 Summary:<br> 
 <input name="summary" size="75" value="${project.summary}" ><br> 
 <br> 
<table cellspacing="0" cellpadding="0" border="0" class="rowmajor"><tr><td> 
 Description:<br> 
 <textarea name="description" rows="20" cols="90" wrap="soft" vlaue="">${project.description}</textarea>
 <br> 
 <br> 
 
<div> 
 
 <input type="hidden" name="has_code" id="has_code" value="checked"> 
 Code license:
 
 <select name="codeLicense" onchange="checksubmit()"> 
				 
	 <option value="" disabled="disabled">Select a license...</option> 
	 
	 <c:forEach var="codeLicense" items="${codeLicenses}">
	 	
	 	<c:choose>
        	<c:when test="${project.codeLicense != null && project.codeLicense.dictCode == codeLicense.dictCode}">
        		<option value="${codeLicense.dictCode}" selected="selected">&nbsp;${codeLicense.dictHint}</option>  
        	</c:when>
        	<c:otherwise>
        		<option value="${codeLicense.dictCode}">&nbsp;${codeLicense.dictHint}</option> 
        	</c:otherwise>
		</c:choose>
	 </c:forEach>
 
 </select> 
 
</div> 
<br> 
<div> 
 <input type="checkbox" name="has_content" id="has_content"
  onchange="checksubmit()"> 
 <label for="has_content">Use a separate content license:</label> 
 <select name="contentLicense" onchange="checksubmit()"> 
				 
	 <option value="" disabled="disabled">Select a license...</option> 
	 
	 <c:forEach var="contentLicense" items="${contentLicenses}">
	 	
	 	<c:choose>
        	<c:when test="${project.contentLicense != null && project.contentLicense.dictCode == contentLicense.dictCode}">
        		<option value="${contentLicense.dictCode}" selected="selected">&nbsp;${contentLicense.dictHint}</option>  
        	</c:when>
        	<c:otherwise>
        		<option value="${contentLicense.dictCode}">&nbsp;${contentLicense.dictHint}</option> 
        	</c:otherwise>
		</c:choose>
	 </c:forEach>
 
 </select> 
 
</div> 
<br> 
 
</td><td class="vt" style="padding: 2px"> 

<input type="hidden" name="wikihelp" id="wikihelp" value="opened" > 
 
</td></tr></table> 
<br> 
 Labels:<br> 
 <c:forEach var="label" items="${project.labels}" varStatus="status">
 	<c:if test="${status.index%3==0}">
 		 <div id="editrow1"> 
 	</c:if>
		 	<input id="labeledit1" size="20" name="label" value="${label}" /> 
	<c:if test="${(status.index+1)%3==0}">
		 </div> 
 	</c:if>
 	
 </c:forEach>

 
 
</div> 
 
 <h4>Custom project logo</h4> 
 
 <div class="section"> 
 
 
 <p> 
 <input type="radio" name="logo_select" value="uploaded_logo" 
 checked="checked"> 
 Use the logo you uploaded: <b>C:\Documents and Settings\Administrator\桌面\fat-jar.gif</b><br> 
 <input type="radio" name="logo_select" value="default_logo" 
 > 
 Use the default project logo<br> 
 </p> 
 
 <p><input name="custom_logo" size="60" type="file" /></p> 
 </div> 


 

 <input type="submit" id="savechanges" name="btn" value="Save changes" class="submit"> 
 </form> 


<%@ include file="../footer.jsp" %>