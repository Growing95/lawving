<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lawving QnA List</title>
<style type="text/css">

</style>
<script type="text/javascript">

</script>
</head>
<body>
	<c:import url="../header.jsp"/>
	<article>
		<div style="text-align: left;">
			Question and answer
			<h2 style="margin: 0px;">Q&#38;A</h2>
		</div>
		<table style="margin: auto; border: 1px solid black; width: 800px; border-collapse: collapse; text-align: center;">
			<tr>
				<th>No.</th>
				<th>카테고리</th>
				<th>제목</th>
				<th>상태</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${requestScope.qnaList}" var="list" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${list.qna_category}</td>
					<td>
						<c:url var="onelist" value="onelist_qna.do">
							<c:param name="page" value="${page}"></c:param>
							<c:param name="qna_idx" value="${list.qna_idx}"></c:param>
						</c:url>
						<a href="${onelist}">
							${list.qna_title}
						</a>
					</td>
					<td>${list.qna_status}</td>
					<td>${list.qna_writer}</td>
					<td>${list.qna_reg.substring(0,10)}</td>
				</tr>
			</c:forEach>
		</table>
	</article>
</body>
</html>