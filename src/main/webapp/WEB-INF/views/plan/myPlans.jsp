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
    <link rel='stylesheet' type='text/css' href='/css/plan/my_plan.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%--    <script defer src="/js/plan/my_plan.js"></script>--%>
</head>
<body>
<input id="userName" type="hidden" value="${userName}"/>
<div id="wrapper">
    <div id="container">
        <header>
            <h1>Header</h1>
        </header>
        <div id="plans-box">
            <c:forEach items="${plans}" var="plan" varStatus="st">
                <div class='plan'>
                    <div id='plan-img'>
                        <img src="${thumbnails[st.index]}" alt="https://via.placeholder.com/150"/>
                    </div>
                    <div id='plan-info'>
                            ${plan.title}
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
