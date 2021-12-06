

$(function(){
	
$("form").submit(function(){
	var title = $("#title").val();
	
		if(title ==''){
			alert("제목을 입력하세요");
//			document.forms[0].title.focus();
			return false;
		}
		
/*		if($("#content").val()==''){
			alert("내용을 입력하세요");
			$("#content").focus();
			return false;
		}*/

});

});