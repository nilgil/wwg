<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
    <title>Q&A게시판 목록</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script defer src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script defer src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script defer src="https://kit.fontawesome.com/6e837646d1.js" crossorigin="anonymous"></script>
    <script defer src="./resources/js/qna/main.js"></script>
    <link rel="stylesheet" media='screen' href="./resources/css/qna/footerbar.css">
    <link rel="stylesheet" media='screen' href="./resources/css/qna/navbar.css">
    <link rel='stylesheet' media='screen' href='./resources/css/qna/mainPage.css'>
    <link rel='stylesheet' media='screen' href='./resources/css/qna/board.css'>
</head>
<body>

   <!-- navbar -->
   <nav class="navbar">
    <div class="navbar_logo"><a href="mainPage.html"><img src="../img/제주도로고2 copy.png"> 혼저옵서예</a></div>
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
    
   <!-- center -->
   
   <div class="table">
          <table class="table table-hover">
          <h2>Q&A 게시판</h2>
          <p>The .table-hover class enables a hover state (grey background on mouse over) on table rows:</p>            
          <p>총 글개수 : ${listcount}</p>  
            <thead>
             <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>날짜</th>
              <th>조회수</th>
             </tr>
            </thead>
            
            
            <c:if test="${not empty qna_list}">
            <c:set var="no1" value="${no}"/>
              <c:forEach var="qna" items="${qna_list}">
              
              <%-- <c:set var="num" value="${listcount-(page-1)*10}"/> --%>
            
            <tbody>
             <tr>
               <td>
                 ${no1}
     <%--         <c:out value="${num}" />
                  <c:set var="num" value="${num-1}" /> --%>
               </td>
               
              <td><a href="detail.html">${qna.qna_title}</a></td>
              
              <td>${qna.username}</td>
              <td>${qna.qna_regdate}</td>
              <td>${qna.qna_hit}</td>
             </tr>
     
            </tbody>
                <c:set var="no1" value="${no1 - 1}"></c:set>
               </c:forEach>
              </c:if>
        </table>
        
        <!-- 페이징처리 -->
        <div class="qna_list_paging">
         <c:if test="${page <= 1}">[이전]&nbsp;</c:if>
         
         <c:if test="${page > 1}">
         <a href="qnalist.do?page=${page-1}">[이전]</a>&nbsp;
         </c:if>
         
         <c:forEach var="a" begin="${startpage}" end="${endpage}">
         <c:if test="${a == page }">
         ${a}
         </c:if>
         <c:if test="${a != page }">
          <a href="qnalist.do?page=${a}">${a}</a>&nbsp;
         </c:if>
         </c:forEach>
         
         <c:if test="${page >= maxpage}">[다음]</c:if>
         <c:if test="${page < maxpage}"><a href="qnalist.do?page=${page+1}">[다음]</a></c:if>
         
        </div>
        
        
   </div>
    
    
   <!-- footer -->
   <div class="footer_body">
      <footer class="footer_bar">
          <div class="footer_menu">이메일 문의 | 개인정보취급방침 | 마케팅 문의 | 채용정보</div>
           <div class="footer_wrap">
            <div class="footer_friends">friends</div>
              <a href="https://ijto.or.kr/korean/"><img src="../img/제주관광공사로고.jpg" alt="제주관광공사" width="150" height="150" title="제주관광공사"></a>
              <a href="https://www.jdcdutyfree.com/"><img src="../img/제주면세점.jpg" alt="제주면세점" width="150" height="150" title="면세점"></a>
              <a href="https://www.skyscanner.co.kr/"><img src="../img/스카이스케너로고.jpg" alt="스카이스케너" width="150" height="150" title="항공권"></a>
              <a href="https://www.agoda.com/"><img src="../img/아고다로고.jpg" alt="아고다" width="150" height="150" title="숙박"></a>
              <a href="https://www.myrealtrip.com/"><img src="../img/마리트로고.jpg" alt="마이리틀트립" width="150" height="150" title="투어티켓"></a>
              <a href="https://www.billycar.co.kr/"><img src="../img/빌리카로고.jpg" alt="빌리카" width="150" height="150" title="렌터카"></a>
           </div>
          <div class="footer_footer">
            <p>(주) WWG</p>
            <p>주소 :  서울 마포구 신촌로 176 3층<br>
                                  대표 : 정준희 | 개인정보책임자 : 길재우<br>
                                  사업자등록번호 : 302-5 | 통신판매업신고번호 : 3025<br>
               Copyright GC COMPANY Corp. All rights reserved.</p>
          </div>
      </footer>
  </div>

</body>
</html>