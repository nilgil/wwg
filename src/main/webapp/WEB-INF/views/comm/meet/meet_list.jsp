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
<%@ include file="/resources/include/headTag.jsp"%>
<link defer rel='stylesheet' media='screen' href='/css/comm/meetlist.css'>
</head>
<body>
<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>
<a>${user.username}님 환영합니다.</a>

<!-- center -->
<div class="c_meetlist_center">

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
	

<div class="c_meetlist_table" >	
<table id="c_m_table1" class="table table-hover">
        <h2>동행구해요 게시판</h2>
		<p>총 글개수 : ${listcount}</p>
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
				<td>좋아요</td>
			</tr>
		</thead>
		<tbody>
				<c:set var="no" value="${meet_no}"></c:set>
				<c:forEach var="n" items="${list}">
					<tr>
						<td>${n.meet_no}</td>
							<td><a href="${path}/meetview/meet_no/${n.meet_no}/pageNum/${pp.currentPage}">
								${n.meet_title}
								</a></td>
							<td>${n.member_id}</td>
							<td><fmt:formatDate value="${n.meet_regdate}" 
						pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${n.meet_hit}</td>
							<td>${n.meet_like}</td>
					</tr>
					<c:set var="no1" value="${no-1}"></c:set>
				</c:forEach>
		</tbody>			
		</table>
		
		<div class="c_m_write_btn">
			<a href="${path}/comm_meetinsertform" class="btn btn-info">글 작성</a>
		</div>
		
		<!-- 페이징 -->
		<div class="c_m_paging" >
		<c:if test="${not empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">
					<li><a href="${path }/meetlist/pageNum/${pp.startPage - 1}?search=${search}&keyword=${keyword}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
						href="${path }/meetlist/pageNum/${i}?search=${search}&keyword=${keyword}">${i}</a></li>
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a href="${path }/meetlist/pageNum/${pp.endPage + 1}?search=${search}&keyword=${keyword}">다음</a></li>
				</c:if>
			</c:if>
			<c:if test="${empty keyword}">
				<c:if test="${pp.startPage > pp.pagePerBlk }">
					<li><a href="${path }/meetlist/pageNum/${pp.startPage - 1}">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
					<li <c:if test="${pp.currentPage==i}">class="active"</c:if>><a
						href="${path }/meetlist/pageNum/${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${pp.endPage < pp.totalPage}">
					<li><a href="${path }/meetlist/pageNum/${pp.endPage + 1}">다음</a></li>
				</c:if>
		  </c:if>
		  </div>
		  
		<!-- 검색기능 --> 
		<div class="c_m_search" >
		<form align="center" action="${path}/meetlist/pageNum/1">
			<select name="search">
				<option value="meet_title"
					<c:if test="${search=='meet_title'}">selected="selected" </c:if>>제목</option>
				<option value="meet_content"
					<c:if test="${search=='meet_content'}">selected="selected" </c:if>>내용</option>
				<option value="member_id"
					<c:if test="${search=='member_id'}">selected="selected" </c:if>>작성자</option>
				<option value="subcon"
					<c:if test="${search=='subcon'}">selected="selected" </c:if>>제목+내용</option>
			</select> 
			<input type="text" name="keyword"> 
			<input type="submit" value="확인">
		</form>
		</div>
</div>

</div>		

<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>
		
</body>
</html>