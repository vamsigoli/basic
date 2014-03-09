<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<html>

<body>

	Welcome <sec:authentication property="principal.username"/>
	<sec:authentication property="authorities" var="roles" scope="page" />
	Your roles are:
	<ul>
		<c:forEach var="role" items="${roles}">
			<li>${role}</li>
		</c:forEach>
	</ul>

	<c:if test="${actionmessage != null}">
		<c:out value="${actionmessage}" />
	</c:if>
	<p>
		Please update information using this link <a
			href="<c:url value="/updateusers/getaccount"/>">Update User</a>
	</p>

	<p>
		<a href="<c:url value="/logout"/>">Logout</a>
</body>
</html>