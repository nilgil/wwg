// ------------------------------- 전역 변수 -------------------------------
const WEEKEND = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일']; // 요일 배열

let username; // 접속한 유저명 (작성자)
let departure;  // 출발일
let days; // 여행 기간

let idx; // 업데이트인 경우 idx에 값이 할당됨
let oldTitle; // 업데이트시 이전 일정 이름
let oldPlans; // 업데이트시 이전 관광지 목록

let chooseDay; // 현재 선택한 여행일
let now_date; // 선택한 여행일의 날짜
let now_week; // 선택한 여행일의 요일

let dayInfoArr = []; // 각 날짜별 플랜 저장해둘 객체 배열
let searchKeyword; // 현재 검색어
let spots = []; // 현재 화면의 검색된 관광지들

let map; // 지도

// ------------------------------- 클래스 -------------------------------
// 각 여행일 정보 (날짜, 요일, 일정들)
let DayInfo = class {
    date;
    week;
    spots;

    constructor(date, week) {
        this.date = date;
        this.week = week;
        this.spots = [];
    }
};

// 관광지 세부정보 (제목, 사진경로, 평점, 위도, 경도)
let SpotInfo = class {
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

// ------------------------------- Init -------------------------------
$(document).ready(async function () {
    // 디테일 뷰 숨기기
    $('#detail-view').hide();

    initVar();

    // Update Plan으로 들어온 경우와 아닌 경우 분리
    if (idx != "") {
        await initForUpdate().then(async (oldPlans) => await getSpotDetailByTitles(oldPlans)).then(async () => await makePlans());
    } else {
        for (let i = 0; i < days; i++) {
            let depDate = new Date(departure);
            depDate.setDate(depDate.getDate() + i);
            dayInfoArr[i] = new DayInfo(dateFormatter(depDate), WEEKEND[depDate.getDay()]);
        }
    }

    // 현재 여행일,날짜,요일 출력
    await printNowDayInfo();

    // 여행 기간에 따른 Days 버튼 출력
    await makeDays();

    // 관광지 출력
    await makeSpots("", 1);

    // 일정의 관광지 출력
    await makePlans();

    // 지도 생성
    await createMap();
});

// 전역변수 초기화
function initVar() {
    username = $('#hiddenUsername').val();
    if ($('#usernameForUpdate').val() != "") {
        username = $('#usernameForUpdate').val();
    }
    departure = new Date($('#hiddenDeparture').val());
    days = $('#hiddenDays').val();
    idx = $('#idxForUpdate').val();

    chooseDay = 1;
    now_date = departure;
    now_week = WEEKEND[departure.getDay()];
}

// 업데이트인 경우 이전 일정 데이터 가져와서 변수 초기화 (Ajax-GET)
function initForUpdate() {
    return new Promise((resolve => {
        $.ajax({
            url: `/plan/${idx}`,
            type: 'GET',
            dataType: 'json',
            success: function (response) {
                departure = new Date(response.departure);
                now_week = WEEKEND[departure.getDay()];
                days = response.days;
                oldTitle = response.title;
                oldPlans = JSON.parse(response.plans);
                $('#title').val(oldTitle);
                resolve(oldPlans);
            },
            error: function () {
                alert("initForUpdate");
            }
        });
    }))
}

// 이전 일정 데이터의 관광지 목록으로 세부정보 구하고 dayInfoArr[]에 저장
function getSpotDetailByTitles(oldPlans) {
    return new Promise((resolve => {
        (async () => {
            for (let i = 0; i < days; i++) {
                await $.ajax({
                    url: `/spot/list/titles`,
                    type: 'get',
                    data: {titles: JSON.stringify(oldPlans[i])},
                    dataType: 'json',
                    success: function (response) {
                        let depDate = new Date(departure);
                        depDate.setDate(depDate.getDate() + i);
                        dayInfoArr[i] = new DayInfo(dateFormatter(depDate), WEEKEND[depDate.getDay()]);

                        for (let j = 0; j < response.length; j++) {
                            let spot = response[j];
                            dayInfoArr[i].spots.push(new SpotInfo(spot.title, spot.photo, spot.rating, spot.lat, spot.lng));
                        }
                        resolve(response);
                    },
                    error: function () {
                        alert("getSpotDetailByTitles");
                    }
                })
            }
        })();
    }));
}

// 현재 여행일,날짜,요일 출력
function printNowDayInfo() {
    $('#now-day').text(`Day${chooseDay}`);
    $('#date').text(dateFormatter(departure));
    $('#week').text(now_week);
}

// 전체 여행 기간에 맞춰 Day 버튼 출력
function makeDays() {
    $('#day-btns').empty();
    for (let i = 1; i <= days; i++) {
        $('#day-btns').append(
            "<div class='day-btn' id='day-btn-" + i + "' onClick='changeDay(" + i + ")'>Day" + i + "</div>"
        )
    }
    $(`#day-btn-${chooseDay}`).css("color", "#0f74a8");
}

// 관광지 화면 출력 (로직X)
function makeSpots(keyword, page) {
    // 전역변수에 현재 검색 키워드 저장
    searchKeyword = keyword;

    // 검색한 관광지 목록 화면에 출력
    searchSpots(keyword, page).then((response) =>
        printSearchResult(response)
    );
}

// 검색어, 페이지 번호로 관광지 목록 구하기 (Ajax-GET)
function searchSpots(keyword, page) {
    return new Promise((resolve) => {
        $.ajax({
            method: "GET",
            url: `/spot/list/keyword/${page}`,
            data: {keyword: keyword},
            dataType: "json",
            success: function (response) {
                resolve(response);
            },
            error: function () {
                alert("searchSpots");
            }
        })
    });
}

// 검색한 관광지 목록, 페이지 버튼 화면에 출력
function printSearchResult(response) {
    // 이전 검색 결과 지우기
    $('#search-result').empty();
    spots = [];
    // 현재 검색 결과 Parse
    let jsonSpots = JSON.parse(response.result);
    let jsonPageInfo = JSON.parse(response.page_info);

    // 검색 결과가 없을 때
    if (jsonPageInfo.count < 1) {
        $('#search-result').empty();
        $('#search-result').append(
            "<div id='search-fail'>" +
            "검색 결과가 없습니다." +
            "</div>"
        );
        $('#pageBtnsUl').empty();
        createMap();
        return;
    }

    // 각 관광지 화면 출력
    for (let i = 0; i < jsonSpots.length; i++) {
        let spot = jsonSpots[i];
        spots[i] = new SpotInfo(spot.title.trim(), spot.photo, spot.rating, spot.lat, spot.lng);
        $('#search-result').append(
            "<div class='plan-item'>" +
            "   <img onclick='viewSpotDetail('" + spots[i].title + "')' src='" + spots[i].photo + "'/>\n" +
            "   <div>\n" +
            "       <p class='spot-title' onclick=\"viewSpotDetail(" + `'${spots[i].title}'` + ")\">" + spots[i].title + "</p></a>\n" +
            "   </div>" +
            "   <div>" +
            "       <p class='add-plan' onclick=\"addPlan('" + spots[i].title + "'," + i + ")\">+</p>" +
            "   </div>" +
            "</div>"
        );
    }

    // 지도 다시 만들기
    createMap();

    // 페이징 처리
    paging(jsonPageInfo.count, jsonPageInfo.divPlans, jsonPageInfo.divPages, jsonPageInfo.page);
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
        pageHtml += "<li><span id='prev'> 이전 </span></li>";
    }

    for (let i = groupStart; i <= groupEnd; i++) {
        if (currentPage == i) {
            pageHtml +=
                "<li class='on'><span id='" + i + "'>" + i + "</span></li>";
        } else {
            pageHtml += "<li><span id='" + i + "'>" + i + "</span></li>";
        }
    }

    if (next < totalPage) {
        pageHtml += "<li><span id='next'> 다음 </span></li>";
    }
    $('#pageBtnsUl').html(pageHtml);

    // 페이지 버튼 클릭시 이벤트
    $("#pageBtnsUl li span").click(function () {
        let $id = $(this).attr("id");
        let selectedPage = $(this).text(); // 현재 선택된 페이지번호

        if ($id == "next") selectedPage = next;
        if ($id == "prev") selectedPage = prev;

        //글 목록 표시 재호출
        makeSpots(searchKeyword, selectedPage);
    });
}

