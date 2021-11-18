<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/userSignUpProcess" method="post">
    <input type="text" name="username" placeholder="pleas type username you wants">
    <input type="password" name="password" placeholder="pleas type password you wants">
    <s:csrfInput/>
    <button>회원가입</button>
</form>
</body>
</html>