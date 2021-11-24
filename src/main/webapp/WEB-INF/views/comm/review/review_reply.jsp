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
<script type="text/javascript">
	$(function() {
		$('.edit1').click(function() {
			var id  = $(this).attr('id');  // rno
			var txt = $('#td_'+id).text(); // replytext
			$('#td_'+id).html("<textarea rows='3' cols='30' id='tt_"+id+"'>"+txt
				+"</textarea>");
			$('#btn_'+id).html(
			   "<input type='button' value='확인' onclick='up("+id+")'> "
			  +"<input type='button' value='취소' onclick='lst()'>");
		});
	});
	function up(id) {
		var review_re_content = $('#tt_'+id).val();
		var formData = "review_re_no="+id+'&review_re_content='+review_re_content
			+"&review_no=${review.review_no}";
		$.post('${path}/repUpdate',formData, function(data) {
			$('#review_view').html(data);
		});
	}
	function lst() {
		$('#review_view').load('${path}/review_view/review_no/${review.review_no}');
	}
	function del(review_re_no,review_no) {
		var formData="review_re_no="+review_re_no+"&review_no="+review_no;
		$.post("${path}/repDelete",formData, function(data) {
			$('#review_view').html(data);
		});
	}
</script>
</head>
<body>
	<div class="container" align="center">
		<h2 class="text-primary">댓글</h2>
		<table class="table table-bordered">
			<tr>
				<td>작성자</td>
				<td>내용</td>
				<td>수정일</td>
				<td></td>
			</tr>
			<c:forEach var="rr" items="${review_view}">
				<tr>
					<td>${rr.member_id}</td>
					<td id="td_${rr.review_re_no}">${rr.review_re_content}</td>
					<td id="btn_${rr.review_re_no}">
						<c:if test="${rr.member_id==review.member_id }">
							<input type="button" value="수정" class="edit1" id="${rr.review_re_no}">
							<input type="button" value="삭제"	 onclick="del(${rr.review_no},${rr.review_no})">
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>