<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/resources/include/headTag.jsp" %>
    <link rel='stylesheet' type='text/css' href='/css/plan/view_plan.css'>
    <script defer src="/js/plan/view_plan.js"></script>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5375710c5345c7b4704ecd71a402404e"></script>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5375710c5345c7b4704ecd71a402404e&libraries=clusterer,drawing"></script>
</head>
<body>
<input id="hiddenIdx" type="hidden" value="${idx}">
<input id="hiddenUsername" type="hidden" value="${username}">
<div id="wrapper">
    <%@ include file="/resources/include/navbar.jsp" %>
    <div id="container">
        <div id="content">
            <div id="content-header">

                <div id="plan-writer">
                    <p>작성자</p>
                    <div id="user-name"></div>
                </div>

                <div id="plan-main">
                    <div id="plan-title"></div>
                    <div id="plan-dayInfo">
                        <div id="plan-date"></div>
                        <div id="plan-days"></div>
                    </div>
                </div>

                <div id="features">
                    <div id="pub"><span>공개</span></div>
                    <div id="good"><span><i class="fas fa-heart"></i>️</span>&nbsp;<span></span></div>
                    <div id="hit"><span><i class="fas fa-search"></i></span>&nbsp;<span></span></div>
                </div>
            </div>

            <div id="sub-menu">
                <div id="goodToggle"><i class="far fa-heart fa-3x" onclick="goodToggle(idx, username)"></i></div>
                <div id="total-spots-count"></div>
                <div id="days"></div>
            </div>

            <div id="content-body">
                <div id="plans"></div>
                <div id="map"></div>
            </div>
        </div>
    </div>
    <%@ include file="/resources/include/footerbar.jsp" %>
</div>

</body>
</html>
