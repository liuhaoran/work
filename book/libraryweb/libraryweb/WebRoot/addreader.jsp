<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="css/table.css">
	
  </head>
  
  <body>
  <form action="/libraryweb/ReaderInfoServlet" method="post">
     <table class="hovertable" style="margin-left:0px;">
<tr>
	<th>读者级别</th><th>姓名</th><th>密码</th><th>性别</th><th>联系方式</th><th>身份证</th>
</tr>
<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
	<td><input type="text" name="readertypeid"></td>
	<td><input type="text" name="name"></td>
	<td><input type="text" name="password"></td>
	<td><input type="text" name="sex"></td>
	<td><input type="text" name="tel"></td>
	<td><input type="text" name="cardid"></td>
</tr>
</table>
<div style="margin-left:500px;"><input type="submit" value="添加"></div>
<input type="hidden" name="action" value="addreader">
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
