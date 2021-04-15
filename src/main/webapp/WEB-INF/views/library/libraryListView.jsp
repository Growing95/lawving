<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/list.css">
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function () { 
	var chkObj= document.getElementsByName("Rowcheck");
	var chkNum=chkObj.length;
	$("input[name='chkall']").click(function() {
		var chk_listArr = $("input[name='Rowcheck']");
		for (var i = 0; i < chk_listArr.length; i++) {
			chk_listArr[i].checked=this.checked;
		}
	})
	
})	
	function chkdelete() {
	var url="chkdelete.do";
	var chkArr= new Array();
	var list =$("input[name='Rowcheck']");
	for (var i = 0; i < list.length; i++) {
		if (list[i].checked) {
			chkArr.push(list[i].value);
		}
	}
	if (chkArr.length==0) {
		alert("선택된 항목이 없습니다.");
	}else{
		var chk = confirm("선택한 항목들을 삭제하시겠습니까?");
		$.ajax({
			url: url,
			method: "POST",
			traditional:true,
			data :{chkArr:chkArr},
			success: function (data) {
				if (data = 1) {
					alert("항목들을 전부 삭제했습니다.");
					location.replace("llist.do");
				}else{
					alert("삭제 실패");
				}
			}
		});
	}
}
</script>
</head>
<body>
	<c:import url="../header.jsp" />
	<article>
		<div class="category">
			<table>
				<tr>
					<td rowspan="2" style="width: 10%">
						<img src="resources/images/library1.png" style="padding-right: 10px;">
					</td>
					<td>Library</td>
				</tr>
				<tr><td><h2><a href="llist.do">자료실</a></h2></td></tr>
			</table>
		</div>
		<br>
	<!-- 검색기능 -->
		<div class="box">
			<form action="lsearch.do" method="post">
				<table>
					<tr>
						<td class="search_box_td">
							<select name="category">
								<option value="library_title" selected>제목</option>
								<option value="library_content">내용</option>
							</select>
							<select name="order">
								<option value="desc" selected>최신순</option>
								<option value="asc">오래된순</option>
							</select>
							<input type="text" name="keyword">
							<input type="submit" value="검색">
						</td>
						<td></td>
				</table>
			</form>
		</div>
		<br>
	<c:if test="${loginMember.members_lev=='2'}">
		<div class="box">
			<input type="checkbox" id="chkall" name="chkall">
			<label class="label_chkall" for="chkall">전체선택</label>
			<div class="select_delete">
				<c:url var="lwf" value="/library_write.do" />
				<button onclick="javascript:location.href='${ lwf }';">글쓰기</button>
				<button onclick="chkdelete()">선택삭제</button>
			</div>
		</div><br><br>
	</c:if>
	<div class="box">
	<table class="list">
		<thead>
			<tr class="list">
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
			    <th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty librarylist}">
					<tr class="list">
						<td colspan="4"><h3>원하시는 자료는 존재하지 않습니다.</h3></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ requestScope.librarylist }" var="k">
						<tr class="list">
							<td>
								<c:if test="${sessionScope.loginMember.members_lev=='2' }">
									<input type="checkbox" name="Rowcheck" value="${k.library_idx }">
								</c:if>
								${k.library_idx }
							</td>
							<c:url value="/onelist_library.do" var="old">
								<c:param name="library_idx" value="${k.library_idx}" />
								<c:param name="cPage" value="${paging.nowPage}"/>
							</c:url>
							<td><a href="${old }">${k.library_title }</a></td>
							<td>${k.library_writer }</td>
							<td><fmt:formatDate value="${k.library_reg }" pattern="yyyy-MM-dd" /></td>
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
							<a href="llist.do?cPage=${paging.beginBlock-paging.pagePerBlock }">이전으로</a>
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
								<a href="llist.do?cPage=${k}">${k}</a>
							</c:otherwise>
						</c:choose>
						</c:forEach>
						<!-- 다음 -->
						<c:choose>
							<c:when test="${paging.endBlock >= paging.totalPage }">
								<span class="non_active">다음으로</span>
							</c:when>
							<c:otherwise>
								<a href="llist.do?cPage=${paging.beginBlock+paging.pagePerBlock }">다음으로</a>
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