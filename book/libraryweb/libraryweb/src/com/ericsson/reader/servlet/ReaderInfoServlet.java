package com.ericsson.reader.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ericsson.bookmanager.bean.BookManager;
import com.ericsson.reader.service.ReaderInfoService;
import com.ericsson.reader.service.impl.ReaderInfoServiceImpl;

public class ReaderInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReaderInfoService readerInfoService=new ReaderInfoServiceImpl();
		String message="";
		String readertypeid=request.getParameter("readertypeid");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String sex=request.getParameter("sex");
		String tel=request.getParameter("tel");
		String cardid=request.getParameter("cardid");
		String action=request.getParameter("action");
		BookManager bookManager=(BookManager)request.getSession().getAttribute("bookManager");
		if(bookManager!=null){
			//添加读者
			if("addreader".equals(action)){
				String parameters[]={readertypeid,name,password,sex,tel,cardid};
				Boolean b=readerInfoService.executeUpdate(parameters);
				if(b){
					message = "添加读者成功！";
				}else{
					message = "添加读者失败！";	
				}
				request.setAttribute("message", message);
				request.getRequestDispatcher("addreader.jsp").forward(request, response);
			//查询读者
			}else if("queryreader".equals(action)){
				String query1=request.getParameter("query1");
				String parameters[]={readertypeid,name,cardid};
				int pageSize=3;
				String pageNow=request.getParameter("pageNow");
				if(pageNow==null){
					pageNow="1";
				}
				List list1=readerInfoService.executeQueryByPage(parameters, pageSize, (Integer.parseInt(pageNow)-1)*pageSize);
				ResultSet rs=readerInfoService.getCount(parameters);
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
				if("query1".equals(query1)){
					request.setAttribute("navgation1", "navgation1");
					request.setAttribute("readertypeid", readertypeid);
					request.setAttribute("name", name);
					request.setAttribute("cardid", cardid);
				}
				request.getRequestDispatcher("queryreader.jsp").forward(request, response);
			 //删除读者
		     }else if("delreader".equals(action)){
		    	 String id=request.getParameter("id");
		    	 String parameters[]={id};
		    	 readerInfoService.executeDelete(parameters);
		    	 response.sendRedirect("/libraryweb/ReaderInfoServlet?action=queryreader");
		     }else if("updreader".equals(action)){
		    	 String id=request.getParameter("id");
		    	 String parameters[]={id};
		    	 List list=readerInfoService.executeFindById(parameters);
		    	 request.setAttribute("list", list);
		    	 request.getRequestDispatcher("updatereader.jsp").forward(request, response);
		     }else if("updatereader".equals(action)){
		    	 String id=request.getParameter("id");
		    	 String parameters[]={readertypeid,name,password,sex,tel,cardid,id};
		    	 readerInfoService.executeUpdateReader(parameters);
		    	 response.sendRedirect("/libraryweb/ReaderInfoServlet?action=queryreader");
		     }
		}else{
			message="请重新登陆，再操作!!!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("updatereader.jsp").forward(request, response);
		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
