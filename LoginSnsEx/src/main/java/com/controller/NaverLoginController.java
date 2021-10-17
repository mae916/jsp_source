package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snslogin.NaverLogin;

public class NaverLoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			NaverLogin naver = new NaverLogin();
			String accessToken = naver.getAccessToken(request);
			HashMap<String, String> memberData = naver.getMemberProfile(accessToken);
			if (memberData == null) { 
				throw new Exception("네이버 아이디 로그인을 실패하였습니다.");
			} else {
				if (naver.isJoin(memberData, request)) {  // 가입 여부
					
					boolean result = naver.login(request);
					if (!result) {
						throw new Exception("네이버 아이디 로그인을 실패하였습니다.");
					}
					
					out.print("<script>location.replace('main');</script>");
					
				} else {
					out.print("<script>location.replace('member/join');</script>");
				}
		}
			
		} catch (Exception e) {
			out.printf("<script>alert('%s');location.replace('member/login');</script>", e.getMessage());
		}
		
	}

}
