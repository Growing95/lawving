<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<c:import url="../common/menubar.jsp" />
	<hr>
	<h1 align="center">공지사항</h1>
	<br>
	<c:if
		test="${ !empty sessionScope.loginUser and sessionScope.loginUser.id eq 'admin' }">
		<div style="align: center; padding-left: 400px;">
			<c:url var="nwf" value="/notice_write.do" />
			<button onclick="javascript:location.href='${ nwf }';">글쓰기</button>
		</div>
	</c:if>
	<br>
	<!-- 검색기능 -->
	<center>
		<div>
			<form action="nsearch.do" method="post">
				<table>
					<tr>
						<td><select name="category">
								<option value="notice_title" selected>제목</option>
								<option value="notice_content">내용</option>
						</select></td>
						<td><select name="order">
								<option value="desc" selected>최신순</option>
								<option value="asc">오래된순</option>
						</select></td>
						<td><input type="text" name="keyword"></td>
						<td><input type="submit" value="검색"></td>
				</table>
			</form>
		</div>
	</center>
	<br>
	<div style="align: center; padding-left: 400px;">
		<c:url var="nlist" value="/nlist.do" />
	</div>
	<br>
	<table align="center" width="500" border="1" cellspacing="0"
		cellpadding="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty noticelist}">
					<tr>
						<td colspan="5"><h3>원하시는 자료는 존재하지 않습니다.</h3></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ requestScope.noticelist }" var="k">

						<tr>
							<td align="center">${k.notice_idx }</td>
							<c:url value="/onelist_notice.do" var="und">
								<c:param name="notice_idx" value="${k.notice_idx}" />
							</c:url>
							<td align="center"><a href="${und }">${k.notice_title }</a></td>
							<td align="center">${k.notice_writer }</td>
							<td align="center"><fmt:formatDate value="${k.notice_reg }"
									pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tfoot>
		<tr>
			<td colspan="4">
				<ol class="paging">
				<!-- 이전 -->
					<c:choose>
						<c:when test="${paging.beginBlock <= paging.pagePerBlock }">
							<li class="disable">이전으로</li>
						</c:when>
						<c:otherwise>
							<li><a href="list_notice.do?cPage=${paging.beginBlock-paging.pagePerBlock }">이전으로</a></li>
								</c:otherwise>
							</c:choose>
							<!-- 블록안에 들어간 페이지번호들 -->
							<c:forEach begin="${paging.beginBlock }" end="${paging.endBlock}"
								step="1" var="k">
								<%--현재 페이지와 현재 페이지가 아니것으로 구분 --%>
								<c:choose>
									<c:when test="${k==paging.nowPage }">
										<li class="now">${k}</li>
									</c:when>
									<c:otherwise>
										<li><a href="list_notice.do?cPage=${k}">${k}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- 다음 -->
							<c:choose>
								<c:when test="${paging.endBlock >= paging.totalPage }">
									<li class="disable">다음으로</li>
								</c:when>
								<c:otherwise>
									<li><a
										href="list_notice.do?cPage=${paging.beginBlock+paging.pagePerBlock }">다음으로</a></li>
								</c:otherwise>
							</c:choose>
						</ol>
					</td>
				</tr>
		</tbody>
		</tfoot>
	</table>
</body>
</html>









