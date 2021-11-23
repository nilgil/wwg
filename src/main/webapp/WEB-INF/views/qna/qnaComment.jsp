<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시판 답글페이지</title>

<%@ include file="/resources/include/headTag.jsp"%>

</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


<!-- center -->
        <div class="comment_center">
        
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
            <td>답글 제목</td>
            <td><input type="text" name="qna_title" required="required" size="60" maxlength="70"></td>
          </tr>
          <tr>
            <td>답글 내용</td>
            <td><textarea rows="15" cols="70" required="required" name="qna_content" required="required" maxlength="5000"></textarea></td>
          </tr>
          <tr>
            <td colspan="2" align="center">
            <input type="button" class="btn btn-dark" value="답글등록"
            onclick="location='/qna_comment/page/${page}'">
            <input type="button" class="btn btn-dark" value="취소"
            onclick="location='qnalist.do'"></td>
          </tr>
          
          </table>
          
          <div class="board_list">{여기는 목록 리스트 호출할거임}</div>
            
          </div>
          </div>
           
<!-- footer  --> 
<%@ include file="/resources/include/footerbar.jsp"%>

</body>
</html>