package com.ericsson.bookmanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;

import com.ericsson.bookmanager.bean.BookManager;
import com.ericsson.bookmanager.service.BookManageService;
import com.ericsson.bookmanager.service.impl.BookManageServiceImpl;

/**
 * 功能:用户登陆验证
 * @author cheng
 *
 */
public class BookManageServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BookManageService bookManageService=new BookManageServiceImpl();
		BookManager bookManager=new BookManager();
		HttpSession session=request.getSession();
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		String action=request.getParameter("action");
		String remember_me=request.getParameter("remember_me");
		String message="";
		
		if("login".equals(action)){
			String parameters[]={name,password};
			ArrayList arrayList=bookManageService.executeQuery1(parameters);
			if(arrayList.size()>0){
				Object obj[]=(Object[])arrayList.get(0);
				bookManager.setBookmanageid(Integer.parseInt(obj[0].toString()));
				bookManager.setName(obj[1].toString());
				session.setAttribute("bookManager", bookManager);
				//设置cookie记住密码
				if("remember_me".equals(remember_me)){
					//创建cookie
					Cookie user=new Cookie("user",name+"-"+password);
					user.setMaxAge(60*60*24);
					response.addCookie(user);
				}else {
					Cookie deleteCookie=new Cookie("user",null);
					deleteCookie.setMaxAge(0);
					response.addCookie(deleteCookie);
				}
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				message="用户id和密码不正确，请核对后重新登陆!";
				request.setAttribute("message", message);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if("logout".equals(action)){
			request.getSession().invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if("changepassword".equals(action)){
			String oldPwd = request.getParameter("oldPwd").trim();
			String newPwd = request.getParameter("newPwd").trim();
		    bookManager=(BookManager)request.getSession().getAttribute("bookManager");
			if(bookManager!=null){
				String parameters[]={newPwd,oldPwd,(bookManager.getBookmanageid())+""};
				Boolean b=bookManageService.executeUpdate(parameters);
				if(b){
					message = "密码修改成功，下次请用新密码登陆！";
				}else{
					message = "旧密码输入错误，请核对后重新修改！";	
				}
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("changepassword.jsp").forward(request, response);
		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
