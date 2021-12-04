<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>에러</title>
</head>
<body>
<script type="text/javascript">
 alert("아이디 또는 비밀번호가 일치하지 않습니다.");
 history.go(-1);
</script>
   <%--  <button onclick="location.href='${pageContext.request.contextPath}/loginPage'">네 알겟습니다^^</button> --%>
</body>
</html>
