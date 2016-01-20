<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="css/table.css">
	
  </head>
  
  <body>
  <form action="/libraryweb/BookSmallTypeServlet" method="post">
           <%
		    ArrayList arrayList=(ArrayList)request.getAttribute("list");
            Object obj[]=(Object[])arrayList.get(0);
		   	%>	
     <table class="hovertable" style="margin-left:250px;">
<tr>
	<th>类别id</th><th>小类别名称</th><th>大类别名</th>
</tr>
<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
	<td><input type="text" disabled="true" value="<%= obj[0].toString() %>"></td>
	<input type="hidden" name="id"  value="<%= obj[0].toString() %>">
	<td><input type="text" name="booksmalltype" value="<%= obj[1].toString() %>"></td>
	<td><input type="text" disabled="true" value="<%= obj[2].toString() %>"></td>
</tr>
</table>
<div style="margin-left:500px;"><input type="submit" value="修改"></div>
<input type="hidden" name="action" value="updatebooksmaltype">
 </form>
 <%
							String message = (String)request.getAttribute("message");
							if(message!=null){
						%>
						<tr>
							<td colspan="10">
								<font color="red" size="2"><%= message %></font>
							</td>
						</tr>
						<%
							}
						%>		
  </body>
</html>
