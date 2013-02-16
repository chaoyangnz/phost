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
				 <a href="${pageContext.request.contextPath}/p/${project.name}/">Summary</a> 
				 </span> 
				 
				 
				 |
				 <span class="inst2"> 
				 <a href="${pageContext.request.contextPath}/p/${project.name}/updates/list">Updates</a> 
				 </span> 
				 
				 
				 |
				 <span class="inst3"> 
				 <a class="text-like" href="${pageContext.request.contextPath}/p/${project.name}/people/list">People</a> 
				 </span> 

            
            </div>
        
        </td>
    
    </tr>

</table>

<div class="vgap"></div>



<%@ include file="../footer.jsp" %>