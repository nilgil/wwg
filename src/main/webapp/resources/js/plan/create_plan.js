let token;
let header;
const WEEKEND = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
let departure = new Date($('#hiddenDeparture').val()); // 출발일
let days = $('#hiddenDays').val(); // 총 여행기간
let week = WEEKEND[departure.getDay()]; // 현재 Day의 요일
let planOfDays; // 각 날짜별 플랜 저장해둘 객체 배열
let chooseDay; // 현재 선택한 Day
let searchKeyword;
let chooseDayPlans;
let spots = [];

// 날짜별 정보
let day = class {
    departure;
    week;
    plans = [];

    constructor(departure, week) {
        this.departure = departure;
        this.week = week;
    }
};

// 관광지 정보
let spot = class {
    title;
    photo;
    rating;

    constructor(title, photo, rating) {
        this.title = title;
        this.photo = photo;
        this.rating = rating;
    }
}

// INIT
$(document).ready(function () {

    // security용 token, header
    token = $("meta[name='_csrf']").attr("content");
    header = $("meta[name='_csrf_header']").attr("content");

    // 현재 Day,날짜,요일 출력
    $('#now').text("Day1");
    $('#departure').text(dateFormatter(departure));
    $('#week').text(week);

    // 날짜별 플랜 객체 생성
    planOfDays = []; //배열 선언
    chooseDay = 1;
    for (let i = 0; i < days; i++) {
        let dep = new Date(departure);
        dep.setDate(dep.getDate() + i);
        planOfDays[i] = new day(dateFormatter(dep), WEEKEND[dep.getDay()]);
    }

    // 여행 기간에 따른 Days 출력
    makeDays();

    // 관광지 출력
    makeSpots("", "1");
});


// 관광지 화면 출력
function makeSpots(keyword, pageNum) {
    searchKeyword = keyword;

    $.ajax({
        method: "POST",
        url: "/spots/search",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        data: {keyword: keyword, pageNum: pageNum},
        dataType: "json"
    }).done(function (data) {
        $('#search-result').empty();

        let jsonSpots = JSON.parse(data.result);
        let jsonPageInfo = JSON.parse(data.page_info);

        // 관광지 출력
        if (jsonPageInfo.count > 0) {
            for (let i = 0; i < jsonSpots.length; i++) {
                let current = jsonSpots[i];
                let title = current.title;
                let photo = current.photo;
                let rating = current.rating;
                spots[i] = new spot(title, photo, rating, 0);
                console.log(spots[i]);

                $('#search-result').append(
                    "<div class='plan-item'>" +
                    "   <img onclick='viewSpotDetail('" + title + "')' src='" + photo + "'/>\n" +
                    "   <div>\n" +
                    "       <p class='spot-title' onclick='viewSpotDetail('" + title + "')'>" + title + "</p></a>\n" +
                    "       <p>👍🏻  " + rating + "</p>\n" +
                    "   </div>" +
                    "   <div>" +
                    "       <p class='add-plan' onclick=\"spotToPlan('" + title + "'," + i + ")\">+</p>" +
                    "   </div>" +
                    "</div>"
                );
            }

            // 페이징 처리
            let resultCount = jsonPageInfo.count;
            let resultDiv = jsonPageInfo.div;
            let currentPage = jsonPageInfo.pageNum;
            let pageDiv = 5;

            paging(resultCount, resultDiv, pageDiv, currentPage);
        } else {
            $('#search-result').empty();
            $('#search-result').append(
                "<div id='search-fail'>" +
                "검색 결과가 없습니다." +
                "</div>"
            );
            $('#pageBtnsUl').empty();
        }
    });
}

