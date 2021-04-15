<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/list.css">
<style type="text/css">
a {text-decoration: none;color: orange;}
#box {position: relative;width: 600px;height: auto;margin: 0 auto;}
#ltoolmenu {list-style-type: none;padding: 0;position: fixed;overflow: auto;left: 8%;bottom: 40%;z-index: 10000;}
#m1, #m2, #m3, #m4 {width: 200px;height: 100px;font-size: 30px;border-radius: 10px;border: none;background-color: #36454F;font-family: 'Abril Fatface', cursive;color: white;}
/* 오른쪽툴팁메뉴 */
#m1:hover {background-color: white;color: black;}
#m2:hover {	background-color: white;color: black;}
#m3:hover {background-color: white;color: black;}
#m4:hover {	background-color: white;color: black;}
/* 테이블 */
.tab {background-color: white;width: 600px;height: 500px;margin: auto;border-radius: 20px;}
.tab table th, .tab table td {text-align: center;border: 1px solid black;padding: 4px 10px;}
/* 콘텐츠영역 */
#box div {
	width: 600px;
	height: auto;
	margin: auto;
}

caption {
	font-weight: bold;
	color: white;
	font-size: 40px;
}

article {
	height: 0 auto;
	background-color: #85929E;
	border-radius: 20px;
}
/* paging 영역*/

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	/* 툴팁메뉴버튼 제이쿼리는 추후 버튼누를시 정보 불러오기때문에 ajax로 변경해야함 */
	$(function() {
		/* 		$(document).on('click', '#m4', function() {
		 $('.menu1').css('display', 'none');
		 $('.menu2').css('display', 'none');
		 $('.menu3').css('display', 'none');
		 $('.menu4').css('display', '');

		 });
		 $(document).on('click', '#m3', function() {
		 $('.menu1').css('display', 'none');
		 $('.menu2').css('display', 'none');
		 $('.menu3').css('display', '');
		 $('.menu4').css('display', 'none');

		 });
		 $(document).on('click', '#m2', function() {
		 $('.menu1').css('display', 'none');
		 $('.menu2').css('display', '');
		 $('.menu3').css('display', 'none');
		 $('.menu4').css('display', 'none');
		 });
		 $(document).on('click', '#m1', function() {
		 $('.menu1').css('display', '');
		 $('.menu2').css('display', 'none');
		 $('.menu3').css('display', 'none');
		 $('.menu4').css('display', 'none');
		 }); */

	})
</script>
<script type="text/javascript">
	function checkAll() {
		if ($("#all_chkdel").is(':checked')) {
			$("input[id=chkdel]").prop("checked", true);
		} else {
			$("input[id=chkdel]").prop("checked", false);
		}
	}
</script>
<body>
	<article>

	<c:import url="/WEB-INF/views/header.jsp" />
		<div id="box">
			<!-- 오른쪽툴팁메뉴 -->
			<ul id="ltoolmenu">
				<li><button id="m1">
						<a href="memberslist.do"
							style="width: 200px; border: 1px soild purple;">회원정보</a>
					</button></li>
				<li><button id="m2">
						<a href="blacklist.do">사용제한회원</a>
					</button></li>
				<li><button id="m3">
						<a href="repotlist.do">신고접수</a>
					</button></li>
				<li><button id="m4">
						<a href="limitlist.do">제제회원</a>
					</button></li>
			</ul>
			<!-- 회원정보 -->
			<div class="menu1" style="display: block;">
				<table class="tab">
					<thead>
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>가입날짜</th>
						</tr>
					</thead>
					<caption>회원 정보</caption>
					<tbody>
						<c:choose>
							<c:when test="${empty memberslist}">
								<tr>
									<td colspan="4">회원정보없음</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="k" items="${memberslist }" varStatus="vs">
									<tr>
										<td>${k.members_idx }</td>
										<c:url value="/membersonelist.do" var="mol">
											<c:param name="members_idx" value="${k.members_idx}" />
										</c:url>
										<td><a href="${mol }">${k.members_id }</a></td>
										<td>${k.members_name }</td>
										<td>${k.members_reg }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4" class="paging">
								<!-- 이전 --> 
								<c:choose>
									<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
										<span class="non_active">이전으로</span>
									</c:when>
									<c:otherwise>
										<a href="memberslist.do?cPage=${paging.beginBlock-paging.pagePerBlock }">이전으로</a>
									</c:otherwise>
								</c:choose>
								<!-- 블록안에 들어간 페이지번호들 --> 
								<c:forEach begin="${paging.beginBlock }" end="${paging.endBlock}" step="1" var="k">
									<%--현재 페이지와 현재 페이지가 아니것으로 구분 --%>
									<c:choose>
										<c:when test="${k==paging.nowPage }">
											<span class="now">${k}</span>
										</c:when>
										<c:otherwise>
											<a href="memberslist.do?cPage=${k}">${k}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach> 
								<!-- 다음 --> 
								<c:choose>
									<c:when test="${paging.endBlock >= paging.totalPage }">
										<span class="non_active">다음으로</span>
									</c:when>
									<c:otherwise>
										<a href="memberslist.do?cPage=${paging.beginBlock+paging.pagePerBlock }">다음으로</a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</article>
</body>
</html>