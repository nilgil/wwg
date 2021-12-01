<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }" />

	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세</title>
<!-- jQuery문 설정 불러오기 -->
<script src="${path}/js/info/jquery.js"></script>


<!-- 댓글 수정, 삭제 기능 -->
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
			+"&review_fno=${review.review_no}";
		$.post('${path}/review_reply_update',formData, function(data) {
			$('#review_reply').html(data);
		});
	}
	function lst() {
		$('#review_reply').load('${path}/review_reply/review_no/${review.review_no}');
	}
	function del(review_re_no,review_fno) {
		var formData="review_re_no="+review_re_no+"&review_fno="+review_fno;
		$.post("${path}/review_reply_delete",formData, function(data) {
			$('#review_reply').html(data);
		});
	}
</script>
</head>
<body>


		<h2>댓글</h2>
		<table class="table table-striped">
			<tr>
				<td>작성자</td>
				<td>내용</td>
				<td>수정일</td>
				<td></td>
			</tr>
			<c:forEach var="rr" items="${review_reply}">
				<tr>
					<td>${rr.member_id}</td>
					<td id="td_${rr.review_re_no}">${rr.review_re_content}</td>
					<td><fmt:formatDate value="${rr.review_re_regdate}"
						pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td id="btn_${rr.review_re_no}">
						<c:if test="${rr.member_id==user.username }">
							<input type="button" value="수정" class="edit1" id="${rr.review_re_no}">
							<input class="btn btn-outline-dark btn-sm" type="button" value="삭제"	 onclick="del(${rr.review_re_no},${rr.review_fno})">
						</c:if></td>
				</tr>
			</c:forEach>
		</table>

</body>
</html>