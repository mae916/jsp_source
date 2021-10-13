<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="js/ckeditor/ckeditor.js"></script>
		<script src="js/form.js"></script>
	</head>
	<body>
		<form method="post" action="#" autocomplete="off">
		<dl>
			<dt>제목</dt>
			<dd>
				<input type="text" name="subject">
			</dd>
		</dl>
		<textarea id="content" name="content"></textarea>
		<button type="button" id="addImage">이미지 첨부</button>
		</form>
		<script type="text/javascript" id="uploadForm">
			<form method="post" action="upload.jsp" enctype="multipart/form-data" target="ifrmHidden">
				<input type="text" name="file">
				<input type="file" name="file">
			</form>
		</script>
		<iframe name="ifrmHidden" width="100%" height="500%" frameborder="0"></iframe>
	</body>
</html>
