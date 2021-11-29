<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/include/headTag.jsp"%>
<link defer rel='stylesheet' media='screen' href='/css/comm/meetupdate.css'>


<!-- <script type="text/javascript">
	function chk() {
		if (frm.passwd.value != frm.passwd2.value) {
			alert("암호가 다르면 수정할 수 없습니다");
			frm.passwd2.focus();
			return false;
		}
	}
</script> -->
<%-- <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/> --%>
</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>

<!-- center -->
<div class="c_meetupdate_center">

	<div class="side"> 
			<table id="side_menu" class="table table-hover">
		 <thead>
		   <tr><th>커뮤니티</th></tr>
		 </thead>
		 <tbody>
		   <tr><td><a href="/noticelist">공지사항</a></td></tr>
		   <tr><td><a href="/reviewlist">여행후기</a></td></tr>
		   <tr><td><a href="/meetlist">동행구해요</a></td></tr>
		 </tbody>
	 </table>
	</div>
	

	<div class="meetupdate_table">
		<form action="${path}/meetupdate/pageNum/${pageNum}" method="post">
		<input type="hidden" name="meet_no" value="${meet.meet_no}"> 
			<!-- onsubmit="return chk()"> -->
			<table id="m_up_table1" class="table table-hover" >
			    <h2>동행구해요 글수정</h2>
             	<p>The .table-hover class enables a hover state (grey background on mouse over) on table rows:</p>
			
				<tr>
					<td>번호</td>
					<td>${meet.meet_no}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="meet_title" required="required" size="60" maxlength="29"
						value="${meet.meet_title}"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${meet.member_id}</td>
				</tr>
				<tr>
					<td>내용</td>
			<td><textarea rows="25" cols="70" name="meet_content" required="required" maxlength="284" >
		     ${meet.meet_content}
		    </textarea>
						</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="btn btn-dark" type="submit" value="확인"></td>
				</tr>
			</table>
		</form>
	</div>

</div>	
	
<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>
	
</body>
</html>