<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			var id  = $(this).attr('id');  // food_re_no
			var txt = $('#td_'+id).text(); // food_re_content
			$('#td_'+id).html("<textarea rows='3' cols='30' id='tt_"+id+"'>"+txt
				+"</textarea>");
			$('#btn_'+id).html(
			   "<input type='button' value='확인' onclick='up("+id+")'> "
			  +"<input type='button' value='취소' onclick='lst()'>");
		});
	});
	function up(id) {
		var food_re_content = $('#tt_'+id).val();
		var formData = "food_re_no="+id+'&food_re_content='+food_re_content
			+"&rfood_no=${foodboard.food_no}";
		$.post('${path}/foodreUpdate',formData, function(data) {
			$('#foodrelist').html(data);
		});
	}
	function lst() {
		$('#foodrelist').load('${path}/foodrelist/food_no/${foodboard.food_no}');
	}
	function del(food_re_no,rfood_no) {
		var formData="food_re_no="+food_re_no+"&rfood_no="+rfood_no;
		$.post("${path}/foodreDelete",formData, function(data) {
			$('#foodrelist').html(data);
		});
	}
</script>
</head>
<body>
<table border=1 width=300 align="center">
	<caption>댓글 목록</caption>
			<tr>
				<td>작성자</td>
				<td>내용</td>
				<td>작성일</td>
				<td></td>
			</tr>
			<c:forEach var="ReFoodBoard" items="${foodrelist}">
				<tr>
					<td>${ReFoodBoard.username}</td>
					<td id="td_${ReFoodBoard.food_re_no}">${ReFoodBoard.food_re_content}</td>
					<td><fmt:formatDate value="${ReFoodBoard.food_re_regdate}"
						pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td id="btn_${ReFoodBoard.food_re_no}">
						<c:if test="${ReFoodBoard.username==foodboard.username }">
							<input type="button" value="수정" class="edit1" id="${ReFoodBoard.food_re_no}">
							<input type="button" value="삭제"	 onclick="del(${ReFoodBoard.food_re_no},${ReFoodBoard.rfood_no})">
						</c:if></td>
				</tr>
			</c:forEach>
</table>
</body>
</html>