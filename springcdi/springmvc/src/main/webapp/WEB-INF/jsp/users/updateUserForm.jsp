<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
			Username: ${accountFormValidation.username}
			
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
	</form:form>
</body>
</html>