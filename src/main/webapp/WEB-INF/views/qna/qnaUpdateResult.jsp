<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>글수정 성공폼</title>
</head>
<body>

<c:if test="${result > 0}">
	<script type="text/javascript">
		alert("수정성공");
		location.href="./qnalist.do?page="+${page}
	</script>
</c:if>

<c:if test="${result <= 0 }">
	<script type="text/javascript">
		alert("수정실패");
		history.go(-1);
	</script>
</c:if>

</body>
</html>