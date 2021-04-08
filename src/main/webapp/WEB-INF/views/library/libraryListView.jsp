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
/* paging 영역*/
table tfoot ol.paging {list-style: none;}
table tfoot ol.paging li {float: left;margin-right: 8px;}
table tfoot ol.paging li a {display: block;padding: 3px 7px;border: 1px solid #00B3DC;color: #2f313e;font-weight: bold;}
table tfoot ol.paging li a:hover {background: #00B3DC;color: white;font-weight: bold;}
.disable {padding: 3px 7px;border: 1px solid silver;color: silver;}
.now {	padding: 3px 7px;border: 1px solid #ff4aa5;background: #ff4aa5;color: white;font-weight: bold;}

</style>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<c:import url="../header.jsp" />
	<hr>
	<h1 align="center">자료실</h1>
	<br>
	<c:if
		test="${loginMember.members_lev=='2'}">
		<div style="align: center; padding-left: 400px;">
			<c:url var="lwf" value="/library_write.do" />
			<button onclick="javascript:location.href='${ lwf }';">글쓰기</button>
		</div>
	</c:if>
	<br>
	<!-- 검색기능 -->
	<center>
		<div>
			<form action="lsearch.do" method="post">
				<table>
					<tr>
						<td><select name="category">
								<option value="library_title" selected>제목</option>
								<option value="library_content">내용</option>
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
		<c:url var="llist" value="/llist.do" />
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
				<c:when test="${empty librarylist}">
					<tr>
						<td colspan="5"><h3>원하시는 자료는 존재하지 않습니다.</h3></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ requestScope.librarylist }" var="k">

						<tr>
							<td align="center">${k.library_idx }</td>
							<c:url value="/onelist_library.do" var="old">
								<c:param name="library_idx" value="${k.library_idx}" />
							</c:url>
							<td align="center"><a href="${old }">${k.library_title }</a></td>
							<td align="center">${k.library_writer }</td>
							<td align="center"><fmt:formatDate value="${k.library_reg }"
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
							<li><a href="llist.do?cPage=${paging.beginBlock-paging.pagePerBlock }">이전으로</a></li>
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
										<li><a href="llist.do?cPage=${k}">${k}</a></li>
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
										href="llist.do?cPage=${paging.beginBlock+paging.pagePerBlock }">다음으로</a></li>
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









