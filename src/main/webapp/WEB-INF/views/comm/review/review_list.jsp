<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<a>${user.username} 님 환영합니다.</a>
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
				<c:set var="no" value="${review_no}"></c:set>
				<c:forEach var="n" items="${list}">
					<tr>
						<td>${n.review_no}</td>
							<td><a href="${path}/reviewview/review_no/${n.review_no}/pageNum/${pp.currentPage}">
								${n.review_title}
								</a></td>
							<td>${n.member_id}</td>
							<td><fmt:formatDate value="${n.review_regdate}" 
						pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${n.review_hit}</td>
							<td>${n.review_like}</td>
					</tr>
					<c:set var="no1" value="${no-1}"></c:set>
				</c:forEach>
		</table>
		<form align="center" action="${path}/reviewlist/pageNum/1">
			<select name="search">
				<option value="review_title"
					<c:if test="${search=='review_title'}">selected="selected" </c:if>>제목</option>
				<option value="review_content"
					<c:if test="${search=='review_content'}">selected="selected" </c:if>>내용</option>
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
					<li><a href="${path }/reviewlist/pageNum/${pp.startPage - 1}?search=${search}&keyword=${keyword}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
						href="${path }/reviewlist/pageNum/${i}?search=${search}&keyword=${keyword}">${i}</a></li>
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a href="${path }/reviewlist/pageNum/${pp.endPage + 1}?search=${search}&keyword=${keyword}">다음</a></li>
				</c:if>
			</c:if>
			<c:if test="${empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">
					<li><a href="${path }/reviewlist/pageNum/${pp.startPage - 1}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
						href="${path }/reviewlist/pageNum/${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a href="${path }/reviewlist/pageNum/${pp.endPage + 1}">다음</a></li>
				</c:if>
		  </c:if>
		</center>
		<div align="center">
			<a href="${path}/comm_reviewinsertform" class="btn btn-info">글 작성</a>
		</div>
</body>
</html>