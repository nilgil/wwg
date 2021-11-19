<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1 align="center" width=800>
	<caption>게시판 목록</caption>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
				<c:set var="no" value="${notice_no}"></c:set>
				<c:forEach var="n" items="${list}">
					<tr>
						<td>${n.notice_no}</td>
							<td><a href="${path}/noticeview/notice_no/${n.notice_no}/pageNum/${pp.currentPage}">
								${n.notice_title}
								</a></td>
							<td>${n.member_id}</td>
							<td><fmt:formatDate value="${n.notice_regdate}" 
						pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${n.notice_hit}</td>
					</tr>
					<c:set var="no1" value="${no-1}"></c:set>
				</c:forEach>
		</table>
		<form align="center" action="${path}/noticelist/pageNum/1">
			<select name="search">
				<option value="notice_title"
					<c:if test="${search=='notice_title'}">selected="selected" </c:if>>제목</option>
				<option value="notice_content"
					<c:if test="${search=='notice_content'}">selected="selected" </c:if>>내용</option>
				<option value="member_id"
					<c:if test="${search=='member_id'}">selected="selected" </c:if>>작성자</option>
				<option value="subcon"
					<c:if test="${search=='subcon'}">selected="selected" </c:if>>제목+내용</option>
			</select> 
			<input type="text" name="keyword"> 
			<input type="submit" value="확인">
		</form>
		
		<center>
			<c:if test="${not empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">
					<li><a href="${path }/noticelist/pageNum/${pp.startPage - 1}?search=${search}&keyword=${keyword}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
						href="${path }/noticelist/pageNum/${i}?search=${search}&keyword=${keyword}">${i}</a></li>
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a href="${path }/noticelist/pageNum/${pp.endPage + 1}?search=${search}&keyword=${keyword}">다음</a></li>
				</c:if>
			</c:if>
			<c:if test="${empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">
					<li><a href="${path }/noticelist/pageNum/${pp.startPage - 1}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
						href="${path }/noticelist/pageNum/${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a href="${path }/noticelist/pageNum/${pp.endPage + 1}">다음</a></li>
				</c:if>
		  </c:if>
		</center>
		<div align="center">
			<a href="${path}/comm_noticeinsertform" class="btn btn-info">글 작성</a>
		</div>
</body>
</html>