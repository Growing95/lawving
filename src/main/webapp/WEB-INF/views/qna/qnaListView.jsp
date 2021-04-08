<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div style="margin: 10px auto; width: 800px; text-align: left;">
			Question and answer
			<h2 style="margin: 0px;">Q&#38;A</h2>
		</div>
		<div style="margin: 10px auto; width: 800px;">
			<form action="search_qna.do" style="display: inline-block;">
				<select name="category">
					<option value="최신순" selected="selected">최신순</option>
					<option value="오래된순">오래된순</option>
					<option value="답변완료">답변완료</option>
					<option value="대기중">대기중</option>
				</select>
				<input type="text" name="keyword" placeholder="검색어를 입력해주세요."
					   style="width: 300px;">
				<input type="submit" value="검색">
			</form>
			<button onclick="location.href='go_insert_qna.do'"
					style="display: inline-block; float: right;">
				문의작성
			</button>
		</div>
		<table style="margin: auto; border: 1px solid black; 
					  width: 800px; border-collapse: collapse; text-align: center;">
			<thead>
				<tr>
					<th>No.</th>
					<th>카테고리</th>
					<th>제목</th>
					<th>상태</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty qnaList}">
						<tr><td colspan="6">게시물이 없습니다.</td></tr>
					</c:when>
					<c:otherwise>
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
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6" class="paging">
						<!-- 이전 -->
						<c:choose>
							<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
								<span style="color: gray;">이전으로</span>
							</c:when>
							<c:otherwise>
								<a href="list_qna.do?cPage=${paging.beginBlock-paging.pagePerBlock}">
										이전으로
								</a>
							</c:otherwise>
						</c:choose>
						<!-- 블록안에 들어간 페이지번호들 -->
						<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}" step="1" var="k">
							<%--현재 페이지와 현재 페이지가 아닌것으로 구분 --%>
							<c:choose>
								<c:when test="${k==paging.nowPage}">
									<span style="color: red;">${k}</span>
								</c:when>
								<c:otherwise>
									<a href="list_qna.do?cPage=${k}">
										${k}
									</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!-- 다음 -->
						<c:choose>
							<c:when test="${paging.endBlock >= paging.totalPage}">
								<span style="color: gray;">다음으로</span>
							</c:when>
							<c:otherwise>
								<a href="list_qna.do?cPage=${paging.beginBlock+paging.pagePerBlock}">
									다음으로
								</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tfoot>
		</table>
	</article>
</body>
</html>