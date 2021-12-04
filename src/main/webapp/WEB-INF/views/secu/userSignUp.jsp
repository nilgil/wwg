<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<html>
<head>
    <title>회원가입</title>
    <%@ include file="/resources/include/headTag.jsp"%>
    <link defer rel='stylesheet' media='screen' href='/css/secu/userSignUp.css'>
    
</head>
<body>
<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


<form action="${pageContext.request.contextPath}/userSignUpProcess" method="post">


     

            <div class="signUp_center">        
            
           
            
            <div class="signUp_id">
            <h2>회원가입</h2><br>
            <p>아이디</p>
            <input class="form-control" type="text" name="username" placeholder="아이디">
            </div>
       
            <div class="signUp_pw">
            <p>비밀번호</p>
            <input class="form-control" type="password" name="password" placeholder="문자  + 숫자 ">
            </div>
        
            <div class="signUp_name">
            <p>이름</p>
            <input class="form-control" type="text" name="realname" placeholder="이름">
            </div>
     
            <div class="signUp_address">
            <p>거주지</p>  
            <input class="form-control" type="text" name="location" placeholder="예)서울,경기...">
            </div>
     
            <s:csrfInput/>
            
            <div class="signUp_btn">
            <button id="btn" class="btn" onclick="location.href='${pageContext.request.contextPath}/userSignUpProcess'">가입</button>
            </div>
  

</div>

</form>

<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>

</body>
</html>