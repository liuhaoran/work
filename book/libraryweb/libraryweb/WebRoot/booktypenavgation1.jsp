<%@ page contentType="text/html;charset=gbk"%>
<table align="center" width="30%" style="margin-left:330px;">
	<tr align="left" width="10%">
		<td width="10%">
<%      
		int pageNow=Integer.parseInt(request.getAttribute("pageNow").toString());    //��ǰҳ��
		int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());   //��ҳ��
		if(pageNow>1){
%>
		<a style="text-decoration:none;" href="BookSmallTypeServlet?action=querybooksmalltype&query=query1&booksmalltype=${booksmalltype}&pageNow=<%= pageNow-1 %>"><��һҳ></a>
<%
		}
%>
		</td>
	<form action="BookSmallTypeServlet?action=querybooksmalltype&query=query1&booksmalltype=${booksmalltype}" method="post">
		<td align="center" width="30%">
			<select name="pageNow" onchange="this.form.submit()">
<%
				for(int i=1;i<=pageCount;i++){
					if(i==pageNow){
%>
					<option value="<%=i%>" selected>��<%= i %>ҳ</option>
<%				
					}
					else{
%>
					<option value="<%=i%>">��<%= i %>ҳ</option>
<%
					}
%>							
<%
				}
%>			
			</select>						
		</td>
		</form>
		<td align="right" width="10%">
<%
		if(pageNow<pageCount){
%>
		<a style="text-decoration:none;" href="BookSmallTypeServlet?action=querybooksmalltype&query=query1&booksmalltype=${booksmalltype}&pageNow=<%= pageNow+1 %>">��һҳ>></a>
<%
		}
%>
		</td>
	
	</tr>
</table>