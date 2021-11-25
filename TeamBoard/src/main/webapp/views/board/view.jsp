<%@ page contentType="text/html; charset=utf-8" %>
<%@ page  import="com.models.member.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String rootURL = (String)request.getAttribute("rootURL");
	boolean isLogin = (Boolean)request.getAttribute("isLogin");
	Member member = (Member)request.getAttribute("member");
%>
<c:set var="rootURL" value="<%=rootURL%>" />
<c:set var="isLogin" value="<%=isLogin%>" />
<c:set var="member" value="<%=member%>" />

<div class="layout_width">
    <h3>플레이어 게시판</h3>
    <div class="view_head">
        <div class="head_tit"></div>
        <dl>
            <dt>작성자</dt>
            <dd><a href="${rootURL}/member/info"> <c:out value="${member.memId}(아이디)" /></a></dd>
        </dl>   
        <dl>
            <dt>작성일</dt>
            <dd>2021.11.25 오후 08:04</dd> <!-- dto -->
        </dl>     
        <dl>
            <dt>조회수</dt>
            <dd>6</dd> 
        </dl>  
            <div>신고?</div>
        </div>
        <div class="content">
            내용
        </div>
        <div class="view_btns">
            <a href="" class="btn_list"></a>
            <c:choose>
                <c:when test=""> <!-- 본인 글일때 -->
                    <a href="">수정하기</a>
                </c:when>
                <c:otherwise>
                    <a href="">글쓰기</a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="comment">
            <div class="tit_cmt">
                <span>댓글</span>
                <span>0</span>
            </div>
        <div class="byte_info">
            <span>0</span>
            <span>/ 600 bytes (한글 300자) | </span>
            <a href="">댓글 운영정책</a>
        </div>
        <form action=""> <!-- 댓글 있는글 없는글 비교 작성 -->
        </form>
        </div>
    </div>
</div>
