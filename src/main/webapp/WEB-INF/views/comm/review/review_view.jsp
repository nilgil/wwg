<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="/comm/jquery"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script type="text/javascript">
	$(function() {
		$('#list').load('reviewlist?pageNum=${pageNum}');
	});
</script> -->


<!-- id 유효성검사 -->

<script type="text/javascript">
	function chk() {
		if (frm.passwd.value != frm.passwd2.value) {
			alert("권한이 없습니다");
			return false;
		}
	}
</script>

<script type="text/javascript">
	/* 	window.onload=function() {
	
	 } */
	$(function() {
		$('#review_view').load('${path}/review_view/review_no/${review.review_no}')
//		$('#list').load('${path}/list/pageNum/${pageNum}');
		$('#repInsert').click(function() {
			if (!frm.review_re_content.value) {
				alert('댓글 입력후에 클릭하시오');
				frm.review_re_content.focus();
				return false;
			}
			var frmData = $('form').serialize();
			// var frmData = 'replyer='+frm.replyer.value+'&bno='+
			//				  frm.bno.value+'&replytext='+frm.replytext.value;				  
			$.post('${path}/sInsert', frmData, function(data) {
				$('#review_view').html(data);
				frm.review_re_content.value = '';
			});
		});
	});
</script>

</head>
<body>
	<div align="center">
		<h2>게시글 상세정보</h2>
			<tr>
				<td>제목</td>
				<td>${review.review_title}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${review.member_id}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${review.review_hit}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><pre>${review.review_content}</pre></td>
			</tr>

		
		<a href="${path}/reviewlist/pageNum/${pageNum}">목록</a> 
		<a href="${path}/reviewupdateform/review_no/${review.review_no}/pageNum/${pageNum}"
		   >수정</a> 
		<a href="${path}/reviewdelete/review_no/${review.review_no}/pageNum/${pageNum}"
		   >삭제</a> 
		   
		<p>
		<form name="frm" id="frm">
			<input type="hidden" name="member_id" value="${review.member_id}">
			<input type="hidden" name="review_no" value="${review.review_no}"> 댓글 :
			<textarea rows="3" cols="50" name="review_re_content"></textarea>
			<input type="button" value="확인" id="repInsert">
		</form>
		<div id="review_view"></div>
		<!-- <div id="list"></div> -->
	</div>
</body>
</html>