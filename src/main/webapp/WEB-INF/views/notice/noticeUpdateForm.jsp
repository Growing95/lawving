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
.all{
	width: 650px;
	margin: auto;
	text-align: left;
}

th{
	padding: 7px;
	width: 100px;
	text-align: center;
}
.btn{
	margin-top: 10px;
}
</style>
</head>
<body>
	<c:import url="../header.jsp" />
	<article>
		<div class="category" style="margin: auto;">
			NOTICE
			<h2>
				<a href="nlist.do">공지사항</a>
			</h2>
		</div>
		<br>
		<%-- form 에서 입력값들과 파일을 같이 전송하려면, 반드시 enctype="multipart/form-data"
  속성 추가해야 함 --%>
		<form action="updatenotice.do" method="post"
			enctype="multipart/form-data">
				<input type="hidden" name="notice_idx" value="${nvo.notice_idx }">
				<input type="hidden" name="notice_writer" value="${nvo.notice_writer }"> 
				<input type="hidden" name="cPage" value="${cPage }"> 
				<input type="hidden" name="notice_file_name" value="${nvo.notice_file_name }"> 
				<input type="hidden" name="notice_refile_name" value="${nvo.notice_refile_name }">
			<c:if test="${ !empty nvo.notice_file_name }">
				<input type="hidden" name="file" value="${ nvo.notice_file_name }">
			</c:if>
			<table class="all">
				<tr>
					<th>제 목</th>
					<td><input style="width: 500px;" type="text" name="notice_title"
						value="${ nvo.notice_title }"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input style="width: 500px;" type="text" name="notice_writer" readonly
						value="${ nvo.notice_writer }"></td>
				</tr>
				<tr>
					<th>파일선택</th>
					<td>
						<%-- 원래 첨부파일이 있는 경우 --%> <c:if
							test="${ !empty nvo.notice_file_name }">${ nvo.notice_file_name }
						<input type="checkbox" name="delFlag" value="yes"> 파일삭제 
						</c:if> <%-- 원래 첨부파일이 없는 경우 --%> <c:if
							test="${ empty nvo.notice_file_name }">
							<input type="file" name="file">
						</c:if>
					</td>
				</tr>
				<tr>
					<th>내 용</th>
					<td><textarea name="notice_content" rows="5" cols="50">${ nvo.notice_content }</textarea></td>
					<td><script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
					
					<script>
                        CKEDITOR.replace( 'notice_content' );
                	</script>	
				</tr>
			</table>
					<div style="padding: 7px">
					<input type="submit" value="등록"> &nbsp; 
					<button onclick="javascript:history.go(-1); return false;">취소</button>
				</div>
		</form>
	</article>
</body>
</html>