// 관광지 검색 이벤트 처리 (로직X)
function clickSearchBtn() {
    searchKeyword = $('#search-keyword').val();
    makeSpots(searchKeyword, "1");
}

// 선택한 날짜의 일정 관광지들 출력
function makePlans() {
    $('#plans').empty();
    for (let i = 0; i < dayInfoArr[chooseDay - 1].spots.length; i++) {
        let currentPlan = dayInfoArr[chooseDay - 1].spots[i];
        $('#plans').append(
            "<div class='day-plans'>" +
            "   <img onclick='viewSpotDetail('" + currentPlan.title + "')' src='" + currentPlan.photo + "'/>\n" +
            "   <div>\n" +
            "       <p class='spot-title' onclick=\"viewSpotDetail(" + `'${currentPlan.title}'` + ")\">" + currentPlan.title + "</p></a>\n" +
            "   </div>" +
            "   <div>" +
            "       <p class='remove-plan' onclick=\"removePlan('" + currentPlan.title + "'," + i + ")\">-</p>" +
            "   </div>" +
            "</div>"
        );
    }
}

// 관광지 검색 목록 + 눌러서 플랜에 추가
function addPlan(title, spotNum) {
    dayInfoArr[chooseDay - 1].spots.push(spots[spotNum]);
    makePlans();
}

// 내 플랜의 관광지 - 눌러서 플랜에서 제거
function removePlan(title, i) {
    dayInfoArr[chooseDay - 1].spots.splice(i, 1);
    makePlans();
}

