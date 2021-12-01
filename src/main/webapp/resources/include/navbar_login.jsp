<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<s:authentication property="principal" var="user"/>
<c:set var="path" value="${pageContext.request.contextPath }" />
    
    
 <!-- navbar -->
    <nav class="navbar">
    
    <div class="navbar_logo">
    <c:choose>
    
    <c:when test="${not empty user.username}"> 
    <a href="/loginMain">
    </c:when>
    
    <c:otherwise>
    <a href="/">
    </c:otherwise>
    
    </c:choose>
    
    <img src="/img/qna/jlogo.png">혼저옵서예</a></div>
     

        <ul class="navbar_menu">

            <li>
                <div class="dropdown">
                <button data-toggle="dropdown">
                    <a href="#">여행정보</a>
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">숙소</a>
                    <a class="dropdown-item" href="/foodlist.do">맛집</a>
                    <a class="dropdown-item" href="#">여행지</a>
                </div>
                </div>
            </li>

        <li>
            <div class="dropdown">
                <button data-toggle="dropdown">
                    <a href="#">커뮤니티</a>
                </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="/noticelist">공지사항</a>
                <a class="dropdown-item" href="/reviewlist">여행후기</a>
                <a class="dropdown-item" href="/meetlist">동행구해요</a>
            </div>
            </div>
        </li>

    <li>
        <div class="dropdown">
            <button data-toggle="dropdown">
                <a href="#">여행계획</a>
            </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/plan">일정짜기</a>
            <a class="dropdown-item" href="/plan/my">내일정</a>
            <a class="dropdown-item" href="/plan/board">일정게시판</a>
        </div>
        </div>
    </li>

    <li>
        <div class="dropdown">
            <button data-toggle="dropdown">
                <a href="#">회원문의</a>
            </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="/qnalist.do">Q&A</a>
        </div>
        </div>
    </li>

        </ul>


        <ul class="navbar_search" action="/action_page.php">
           <li><input class="form-control mr-sm-2" type="text" placeholder="애월 카페" title="검색어입력"></li>
           <li><button class="btn btn-success" type="submit">검색</button></li>
        </ul>
        
        
        <div class="navbar_logout" >${user.username}님 | <a href="#">로그아웃</a></div>
        

        <a href="#" class="navbar_toogleBtn">
            <i class="fas fa-bars"></i>
        </a>

        </nav>

