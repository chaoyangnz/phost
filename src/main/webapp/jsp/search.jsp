<%@ include file="signbar.jsp" %>

<div align=center>
	<a href="${pageContext.request.contextPath}/">
		<img src="${pageContext.request.contextPath}/images/code_sm.png" />
	</a>
</div>


	<table align=center style="margin:3em auto;">
	<tr><td>
	<form action="${pageContext.request.contextPath}/search" method=get>
		<p align=center style="padding:0; margin:0">
		<input type=input name=q size=30 />
		<input type=submit name=btn value="Search Projects" />
		</p>
	</form>
	</td></tr>
	<tr><td align=center>
	<div style="font-size:130%; font-weight:bold; color:blue; margin:1em; text-align:center"
	title="We do &nbsp; &nbsp;">
	
	<a style="text-decoration:none" href="http://www.google.com/search?q=release early release often">Release early, release often</a>
	
	</div>
	</td></tr>
	</table>

	<table align=center style="margin:2em auto">
	<tr><td><div>Example project tags:</div></td></tr>
	<tr><td align=center>
	<div id=popular>
		<table>
		<tr>
		<td width=20%><a href="hosting/search?q=label%3aPython">Python</a></td>
		<td width=20%><a href="hosting/search?q=label%3aCPlusPlus">CPlusPlus</a></td>
		<td width=20%><a href="hosting/search?q=label%3aJava">Java</a></td>
		<td width=20%><a href="hosting/search?q=label%3aGoogle">Google</a></td>
		<td width=20%><a href="hosting/search?q=label%3aStudent">Student</a></td>
		<td width=1%><a href="hosting/search?q=label%3aGraphics">Graphics</a></td>
		</tr>
		<tr>
		<td><a href="hosting/search?q=label%3aUtility">Utility</a></td>
		<td><a href="hosting/search?q=label%3aLinux">Linux</a></td>
		<td><a href="hosting/search?q=label%3aWindows">Windows</a></td>
		<td><a href="hosting/search?q=label%3aPlugIn">PlugIn</a></td>
		<td><a href="hosting/search?q=label%3aWeb">Web</a></td>
		<td><a href="hosting/search?q=label%3aDatabase">Database</a></td>
		</tr>
		<tr>
		<td><a href="hosting/search?q=label%3aChat">Chat</a></td>
		<td><a href="hosting/search?q=label%3aStable">Stable</a></td>
		<td><a href="hosting/search?q=label%3aAudio">Audio</a></td>
		<td><a href="hosting/search?q=label%3aDevTool">DevTool</a></td>
		<td><a href="hosting/search?q=label%3aXML">XML</a></td>
		<td><a href="hosting/search?q=label%3aCSharp">CSharp</a></td>
		</tr>
		
		</table>
		</div>
		</td></tr>
	</table>

	
	<div style="text-align:center;margin:1em">
	
	
	Want to take part in Phost? 
	<% 
    if(login == null){// || login == false) {
    %>
	<a href="${pageContext.request.contextPath}/login">Sign in</a> or <a href="${pageContext.request.contextPath}/createAccount">create a new account</a>.
	<% } else {%>
	<a href="${pageContext.request.contextPath}/createProject">create a project</a>.
	<% } %>
	</div>



<%@ include file="footer.jsp" %>
