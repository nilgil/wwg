<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<a href="/comm_noticeinsertform">글쓰기</a>  <br>


<table border=1 width=700 align=center>
	<caption>게시판 목록</caption>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
		<th>조회수</th>
	</tr>
	
	<c:set var="num" value="${listcount - (page-1) * 10 }"/>
	<c:forEach var="b" items="${list}">
	<tr>
		<td>${b.notice_no}
		</td>
		<td>	
		<a href="/noticeview/notice_no/${b.notice_no}/pageNum/${pp.currentPage}">
		${b.notice_title}</a>		
		</td>
		<td>${b.member_id}</td>		
		<td>		
			<fmt:formatDate value="${b.notice_regdate}"
							pattern="yyyy-MM-dd EEE요일  HH:mm:ss "/>		
		</td>
		<td>${b.notice_hit}</td>
	</tr>
	</c:forEach>	
	
</table> <br><br>

<!-- 페이지 처리 -->
<center>
<c:if test="${listcount > 0}">

<!-- 1페이지로 이동 -->
	<a href="./BoardListAction.do?page=1"
	   style="text-decoration:none"> << </a>
	   
<!-- 이전 블럭으로 이동 -->	
<c:if test="${startPage > 10 }">
	<a href="./BoardListAction.do?page=${startPage-10}">[이전]</a>
</c:if> 

<!-- 각 블럭에 10개의 페이지 출력 -->
<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<c:if test="${i == page}">	<!-- 현재 페이지 -->
		[${i}]
	</c:if>
	<c:if test="${i != page}">	<!-- 현재 페이지가 아닌 경우 -->
		<a href="./BoardListAction.do?page=${i}">[${i}]</a>
	</c:if>
</c:forEach>

<!-- 다음 블럭으로 이동 -->
<c:if test="${endPage < pageCount}">
	<a href="./BoardListAction.do?page=${startPage+10}">[다음]</a>
</c:if> 
 
<!-- 마지막 페이지로 이동 --> 
 	<a href="./BoardListAction.do?page=${pageCount}" 
 	   style="text-decoration:none"> >> </a> 

</c:if>

</center>



