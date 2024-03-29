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
<style type="text/css">
body { height: 100vh; margin: 0px; }
article { height: 100%; background-color: White; }
</style>
</head>
<body>
	<%-- 절대경로로 처리한 경우 --%>
	<article>
	<c:import url="../header.jsp" />
		<div class="category">
			${lvo.library_idx }번 자료 상세보기
		<h2><a href="llist.do">자료실</a></h2>
	</div>
	<div class="box">
		<table align="center" width="850" border="1" cellspacing="0"
		cellpadding="5">
			<tr>
				<th width="70">제 목</th>
				<td>${ lvo.library_title }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ lvo.library_writer }</td>
			</tr>
			<tr>
				<th>날 짜</th>
				<td><fmt:formatDate value="${lvo.library_reg }"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><c:if test="${ !empty lvo.library_file_name }">
						<c:url var="dld" value="/download_library.do">
							<c:param name="ofile" value="${ lvo.library_file_name }" />
							<c:param name="rfile" value="${ lvo.library_refile_name }" />
						</c:url>
						<a href="${ dld }">${lvo.library_file_name }</a>
					</c:if> <c:if test="${ empty lvo.library_file_name }">&nbsp;</c:if></td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>${ lvo.library_content }</td>
			</tr>
			<c:if test="${loginMember.members_lev=='2'}">
				<div style="align: center; padding-left: 400px;">
					<c:url var="update" value="/library_update.do" />
					<button onclick="javascript:location.href='${ update }';">수정</button>
					<button
						onclick="location.href='library_delete.do?library_idx=${lvo.library_idx}'">삭제</button>
				</div>
			</c:if>
		</table>
		</div>
		<div>
			<tr>
				<th>
					<button onclick="location.href='before_library.do?library_idx=${lvo.library_idx}&cPage=${cPage}'">이전글</button>
					<button onclick="location.href='llist.do?cPage=${cPage }'">목록</button>
					<button onclick="location.href='after_library.do?library_idx=${lvo.library_idx}&cPage=${cPage}'">다음글</button>
				</th>
			</tr>
		</div>
	</article>
</body>
</html>