<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lawving</title>
<link rel="stylesheet" type="text/css" href="resources/css/list.css">
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function () {
		var chkObj = document.getElementsByName("Rowcheck");
		var chkNum = chkObj.length;
		$("input[name='chkall']").click(function() {
			var chk_listArr = $("input[name='Rowcheck']");
			for (var i = 0; i < chk_listArr.length; i++) {
				chk_listArr[i].checked=this.checked;
			}
		})
		
	})	
	function chkdelete() {
		var url = "chk_delete_qna.do";
		var chkArr = new Array();
		var list = $("input[name='Rowcheck']");
		for (var i = 0; i < list.length; i++) {
			if (list[i].checked) {
				chkArr.push(list[i].value);
			}
		}
		if (chkArr.length==0) {
			alert("선택된 항목이 없습니다.");
		}else{
			var chk = confirm("선택한 항목들을 삭제하시겠습니까?");
			if (chk) {
				$.ajax({
					url: url,
					method: "POST",
					traditional: true,
					data : {chkArr:chkArr},
					success: function (data) {
						if (data = 1) {
							alert("항목들을 전부 삭제했습니다.");
							location.replace("list_qna.do");
						}else{
							alert("삭제 실패");
						}
					}
				});
			} else {
				return;
			}
		}
	}
</script>
</head>
<body>
	<c:import url="../header.jsp"/>
	<article>
		<div class="category">
			<table>
				<tr>
					<td rowspan="2" style="width: 10%">
						<img src="resources/images/qna.png" style="padding-right: 10px;">
					</td>
					<td>Question and answer</td>
				</tr>
				<tr><td><h2><a href="list_qna.do">Q&#38;A</a></h2></td></tr>
			</table>
		</div>
		<br>
		<div class="box">
			<form action="search_qna.do" method="post">
				<table>
					<tr>
						<td class="search_box_td">
							<select name="status">
								<option value="all" selected>모두보기</option>
								<option value="completed">답변완료</option>
								<option value="waiting">대기중</option>
							</select>
							<select name="order">
								<option value="desc" selected>최신순</option>
								<option value="asc">오래된순</option>
							</select>
							<input type="text" name="keyword" placeholder="검색어를 입력해주세요.">
							<input type="submit" value="검색">
						</td>
						<%-- 사용자가 로그인 했을 경우 문의작성 버튼 --%>
						<td class="insert_btn_td">
							<c:if test="${!empty sessionScope.loginMember && sessionScope.loginMember.members_lev!='2'}">
								<input type="button" value="문의작성" onclick="location.href='go_insert_qna.do?cPage=${paging.nowPage}'">
							</c:if>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<br>
		<%-- 사용자가 관리자일 때 전체선택, 선택삭제 버튼 --%>
		<c:if test="${sessionScope.loginMember.members_lev=='2'}">
			<div class="box">
				<input type="checkbox" id="chkall" name="chkall">
				<label class="label_chkall" for="chkall">전체선택</label>
				<button class="select_delete" onclick="chkdelete()">선택삭제</button>
				<br>
			</div>
			<br>
		</c:if>
		<%-- 검색결과를 볼 때 검색 키워드 안내 --%>
		<c:if test="${!empty requestScope.searchObject}">
			<div class="box">"${requestScope.searchObject.keyword}"로 검색</div>
		</c:if>
		<div class="box">
			<table class="list">
				<thead>
					<tr>
						<th>번호</th>
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
							<tr class="list">
								<td colspan="6">${requestScope.noData}</td>
							</tr>
						</c:when>
						<%-- 등록된 게시물이 없을 때 --%>
						<c:when test="${empty requestScope.qnaList}">
							<tr class="list">
								<td colspan="6">등록된 게시물이 없습니다.</td>
							</tr>
						</c:when>
						<%-- 검색/리스트 결과 출력 --%>
						<c:otherwise>
							<c:forEach items="${requestScope.qnaList}" var="list" varStatus="vs">
								<tr class="list">
									<%-- 사용자가 관리자가 아닐 때 --%>
									<c:if test="${sessionScope.loginMember.members_lev!='2'}">
										<c:choose>
											<%-- 자신의 것이 아닌 비공개 글 --%>
											<c:when test="${list.qna_view=='비공개' && list.members_idx!=loginMember.members_idx}">
												<td>${list.qna_idx}</td>
												<td>${list.qna_category}</td>
												<td class="non_active">비밀글은 로그인 한 본인만 열람 가능합니다.</td>
												<td>${list.qna_status}</td>
												<td>${list.qna_writer.substring(0,3)}***</td>
												<td>${list.qna_reg.substring(0,10)}</td>
											</c:when>
											<%-- 공개글 혹은 자신의 비공개글 --%>
											<c:otherwise>
												<td>${list.qna_idx}</td>
												<td>${list.qna_category}</td>
												<td>
													<c:url var="onelist" value="onelist_qna.do">
														<c:param name="cPage" value="${paging.nowPage}"/>
														<c:param name="qna_idx" value="${list.qna_idx}"/>
													</c:url>
													<a href="${onelist}">${list.qna_title}</a>
												</td>
												<td>${list.qna_status}</td>
												<td>${list.qna_writer}</td>
												<td>${list.qna_reg.substring(0,10)}</td>
											</c:otherwise>
										</c:choose>
									</c:if>
									<%-- 사용자가 관리자일 때 --%>
									<c:if test="${sessionScope.loginMember.members_lev=='2'}">
										<td>
											<input type="checkbox" name="Rowcheck" value="${list.qna_idx}">
											${list.qna_idx}
										</td>
										<td>${list.qna_category}</td>
										<td>
											<c:url var="onelist" value="onelist_qna.do">
												<c:param name="cPage" value="${paging.nowPage}"/>
												<c:param name="qna_idx" value="${list.qna_idx}"/>
											</c:url>
											<a href="${onelist}">${list.qna_title}</a>
										</td>
										<td>${list.qna_status}</td>
										<td>${list.qna_writer}</td>
										<td>${list.qna_reg.substring(0,10)}</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6" class="paging">
							<%-- 이전으로 --%>
							<c:choose>
								<c:when test="${paging.beginBlock <= paging.pagePerBlock}">
									<span class="non_active">이전으로</span>
								</c:when>
								<c:otherwise>
									<a href="list_qna.do?cPage=${paging.beginBlock-paging.pagePerBlock}">
											이전으로
									</a>
								</c:otherwise>
							</c:choose>
							<%-- 페이지번호 --%>
							<c:forEach begin="${paging.beginBlock}" end="${paging.endBlock}" step="1" var="k">
								<c:choose>
									<%-- 현재 페이지일 때 --%>
									<c:when test="${k==paging.nowPage}">
										<span class="now">${k}</span>
									</c:when>
									<c:otherwise>
										<c:choose>
											<%-- 일반 페이지 이동 --%>
											<c:when test="${empty searchObject}">
												<c:url var="nonSearch" value="list_qna.do">
													<c:param name="cPage" value="${k}"/>
												</c:url>
												<a href="${nonSearch}">${k}</a>
											</c:when>
											<%-- 검색하고서 페이지 이동 --%>
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
									<span class="non_active">다음으로</span>
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