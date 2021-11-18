const WEEKEND = ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'];
let departure = new Date($('#departure').text()); // 출발일
let days = $('#hiddenDays').val(); // 여행기간
let week = WEEKEND[departure.getDay()]; // 요일
let planOfDays; // 각 날짜별 플랜 저장해둘 객체 배열
let globalCurrentPage;
let searchKeyword;
let token;
let header;

// 날짜별 정보
let day = class {
    constructor(departure, week) {
        this.departure = departure;
        this.week = week;
        this.plans = "";
    }
};

// INIT
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

    // 검색창 엔터키 이벤트
    $("#search-keyword").keydown(function (keyNum) {
        if (keyNum.keyCode == 13) {
            $("#searchBtn").click()
        }
    })

    // security 403 해결
    token = $("meta[name='_csrf']").attr("content");
    header = $("meta[name='_csrf_header']").attr("content");

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
                let $photo = current.photo;
                let $title = current.title;
                let $rating = current.rating;
                $('#search-result').append(
                    "<div class='plan-item'>" +
                    "   <img src='" + $photo + "'/>\n" +
                    "   <div>\n" +
                    "       <p onclick='viewSpotDetail()'>" + $title + "</p></a>\n" +
                    "       <p>👍🏻  " + $rating + "</p>\n" +
                    "   </div>" +
                    "   <div>" +
                    "       <p onclick='spotToPlan()'>+</p>" +
                    "   </div>" +
                    "</div>" +
                    "</a>"
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

        //전역변수에 선택한 페이지 번호를 담는다...
        globalCurrentPage = selectedPage;
        //페이징 표시 재호출
        paging(resultCount, resultDiv, pageDiv, selectedPage);
        //글 목록 표시 재호출
        makeSpots(searchKeyword, selectedPage);
    });
}

function clickSearchBtn() {
    searchKeyword = $('#search-keyword').val();
    makeSpots(searchKeyword, "1");
}

function spotToPlan() {

}

function viewSpotDetail() {

}

// Day 변경시 실행
function changeDay(i) {
    $('#now').text("Day" + i);
    changePlan(i);
}

function changePlan(i) {
    $('#departure').text(planOfDays[i - 1].departure);
    $('#week').text(planOfDays[i - 1].week);
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
