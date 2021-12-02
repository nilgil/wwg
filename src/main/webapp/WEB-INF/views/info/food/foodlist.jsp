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
<%@ include file="/resources/include/headTag.jsp"%>
<link defer rel='stylesheet' media='screen' href='/css/info/foodlist.css'>
</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


<!-- center -->
<div class="foodlist_center">

	<div class="side"> 
			<table id="side_menu" class="table table-hover">
		 <thead>
		   <tr><th>여행정보</th></tr>
		 </thead>
		 <tbody>
		   <tr><td><a href="/staylist.do">숙소</a></td></tr>
		   <tr><td><a href="/foodlist.do">맛집</a></td></tr>
		   <tr><td><a href="/tourlist.do">여행지</a></td></tr>
		 </tbody>
	 </table>
	</div>

	<div class="foodlist_table">
    <table id="f_table" class="table table-hover" align="center" width=900>
		<h2>맛집 정보 게시판</h2>
	    <p>총 글개수 : ${food_count}</p>
	    <thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>좋아요</th>
			</tr>
		</thead>

		<tbody>
				<c:set var="no1" value="${no}"></c:set>
				<c:forEach var="fb" items="${foodlist }">
					<tr>
						<td>${no1}</td>
							<td><a href="${path }/foodcontent.do/food_no/${fb.food_no}/pageNum/${pp.currentPage}">
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
				</tbody>		
			</table>

			<div class="f_write_btn" align="center">
				<s:authorize access="hasRole('ROLE_ADMIN')">
				<a href="${path}/foodform.do" class="btn btn-info">글 작성</a>
				</s:authorize>
			</div>
			

			<!-- 페이지 넘기기 기능 -->
			<div class="foodlist_paging">
			<center>
				<c:if test="${not empty keyword}">
					<c:if test="${pp.startPage > pp.pagePerBlk }">
						<li><a href="${path }/foodlist.do/pageNum/${pp.startPage - 1}?search=${search}&keyword=${keyword}">이전</a></li>
					</c:if>
					<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
						<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
							href="${path }/foodlist.do/pageNum/${i}?search=${search}&keyword=${keyword}">${i}</a></li>
					</c:forEach>
					<c:if test="${pp.endPage < pp.totalPage}">
						<li><a href="${path }/foodlist.do/pageNum/${pp.endPage + 1}?search=${search}&keyword=${keyword}">다음</a></li>
					</c:if>
				</c:if>
				<c:if test="${empty keyword}">
					<c:if test="${pp.startPage > pp.pagePerBlk }">
						<li><a href="${path }/foodlist.do/pageNum/${pp.startPage - 1}">이전</a></li>
					</c:if>
					<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
						<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
							href="${path }/foodlist.do/pageNum/${i}">${i}</a></li>
					</c:forEach>
					<c:if test="${pp.endPage < pp.totalPage}">
						<li><a href="${path }/foodlist.do/pageNum/${pp.endPage + 1}">다음</a></li>
					</c:if>
			  </c:if>
			</center>
		    </div>	


		<!-- 검색 기능 -->
		<div class="foodlist_search">
		<form align="center" action="${path}/foodlist.do/pageNum/1">
			<select name="search">
				<option value="food_title"
					<c:if test="${search=='food_title'}">selected="selected" </c:if>>제목</option>
				<option value="food_content"
					<c:if test="${search=='food_content'}">selected="selected" </c:if>>내용</option>
				<option value="username"
					<c:if test="${search=='username'}">selected="selected" </c:if>>작성자</option>
				<option value="subcon"
					<c:if test="${search=='subcon'}">selected="selected" </c:if>>제목+내용</option>
			</select> 
			<input type="text" name="keyword"> 
			<input class="btn btn-dark" type="submit" value="확인">
		</form>
	    </div>
	    </div>

	</div> 
	
	<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>
	
</body>
</html>