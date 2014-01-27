<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<title>Login</title>
</head>
<body>

	<form:form action="." modelAttribute="accountFormValidation">
		<h1>New User Registration</h1>
		<div>
			Username:
			<form:input path="username" />
		</div>
		<div>
			Password:
			<form:password path="password" />
		</div>
		
		
	</form:form>	

</body>
</html>