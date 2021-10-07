<%@ page contentType="text/html; charset=UTF-8"%>
<%
	HttpSession session2 = request.getSession();
	session2.setAttribute("userId2","admin2");
	session2.setAttribute("userPass2","1234");
%>