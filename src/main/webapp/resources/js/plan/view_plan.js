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

function printSpotsInfo() {

}

$(document).ready(function () {
    initVar();

    getPlans(idx).then(response => {
        initPage(response);
        printSpotsInfo();
        getSpotsDetails(response).then(r => {

        });
    });

    createMap();
});

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
    console.log(JSON.stringify(plans[0]));
    console.log(plans[0]);
    return new Promise((resolve => {
        (async () => {
            for (let i = 0; i < plans.length; i++) {
                await $.ajax({
                    url: '/spots/searchArray',
                    method: 'post',
                    data: {"titles": plans[i]},
                    dataType: 'json',
                    success: function (response) {
                        resolve(JSON.parse(response));
                    },
                    error: function () {
                        alert("getSpotsDetail");
                    }
                })
            }
        })();
    }));
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

    // createMarker();
}

function dateFormatter(date) {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);

    return year + '-' + month + '-' + day;
}