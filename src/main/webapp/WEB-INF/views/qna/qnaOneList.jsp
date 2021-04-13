<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lawving</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	/* 질문 삭제 */
	function delete_qna_check(form) {
		var chk = confirm("정말 삭제할까요?");
		if (chk) {
			form.submit();
		} else {
			return;
		}
	}
	/* 답변 입력/수정 */
	function update_answer_func() {
		/* 데이터 변수화 */
		var this_qna_idx = "${requestScope.qnaOnelist.qna_idx}";
		var this_qna_comment = CKEDITOR.instances['qna_comment'].getData();
		var this_qna_comment_writer = "${sessionScope.loginMember.members_name}";
		/* 유효성 검사 */
		if (CKEDITOR.instances['qna_comment'].getData() == '') {
			alert("답변을 입력하세요.");
			return;
		} else {
			/* 답변 전송 후 입력된 답변 다시 가져오기 */
			$.ajax({
				url : "update_answer.do",
				method : "post",
				data : {qna_comment : this_qna_comment, 
						qna_idx : this_qna_idx,
						qna_comment_writer : this_qna_comment_writer},
				dataType : "json",
				success : function(data) {
					document.getElementById("writer_and_reg_box_new").style.display = "block";
					document.getElementById("print_box").style.display = "block";
					document.getElementById("insert_box").style.display = "none";
					$("#print_box").append(data.qna_comment);
					var writer_and_reg = "작성자 : "
										+ data.qna_comment_writer 
										+ "&nbsp;&nbsp;&nbsp;" 
										+ "작성일 : " 
										+ data.qna_reg_a.substring(0,10);
					$("#writer_and_reg_box_new").append(writer_and_reg);
				},
				error : function() {
					alert("입력실패");
				}
			});
		}
	}
	/* 답변 삭제 */
	function delete_answer_check(form) {
		var chk = confirm("정말 삭제할까요?");
		if (chk) {
			form.submit();
		} else {
			return;
		}
	}
	/* 수정모드로 전환 */
	function switch_to_insert_box() {
		document.getElementById("writer_and_reg_box_old").style.display = "none";
		document.getElementById("content_box").style.display = "none";
		document.getElementById("button_box").style.display = "none";
		document.getElementById("insert_box").style.display = "block";
	}
	/* 수정모드 취소 */
	function switch_to_button_box() {
		document.getElementById("writer_and_reg_box_old").style.display = "block";
		document.getElementById("content_box").style.display = "block";
		document.getElementById("button_box").style.display = "block";
		document.getElementById("insert_box").style.display = "none";
	}
