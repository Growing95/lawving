<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>LAWVING</title>

<style type="text/css">
* {
  margin: 0;
  padding: 0;
}

body {
  position: relative;
  background: url('http://thecodeplayer.com/u/m/b1.png') no-repeat center center fixed;
  background-size: cover;
  width:100%;
  height:100vh;
  color: #fff;
  font-family: 'Montserrat';
}

.bars {
  /*   background-color:pink; */
  position: absolute;
  width: 27px;
  height: 27px;
  top: 30px;
  right: 30px;
  cursor: pointer;
  z-index: 101;
  padding-top:9px;
}

.bar {
  width: 100%;
  height: 4px;
  background-color: #fff;
  position: absolute;
}

span::before,
span::after {
  content: "";
  display: block;
  background-color: #fff;
  width: 100%;
  height: 4px;
  position: absolute;
}

.bar::before {
  transform: translateY(-9px);
}

.bar::after {
  transform: translateY(9px);
}

.bars.active .bar {
  background-color: transparent;
}

.bars.active span::before {
  animation: top-bar 1s;
  animation-fill-mode: forwards;
}

.bars.active span::after {
  animation: bottom-bar 1s;
  animation-fill-mode: forwards;
}


/* Navbar Links CSS */

#nav {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  transition: all 1s;
  z-index: -1;
  overflow: hidden;
  opacity: 0;
}

#nav a {
  color: #fff;
  text-decoration: none;
  line-height: 70vw;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  text-indent: 50vw;
  border-radius: 50%;
  transition: all .5s;
}

#nav a:hover {
  background: #357FFD;
}

ul {
  list-style: none;
}

.visible {
  z-index: 100 !important;
  opacity: 1 !important;
}

.shape-circle {
  border-radius: 50%;
  width: 20vw;
  height: 20vw;
  top: -10vw;
  right: -10vw;
  position: absolute;
  transition: all 1s ease-in-out;
  background: rgb(39 73 107 / 53%);
  box-shadow: 0 0px 0px rgba(4, 26, 62, 0.5);
}

nav.visible li:first-child {
  width: 200vw;
  height: 200vw;
  top: -100vw;
  right: -100vw;
  z-index: 5;
  transition: all .5s ease-in-out;
  box-shadow: 0 0px 80px rgba(4, 26, 62, 0.5);
}

nav.visible li:nth-child(2) {
  width: 150vw;
  height: 150vw;
  top: -75vw;
  right: -75vw;
  z-index: 6;
  transition: all .6s ease-in-out;
  box-shadow: 0 0px 80px rgba(4, 26, 62, 0.5);
}

nav.visible li:nth-child(3){
  width: 100vw;
  height: 100vw;
  top: -50vw;
  right: -50vw;
  z-index: 7;
  transition: all .7s ease-in-out;
  box-shadow: 0 0px 80px rgba(4, 26, 62, 0.5);
}

nav.visible li:last-child{
  width: 50vw;
  height: 50vw;
  top: -25vw;
  right: -25vw;
  z-index: 8;
  transition: all .8s ease-in-out;
  box-shadow: 0 0px 80px rgba(4, 26, 62, 0.5);
}

nav.visible li:first-child a {
  line-height: 265vw !important;
    text-indent: 15vw !important;

}

nav.visible li:nth-child(2) a {
  line-height: 200vw !important;
  text-indent:17vw !important;
}

nav.visible li:nth-child(3) a {
  line-height: 137vw !important;
    text-indent: 17vw !important;
  
}

nav.visible li:last-child a {
  line-height: 70vw !important;
  text-indent:12vw !important;
}


/* Main Body CSS */

.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
}

h1 {
  font-size: 60px;
  text-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
  text-transform: uppercase;
  font-size: 120px;
  letter-spacing:5px;
  padding-top:40px;
}

article p {
  padding-bottom: 15px;
  text-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
}

article a {
  color: #fff;
  text-decoration: none;
  opacity: .2;
  font-size: 12px;
}

article a:hover {
  opacity: .8;
}

@keyframes top-bar {
  50% {
    transform: translateY(0);
  }
  100% {
    transform: rotate(45deg) translateY(0);
  }
}

@keyframes bottom-bar {
  50% {
    transform: translateY(0);
  }
  100% {
    transform: rotate(-45deg) translateY(0);
  }
}


