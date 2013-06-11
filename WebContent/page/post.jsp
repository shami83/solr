<%@ page language="java" import="solr.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post</title>
</head>
<body>

<form action="postSolr" method="post">
<table>
<tr><td colspan="2">POST DATA TO SOLR</td></tr>
<tr><td>DVD Name:</td><td><input type="text" name="dvdName"/></td></tr>
<tr><td>DVD Type:</td>
<td>
<select name="dvdType">
<%
for(DvdType type : DvdType.values())
{
 out.println("<option value=\""+type.getTypeID()+"\">"+type.getTypeName()+"</option>");
}
%>
</select>
</td>
</tr>
<tr><td colspan="2"><input type="submit" value="Post"/></td></tr>
</table>
</form>
</body>
</html>