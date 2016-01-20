package com.ericsson.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.book.service.BookTypeService;
import com.ericsson.book.service.impl.BookTypeServiceImpl;
import com.ericsson.bookmanager.bean.BookManager;

public class BookTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BookManager bookManager=(BookManager)request.getSession().getAttribute("bookManager");
		String message="";
		String action=request.getParameter("action");
		String bookMajorType=request.getParameter("bookMajorType");
		BookTypeService bookTypeService=new BookTypeServiceImpl();
		if(bookManager!=null){
			//��Ӵ�����
			if("addbooktype".equals(action)){
				String parameters[]={bookMajorType};
				Boolean b=bookTypeService.executeUpdate(parameters);
				if(b){
					message = "���ͼ��������ɹ���";
				}else{
					message = "���ͼ�������ʧ�ܣ�";	
				}
				request.setAttribute("message", message);
				request.getRequestDispatcher("addbooktype.jsp").forward(request, response);
				//���Ҵ�����
			}else if("querybooktype".equals(action)){
				String parameters[]={};
				List booktype=bookTypeService.executeQuery1(parameters);
				if(booktype.size()>0){
					request.setAttribute("booktype", booktype);
					request.getRequestDispatcher("addbooksmalltype.jsp").forward(request, response);
				}else{
					message="δ���ҵ���������������Ӵ�����!";
					request.setAttribute("message", message);
					request.getRequestDispatcher("addbooktype.jsp").forward(request, response);
				}
				//���С����
			}else if("addbooksmalltype".equals(action)){
				String booksmalltype=request.getParameter("booksmalltype");
				String parameters[]={booksmalltype,bookMajorType};
				Boolean b=bookTypeService.executeInsert(parameters);
				String parameters1[]={};
				List booktype=bookTypeService.executeQuery1(parameters1);
				if(b){
					message = "���ͼ��С�����ɹ���";
				}else{
					message = "���ͼ��С����ʧ�ܣ�";	
				}
				request.setAttribute("message", message);
				request.setAttribute("booktype", booktype);
				request.getRequestDispatcher("addbooksmalltype.jsp").forward(request, response);
			}
			
		}else{
			message="�����µ�½���ٲ���!!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("addbooktype.jsp").forward(request, response);
		}

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doGet(request, response);
	}

}
