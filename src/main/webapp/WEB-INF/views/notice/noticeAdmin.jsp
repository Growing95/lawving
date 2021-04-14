<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/list.css">
</head>
<body>
	<!-- 절대경로로 처리한 경우 -->
	<c:import url="../header.jsp" />
	<article>
		<div class="category" style="margin: auto;">
			${nvo.notice_idx }번 공지 상세보기
			<h2><a href="nlist.do">공지사항</a></h2>
		</div>
	<br>
	<table align="center" width="500" border="1" cellspacing="0"
		cellpadding="5">
		<tr>
			<th>제 목</th>
			<td>${ nvo.ntitle }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${ nvo.nwriter }</td>
		</tr>
		<tr>
			<th>날 짜</th>
			<td><fmt:formatDate value="${nvo.notice_reg }"
					pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><c:if test="${ !empty nvo.notice_file_name }"> 첨부파일이 있다면 다운로드 설정함
			<c:url var="dnd" value="/download.do">
						<c:param name="ofile" value="${ nvo.notice_file_name }" />
					</c:url>
					<a href="${ dnd }">${nvo.notice_file_name }</a>
				</c:if> <c:if test="${ empty nvo.notice_file_name }">&nbsp;</c:if></td>
		</tr>
		<tr>
			<th>내 용</th>
			<td>${ nvo.notice_content }</td>
		</tr>
		<tr>
			<th colspan="2">수정페이지로 이동 버튼 <c:url var="nup" value="/notice_update.do">
					<c:param name="nid" value="${ nvo.notice_idx }" />
				</c:url>
				<button onclick="javascript:location.href=''${ update }';">수정페이지로
					이동</button> &nbsp; 삭제하기 버튼 <c:url var="delete" value="/notice_delete.do">
					<c:param name="notice_idx" value="${ nvo.notice_idx }" />
					<c:if test="${ !empty nvo.notice_file_name }">
						<c:param name="ofile" value="${ nvo.notice_file_name }" />
					</c:if>

				</c:url>
				<button onclick="javascript:location.href='${ delete }';">글삭제</button>
				&nbsp; 이전페이지로 이동
				<button onclick="javascript:history.go(-1);">목록</button></th>
		</tr>
	</table>
	</article>
</body>
</html>