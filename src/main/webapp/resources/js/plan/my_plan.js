function updatePlanForm(idx) {
    location.replace(`/plan/update-form/${idx}`);
}

function deletePlan(idx) {
    $.ajax({
        method: "DELETE",
        url: "/plan/delete",
        data: {'idx': idx},
        success: function () {
            location.reload();
        }
    })
}

function askReallyDelete(idx) {
    if (confirm("정말로 일정을 삭제하시겠습니까?")) {
        deletePlan(idx)
    }
}