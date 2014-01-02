<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="jquery-1.10.2.js"></script>
<title>Parking for Employee</title>
</head>
<body>
	<form method="post"
		action="${pageContext.request.contextPath}/addparking">

		<fieldset>
			<legend>
				<c:choose>
					<c:when test="${not empty employee.parking.id }">
						<h1>Updating Parking</h1>
					</c:when>
					<c:otherwise>
						<h1>Adding Parking</h1>
					</c:otherwise>
				</c:choose>

			</legend>

			<c:if test="${not empty employee.id}">
				<input type="hidden" name="id" value="${employee.id}" />
			</c:if>

			<table>

				<tr>
					<td><label for="lastname">Last name</label></td>
					<td><input name="lastname" type="text" id="lastname"
						value="${employee.lastName}" readonly /></td>
				</tr>

				<tr>
					<td><label for="email">E-mail</label></td>
					<td><input name="email" id="email" type="email"
						value="${employee.emailAddress}" readonly /></td>
				</tr>


				<tr>
					<td><label for="location">Location Preferred</label></td>
					<td><select name="location" id="location">
							<option value="--Select--" selected>--Select--</option>
							<c:forEach items="${parkingLotLocations}" var="lotLocation">
								<option value="${lotLocation}">${lotLocation.location}</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>

					<td><label for="parking">Parking slot</label></td>
					<td><select name="parkingspace">
							<option value="--Select--" selected>--Select--</option>
					</select></td>
				</tr>
			</table>

		</fieldset>

		<input id="submit" type="submit" />

	</form>

	<script>
		$("#location").change(
				function() {
					var optionSelected = $(this).find("option:selected");
					var valueSelected = optionSelected.val();
					//var textSelected   = optionSelected.text();

					var reqOption = JSON.stringify({
						"reqOption" : valueSelected
					});

					//$.getJSON('getavailablelocations', reqOption, function(data) {
					//the above line doesnot work in jquery. jquery updates the url with the data
					//we provide as url?name=value.. and sends it to the server
					//if we want to send the json string only, we need to stringify reqOption and
					//append it to the url as 'getavailableoptions?parameters=" + stringified json
					// and read in the server as request.getParameter("parameters")
					//then parse the json string
					$.getJSON('getavailablelocations', {reqOption : valueSelected}, function(data) {
						var parkingspace = $("#parkingspace");
						alert(data);
						var dataParsed = $.parseJSON(data);
						$.each(dataParsed, function() {
							parkingspace.append($("<option />").val(this.id)
									.text(this.lot));
						});

					});

				});
	</script>

</body>
</html>