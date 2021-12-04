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

<script>
$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
});
</script>

</head>

<body>
<!-- navbar -->
<%@ include file="/resources/include/navbar.jsp"%>


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
</ul><hr>
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
    <ul style="list-style-type: circle">
        <c:forEach var="fod" items="${food}">
        <li>
        <a href="#"><img alt="이미지2" src="/food/ckImgSubmit.do?uid=a231549b-a265-444c-91c6-c9c6be42fe80&amp;fileName=Tulips.jpg"><span>${fod.food_title}</span></a>
     <%--    <a href="#"><span>${fod.food_content}</span> <span>${fod.food_title}</span></a> --%>
        </li>
        </c:forEach>
        
        <c:forEach var="sy" items="${stay}">
        <li>
        <a href="#"><img alt="이미지2" src="/img/qna/test사진2.jpg"><span>${sy.stay_title}</span></a>
        </li>
        </c:forEach>
        
        <c:forEach var="tr" items="${tour}">
        <li>
        <a href="#"><img alt="이미지3" src="/img/qna/test사진7.jpg"><span>${tr.tour_title}</span></a>
        </li>
        </c:forEach>
    </ul>
</div>

<div class="center_main3">인기 여행계획<hr>
    <div id="plan-box">
        <c:forEach items="${bestPlans}" var="plan" varStatus="st">
            <div class='plan'>
                <div id='plan-img'>
                    <a href="/plan/view/${plan.idx}"><img src="${thumbnails[st.index]}"
                                                          alt="https://via.placeholder.com/150"/></a>
                </div>
                <div id='plan-info'>
                    <div id="day-info">
                        <p>
                            <c:if test="${plan.days == 1}">당일치기</c:if>
                            <c:if test="${plan.days != 1}">${plan.days-1}박 ${plan.days}일</c:if>
                        </p>
                        <p>${plan.username}</p>
                    </div>
                    <div id="title">
                        <a href="/plan/view/${plan.idx}"><p>${plan.title}</p></a>
                    </div>
                    <div id="good">
                        <p><i class="fas fa-eye"></i> ${plan.hit}</p>
                        <p><i class="far fa-thumbs-up"></i> ${plan.good}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
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

<div class="aside_login">
      
    <c:choose>
    <c:when test="${username eq 'guest'}">
    
          로그인<hr>
    
    <form action="/login" method="post">
    <div class="form-group">
    <label for="usr">아이디</label>
    <input type="text" class="form-control" name="id" placeholder="아이디">
    </div>
    <div class="form-group">
    <label for="pwd">비밀번호</label>
    <input type="password" class="form-control" name="pw" placeholder="비밀번호">
    </div>
    <s:csrfInput/>
    <div class="login_btn"><button type="submit" class="btn btn-success" >로그인</button>
     <a href="/userSignUp">회원가입</a></div>    
    </form>
    
    </c:when>
    
    <c:otherwise>
    
         ${username}님 환영합니다^^<hr>
    
    <form action="/login" method="post">
    <div class="main_profile"><img alt="이미지1" src="/img/qna/프로필사진1.png" width="90" height="90"></div>
    <div class="login_id">
    ${username}님 | <a href="/user/mypage">내정보</a>
    </div>
    <div class="logout_btn">
    <input type="button" name="logout" value="로그아웃" onclick="location='/logout'">
    </div>
    <s:csrfInput/>
    <div class="quit_share"><a data-toggle="tooltip" title="안 하실거죠?!" href="/user/quit">회원탈퇴</a></div>
    </form>
    
    </c:otherwise>
    
    </c:choose>  
    
</div>



<div class="notice">공지사항<hr>
<ul style="list-style-type: circle">
<c:forEach var="notice" items="${noticelist}">
<li>
<a href="/noticeviewcont/notice_no/${notice.notice_no}/pageNum/1">${notice.notice_title}
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
<a href="/meetviewcont/meet_no/${meet.meet_no}/pageNum/1">${meet.meet_title}
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
<a href="/qna_detailcont/qna_no/${qna.qna_no}/page/1">${qna.qna_title}
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