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
			 <th> 
			     <div class="round4"></div> 
			     <div class="round2"></div> 
			     <div class="round1"></div> 
			     <div class="content-box"> 
			        <a onclick="cancelBubble=true;" href="${pageContext.request.contextPath}/u/${account.email}/">Account&nbsp;Profile</a> 
			     </div> 
			 </th>
			 
			 <td class="seperator"></td>
			 
			 <th class="active"> 
			     <div class="round4"></div> 
			     <div class="round2"></div> 
			     <div class="round1"></div> 
			     <div class="content-box"> 
			        <a onclick="cancelBubble=true;" href="${pageContext.request.contextPath}/u/${account.email}/settings">Settings</a>
			     </div> 
			 </th>
			 
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
 
<div id="maincol">

 

<div style="max-width:50em">
<h4>User Preferences</h4>
<form action="settings.do" method=POST>
<h6>Issue change notification</h6>
<div style="margin:0 0 2em 2em">
<p>Whenever an issue is changed by another user, send me an email:</p>
<input type=checkbox name=notify id=notify value=1 checked=checked />
<label for=notify>If I am in the issue's <b>owner</b> or <b>CC</b> fields.</label><br/>
<input type=checkbox name=notify_starred id=notify_starred value=1 checked=checked />
<label for=notify_starred>If I <b>starred</b> the issue.</label>
<br/><br/>
</div>
<h6>Source code browsing</h6>
<div style="margin:0 0 2em 2em">
<p>When I click the Source tab, show me:
<select name=pref_source_subtab>
<option value=0
selected=selected
>Checkout instructions</option>
<option value=1>Directory tree</option>
<option value=2>Recent changes</option>
</select>
</p>
</div>
<input id=submit type=submit name=btn value="Save Preferences" /> &nbsp;
</form>
</div>
</div>
<script> 
var cancelBubble = false;
function _go(url) { document.location = url; }
</script>


<%@ include file="footer.jsp" %>