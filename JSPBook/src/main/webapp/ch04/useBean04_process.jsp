<%@ page contentType="text/html; charset=utf-8" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="member" class="com.model.dto.MemberBean" scope="request" />
<jsp:setProperty name="member" property="id" param="userId" />
<jsp:setProperty name="member" property="name" />
아이디 : <jsp:getProperty name="member" property="id" /><br>
비밀번호 : <jsp:getProperty name="member" property="name" />
