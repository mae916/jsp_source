<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.dto.Blog" %>
<%
	ArrayList<Blog> list = (ArrayList<Blog>)request.getAttribute("list");
	String siteURL = (String)request.getAttribute("siteURL");
%>
<c:set var="list" value="<%=list%>" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<%=siteURL%>/resources/js/paging.js"></script>

<h1>게시판 목록</h1>
<ul>
<c:forEach var="blog" items="${list}">
	<li>
		<a href='view?idx=${blog.idx}'>
			${blog.subject}  / ${blog.writer} / ${blog.regDt}
		</a>
	</li>
</c:forEach>
</ul>
<a href='write'>✏글쓰기</a>
<br/>
<div id="blog_post">
	블로그 게시글
</div>
<button id="button" type="button">다음 게시글 5개 보기</button>