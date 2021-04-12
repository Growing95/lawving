<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function saveTemp() {
		localStorage.setItem('user_qna_category', document.insert_qna_form.qna_category.value);
		localStorage.setItem('user_qna_title', document.insert_qna_form.qna_title.value);
		localStorage.setItem('user_qna_content', CKEDITOR.instances['qna_content'].getData());
		alert("임시저장 되었습니다.");
	}
	function callSave() {
		document.insert_qna_form.qna_category.value = localStorage.getItem('user_qna_category');
		document.insert_qna_form.qna_title.value = localStorage.getItem('user_qna_title');
		CKEDITOR.instances['qna_content'].setData(localStorage.getItem('user_qna_content'));
	}
	function insert_qna_func() {
		if (document.insert_qna_form.qna_category.value == 'none') {
			alert("카테고리를 선택하세요.");
			return;
		} else if (document.insert_qna_form.qna_title.value == '') {
			alert("제목을 입력하세요.");
			return;
		}else if (CKEDITOR.instances['qna_content'].getData() == '') {
			alert("내용을 입력하세요.");
			return;
		} else {
			insert_qna_form.submit();
		}
	}
</script>
</head>
<body>
	<c:import url="../header.jsp" />
	<article>
		<div style="margin: 10px auto; width: 800px; text-align: left;">
			Question and answer
			<h2 style="margin: 0px;">
				<a href="list_qna.do" style="text-decoration: none; color: black;">Q&#38;A</a>
			</h2>
		</div>
		<form name="insert_qna_form" action="insert_qna.do" method="post">
			<table style="margin: 10px auto; width: 800px; text-align: left;">
				<tr>
					<td colspan="4" style="text-align: right;">
						<input type="button" value="불러오기" onclick="callSave()">
					</td>
				</tr>
				<tr>
					<th style="width: 10%;">카테고리</th>
					<td style="width: 10%;">
						<select name="qna_category" style="width: 100%;">
							<option value="none" selected>선택</option>
							<option value="question">질문</option>
							<option value="suggestion">건의</option>
						</select>
					</td>
					<th style="width: 10%;">&nbsp;제목</th>
					<td style="width: 70%;">
						<input type="text" name="qna_title" style="width: 99%;">
					</td>
				</tr>
				<tr><td colspan="4">&nbsp;</td></tr>
				<tr>
					<td colspan="4">
						<script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
						<textarea name="qna_content" style="width: 100%"></textarea>
						<script>
							CKEDITOR.replace('qna_content');
						</script>
					</td>
				</tr>
				<tr><td colspan="4">&nbsp;</td></tr>
				<tr style="text-align: center;">
					<th colspan="4">
						<input type="button" value="목록" 
							   onclick="location.href='list_qna.do?cPage=${cPage}'">&nbsp; 
						<input type="button" value="임시저장" onclick="saveTemp()">&nbsp;
						<input type="button" value="등록" onclick="insert_qna_func()">&nbsp;
						<label for="private" style="font-weight: normal;">비공개</label>
						<input type="checkbox" name="private" id="private" value="private">
						<input type="hidden" name="qna_view" value="public">
						<input type="hidden" name="members_idx" value="${loginMember.members_idx}">
						<input type="hidden" name="qna_writer" value="${loginMember.members_name}">
					</th>
				</tr>
			</table>
		</form>
	</article>
</body>
</html>