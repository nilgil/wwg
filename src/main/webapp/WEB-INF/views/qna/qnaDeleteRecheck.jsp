<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글삭제 재확인</title>

<script src="${path}/js/info/jquery.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

</head>
<body>

<script type="text/javascript">

var username = "${qnalist.username}";
var session = "${sessionScope.username}";

	if(username != session){
		alert("사용자가 다르면 삭제할 수 없습니다"); 
		history.go(-1);
	} 
/* function check(){ */
	
		
	if(username == session){	
    if(confirm("정말 삭제하시겠습니까?")==true){
		location.href="${path}/delete/qna_no/${qnalist.qna_no}";
		alert("삭제되었습니다.");
	}else{
		history.go(-1);
		/* location.href="${path}/qna_detail/qna_no/${qna.qna_no}/page/${page}/no/{no1}"; */
	}
	}
/* } */
</script>

</body>
</html>