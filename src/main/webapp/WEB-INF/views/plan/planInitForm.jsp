<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>

<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script defer src="/resources/js/plan/init_plan.js"></script>
</head>
<body>
<div id="container">
    <form method="post" action="/planner/create_plan">
        <div id="calendar">
            <p>출발일?</p>
            <input type="date" name="departure" id="depDate" >
        </div>

        <div id="howdays">
            <p>몇일?</p>
            <input type="number" name="days">
        </div>

        <div id="submit">
            <input type="submit" value="플랜만들기">
        </div>
        <s:csrfInput/>
    </form>
</div>
</body>
</html>
