<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="css/table.css">
		<script type="text/javascript">
	   function del(){
	        if(confirm("请确认是否删除！" )){
	             return true;
	        }else{
	        	return false; 	
	        }
	      } 
	</script>
	</head>
  
  <body>
  <form action="/libraryweb/BookSmallTypeServlet?action=querybooksmalltype" method="post">
  <span style="margin-left:150px;">小类别名:<input type="text" name="booksmalltype"><input type="submit" value="查询"></span>
  <input type="hidden" name="query" value="query1">
   </form>
   <%
		    ArrayList arrayList=(ArrayList)request.getAttribute("list1");
		   	if(arrayList!=null&&arrayList.size()!=0){
		   	%>	
		   	 <table class="hovertable" width="80%" >
		   	<tr>
		   		<th>类别id</th><th>小类别名</th><th>大类别名</th><th>修改</th><!--<th>删除</th>  -->
		   	</tr>
		   	<% 
		    for(int i=0;i<arrayList.size();i++){
		        Object obj[]=(Object[])arrayList.get(i);
		        %>
		        <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
		    	<td><%= obj[0].toString() %></td>
		    	<td><%= obj[1].toString() %></td>
		    	<td><%= obj[2].toString() %></td>
		    	<td><a style="text-decoration:none;" href="/libraryweb/BookSmallTypeServlet?action=updbooktype&id=<%= obj[0].toString() %>">修改</a></td>
		    	<!-- <td><a style="text-decoration:none;" href="/libraryweb/BookSmallTypeServlet?action=delbooktype&id=<%= obj[0].toString() %>" onclick="return del()">删除</a></td>-->
		        </tr>
		        <% 
		        }
		   		%>
		   		</table>
		   		<% 
		   	}
		%>
    <%
    String navgation1=(String)request.getAttribute("navgation1");
    if(navgation1!=null){
    	%>
    	<jsp:include page="booktypenavgation1.jsp"></jsp:include>
    	<% 
    }else{
    	%>
    	<jsp:include page="booktypenavgation.jsp"></jsp:include>
    	<% 
    }
    %>
    
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
