<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.dao.*" %>
<%@ page import="com.model.dto.*" %>
<%@ page import="java.util.*" %>

<%
	int p = 1;
	if (request.getParameter("page") != null) {
		p = Integer.parseInt(request.getParameter("page").trim());
	}
	BlogDao dao = new BlogDao();
	ArrayList<Blog> list = dao.getList(p, 5);
%>
<c:set var="list" value="<%=list%>" />
<c:forEach var="blog" items="${list}">
	<div class="post_content" style="border-bottom: 1px solid gray; padding: 10px; margin-bottom: 20px;">
		제목 : <c:out value="${blog.subject}" /><br>
	 </div>
</c:forEach>