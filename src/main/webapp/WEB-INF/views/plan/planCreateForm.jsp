<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<head>
    <title>Planner</title>
    <link rel='stylesheet' type='text/css' href='/resources/css/plan/planner.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5375710c5345c7b4704ecd71a402404e"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5375710c5345c7b4704ecd71a402404e&libraries=clusterer,drawing"></script>
    <script defer src="/js/plan/create_plan.js"></script>
</head>
<body>
<div id="wrapper">
    <header>
        <h1>Header</h1>
    </header>
    <div id="container">
        <input id="hiddenDays" type="hidden" value="${days}"/>
        <input id="hiddenDeparture" type="hidden" value="${departure}"/>
        <div id="content-header">
            <div id="now-day">
                <div id="now"></div>
                <div id="date">
                    <div id="departure"></div>
                    <div id="week"></div>
                </div>
            </div>
            <div id="days">
            </div>
            <div id="day-controll">
                <div id="day-plus" onclick="dayPlus()">+</div>
                <div id="day-minus" onclick="dayMinus()">-</div>
            </div>
            <div id="title"><p>유저가 지정한 플랜명</p></div>
            <div id="submit"><p>저장</p></div>
        </div>

        <div id="content-body">
            <div id="plans">
            </div>
            <div id="pick">
                <div id="search">
                    <div id="search-input">
                        <input id="search-keyword" type="text" placeholder="검색어를 입력하세요.">
                        <button id="searchBtn" onclick="clickSearchBtn()">검색</button>
                    </div>
                </div>
                <div id="search-result">
                </div>
                <div id="pageBtns">
                    <ul id="pageBtnsUl">
                    </ul>
                </div>
            </div>

            <div id="map" ></div>
            <script>

            </script>
        </div>
    </div>
</div>
</body>
</html>
