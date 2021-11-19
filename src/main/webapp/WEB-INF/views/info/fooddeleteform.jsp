<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글작성</title>
</head>
<body>

<form method=post action="fooddelete.do">
	<input type="hidden" name="food_no" value="${param.food_no}">
	<input type="hidden" name="pageNum" value="${param.pageNum}">
	
	<table border=1 width=400 align=center>
	<caption>글삭제</caption>
	<tr><td colspan=2 align=center>
			<input type=submit value="삭제">
		</td>
	</tr>
</table>
</form>

</body>
</html>