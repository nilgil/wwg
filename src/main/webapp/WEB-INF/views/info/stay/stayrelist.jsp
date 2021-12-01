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
<link defer rel='stylesheet' media='screen' href='/css/info/foodrelist.css'>

<!-- jQuery문 설정 불러오기 -->
<script src="${path}/js/info/jquery.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- 댓글 수정, 삭제 기능 -->
<script type="text/javascript">
	$(function() {
		$('.edit1').click(function() {
			var id  = $(this).attr('id');  // stay_re_no
			var txt = $('#td_'+id).text(); // stay_re_content
			$('#td_'+id).html("<textarea rows='3' cols='30' id='tt_"+id+"'>"+txt
				+"</textarea>");
			$('#btn_'+id).html(
			   "<input type='button' value='확인' onclick='up("+id+")'> "
			  +"<input type='button' value='취소' onclick='lst()'>");
		});
	});
	function up(id) {
		var stay_re_content = $('#tt_'+id).val();
		var formData = "stay_re_no="+id+'&stay_re_content='+stay_re_content
			+"&rstay_no=${stayboard.stay_no}";
		$.post('${path}/stayreUpdate',formData, function(data) {
			$('#stayrelist').html(data);
		});
	}
	function lst() {
		$('#stayrelist').load('${path}/stayrelist/stay_no/${stayboard.stay_no}');
	}
	function del(stay_re_no,rstay_no) {
		var formData="stay_re_no="+stay_re_no+"&rstay_no="+rstay_no;
		$.post("${path}/stayreDelete",formData, function(data) {
			$('#stayrelist').html(data);
		});
	}
</script>
</head>
<body>
<table id="f_Re_table" class="table table-striped" width=300 align="center">
	       <thead>
			<tr>
				<td>작성자</td>
				<td>내용</td>
				<td>작성일</td>
				<td></td>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="ReStayBoard" items="${stayrelist}">
				<tr>
					<td>${ReStayBoard.username}</td>
					<td id="td_${ReStayBoard.stay_re_no}">${ReStayBoard.stay_re_content}</td>
					<td><fmt:formatDate value="${ReStayBoard.stay_re_regdate}"
						pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td id="btn_${ReStayBoard.stay_re_no}">
						<c:if test="${ReStayBoard.username==user.username }">
							<input type="button" value="수정" class="edit1" id="${ReStayBoard.stay_re_no}">
							<input class="btn btn-outline-dark btn-sm" type="button" value="삭제"	 onclick="del(${ReStayBoard.stay_re_no},${ReStayBoard.rstay_no})">
						</c:if></td>
				</tr>
			</c:forEach>
			</tbody>
</table>
</body>
</html>