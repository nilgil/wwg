<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <%@ include file="/resources/include/headTag.jsp"%>
    <link defer rel='stylesheet' media='screen' href='/css/secu/login.css'>
    
</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


<form action="/login" method="post">

<div class="loginPage_center1">
 
    <div class="loginPage_id">
    <h4>로그인 후 이용 가능합니다.</h4><br>
    <label for="usr">아이디</label>
    <input class="form-control" type="text" name="id" placeholder="아이디">
    </div>
    
    <div class="loginPage_pw"> 
    <label for="pwd">비밀번호</label>
    <input class="form-control" type="password" name="pw" placeholder="비밀번호">
    </div>
    
    
<%--    <s:csrfInput/>--%>
    <div class="loginPage_btn">
     <button type="submit" class="btn btn-success" >로그인</button><br>
     <a href="/userSignUp">회원가입</a>
    </div>
     
    
</div>

</form>



<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>


</body>
</html>
