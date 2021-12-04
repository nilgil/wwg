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
		var meet_re_content = $('#tt_'+id).val();
		var formData = "meet_re_no="+id+'&meet_re_content='+meet_re_content
			+"&meet_fno=${meet.meet_no}";
		$.post('${path}/meet_reply_update',formData, function(data) {
			$('#meet_reply').html(data);
		});
	}
	function lst() {
		$('#meet_reply').load('${path}/meet_reply/meet_no/${meet.meet_no}');
	}
	function del(meet_re_no,meet_fno) {
		var formData="meet_re_no="+meet_re_no+"&meet_fno="+meet_fno;
		$.post("${path}/meet_reply_delete",formData, function(data) {
			$('#meet_reply').html(data);
		});
	}
</script>
</head>
<body>

		<h3 align="center">댓글</h3>
		<table class="table table-striped">
			<tr>
				<td>작성자</td>
				<td>내용</td>
				<td>수정일</td>
				<td></td>
			</tr>
			<c:forEach var="mr" items="${meet_reply}">
				<tr>
					<td>${mr.member_id}</td>
					<td id="td_${mr.meet_re_no}">${mr.meet_re_content}</td>
					<td><fmt:formatDate value="${mr.meet_re_regdate}"
						pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td id="btn_${mr.meet_re_no}">
						<c:if test="${mr.member_id==username }">
							<input  type="button" value="수정" class="edit1" id="${mr.meet_re_no}">
							<input class="btn btn-outline-dark btn-sm" type="button" value="삭제"	 onclick="del(${mr.meet_re_no},${mr.meet_fno})">
						</c:if></td>
				</tr>
			</c:forEach>
		</table>

</body>
</html>