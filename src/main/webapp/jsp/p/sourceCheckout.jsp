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
	 
	 <th class="active"> 
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
            
            	 <span class="inst1"><a class="text-like" href="/p/google-web-toolkit/source/checkout">Checkout</a></span> |
				 <span class="inst2"><a href="/p/google-web-toolkit/source/browse/">Browse</a></span> |
				 <span class="inst3"><a href="/p/google-web-toolkit/source/list">Changes</a></span> |
				 
				 <form action="http://www.google.com/codesearch" method="get" style="display:inline"
				 onsubmit="document.getElementById('codesearchq').value = document.getElementById('origq').value + ' package:http://google-web-toolkit\\.googlecode\\.com'"> 
				 <input type="hidden" name="q" id="codesearchq" value=""> 
				 <input maxlength="2048" size="38" id="origq" name="origq" value="" title="Google Code Search" style="font-size:92%">&nbsp;<input type="submit" value="Search Trunk" name="btnG" style="font-size:92%"> 
				 
				 
				 
				 </form> 

            
            </div>
        
        </td>
    
    </tr>

</table>

<div class="featured-bg" style="width: 560px; margin: 0 auto;">
	 <div class="content-box" style="font-size: 0.9em; line-height: 1em;"> 
	 	<span><strong>How-to:</strong> Explore this project's source code by clicking the "Browse" and "Changes" links above.</span>
	 </div> 
	 <div class="round1"></div> 
	 <div class="round2"></div> 
	 <div class="round4"></div> 
</div>

<div class="vgap"></div>

<div>
	<h6>Command-line access</h6> 
	<div class="indent"> 
	 
	 
	 <p>Use this command to anonymously check out the latest project source code:</p> 
	 <div class="basic-bg" style="width: 700px; font-size: .8em"> 
		 <div class="round4"></div> 
		 <div class="round2"></div> 
		 <div class="round1"></div> 
			 <div class="content-box"> 
			 <tt># Non-members may check out a read-only working copy anonymously over HTTP.</tt><br> 
			 <tt id="checkoutcmd">svn checkout <strong><em>http</em></strong>://<%=request.getRemoteHost() %>:<%=request.getRemotePort() %>${pageContext.request.contextPath}/svn/trunk/ ${project.name}</tt> 
			 </div> 
		 <div class="round1"></div> 
		 <div class="round2"></div> 
		 <div class="round4"></div> 
		 </div> 
	 </div> 
</div> 
	
<div class="vgap"></div>
	
<h6>GUI and IDE access</h6> 
<div class="indent"> 
	<p>This project's Subversion repository may be accessed using many different
	<a href="http://subversion.apache.org/packages.html">client programs and plug-ins</a>.
	 See your client's documentation for more information.</p> 
</div>

<%@ include file="../footer.jsp" %>