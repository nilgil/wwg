<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Page Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/6e837646d1.js" crossorigin="anonymous"></script>
    <link rel='stylesheet' type='text/css' media='screen' href='mainPage.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='board.css'>
    <script defer src="main.js"></script>
   
</head>
<body>

<!--     <nav class="navbar">

        <div class="navbar_logo"><i class="fas fa-cannabis"></i> 가을</div>
        
        <ul class="navbar_menu">
            <li><a href="mainPage.html">Home</a></li>
            
            <li><a href="#">menu1</a>
             <ul>
                <li><a href="#">사과</a></li>
                <li><a href="#">바나나</a></li>
                <li><a href="#">포도</a></li>
                <li><a href="#">배</a></li>
             </ul>
            </li>
        
            <li><a href="#">menu2</a>
                <ul>
                    <li><a href="#">오스트레일리아</a></li>
                    <li><a href="#">바나나</a></li>
                    <li><a href="#">포도</a></li>
                    <li><a href="#">배</a></li>
                 </ul>
            </li>
        
            <li><a href="#">menu3</a>
                <ul>
                    <li><a href="#">사과</a></li>
                    <li><a href="#">바나나</a></li>
                    <li><a href="#">포도</a></li>
                    <li><a href="#">배</a></li>
                 </ul>
            </li>
        
            <li><a href="#">menu4</a>
                <ul>
                    <li><a href="#">사과</a></li>
                    <li><a href="#">바나나</a></li>
                    <li><a href="#">포도</a></li>
                    <li><a href="#">배</a></li>
                 </ul>
            </li>
        </ul>
        
        <ul class="navbar_icons">
            <li><i class="fas fa-search"></i></li>
            <li><i class="fas fa-sign-in-alt"></i></li>
        </ul>
        
        <a href="#" class="navbar_toogleBtn">
            <i class="fas fa-bars"></i>
        </a>
        
    </nav>

    &nbsp;

    <div class="container">

        <div class="aside_menu">
            <ul>
                <p3>menu1</p3><br><br>
                <li><a href="#">사과</a></li>
                <li><a href="#">바나나</a></li>
                <li><a href="#">포도</a></li>
                <li><a href="#">배</a></li>
            </ul>
        </div> -->
       
     <div class="table">
        <table class="table table-hover">
        <h2>게시판</h2>
<p>The .table-hover class enables a hover state (grey background on mouse over) on table rows:</p>            
          <thead>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>날짜</th>
              <th>조회수</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td>
              <td>제목이 길면 어떻게 나오는지 함 보자 </td>
              <td>네글자이름</td>
              <td>2021-11-07</td>
              <td>10</td>
            </tr>
            <tr>
              <td>2</td>
              <td>Moe 가나다라마바사아자차카타파하abcdefghijklmnopqrstuvwxyz </td>
              <td>네글자이름</td>
              <td>2021-11-08</td>
              <td>1000</td>
            </tr>
            <tr>
              <td>3</td>
              <td>Dooley</td>
              <td>네글자이름</td>
              <td>2021-11-08</td>
              <td>1000</td>
            </tr>
            <tr>
                <td>5</td>
                <td>Dooley</td>
                <td>네글자이름</td>
                <td>2021-11-08</td>
                <td>1000</td>
              </tr>
              <tr>
                <td>6</td>
                <td>Dooley</td>
                <td>네글자이름</td>
                <td>2021-11-08</td>
                <td>1000</td>
              </tr>
              <tr>
                <td>7</td>
                <td>Dooley</td>
                <td>네글자이름</td>
                <td>2021-11-08</td>
                <td>1000</td>
              </tr>
              <tr>
                <td>8</td>
                <td>Dooley</td>
                <td>네글자이름</td>
                <td>2021-11-08</td>
                <td>1000</td>
              </tr>
              <tr>
                <td>9</td>
                <td>Dooley</td>
                <td>네글자이름</td>
                <td>2021-11-08</td>
                <td>1000</td>
              </tr>
              <tr>
                <td>10</td>
                <td>Dooley</td>
                <td>네글자이름</td>
                <td>2021-11-08</td>
                <td>1000</td>
              </tr>
          </tbody>
        </table>
      </div>
        <div class="search_bar">
        <select class="search">
            <option value="subject">제목</option>
            <option value="content">내용</option>
            <option value="writer">작성자</option>
            <option value="subcon">제목+내용</option>
        </select> 
        <input type="text" name="keyword"> 
        <input type="submit" value="확인">
        </div>

        <div class="footer">footer</div>
        <div></div>
        <div></div>
  
    </div>



</body>
</html>