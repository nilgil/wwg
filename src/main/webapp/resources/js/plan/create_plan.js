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
let map;

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
    lat;
    lng;

    constructor(title, photo, rating, lat, lng) {
        this.title = title;
        this.photo = photo;
        this.rating = rating;
        this.lat = lat;
        this.lng = lng;
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

    // 지도 생성
    createMap();
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
                let lat = current.lat;
                let lng = current.lng;
                spots[i] = new spot(title, photo, rating, lat, lng);

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

            createMap();

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

// 지도 생성
function createMap() {

    let container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(33.380690, 126.545383),
        level: 10
    };

    map = new kakao.maps.Map(container, options);

    // 지도 타입 컨트롤
    let mapTypeControl = new kakao.maps.MapTypeControl();
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 줌 컨트롤
    let zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    createMarker();
}

// 마커 생성, 인포윈도우
function createMarker() {
    let points = [];
    let marker = [];

    for (let i = 0; i < spots.length; i++) {
        points.push(new kakao.maps.LatLng(spots[i].lat, spots[i].lng));
        marker.push(new kakao.maps.Marker({position: points[i]}));
        marker[i].setMap(map);

        // 인포윈도우 생성
        let iwContent = '<div style="padding:5px;">' + spots[i].title + '</div>';

        let infowindow = new kakao.maps.InfoWindow({
            content: iwContent
        });

        // 마커에 마우스오버 이벤트를 등록합니다
        kakao.maps.event.addListener(marker[i], 'mouseover', function () {
            // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
            infowindow.open(map, marker[i]);
        });

        // 마커에 마우스아웃 이벤트를 등록합니다
        kakao.maps.event.addListener(marker[i], 'mouseout', function () {
            // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
            infowindow.close();
        });

        // $("#plan-item").mouseover(function () {
        //     infowindow.open(map, marker[i]);
        // });
    }
}

// -------------------- 이벤트 관련 --------------------

// 검색창 엔터키 이벤트
$("#search-keyword").keydown(function (keyNum) {
    if (keyNum.keyCode == 13) {
        $("#searchBtn").click()
    }
})

// 관광지 마우스오버시 마커 변경


// ----------------------- 유틸 ----------------------

// 날짜 포맷변경
function dateFormatter(date) {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);

    return year + '-' + month + '-' + day;
}