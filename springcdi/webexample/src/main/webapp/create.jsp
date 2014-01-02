<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Employee</title>
</head>
<body>
	<form method="post"
		action="${pageContext.request.contextPath}/employeecreate">

		<fieldset>
			<legend> 
			<c:choose>
					<c:when test="${not empty employee.id }">
						Updating Employee 
					</c:when>
					<c:otherwise>
						Adding Employee 
					</c:otherwise>
				</c:choose>
			
			 </legend>

			<div>
				<label for="lastname">Last name</label> <input name="lastname" type="text"
					id="lastname" value="${employee.lastName}" required/>
			</div>

			<div>
				<label for="email">E-mail</label> <input name="email" id="email" type="email" 
				value="${employee.emailAddress}" required/>
			</div>
			
			<div>
				<label for="jobtitle">Job Title</label> <input name="jobtitle"
					id="jobtitle" type="text" 
					value="${employee.jobTitle}"  required />
			</div>
			
			<div>
				<label for="dateofhire">Date of hire</label> <input name="dateofhire"
					id="dateofhire" type="date" 
					value="${employee.dateOfHire}" required /><label class="after">(MM/DD/YYYY)</label>
			</div>


			<div>
				<label for="salary">Salaray</label> <input name="salary"
					id="salary" type="number" value="${employee.salary}" required/>
					
			</div>
			
			<c:if test="${not empty employee.id}">
				<input type="hidden" name="id" value="${employee.id}" />
			</c:if>
			
			<c:if test="${not empty employee.id}">
			   <a href="${pageContext.request.contextPath}/addparking.jsp" >Add Parking </a>
			</c:if>

		</fieldset>
		
		<input id="submit" type="submit" />

	</form>

</body>
</html>