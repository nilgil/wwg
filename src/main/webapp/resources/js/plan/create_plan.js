const WEEKEND = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
let departure = new Date($('#departure').text()); // 출발일
let days = $('#hiddenDays').val(); // 여행기간
let week = WEEKEND[departure.getDay()]; // 요일
let planOfDays; // 각 날짜별 플랜 저장해둘 객체 배열


let day = class {
    constructor(departure, week) {
        this.departure = departure;
        this.week = week;
    }
};

$(document).ready(function () {
    // 요일 화면 출력
    $('#week').text(week);

    // 날짜별 플랜 객체 생성
    planOfDays = []; //배열 선언
    for (let i = 0; i < days; i++) {
        let dep = new Date(departure);
        dep.setDate(dep.getDate() + i);
        planOfDays[i] = new day(dateFormatter(dep), WEEKEND[dep.getDay()]);
    }
});

function changeDay(i) {
    $('#now').text("Day" + i);
    changeNowDate(i);
}

function changeNowDate(i) {
    $('#departure').text(planOfDays[i - 1].departure);
    $('#week').text(planOfDays[i - 1].week);
}

function clickSearchBtn() {
    $.ajax({
        url: './time.php',
        success: function (data) {
            $('#time').append(data);
        }
    })
}


function dayPlus() {
}

function dayMinus() {
}


function dateFormatter(date) {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);

    return year + '-' + month + '-' + day;
}
