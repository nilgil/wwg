<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>

<html>
<head>
    <title>Title</title>
    <link rel='stylesheet' type='text/css' href='/css/plan/init_plan.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script defer src="/js/plan/init_plan.js"></script>
</head>
<body>
<div id="container">
    <form id="initForm" method="post" action="/plan/create">
        <s:csrfInput/>
        <input name="username" type="hidden" value="${username}"/>
        <div id="calendar">
            <p>출발일?</p>
            <input id="departureIn" name="departure" type="date" >
        </div>
        <br>
        <div id="howdays">
            <p>몇일?</p>
            <input hidden="hidden"/>
            <input id="dayIn" name="days" onkeydown="">
        </div>
        <br><br>
        <div id="submit" onclick="checkDays()">플랜 만들기</div>
    </form>
</div>
</body>
</html>
