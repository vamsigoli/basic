<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>Registration</title></head>
<body>

<c:choose>
					<c:when test="${not empty account && not empty account.lastName && not empty account.firstName}">
<p>Thank you for registering.</p>
${account.firstName}, ${account.lastName} added successfully
<p> Please update information using this link <a href="<c:url value="/updateuser/updateaccount"/>">Update User</a></p>
					</c:when>
					<c:otherwise>
					<p> Please register using this link <a href="<c:url value="/registerusers/newaccount"/>">New User</a></p>
					</c:otherwise>
				</c:choose>
</body>
</html>