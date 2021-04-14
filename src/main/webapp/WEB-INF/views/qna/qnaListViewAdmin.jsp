<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lawving</title>
<script type="text/javascript">
	function see_waiting() {
	
	}
	function see_completed() {
	
	}
	function select_all() {
	
	}
</script>
</head>
<body>
	<c:import url="../header.jsp"/>
	<article>
		<div style="margin: 10px auto; width: 800px; text-align: left;">
			<div>Question and answer</div>
			<h2 style="margin: 0px;">
				<a href="list_qna.do" style="text-decoration: none; color: black;">Q&#38;A</a>
			</h2>
		</div>
		<div>
			<button name="status" value="waiting" onclick="see_waiting()">대기중</button>
			<button name="status" value="completed" onclick="see_completed()">답변완료</button>
			<button name="status" value="all" onclick="see_all()">모두보기</button>
		</div>
		<div>
			<form action="search_qna.do" method="post">
				<table style="margin: 10px auto; width: 800px;">
					<tr>
						<td><input type="checkbox" onclick="select_all()">전체선택</td>
						<td style="text-align: right;">
							<c:if test="${!empty sessionScope.loginMember}">
								<input type="button" value="선택항목 삭제" 
									   onclick="location.href=''">
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
						<%-- 검색 결과가 없을 때 --%>
						<c:when test="${!empty requestScope.noData}">
							<tr style="border: 1px solid black;">
								<td colspan="6">${requestScope.noData}</td>
							</tr>
						</c:when>
						<%-- 등록된 게시물이 없을 때 --%>
						<c:when test="${empty requestScope.qnaList}">
							<tr style="border: 1px solid black;">
								<td colspan="6">등록된 게시물이 없습니다.</td>
							</tr>
						</c:when>
						<%-- 검색/리스트 결과 출력 --%>
						<c:otherwise>
							<c:forEach items="${requestScope.qnaList}" var="list" varStatus="vs">
								<c:choose>
									<c:when test="${list.qna_view=='비공개' && list.members_idx!=loginMember.members_idx}">
										<tr style="border: 1px solid black; color: DarkGray;">
											<td><input type="checkbox" name="select_delete" value="${list.qna_idx}">${vs.count}</td>
											<td>${list.qna_category}</td>
											<td>비밀글은 로그인 한 본인만 열람 가능합니다.</td>
											<td>${list.qna_status}</td>
											<td>${list.qna_writer.substring(0,3)}***</td>
											<td>${list.qna_reg.substring(0,10)}</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr style="border: 1px solid black;">
											<td>${vs.count}</td>
											<td>${list.qna_category}</td>
											<td>
												<c:url var="onelist" value="onelist_qna.do">
													<c:param name="cPage" value="${paging.nowPage}"/>
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
									</c:otherwise>
								</c:choose>
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
													<c:param name="status" value="${searchObject.status}"/>
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