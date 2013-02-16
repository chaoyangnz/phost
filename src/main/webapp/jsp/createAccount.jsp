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
	<span>Register An Account </span>
</div>


<div>
	<span class="item"><a href="/hosting/">Phost Home</a></span> &gt; 
	<span class="item"><a href="createAccount">Register</a></span>
</div>



<div id="registration_box" align=left>
	<form action="createAccount.do" method="post">
		<div align=right>
		<label>Email Address:</label>
		<input type="text" size=40 id="email" name="email" value="" />
		
		</div>
		<div align=right>
		<label>Password:</label>
		<input type="password" size=40 id="password" name="password" />
		
		</div>
		<div align=right>
		<label>Password (Confirm):</label>
		<input type="password" size=40 id="password2" name="password2" />
		
		</div>
		<div align=right>
		
		</div>
		<div align=right>
		<input type="submit" value="Register an Account" />
		</div>
		<div align=right>
		Already have an account? <a href="login">Log in</a>.
		</div>
	</form>
</div>

<%@ include file="footer.jsp" %>