<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/include/headTag.jsp"%>
<link defer rel='stylesheet' media='screen' href='/css/comm/comm_noticelist.css'>
</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


<!-- center -->
<div class="c_notlist_center">

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
	
<div class="c_notlist_table" >	
<table id="c_n_table1" class="table table-hover">
        <h2>공지사항 게시판</h2>
        &nbsp;
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
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
		</tbody>			
		</table>
	
		<!-- 글쓰기버튼 -->
		<div class="c_n_write_btn">
		<form method=post action="${path}/comm_noticeinsertform">
		<s:authorize access="hasRole('ROLE_ADMIN')">
			<input class="btn btn-dark" type=submit value="글작성">
		</s:authorize>
		</form>
		</div>
		

		
		
		<!-- 페이징 -->
		<div class="c_n_paging" >
			<c:if test="${not empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">
					<li><a href="${path }/noticelist/pageNum/${pp.startPage - 1}?search=${search}&keyword=${keyword}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
						href="${path }/noticelist/pageNum/${i}?search=${search}&keyword=${keyword}">&nbsp;${i}&nbsp;</a></li>
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

		</div>
		
		
		<!-- 검색기능 -->
		<div class="c_n_search" >
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
			<input class="btn btn-dark" type="submit" value="검색">
		</form>
		</div>
			
		
	</div>

</div>		
		
		
<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>	
	

</body>
</html>