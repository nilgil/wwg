<%--
  Created by IntelliJ IDEA.
  User: giri
  Date: 15/11/2021
  Time: 2:35 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Search Result</title>
</head>
<body>
<c:forEach items="${items}" var="item">
    <p><c:out value="${item.title}"/></p>
</c:forEach>
</body>
</html>
