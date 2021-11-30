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
<link defer rel='stylesheet' media='screen' href='/css/comm/comm_notiupdate.css'>

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
<div class="c_notiupdate_center">

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
	
	<div class="c_notiupdate_table">
		<form action="${path}/noticeupdate/pageNum/${pageNum}" method="post">
		<input type="hidden" name="notice_no" value="${notice.notice_no}"> 
			<!-- onsubmit="return chk()"> -->
			<table id="c_n_up_table1" class="table table-hover">
			<h2>공지사항 게시글 수정</h2>
				<tr>
					<td width="90">번호</td>
					<td>${notice.notice_no}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="notice_title" required="required" size="60" maxlength="29"
						value="${notice.notice_title}"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${notice.member_id}</td>
				</tr>
				<tr>
					<td>내용</td>
			<td><textarea rows="25" cols="70" name="notice_content" required="required">
		     ${notice.notice_content}
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