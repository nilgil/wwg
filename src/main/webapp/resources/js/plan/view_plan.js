let idx;
let writer;
let title;
let departure;
let days;
let plans;
let good;
let hit;
let pub;

let username;
let beforeUrl;
let alreadyGood;
let plan;

let spotsOfDays = [];
let chooseDay = 1;
let dayMoveLength;

$(document).ready(function () {
    $('#detail-view').hide();
    initVar();

    getPlans(idx).then(response => {
        initPage(response);
        printSpotsInfo();
        getSpotsDetails(response).then(() => {
            printSpotsInfo();
            printDaysforMap();
            createMap(1);
        });
    });

})
;

function initVar() {
    beforeUrl = window.location.href;
    idx = $('#hiddenIdx').val();
    username = $('#hiddenUsername').val();
    alreadyGood = $('#hiddenIsAlreadyGood').val();
}

function getPlans(idx) {
    return new Promise((resolve => {
        $.ajax({
            url: `/plan/${idx}`,
            method: 'get',
            data: {'idx': idx},
            dataType: 'json',
            success: function (response) {
                plan = response;
                resolve(response);
            },
            error: function () {
                alert("getPlans");
            }
        })
    }));
}

function initPage(response) {
    writer = response.username;
    title = response.title;
    departure = response.departure;
    days = response.days;
    plans = JSON.parse(response.plans);
    good = response.good;
    hit = response.hit;
    pub = response.pub;

    $('#plan-title').text(title);
    $('#user-name').text(writer);

    let depDate = new Date(departure);
    depDate.setDate(depDate.getDate() + days - 1);
    $('#plan-date').text(departure + " ~ " + dateFormatter(depDate));
    $('#plan-days').text(days == 1 ? "당일치기" : "(" + (days - 1) + "박 " + days + "일)");

    if (pub == 0) {
        $('#pub span').html("비공개");
    }

    $('#good span:nth-child(2)').text(good);
    $('#hit span:nth-child(2)').text(hit);

    if (alreadyGood == "true") {
        $('#heart').css({"fontWeight": "bolder"});
    }
}

function getSpotsDetails(response) {
    let plans = JSON.parse(response.plans);
    return new Promise((resolve => {
        (async () => {
            for (let i = 0; i < plans.length; i++) {
                await $.ajax({
                    url: '/spots/searchArray',
                    method: 'post',
                    data: {"titles": JSON.stringify(plans[i])},
                    success: function (response) {
                        spotsOfDays[i] = response;
                    },
                    error: function () {
                        alert("getSpotsDetail");
                    }
                })
            }
            resolve(spotsOfDays);
        })();
    }));
}

function printSpotsInfo() {
    for (let i = 0; i < spotsOfDays.length; i++) {
        $('#plans').append(
            `<div class='day-header'>Day${i + 1}</div>`
        );
        for (let j = 0; j < spotsOfDays[i].length; j++) {
            let spot = spotsOfDays[i][j];
            $('#plans').append(
                `<div class='day-spots' id='day${i + 1}-spots${j + 1}' onclick="viewSpotDetail(spotsOfDays[${i}][${j}])">` +
                `  <img src='${spot.photo}'>` +
                `  <div class='spot-title'>${spot.title}</div>` +
                `</div>`
            );
        }
        if (spotsOfDays[i].length == undefined) {
            $('#plans').append(
                `<div class="none-spots"><div>일정이 없습니다.</div></div>`
            );
        }
    }
}

function printDaysforMap() {
    for (let i = 0; i < spotsOfDays.length; i++) {
        $('#days').append(
            `<div class="day" onclick="createMap(${i + 1})">Day${i + 1}</div>`
        );
    }
}


function goodToggle(idx, username) {
    $.ajax({
        url: '/plan/good',
        method: 'put',
        data: {"idx": idx, 'username': username, 'beforeUrl': beforeUrl},
        dataType: 'text',
        success: function (response) {
            console.log(response);
            if (response == "guest") {
                location.replace('/loginPage');
            } else if (response == "true") {
                good = good - 1;
                $('#good span:nth-child(2)').text(good);
                $('#heart').css({"fontWeight": "normal"});
            } else if (response == "false") {
                good = good + 1;
                $('#good span:nth-child(2)').text(good);
                $('#heart').css({"fontWeight": "bolder"});
            }
        }
    })
}

function viewSpotDetail(spot) {
    $('#map').hide();
    $('#detail-view').empty();

    $('#detail-view').append(
        `<img src="${spot.photo}">` +
        `<div id="detail-title">${spot.title}</div>` +
        `<div id="detail-info">${spot.info}</div>` +
        `<div id="close" onclick="detailToMap()">X</div>`
    );

    $('#detail-view').show();
}

function detailToMap() {
    $('#detail-view').empty();
    $('#detail-view').hide();
    $('#map').show();
}

// 지도 생성
function createMap(day) {
    chooseDay = day;
    $(`.day`).css({'color': 'black'});
    $(`.day:nth-child(${day})`).css({'color': 'rgba(217,117,22,0.8)'});

    $('#detail-view').hide();
    $('#map').show();

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

    createMarkerAndLine(day);
}

function createMarkerAndLine(day) {
    let points = [];
    let marker = [];
    let linePath = [];

    let target = document.querySelectorAll('.day-spots');

    for (let i = 0; i < spotsOfDays[day - 1].length; i++) {
        let daySpots = spotsOfDays[day - 1][i];
        points.push(new kakao.maps.LatLng(daySpots.lat, daySpots.lng));
        marker.push(new kakao.maps.Marker({position: points[i]}));
        marker[i].setMap(map);

        // 선 그을 위치 정보 저장
        linePath.push(new kakao.maps.LatLng(daySpots.lat, daySpots.lng));

        // 인포윈도우 생성
        let iwContent = '<div style="padding:5px; overflow: hidden; white-space: nowrap; font-size: 14px;">' + (i + 1) + '. ' + daySpots.title + '</div>';

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

    let polyline = new kakao.maps.Polyline({
        path: linePath, // 선을 구성하는 좌표배열 입니다
        strokeWeight: 5, // 선의 두께 입니다
        strokeColor: '#d77045', // 선의 색깔입니다
        strokeOpacity: 0.9, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'solid' // 선의 스타일입니다
    });
    dayMoveLength = Math.round(polyline.getLength() / 100) / 10;
    polyline.setMap(map);
    printDayInfo(day);
}

function printDayInfo(day) {
    $('#choose-day').text(`Day${chooseDay}`)

    let date = new Date(plan.departure);
    date.setDate(date.getDate() + day - 1);
    $('#day-date').text(dateFormatter(date));

    let spotsCount = spotsOfDays[chooseDay - 1].length;
    if (spotsCount == null) {
        spotsCount = 0;
    }
    $('#day-spots-count').text(`일정 개수 : ${spotsCount}개`);
    $('#day-move-length').text(`이동거리 : 약 ${dayMoveLength}km`);
}

function dateFormatter(date) {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return year + '-' + month + '-' + day;
}