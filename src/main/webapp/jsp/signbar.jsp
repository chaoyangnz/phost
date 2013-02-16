<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/core.css"/>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/component.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/DOMAssistantCompressed-2.8.js"></script>
	<script type="text/javascript">
		DOMAssistant.DOMReady(function() {
			dataGridRowsEffect();
		});
		
		
		function dataGridRowsEffect() {
			var rows = $("table.datagrid tbody tr");
		
			for(var i=0; i < rows.length; ++i) {
				//row.style.borderRight = "#ccc solid 1px";
				rows[i].onmouseover =  function() {
					var cols = this.getElementsByTagName("td");
					for(var i=0; i < cols.length; ++i) {
						cols[i].style.backgroundColor= "#f4f4ff";
						cols[i].style.color= "blue";
						this.style.cursor = "hand";
					}
				};
				
				rows[i].onmouseout =  function() {
					var cols = this.getElementsByTagName("td");
					for(var i=0; i < cols.length; ++i) {
						cols[i].style.backgroundColor= "#fff";
						cols[i].style.color= "#000";
					}
				};
			}
		}
	</script>
	<title>Phost - for project hosting service</title>
</head>
<body>

<div id="signinbar" style="text-align: right; border-bottom: #c9d7f1 solid 1px; padding-right: 10px">
	<% 
    Boolean login = (Boolean)session.getAttribute("login");
    if(login == null){// || login == false) {
    %>
        <font size="-1">
           <a href="http://code.google.com/p/phost-app/">Help</a> | <a href="${pageContext.request.contextPath}/login?followup=${followup}">Sign In</a>
        </font>
    <% } else { %>
	
    <font size="-1">
 
    <b><%=session.getAttribute("email") %></b>

     |
    <select name="menu" onchange="javascript:window.location=this.options[this.selectedIndex].value;">
        <option selected="selected" disabled="disabled">My Projects...</option>
        <option disabled="disabled"> Owner of:</option>
        <c:forEach var="projectName" items="${sessionScope.ownerProjects}">
        	<c:choose>
        	<c:when test="${project != null && projectName == project.name}">
        		<option value="${pageContext.request.contextPath}/p/${projectName}/" selected="selected"> - ${projectName}</option>
        	</c:when>
        	<c:otherwise>
        		<option value="${pageContext.request.contextPath}/p/${projectName}/"> - ${projectName}</option>
        	</c:otherwise>
        	</c:choose>
        </c:forEach>
         
        <option disabled="disabled"> Member of:</option>
    </select>

    | <a href="http://code.google.com/p/phost-app/">Help</a>
    | <a href="${pageContext.request.contextPath}/u/<%=session.getAttribute("email") %>/">My Account Profile</a>
    | <a href="${pageContext.request.contextPath}/u/<%=session.getAttribute("email") %>/settings">Settings</a>
    | <a href="${pageContext.request.contextPath}/logout.do?followup=${followup}">Sign Out</a>
     
    </font>
	<% } %>
</div>

<!--[if IE 6]>
<div style="text-align:center;">
<span style="background-color: #dfe">
Support browsers with better web standard compatibility, try <a href="http://www.firefox.com">Firefox</a> or <a href="http://www.google.com/chrome">Google Chrome</a>, <a href="http://www.microsoft.com/windows/internet-explorer/default.aspx">Internet Explorer 7+</a>.
</span>
</div>
<![endif]-->

<div class="vgap"></div>

