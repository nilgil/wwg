<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/resources/include/headTag.jsp" %>
    <link rel='stylesheet' type='text/css' href='/css/plan/plan_board.css'/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
          integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
    <script defer src="/js/plan/plan_board.js"></script>
</head>
<body>
<input id="userName" type="hidden" value="${userName}"/>
<div id="wrapper">
    <%@ include file="/resources/include/navbar.jsp" %>
    <div id="container">
        <div id="bestPlans">
            <h1>Best Plans</h1>
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
                                <span><i class="fas fa-eye"></i> ${plan.hit}</span>
                                <span><i class="far fa-thumbs-up"></i> ${plan.good}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div id="pubPlans">
            <table id="f_table" class="table table-hover" align="center" width=900>
                <h2>일정 게시판</h2>
                <br>
                <p>총 글개수 : <span id="count"></span></p>
                <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>기간</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                    <th>좋아요</th>
                </tr>
                </thead>
                <tbody id="boardContent">
                </tbody>
            </table>

            <!-- 페이지 넘기기 기능 -->
            <div id="paging">
            </div>

        </div>
    </div>
</div>
<%@ include file="/resources/include/footerbar.jsp" %>
</div>
</body>
</html>
