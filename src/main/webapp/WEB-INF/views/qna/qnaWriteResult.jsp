<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="UTF-8">
<title>글작성 성공폼</title>
</head>
<body>

<c:if test="${result > 0}">
<script type="text/javascript">
alert("입력성공");
location.href="qnalist.do";
</script>
</c:if>

<c:if test="${result <= 0 }">
<script type="text/javascript">
alert("입력실패");
history.go(-1);
</script>
</c:if>

</body>
</html>
