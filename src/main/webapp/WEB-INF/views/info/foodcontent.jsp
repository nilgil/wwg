<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<%@ include file="/resources/include/headTag.jsp"%>
<link defer rel='stylesheet' media='screen' href='/css/info/foodcontent.css'>
<link defer rel='stylesheet' media='screen' href='/css/info/foodrelist.css'>

<!-- jQuery문 설정 불러오기 -->
<script src="${path}/js/info/jquery.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<!-- 로그인한 사람과 글쓴사람이 동일할때 수정,삭제 가능 -->
<script type="text/javascript">
	var session = '${user.username}';
    var username = '${foodboard.username}';
    console.log(username);
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
var session = '${user.username}';
var username = '${foodboard.username}';
$(function() {	
	$("#like").submit(function(){
    	if(username == session){
        	alert("사용자가 같으면 좋아요를 할수 없습니다.");
        	return false;
        }
    });
});
</script>
</head>
<body>

<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>



<div class="foodcontent_center" >

	<div class="side"> 
			<table id="side_menu" class="table table-hover">
		 <thead>
		   <tr><th>여행정보</th></tr>
		 </thead>
		 <tbody>
		   <tr><td><a href="#">숙소</a></td></tr>
		   <tr><td><a href="/foodlist.do">맛집</a></td></tr>
		   <tr><td><a href="#">여행지</a></td></tr>
		 </tbody>
	 </table>
	</div>

    <div class="foodcontent_table">
	<table id="foodcont_table1" class="table table-hover" align="center">
	<h2>맛집 정보 게시판</h2>
	<p>The .table-hover class enables a hover state (grey background on mouse over) on table rows:</p>
	<tbody>
			<tr>
				<td width="90">제목</td>
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
		
	        <div class="foodcont_btns" align=center>
				<input class="btn btn-dark" type="button" value="목록" onClick="location.href='/foodlist.do/pageNum/${pageNum}' " >
				
				<form action="${path}/foodupdateform.do/food_no/${foodboard.food_no}/pageNum/${pageNum}"
		 		method="post" name="chk" id="chk1">
				<input class="btn btn-dark" type="submit" value="수정" >
				</form>

				<form action="${path}/fooddeleteform.do?food_no=${foodboard.food_no}&pageNum=${pageNum}"
		 		method="post" name="chk" id="chk2">
				<input class="btn btn-dark" type="submit" value="삭제" >
				</form>
				
				<form action="/foodlike.do?food_no=${foodboard.food_no}&pageNum=${pageNum}"
				method="post" id="like">
				<input class="btn btn-danger" type="submit" value="좋아요">
				</form>
	          </div>
			</tbody>
			
		</div>	
			
		<!-- 댓글 작성 -->
		<div class="foodcont_relp" align=center>
		<form name="frm" id="frm">
			<input type="hidden" name="username" value="${user.username}">
			<input type="hidden" name="rfood_no" value="${foodboard.food_no}"> 
			<p>댓글 쓰기:</p>
			<div>
			<textarea class="form-control" rows="3" cols="50" name="food_re_content"></textarea>
			</div>
			<div>
			<input class="btn btn-outline-secondary" type="button" value="확인" id="repInsert">
			</div>
		</form>
		</div>
		
		<!-- 댓글 list 불러오는곳 -->
		<div id="foodrelist" class="food_rel_list"></div>
		
</div>		
		
<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>
		
</body>
</html>