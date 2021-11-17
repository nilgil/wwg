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
				<td>좋아요</td>
			</tr>
				<c:set var="no1" value="${no}"></c:set>
				<c:forEach var="fb" items="${foodlist }">
					<tr>
						<td>${no1}</td>
							<td><a href="foodcontent.do?food_no=${fb.food_no}&page=${page}">
								${fb.food_title}
								</a></td>
							<td>${fb.username}</td>
							<td><fmt:formatDate value="${fb.food_regdate}" 
						pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${fb.food_hit}</td>
							<td>${fb.food_like}</td>
					</tr>
					<c:set var="no1" value="${no1-1}"></c:set>
				</c:forEach>
		</table>
		<form align="center" action="${path}/foodlist/pageNum/1">
			<select name="search">
				<option value="subject"
					<c:if test="${search=='food_title'}">selected="selected" </c:if>>제목</option>
				<option value="content"
					<c:if test="${search=='food_content'}">selected="selected" </c:if>>내용</option>
				<option value="writer"
					<c:if test="${search=='username'}">selected="selected" </c:if>>작성자</option>
				<option value="subcon"
					<c:if test="${search=='subcon'}">selected="selected" </c:if>>제목+내용</option>
			</select> 
			<input type="text" name="keyword"> 
			<input type="submit" value="확인">
		</form>
		
		<center>
			<c:if test="${not empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">
					<li><a href="${path }/foodlist/pageNum/${pp.startPage - 1}?search=${search}&keyword=${keyword}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
						href="${path }/foodlist/pageNum/${i}?search=${search}&keyword=${keyword}">${i}</a></li>
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a href="${path }/foodlist/pageNum/${pp.endPage + 1}?search=${search}&keyword=${keyword}">다음</a></li>
				</c:if>
			</c:if>
			<c:if test="${empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">
					<li><a href="${path }/foodlist/pageNum/${pp.startPage - 1}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
						href="${path }/foodlist/pageNum/${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a href="${path }/foodlist/pageNum/${pp.endPage + 1}">다음</a></li>
				</c:if>
		  </c:if>
		</center>
		<div align="center">
			<a href="${path}/foodform.do" class="btn btn-info">글 입력</a>
		</div>
</body>
</html>