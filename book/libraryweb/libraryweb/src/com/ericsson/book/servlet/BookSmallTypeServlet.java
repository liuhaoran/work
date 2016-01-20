package com.ericsson.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.book.service.BookSmallTypeService;
import com.ericsson.book.service.impl.BookSmallTypeServiceImpl;
import com.ericsson.bookmanager.bean.BookManager;

public class BookSmallTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookSmallTypeService bookSmallTypeService=new BookSmallTypeServiceImpl();
		String action=request.getParameter("action");
		String message="";
		BookManager bookManager=(BookManager)request.getSession().getAttribute("bookManager");
		String query=request.getParameter("query");
		String booksmalltype=request.getParameter("booksmalltype");
		if(bookManager!=null){
			if("querybooksmalltype".equals(action)){
				String parameters[]=null;
				if("query1".equals(query)){
					parameters=new String[]{"%"+booksmalltype+"%"};
					request.setAttribute("navgation1", "navgation1");
					request.setAttribute("booksmalltype", booksmalltype);
				}
				int pageSize=5;
				String pageNow=request.getParameter("pageNow");
				if(pageNow==null){
					pageNow="1";
				}
				List list1=bookSmallTypeService.executeQueryByPage(parameters, pageSize, (Integer.parseInt(pageNow)-1)*pageSize);
				ResultSet rs=bookSmallTypeService.getCount(parameters);
				try {
					rs.next();
					int RowCount=rs.getInt(1);
					int pageCount=(RowCount+pageSize-1)/pageSize;
					request.setAttribute("pageNow", pageNow);
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("list1", list1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.getRequestDispatcher("querybooktype.jsp").forward(request, response);
			}else if("updbooktype".equals(action)){
				 String id=request.getParameter("id");
		    	 String parameters[]={id};
		    	 List list=bookSmallTypeService.executeFindById(parameters);
		    	 request.setAttribute("list", list);
		    	 request.getRequestDispatcher("updatebooktype.jsp").forward(request, response);
			}else if("updatebooksmaltype".equals(action)){
				 String id=request.getParameter("id");
		    	 String parameters[]={booksmalltype,id};
		    	 bookSmallTypeService.executeUpdateBookSmallType(parameters);
		    	 response.sendRedirect("/libraryweb/BookSmallTypeServlet?action=querybooksmalltype");
			}
			
			
		}else{
			message="请重新登陆，再操作!!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
