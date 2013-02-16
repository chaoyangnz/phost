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
            			<a class="text-like" href="${pageContext.request.contextPath}/p/${project.name}/downloads/entry">New download</a> | 
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

<form action="${pageContext.request.contextPath}/p/${project.name}/downloads/entry.do?followup=${followup}" method="POST" style="margin: 0; padding: 0" enctype="multipart/form-data"> 
	<table cellspacing="2" cellpadding="2" border="0" class="rowmajor"> 
	 	<tr>
	 		<th class="vt">Summary:</th> 
	 		<td colspan="2" class="inplace"> 
				 <input id="summary" name="summary" value="Enter one-line summary"
				 onclick="_clearOnFirstEvent(); checksubmit()" onkeydown="_clearOnFirstEvent()"
				 onkeyup="_dirty(); checksubmit(); return true;" onfocus="_acrob(null)"/> 
	 
	 		</td> 
	 	</tr> 
	 	
	 	<tr>
	 		<th class="vt">File:</th>
	 		<td colspan="2"> 
				 <input type="file" name="uploadFile" size="35" style="width:auto" id="filefield"
				 onchange="checksubmit()" onclick="_acrob(null);checksubmit()" onfocus="_acrob(null)"/>
				 Max upload file size: 10 MB
	 
				 <div style="padding:4px 0 10px 4px"> 
				 Total upload upper limit: 100 MB. 
				 Using 10.3 KB out of 2048 MB.
				 </div> 
	 
	 		</td> 
			 <td> 
			 
			 </td> 
	 	</tr> 
	 
		 <tr>
		 
		 	<th class="vt">Labels:<br> </th> 
		 	<td colspan="2" class="labelediting"> 
				 <div id="enterrow1"> 
					 <input onfocus="_acof(event)" id="labelenter0" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
					 <input onfocus="_acof(event)" id="labelenter1" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
					 <input onfocus="_acof(event)" id="labelenter2" size="20" autocomplete="off"
					 onkeyup="_dirty(); if (this.value){_showID('enterrow2');_hideID('addrow1');} return _vallab(this);"
					 onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" />&nbsp;<u id="addrow1" class="fakelink" onclick="_acrob(null);_showInstead('enterrow2',this)">Add a row</u> 
				 </div> 
		 		<div id="enterrow2" style="display:none"> 
					 <input onfocus="_acof(event)" id="labelenter3" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
					 <input onfocus="_acof(event)" id="labelenter4" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
					 <input onfocus="_acof(event)" id="labelenter5" size="20" autocomplete="off"
					 onkeyup="_dirty(); if (this.value){_showID('enterrow3');_hideID('addrow2');} return _vallab(this);"
					 onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" />&nbsp;<u id="addrow2" class="fakelink" onclick="_acrob(null);_showInstead('enterrow3',this)">Add a row</u> 
			   </div> 
				 <div id="enterrow3" style="display:none"> 
					 <input onfocus="_acof(event)" id="labelenter6" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
					 <input onfocus="_acof(event)" id="labelenter7" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
					 <input onfocus="_acof(event)" id="labelenter8" size="20" autocomplete="off"
					 onkeyup="_dirty(); if (this.value){_showID('enterrow4');_hideID('addrow3');} return _vallab(this);"
					 onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" />&nbsp;<u id="addrow3" class="fakelink" onclick="_acrob(null);_showInstead('enterrow4',this)">Add a row</u> 
				 </div> 
				 <div id="enterrow4" style="display:none"> 
					 <input onfocus="_acof(event)" id="labelenter9" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
					 <input onfocus="_acof(event)" id="labelenter10" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
					 <input onfocus="_acof(event)" id="labelenter11" size="20" autocomplete="off"
					 onkeyup="_dirty(); if (this.value){_showID('enterrow5');_hideID('addrow4');} return _vallab(this);"
					 onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" />&nbsp;<u id="addrow4" class="fakelink" onclick="_acrob(null);_showInstead('enterrow5',this)">Add a row</u> 
				 </div> 
				 <div id="enterrow5" style="display:none"> 
					 <input onfocus="_acof(event)" id="labelenter12" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
					 <input onfocus="_acof(event)" id="labelenter13" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
					 <input onfocus="_acof(event)" id="labelenter14" size="20" autocomplete="off"
					 onkeyup="_dirty(); return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
					 name="label" value="" /> 
				 </div> 
			 </td> 
		 </tr> 
	 
		 <tr> 
			 <td colspan="3"><span id="confirmarea" class="novel" style="padding-top:5px; margin:0"> 
				 <span id="confirmmsg"></span>&nbsp;
				 	<input type="submit" value="submit" name="submit" />
				 </span> 
			 </td> 
		 </tr> 
  	</table> 
</form>


<%@ include file="../footer.jsp" %>