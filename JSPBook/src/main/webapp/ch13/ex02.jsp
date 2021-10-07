<%@ page contentType="text/html; charset=utf-8"%>
<%
	String userId = (String)session.getAttribute("userId"); //Object로 반환 되기 때문에 형변환 해줘야함..
	String userPass = (String)session.getAttribute("userPass");
%>

아이디 : <%=userId%> <br>
비밀번호 : <%=userPass%>