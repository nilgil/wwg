<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글작성</title>
<%@ include file="/resources/include/headTag.jsp"%>
<link defer rel='stylesheet' media='screen' href='/css/comm/comm_notiinsert.css'>

<!-- jQuery문 설정 불러오기 -->
<script src="${path}/js/info/jquery.js"></script>

</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>

<!-- center -->
<div class="c_notinsert_center">

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


<div class="c_notinsert_table" >
<form method=post action="/comm_noticeinsert">
<table id="c_notinsert_table1" class="table table-hover" >
    <h2>공지사항 글작성</h2>
	&nbsp;
	<tr><th>제목</th>
		<td><input type=text name="notice_title" required="required" size="60" maxlength="29" placeholder="제목(최대 29자)"></td>
	</tr>
	<tr><th>내용</th>
		<td><textarea rows="25" cols="70" name="notice_content" maxlength="284" placeholder="내용(최대 284자)"></textarea></td>
	</tr>
	<tr><td colspan=2 align=center>
			<input class="btn btn-dark" type=button value="글목록"
			onClick="location.href='noticelist' ">
			<input class="btn btn-dark"  type=submit value="글작성">
			<input class="btn btn-dark" type=reset value="취소">
		</td>
	</tr>
</table>
</form>
</div>

</div>

<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>

</body>
</html>