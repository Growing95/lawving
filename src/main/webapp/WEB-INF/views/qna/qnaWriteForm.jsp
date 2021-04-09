<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../header.jsp" />
	<article>
		<div style="margin: 10px auto; width: 800px; text-align: left;">
			Question and answer
			<h2 style="margin: 0px;">
				<a href="list_qna.do" style="text-decoration: none; color: black;">Q&#38;A</a>
			</h2>
		</div>
		<form action="insert_qna.do" method="post">
			<table align="center" width="800" border="1" cellspacing="0"
				cellpadding="5">
				<tr>
					<th>제 목</th>
					<td><input type="text" name="ntitle"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="nwriter" readonly
						value="${ sessionScope.loginUser.id }"></td>
				</tr>
				<tr>
					<th>파일선택</th>
					<td><input type="file" name="upfile"></td>
				</tr>
				<tr>
					<th>내 용</th>
					<td><textarea name="ncontent" rows="5" cols="50"></textarea></td>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="등록하기">
						&nbsp; <input type="reset" value="작성취소"> &nbsp;
						<button onclick="javascript:history.go(-1); return false;">목록</button></th>
				</tr>
			</table>
		</form>
	</article>
</body>
</html>