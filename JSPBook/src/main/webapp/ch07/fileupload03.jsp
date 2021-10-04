<%@ page contentType="text/html; charset=utf-8" %>
<form method="post" enctype="multipart/form-data" action="fileupload03_process.jsp" >
	이름 : <input type="text" name="name"><br>
	제목 : <input type="text" name="subject"><br>
	파일 : <input type="file" name="filename"><br>
	<input type="submit" value="업로드">
</form>