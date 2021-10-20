<%@ page contentType="text/html; charset=utf-8"%>
사용자명 : <%=request.getRemoteUser()%><br>
인증방법 : <%=request.getAuthType()%><br>
Manager에 속하는 Role 사용자 인가?
<%=request.isUserInRole("manager")%>