<%@ page language="java" import="solr.*,java.util.*;" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
     Map<String,String> map =(Map<String,String>)request.getAttribute("RESULT_MAP");
    %>
    
    <html>
    <body>
    <form action="search" method="post">
    <table width="100%">
       <tr>
       <td>DVD NAME :</td><td><input type="text" name="dvdName"/></td>
       </tr>
       <tr>
       <td>DVD Type :</td>
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
       <tr>
       <td colspan="2"><input type="submit" value="Search"/></td>
       </tr>
    </table>
    </form>
    <table width="100%">
    <tr>
    <td>DVD NAME</td><td>DVD TYPE</td>
    </tr>
    <%
    if(map != null)
    {
    	for(String key : map.keySet())
    	{
    		String type = map.get(key);
    		out.println("<tr>");
    		out.println("<td>"+key+"</td><td>"+type+"</td>");
    		out.println("</tr>");
    	}
    }
    %>
    
    </table>
    </body>
    </html>