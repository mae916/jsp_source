$(function() {
	CKEDITOR.replace("content");	
	CKEDITOR.config.height = 300;
	
	$(".addImage").click(function() {
		layer.popup("../popup/upload", 350, 350); // /MiniBoard/popup/upload
	});
});	