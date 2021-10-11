<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.dto.Blog" %>
<%
	Blog blog = (Blog)request.getAttribute("blog");
%>
<c:set var="blog" value="<%=blog%>" />
<dl>
	<dt>제목</dt>
	<dd>${blog.subject}</dd>
</dl>
<dl>
	<dt>작성자</dt>
	<dd>${blog.writer} / ${blog.regDt}</dd>
</dl>
<dl>
	<dt>내용</dt>
	<dd>${blog.content}</dd>
</dl>
<a href='delete?idx=${blog.idx}' onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
<a href='list'>게시글 목록</a>
<a href='write'>게시글 작성</a>
<a href='edit?idx=${blog.idx}'>게시글 수정</a>