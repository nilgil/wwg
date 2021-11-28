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
<a>${user.username}님 환영합니다.</a>

<!-- jQuery문 설정 불러오기 -->
<script src="${path}/js/info/jquery.js"></script>

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
<c:if test="${user.username eq admin}">
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
</c:if>
	</div> 
</body>
</html>