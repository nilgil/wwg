// 일정 수정 폼으로 이동
function updatePlanForm(idx) {
    location.replace(`/plan/${idx}/update-form`);
}

// 일정 삭제 실수 방지 alert
function askReallyDelete(idx) {
    if (confirm("정말로 일정을 삭제하시겠습니까?")) {
        deletePlan(idx)
    }
}

// 공개 여부 변경 실수 방지 alert
function askReallyPubToggle(idx,pub) {
    if (confirm("공개 여부를 변경하시겠습니까?")) {
        pubToggle(idx,pub)
    }
}

// 일정 삭제 (Ajax-DELETE)
function deletePlan(idx) {
    $.ajax({
        method: "DELETE",
        url: `/plan/${idx}`,
        success: function () {
            location.reload();
        },
        error: function () {
            alert("deletePlan");
        }
    })
}

// 공개 여부 변경 (Ajax-PUT)
function pubToggle(idx,pub) {
    $.ajax({
        method: 'post',
        url: `/plan/${idx}/pub/`,
        data: {'pub': Number(pub)},
        success: function () {
            location.replace("/plan/my");
        },
        error: function () {
            alert("pubToggle");
        }
    })
}
