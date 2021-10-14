package com.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.snslogin.NaverLogin;

public class CommonFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			NaverLogin.init(filterConfig);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain chain)
			throws IOException, ServletException {
		
		
	}
	
}
