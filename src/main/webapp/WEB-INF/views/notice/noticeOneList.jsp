<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#btn1 {
	height: 70px;
	width: 100px;
	margin-right: 8px;
	margin-top: 8px;
}
#btn2 {
	width: 80px; height: 30px;
	margin-bottom: 10px;
	
}
</style>
</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">

</script>

<body>
	<%-- 절대경로로 처리한 경우 --%>
	<c:import url="../header.jsp" />
	<HR>
	<h2 align="center">${nvo.notice_idx }번공지사항 상세보기</h2>
	<br>
	<table frame=void align="center" width="850" border="1" cellspacing="0"
 		cellpadding="5">
		<tr>
			<!-- 제목 -->
			<td colspan="5" style="padding-left:10px;"><h1>${ nvo.notice_title }</h1>
		
		
			<p style="margin: 0 0 0 540px; ">작성자 :
			${ nvo.notice_writer }
		
		
			 / 작성날짜 : 
			<fmt:formatDate value="${nvo.notice_reg }"
					pattern="yyyy-MM-dd" /></p></td>
		</tr>
		<tr style="border:hidden;">
			<th colspan="2" style="border:hidden; padding: 10px 0 10px 640px; ">첨부파일
			<c:if test="${ !empty nvo.notice_file_name }">
					<%-- 첨부파일이 있다면 다운로드 설정함 --%>
					<c:url var="dnd" value="/download_notice.do">
						<c:param name="ofile" value="${ nvo.notice_file_name }" />
						<c:param name="rfile" value="${ nvo.notice_refile_name }" />
					</c:url>
					<a href="${ dnd }">${nvo.notice_file_name }</a>
				</c:if> <c:if test="${ empty nvo.notice_file_name }">&nbsp;</c:if></th>
		</tr>
		
		<tr>
			<!-- 내용 -->
			<td style="text-align: left; height: 500px; display: block;">${ nvo.notice_content }</td>
		</tr>
		<c:if test="${loginMember.members_lev=='2'}">
			<div style="text-align: center; margin-left: 683px;">
				<c:url var="update" value="notice_update.do" />
				<button id="btn2"  onclick="javascript:location.href='${ update }';">수정</button>
				<button id="btn2"  onclick="location.href='notice_delete.do?notice_idx=${nvo.notice_idx}'">삭제</button>
			</div>
		</c:if>
		<div>
			<c:url var="goback" value="/nlist.do" />
			<tr>
				<th colspan="2" style="border: hidden;">
				<button id="btn1" onclick="location.href='before_notice.do?notice_idx=${nvo.notice_idx}&cPage=${cPage}'">이전글</button>
				<button id="btn1" onclick="javascript:location.href='${goback }';">목록</button>
				<button id="btn1" onclick="location.href='after_notice.do?notice_idx=${nvo.notice_idx}&cPage=${cPage}'">다음글</button>
			</th>
			</tr>
		</div>
	</table>

</body>
</html>