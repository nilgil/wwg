// 출발일 기본값 오늘로 설정
Date.prototype.toDateInputValue = (function () {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0, 10);
});

$(document).ready(function () {
    $('#departureIn').val(new Date().toDateInputValue());
});


function checkDays() {
    if ($('#dayIn').val() == "") {
        alert("여행 기간을 입력해주세요.");
        $('#dayIn').focus();
        return false;
    } else if($('#dayIn').val() < 1){
        alert("1이상의 숫자를 입력해주세요.");
        $('#dayIn').focus();
        return false;
    }
        $("#initForm").submit();
}

$("#dayIn").keydown(function (keyNum) {
    if (keyNum.keyCode == 13) {
        checkDays();
    }
})

// function fetchPage(name) {
//     fetch(name).then(function (response) {
//         response.text().then(function (text) {
//             document.querySelector('article').innerHTML = text;
//         })
//     });
// }

