<%@ include file="header.jsp" %>

<div class="section-title">
	<span>Error Console </span>
</div>

<div>
	<span class="item"><a href="${pageContext.request.contextPath}/">Phost Home</a></span> &gt; 
	<span class="item">Error Console</span>
</div>

<div class="notice" style="margin: 20px auto; padding: 0.5em 1em">
${exception.message} (${exception.class})
</div>

<c:if test="${param.debug!=null}">
<div class="error" style="margin: 20px auto; padding: 0.5em 1em">
	<c:forEach var="element" items="${exception.stackTrace}">
		${element.className}#${element.methodName}(${element.fileName}:${element.lineNumber})<br/>
	</c:forEach>
</div>
</c:if>

<%@ include file="footer.jsp" %>