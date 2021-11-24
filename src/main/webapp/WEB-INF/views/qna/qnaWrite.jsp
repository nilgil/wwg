<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
    <title>글작성폼</title>
    <%@ include file="/resources/include/headTag.jsp"%>
    
</head>
<body>

<%-- <jsp:include page="/resources/include/navbar.jsp"></jsp:include> --%>

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
        <p>The .table-hover class enables a hover state (grey background on mouse over) on table rows:</p>
        <thead>
        <tr>
            <th colspan="2" >글작성</th>
        </tr>
        </thead>
        <tbody>
        
        <tr>
            <td>아이디</td>
            <td><input type="text" name="username" required="required" size="10" ></td>
        </tr>
        
        <tr>
            <td>제목</td>
            <td><input type="text" name="qna_title" required="required" size="60" maxlength="70"></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea id="" rows="25" cols="70" name="qna_content" required="required" maxlength="5000"></textarea></td>
        </tr>
        </tbody>
      </table>
      
      <div class="write_end_btn">
        <div><input class="btn btn-dark" type="button" value="미리보기"></div>
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