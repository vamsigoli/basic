<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Spring MVC App</title>
</head>
<body>
  Hello  ${name}
  
 
 
 <c:choose>  
    <c:when test="${name eq 'VAMSI'}">  
         <c:out value="You are great" />             
         </c:when>  
         <c:otherwise>  
         <c:out value="You are good"></c:out>             
         </c:otherwise>  
</c:choose> 
 
</body>
</html>
