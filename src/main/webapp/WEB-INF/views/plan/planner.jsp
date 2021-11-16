<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Planner</title>
    <link rel='stylesheet' type='text/css' href='../../../css/plan/planner.css'>
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
