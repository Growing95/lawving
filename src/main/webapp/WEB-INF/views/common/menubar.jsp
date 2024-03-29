<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.nav {
		width:480px;
		margin: 0px auto;
		
	}
	.menu {
		display:inline-block;
		background:gray;
		text-align:center;
		line-height:50px;
		width:150px;
		height:50px;
		border-radius:20px;
	}
	.menu:hover {
		background:orangered;
		color:white;
		cursor:pointer;
	}
</style>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script> 
</head>
<body>
	<%-- <c:set var="contextPath" value=" ${ pageContext.servletContext.contextPath}"  scope="application" /> --%>
	<center><a href="home.do"><img alt="logo" src="resources/images/Lawving-color1.png"></a></center>
	<br>
	
	<div class="loginArea" align="right">
		<c:if test="${ empty sessionScope.loginUser }">
			<form action="login.do" method="post">
				<table id="loginTable" style="text-align:center">
					<td colspan="3">
							<a href="go_login.do">로그인</a>
							<a href="#"">회원가입</a>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${ !empty sessionScope.loginUser }">
			<h3 align="right">
				<c:out value="${ loginUser.name }님 환영합니다!!"/>
				<c:url var="myInfo" value="myInfo.do"/>
				<c:url var="logout" value="logout.do"/>
				<button onclick="location.href='${myInfo}'">정보수정</button>
				<button onclick="location.href='${logout}'">로그아웃</button>
			</h3>
		</c:if>
	</div>
	
	<c:url var="nlist" value="nlist.do" />		
	<c:url var="blist" value="blist.do" >
		<c:param name="page" value="1" />
	</c:url>
	<div class="menubar">
		<div class="nav">
			<div class="menu"><a href="${ nlist }">공지사항</a></div>
			<div class="menu"><a href="${ nlist }">자료실</a></div>
			<div class="menu"><a href="${ blist }">QnA게시판</a></div>
		</div>
	</div>
</body>
</html>