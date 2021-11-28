<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>

<head>
<title>글작성폼</title>
<%@ include file="/resources/include/headTag.jsp"%>
<link rel='stylesheet' media='screen' href='/css/qna/write.css'>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${path}/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
$(function(){
    //전역변수
    var obj = [];              
    //스마트에디터 프레임생성
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: obj,
        elPlaceHolder: "editor",
        sSkinURI: "/smarteditor/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,    
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true,
        }
    });
    //전송버튼
    $("#savebutton").click(function(){
        //id가 smarteditor인 textarea에 에디터에서 대입
        obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
        //폼 submit
        $("#frm").submit();
    })
})
</script>

</head>

<body>
<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>

    <!-- center -->
   <form action="/qnawrite.do" method="post">
    <div class="write_center">
    <div class="side"> 
        <table id="side_menu" class="table table-hover">
            <thead>
                <tr><th>회원문의</th></tr>
            </thead>
            <tbody>
                <tr><td><a href="/qnalist.do">Q&A</a></td></tr>
                <tr><td></td></tr>
                <tr><td></td></tr>
            </tbody>
        </table>
    </div>     
    
     <div class="write_table">
      <table id="write_table1" class="table table-hover">
        <h2>문의글 작성</h2>
        <p>The .table-hover class enables a hover state (grey background on mouse over) on table rows:</p>
        <thead>
        <tr>
            <th colspan="2" >글작성</th>
        </tr>
        </thead>
        <tbody>
        
        <tr>
            <td>아이디</td>
            <td>${user.username}</td>
        </tr>
        
        <tr>
            <td>제목</td>
            <td><input type="text" name="qna_title" required="required" size="60" maxlength="70"></td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea id="" rows="25" cols="70" name="qna_content" required="required" maxlength="5000"></textarea></td>
        </tr>
        
        <tr><td>
<!-- action : 에디터에 입력한 html 코드를 전달받을 Controller페이지 URL -->
<form action="/submit" method="post" id="frm">
    <textarea name="editor" id="editor" rows="10" cols="100" style="width:766px; height:412px;"></textarea>
    <input type="button" id="savebutton" value="서버전송" />
</form>
        </td></tr>
        
        </tbody>
      </table>
      
      <div class="write_end_btn">
        <div><input class="btn btn-dark" type="button" value="미리보기"></div>
        <div><input class="btn btn-dark" type="submit" value="글등록"></div>
        <div><input class="btn btn-dark" type="button" value="작성취소"
        onclick="location='qnalist.do'"></div>
       </div>
       
     </div>
     
    </div>
    </form>

<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>


</body>
</html>