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

    <div class="signUp_table">
        <table id="signUp_table_2" class="table table-hover">
        <h2>회원가입</h2>           
          <thead>
           <tr>
            <th colspan="2" text-align="center">회원가입폼</th>
           </tr>
          </thead>
          
          <tbody>
        <tr>
            <td>아이디</td>
            <td><input type="text" name="username" placeholder="pleas type username you wants"></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="password" placeholder="pleas type password you wants"></td>
        </tr>
        <tr>    
            <td>이름</td>
            <td><input type="text" name="realname" placeholder="please type your name"></td>
        </tr>
        <tr>
            <td>거주지</td>   
            <td><input type="text" name="location" placeholder="please type the location you live."></td>
        </tr>
        <tr>
            <td colspan="2" text-align="center"> 
                <s:csrfInput/>
                <button>회원가입</button>
            </td>
        </tr>
        </tbody>
        </table>
    </div>

</div>

</form>

<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>

</body>
</html>