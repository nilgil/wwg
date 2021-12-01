function goodToggle(idx) {
    $.ajax({
        url:'/plan/good',
        method: 'put',
        data:{"idx": idx},
        success: function () {
            $('#plans').append("good!");
        }

    })
}