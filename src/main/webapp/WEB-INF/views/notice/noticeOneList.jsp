<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 절대경로로 처리한 경우 --%>
	<c:import url="../header.jsp" />
	<HR>
	<h2 align="center">${nvo.notice_idx }번공지상세보기</h2>
	<br>
	<div style="margin-left: 60%; margin-bottom: 10px;"></div>
	<table align="center" width="850" border="1" cellspacing="0"
		cellpadding="5">

		<c:choose>
			<c:when test="${loginMember.members_lev=='2'}">
				<div style="align: center; paiing-left: 400px;">
					<c:url var="update" value="/notice_update.do" />
					<c:url var="delete" value="/notice_delete.do" />
					<button onclick="javascript:location.href='${ update }';">수정</button>
					<button onclick="javascript:location.href='${ delete }';">삭제</button>
				</div>
				<tr>
					<th>제 목</th>
					<td>${ nvo.notice_title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${ nvo.notice_writer }</td>
				</tr>
				<tr>
					<th>날 짜</th>
					<td><fmt:formatDate value="${nvo.notice_reg }"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				<%-- <tr>
					<th>첨부파일</th>
					<td><c:if test="${ !empty nvo.notice_file_name }">
							첨부파일이 있다면 다운로드 설정함
							<c:url var="unf" value="/download_notice.do">
								<c:param name="download_notice"
									value="${ nvo.notice_file_name }" />
							</c:url>
							<a href="${ unf }">${nvo.notice_file_name }</a>
						</c:if> <c:if test="${ empty nvo.notice_file_name }">&nbsp;</c:if></td>
				</tr>
 --%>
				<tr>
					<th>내 용</th>
					<td>${ nvo.notice_content }</td>
				</tr>
				<tr>
					<th colspan="2">
						<button onclick="javascript:history.go(-1);">목록</button>
						<button onclick="javascript:history.go(-1);">이전글</button>
						<button onclick="javascript:history.go(-1);">다음글</button>
					</th>

				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<th>제 목</th>
					<td>${ nvo.notice_title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${ nvo.notice_writer }</td>
				</tr>
				<tr>
					<th>날 짜</th>
					<td><fmt:formatDate value="${nvo.notice_reg }"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><c:if test="${ !empty nvo.notice_file_name }">
							<%-- 첨부파일이 있다면 다운로드 설정함 --%>
							<c:url var="unf" value="/download_notice.do">
								<c:param name="download_notice"
									value="${ nvo.notice_file_name }" />
							</c:url>
							<a href="${ unf }">${nvo.notice_file_name }</a>
						</c:if> <c:if test="${ empty nvo.notice_file_name }">&nbsp;</c:if></td>
				</tr>

				<tr>
					<th>내 용</th>
					<td>${ nvo.notice_content }</td>
				</tr>
				<tr>
					<th colspan="2">
						<button onclick="javascript:history.go(-1);">목록</button>
						<button onclick="/">이전글</button>
						<button onclick="/">다음글</button>
					</th>
				</tr>
				</div>
			</c:otherwise>
		</c:choose>
	</table>
</body>
</html>