<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>일정짜기</title>
    <%@ include file="/resources/include/headTag.jsp" %>
    <link rel='stylesheet' type='text/css' href='/css/plan/planner.css'/>
    <script defer src="/js/plan/planner.js"></script>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5375710c5345c7b4704ecd71a402404e"></script>
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5375710c5345c7b4704ecd71a402404e&libraries=clusterer,drawing"></script>
</head>
<body>
<input id="idxForUpdate" type="hidden" value="${idx}"/>
<input id="usernameForUpdate" type="hidden" value="${username}"/>
<input id="hiddenUsername" type="hidden" value="${plan.username}"/>
<input id="hiddenDeparture" type="hidden" value="${plan.departure}"/>
<input id="hiddenDays" type="hidden" value="${plan.days}"/>

<form name="save" id="save" method="post" action="/plan/save">
    <input class="hiddenUsername" name="username" type="hidden"/>
    <input class="hiddenDeparture" name="departure" type="hidden"/>
    <input class="hiddenDays" name="days" type="hidden"/>
    <input class="hiddenTitle" name="title" type="hidden"/>
    <input class="hiddenPlans" name="plans" type="hidden"/>
</form>
<form name="update" id="update" method="post" action="/plan/${idx}">
    <input type="hidden" name="_method" value="PUT"/>
    <input class="hiddenUsername" name="username" type="hidden"/>
    <input class="hiddenDeparture" name="departure" type="hidden"/>
    <input class="hiddenDays" name="days" type="hidden"/>
    <input class="hiddenTitle" name="title" type="hidden"/>
    <input class="hiddenPlans" name="plans" type="hidden"/>
    <input class="hiddenIdx" name="idx" type="hidden"/>
</form>

<div id="wrapper">
    <%@ include file="/resources/include/navbar.jsp" %>
    <div id="container">
        <div id="content-header">
            <div id="day-info">
                <div id="now-day"></div>
                <div id="now-date">
                    <div id="date"></div>
                    <div id="week"></div>
                </div>
            </div>
            <div id="day-btns">
            </div>
            <div id="day-controll">
                <div id="day-plus" onclick="dayPlus()">+</div>
                <div id="day-minus" onclick="dayMinus()">-</div>
            </div>
            <div id="title-box"><input type="text" id="title" name="title" placeholder="제목을 입력해주세요."></div>
            <div id="submit">
                <div id="submitBtn" onclick="checkPlan()">저장</div>
            </div>
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
                    <div id="detail-phone-box">
                        <span id='detail-phone'></span>
                    </div>
                    <div id="detail-address-box">
                        <span id='detail-address'></span>
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
    <%@ include file="/resources/include/footerbar.jsp" %>
</div>
</body>
</html>
