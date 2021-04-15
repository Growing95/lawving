<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LAWVING_BOOKMARK</title>
<style type="text/css">
.table{
	background-color: #27496b; width: 600px; height: auto; margin: 0 auto; border-radius: 20px; 
	border-collapse:collapse;
	 color: white; }
	
}
td{
text-align: center;
}
a { text-decoration: none; color: black; }
bc{background: darkgray;
    border-radius: 20px;}
    #answer{font-weight: bold;}
    body { height: 100vh; margin: 0px; }
article { height: 100%;}
section{background-color: #ffe6002e;
border-radius: 100px;
    width: 80%;
    margin: auto;
    min-width: 800px;
    min-height: 800px;
}
.category { margin: 10px auto; width: 800px; text-align: left;}
</style>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp" />
<article>
<section>
		<div class="category" style="margin: auto;">
			BOOKMARK
			<h2><a href="list_qna.do">북마크 상세보기</a></h2>
			<br>
			<img alt="" src="resources/images/bookmark.png" style="padding-left: 6px;">
		</div>
<table class='table'>
<tbody>
<c:choose>
<c:when test="${!empty bookmark}">
		<tr><td colspan='2'><h2 class="bc">카테고리</td></tr>
		<tr><td>${bookmark.bookmark_category}</td></tr>
		<tr><td colspan='2'><h2 class="bc">질문</td></tr>
		<tr><td>${bookmark.bookmark_question}</td></tr>
		<tr><td colspan='2' ><h2 class="bc">답변</td></tr>
		<tr><td id="answer">${bookmark.bookmark_answer}</td></tr>
</c:when>
<c:otherwise>
<tr><td>조회된 정보가 없습니다.</td></tr>
</c:otherwise>
</c:choose>
<tr><td><input type='button' id='home' value='목록' onclick="location.href='list_bookmark.do?members_idx=${loginMember.members_idx}&cPage=${cPage}'" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type='button' id='delete' value='삭제' onclick="location.href='delete_bookmark.do?bookmark_idx=${bookmark.bookmark_idx}&members_idx=${loginMember.members_idx}'" /></td></tr>
</tbody>
</table>
<br><br><br><br><br><br><br>
</section>
</article>
</body>
</html>