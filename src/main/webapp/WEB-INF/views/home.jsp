<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<html>
<head>
<title>Home</title>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

</script>
</head>

	<c:import url="header.jsp" />
<body>
	<hr style="clear:both;">
	<%-- <article>
	<section>
	
	<div style="float:left; border:1px solid navy; padding: 5px; margin-left: 150px;">
		<h4>새로운 공지글</h4>
		<table id="new_notice" border="1" cellspacing="0">
			<tr><th>번호</th><th>제목</th><th>날짜</th></tr>
		</table>
	</div>
	인기 게시글 3개 자동 조회 출력 : ajax 사용
	<div style="float:left; border:1px solid olive; padding: 5px; margin-left: 5px;">
		<h4>인기 게시글</h4>
		<table id="top_board" border="1" cellspacing="0">
			<tr><th>번호</th><th>제목</th><th>조회수</th></tr>
		</table>
	</div>
	</section>
	</article>
<br style="clear:both;">	
	 --%>
	
</body>
</html>
