<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<!-- jQuery문 설정 불러오기 -->
<script src="${path}/js/info/jquery.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- 댓글 수정, 삭제 기능 -->
<script type="text/javascript">
	$(function() {
		$('.edit1').click(function() {
			var id  = $(this).attr('id');  // tour_re_no
			var txt = $('#td_'+id).text(); // tour_re_content
			$('#td_'+id).html("<textarea rows='3' cols='30' id='tt_"+id+"'>"+txt
				+"</textarea>");
			$('#btn_'+id).html(
			   "<input type='button' value='확인' onclick='up("+id+")'> "
			  +"<input type='button' value='취소' onclick='lst()'>");
		});
	});
	function up(id) {
		var tour_re_content = $('#tt_'+id).val();
		var formData = "tour_re_no="+id+'&tour_re_content='+tour_re_content
			+"&rtour_no=${tourboard.tour_no}";
		$.post('${path}/tourreUpdate',formData, function(data) {
			$('#tourrelist').html(data);
		});
	}
	function lst() {
		$('#tourrelist').load('${path}/tourrelist/tour_no/${tourboard.tour_no}');
	}
	function del(tour_re_no,rtour_no) {
		var formData="tour_re_no="+tour_re_no+"&rtour_no="+rtour_no;
		$.post("${path}/tourreDelete",formData, function(data) {
			$('#tourrelist').html(data);
		});
	}
</script>
</head>
<body>
<h3 align="center">댓글</h3>
<table id="f_Re_table" class="table table-striped" width=300 align="center">

			<tr>
				<td>작성자</td>
				<td>내용</td>
				<td>작성일</td>
				<td></td>
			</tr>
		
			<c:forEach var="ReTourBoard" items="${tourrelist}">
				<tr>
					<td>${ReTourBoard.username}</td>
					<td id="td_${ReTourBoard.tour_re_no}">${ReTourBoard.tour_re_content}</td>
					<td><fmt:formatDate value="${ReTourBoard.tour_re_regdate}"
						pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td id="btn_${ReTourBoard.tour_re_no}">
						<c:if test="${ReTourBoard.username==username }">
							<input type="button" value="수정" class="edit1" id="${ReTourBoard.tour_re_no}">
							<input class="btn btn-outline-dark btn-sm" type="button" value="삭제"	 onclick="del(${ReTourBoard.tour_re_no},${ReTourBoard.rtour_no})">
						</c:if></td>
				</tr>
			</c:forEach>
	
</table>
</body>
</html>