</script>
</head>
<body>
	<c:import url="../header.jsp" />
	<article>
		<div style="margin: auto; width: 800px; text-align: left;">
			<div style="width: 100%;">
				Question and answer
				<h2 style="margin: 0px;">
					<a href="list_qna.do" style="text-decoration: none; color: black;">Q&#38;A</a>
				</h2>
			</div>
			<br>
			<%-- 질문글 제목, 조회수, 작성자, 작성일 박스 --%>
			<div style="width: 100%; border: 1px solid black; padding: 10px; box-sizing: border-box;">
				<strong>${requestScope.qnaOnelist.qna_title}</strong>
				<c:if test="${sessionScope.loginMember.members_lev!='2'}">
					<div style="float: right; font-size: 15px;">
						신고하기
						<c:choose>
							<c:when test="${empty sessionScope.loginMember}">
								<img src="resources/images/repot.png" style="height: 20px; vertical-align: middle;"
									 onclick="location.href='go_login.do'">
							</c:when>
							<c:otherwise>
								<img src="resources/images/repot.png" style="height: 20px; vertical-align: middle;" 
									 onclick="location.href='go_repot.do?members_idx=${sessionScope.loginMember.members_idx}
									 									&members_idx_2=${requestScope.qnaOnelist.qna_writer}'">
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>
				<div style="text-align: right;">
					조회수 : ${requestScope.qnaOnelist.qna_hit}&nbsp;&nbsp;&nbsp;
					작성자 : ${requestScope.qnaOnelist.qna_writer}&nbsp;&nbsp;&nbsp;
					작성일 : ${requestScope.qnaOnelist.qna_reg.substring(0,10)}
				</div>
			</div>
			<br>
			<%-- 질문글 내용 박스 --%>
			<div style="width: 100%; border: 1px dotted black; padding: 10px; box-sizing: border-box;">
				<div style="margin-bottom: 10px;">${requestScope.qnaOnelist.qna_content}</div>
				<%-- 사용자가 글쓴이와 동일인물이거나 관리자일 경우 삭제 버튼 --%>
				<c:if test="${requestScope.qnaOnelist.members_idx eq sessionScope.loginMember.members_idx || loginMember.members_lev=='2'}">
					<%-- 사용자가 글쓴이와 동일인물일 경우 새 질문 작성 버튼 --%>
					<c:if test="${requestScope.qnaOnelist.members_idx eq sessionScope.loginMember.members_idx}">
					<button onclick="location.href='go_insert_qna.do'">새 질문 작성</button>
					</c:if>
					<form action="delete_qna.do" method="post" style="display: inline-block; float: right;">
						<input type="button" value="삭제" onclick="delete_qna_check(this.form)" style="float: right;">
						<input type="hidden" name="qna_idx" value="${requestScope.qnaOnelist.qna_idx}">
						<input type="hidden" name="members_lev" value="${sessionScope.loginMember.members_lev}">
						<input type="hidden" name="members_idx" value="${sessionScope.loginMember.members_idx}">
					</form>
					<br>
				</c:if>
			</div>
			<br>
			<%-- 답글 제목 박스 --%>
			<div style="width: 100%; border: 1px solid black; padding: 10px; box-sizing: border-box;">
				<strong>관리자 답변</strong>
				<%-- 입력된 답변이 있을 때 --%>
				<c:if test="${!empty requestScope.qnaOnelist.qna_comment}">
					<div id="writer_and_reg_box_old" style="text-align: right;">
						작성자 : ${requestScope.qnaOnelist.qna_comment_writer}&nbsp;&nbsp;&nbsp;
						작성일 : ${requestScope.qnaOnelist.qna_reg_a.substring(0,10)}
					</div>
				</c:if>
				<div id="writer_and_reg_box_new" style="text-align: right;"></div>
			</div>
			<br>
			<%-- 답글 내용 박스 --%>
			<div style="width: 100%; border: 1px dotted black; padding: 10px; box-sizing: border-box;">
				<div id="print_box"></div>
				<c:choose>
					<%-- 입력된 답변이 있을 때 --%>
					<c:when test="${!empty requestScope.qnaOnelist.qna_comment}">
						<div id="content_box">
						${requestScope.qnaOnelist.qna_comment}
						</div>
						<%-- 사용자가 관리자일 경우--%>
						<c:if test="${sessionScope.loginMember.members_lev=='2'}">
							<div id="insert_box" style="display: none;">
								<form name="update_answer_form">
									<script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
									<textarea name="qna_comment" style="width: 100%">${requestScope.qnaOnelist.qna_comment}</textarea>
									<script>
										CKEDITOR.replace('qna_comment');
									</script>
									<div style="margin: 10px auto; text-align: center;">
										<input type="button" value="수정취소" onclick="switch_to_button_box()">
										<input type="button" value="답변수정" onclick="update_answer_func()">
									</div>
								</form>
							</div>
							<div id="button_box" style="text-align: right;">
								<form action="delete_answer.do" method="post" style="display: inline-block; float: right;">
									<input type="button" value="수정" onclick="switch_to_insert_box()">
									<input type="button" value="삭제" onclick="delete_answer_check(this.form)">
									<input type="hidden" name="qna_idx" value="${requestScope.qnaOnelist.qna_idx}">
									<input type="hidden" name="cPage" value="${cPage}">
								</form>
								<br>
							</div>
						</c:if>
					</c:when>
					<%-- 입력된 답변이 없을 때 --%>
					<c:otherwise>
						<c:choose>
							<%-- 사용자가 관리자일 경우--%>
							<c:when test="${sessionScope.loginMember.members_lev=='2'}">
								<div id="insert_box">
									<form name="update_answer_form">
										<script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
										<textarea name="qna_comment" style="width: 100%"></textarea>
										<script>
											CKEDITOR.replace('qna_comment');
										</script>
										<div style="margin: 10px auto; text-align: center;">
											<input type="button" value="답변쓰기" onclick="update_answer_func()">
										</div>
									</form>
								</div>
							</c:when>
							<%-- 사용자가 관리자가 아닌 경우 --%>
							<c:otherwise>
								<div>관리자 답변이 없습니다.</div>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</div>
			<br>
			<div style="text-align: center;">
				<button onclick="location.href='before_qna.do?qna_idx=${requestScope.qnaOnelist.qna_idx}&cPage=${cPage}'">이전글</button>
				<button onclick="location.href='list_qna.do?cPage=${cPage}'">목록</button>
				<button onclick="location.href='after_qna.do?qna_idx=${requestScope.qnaOnelist.qna_idx}&cPage=${cPage}'">다음글</button>
			</div>
		</div>
	</article>
</body>
</html>