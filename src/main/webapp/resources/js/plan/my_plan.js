let token;
let header;
let planOfDays;
let spot;
let userName = $('#userName').val();
let thumbnails = [];

$(document).ready(function () {
    initFunction().then(printPlans);
});

function initFunction() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            method: "GET",
            url: "/plan/my/plans",
            data: {userName: userName},
            dataType: "json",
            success: function (response) {
                planOfDays = response;
                let firstSpots = [];
                for (const plan of planOfDays) {
                    let plansOfPlan = JSON.parse(plan.plans);
                    firstSpots.push(plansOfPlan[0][0]);
                }
                getThumbnails(firstSpots);
                console.log(thumbnails.length);
                resolve(response);
            }
        })
    });
}

function getThumbnails(firstSpots) {
    $.ajax({
        method: "POST",
        url: "/spots/searchArray",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        data: {titles: firstSpots},
        dataType: "json",
        success: function (response) {
            console.log(response.photo);
            thumbnails.push(response.photo);
        }
    })
}
//
// function printPlans(thumbnails) {
//     return new Promise(function (resolve, reject) {
//         for (const thumbnail of thumbnails) {
//             console.log(thumbnail);
//             $('#plans-box').append(`<div class='plan'><div id='plan-img'><img src="${thumbnail}" alt="https://via.placeholder.com/150"/></div><div id='plan-info'></div></div>`);
//         }
//         resolve(planOfDays);
//     });
// }