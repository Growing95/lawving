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
.n_title{
	border: 1px solid black;
	width: 800px;
	margin: auto;
	padding: 5px;
}
.n_file{
    margin: 10px 0 10px 600px;
}
.n_content{
	margin : auto;
	border: 1px solid black;
	height: 500px;
	width: 800px;
	text-align: left;
	padding: 5px;
}
.btn {
	margin-top: 10px;
}
</style>
<link rel="stylesheet" type="text/css" href="resources/css/list.css">
</head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">
</script>
<body>
	<article>
	<c:import url="../header.jsp" />
			<div class="category">
				NOTICE
				<h2>
					<a href="list_notice.do">공지사항</a>
				</h2>
			</div>
		<br>
		<c:if test="${loginMember.members_lev=='2'}">
			<div style=" margin: 0 0 5px 720px; ">
				<c:url var="update" value="notice_update.do" />
				<button onclick="javascript:location.href='${ update }';">수정</button>
				<button onclick="location.href='notice_delete.do?notice_idx=${nvo.notice_idx}'">삭제</button>
			</div>
			
		</c:if>
		<div class="n_title">
			<!-- 제목 -->
			<h1>${ nvo.notice_title }</h1>
		
		
			<p style="margin: 0 0 0 450px; ">작성자 :
			${ nvo.notice_writer }
		
		
			 / 작성날짜 : 
			<fmt:formatDate value="${nvo.notice_reg }"
					pattern="yyyy-MM-dd" /></p>
		</div>
		<div class="n_file">첨부파일
			<c:if test="${ !empty nvo.notice_file_name }">
					<%-- 첨부파일이 있다면 다운로드 설정함 --%>
					<c:url var="dnd" value="/download_notice.do">
						<c:param name="ofile" value="${ nvo.notice_file_name }" />
						<c:param name="rfile" value="${ nvo.notice_refile_name }" />
					</c:url>
					<a href="${ dnd }">${nvo.notice_file_name }</a>
				</c:if> <c:if test="${ empty nvo.notice_file_name }">&nbsp;</c:if></div>
		
		<div>
			<!-- 내용 -->
			<div class="n_content">${ nvo.notice_content }</div>
		</div>
		
		<div>
			<c:url var="goback" value="/nlist.do" />
			<div class="btn">
				<button  onclick="location.href='before_notice.do?notice_idx=${nvo.notice_idx}&cPage=${cPage}'">이전글</button>
				<button  onclick="javascript:location.href='${goback }';">목록</button>
				<button  onclick="location.href='after_notice.do?notice_idx=${nvo.notice_idx}&cPage=${cPage}'">다음글</button>
			</div>
		</div>
</article>
</body>
</html>