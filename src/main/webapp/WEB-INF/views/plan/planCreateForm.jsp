<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<head>
    <title>Planner</title>
    <link rel='stylesheet' type='text/css' href='/css/plan/planner.css'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5375710c5345c7b4704ecd71a402404e"></script>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5375710c5345c7b4704ecd71a402404e&libraries=clusterer,drawing"></script>
    <script defer src="/js/plan/create_plan.js"></script>
</head>
<body>
<div id="wrapper">
    <header>
        <h1>Header</h1>
    </header>
    <s:csrfInput/>
    <input name="days" id="hiddenDays" type="hidden" value="${plan.days}"/>
    <input name="username" id="hiddenUsername" type="hidden" value="${plan.username}"/>
    <input id="hiddenDepartureL" type="hidden" value="${plan.departure}"/>
    <input name="departure" id="hiddenDeparture" type="hidden"/>
    <input name="plans" id="hiddenPlans" type="hidden"/>
    <div id="container">
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
            <div id="title-box"><input type="text" id="title" name="title" placeholder="제목을 입력해주세요."></div>
            <div id="submit" onclick="submitPlan()"><p>저장</p></div>
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

            <div id="map"></div>
            <div id="detail-view">
                <img id='detail-img'/>
                <div id="detail-box">
                    <div id="detail-title-box">
                        <span id='detail-title'></span>
                    </div>
                    <div id="detail-rating-box">
                        <span id='detail-rating'></span>
                    </div>
                    <div id='detail-info-box'>
                        <span id='detail-info'></span>
                    </div>

                </div>
                <div id='close-box' onclick="detailToMap()">
                    <span id='detail-close'>X</span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
