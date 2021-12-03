<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정 재확인</title>

<script src="${path}/js/info/jquery.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

</head>
<body>

<!-- 방법 1 -->
<c:if test="${qnalist.username != username}">
<script type="text/javascript">
	alert("사용자가 다르면 수정할 수 없습니다");
	history.go(-1);
</script>
</c:if>

<c:if test="${qnalist.username == username}">
<script type="text/javascript">
    location.href = "${path}/qna_updateform?qna_no=${qnalist.qna_no}&page=${page}";
</script>
</c:if>



</body>
</html>