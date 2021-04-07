<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:import url="/WEB-INF/views/header.jsp" />

	<h1 align="center">로그인</h1>
	<br>
	<br>
	<br>
	<br>
	
	<div class="outer" align="center">
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
						<input type="submit" value="login">
					</td>
				</tr>
				<br>
			</table>
		</form>
		<br>
		<br>
				<div id="find">
				<a href="go_find.do">ID/PW 찾기</a><br>
				</div>	
	</div>
	

	
</body>
</html>