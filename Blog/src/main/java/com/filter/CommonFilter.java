package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CommonFilter implements Filter {
	private FilterConfig filterconfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterconfig = filterconfig;
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		header(request, response);
		chain.doFilter(request, response);
		footer(request, response);
	}
	
	public void header(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("/outline/header.jsp");
		rd.include(request,response);
	}
	
	public void footer(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/outline/footer.jsp");
		rd.include(request,response);
	}

}
