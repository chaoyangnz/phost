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
	
	
	 <th class="active"> 
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
            	
            	<c:if test="${isLogin}">
            		<c:if test="${isOwner}">
            			<a class="text-like" href="${pageContext.request.contextPath}/p/${project.name}/w/entry">New page</a> | 
            		</c:if>
            	</c:if>
            
            	 <span>Search</span>
                 <form action="list" method="GET" style="display:inline">
                 <select id="can" name="can">
                 <option disabled="disabled">Search within:</option>
                 
                 <option value="1" >&nbsp;All wiki pages</option>
                 <option value="3" >&nbsp;Featured pages</option>
                 <option value="2" selected="selected">&nbsp;Current pages</option>
                 
                 
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

<table cellpadding="0" cellspacing="0" border="0"> 
 <tr><td class="vt"> 
 <table cellspacing="2" cellpadding="2" border="0" class="rowmajor"> 
 <tr><th class="vt">Page&nbsp;Name:</th> 
 <td> 
 
 <input size="30" id="summary" name="pagename"
 
 value="PageName"
 onclick="_clearOnFirstEvent()" onkeydown="_clearOnFirstEvent()"
 /> 
 
 
 </td> 
 </tr> 
 <tr> 
 <th class="vt">Content:<br> 
 
 </th> 
 <td colspan="2"> 
 <textarea cols="90" rows="28" wrap="soft" name="content" id="content">#summary One-sentence summary of this page.
 
= Introduction =
 
Add your content here.
 
 
= Details =
 
Add your content here.  Format your content with:
  * Text in *bold* or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages
</textarea> 
 
 </td> 
 </tr> 
 <tr><th class="vt">Labels:<br> 
 
 </th> 
 <td colspan="2" class="labelediting"> 
 <div id="enterrow1"> 
 <input onfocus="_acof(event)" id="labelenter0" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 <input onfocus="_acof(event)" id="labelenter1" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 <input onfocus="_acof(event)" id="labelenter2" size="20" autocomplete="off"
 onkeyup="if (this.value){_showID('enterrow2');_hideID('addrow1');} return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" />&nbsp;<u id="addrow1" class="fakelink" onclick="_acrob(null);_showInstead('enterrow2',this)">Add a row</u> 
 </div> 
 <div id="enterrow2" style="display:none"> 
 <input onfocus="_acof(event)" id="labelenter3" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 <input onfocus="_acof(event)" id="labelenter4" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 <input onfocus="_acof(event)" id="labelenter5" size="20" autocomplete="off"
 onkeyup="if (this.value){_showID('enterrow3');_hideID('addrow2');} return _vallab(this);"
 onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" />&nbsp;<u id="addrow2" class="fakelink" onclick="_acrob(null);_showInstead('enterrow3',this)">Add a row</u> 
 </div> 
 <div id="enterrow3" style="display:none"> 
 <input onfocus="_acof(event)" id="labelenter6" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 <input onfocus="_acof(event)" id="labelenter7" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 <input onfocus="_acof(event)" id="labelenter8" size="20" autocomplete="off"
 onkeyup="if (this.value){_showID('enterrow4');_hideID('addrow3');} return _vallab(this);"
 onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" />&nbsp;<u id="addrow3" class="fakelink" onclick="_acrob(null);_showInstead('enterrow4',this)">Add a row</u> 
 </div> 
 <div id="enterrow4" style="display:none"> 
 <input onfocus="_acof(event)" id="labelenter9" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 <input onfocus="_acof(event)" id="labelenter10" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 <input onfocus="_acof(event)" id="labelenter11" size="20" autocomplete="off"
 onkeyup="if (this.value){_showID('enterrow5');_hideID('addrow4');} return _vallab(this);"
 onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" />&nbsp;<u id="addrow4" class="fakelink" onclick="_acrob(null);_showInstead('enterrow5',this)">Add a row</u> 
 </div> 
 <div id="enterrow5" style="display:none"> 
 <input onfocus="_acof(event)" id="labelenter12" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 <input onfocus="_acof(event)" id="labelenter13" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 <input onfocus="_acof(event)" id="labelenter14" size="20" autocomplete="off"
 onkeyup="return _vallab(this);" onblur="_vallab(this); _RC(this,'hasfocus')"
 name="label" value="" /> 
 </div> 
 </td> 
 </tr> 
 <tr> 
 <td colspan="3"><span id="confirmarea" class="novel" style="padding-top:5px; margin:0"> 
 <span id="confirmmsg"></span>&nbsp;
 
 </span> 
 </td> 
 </tr> 
 <tr class="ifExpand"><th class="vt">Commit&nbsp;Log:</th> 
 <td colspan="2"> 
 <textarea cols="90" rows="5" wrap="soft" name="svn_log" id="svn_log">Created wiki page through web user interface.</textarea> 
 
 </td> 
 </tr> 
 </table>


<%@ include file="../footer.jsp" %>