<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
  function changePassword(){
	var oldPwd = document.readerChangePwd.oldPwd.value;
	var newPwd = document.readerChangePwd.newPwd.value;
	var newPwdAgain = document.readerChangePwd.newPwdAgain.value;
	if(oldPwd==""){
		alert("旧密码为空！！！");
		readerChangePwd.oldPwd.value="";
		readerChangePwd.newPwd.value="";
		readerChangePwd.newPwdAgain.value="";
		readerChangePwd.oldPwd.focus();
		return;
	}
	if(oldPwd.trim().length<6){
		alert("旧密码不得包含空格且长度不得小于6位！！！");
		readerChangePwd.oldPwd.value="";
		readerChangePwd.oldPwd.focus();
		return;
	}
	if(newPwd==""){
		alert("新密码为空！！！");
		readerChangePwd.newPwd.value="";
		readerChangePwd.newPwdAgain.value="";
		readerChangePwd.newPwd.focus();
		return;
	}
	if(newPwd.trim().length<6){
		alert("新密码不得包含空格且长度不得小于6位！！！");
		readerChangePwd.newPwd.value="";
		readerChangePwd.newPwdAgain.value="";
		readerChangePwd.newPwd.focus();
		return;
	}
	if(newPwd!=newPwdAgain){
		alert("两次输入密码不一致！！！");
		readerChangePwd.newPwd.value="";
		readerChangePwd.newPwdAgain.value="";
		readerChangePwd.newPwd.focus();
		return;
	}
	document.readerChangePwd.submit();			   
}
  
  </script>
  </head>
  
  <body>
<table align="center"><!-- 修改密码表单 -->
					<form name="readerChangePwd" action="/libraryweb/bookmanager/BookManageServlet" method="post">
						<tr>
							<td align="right">请输入旧密码：</td>
							<td align="left">
								<input name="oldPwd" type="password" size="20">
							</td>
						</tr>
						<tr>
							<td align="right">请输入新密码：</td>
							<td align="left">
								<input name="newPwd" type="password" size="20">
							</td>
						</tr>
						<tr>
							<td align="right">再次输入新密码：</td>
							<td align="left">
								<input name="newPwdAgain" type="password" size="20">
							</td>
						</tr>
						<input type="hidden" name="action" value="changepassword">
						<tr><td align="right"></td><td align="left"><input type="button" value="修改" onclick="changePassword()"></td></tr>
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
						</form>
</table>
  </body>
</html>
