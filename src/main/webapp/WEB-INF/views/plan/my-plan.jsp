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
<%--    <script defer src="/js/plan/my_plan.js"></script>--%>
</head>
<body>
<input id="userName" type="hidden" value="${userName}"/>
<div id="wrapper">
    <%@ include file="/resources/include/navbar.jsp" %>
    <div id="container">
        <div id="plans-box">
            <c:forEach items="${plans}" var="plan" varStatus="st">
                <div class='plan'>
                    <div id='plan-img'>
                        <img src="${thumbnails[st.index]}" alt="https://via.placeholder.com/150"/>
                    </div>
                    <div id='plan-info'>
                        <div id="day-info"><${plan.departure}/div>
                        <div id="title-info">${plan.title}</div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <%@ include file="/resources/include/footerbar.jsp" %>
</div>
</body>
</html>
