<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<script type="text/javascript">
	function chk() {
		if (frm.username.value != frm.username.value) {
			alert("사용자가 다르면 수정할 수 없습니다");
			frm.username.focus();
			return false;
		}
	}
</script>
<!-- <script type="text/javascript">
	$(function() {
		$('#slist').load('${path}/slist/num/${board.num}')
//		$('#list').load('${path}/list/pageNum/${pageNum}');
		$('#repInsert').click(function() {
			if (!frm.replytext.value) {
				alert('댓글 입력후에 클릭하시오');
				frm.replytext.focus();
				return false;
			}
			var frmData = $('form').serialize();		  
			$.post('${path}/sInsert', frmData, function(data) {
				$('#slist').html(data);
				frm.replytext.value = '';
			});
		});
	});
</script> -->
</head>
<body>
	<table border=1 width=400 align="center">
	<caption>상세 페이지</caption>
			<tr>
				<td>제목</td>
				<td>${foodboard.food_title}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${foodboard.username}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${foodboard.food_hit}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><pre>${foodboard.food_content}</pre></td>
			</tr>
		</table>
			<div align=center>
				<input type="button" value="목록" onClick="location.href='/foodlist.do/pageNum/${pageNum}' " >
				
				<form action="${path}/foodupdateform.do/food_no/${foodboard.food_no}/pageNum/${pageNum}"
		 		method="post" name="frm" onsubmit="return chk()">
				<input type="submit" value="수정" >
				</form>
				
				<form action="${path}/fooddeleteform.do?food_no=${foodboard.food_no}&pageNum=${pageNum}"
		 		method="post" name="frm" onsubmit="return chk()">
				<input type="submit" value="삭제" >
				</form>
				
				<input type="button" value="답변" onClick="location.href='/insertForm/nm/${foodboard.food_no}/pageNum/${pageNum}' " >
			</div>
			
<%-- 		<p>
		<form name="frm" id="frm">
			<input type="hidden" name="replyer" value="${board.writer}">
			<input type="hidden" name="bno" value="${board.num}"> 댓글 :
			<textarea rows="3" cols="50" name="replytext"></textarea>
			<input type="button" value="확인" id="repInsert">
		</form>
		<div id="slist"></div> --%>
</body>
</html>