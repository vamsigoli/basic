<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Update User Details</title>
</head>
<body>
	<form:form action="updateuser" modelAttribute="accountFormValidation">

		<form:errors path="*">
			<div>
				<spring:message code="error.global" />
			</div>
		</form:errors>


		<h1>Update User Registration</h1>
		<div>
			Username(Un Editable): 
			<form:input readonly="true" path="username" />
			
		</div>




		<div>
			Password:
			<form:password path="password" />
		</div>
		<div>
			Confirm password:
			<form:password path="confirmPassword" />
		</div>
		
		<div>
				<form:errors path="password" htmlEscape="false" />
			</div>
		
		
		
		<div>
			E-mail address:
			<form:input path="email" />
		</div>
		<div>
			First name:
			<form:input path="firstName" />
		</div>
		<div>
			Last name:
			<form:input path="lastName" />
		</div>
		<div>
			<form:checkbox id="marketingOk" path="marketingOk" />
			Please send me product updates by e-mail.
		</div>
		<div>
			<form:checkbox id="acceptTerms" path="acceptTerms" />
			I accept the <a href="#">terms of use</a>.
		</div>
		<div>
			<input type="submit" value="Register" />
		</div>
		<input type="hidden"                        
        name="${_csrf.parameterName}"
        value="${_csrf.token}"/>
        
        
        <form:input type="hidden"  path="version" />
	</form:form>
	
<p><a href="<c:url value="/logout"/>">Logout</a></p>
</body>
</html>