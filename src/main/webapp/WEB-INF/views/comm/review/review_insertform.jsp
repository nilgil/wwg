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
<link defer rel='stylesheet' media='screen' href='/css/comm/review_insert.css'>
</head>
<body>
<script src="//cdn.ckeditor.com/4.17.1/standard/ckeditor.js"></script>
<!-- CKeditor 적용 -->
<!-- <script type="text/javascript" src="/js/info/ckeditor/ckeditor.js"></script> -->

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


<!-- center -->
<div class="review_insert_center">

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
	
<div class="review_insert_table" >
<form method=post action="/comm_reviewinsert">
<table id="r_inert_table1" class="table table-hover">
	<h2>여행후기 글작성</h2>
	<tbody>
	<tr><th>제목</th>
		<td><input type=text name="review_title" required="required" size="60" maxlength="29" placeholder="제목(최대 29자)"></td>
	</tr>
	<tr><th>내용</th>
		<td><textarea id="content" name="review_content" rows="25" cols="70" maxlength="284" placeholder="내용(최대 284자)"></textarea>
		<script type="text/javascript">	// 글쓰기 editor 및 사진 업로드 기능
			CKEDITOR.replace('content',
			{filebrowserUploadUrl:'/comm/imageupload'
			});
		</script></td>
	</tr>
	
	<tr><td colspan=2 align=center>
			<input class="btn btn-dark" type=button value="글목록"
			onClick="location.href='reviewlist' ">
			<input class="btn btn-dark" type=submit value="글작성">
			<input class="btn btn-dark" type=reset value="취소">
		</td>
	</tr>
	</tbody>
</table>
</form>

</div>

</div>

<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>

</body>
</html>