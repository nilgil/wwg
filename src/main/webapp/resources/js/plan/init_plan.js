$(document).ready(function () {
    $('#departureIn').val(new Date().toDateInputValue());
    onlyNumberFunc(document.getElementById("dayIn"));
});

// 출발일 기본값 오늘로 설정
Date.prototype.toDateInputValue = (function () {
    let local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0, 10);
});

// 출발일, 여행기간 유효성 검사
function checkDays() {
    if ($('#departureIn').val() == "") {
        alert("출발일을 정해주세요.");
        $('#departureIn').focus();
        return false;
    }
    if ($('#dayIn').val() == "") {
        alert("여행 기간을 입력해주세요.");
        $('#dayIn').focus();
        return false;
    }
    $("#initForm").submit();
}

// 여행 기간 엔터 이벤트
$("#dayIn").keydown(function (keyNum) {
    if (keyNum.keyCode == 13) {
        checkDays();
    }
})

// 여행 기간에 1~9 외에 허용하지 않는다.
function onlyNumberFunc(t) {
    let regexp = /[^1-9]/gi;
    t.onkeyup = function (e) {
        let v = this.value;
        this.value = v.replace(regexp, '');
    }
}