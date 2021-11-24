<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>

<!-- jQuery문 설정 불러오기 -->
<script src="${path}/js/info/jquery.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- 로그인한 사람과 글쓴사람이 동일할때 수정,삭제 가능 -->
<script type="text/javascript">
var member_id = '${review.member_id}';
var session = '${sessionScope.member_id}';
$(function(){
	$("#chk1").submit(function(){
		if (username != session) {
			alert("사용자가 다르면 수정할 수 없습니다");
			return false;
		}				
	});
	$("#chk2").submit(function(){
		if (username != session) {
			alert("사용자가 다르면 삭제할 수 없습니다");
			return false;
		}				
	});
});	
</script>

<!-- 댓글 작성 jQuery문 -->
<script type="text/javascript">
	$(function() {
		$('#review_reply').load('${path}/review_reply/review_no/${review.review_no}')
	
		$('#review_reply_insert').click(function() {
			if (!frm.review_re_content.value) {
				alert('댓글 입력후에 클릭하시오');
				frm.review_re_content.focus();
				return false;
			}
			var frmData = $('frm').serialize();
			$.post('${path}/review_reply_insert', frmData, function(data) {
				$('#review_reply').html(data);
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
		   </div>
		 
		<!-- 댓글 작성 -->   
		<p><p>
		<form name="frm" id="frm">
			<input type="hidden" name="member_id" value="${review.member_id}">
			<input type="hidden" name="review_fno" value="${review.review_no}"> 댓글 :
			<textarea rows="3" cols="50" name="review_re_content"></textarea>
			<input type="button" value="확인" id="review_reply_insert">
		</form>
	
		<!-- 댓글 list 불러오는곳 -->
		<div id="review_reply"></div>
</body>
</html>