<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시판 상세페이지</title>

<%@ include file="/resources/include/headTag.jsp"%>

<script defer type="text/javascript">
$(function(){
	$('#qna_list').load('qnalist.do?page=${page}');
});
</script>


</head>
<body>
<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>

<!-- center -->
        <div class="detail_center">
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
        
            <div class="detail_table">
                <table id="table_2" class="table table-hover">
                <h2>Q&A 게시판</h2>
                <h3>${qnalist.qna_title}</h3>            
                  <thead>
                   <tr>
                    <th>게시물번호:${no}</th>
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
            
               <div class="table_btn">
                <div><input class="btn btn-dark" type="button" id="de_comment" value="답글"
                onclick="location='${path}/qna_commentForm/qna_no/${qnalist.qna_no}/page/${page}'"></div>
                
                <div><input class="btn btn-dark" type="button" id="de_update" value="수정"
                onclick="location='${path}/qna_updateform/qna_no/${qnalist.qna_no}/page/${page}'"></div>
                
                <div><input class="btn btn-dark" type="button" id="de_delete" value="삭제"
                onclick="location='${path}/qna_deletecheck/qna_no/${qnalist.qna_no}/page/${page}'"></div>
               </div>
              </div>
            
            <div id="qna_list"></div>
            
            </div>
           <!-- footer -->
           <%@ include file="/resources/include/footerbar.jsp"%>

</body>
</html>