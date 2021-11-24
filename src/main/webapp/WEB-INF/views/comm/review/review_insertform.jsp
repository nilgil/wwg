<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글작성</title>
</head>
<body>

<form method=post action="/comm_reviewinsert">
<table border=1 width=400 align=center>
	<caption>글작성</caption>

	<tr><th>제목</th>
		<td><input type=text name="review_title"></td>
	</tr>
	<tr><th>내용</th>
		<td><textarea cols=40 rows=5 name="review_content"></textarea></td>
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