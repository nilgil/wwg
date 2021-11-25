<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ include file="navbar.jsp"%> --%>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>

<head>
<title>Q&A 상세페이지 수정폼</title>
<%@ include file="/resources/include/headTag.jsp"%>
<link rel='stylesheet' media='screen' href='/css/qna/update.css'>
</head>

<body>
<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


<!-- center -->
   <form action="qna_update" method="post">
   <!-- hidden으로 넘기면 DTO 필드와 같으면 따로 매개변수 안잡아도됨...?-->
   <input type="hidden" name="qna_no" value="${qnalist.qna_no}" >
   <input type="hidden" name="page" value="${page}" >
   
    <div class="update_center">
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
    
     <div class="update_table">
      <table id="update_table1" class="table table-hover">
        <h2>문의글 수정</h2>
        <p>The .table-hover class enables a hover state (grey background on mouse over) on table rows:</p>
        <thead>
        <tr>
            <th colspan="2">글 수정</th>
        </tr>
        </thead>
        <tbody>
        
        <tr>
            <td>아이디</td>
            <td>${qnalist.username}</td>
        </tr>
        
        <tr>
            <td>제목</td>
            <td><input type="text" name="qna_title" required="required" size="60" maxlength="70" 
            value="${qnalist.qna_title}"></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea rows="25" cols="70" name="qna_content" required="required" maxlength="5000" 
            >${qnalist.qna_content}</textarea></td>
        </tr>
        </tbody>
      </table>

     <div class="update_end_btn">
        <div><input class="btn btn-dark" type="button" value="미리보기"></div>
        <div><input class="btn btn-dark" type="submit" value="글수정"></div>
        <div><input class="btn btn-dark" type="button" value="수정취소"
        onclick="location='/qnalist.do'"></div>
     </div>
     
     
     </div>
    </div>
    </form>


<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>


</body>
</html>