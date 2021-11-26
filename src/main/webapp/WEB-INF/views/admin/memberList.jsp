<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>


<html>
<head>
    <title>Title</title>
    <%@ include file="/resources/include/headTag.jsp"%>
    <link rel='stylesheet' media='screen' href='${path}/css/qna/memberList.css'>

    
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
    
    
</head>

<body>
<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>

<!-- cneter -->
<%--<s:authentication property="principal" var="user"/>--%>
<%--    ${user.username}--%>
<%--    ${user.password}--%>
<%--    ${user.authorities}--%>

<!-- hasRole('ROLE_ADMIN') -->

<div class="memberList_center">

<div class="memberList_side"> 
<table id="side_menu" class="table table-hover">
<thead>
<tr><th>관리자모드</th></tr>
</thead>
<tbody>
<tr><td>회원관리/삭제</td></tr>
</tbody>
</div>

<div class="table">
<table class="table table-hover">
<s:authorize access="permitAll">
    <c:forEach var="i" items="${memberList}">
        <div data-user="${i.username}" class="user-info">
            <c:out value="${i.username}"/>
            <c:out value="${i.location}"/>
            <c:out value="${i.realname}"/>

            <button class="user-del">삭제</button>
        </div>
    </c:forEach>
</s:authorize>
</table>
</div>




</div>

<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>
</body>

</html>
