<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LAWVING_login</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">
#loginbtn{    width: 150px;
    height: 40px;
    padding: 0;
    display: inline;
    border-radius: 4px;
    background: #212529;
    color: #fff;
    margin-top: 20px;
    border: solid 2px #212529;
    transition: all 0.5s ease-in-out 0s;}
    a{text-decoration: none;}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/header.jsp" />
	<c:if test="${!empty msg }">
	<script type="text/javascript">
	alert(${msg});
	</script>
	</c:if>
	<c:set var="contextPath" value="${ pageContext.servletContext.contextPath }" scope="application"/>
<!-- 
	<h1 align="center">LOGIN</h1>
	<br>
	<br>
	<br>
	<br> -->
	<!-- <div class="outer" align="center">
		<form action="login.do" method="post" id="joinForm">
			<table width="500" cellspacing="5">
				<tr>
					<td align="center"><input type="text" name="members_id" placeholder="ID" required></input></td>
				</tr>
				<tr>
					<td align="center"><input type="password" name="members_pw" placeholder="PASSWORD" required></td>
				</tr>		
				<tr>
					<td align="center">
						<input type="submit" value="login"><a href="go_findpw.do">비밀번호찾기</a>
					</td>
				</tr>
			</table>
		</form> -->
			<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4 w3-auto" style="width: 382px;height: 456.3px;">
		<form action="login.do" method="post" id="joinForm">
			<div class="w3-center w3-large w3-margin-top">
				<h3>로그인</h3>
			</div>
			<div>
				<p>
					<label>아이디</label>
					<input class="w3-input" type="text" id="members_id" name="members_id" placeholder="아이디를 입력하세요" required />
				</p>
				<p>
					<label>패스워드</label>
					<input class="w3-input" type="password" id="members_pw" name="members_pw" placeholder="패스워드를 입력하세요" required>
				</p>
				<p><a href="#" >ID찾기</a>&nbsp;&nbsp;<a href="go_findpw.do" >비밀번호찾기</a></p>
				<p class="w3-center">
					<button type="submit" id="loginbtn" class="w3-button w3-hover-white w3-ripple w3-margin-top w3-round mybtn">Login</button>
				</p>
				<p class="w3-center"><a href="https://kauth.kakao.com/oauth/authorize?client_id=e2179d87d4b2efece0708e2c85212139&redirect_uri=http://localhost:8090/kakao_login.do&response_type=code">
				<img alt="" src="resources/images/kakao_login.png">
				</a></p>
			</div>
			</form>
		</div>
	</div>
</body>
</html>