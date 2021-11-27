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
<title>상세페이지</title>
<a>${user.username}님 환영합니다.</a>

<!-- jQuery문 설정 불러오기 -->
<script src="${path}/js/info/jquery.js"></script>

<!-- 로그인한 사람과 글쓴사람이 동일할때 수정,삭제 가능 -->
<script type="text/javascript">
var session = '${user.username}';
var member_id = '${meet.member_id}';
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

<!-- 좋아요 버튼 -->
<script type="text/javascript">
var session = '${user.username}';
var member_id = '${meet.member_id}';
$(function() {	
	$("#like").submit(function(){
    	if(member_id == session){
        	alert("사용자가 같으면 좋아요를 할수 없습니다.");
        	return false;
        }
    });
});
</script>

<!-- 댓글 작성 jQuery문 -->
<script type="text/javascript">
	$(function() {
		$('#meet_reply').load('${path}/meet_reply/meet_no/${meet.meet_no}')
	
		$('#meet_reply_insert').click(function() {
			if (!frm.meet_re_content.value) {
				alert('댓글 입력후에 클릭하시오');
				frm.meet_re_content.focus();
				return false;
			}
			var frmData = $('#frm').serialize();
			$.post('${path}/meet_reply_insert', frmData, function(data) {
				$('#meet_reply').html(data);
				frm.meet_re_content.value = '';
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
				<td>${meet.meet_title}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${meet.member_id}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${meet.meet_hit}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><pre>${meet.meet_content}</pre></td>
			</tr>
		   </div>
		   <div align=center>
		<input type="button" value="목록"
			onClick="location.href='/foodlist.do/pageNum/${pageNum}' ">

		<form
			action="${path}/meetupdateform/meet_no/${meet.meet_no}/pageNum/${pageNum}"
			method="post" name="chk" id="chk1">
			<input type="submit" value="수정">
		</form>

		<form
			action="${path}/meetdelete/meet_no/${meet.meet_no}/pageNum/${pageNum}"
			method="post" name="chk" id="chk2">
			<input type="submit" value="삭제">
		</form>

		<form
			action="/meetlike?meet_no=${meet.meet_no}&pageNum=${pageNum}"
			method="post" id="like">
			<input type="submit" value="좋아요">
		</form>
	</div>
		   
		 
		<!-- 댓글 작성 -->   
		<p><p>
		<form name="frm" id="frm">
			<input type="hidden" name="member_id" value="${user.username}">
			<input type="hidden" name="meet_fno" value="${meet.meet_no}"> 댓글 :
			<textarea rows="3" cols="50" name="meet_re_content"></textarea>
			<input type="button" value="확인" id="meet_reply_insert">
		</form>
	
		<!-- 댓글 list 불러오는곳 -->
		<div id="meet_reply"></div>
</body>
</html>