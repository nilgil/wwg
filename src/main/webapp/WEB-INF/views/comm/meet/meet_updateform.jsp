<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
	<div align="center">
		<h2>게시글 글수정</h2>
		<form action="${path}/meetupdate/pageNum/${pageNum}" method="post">
		<input type="hidden" name="meet_no" value="${meet.meet_no}"> 
			<!-- onsubmit="return chk()"> -->
			<table class="table table-striped">
				<tr>
					<td>번호</td>
					<td>${meet.meet_no}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="meet_title" required="required"
						value="${meet.meet_title}"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${meet.member_id}</td>
				</tr>
				<tr>
					<td>내용</td>
			<td><textarea rows="5" cols="30" name="meet_content" required="required">
		     ${meet.meet_content}
		    </textarea>
						</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="확인"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>