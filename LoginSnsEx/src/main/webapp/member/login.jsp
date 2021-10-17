<%@ page contentType="text/html; charset=utf-8" %>
<%
	String naverCodeUrl = (String)request.getAttribute("naverCodeUrl");
%>
<form method="post" action="login" target="ifrmHidden" autocomplete="off">
	<dl>
		<dt>아이디</dt>
		<dd>
			<input type="text" name="memId">
		</dd>
	</dl>
	<dl>
		<dt>비밀번호</dt>
		<dd>
			<input type="password" name="memPw">
		</dd>
	</dl>
	<input type="submit" value="로그인">
	<br/>
	<br/>
	<a href="<%=naverCodeUrl%>"><img src="../public/img/naverLogin.png" height="50"></a>
</form>