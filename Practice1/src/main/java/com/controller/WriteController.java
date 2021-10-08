package com.controller;

import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;


public class WriteController extends HttpServlet {

	private static final long serialVersionUID = -1L;
	
	//게시글 양식 출력
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("/blog/form.jsp"); 
		//request에서 받은 내용을 getRequestDispatcher로 blog/form.jsp(양식)에 출력하고 그걸 RequestDispatcher rd에 넣은 것?
		//RequestDispatcher는 getRequestDispatcher()메서드를 구현해서 생성되는 객체임
		rd.include(request, response);
	}

	//게시글 저장 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; contentType=utf-8");
		
		PrintWriter out = response.getWriter(); // '쓰기'를 통해 응답(response) 하겠다는 뜻
		
		
	}
	
}
