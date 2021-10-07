<%@ page contentType="text/html; charset=UTF-8"%>
<%
	int time1 = session.getMaxInactiveInterval();
	out.print("세션유효시간 :  " + (time1 /60) + "분<br>");
	
	session.setMaxInactiveInterval(60*60); // 유효시간 늘려주기
	int time2 = session.getMaxInactiveInterval();
	out.print("세션유효시간 :  " + (time2 /60) + "분<br>");
%>