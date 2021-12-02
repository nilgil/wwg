<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<c:set var="path" value="${pageContext.request.contextPath }" />


<html>

<head>
<title>메인페이지</title>
<%@ include file="/resources/include/headTag.jsp"%>
</head>

<body>
<!-- navbar -->
<%@ include file="/resources/include/navbar_login.jsp"%>


<!-- cneter -->
<div class="mainpage_center">

<div class="center_main1">여행후기<hr>
<ul style="list-style-type: circle">
<c:forEach var="review1" items="${reviewlist1}">
<li>
<a href="/reviewviewcont/review_no/${review1.review_no}/pageNum/1">${review1.review_title}
<span class="pull-right">
<fmt:formatDate value="${review1.review_regdate}"
 pattern="yyyy-MM-dd"/>
</span>
</a></li>
</c:forEach>
</ul>
</div>

<div class="center_main5">여행후기<hr>
<ul style="list-style-type: circle">
<c:forEach var="review2" items="${reviewlist2}">
<li>
<a href="/reviewviewcont/review_no/${review2.review_no}/pageNum/2">${review2.review_title}
<span class="pull-right">
<fmt:formatDate value="${review2.review_regdate}"
 pattern="yyyy-MM-dd"/>
</span>
</a></li>
</c:forEach>
</ul>
<hr></div>

<div class="center_main2">여행정보<hr>
    <ul>
        <li><a href="#"><img alt="이미지1" src="/img/qna/test사진1.jpg">제목</a></li>
        <li><a href="#"><img alt="이미지2" src="/img/qna/test사진2.jpg">제목</a></li>
        <li><a href="#"><img alt="이미지3" src="/img/qna/test사진7.jpg">제목</a></li>
    </ul>
</div>

<div class="center_main3">일정게시판<hr>
    <img alt="이미지4" src="/img/qna/test사진6.JPG">
    <p>나리는 꽃가루에 눈이 따끔해 (아야) 눈물이 고여도 꾹 참을래 내 마음 한켠 비밀스런 오르골에 넣어두고서 영원히 되감을 순간이니까 우리 둘의 마지막 페이지를 잘 부탁해 어느 작별이 이보다 완벽할까 Love me only till this spring 오 라일락 꽃이 지는 날 good bye 이런 결말이 어울려 안녕 꽃잎 같은 안녕 하이얀 우리 봄날의 climax 아 얼마나 기쁜 일이야 Ooh ooh Love me only till this spring 봄바람처럼 Ooh ooh Love me only till this spring 봄바람처럼 기분이 달아 콧노래 부르네 (랄라) 입꼬리는 살짝 올린 채 어쩜 이렇게 하늘은 더 바람은 또 완벽한 건지 오늘따라 내 모습 맘에 들어 처음 만난 그날처럼 예쁘다고 말해줄래 어느 이별이 이토록 달콤할까 Love resembles misty dream 오 라일락 꽃이 지는 날 good bye 이런 결말이 어울려 안녕 꽃잎 같은 안녕 하이얀 우리 봄날의 climax 아 얼마나 기쁜 일이야 Ooh ooh Love resembles misty dream 뜬구름처럼 Ooh ooh Love resembles misty dream 뜬구름처럼 너도 언젠가 날 잊게 될까 지금 표정과 오늘의 향기도 단잠 사이에 스쳐간 봄날의 꿈처럼 오 라일락 꽃이 지는 날 good bye 너의 대답이 날 울려 안녕 약속 같은 안녕 하이얀 우리 봄날에 climax 아 얼마나 기쁜 일이야 Ooh ooh Love me only untill this spring 봄바람처럼 Ooh ooh Love me only untill this spring 봄바람처럼 Ooh ooh Love resembles misty dream 뜬구름처럼 Ooh ooh Love resembles misty dream 뜬구름처럼</p>
</div>

<div class="center_main4">제주도 사진<hr>
    <div id="demo" class="carousel slide" data-ride="carousel">
        <ul class="carousel-indicators">
          <li data-target="#demo" data-slide-to="0" class="active"></li>
          <li data-target="#demo" data-slide-to="1"></li>
          <li data-target="#demo" data-slide-to="2"></li>
        </ul>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="/img/qna/test사진3.jpg" alt="제주도">   
          </div>
          <div class="carousel-item">
            <img src="/img/qna/test사진4.jpg" alt="제주도">
          </div>
          <div class="carousel-item">
            <img src="/img/qna/test사진5.png" alt="제주도">  
          </div>
        </div>
        <a class="carousel-control-prev" href="#demo" data-slide="제주도">
          <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="제주도">
          <span class="carousel-control-next-icon"></span>
        </a>
      </div>
</div>

<div class="aside_login">${user.username}님 환영합니다.<hr>
<!--     <form id="login" action="/action_page.php">
        <div class="form-group">
          <label for="usr">아이디</label>
          <input type="text" class="form-control" id="usr" name="username">
        </div>
        <div class="form-group">
          <label for="pwd">비밀번호</label>
          <input type="password" class="form-control" id="pwd" name="password">
        </div>
        <div class="login_btn"><button type="submit" class="btn btn-success">로그인</button> 
            <a href="#">회원가입</a> | 
            <a href="#">아이디/비밀번호 찾기</a></div>
      </form> -->
      
    <form action="/login" method="post">
    <div class="main_profile"><img alt="이미지1" src="/img/qna/프로필사진1.png" width="90" height="90"></div>
    <div class="login_id">
    ${user.username}님 | <a href="/user/mypage">내정보</a>
    </div>
    <div class="logout_btn">
    <input type="button" name="logout" value="로그아웃">
    </div>
    <s:csrfInput/>
     <div class="quit_share"><a href="#">회원탈퇴</a> | 
     <a href="#">친구공유</a></div>
    </form>
    
</div>

<div class="notice">공지사항<hr>
<ul style="list-style-type: circle">
<c:forEach var="notice" items="${noticelist}">
<li>
<a href="/noticelist">${notice.notice_title}
<span class="pull-right">
<fmt:formatDate value="${notice.notice_regdate}"
 pattern="yyyy-MM-dd"/>
</span></a>
</li>
</c:forEach>
</ul><hr>
</div>

<div class="aside1">동행구해요<hr>
<ul style="list-style-type: circle">
<c:forEach var="meet" items="${meetlist}">
<li>
<a href="/meetlist">${meet.meet_title}
<span class="pull-right">
<fmt:formatDate value="${meet.meet_regdate}"
 pattern="yyyy-MM-dd"/>
</span>
</a></li>
</c:forEach>
</ul>
<hr></div>


<div class="aside2">Q&A<hr>

<ul style="list-style-type: circle">
<c:forEach var="qna" items="${qnalist}">
<li>
<a href="/qnalist.do">${qna.qna_title}
<span class="pull-right">
<fmt:formatDate value="${qna.qna_regdate}"
 pattern="yyyy-MM-dd"/>
</span>
</a></li>
</c:forEach>
</ul>
</div>



</div>

<!-- footer -->
<%@ include file="/resources/include/footerbar.jsp"%>

</body>
</html>