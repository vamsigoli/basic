<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Roster</title>
</head>
<body>
	<h1>Roster</h1>
	<ul>
		<c:forEach var="member" items="${memberList}" varStatus="status">
			<li><a href="member.do?id=${status.index}"> <c:out
						value="${member}"></c:out>
			</a></li>
		</c:forEach>
	</ul>
</body>
</html>