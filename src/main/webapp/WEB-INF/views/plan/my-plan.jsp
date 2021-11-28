<%--
  Created by IntelliJ IDEA.
  User: giri
  Date: 17/11/2021
  Time: 6:06 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<head>
    <title>Title</title>
    <%@ include file="/resources/include/headTag.jsp" %>
    <link rel='stylesheet' type='text/css' href='/css/plan/my_plan.css'>
    <script defer src="/js/plan/my_plan.js"></script>
</head>
<body>
<input id="userName" type="hidden" value="${userName}"/>
<div id="wrapper">
    <%@ include file="/resources/include/navbar.jsp" %>
    <div id="container">
        <header>
            <h1>내 일정</h1>
        </header>

        <div id="plan-box">
            <c:forEach items="${plans}" var="plan" varStatus="st">
                <div class='plan'>
                    <div id='plan-img'>
                        <img src="${thumbnails[st.index]}" alt="https://via.placeholder.com/150"/>
                    </div>
                    <div id='plan-info'>
                        <div id="day-info">
                            <p>${plan.departure}</p>
                            <p>
                                <c:if test="${plan.days == 1}">당일치기</c:if>
                                <c:if test="${plan.days != 1}">${plan.days-1}박 ${plan.days}일</c:if>
                            </p>
                        </div>
                        <div id="title">
                            <p>${plan.title}</p>
                        </div>
                        <div id="plan-controll">
                            <p onclick="updatePlanForm(${plan.idx})">수정</p>
                            <p onclick="askReallyDelete(${plan.idx})">삭제</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <%@ include file="/resources/include/footerbar.jsp" %>
</div>
</body>
</html>
