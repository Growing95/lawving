<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Abril+Fatface&family=Lobster&display=swap')
	;

#loginUI {
	float: right;
	margin-top: -150px;
	z-index: 5000;
	position: relative;
}

#toolmenu {
	list-style-type: none;
	padding: 0;
	position: fixed;
	overflow: auto;
	right: 15.2%;
	bottom: 50%;
	z-index: 10000;
}

#toolmenu li {
	text-align: center;
	display: list-item;
	color: #000;
	padding: 8px 15px 8px 15px;
	font-weight: bold;
	background-color: #2C3E50;
	border-collapse: collapse;
	border-top-left-radius: 20px;
	border: 1px solid #4c5d6f;
}
center{width: 70%; margin: auto;}


article {
	width: 70%;
	margin: 0 auto;
	text-align: center;
}

#toolmenu li a {
	text-decoration: none;
	color: white;
	font-weight: bold;
	width: 96px;
}

#toolmenu li:hover {
	background-color: #808B96;
	border-color: #808B96;
}

#log a {
	text-decoration: none;
	color: black;
	font-weight: bold;
}
#iframe{
position:fixed;
width: 420px; 
height: 300px;
bottom:50px;
right:100px;
background-color: white;
z-index: 1000000;
}
#chatbtn2{
	position:fixed;
	overflow: auto;
	bottom:353px;
	right:100px;
	padding: 8px 15px 8px 15px;
	font-weight: bold;
	background-color: #2C3E50;
	border-collapse: collapse;
	border-top-right-radius: 20px;
	border: 1px solid #4c5d6f;
	color:white;
}
</style>
<!--배너관련 스크립트  -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
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
<script>
$(document).ready(function(){
  $("#chatbtn1").click(function(){
	  if(${empty sessionScope.loginMember}){ 
		  alert("챗봇기능은 로그인후 이용가능합니다.");
	  }else{
		  
    $("#iframe").toggle();
	  }
  });
});
</script>
<script type="text/javascript">
function golaw() {
	location.href="update_lawdata.do";
}
</script>
</head>
<body>
	<header>
	<div id="head">
		<center>
			<a href="home.do"><img alt="logo"
				src="resources/images/Lawving-color1.png"></a>
		</center>
		</div>
		<div id="loginUI">
			<c:choose>
				<c:when test="${loginMember.members_lev=='2'}">
					<div id="log">
						<span style="font-weight: bold;">관리자</span>&nbsp;${loginMember.members_name }님|<a
							href="logout.do">로그아웃</a><br> <a href="memberslist.do"
							style="font-size: none; color: black;">회원정보관리</a><br><button onclick="golaw()">법률api자료갱신</button>
					</div>
				</c:when>
				<c:when test="${loginMember.members_lev=='1' }">
					<div id="log">
						${loginMember.members_name }님|&nbsp; <span style="color: red;">누적신고수
							: <%-- ${limit.limit_count} --%>0
						</span>회<br> <a href="logout.do">로그아웃</a><br> <a
							href="list_mypage.do?members_idx=${loginMember.members_idx }">MY페이지</a>|<a href="list_bookmark.do?members_idx=${loginMember.members_idx }">MY북마크</a>
					</div>
				</c:when>
				<c:when test="${kakaoMember=='kakao' }">
					<div id="log">
						카카오회원 <div id="res"></div>|&nbsp; <span style="color: red;">누적신고수
							: <%-- ${limit.limit_count} --%>0
						</span>회<br> <a href="logout.do">로그아웃</a><br> <a
							href="list_mypage.do?members_idx=${loginMember.members_idx }">MY페이지</a>|<a href="list_bookmark.do?members_idx=${loginMember.members_idx }">MY북마크</a>
					</div>
				</c:when>
				<c:otherwise>
					<div id="log">
						<a href="go_login.do">로그인</a>|<a href="go_signup.do">회원가입</a><br>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
	<c:url var="nlist" value="nlist.do" />
	<ul id="toolmenu">
		<li><a href="${ nlist }">공지사항</a></li>
  <li><a href="llist.do">자료실</a></li>
  <li><a href="list_qna.do">Q&A</a></li>
  <li><a id="chatbtn1" style="cursor: pointer;">챗봇열기</a></li>
	</ul>
	<div>
	<!-- <iframe id="iframe" scrolling="yes" src="http://@203.236.220.89:8090/chat.do"></iframe> -->
	<c:choose>
		<c:when test="${loginMember.members_lev=='1' }">

			<iframe id="iframe"  scrolling="yes" src="http://@192.168.71.1:8081/chat.do" style="display: none;"></iframe>
			<!-- 자신의 ip로바꾼후 톰캣서버 모듈 / 로 수정해야 정상 작동 -->

		</c:when>
	</c:choose>
	</div>
</body>
</html>