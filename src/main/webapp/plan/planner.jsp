<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <style>
        #wrapper {
            display: flex;
            flex-direction: column;
            height: 100%;
        }

        header {
            text-align: center;
            height: 10%;
            border: 1px solid black;
        }

        #container {
            display: flex;
            flex-direction: column;
            height: 100%;
        }

        #content-header {
            display: flex;
            width: 100%;
            height: 8%;
            border: 1px solid black;
        }

        #content-body {
            display: flex;
            width: 100%;
            height: 92%;

        }

        #plans {
            display: flex;
            flex-direction: column;
            width: 18%;
            border: 1px solid black;
        }

        .plan-item {
            display: flex;
            align-items: center;
            height: 90px;
            border: 1px solid black;
        }

        .plan-item img {
            padding-left: 6px;
            width: 80px;
            height: 80px;
        }

        .plan-item div {
            display: flex;
            flex-direction: column;
            justify-content: space-evenly;
            position: relative;
            width: 100%;
            height: 100%;
            line-height: 15px;
            padding-left: 10px;
            padding-right: 13px;
        }

        .plan-item p {
            margin: 0;
        }

        .plan-item p:nth-child(2) {
            font-size: 13px;
        }
        .plan-item span {
            position: absolute;
            right: 10px;
            top: 10px;
            font-size: 20px;
            border: 1px solid black;
            border-radius: 50%;
            box-sizing: border-box;
            width: 22px;
            height: 22px;
            text-align: center;
            line-height: 22px;
            background-color: ghostwhite;
        }

        #pick {
            display: flex;
            flex-direction: column;
            width: 25%;
            border: 1px solid black;
        }

        #map {
            width: 57%;
            border: 1px solid black;
        }

        #now-day {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 18%;
        }

        #now {
            font-size: 30px;
            width: 40%;
            text-align: right;
        }

        #date {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-evenly;
            line-height: 25px;
            width: 60%;
        }

        #days {
            display: flex;
            align-items: center;
            justify-content: space-evenly;
            width: 54%;
            border: 1px solid black;
            color: dimgray;
        }

        #days div {
            font-size: 25px;
        }

        #day-controll {
            display: flex;
            align-items: center;
            justify-content: space-evenly;
            width: 8%;
            font-size: 40px;
            font-weight: lighter;
            border: 1px solid black;
        }

        #price {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 20%;
            border: 1px solid black;
        }

        #price div {
            width: 50%;
            font-size: 25px;
            color: saddlebrown;
            font-weight: lighter;
            text-align: center;
        }

        #search {
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 150px;
            line-height: 43px;
            border: 1px solid black;
        }

        #search-header {
            background-color: darkorange;
            width: 100%;
            height: 28%;
            font-size: 22px;
            text-align: center;
            font-weight: lighter;
        }

        #search-input {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 100%;
            height: 37%;
        }

        #search-input input {
            width: 70%;
            height: 70%;
        }

        #search-input span {
        }

        #search-input button{
            height: 70%;
        }

        #search-category {
            display: flex;
            align-items: center;
            justify-content: space-evenly;
            width: 100%;
            height: 35%;
        }

        #map img{
            width: 100%;
            height: 100%;
        }


    </style>
</head>
<body>

<div id="wrapper">
    <header>
        <h1>Header</h1>
    </header>
    <div id="container">

        <div id="content-header">
            <div id="now-day">
                <div id="now">Day1</div>
                <div id="date">
                    <div>2021/12/1</div>
                    <div>수요일</div>
                </div>
            </div>
            <div id="days">
                <div>Day1</div>
                <div>Day2</div>
                <div>Day3</div>
                <div>Day4</div>
                <div>Day5</div>
            </div>
            <div id="day-controll">
                <div>+</div>
                <div>-</div>
            </div>
            <div id="price">
                <div>예상 경비 :</div>
                <div>325,000 원</div>
            </div>
        </div>

        <div id="content-body">
            <div id="plans">
                <div class="plan-item">
                    <img src="https://via.placeholder.com/80"/>
                    <div>
                        <p>타이틀</p>
                        <p>관광지</p>
                        <span>1</span>
                    </div>
                </div>
                <div class="plan-item">
                    <img src="https://via.placeholder.com/80"/>
                    <div>
                        <p>타이틀</p>
                        <p>관광지</p>
                        <span>2</span>
                    </div>
                </div>
                <div class="plan-item">
                    <img src="https://via.placeholder.com/80"/>
                    <div>
                        <p>타이틀</p>
                        <p>관광지</p>
                        <span>3</span>
                    </div>
                </div>
                <div class="plan-item">
                    <img src="https://via.placeholder.com/80"/>
                    <div>
                        <p>타이틀</p>
                        <p>관광지</p>
                        <span>4</span>
                    </div>
                </div>
                <div class="plan-item">
                    <img src="https://via.placeholder.com/80"/>
                    <div>
                        <p>타이틀</p>
                        <p>관광지</p>
                        <span>5</span>
                    </div>
                </div>

            </div>

            <div id="pick">
                <div id="search">
                    <div id="search-header">제주도</div>
                    <div id="search-input">
                        <span></span>
                        <input type="text" placeholder="검색어를 입력하세요.">
                        <button>검색</button>
                    </div>
                    <div id="search-category">
                        <div>관광지</div>
                        <div>맛집</div>
                        <div>쇼핑</div>
                        <div>숙박</div>
                    </div>
                </div>
                <div id="search-result">
                    <div class="plan-item">
                        <img src="https://via.placeholder.com/80"/>
                        <div>
                            <p>타이틀</p>
                            <p>관광지</p>
                            <p>👍🏻</p>
                        </div>
                    </div>
                    <div class="plan-item">
                        <img src="https://via.placeholder.com/80"/>
                        <div>
                            <p>타이틀</p>
                            <p>관광지</p>
                            <p>👍🏻 4.7</p>
                        </div>
                    </div>

                </div>
            </div>

            <div id="map">
                <img src="https://png.pngtree.com/element_our/png_detail/20181221/illustrations-of-scenic-spots-on-the-map-of-jeju-island-png_296182.jpg"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
