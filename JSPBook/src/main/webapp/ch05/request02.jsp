<%@ page contentType="text/html; charset=utf-8" %>
<%
	String contentType = request.getHeader("content-type");
	String host = request.getHeader("Host");
%>
Content-Type : <%=contentType%><br>
Host : <%=host%>