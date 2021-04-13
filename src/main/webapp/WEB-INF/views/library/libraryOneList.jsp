<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
<h2 align="center">${lvo.library_idx } 번 공지 상세보기</h2>
<br>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
<tr><th>제 목</th><td>${ lvo.library_title }</td></tr>
<tr><th>작성자</th><td>${ lvo.library_writer }</td></tr>
<tr><th>날 짜</th>
<td><fmt:formatDate value="${lvo.library_reg }" pattern="yyyy-MM-dd"/></td></tr>
<tr><th>첨부파일</th>
<td>
	<c:if test="${ !empty lvo.library_file_name }"> <%-- 첨부파일이 있다면 다운로드 설정함 --%>
		<c:url var="dld" value="/download_library.do">
	<c:param name="ofile" value="${ lvo.library_file_name }" />
	<c:param name="rfile" value="${ lvo.library_refile_name }" />
		</c:url>
		<a href="${ dld }">${lvo.library_file_name }</a>
	</c:if>
	<c:if test="${ empty lvo.library_file_name }">&nbsp;</c:if>
</td></tr>
<tr><th>내 용</th><td>${ lvo.library_content }</td></tr>
<c:if
		test="${loginMember.members_lev=='2'}">
		<div style="align: center; padding-left: 400px;">
			<c:url var="update" value="/library_update.do" />
			<button onclick="javascript:location.href='${ update }';">수정</button>
			<button onclick="location.href='library_delete.do?library_idx=${lvo.library_idx}'">삭제</button>
		</div>
	</c:if>
	<div>
	<div style="text-align: center;">
				<button onclick="location.href='before_library.do?library_idx=${lvo.library_idx}&cPage=${cPage}'">이전글</button>
	<c:url var = "goback" value="/llist.do" />
<button onclick="javascript:location.href='${goback }';">목록</button>
				<button onclick="location.href='after_library.do?library_idx=${lvo.library_idx}&cPage=${cPage}'">다음글</button>
			</div>
	</div>
</table>
</body>
</html>