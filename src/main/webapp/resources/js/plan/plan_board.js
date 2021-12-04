$(document).ready(function () {
    resetBoard(1);
});

let pageNum;
// 페이지 번호로 전체 화면 재출력
function resetBoard(page) {
    pageNum = page;
    getBoard(page).then((data) => {
        printCount(data);
        printBoard(data);
        printPaging(data);
    });
}

// 일정 게시글, 페이지 정보 가져오기
function getBoard(page) {
    return new Promise((resolve => {
        $.ajax({
            url: "/plan/pub-list",
            method: "GET",
            data: {'page': page},
            dataType: 'json',
            success: function (response) {
                let data = JSON.parse(response);
                resolve(data);
            },
            error: function () {
                alert("getBoard");
            }
        })
    }));
}

// 전체 일정 개수 출력
function printCount(data) {
    let count = data.pageInfo.count;
    $('#count').text(count);
}

// 일정 리스트 출력
function printBoard(data) {
    $('#boardContent').empty();

    let plans = data.plans;
    for (let i = 0; i < plans.length; i++) {
        let date = new Date(plans[i].regDate);
        $('#boardContent').append(
            `   <tr>` +
            `       <td>${plans[i].idx}</td>` +
            `       <td><a href="/plan/view/${plans[i].idx}">${plans[i].title}</a></td>` +
            `       <td style="width: 120px">${plans[i].days == 1 ? "당일치기" : plans[i].days - 1 + "박 " + plans[i].days + "일"}</td>` +
            `       <td style="width: 100px">${plans[i].username}</td>` +
            `       <td style="width: 250px">` +
            `            <span>${date.toLocaleString()}</span>` +
            `       </td>` +
            `       <td style="width: 80px">${plans[i].hit}</td>` +
            `       <td style="width: 80px">${plans[i].good}</td>` +
            `   </tr>`
        );
    }
}

// 페이징 버튼 출력
function printPaging(data) {
    $('#paging').empty();

    let pageInfo = data.pageInfo;
    if (pageInfo.startPage > pageInfo.divPages) {
        $('#paging').append(`<li><a class="pages" onclick="resetBoard(${pageInfo.startPage - 1})">이전</a></li>`);
    }

    for (let i = pageInfo.startPage; i <= (pageInfo.endPage); i++) {
        $('#paging').append(`<li ${pageInfo.page == i ? "class=\"active\"" : ""}>
        <a class="pages" onclick="resetBoard(${i})">${i}</a></li>`);
    }

    if (pageInfo.endPage < pageInfo.totalPage) {
        $('#paging').append(`<li><a class="pages" onclick="resetBoard(${pageInfo.endPage + 1})">다음</a></li>`);
    }
}