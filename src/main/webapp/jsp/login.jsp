<%@ include file="header.jsp" %>

<style type="text/css">
	#login_box{
	position:relative;
	top:10px;
	width:100%;
	}
	#login_box form{width:500px;height:auto;}
	#login_box form div{margin-bottom:1em;}
	#login_box form div.button{text-align:left;}
</style>



<div class="section-title">
	<span>Log In to Your Account </span>
</div>


<div>
	<span class="item"><a href="${pageContext.request.contextPath}/">Phost Home</a></span> &gt; 
	<span class="item"><a href="${pageContext.request.contextPath}/login">Login</a></span>
</div>

<c:if test="${errorMessage != null}">
	<div class="notice">${errorMessage}</div>
</c:if>

<div id="login_box">
	<form action="${pageContext.request.contextPath}/login.do?followup=${param.followup}" method=POST>
	<div align=right>
	<label>Email Address:</label>
	<input type="text" size=30 id="email" name="email" value="" />
	</div>
	<div align=right>
	<label>Password:</label>
	<input type="password" size=30 id="password" name="password" />
	</div>
	<input type="hidden" name="followup_page" id="followup_page" value="" />
	<div align=right>
	<input type="submit" value="Sign in" />
	</div>
	<div align=right>Don't have an account? <a href="/hosting/createAccount">Register one!</a>	</div>
	</form>
</div>


<%@ include file="footer.jsp" %>