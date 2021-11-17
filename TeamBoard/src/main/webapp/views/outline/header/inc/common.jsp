<%@ page contentType="text/html; charset=utf-8" %>
<%-- <%@ page  import="com.models.member.*" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String rootURL = (String)request.getAttribute("rootURL");
	boolean isLogin = (Boolean)request.getAttribute("isLogin");
	/* Member member = (Member)request.getAttribute("member"); */
%>
<c:set var="rootURL" value="<%=rootURL%>" />
<c:set var="isLogin" value="<%=isLogin%>" />
<%-- <c:set var="member" value="<%=member%>" /> --%>
<header>
	<!--로그인 전 로그인 버튼만 // 로그인 후, OOO님 환영합니다!, 로그아웃// OOO님(hover 밑줄) 클릭하면 회원정보 수정페이지로 넘어감.-->
	<div class="main_top">
		<div class='inner layout_width'>
		<c:choose>
			<c:when test="${isLogin}">
				<a href="${rootURL}/member/logout">로그아웃</a>
				<a href="${rootURL}/member/info">회원정보수정</a>
			</c:when>
			<c:otherwise>
				<a href="${rootURL}">로그인</a>
				<a href="${rootURL}/member/join">회원가입</a>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</header>

<!--com/models/kanban/kanban.java-->
<!--show-bar z-index처리, 메뉴scroll고정 -->
<nav>
	<ul class='nav_list layout_width'>
		<li class='box'
			<c:if test="${menu == 'work'}"> class='on'</c:if>
		>
			<a class="nav_over" href="${rootURL}/kanban/work">새소식</a>
			<ul class="show-bar">
				<li><a href="#">공지사항</a></li>
				<li><a href="#">GM소식</a></li>
				<li><a href="#">이벤트</a></li>
				<li><a href="#">업데이트</a></li>
				<li><a href="#">대회소식</a></li>
			</ul>
		</li>
		<li class='box'
			<c:if test="${menu == 'list_ready'}"> class='on'</c:if>
		>
			<a class="nav_over" href="${rootURL}/kanban/list?status=ready">가이드</a>
			<ul class="show-bar">
				<li><a href="#">게임가이드</a></li>
			</ul>
		</li>
		<li class='box'
			<c:if test="${menu == 'list_progress'}"> class='on'</c:if>
		>
			<a class="nav_over" href="${rootURL}/kanban/list?status=progress">커뮤니티</a>
			<ul class="show-bar">
				<li><a href="#">플레이어 게시판</a></li>
				<li><a href="#">미디어 게시판</a></li>
				<li><a href="#">DUO/SQUAD 모집</a></li>
			</ul>
		</li>
		<li class='box'
			<c:if test="${menu == 'list_work'}"> class='on'</c:if>
		>
			<a class="nav_over" href="${rootURL}/kanban/list?status=done">SNS</a>
			<ul class="show-bar">
				<li><a href="#">디스코드</a></li>
				<li><a href="#">페이스북</a></li>
				<li><a href="#">유튜브</a></li>
				<li><a href="#">톡채널</a></li>
			</ul>
		</li>
		<li class='box'
		<c:if test="${menu == 'work'}"> class='on'</c:if>
	>
		<a class="nav_over" href="${rootURL}/kanban/work">랭킹</a>
		<ul class="show-bar">
			<li><a href="#">랭킹</a></li>
		</ul>
	</li>
	<li class='box'
		<c:if test="${menu == 'list_ready'}"> class='on'</c:if>
	>
		<a class="nav_over" href="${rootURL}/kanban/list?status=ready">상점</a>
		<ul class="show-bar">
			<li><a href="#">게임구매</a></li>
		</ul>
	</li>
	<li class='box'
		<c:if test="${menu == 'list_progress'}"> class='on'</c:if>
	>
		<a class="nav_over" href="${rootURL}/kanban/list?status=progress">자료실</a>
		<ul class="show-bar">
			<li><a href="#">다운로드</a></li>
		</ul>
	</li>
	<li class='box'
		<c:if test="${menu == 'list_work'}"> class='on'</c:if>
	>
		<a class="nav_over" href="${rootURL}/kanban/list?status=done">고객센터</a>
		<ul class="show-bar">
			<li><a href="#">고객센터</a></li>
			<li><a href="#">이용제한</a></li>
			<li><a href="#">이의신청</a></li>
			<li><a href="#">보안센터</a></li>
		</ul>
	</li>
	</ul>
</nav>



