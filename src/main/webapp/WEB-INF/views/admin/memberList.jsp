<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
${test}
<c:forEach var="i" items="${memberList}">
    <div data-user="${i.username}" class="user-info">
        <c:out value="${i.username}"/>
        <c:out value="${i.location}"/>
        <c:out value="${i.realname}"/>
    </div>
    <button class="user-del">삭제</button>
</c:forEach>
</body>
<script src="${pageContext.request.contextPath}/resources/admin/memberList.js"></script>
</html>
