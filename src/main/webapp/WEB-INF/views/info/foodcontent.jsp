<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
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
		<a href="${path}/foodlist.do/pageNum/${pageNum}" class="btn btn-info">목록</a>
<%-- 		<a href="${path}/updateForm/num/${fb.num}/pageNum/${pageNum}"
			class="btn btn-info">수정</a> <a
			href="${path}/deleteForm/num/${fb.num}/pageNum/${pageNum}"
			class="btn btn-info">삭제</a> <a
			href="${path}/insertForm/nm/${fb.num}/pageNum/${pageNum}"
			class="btn btn-info">답변</a> --%>
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