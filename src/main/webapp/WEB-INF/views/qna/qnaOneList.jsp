<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lawving</title>
<script type="text/javascript">
	function delete_qna_check(form) {
		var chk = confirm("정말 삭제할까요?");
		if (chk) {
			form.submit();
		} else {
			return;
		}
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
			<div style="width: 100%; border: 1px solid black; padding: 10px;">
				<strong>${requestScope.qnaOnelist.qna_title}</strong>
				<div style="float: right; font-size: 15px;" onclick="location.href='repot.do'">
					신고하기
					<img src="resources/images/repot.png" style="height: 20px; vertical-align: middle;">
				</div>
				<div style="text-align: right;">
					조회수 : ${requestScope.qnaOnelist.qna_hit}&nbsp;&nbsp;&nbsp;
					작성자 : ${requestScope.qnaOnelist.qna_writer}&nbsp;&nbsp;&nbsp;
					작성일 : ${requestScope.qnaOnelist.qna_reg.substring(0,10)}
				</div>
			</div>
			<br>
			<div style="width: 100%; border: 1px dotted black; padding: 10px;">
				<div style="margin-bottom: 10px;">${requestScope.qnaOnelist.qna_content}</div>
				<c:if test="${!empty sessionScope.loginMember}">
					<button onclick="location.href='go_insert_qna.do?cPage=${cPage}'">새 질문 작성</button>
				</c:if>
				<c:if test="${requestScope.qnaOnelist.members_idx eq sessionScope.loginMember.members_idx}">
					<form action="delete_qna.do" method="post" style="display: inline-block; float: right;">
						<input type="button" value="삭제" onclick="delete_qna_check(this.form)" style="float: right;">
						<input type="hidden" name="qna_idx" value="${requestScope.qnaOnelist.qna_idx}">
						<input type="hidden" name="members_lev" value="${sessionScope.loginMember.members_lev}">
						<input type="hidden" name="members_idx" value="${sessionScope.loginMember.members_idx}">
					</form>
				</c:if>
			</div>
			<br>
			<c:choose>
				<c:when test="${loginMember.members_lev=='2'}">
					<c:choose>
						<c:when test="${empty qnaOnelist.qna_comment}">
							<div style="text-align: center;">
								<form action="update_answer.do">
									<script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
									<textarea name="qna_content" style="width: 100%"></textarea>
									<script>
										CKEDITOR.replace('qna_content');
									</script>
								</form>
							</div>
							<br>
						</c:when>
						<c:otherwise>
							<div style="width: 100%; border: 1px solid black; padding: 10px;">
								<strong>관리자 답변</strong>
								<div style="text-align: right;">
									작성일 : ${requestScope.qnaOnelist.qna_reg_a.substring(0,10)}
								</div>
							</div>
							<br>
							<div style="width: 100%; border: 1px dotted black; padding: 10px;">
								${requestScope.qnaOnelist.qna_comment}
							</div>
							<br>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${empty qnaOnelist.qna_comment}">
							<div style="text-align: center;">
								관리자 답변이 없습니다.
							</div>
							<br>
						</c:when>
						<c:otherwise>
							<div style="width: 100%; border: 1px solid black; padding: 10px;">
								<strong>관리자 답변</strong>
								<div style="text-align: right;">
									작성일 : ${requestScope.qnaOnelist.qna_reg_a.substring(0,10)}
								</div>
							</div>
							<br>
							<div style="width: 100%; border: 1px dotted black; padding: 10px;">
								${requestScope.qnaOnelist.qna_comment}
							</div>
							<br>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			<div style="text-align: center;">
				<button onclick="location.href='before_qna.do?qna_idx=${requestScope.qnaOnelist.qna_idx}&cPage=${cPage}'">이전글</button>
				<button onclick="location.href='list_qna.do?cPage=${cPage}'">목록</button>
				<button onclick="location.href='after_qna.do?qna_idx=${requestScope.qnaOnelist.qna_idx}&cPage=${cPage}'">다음글</button>
			</div>
		</div>
	</article>
</body>
</html>