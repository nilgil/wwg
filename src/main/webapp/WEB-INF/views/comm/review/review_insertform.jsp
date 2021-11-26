<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글작성</title>
</head>
<body>
<script src="//cdn.ckeditor.com/4.17.1/standard/ckeditor.js"></script>
<!-- CKeditor 적용 -->
<!-- <script type="text/javascript" src="/js/info/ckeditor/ckeditor.js"></script> -->

<form method=post action="/comm_reviewinsert">
<table border=1 width=800 align=center>
	<caption>글작성</caption>

	<tr><th>제목</th>
		<td><input type=text name="review_title"></td>
	</tr>
	<tr><th>내용</th>
		<td><textarea id="content" name="review_content"></textarea>
		<script type="text/javascript">	// 글쓰기 editor 및 사진 업로드 기능
			CKEDITOR.replace('content',
			{filebrowserUploadUrl:'/comm/imageupload'
			});
		</script></td>
	</tr>
	<tr><td colspan=2 align=center>
			<input type=button value="글목록"
			onClick="location.href='reviewlist' ">
			<input type=submit value="글작성">
			<input type=reset value="취소">
		</td>
	</tr>
</table>
</form>

</body>
</html>