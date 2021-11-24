<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.ckeditor.com/ckeditor5/12.0.0/classic/ckeditor.js"></script>
<meta charset="UTF-8">
<title>글작성</title>
</head>
<body>
<form method=post action="foodwrite.do">
<table border=1 width=1000 align=center>
	<caption>글작성</caption>
	<tr><th>작성자명</th>
		<%-- <td>${foodboard.username}</td> --%>	<!-- ${sessionScope.username} -->
	</tr>
	<tr><th>제목</th>
		<td><input type=text name="food_title"></td>
	</tr>
	<tr><th>내용</th>
		<td><textarea rows=100 id="content" name="food_content"></textarea></td>
	</tr>
	<tr><th>파일첨부</th>
	</tr>
	<tr><td colspan=2 align=center>
			<input type=button value="글목록"
			onClick="location.href='foodlist.do' ">
			<input type=submit value="글작성">
			<input type=reset value="취소">
		</td>
	</tr>
</table>
<script src="${pageContext.request.contextPath}/js/info/ckeditor.js"></script>
</form>

</body>
</html>