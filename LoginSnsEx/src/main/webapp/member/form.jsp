<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="com.models.dto.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	boolean isSnsJoin = (Boolean)request.getAttribute("isSnsJoin");
	Member member = (Member)request.getAttribute("member");
%>
<c:set var="member" value="<%=member%>" />
<c:set var="isSnsJoin" value="<%=isSnsJoin%>" />
<h1>
	<c:if test="${isSnsJoin == true}">
	<c:choose>
		<c:when test="${member.socialChannel == 'Naver'}">
		네이버
		</c:when>
	</c:choose>
		아이디로
	</c:if>
	회원가입
</h1>
<form method="post" action="join" target="ifrmHidden" autocomplete="off">
	<dl>
		<dt>아이디</dt>
		<dd>
			<input type="text" name="memId" value="<c:out value='${member.memId}' />">
		</dd>
	</dl>
<c:if test="${isSnsJoin == false}">
	<dl>
		<dt>비밀번호</dt>
		<dd>
			<input type="password" name="memPw">
		</dd>
	</dl>
	<dl>
		<dt>비밀번호확인</dt>
		<dd>
			<input type="password" name="memPwRe">	
		</dd>
	</dl>
</c:if>
	<dl>
		<dt>회원명</dt>
		<dd>
			<input type="text" name="memNm" value="<c:out value='${member.memNm}' />">
		</dd>
	</dl>
	<input type="submit" value="가입하기">
</form>