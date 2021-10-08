package com.filter;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.core.DB;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 사이트 공통 필터
 *
 */
public class CommonFilter implements Filter {
	
	//정적 경로 -> 헤더, 푸터 출력 X
	private String[] staticDirs = {"public"};
	
	
	@Override
	public void init(FilterConfig filterconfig) {
		DB.init(filterconfig);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		/**기준 URI*/
		String siteURL = request.getServletContext().getContextPath();
		request.setAttribute("siteURL", siteURL);
		
		//전처리 - 헤더 출력
		printHeader(request, response);
		
		chain.doFilter(request, response); //다음 필터로 넘어가거나 없으면 서블릿 넘어간다. 앞은 전처리 영역, 뒤는 후처리 영역
		
		//후처리 - 푸터 출력
		printFooter(request, response);
	}
	
	/*헤더 HTML 출력*/
	public void printHeader(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		if(isPrintOK(request)) { //출력 가능 조건일때만 출력
		response.setContentType("text/html; charset=utf-8"); // 헤더를 통해서 먼저 통신하고 전달 된 후 다음이 출력 되기 때문에 푸터는 해줄 필요 X
		RequestDispatcher rd = request.getRequestDispatcher("/outline/header.jsp"); 
		// ServletRequest 인터페이스의 RequestDispatcher 메서드는 getRequestDispatcher 객체를 반환하기 때문에 ServletRequest request에서 getRequestDispatcher를 봐야함.  
		rd.include(request, response);
		}
	}
	
	/*푸터 HTML 출력*/
	public void printFooter(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		if(isPrintOK(request)) { //출력 가능 조건일때만 출력
		RequestDispatcher rd = request.getRequestDispatcher("/outline/footer.jsp");
		rd.include(request, response);
		}
	}
	
	/**헤더 푸터를 출력해도 되는지 체크하는 메서드*/
	public boolean isPrintOK(ServletRequest request) {
		/**
		 * 0.요청 method이 GET이 아닌 경우 출력 제외(return false)
		 * 		 HttpServletRequest - getMethod() -> ServletRequest request에는 없음.
		 * 1.정적 경로인 경우 헤더 푸터 출력 제외(return false)
		 * 		 URI에 정적 경로가 포함되어 있으면 false
		 * 		 HttpServletRequest - getRequestURI();
		 */
		if(request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest)request;
			if(!req.getMethod().toUpperCase().equals("GET")) {
				return false;
			}
			
			String URI = req.getRequestURI();
			for(String dir:staticDirs) {
				if(URI.indexOf("/" + dir) != -1) { // 정적 경로가 포함되어 있으면
					return false;
				}
			}
		}
		return true;
	}
}
