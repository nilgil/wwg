<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
    
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이게이션바</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <script src="https://kit.fontawesome.com/6e837646d1.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel='stylesheet' type='text/css' media='screen' href='navbar.css'>
    <script src='main.js'></script>
</head>
<body> -->

 <!-- navbar -->
    <nav class="navbar">
        <div class="navbar_logo"><a href="mainPage.html"><img src="/resources/img/qna/jlogo.png"> 혼저옵서예</a></div>
<%--    <div class="navbar_logo"><a href="mainPage.html"><img src="${path}/img/jlogo.png"> 혼저옵서예</a></div> --%>
        
        <ul class="navbar_menu">

            <li>
                <div class="dropdown">
                <button data-toggle="dropdown">
                    <a href="#">여행정보</a>
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">숙소</a>
                    <a class="dropdown-item" href="#">맛집</a>
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
                <a class="dropdown-item" href="#">공지사항</a>
                <a class="dropdown-item" href="#">여행후기</a>
                <a class="dropdown-item" href="#">동행구해요</a>
            </div>
            </div>
        </li>
        
    <li>
        <div class="dropdown">
            <button data-toggle="dropdown">
                <a href="#">여행계획</a>
            </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">계획짜기</a>
            <a class="dropdown-item" href="#">계획게시판</a>
        </div>
        </div>
    </li>
        
    <li>
        <div class="dropdown">
            <button data-toggle="dropdown">
                <a href="#">회원문의</a>
            </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Q&A</a>
        </div>
        </div>
    </li>
        
        </ul>
        

        <ul class="navbar_search" action="/action_page.php">
           <li><input class="form-control mr-sm-2" type="text" placeholder="애월 카페" title="검색어입력"></li>
           <li><button class="btn btn-success" type="submit">검색</button></li>
        </ul>
        
        <a href="#" class="navbar_toogleBtn">
            <i class="fas fa-bars"></i>
        </a>
        
        </nav>

<!-- </body> 
</html> -->