<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="../header.jsp" />
	<hr>
	<h1 align="center">신고하기</h1>
	<%-- form 에서 입력값들과 파일을 같이 전송하려면, 반드시 enctype="multipart/form-data"
  속성 추가해야 함 --%>
	<form action="insert_repot.do" method="post">
		<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
			<tr>
				<th>신고 사유</th>
				<td><textarea name="notice_content" rows="5" cols="50"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="hidden" value="${sessionScope.loginMember.members_idx}">
				<input type="hidden" value="${requestScope.qnaOnelist.qna_writer}">
				
					<input type="submit" value="신고하기"> &nbsp; 
					<input type="reset" value="취소하기"> &nbsp;
					<button onclick="javascript:history.go(-1); return false;">목록</button></td>
			</tr>
		</table>
	</form>
</body>
</html>