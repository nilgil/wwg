<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<html>
<head>
    <title>마이페이지</title>
</head>
<body>
<s:authentication property="principal" var="user"/>

<form action="${pageContext.request.contextPath}/user/changeInfoProcess" method="post">

<div class="signUp_center">

    <div class="signUp_table">
        <table id="signUp_table_2" class="table table-hover">
        <h2>회원가입</h2>
        <h3>{제목}</h3>            
          <thead>
           <tr>
            <th colspan="3" text-align="center">회원가입폼</th>
           </tr>
          </thead>
          
          <tbody>
        <tr>
            <td>아이디</td>
            <td><input type="text" name="username" value="${user.username}" readonly/></td>
            <td><p class="alert"></p></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="password"
                       placeholder="pleas type password you wants"></td>
            <td><p class="alert"></p></td>
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
            <td colspan="3" text-align="center">
                <input type="hidden" name="_method" value="PUT"/>
                <s:csrfInput/>
                <button>정보변경</button>
            </td>
        </tr>
        </tbody>
        </table>
    </div>

</div>

</form>

</body>
</html>