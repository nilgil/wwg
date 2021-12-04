class MemberList{

    deleteUser=function(){
        let username;
        username = this.dataset.user;
        console.log(username);
        confirm("이 유저를 삭제하시겠습니까?");
        fetch('http://localhost:8080/admin/api/deleteMember?username='+username,
            {
                method : 'DELETE'
            }).then(data=>{
                console.log(data.status);
            if(data.status===200) {
                this.remove();
            }else{
                alert('삭제실패');
            }
        })
    }
}

