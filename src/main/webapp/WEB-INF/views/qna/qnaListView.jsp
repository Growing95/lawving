<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<h2 style="margin: 0px;">
				<a href="list_qna.do" style="text-decoration: none; color: black;">Q&#38;A</a>
			</h2>
		</div>
		<div>
			<form action="search_qna.do" method="post">
				<table style="margin: 10px auto; width: 800px;">
					<tr>
						<td style="width: 83%; text-align: right;">
							<select name="category">
								<option value="all" selected>모두보기</option>
								<option value="completed">답변완료</option>
								<option value="waiting">대기중</option>
							</select>
							<select name="order">
								<option value="desc" selected>최신순</option>
								<option value="asc">오래된순</option>
							</select>
							<input type="text" name="keyword" placeholder="검색어를 입력해주세요."
								   style="width: 300px;">
							<input type="submit" value="검색">
						</td>
						<td style="text-align: right;">
							<c:if test="${!empty sessionScope.loginMember}">
									<input type="button" value="문의작성" onclick="location.href='go_insert_qna.do'">
							</c:if>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<c:choose>
			<c:when test="${!empty requestScope.searchObject}">
				<div style="margin: 10px auto;">"${requestScope.searchObject.keyword}"로 검색</div>
			</c:when>
		</c:choose>
		<div>
			<table style="margin: auto; width: 800px; border: 1px solid black; 
						  border-collapse: collapse; text-align: center;">
				<thead>
					<tr style="border: 1px solid black;">
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
						<c:when test="${!empty requestScope.noData}">
							<tr style="border: 1px solid black;">
								<td colspan="6">${requestScope.noData}</td>
							</tr>
						</c:when>
						<c:when test="${empty requestScope.qnaList}">
							<tr style="border: 1px solid black;">
								<td colspan="6">등록된 게시물이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${requestScope.qnaList}" var="list" varStatus="vs">
								<tr style="border: 1px solid black;">
									<td>${vs.count}</td>
									<td>${list.qna_category}</td>
									<td>
										<c:url var="onelist" value="onelist_qna.do">
											<c:param name="page" value="${page}"/>
											<c:param name="qna_idx" value="${list.qna_idx}"/>
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
							<!-- 이전으로 -->
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
							<!-- 페이지번호 -->
							<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}" step="1" var="k">
								<c:choose>
									<%-- 현재 페이지일 때 --%>
									<c:when test="${k==paging.nowPage}">
										<span style="color: red;">${k}</span>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${empty searchObject}">
												<c:url var="nonSearch" value="list_qna.do">
													<c:param name="cPage" value="${k}"/>
												</c:url>
												<a href="${nonSearch}">${k}</a>
											</c:when>
											<c:otherwise>
												<c:url var="search" value="search_qna_get.do">
													<c:param name="cPage" value="${k}"/>
													<c:param name="category" value="${searchObject.category}"/>
													<c:param name="order" value="${searchObject.order}"/>
													<c:param name="keyword" value="${searchObject.keyword}"/>
												</c:url>
												<a href="${search}">${k}</a>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- 다음으로 -->
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
		</div>
	</article>
</body>
</html>