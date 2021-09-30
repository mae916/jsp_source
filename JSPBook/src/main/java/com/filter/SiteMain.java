package com.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;


public class SiteMain implements Filter {
	private FilterConfig filterConfig;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		String filter123 = filterConfig.getInitParameter("filter123");
		System.out.println("filter : " + filter123);
		
		response.setContentType("text/html; charset=utf-8");
		//System.out.println("전처리 영역");
		RequestDispatcher header = request.getRequestDispatcher("../ch00/header.jsp");
		header.include(request, response); // 전처리
		
		chain.doFilter(request, response); // 반드시 dofilter 넣어줘야 출력 됨.
		
		//System.out.println("후처리 영역");
		RequestDispatcher footer = request.getRequestDispatcher("../ch00/footer.jsp");
		footer.include(request, response); // 후처리
	}
	
	public void destroy() {
		System.out.println("destroy");
	}
}