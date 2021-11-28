<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<!-- CKeditor 적용 -->
<script src="//cdn.ckeditor.com/4.17.1/standard/ckeditor.js"></script>

	<div align="center">
		<h2>게시글 글수정</h2>
		<form action="${path}/reviewupdate/pageNum/${pageNum}" method="post">
		<input type="hidden" name="review_no" value="${review.review_no}"> 
			<table border=1 width=800 align=center>
				<tr>
					<td>번호</td>
					<td>${review.review_no}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="review_title" required="required"
						value="${review.review_title}"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${review.member_id}</td>
				</tr>
				<tr>
					<td>내용</td>
			<td><textarea rows="5" cols="30" name="review_content"  id="content" required="required">
		     ${review.review_content}</textarea>
		    <script type="text/javascript">	// 글쓰기 editor 및 사진 업로드 기능
			CKEDITOR.replace('content',
			{filebrowserUploadUrl:'/comm/imageupload'
			});
		</script></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="확인"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>