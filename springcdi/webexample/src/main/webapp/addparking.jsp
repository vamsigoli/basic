<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Parking to Employee</title>
</head>
<body>
	<form method="post"
		action="${pageContext.request.contextPath}/addparking">

		<fieldset>
			<legend> 
			<c:choose>
					<c:when test="${not empty employee.parking.id }">
			<h1>
						Updating Parking
				</h1>
					</c:when>
					<c:otherwise>
			<h1>
						Adding Parking
				</h1>
					</c:otherwise>
				</c:choose>
			
			 </legend>

			<div>
				<label for="lastname">Last name</label> <input name="lastname" type="text"
					id="lastname" value="${employee.lastName}" readonly/>
			</div>

			<div>
				<label for="email">E-mail</label> <input name="email" id="email" type="email" 
				value="${employee.emailAddress}" readonly />
			</div>
			
			<c:if test="${not empty employee.id}">
				<input type="hidden" name="id" value="${employee.id}" />
			</c:if>
			
			
			<label for="location">Location Preferred</label>
			<select name="location">
  <c:forEach items="${parkingLotLocations}" var="lotLocation">
    <option value="${lotLocation}">${lotLocation.location}</option>
  </c:forEach>
</select>

		</fieldset>
		
		<input id="submit" type="submit" />

	</form>

</body>
</html>