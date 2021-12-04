<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>


<html>
<head>
<title>Title</title>
<link rel='stylesheet' media='screen' href='/css/admin/memberList.css'>
<script
	src="${pageContext.request.contextPath}/admi/memberList.js"></script>

<script>
	window.onload = function() {
		console.log("시작");
		let memberList = new MemberList();
		for (let i = 0; i <= document.getElementsByClassName('user-info').length; i++) {
			document.getElementsByClassName('user-del')[i].addEventListener(
					"click", memberList.deleteUser);
		}
	}
</script>


</head>

<body>

	<!-- cneter -->
	<%--<s:authentication property="principal" var="user"/>--%>
	<%--    ${user.username}--%>
	<%--    ${user.password}--%>
	<%--    ${user.authorities}--%>

	<!-- hasRole('ROLE_ADMIN') -->

	<div class="memberList_center">

		<div class="memberList_side">
			<table id="side_menu" class="table table-hover">
				<thead>
					<tr>
						<th>관리자모드</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="/admin/memberList">회원관리/삭제</a></td>
					</tr>
				</tbody>
				</table>
	    </div>
          
        

		<div class="memberList_table">
				
					<table class="table table-hover">
						<h2>회원관리 게시판</h2>
						<p>The .table-hover class enables a hover state (grey
							background on mouse over) on table rows:</p>
						<p> 총 회원수 : ${memberList.size()} </p>
						<thead>
							<tr>
								<th>아이디</th>
								<th>지역</th>
								<th>이름</th>
							</tr>
						</thead>
						
						<tbody>
						<s:authorize access="permitAll">
							<c:forEach var="i" items="${memberList}">
								<div data-user="${i.username}" class="user-info">
					            <tr>
								<th><c:out value="${i.username}" /></th>
								<th><c:out value="${i.location}" /></th>
								<th><c:out value="${i.realname}" /></th>
								<th><button class="user-del">삭제</button></th>
								</tr>
								</div>
							</c:forEach>
						</s:authorize>
						</tbody>

					</table>
			</div>

</div>
</body>

</html>