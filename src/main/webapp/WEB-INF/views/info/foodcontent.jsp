<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>

<!-- jQuery문 설정 불러오기 -->
<script src="${path}/js/info/jquery.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- 로그인한 사람과 글쓴사람이 동일할때 수정,삭제 가능 -->
<script type="text/javascript">  
    var username = '${foodboard.username}';
    var session = '${sessionScope.username}';
	$(function(){
		$("#chk1").submit(function(){
			if (username != session) {
				alert("사용자가 다르면 수정할 수 없습니다");
				return false;
			}				
		});
		$("#chk2").submit(function(){
			if (username != session) {
				alert("사용자가 다르면 삭제할 수 없습니다");
				return false;
			}				
		});
	});	
</script>

<!-- 댓글 작성 jQuery문 -->
<script type="text/javascript">
	$(function() {	
		$('#foodrelist').load('${path}/foodrelist/food_no/${foodboard.food_no}')
		
		$('#repInsert').click(function() {
			if (!frm.food_re_content.value) {
				alert('댓글 입력후에 클릭하시오');
				frm.food_re_content.focus();
				return false;
			}
			var frmData = $('#frm').serialize();		  
			$.post('${path}/foodreInsert', frmData, function(data) {
				$('#foodrelist').html(data);
				frm.food_re_content.value = '';
			});
		});
	});
</script>

<!-- 좋아요 버튼 -->
<script type="text/javascript">
var username = '${foodboard.username}';
var session = '${sessionScope.username}';
$(function() {	
	$("#like").submit(function(){
    	if(username == session){
        	alert("사용자가 같으면 좋아요를 할수 없습니다.");
        	history.go(-1);
        	
    	}else if{(username != session)
        	alert("좋아요.");
        	history.go(-1);
        
    	}

        }
    });
});
</script>

</head>
<body>
	<table border=1 width=400 align="center">
	<caption>상세 페이지</caption>
			<tr>
				<td>제목</td>
				<td>${foodboard.food_title}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${foodboard.username}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${foodboard.food_hit}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><pre>${foodboard.food_content}</pre></td>
			</tr>
		</table>
			<div align=center>
				<input type="button" value="목록" onClick="location.href='/foodlist.do/pageNum/${pageNum}' " >
				
				<form action="${path}/foodupdateform.do/food_no/${foodboard.food_no}/pageNum/${pageNum}"
		 		method="post" name="chk" id="chk1">
				<input type="submit" value="수정" >
				</form>

				<form action="${path}/fooddeleteform.do?food_no=${foodboard.food_no}&pageNum=${pageNum}"
		 		method="post" name="chk" id="chk2">
				<input type="submit" value="삭제" >
				</form>
				
				<form action="/foodlike.do?food_no=${foodboard.food_no}&pageNum=${pageNum}"
				method="post" id="like">
				<input type="submit" value="좋아요">
				</form>
			</div>
			
		<!-- 댓글 작성 -->
		<div align=center>
		<form name="frm" id="frm">
			<input type="hidden" name="username" value="${foodboard.username}">
			<input type="hidden" name="rfood_no" value="${foodboard.food_no}"> 댓글 :
			<textarea rows="3" cols="50" name="food_re_content"></textarea>
			<input type="button" value="확인" id="repInsert">
		</form>
		</div>
		
		<!-- 댓글 list 불러오는곳 -->
		<div id="foodrelist"></div>
</body>
</html>