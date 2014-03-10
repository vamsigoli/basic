<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>Registration</title></head>
<body>

<c:choose>
					<c:when test="${not empty accountFormValidation && not empty accountFormValidation.lastName && not empty accountFormValidation.firstName}">
<p>Thank you for registering.</p>
${accountFormValidation.firstName}, ${accountFormValidation.lastName} added successfully
<p> Please access the application using <a href="<c:url value="/updateusers/main"/>">Update User</a></p>
					</c:when>
				</c:choose>
					<p> Please register using this link <a href="<c:url value="/registerusers/newaccount"/>">New User</a></p>
</body>
</html>