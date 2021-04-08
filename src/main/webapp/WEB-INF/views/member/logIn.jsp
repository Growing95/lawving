<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:import url="/WEB-INF/views/header.jsp" />
	<c:if test="${!empty msg }">
	<script type="text/javascript">
	alert(${msg});
	</script>
	</c:if>

	<c:set var="contextPath" value="${ pageContext.servletContext.contextPath }" scope="application"/>

	<h1 align="center">LOGIN</h1>
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
			</table>
		</form>
		<br>
		<br>
	</div>
	

	
</body>
</html>