<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글삭제 페이지</title>

<%@ include file="/resources/include/headTag.jsp"%>

</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


<!-- center -->
<form action="/qnadelete/qna_no/${qnalist.qna_no}" method="post">
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
                        <pre>${qnalist.qna_content}</pre>
                    </td>
                   </tr>
                   <tr>
                      <td>! 게시글을 삭제하기 위한 비밀번호를 입력하세요 ! <br> <input type="password" id="delete_pw"></td>
                   </tr>
                </tbody>
            </table>
            
            <div class="table_btn">
                <input class="btn btn-dark" type="submit" id="de_delete" value="삭제">
            </div>


            <div class="board_list">
                {여기는 목록 리스트 호출할거임}
            </div>
            
            </div>
            </div>
</form>

         <!-- footer -->
         <%@ include file="/resources/include/footerbar.jsp"%>

</body>
</html>