@media screen and (max-width:800px) {

  h1 {
    padding-top:80px;
    font-size: 60px;
  }
}
/* 검색바 */
#search{
    width: 600px;
    text-align: center;
    border-radius: 20px;
        height: 40px;
}
.tm{padding: 20px; font-size: 20px;}
#log{padding-left: 70%; font-weight: bold;}
#log a{color: white;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
$("#res").empty();
if(${kakaoMember=='kakao'}){
$.ajax({
	url:"kakao_member.do",
	method:"post",
	dataType:"json",
	success: function(data) {
			var name = "";
			var email = "";
			var profile_image_url ="";
		$.each(data, function() {
			var profile=this["profile"];
			$.each(profile, function() {
				 name = profile["nickname"];
			});
		});
		alert("카카오로그인 연계를통한 웹사이트이용은 개발중에있습니다.\n로그아웃후 별도로 회원가입을 진행해주시기 바랍니다.")
		$("#res").append(name+"님");
	},
	error: function() {
		alert("읽기실패");
	}
});
}
});  
</script>
<script type="text/javascript">
function golaw() {
	location.href="update_lawdata.do";
}
</script>
</head>
<body>
			<c:choose>
				<c:when test="${loginMember.members_lev=='2'}">
					<div id="log">
						<p style="font-weight: bold;">*관리자*</p>&nbsp;${loginMember.members_name }님|<a
							href="logout.do">로그아웃</a><br> <a href="memberslist.do" style="font-size: none; color: orange;">회원정보관리</a><br><button onclick="golaw()">법률api자료갱신</button>
					</div>
				</c:when>
				<c:when test="${loginMember.members_lev=='1' }">
					<div id="log">
						${loginMember.members_name }님|<a href="logout.do">로그아웃</a><p style="color: red;">누적신고수${limit}회</p><a href="list_mypage.do?cPage=1&members_idx=${loginMember.members_idx }">MY페이지</a>|<a href="list_bookmark.do?cPage=1&members_idx=${loginMember.members_idx }">MY북마크</a>
					</div>
				</c:when>
				<c:when test="${sessionScope.kakaoMember=='kakao'}">
							<div id="log">
						카카오회원 <div id="res"></div>|&nbsp; <p style="color: red;">누적신고수
							:${limit}
						</p>회<br> <a href="logout.do">로그아웃</a><br> <a
							href="list_mypage.do?cPage=1&members_idx=${loginMember.members_idx }">MY페이지</a>|<a href="list_bookmark.do?cPage=1&members_idx=${loginMember.members_idx }&cPage=1">MY북마크</a>
				</div>
				</c:when>
				<c:otherwise>
					<div id="log">
						<a href="go_login.do">Login</a> | <a href="go_signup.do">Sign Up</a><br>
				</div>
				</c:otherwise>
			</c:choose>
<div class="bars" id="nav-action">
  <span class="bar"> </span>
</div>
<p style="padding-left: 96%; text-align: center; top: 60; position: fixed;">법률항목 선택</p>
<!--Navbar Links-->
<nav id="nav">
  <ul>
    <li class="shape-circle circle-one"><a href="list_lawdata.do?law=부동산">부동산</a></li>
    <li class="shape-circle circle-two"><a href="list_lawdata.do?law=상속">상속</a></li>
    <li class="shape-circle circle-three"><a href="list_lawdata.do?law=임금">임금</a></li>
    <li class="shape-circle circle-five"><a href="list_lawdata.do?law=해고">해고</a></li>
  </ul>
</nav>

<!--Main Body Content-->
<article class="container">
  <h1><img alt="" src="resources/images/LAWVINGhome1.png"></h1>
  <form action="search_lawdata.do" method="post">
<input type="text" id="search" name="search" placeholder="검색할법률을 입력하세요" width="400px;"><input type="submit" value="검색" style="    height: 40px;
    border-radius: 20px;
    width: 80px;">
</form>
<br><br>
<div id="menu">
<c:url var="nlist" value="nlist.do" />
	<ul id="toolmenu">
		<li><a href="${ nlist }" class="tm">공지사항</a>
  <a href="llist.do" class="tm">자료실</a>
<a href="list_qna.do" class="tm">Q&A</a></li>
	</ul>
	</div>
</article>
<script type="text/javascript">
//Setting up the Variables
var bars = document.getElementById("nav-action");
var nav = document.getElementById("nav");


//setting up the listener
bars.addEventListener("click", barClicked, false);


//setting up the clicked Effect
function barClicked() {
  bars.classList.toggle('active');
  nav.classList.toggle('visible');
 }
</script>
</body>
</html>
