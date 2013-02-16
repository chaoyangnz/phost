<%@ include file="header.jsp" %>

<style type="text/css">
	#registration_box{
	position:relative;
	top:10px;
	width:99%;
	}
	#registration_box form{width:500px;height:auto;}
	#registration_box form div{margin-bottom:1em;}
	#registration_box form div.button{text-align:left;}
</style>


<div class="section-title">
	<span>Create Project </span>
</div>

<div class="vbox">
		<form action="createProject.do" method="POST" onsubmit="s=document.getElementById('submit');s.value='Creating Project...'; s.disabled='disabled'"> 
			 <input type="hidden" name="token" value="770b7cb9e952a5c8bbaff02b845e3905" > 
			 <div>Project name:</div> 
			 <div> 
				 <input id="projectname" name="name" size="30" value="" onkeyup="checkprojectname()"/> 
				 <span id="projectnamefeedback"></span> 
			 </div> 
			 
			 <div style="padding-top: 2ex;">Project summary:</div> 
			 <div> 
			 	<input id="summary" name="summary" size="75" value="" onkeyup="checksubmit()"/><br> 
			 </div> 
			 
			 <div style="padding-top: 2ex;">Project description:</div> 
			 	<textarea cols="62" rows="13" name="description" id="description" onkeyup="checksubmit()"></textarea> 
			 </div> 
			 
			 
			 <div style="padding-top: 2ex;"> 
				 Version control system:
				 <select name="versionControl" id="versionControl" onchange="checksubmit()"> 
					 <option value="" selected="selected" disabled="disabled">Select a version control system...</option> 
					 
					 <c:forEach var="versionControl" items="${versionControls}">
					 	<option value="${versionControl.dictCode}">&nbsp;${versionControl.dictHint}</option> 
					 </c:forEach>
				 </select> 
			 
			 </div> 
			 
			 <div style="padding-top: 2ex;"> 
			 
			 	<input type="hidden" name="has_code" id="has_code" value="checked"/> 
			 	Source code license:
			 
				 <select name="codeLicense" onchange="checksubmit()"> 
				 
					 <option value="" selected="selected" disabled="disabled">Select a license...</option> 
					 
					 <c:forEach var="codeLicense" items="${codeLicenses}">
					 	<option value="${codeLicense.dictCode}">&nbsp;${codeLicense.dictHint}</option> 
					 </c:forEach>
				 
				 </select> 
			 
			 </div> 
			 <div style="padding-top: 2ex;"> 
				 <input type="checkbox" name="has_content" id="has_content" onchange="checksubmit()" onclick="contentLicense.disabled=!contentLicense.disabled"> 
				 <label for="has_content">Use a separate content license:</label> 
				 <select name="contentLicense" onchange="checksubmit()" disabled="disabled"> 
					 <option value="" selected="selected" disabled="disabled">Select a license...</option> 
					 
					 <c:forEach var="contentLicense" items="${contentLicenses}">
					 	<option value="${contentLicense.dictCode}">&nbsp;${contentLicense.dictHint}</option> 
					 </c:forEach>
				 </select> 
			 </div> 
			 
			 <div style="padding-top: 2ex;">Project labels:</div> 
			 <div id="editrow1"> 
				 <input onfocus="_acof(event)" id="labeledit0" size="20" name="label" value="" /> 
				 <input onfocus="_acof(event)" id="labeledit1" size="20" name="label" value="" /> 
				 <input onfocus="_acof(event)" id="labeledit2" size="20" name="label" value="" /> 
			 </div> 
			 <div class="vgap"></div>
			 <div id="editrow2"> 
				 <input onfocus="_acof(event)" id="labeledit3" size="20" name="label" value="" /> 
				 <input onfocus="_acof(event)" id="labeledit4" size="20" name="label" value="" /> 
				 <input onfocus="_acof(event)" id="labeledit5" size="20" onkeyup="if (this.value){_showID('editrow3');_hideID('addrow2');}return true;"
				 name="label" value="" /> 
				 <u class="fakelink" id="addrow2" onclick="_showInstead('editrow3', this)">Add row</u> 
			</div> 
			<div id="editrow3" style="display:none"> 
				 <input onfocus="_acof(event)" id="labeledit6" size="20" name="label" value="" /> 
				 <input onfocus="_acof(event)" id="labeledit7" size="20" name="label" value="" /> 
				 <input onfocus="_acof(event)" id="labeledit8" size="20" onkeyup="if (this.value){_showID('editrow4');_hideID('addrow3');}return true;" name="label" value="" /> 
				 <u class="fakelink" id="addrow3" onclick="_showInstead('editrow4', this)">Add row</u> 
			 </div> 
			 <div id="editrow4" style="display:none"> 
				 <input onfocus="_acof(event)" id="labeledit9" size="20"
				 name="label" value="" /> 
				 <input onfocus="_acof(event)" id="labeledit10" size="20"
				 name="label" value="" /> 
				 <input onfocus="_acof(event)" id="labeledit11" size="20"
				 onkeyup="if (this.value){_showID('editrow5');_hideID('addrow4');}return true;"
				 name="label" value="" /> 
				 <u class="fakelink" id="addrow4" onclick="_showInstead('editrow5', this)">Add row</u> 
			 </div> 
			 <div id="editrow5" style="display:none"> 
				 <input onfocus="_acof(event)" id="labeledit12" size="20" name="label" value="" /> 
				 <input onfocus="_acof(event)" id="labeledit13" size="20" name="label" value="" /> 
				 <input onfocus="_acof(event)" id="labeledit14" size="20" name="label" value="" /> 
			 </div> 
			 
			 <div style="padding-top: 2ex"> 
			 
			 <input type="submit" id="submit" name="btn" value="Create project" /> 
			 </div> 
		</form>
	</div>
</div>


<%@ include file="footer.jsp" %>