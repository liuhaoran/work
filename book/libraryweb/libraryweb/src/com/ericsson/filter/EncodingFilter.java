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
 * 功能:解决中文乱码问题
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
		   filterChain.doFilter(request, response);//通过过滤器链arg2将进入下一个过滤器
		  }
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	     //本过滤器默认编码是UTF-8，但也可以在web.xml配置文件里设置自己需要的编码  
	     if(filterConfig.getInitParameter("encoding") != null)  
	            encoding = filterConfig.getInitParameter("encoding"); 
	}
	
	public void destroy() {
		 this.encoding = null;  
	}

}
