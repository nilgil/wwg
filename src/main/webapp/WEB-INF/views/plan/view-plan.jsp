<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/resources/include/headTag.jsp" %>
    <link rel='stylesheet' type='text/css' href='/css/plan/view_plan.css'>
    <script defer src="/js/plan/view_plan.js"></script>
</head>
<body>
<div id="wrapper">
    <%@ include file="/resources/include/navbar.jsp" %>
    <div id="container">
        <div id="content-header">
            <div id="plan-info">
                <div id="user-name">${plan.username}</div>
                <div id="plan-title">${plan.title}</div>
                <div id="plan-departure">${plan.departure}</div>
                <div id="plan-days">${plan.days}</div>
            </div>
            <div id="features">
                <div id="good">${plan.good}</div>
                <div id="hit">${plan.hit}</div>
                <div id="pub">${plan.pub}</div>
            </div>
        </div>
        <div id="content-body">
            <div id="plans"></div>
            <button onclick="goodToggle(${plan.idx})">좋아요!</button>
        </div>
    </div>
    <%@ include file="/resources/include/footerbar.jsp" %>
</div>

</body>
</html>
