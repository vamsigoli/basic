<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
    <body style="width:100%;height:100%">
<table border="1" cellspacing="0" cellpadding="0" style="width:100%;height:100%">
<tr>
<td height="30" colspan="2">
                    <tiles:insertAttribute name="header" /></td>
</tr>
<tr>
<td height="250">
                    <tiles:insertAttribute name="menu" /></td>
<td width="350" >
                    <tiles:insertAttribute name="body" /></td>
</tr>
<tr>
<td height="30" colspan="2">
                    <tiles:insertAttribute name="footer" /></td>
</tr>
</table>
</body>
</html>