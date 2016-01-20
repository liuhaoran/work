package com.ericsson.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����:���������������
 * @author cheng
 *
 */
public class EncodingFilter implements Filter {
	
	protected String encoding=null;
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		 if(encoding!=null)
		  {
		   request.setCharacterEncoding(encoding);
		   response.setContentType("text/html;charset="+encoding);
		   filterChain.doFilter(request, response);//ͨ����������arg2��������һ��������
		  }
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	     //��������Ĭ�ϱ�����UTF-8����Ҳ������web.xml�����ļ��������Լ���Ҫ�ı���  
	     if(filterConfig.getInitParameter("encoding") != null)  
	            encoding = filterConfig.getInitParameter("encoding"); 
	}
	
	public void destroy() {
		 this.encoding = null;  
	}

}
