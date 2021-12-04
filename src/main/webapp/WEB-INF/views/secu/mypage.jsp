<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<html>
<head>
    <title>마이페이지</title>
    <%@ include file="/resources/include/headTag.jsp"%>
    <link defer rel='stylesheet' media='screen' href='/css/secu/mypage.css'>
</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>

<s:authentication property="principal" var="user"/>
<form action="${pageContext.request.contextPath}/user/changeInfoProcess" method="post">

<div class="mypage_center">
        
            <div class="mypage_id">
            <h2>내 정보 수정</h2><br>
            <p>아이디</p>
            <input class="form-control" type="text" name="username" value="${user.username}" readonly ">
            </div>
       
            <div class="mypage_pw">
            <p>비밀번호</p>
            <input class="form-control" type="password" name="password" placeholder="문자  + 숫자">
            </div>
        
            <div class="mypage_name">
            <p>이름</p>
            <input class="form-control" type="text" name="realname" placeholder="이름" >
            </div>
     
            <div class="mypage_address">
            <p>거주지</p>  
            <input class="form-control" type="text" name="location" placeholder="예)서울,경기...">
            </div>
            
        
                <input type="hidden" name="_method" value="PUT"/>
                <s:csrfInput/>
            <div class="mypage_btn">
            <button id="btn" class="btn" onclick="location.href='${pageContext.request.contextPath}/user/changeInfoProcess'">정보변경</button>
            </div>
      


</div>

</form>

<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>


</body>
</html>