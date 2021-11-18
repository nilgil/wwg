<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script type="text/javascript">
	$(function() {
		$('#list').load('noticelist?pageNum=${pageNum}');
	});
</script> -->
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

		
		<a href="${path}/noticelist/pageNum/${pageNum}">목록</a> 
		<a href="updateForm.do?num=${notice.notice_no}&pageNum=${pageNum}"
		   class="btn btn-info">수정</a> 
		<a href="deleteForm.do?num=${notice.notice_no}&pageNum=${pageNum}"
		   class="btn btn-info">삭제</a> 
		<a href="insertForm.do?nm=${notice.notice_no}&pageNum=${pageNum}"
		   class="btn btn-info">답변</a>
</body>
</html>