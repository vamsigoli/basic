<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>Registration</title></head>
<body>

<c:choose>
					<c:when test="${not empty account && not empty account.lastName && not empty account.firstName}">
<p>Thank you for registering.</p>
${account.firstName}, ${account.lastName} added successfully
					</c:when>
					<c:otherwise>
					<p> Please register using this link <a href="<c:url value="/registerusers/newaccount"/>">New User</a>
					</c:otherwise>
				</c:choose>
</body>
</html>