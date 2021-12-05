<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>

<head>
<title>글작성폼</title>
<%@ include file="/resources/include/headTag.jsp"%>
<link rel='stylesheet' media='screen' href='/css/qna/write.css'>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>

</head>

<body>
<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>

    <!-- center -->
   <form action="/qnawrite.do" method="post">
    <div class="write_center">
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
    
     <div class="write_table">
      <table id="write_table1" class="table table-hover">
        <h2>문의글 작성</h2>
        <thead>
        <tr>
            <th colspan="2" >글작성</th>
        </tr>
        </thead>
        <tbody>
        
        <tr>
            <td>아이디</td>
            <td>${user.username}</td>
        </tr>
        
        <tr>
            <td>제목</td>
            <td><input type="text" name="qna_title" required="required" size="60" maxlength="29" placeholder="제목(최대 29자)"></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><pre><textarea id="content" rows="25" cols="70" name="qna_content" required="required" maxlength="284" placeholder="내용(최대 284자)"></textarea></pre></td>
        </tr>
        
        </tbody>
      </table>
      
      <div class="write_end_btn">
        <div><input class="btn btn-dark" type="submit" value="글등록"></div>
        <div><input class="btn btn-dark" type="button" value="작성취소"
        onclick="location='qnalist.do'"></div>
       </div>
       
     </div>
     
    </div>
    </form>

<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>


</body>
</html>