package com.controller.member;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.models.dao.MemberDao;
import com.models.dto.Member;
import com.exception.AlertException;
import com.snslogin.*;

/**
 * 회원 가입 
 * GET - 가입 양식, POST - 가입 처리 
 */
public class JoinController extends HttpServlet {
	
	/** 회원가입 양식 */
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		NaverLogin naver = new NaverLogin();
		Member member = naver.getSocialUserInfo(request);
		boolean isSocialJoin = false;
		if (member != null) { // 네이버 로그인으로 회원 가입 유입된 경우 
			isSocialJoin = true;
		}
		request.setAttribute("isSocialJoin", isSocialJoin);
		request.setAttribute("member", member);
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/form.jsp");
		rd.include(request, response);
	}
	
	/** 회원가입 처리 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			NaverLogin naver = new NaverLogin();
			Member socialMember = naver.getSocialUserInfo(request);
			
			MemberDao dao = new MemberDao();
			boolean result = dao.join(request);
			if (!result) { // 회원가입 실패 
				throw new AlertException("회원가입 실패!");
			}
			if (socialMember == null) { // 일반회원은 로그인 페이지
				out.print("<script>parent.location.href='login';</script>");
			} else { // 소셜 회원 가입 완료 후 - 자동 로그인 -> 메인페이지
				out.print("<script>parent.location.href='../main';</script>");
			}
		} catch (AlertException e) {
			out.print("<script>alert('" + e.getMessage() + "');</script>");
			return;
		}
	}
}