<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
		<form action="${path}/reviewupdate/pageNum/${pageNum}" method="post">
		<input type="hidden" name="review_no" value="${review.review_no}"> 
			<!-- onsubmit="return chk()"> -->
			<table class="table table-striped">
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
			<td><textarea rows="5" cols="30" name="review_content" required="required">
		     ${review.review_content}
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