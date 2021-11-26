<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글작성</title>
<!-- CKeditor 적용 -->
<script type="text/javascript" src="/js/info/ckeditor/ckeditor.js"></script>
</head>
<body>

<form method=post action="${path}/foodupdate.do/pageNum/${pageNum}">
	<input type="hidden" name="food_no" value="${foodboard.food_no}">
	<input type="hidden" name="username" value="${foodboard.username}">
<table border=1 width=400 align=center>
	<caption>글수정</caption>
	<tr><th>번호</th>
		<td>${foodboard.food_no}</td>
	</tr>
	<tr><th>제목</th>
		<td><input type=text name="food_title" required="required"
			value="${foodboard.food_title}"></td>
	</tr>
	<tr><th>작성자명</th>
		<td>${foodboard.username}</td>
	</tr>
	<tr><th>내용</th>
		<td><textarea id="content" name="food_content">${foodboard.food_content}</textarea>
		<script type="text/javascript">	// 글쓰기 editor 및 사진 업로드 기능
			CKEDITOR.replace('content',
			{filebrowserUploadUrl:'/food/imageUpload.do'
			});
		</script></td>
	</tr>
	<tr><td colspan=2 align=center>
			<input type=button value="글목록"
			onClick="location.href='/foodlist.do/pageNum/${pageNum}' ">
			<input type=submit value="글작성">
			<input type=reset value="취소">
		</td>
	</tr>
</table>
</form>

</body>
</html>