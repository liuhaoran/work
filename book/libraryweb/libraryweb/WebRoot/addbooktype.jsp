<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="css/table.css">
	
  </head>
  
  <body>
  <form action="/libraryweb/BookTypeServlet" method="post">
     <table class="hovertable" style="margin-left:430px;">
<tr>
	<th>图书大类名</th>
</tr>
<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
	<td><input type="text" name="bookMajorType"></td>
</tr>
</table>
<div style="margin-left:500px;"><input type="submit" value="添加"></div>
<input type="hidden" name="action" value="addbooktype">
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
