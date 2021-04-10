<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function saveTemp() {
		localStorage.setItem();
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
		<form action="insert_qna.do" method="post">
			<table style="margin: 10px auto; width: 800px; text-align: left;">
				<tr>
					<td colspan="4">
						<input type="button" value="불러오기" onclick="call()">
					</td>
				</tr>
				<tr>
					<th style="width: 10%;">카테고리</th>
					<td style="width: 10%;">
						<select name="qna_category" style="width: 100%;">
							<option selected>선택</option>
							<option value="question">질문</option>
							<option value="suggestion">건의</option>
						</select>
					</td>
					<th style="width: 10%;">&nbsp;제목</th>
					<td style="width: 70%;">
						<input type="text" name="qna_title" style="width: 98%;">
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
						<input type="button" value="임시저장" onclick="saveTemp(this.form)">&nbsp;
						<input type="submit" value="등록">&nbsp;
						<label for="qna_view" style="font-weight: normal;">비공개</label>
						<input type="checkbox" name="qna_view" id="qna_view" value="private">
						<input type="hidden" name="members_idx" value="${loginMember.members_idx}">
						<input type="hidden" name="qna_writer" value="${loginMember.members_name}">
					</th>
				</tr>
			</table>
		</form>
	</article>
</body>
</html>