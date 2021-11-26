<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<%--<s:authentication property="principal" var="user"/>--%>
<%--    ${user.username}--%>
<%--    ${user.password}--%>
<%--    ${user.authorities}--%>


<s:authorize access="hasRole('ROLE_ADMIN')">
    <c:forEach var="i" items="${memberList}">
        <div data-user="${i.username}" class="user-info">
            <c:out value="${i.username}"/>
            <c:out value="${i.location}"/>
            <c:out value="${i.realname}"/>

            <button class="user-del">삭제</button>
        </div>
    </c:forEach>
</s:authorize>
</body>

<script src="${pageContext.request.contextPath}/resources/admin/memberList.js"></script>

<script>
    window.onload=function() {
        console.log("시작");
        let memberList = new MemberList();
        for (let i = 0; i <= document.getElementsByClassName('user-info').length; i++) {
            document.getElementsByClassName('user-info')[i]
                .addEventListener("click", memberList.deleteUser);
        }
    }
</script>
</html>
