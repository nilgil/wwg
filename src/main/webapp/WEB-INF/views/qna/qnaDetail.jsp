<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시판 상세페이지</title>
</head>
<body>


        <div class="detail">
        
            <div class="side"> 
                <table id="side_menu" class="table table-hover">
                     <thead>
                       <tr><th>회원문의</th></tr>
                     </thead>
                     <tbody>
                       <tr><td><a href="qna_board.html">Q&A</a></td></tr>
                       <tr><td>Q&A</td></tr>
                       <tr><td>Q&A</td></tr>
                     </tbody>
                 </table>
            </div>
        
            <div class="table">
                <table id="table_2" class="table table-hover">
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
                        ${qnalist.qna_content}
                    </td>
                   </tr>
                </tbody>
            </table>
            </div>
            
            
            </div>
           

</body>
</html>