// 현재 여행일 변경
function changeDay(i) {
    $('.day-btn').css("color", "dimgray");
    chooseDay = i;
    $('#now-day').text("Day" + i);
    $('#date').text(dayInfoArr[i - 1].date);
    $('#week').text(dayInfoArr[i - 1].week);

    makePlans();
    $(`#day-btn-${i}`).css("color", "#0f74a8");
}

// 여행 기간 + 눌러서 1일 추가
function dayPlus() {
    days = Number(days) + 1;

    let dep = new Date(departure);
    dep.setDate(dep.getDate() + dayInfoArr.length);
    dayInfoArr.push(new DayInfo(dateFormatter(dep), WEEKEND[dep.getDay()]))
    makeDays();
}

// 여행 기간 - 눌러서 1일 감소
function dayMinus() {
    if (days > 1) {
        if (chooseDay == dayInfoArr.length)
            changeDay(1);

        days = Number(days) - 1;
        dayInfoArr.splice(dayInfoArr.length - 1, 1);
        makeDays();
    }
}

// 제목으로 관광지 세부정보 불러와 출력 (Ajax-GET)
function viewSpotDetail(title) {
    $.ajax({
        url: `/spot/title`,
        method: "GET",
        data: {title: title},
        dataType: "json"
    }).done(function (response) {
        $('#detail-img').attr('src', response.photo);
        $('#detail-title').text(response.title);
        $('#detail-info').text(response.info);
        $('#detail-phone').text("📞 " + response.phone);
        $('#detail-address').text("📮 " + response.address);

        $('#map').hide();
        $('#detail-view').show();
    });
}

// 관광지 세부정보 뷰에서 지도 뷰로 전환
function detailToMap() {
    $('#detail-view').hide();
    $('#map').show();
}

// 일정 저장/업데이트
function submitPlan() {
    let plans = []; // 각 여행일의 플랜을 한번에 담을 2차 배열
    for (let i = 0; i < dayInfoArr.length; i++) {
        let dayPlan = dayInfoArr[i].spots; // 각 여행일의 하루 일정들 정보
        let titles = []; // 하루 일정들의 관광지명만 추출하여 담을 배열
        for (let j = 0; j < dayPlan.length; j++) {
            titles.push(dayPlan[j].title);
        }
        plans.push(titles); // 하루의 일정을 관광지명만 추출하여 저장
    }
    let jsonPlans = JSON.stringify(plans); // 추출한 일정들을 JSON 형식으로 변환

    $('.hiddenUsername').val(username);
    $('.hiddenDeparture').val(dateFormatter(departure));
    $('.hiddenDays').val(Number(days));
    $('.hiddenTitle').val($("#title").val());
    $('.hiddenPlans').val(jsonPlans);
    $('.hiddenIdx').val(Number(idx));

    if (idx != "") {
        $('#update').submit();
    } else {
        $('#save').submit();
    }
}

// ---------------------------- 지도 ----------------------------
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

// 마커, 인포윈도우 생성
function createMarker() {
    let points = [];
    let marker = [];
    let target = document.querySelectorAll('.plan-item');

    for (let i = 0; i < spots.length; i++) {
        points.push(new kakao.maps.LatLng(spots[i].lat, spots[i].lng));
        marker.push(new kakao.maps.Marker({position: points[i]}));
        marker[i].setMap(map);

        // 인포윈도우 생성
        let iwContent = '<div style="padding:5px; overflow: hidden; white-space: nowrap; font-size: 14px;">' + spots[i].title + '</div>';

        let infowindow = new kakao.maps.InfoWindow({
            content: iwContent
        });

        // 마커 클릭 이벤트 : 관광지 세부정보 표시
        kakao.maps.event.addListener(marker[i], 'click', function () {
            viewSpotDetail(spots[i].title);
        });

        // 마커 마우스오버 이벤트 : 인포윈도우 표시
        kakao.maps.event.addListener(marker[i], 'mouseover', function () {
            infowindow.open(map, marker[i]);
        });

        // 마커 마우스아웃 이벤트 : 인포윈도우 제거
        kakao.maps.event.addListener(marker[i], 'mouseout', function () {
            infowindow.close();
        });

        // 관광지 목록 마우스오버 이벤트 : 인포윈도우 표시
        target[i].addEventListener('mouseover', function () {
            infowindow.open(map, marker[i]);
        });

        // 관광지 목록 마우스아웃 이벤트 : 인포윈도우 제거
        target[i].addEventListener('mouseout', function () {
            infowindow.close();
        });
    }
}

// --------------------------- 그 외 ---------------------------
// 저장시 유효성 검사
function checkPlan() {
    if ($('#title').val() == "") {
        alert("일정 제목을 입력해주세요.");
        $('#title').focus();
        return false;
    }
    submitPlan();
}

// 검색창 엔터 이벤트
$("#search-keyword").keydown(function (keyNum) {
    if (keyNum.keyCode == 13) {
        $("#searchBtn").click()
    }
})

// 날짜 포맷변경
function dateFormatter(date) {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);

    return year + '-' + month + '-' + day;
}