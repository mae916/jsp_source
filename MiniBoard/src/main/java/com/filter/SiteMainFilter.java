package com.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.core.*;

/**
 *  사이트 전역 필터
 * 
 */
public class SiteMainFilter implements Filter {
	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		this.filterConfig = filterConfig;
		
		/* 데이터 베이스 설정 초기화 */
		DB.init(filterconfig);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
	
		// 사이트 root URL
		String rootURL = request.getServletContext().getContextPath();
		request.setAttribute("siteURL", rootURL);

		String method = null;
		boolean isScriptCss = false;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest)request;
			method = req.getMethod().toUpperCase();
			String URI = req.getRequestURI();
			//js(자바스크립트), css(스타일시트) 헤더, 푸터가 추가되지 않도록 처리
			if(URI.indexOf(".js") != -1 || URI.indexOf(".css") != -1 || URI.indexOf(".png") != -1) {
				isScriptCss = true;
			}
		}
		
		if (method != null && method.equals("GET") && !isScriptCss) { // isScriptCss js나 css 둘중 하나가 아니면 헤더가 추가되는것
			RequestDispatcher header = request.getRequestDispatcher("/outline/header.jsp");
			header.include(request, response);
		}
		
		chain.doFilter(request, response);
		
		if (method != null && method.equals("GET") && !isScriptCss) {
			RequestDispatcher footer = request.getRequestDispatcher("/outline/footer.jsp");
			footer.include(request, response);
		}
	}
}
