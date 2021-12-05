<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>내 일정</title>
    <%@ include file="/resources/include/headTag.jsp" %>
    <link rel='stylesheet' type='text/css' href='/css/plan/my_plan.css'>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
          integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
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
        <c:if test="${empty plans}">
            <div id="none-plan">
                <br>
                <h3>생성된 일정이 없습니다.</h3>
                <br>
                <a href="/plan">일정 만들기</a>
            </div>
        </c:if>
        <div id="plan-box">
            <c:forEach items="${plans}" var="plan" varStatus="st">
                <div class='plan'>
                    <div id='plan-img'>
                        <a href="/plan/${plan.idx}/detail"><img src="${thumbnails[st.index]}"
                                                              alt="/img/qna/jlogo.png"/></a>
                        <span id="pubToggle" onclick="askReallyPubToggle(${plan.idx},${plan.pub})">
                            <c:if test="${plan.pub==0}"><i class="fas fa-toggle-off fa-2x"></i></c:if>
                            <c:if test="${plan.pub==1}"><i class="fas fa-toggle-on fa-2x"></i></c:if>
                    </span>
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
                            <a href="/plan/${plan.idx}/detail"><p>${plan.title}</p></a>
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
