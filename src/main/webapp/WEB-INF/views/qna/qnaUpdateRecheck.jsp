<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정 재확인</title>

<script src="${path}/js/info/jquery.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

</head>
<body>


<script type="text/javascript">

System.out.println("qnaUpdateRecheck 까지 옴");

/* var username = "${qnalist.username}";
var session = "${sessionScope.username}";

	if(username != session){
		alert("사용자가 다르면 수정할 수 없습니다"); 
		history.go(-1);
	} */
	
	var username = session;
	
	if(username == session){	
/* 		location.href="${path}/delete/qna_no/${qnalist.qna_no}"; */
		location.href="${path}/qna_updateform?qna_no=${qnalist.qna_no}&page=${page}";
		       /*         ${path}/qna_updateform?qna_no=${qnalist.qna_no}&page=${page}' */
	}

</script>

</body>
</html>