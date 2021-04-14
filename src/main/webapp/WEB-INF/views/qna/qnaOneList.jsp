<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lawving</title>
<link rel="stylesheet" type="text/css" href="resources/css/onelist.css">
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
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
			<div class="category">
				Question and answer
				<h2>
					<a href="list_qna.do">Q&#38;A</a>
				</h2>
			</div>
			<%-- 질문글 제목, 조회수, 작성자, 작성일 박스 --%>
			<div class="title">
				<strong>${requestScope.qnaOnelist.qna_title}</strong>
				<%-- 관리자는 신고 버튼 볼 수 없음 --%>
				<c:if test="${sessionScope.loginMember.members_lev!='2'}">
					<div class="repot">
						신고하기
						<c:choose>
							<%-- 로그인 하지 않은 경우 로그인 화면으로 --%>
							<c:when test="${empty sessionScope.loginMember}">
								<img class="repot_icon" src="resources/images/repot.png" onclick="location.href='go_login.do'">
							</c:when>
							<%-- 로그인 한 경우 신고 화면으로 --%>
							<c:otherwise>
								<img class="repot_icon" src="resources/images/repot.png" 
									 onclick="location.href='go_repot.do?members_idx=${sessionScope.loginMember.members_idx}
									 									&members_idx_2=${requestScope.qnaOnelist.qna_writer}'">
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>
				<div class="info">
					조회수 : ${requestScope.qnaOnelist.qna_hit}&nbsp;&nbsp;&nbsp;
					작성자 : ${requestScope.qnaOnelist.qna_writer}&nbsp;&nbsp;&nbsp;
					작성일 : ${requestScope.qnaOnelist.qna_reg.substring(0,10)}
				</div>
			</div>
			<%-- 질문글 내용 박스 --%>
			<div class="content">
				<div>${requestScope.qnaOnelist.qna_content}</div>
				<%-- 사용자가 글쓴이와 동일인물일 경우 삭제/새질문작성 버튼 --%>
				<c:if test="${requestScope.qnaOnelist.members_idx eq sessionScope.loginMember.members_idx}">
					<form action="delete_qna.do" method="post">
						<div class="content_in_btn">
							<input type="button" value="새 질문 작성" onclick="location.href='go_insert_qna.do'">
							<input type="button" value="삭제" onclick="delete_qna_check(this.form)">
							<input type="hidden" name="qna_idx" value="${requestScope.qnaOnelist.qna_idx}">
							<input type="hidden" name="members_lev" value="${sessionScope.loginMember.members_lev}">
							<input type="hidden" name="members_idx" value="${sessionScope.loginMember.members_idx}">
						</div>
					</form>
				</c:if>
				<%-- 사용자가 관리자일 경우 관리자용 삭제버튼 --%>
				<c:if test="${loginMember.members_lev=='2'}">
					<form class="content_in_btn" action="delete_question.do" method="post">
						<div class="content_in_btn">
							<input type="hidden" name="qna_idx" value="${requestScope.qnaOnelist.qna_idx}">
							<input type="hidden" name="members_idx" value="${requestScope.qnaOnelist.members_idx}">
							<input type="hidden" name="members_id" value="${requestScope.memberslist.members_id}">
							<input type="hidden" name="delpost_category" value="${requestScope.qnaOnelist.qna_category}">
							<input type="hidden" name="delpost_title" value="${requestScope.qnaOnelist.qna_title}">
							<input type="hidden" name="delpost_writer" value="${requestScope.qnaOnelist.qna_writer}">
							<input type="hidden" name="delpost_content" value="${requestScope.qnaOnelist.qna_content}">
							<input type="submit" value="게시글삭제" >
						</div>
					</form>
				</c:if>
			</div>
			<%-- 답글 제목 박스 --%>
			<div class="title">
				<strong>관리자 답변</strong>
				<%-- 입력된 답변이 있을 때 --%>
				<c:if test="${!empty requestScope.qnaOnelist.qna_comment}">
					<div class="info" id="writer_and_reg_box_old">
						작성자 : ${requestScope.qnaOnelist.qna_comment_writer}&nbsp;&nbsp;&nbsp;
						작성일 : ${requestScope.qnaOnelist.qna_reg_a.substring(0,10)}
					</div>
				</c:if>
				<div class="info" id="writer_and_reg_box_new"></div>
			</div>
			<%-- 답글 내용 박스 --%>
			<div class="content">
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
									<script>CKEDITOR.replace('qna_comment');</script>
									<div class="btn">
										<input type="button" value="수정취소" onclick="switch_to_button_box()">
										<input type="button" value="답변수정" onclick="update_answer_func()">
									</div>
								</form>
							</div>
							<div id="button_box" class="content_in_btn" style="text-align: right;">
								<form action="delete_answer.do" method="post">
									<input type="button" value="수정" onclick="switch_to_insert_box()">
									<input type="button" value="삭제" onclick="delete_answer_check(this.form)">
									<input type="hidden" name="qna_idx" value="${requestScope.qnaOnelist.qna_idx}">
									<input type="hidden" name="cPage" value="${cPage}">
								</form>
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
										<script>CKEDITOR.replace('qna_comment');</script>
										<div class="btn">
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
			<div class="btn">
				<button onclick="location.href='before_qna.do?qna_idx=${requestScope.qnaOnelist.qna_idx}&cPage=${cPage}'">이전글</button>
				<button onclick="location.href='list_qna.do?cPage=${cPage}'">목록</button>
				<button onclick="location.href='after_qna.do?qna_idx=${requestScope.qnaOnelist.qna_idx}&cPage=${cPage}'">다음글</button>
			</div>
	</article>
</body>
</html>