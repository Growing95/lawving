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
		<c:url var="unf" value="/download_library.do">
			<c:param name="download_library" value="${ lvo.library_file_name }" />
		</c:url>
		<a href="${ unf }">${lvo.library_file_name }</a>
	</c:if>
	<c:if test="${ empty lvo.library_file_name }">&nbsp;</c:if>
</td></tr>
<tr><th>내 용</th><td>${ lvo.library_content }</td></tr>
<tr><th colspan="2"><button onclick="javascript:history.go(-1);">목록</button></th></tr>
</table>
</body>
</html>