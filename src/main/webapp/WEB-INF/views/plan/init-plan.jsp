<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<html>
<head>
    <title>일정짜기</title>
    <%@ include file="/resources/include/headTag.jsp" %>
    <link rel='stylesheet' type='text/css' href='/css/plan/init_plan.css'>
    <script defer src="/js/plan/init_plan.js"></script>
</head>
<body>
<div id="wrapper">
    <%@ include file="/resources/include/navbar.jsp" %>
    <div id="container">
        <form id="initForm" method="post" action="/plan/create">
            <div class="main_img"><img src="/img/qna/plan03.jpg"></div>
            <s:csrfInput/>
            <input name="username" type="hidden" value="${username}"/>
            <div id="calendar">
                <p>출발일</p>
                <input id="departureIn" class="form-control" name="departure" type="date">
            </div>
            <br>
            <div id="howdays">
                <p>여행 기간</p>
                <input hidden="hidden"/>
                <input class="form-control" type="text" id="dayIn" name="days" placeholder=""/>
                <p class="days">일</p>
            </div>
            <br><br>
            <div id="submit" onclick="checkDays()">플랜 만들기</div>
        </form>
    </div>
    <%@ include file="/resources/include/footerbar.jsp" %>
</div>
</body>
</html>
