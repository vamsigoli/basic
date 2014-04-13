<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="myTagLibrary" uri="WEB-INF/tld/funckytaglib.tld"%>
    
<!DOCTYPE html>
<html>
 <body>
  <%-- call your custom el function --%>
  Here is a random color: ${myTagLibrary:randomColor()}.
 
  <%-- call your custom tag --%>
  Here is a random number: <myTagLibrary:randomNumber greaterThan="10"/>
 </body>
</html>