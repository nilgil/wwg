<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<c:set var="path" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>

<head>
<title>Q&A게시판 목록</title>
<%@ include file="/resources/include/headTag.jsp"%>
<link rel='stylesheet' media='screen' href='/css/qna/qnalist.css'>
</head>

<body>
   <!-- navbar -->
   <%@ include file="/resources/include/navbar.jsp"%>
    
    <!-- center -->
   <div class="qnalist_center">
   
     <div class="side"> 
                <table id="side_menu" class="table table-hover">
                     <thead>
                       <tr><th>회원문의</th></tr>
                     </thead>
                     <tbody>
                       <tr><td><a href="/qnalist.do">Q&A</a></td></tr>
                       <tr><td></td></tr>
                       <tr><td></td></tr>
                     </tbody>
                 </table>
      </div>
   
   <div class="table">
          <table class="table table-hover">
          <h2>Q&A 게시판</h2>           
          <p>총 글개수 : ${listcount}</p>  
            <thead>
             <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>날짜</th>
              <th>조회수</th>
             </tr>
            </thead>
            
            
            <c:if test="${not empty qnalist}">
            <c:set var="no1" value="${no}"/>
              <c:forEach var="qna" items="${qnalist}">
              
            <tbody>
             <tr>
              <td>${no1}</td>

              <td>
              <c:if test="${qna.qna_re_lev != 0}"> 
						<c:forEach var="q" begin="1" end="${qna.qna_re_lev}">
							&nbsp;&nbsp;
						</c:forEach>
						<img src="/img/qna/reply.png" width="22" height="22">	
		      </c:if>
              <a href="${path}/qna_detail/qna_no/${qna.qna_no}/page/${page}/no/${no1}">${qna.qna_title}</a></td>
              
              <td>${qna.username}</td>
              <td>${qna.qna_regdate}</td>
              <td>${qna.qna_hit}</td>
             </tr>
   
            </tbody>
                <c:set var="no1" value="${no1-1}"></c:set>
               </c:forEach>
              </c:if>
        </table>
        
        
        <!-- 글작성버튼 -->
          <div class="qnalist_write_btn">
            
            <c:choose>
            <c:when test="${username eq 'guest'}">
            <a href="loginPage"><input class="btn btn-dark" type="button" value="글작성"></a>
            </c:when>
            <c:otherwise>
            <a href="qnawriteform.do"><input class="btn btn-dark" type="button" value="글작성"></a>
            </c:otherwise>
            
            </c:choose>
            

            
          </div>
        
        
        <!-- 페이징처리 -->
        <div class="qna_list_paging">
         <c:if test="${page <= 1}">[이전]&nbsp;</c:if>
         
         <c:if test="${page > 1}">
         <a href="qnalist.do?page=${page-1}">[이전]</a>&nbsp;
         </c:if>
         
         <c:forEach var="a" begin="${startpage}" end="${endpage}">
         <c:if test="${a == page }">
         &nbsp;${a}&nbsp;
         </c:if>
         <c:if test="${a != page }">
          <a href="qnalist.do?page=${a}">${a}</a>&nbsp;
         </c:if>
         </c:forEach>
         
         <c:if test="${page >= maxpage}">[다음]</c:if>
         <c:if test="${page < maxpage}"><a href="qnalist.do?page=${page+1}">[다음]</a></c:if>
        </div>
        
        
        <!-- 목록검색기능 -->
        <div class="search_bar">
        <form action="qnalist.do">
        <input type="hidden" name="page" value="${page}">
        
            <select name="search">
                <option value="qna_title"
                <c:if test="${search=='qna_title'}"> selected="selected"</c:if> > 제목 </option>
                <option value="qna_content"
                <c:if test="${search=='qna_content'}"> selected="selected"</c:if> > 내용 </option>
                <option value="username"
                <c:if test="${search=='username'}"> selected="selected" </c:if> > 작성자 </option>
                <option value="qna_titcon"
                <c:if test="${search=='qna_titcon'}"> selected="selected" </c:if> > 제목+내용 </option>
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