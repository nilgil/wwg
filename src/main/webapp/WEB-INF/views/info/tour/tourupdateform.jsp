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
<link defer rel='stylesheet' media='screen' href='/css/info/foodupdate.css'>

<!-- CKeditor 적용 -->
<script type="text/javascript" src="/js/info/ckeditor/ckeditor.js"></script>
</head>
<body>
<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>

<!-- center -->
<div class="foodupdate_center">

    <div class="side"> 
			<table id="side_menu" class="table table-hover">
		 <thead>
		   <tr><th>여행정보</th></tr>
		 </thead>
		 <tbody>
		   <tr><td><a href="/tourlist.do">숙소</a></td></tr>
		   <tr><td><a href="/foodlist.do">맛집</a></td></tr>
		   <tr><td><a href="/tourlist.do">여행지</a></td></tr>
		 </tbody>
	 </table>
	</div>
	
	
<div class="foodupdate_table">
<form method=post action="${path}/tourupdate.do/pageNum/${pageNum}">
	<input type="hidden" name="tour_no" value="${tourboard.tour_no}">
	<input type="hidden" name="username" value="${tourboard.username}">
<table id="f_up_table1" class="table table-striped" width=400 align=center>
	<h2>여행지 게시글 수정</h2>
	<tr><th>번호</th>
		<td>${tourboard.tour_no}</td>
	</tr>
	<tr><th>제목</th>
		<td><input type=text name="tour_title" required="required"
			value="${tourboard.tour_title}"></td>
	</tr>
	<tr><th>작성자명</th>
		<td>${tourboard.username}</td>
	</tr>
	<tr><th>내용</th>
		<td><textarea id="content" name="tour_content">${tourboard.tour_content}</textarea>
		<script type="text/javascript">	// 글쓰기 editor 및 사진 업로드 기능
			CKEDITOR.replace('content',
			{filebrowserUploadUrl:'/tour/imageUpload.do'
			});
		</script></td>
	</tr>
	<tr><td colspan=2 align=center>
			<input class="btn btn-dark" type=button value="글목록"
			onClick="location.href='/tourlist.do/pageNum/${pageNum}' ">
			<input class="btn btn-dark" type=submit value="글작성">
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