// 페이징 처리
function paging(resultCount, resultDiv, pageDiv, currentPage) {
    let totalPage = Math.ceil(resultCount / resultDiv);
    if (totalPage < pageDiv) {
        pageDiv = totalPage;
    }
    let pageGroup = Math.ceil(currentPage / pageDiv);
    let groupEnd = pageGroup * pageDiv;
    if (totalPage < groupEnd) {
        groupEnd = totalPage;
    }
    let groupStart = groupEnd - pageDiv + 1;
    let next = groupEnd + 1;
    let prev = groupStart - 1;

    let pageHtml = "";
    if (prev > 0) {
        pageHtml += "<li><a href='#' id='prev'> 이전 </a></li>";
    }

    for (let i = groupStart; i <= groupEnd; i++) {
        if (currentPage == i) {
            pageHtml +=
                "<li class='on'><a href='#' id='" + i + "'>" + i + "</a></li>";
        } else {
            pageHtml += "<li><a href='#' id='" + i + "'>" + i + "</a></li>";
        }
    }

    if (next < totalPage) {
        pageHtml += "<li><a href='#' id='next'> 다음 </a></li>";
    }
    $('#pageBtnsUl').html(pageHtml);

    $("#pageBtnsUl li a").click(function () {
        let $id = $(this).attr("id");
        let selectedPage = $(this).text();

        if ($id == "next") selectedPage = next;
        if ($id == "prev") selectedPage = prev;

        //페이징 표시 재호출
        paging(resultCount, resultDiv, pageDiv, selectedPage);
        //글 목록 표시 재호출
        makeSpots(searchKeyword, selectedPage);
    });
}

// 관광지 검색 처리
function clickSearchBtn() {
    searchKeyword = $('#search-keyword').val();
    makeSpots(searchKeyword, "1");
}

// 관광지 + 눌러서 플랜에 추가
function spotToPlan(title, spotNum) {
    chooseDayPlans = planOfDays[chooseDay - 1].plans;
    chooseDayPlans.push(spots[spotNum]);
    makePlans(chooseDayPlans);
}

// Day의 플랜들 출력
function makePlans(chooseDayPlans) {
    $('#plans').empty();
    for (let i = 0; i < chooseDayPlans.length; i++) {
        let currentPlan = chooseDayPlans[i];
        $('#plans').append(
            "<div class='day-plans'>" +
            "   <img onclick='viewSpotDetail('" + currentPlan.title + "')' src='" + currentPlan.photo + "'/>\n" +
            "   <div>\n" +
            "       <p class='spot-title' onclick='viewSpotDetail('" + currentPlan.title + "')'>" + currentPlan.title + "</p></a>\n" +
            "   </div>" +
            "   <div>" +
            "       <p class='remove-plan' onclick=\"removePlan('" + currentPlan.title + "'," + i + ")\">-</p>" +
            "   </div>" +
            "</div>"
        );
    }
}

// 플랜의 관광지 - 눌러서 플랜에서 제거
function removePlan(title, i) {
    chooseDayPlans.splice(i, 1);
    makePlans(chooseDayPlans);
}

// 현재 Day 변경
function changeDay(i) {
    chooseDay = i;
    chooseDayPlans = planOfDays[chooseDay - 1].plans;
    $('#now').text("Day" + i);
    $('#departure').text(planOfDays[i - 1].departure);
    $('#week').text(planOfDays[i - 1].week);
    makePlans(chooseDayPlans)
}

// 여행 기간 1일 추가
function dayPlus() {
    days = Number(days) + 1;
    let dep = new Date(departure);
    dep.setDate(dep.getDate() + planOfDays.length);
    planOfDays.push(new day(dateFormatter(dep), WEEKEND[dep.getDay()]))
    makeDays();
}

// 여행 기간 1일 감소
function dayMinus() {
    if (days > 1) {
        days = Number(days) - 1;
        if (chooseDay == planOfDays.length)
            changeDay(1);
        planOfDays.splice(planOfDays.length - 1, 1);
        let dep = new Date(departure);
        dep.setDate(dep.getDate() + planOfDays.length);
        makeDays();
    }
}

// 여행 기간에 맞춰 Day 출력
function makeDays() {
    $('#days').empty();
    for (let i = 1; i <= days; i++) {
        $('#days').append(
            "<div class='day-btn' onClick='changeDay(" + i + ")'>Day" + i + "</div>"
        )
    }
}

function viewSpotDetail(title) {

}


// -------------------- 지도 관련 --------------------
var container = document.getElementById('map');
var options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 10
};

var map = new kakao.maps.Map(container, options);

var mapTypeControl = new kakao.maps.MapTypeControl();
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

// var points = [
//     for(let i=0; i<)
//     new kakao.maps.LatLng(33.452278, 126.567803)
//
// ];



// -------------------- 이벤트 관련 --------------------

// 검색창 엔터키 이벤트
$("#search-keyword").keydown(function (keyNum) {
    if (keyNum.keyCode == 13) {
        $("#searchBtn").click()
    }
})


// ----------------------- 유틸 ----------------------

// 날짜 포맷변경
function dateFormatter(date) {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);

    return year + '-' + month + '-' + day;
}