<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<c:set var="path" value="${pageContext.request.contextPath }" />


<!DOCTYPE html>
<html>

<head>
<title>Q&A 게시판 답글페이지</title>
<%@ include file="/resources/include/headTag.jsp"%>
<link rel='stylesheet' media='screen' href='/css/qna/comment.css'>
</head>

<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


<!-- center -->

        <div class="comment_center">
        
            <div class="side"> 
            <form method="post" action="qna_comment.do">
            <input type="hidden" name="page" value="${page}">
            <input type="hidden" name="qna_re_ref" value="${qnalist.qna_re_ref}">
            <input type="hidden" name="qna_re_lev" value="${qnalist.qna_re_lev}">
            <input type="hidden" name="qna_re_seq" value="${qnalist.qna_re_seq}">
            
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
        
            <div class="comment_table">
                <table id="table_1" class="table table-hover">
                <h2>Q&A 게시판</h2>
                <h3>${qnalist.qna_title}</h3>  
                          
                  <thead>
                   <tr>
                    <th>{게시물번호}</th>
                    <th>작성자:${qnalist.username}</th>
                    <th>댓글수:${qnalist.qna_re_lev}</th>
                    <th>조회수:${qnalist.qna_hit}</th>
                    <th>날짜:${qnalist.qna_regdate}</th>
                   </tr>
                  </thead>
                  
                  <tbody>
                   <tr>
                    <td colspan="5" text-align="center">
                        <pre>${qnalist.qna_content}</pre>
                    </td>
                   </tr>
                  </tbody>
                  
                </table>


            
          <table id="table_2" class="table table-hover">
           <thead>
            <tr><th>※답글작성※</th></tr>
           </thead>
           <tr>
            <td>작성자</td>
            <td><input type="text" name="username" value="${user.username }"></td>
           </tr>
          
          <tr>
            <td>답글 제목</td>
            <td><input type="text" name="qna_title" required="required" size="60" maxlength="70"></td>
          </tr>
          <tr>
            <td>답글 내용</td>
            <td><textarea rows="15" cols="70" required="required" name="qna_content" required="required" maxlength="5000"></textarea></td>
          </tr>
          <tr>
            <td colspan="2" align="center">
            <input type="submit" class="btn btn-dark" value="답글등록">
            <input type="button" class="btn btn-dark" value="취소"
            onclick="location='/qnalist.do?page=${page}'"></td>
          </tr>
          
          </table>
          </form>
            
          </div>
          </div>
           
<!-- footer  --> 
<%@ include file="/resources/include/footerbar.jsp"%>

</body>
</html>