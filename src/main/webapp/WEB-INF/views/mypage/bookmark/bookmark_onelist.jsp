<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LAWVING_BOOKMARK</title>
<style type="text/css">
article {
	height: 0 auto;
	background-color: #85929E;
	border-radius: 20px;
}
.table{
	background-color: #27496b; width: 600px; height: auto; margin: 0 auto; border-radius: 20px; 
	border-collapse:collapse;
	 color: white; }
	
}
td{
text-align: center;
}
</style>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp" />
<article>
<center><h1 style="font-size: 40px;">BOOK MARK</h1></center>
<center><h1 style="font-size: 20px;">상세보기</h1></center>
<table class='table'>
<tbody>
<c:choose>
<c:when test="${!empty bookmark}">
		<tr><td colspan='2'><h2>카테고리</td></tr>
		<tr><td>${bookmark.bookmark_category}</td></tr>
		<tr><td colspan='2'><h2>질문</td></tr>
		<tr><td>${bookmark.bookmark_question}</td></tr>
		<tr><td colspan='2'><h2>답변</td></tr>
		<tr><td>${bookmark.bookmark_answer}</td></tr>
</c:when>
<c:otherwise>
<tr><td>조회된 정보가 없습니다.</td></tr>
</c:otherwise>
</c:choose>
<tr><td><input type='button' id='home' value='목록' onclick="location.href='list_bookmark.do?members_idx=${loginMember.members_idx}'" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type='button' id='delete' value='삭제' onclick="location.href='delete_bookmark.do?bookmark_idx=${bookmark.bookmark_idx}&members_idx=${loginMember.members_idx}'" /></td></tr>
</tbody>
</table>
<br><br><br><br><br><br>
</article>
</body>
</html>