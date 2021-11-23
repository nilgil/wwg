// 출발일 기본값 오늘로 설정
Date.prototype.toDateInputValue = (function () {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0, 10);
});

$(document).ready(function () {
    $('#depDate').val(new Date().toDateInputValue());
});


// function fetchPage(name) {
//     fetch(name).then(function (response) {
//         response.text().then(function (text) {
//             document.querySelector('article').innerHTML = text;
//         })
//     });
// }

