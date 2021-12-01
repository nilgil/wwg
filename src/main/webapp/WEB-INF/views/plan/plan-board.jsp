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
                                <p>
                                        ${plan.username}
                                </p>
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
                <p>총 글개수 : ${pageInfo.count}</p>
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

                <tbody>
                <c:forEach var="plan" items="${plans}">
                    <tr>
                        <td>${plan.idx}</td>
                        <td><a href="/plan/view/${plan.idx}">
                                ${plan.title}
                        </a></td>
                        <td style="width: 120px">(<c:if test="${plan.days == 1}">당일치기</c:if><c:if test="${plan.days != 1}">${plan.days-1}박 ${plan.days}일</c:if>)</td>
                        <td style="width: 100px">${plan.username}</td>
                        <td style="width: 250px">
                            <span style="margin-right: 10px"><fmt:formatDate value="${plan.regDate}" pattern="yyyy-MM-dd"/></span>
                            <span><fmt:formatDate value="${plan.regDate}" pattern="HH:mm:ss"/></span>
                        </td>
                        <td style="width: 80px">${plan.hit}</td>
                        <td style="width: 80px">${plan.good}</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- 페이지 넘기기 기능 -->
            <div id="paging">
                <c:if test="${pageInfo.startPage > pageInfo.divPages }">
                    <li><a href="/plan/board?page=${pageInfo.startPage - 1}">이전</a></li>
                </c:if>
                <c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
                    <li <c:if test="${pageInfo.page==i}">class="active"</c:if>><a
                            href="/plan/board?page=${i}">${i}</a></li>
                </c:forEach>
                <c:if test="${pageInfo.endPage < pageInfo.totalPage}">
                    <li><a href="/plan/board?page=${pageInfo.endPage + 1}">다음</a></li>
                </c:if>
            </div>


            <!-- 검색 기능 -->
            <div class="foodlist_search">
                <form align="center" action="${path}/foodlist.do/page/1">
                    <select name="search">
                        <option value="food_title"
                                <c:if test="${search=='food_title'}">selected="selected" </c:if>>제목
                        </option>
                        <option value="food_content"
                                <c:if test="${search=='food_content'}">selected="selected" </c:if>>내용
                        </option>
                        <option value="username"
                                <c:if test="${search=='username'}">selected="selected" </c:if>>작성자
                        </option>
                        <option value="subcon"
                                <c:if test="${search=='subcon'}">selected="selected" </c:if>>제목+내용
                        </option>
                    </select>
                    <input type="text" name="keyword">
                    <input class="btn btn-dark" type="submit" value="확인">
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/resources/include/footerbar.jsp" %>
</div>
</body>
</html>
