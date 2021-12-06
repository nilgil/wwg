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
<link defer rel='stylesheet' media='screen' href='/css/info/foodform.css'>
<!-- <script src="//cdn.ckeditor.com/4.17.1/standard/ckeditor.js"></script> -->
<!-- CKeditor 적용 -->
<script type="text/javascript" src="/js/info/ckeditor/ckeditor.js"></script>
</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


<!-- center -->
<div class="foodform_center">

     <div class="side"> 
                <table id="side_menu" class="table table-hover">
                     <thead>
                       <tr><th>회원문의</th></tr>
                     </thead>
                     <tbody>
		             <tr><td><a href="/staylist.do">숙소</a></td></tr>
		             <tr><td><a href="/foodlist.do">맛집</a></td></tr>
		             <tr><td><a href="/tourlist.do">여행지</a></td></tr>
                     </tbody>
                 </table>
      </div>

<div class="foodform_table">

<form method=post action="tourwrite.do">
<table id="foodform_table1" class="table table-hover" width=900 align=center>
	<h2>여행지 게시판 글작성</h2>
	&nbsp;
	<tr><th>작성자명</th>
		<td>${user.username}</td>
	</tr>
	<tr><th>제목</th>
		<td><input type=text name="tour_title" required="required" size="60" maxlength="29" placeholder="제목(최대 29자)"></td>
	</tr>
	<tr><th>내용</th>
		<td><textarea rows="25" cols="70" id="content" name="tour_content" maxlength="284" placeholder="내용(최대 284자)"></textarea>
		<script type="text/javascript">	// 글쓰기 editor 및 사진 업로드 기능
			CKEDITOR.replace('content',
			{filebrowserUploadUrl:'/tour/imageUpload.do'
			});
		
			$(function(){
				$("form").submit(function(){
					if(CKEDITOR.instances.content.getData() =='' 
				        || CKEDITOR.instances.content.getData().length ==0){
				      alert("내용을 입력해주세요.");
				     return false;
				    }
				});		
			});
		</script></td>
	</tr>
	<tr><td colspan=2 align=center>
			<input class="btn btn-dark" type=button value="글목록"
			onClick="location.href='tourlist.do' ">
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