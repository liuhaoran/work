<%@ page contentType="text/html;charset=gbk"%>
<table align="center" width="30%" style="margin-left:330px;">
	<tr align="left" width="10%">
		<td width="10%">
<%      
		int pageNow=Integer.parseInt(request.getAttribute("pageNow").toString());    //当前页数
		int pageCount=Integer.parseInt(request.getAttribute("pageCount").toString());   //总页数
		if(pageNow>1){
%>
		<a style="text-decoration:none;" href="ReaderInfoServlet?action=queryreader&query1=query1&readertypeid=${readertypeid}&name=${name}&cardid=${cardid}&pageNow=<%= pageNow-1 %>"><上一页></a>
<%
		}
%>
		</td>
	<form action="ReaderInfoServlet?action=queryreader&query1=query1&readertypeid=${readertypeid}&name=${name}&cardid=${cardid}" method="post">
		<td align="center" width="30%">
			<select name="pageNow" onchange="this.form.submit()">
<%
				for(int i=1;i<=pageCount;i++){
					if(i==pageNow){
%>
					<option value="<%=i%>" selected>第<%= i %>页</option>
<%				
					}
					else{
%>
					<option value="<%=i%>">第<%= i %>页</option>
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
		<a style="text-decoration:none;" href="ReaderInfoServlet?action=queryreader&query1=query1&readertypeid=${readertypeid}&name=${name}&cardid=${cardid}&pageNow=<%= pageNow+1 %>">下一页>></a>
<%
		}
%>
		</td>
	
	</tr>
</table>