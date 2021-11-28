<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<form action="/login" method="post">
    <input type="text" name="id" placeholder="아이디">
    <input type="password" name="pw" placeholder="비밀번호">
    <label>${_csrf.token}
    <input name="${_csrf.parameterName}" type="checkbox" checked="checked" value="${_csrf.token}">
    </label>
    <h6>${_csrf.parameterName}</h6>
<%--    <s:csrfInput/>--%>
    <button type="submit">로그인</button>
</form>

</body>
</html>
