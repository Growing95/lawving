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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">
function loadNextPage(page){

var param = "page="+page;

$('#append_article').load("list.do", param, function(data){

alert(data);

});

}
</script>
<body>
	<%-- 절대경로로 처리한 경우 --%>
	<c:import url="../header.jsp" />
	<article>
		<div class="category" style="margin: auto;">
			${nvo.notice_idx }번 공지 상세보기
			<h2><a href="lnlist.do">공지사항</a></h2>
		</div>
	<br>
	<table align="center" width="850" border="1" cellspacing="0"
		cellpadding="5">
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
					<c:url var="dnd" value="/download_notice.do">
						<c:param name="ofile" value="${ nvo.notice_file_name }" />
						<c:param name="rfile" value="${ nvo.notice_refile_name }" />
					</c:url>
					<a href="${ dnd }">${nvo.notice_file_name }</a>
				</c:if> <c:if test="${ empty nvo.notice_file_name }">&nbsp;</c:if></td>
		</tr>
		<tr>
			<th>내 용</th>
			<td>${ nvo.notice_content }</td>
		</tr>
		<c:if test="${loginMember.members_lev=='2'}">
			<div style="align: center; padding-left: 400px;">
				<c:url var="update" value="notice_update.do" />
				<button onclick="javascript:location.href='${ update }';">수정</button>
				<button
					onclick="location.href='notice_delete.do?notice_idx=${nvo.notice_idx}'">삭제</button>
			</div>
		</c:if>
		<div>
			<tr>
				<th colspan="2">
				<button onclick="location.href='before_notice.do?notice_idx=${nvo.notice_idx}&cPage=${cPage}'">이전글</button>
				<button onclick="location.href='nlist.do?cPage=${cPage }'">목록</button>
				<button onclick="location.href='after_notice.do?notice_idx=${nvo.notice_idx}&cPage=${cPage}'">다음글</button>
			</th>
			</tr>
		</div>
	</table>
</article>
</body>
</html>