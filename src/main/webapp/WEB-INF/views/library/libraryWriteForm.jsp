<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../header.jsp" />
	<article>
<hr>
<h1 align="center">새 자료글 등록 페이지</h1>
<%-- form 에서 입력값들과 파일을 같이 전송하려면, 반드시 enctype="multipart/form-data"
  속성 추가해야 함 --%>
<form action="insertlibrary.do" method="post" enctype="multipart/form-data">
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
<tr>
<td>카테고리</td>
<td><input type="text" name="library_category" required="required"></td>
</tr>
<tr>
<td>제 목</td>
<td><input type="text" name="library_title" required="required"></td>
</tr>
<tr>
<td>글 출처</td>
<td><input type="text" name="library_link"></td>
</tr>
<tr>
<td>작성자</td>
<td><input type="text" name="library_writer" readonly value="${loginMember.members_name}"></td>
</tr>
<tr>
<td>파일선택</td>
<td><input type="file" name="file"></td>
</tr>
<tr>
<td>내 용</td>
<td><textarea name="library_content" rows="5" cols="50" required="required"></textarea></td>
</tr>
<tr><td colspan="2">
<input type="hidden" value="${members_idx }">
<input type="submit" value="등록하기"> &nbsp; 
<input type="reset" value="작성취소"> &nbsp; 
<button onclick="javascript:history.go(-1); return false;">목록</button></td></tr>
</table>
</form>  
</article>
</body>
</html>