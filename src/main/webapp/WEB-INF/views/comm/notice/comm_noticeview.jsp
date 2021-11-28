<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<a>${user.username}님 환영합니다.</a>

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
	<div align="center">
		<h2>게시글 상세정보</h2>
			<tr>
				<td>제목</td>
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
			</div>
	<div align=center>
		<input type="button" value="목록"
			onClick="location.href='/foodlist.do/pageNum/${pageNum}' ">

		<form
			action="${path}/noticeupdateform/notice_no/${notice.notice_no}/pageNum/${pageNum}"
			method="post" name="chk" id="chk1">
			<input type="submit" value="수정">
		</form>

		<form
			action="${path}/noticedelete/notice_no/${notice.notice_no}/pageNum/${pageNum}"
			method="post" name="chk" id="chk2">
			<input type="submit" value="삭제">
		</form>

	</div> 
</body>
</html>