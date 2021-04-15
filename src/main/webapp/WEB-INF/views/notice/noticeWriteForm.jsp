<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.all{
	width: 650px;
	margin: auto;
	text-align: left;
}
.all input{
	width: 500px;
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
<link rel="stylesheet" type="text/css" href="resources/css/list.css">
</head>
<body>
	<c:import url="../header.jsp" />
	<article>
		<div class="category" style="margin: auto;">
			NOTICE
			<h2><a href="nlist.do">공지사항</a></h2>
		</div>
		<br>
		
	<%-- form 에서 입력값들과 파일을 같이 전송하려면, 반드시 enctype="multipart/form-data"
  속성 추가해야 함 --%>
	<form action="insert_notice.do" method="post" enctype="multipart/form-data">
		<table class="all">
			<tr>
				<th>제 목</th>
				<td><input type="text" name="notice_title"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input name="notice_writer"  readonly value="${loginMember.members_name}"></td>
			</tr>
			<tr>
				<th>파일선택</th>
				<td><input type="file" name="file"></td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><textarea name="notice_content" ></textarea></td>
				<td><script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
					
					<script>
                        CKEDITOR.replace( 'notice_content' );
                	</script>	
			</tr>
		</table>
			<div class="btn">
					<input type="hidden" value="${members_idx }">
					<input id="btn1" type="submit" value="등록"> &nbsp; 
					<button id="btn1" onclick="javascript:history.go(-1); return false;">취소</button>
			</div>
	</form>
	</article>
</body>
</html>