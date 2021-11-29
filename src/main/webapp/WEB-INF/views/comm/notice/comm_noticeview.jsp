<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/include/headTag.jsp"%>
<link defer rel='stylesheet' media='screen' href='/css/comm/comm_noticview.css'>

<!-- jQuery문 설정 불러오기 -->
<script src="${path}/js/info/jquery.js"></script>

<!-- 로그인한 사람과 글쓴사람이 동일할때 수정,삭제 가능 -->
<script type="text/javascript">
var session = '${user.username}';
var member_id = '${notice.member_id}';
console.log(member_id);
$(function(){
	$("#chk1").submit(function(){
		if (member_id != session) {
			alert("사용자가 다르면 수정할 수 없습니다");
			return false;
		}				
	});
	$("#chk2").submit(function(){
		if (member_id != session) {
			alert("사용자가 다르면 삭제할 수 없습니다");
			return false;
		}				
	});
});	
</script>

</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>
<a>${user.username}님 환영합니다.</a>

<!-- center -->
<div class="c_notiview_center">

	<div class="side"> 
			<table id="side_menu" class="table table-hover">
		 <thead>
		   <tr><th>커뮤니티</th></tr>
		 </thead>
		 <tbody>
		   <tr><td><a href="/noticelist">공지사항</a></td></tr>
		   <tr><td><a href="/reviewlist">여행후기</a></td></tr>
		   <tr><td><a href="/meetlist">동행구해요</a></td></tr>
		 </tbody>
	 </table>
	</div>

<div class="c_notiview_table">
	<table id="c_n_view_table1" class="table table-hover" width=900 align=center>
	<h2>공지사항 게시판</h2>
	<p>The .table-hover class enables a hover state (grey background on mouse over) on table rows:</p>
			<tr>
				<td width="90">제목</td>
				<td>${notice.notice_title}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${notice.member_id}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${notice.notice_hit}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><pre>${notice.notice_content}</pre></td>
			</tr>
	</table>
			
	<div class="c_notiview_btns">
		<input type="button" value="목록" class="btn btn-dark"
			onClick="location.href='/noticelist/pageNum/${pageNum}' ">

		<form
			action="${path}/noticeupdateform/notice_no/${notice.notice_no}/pageNum/${pageNum}"
			method="post" name="chk" id="chk1">
			<input class="btn btn-dark" type="submit" value="수정">
		</form>

		<form
			action="${path}/noticedelete/notice_no/${notice.notice_no}/pageNum/${pageNum}"
			method="post" name="chk" id="chk2">
			<input class="btn btn-dark" type="submit" value="삭제">
		</form>

	</div>
	 
</div>	

</div>	
	
<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>
	
</body>
</html>