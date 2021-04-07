<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
{
	margin-top: 100px;
	font-family: 'Trebuchet MS', serif;
	line-height: 1.6
}
.container{
	width: 500px;
	margin: 0 auto;
}

ul.tabs{
	margin: 0px;
	padding: 0px;
	list-style: none;
}
ul.tabs li{
	background: none;
	color: #222;
	display: inline-block;
	padding: 10px 15px;
	cursor: pointer;
}

ul.tabs li.current{
	background: #ededed;
	color: #222;
}

.tab-content{
	display: none;
	background: #ededed;
	padding: 15px;
}

.tab-content.current{
	display: inherit;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	
	$('ul.tabs li').click(function(){
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#"+tab_id).addClass('current');
	})

});
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/header.jsp" />

	<h1 align="center">로그인</h1>
	<br>
	<br>
	<br>
	<br>
	
  <div class="container">

	<ul class="tabs">
		<li class="tab-link current" data-tab="tab-1">ID 찾기</li>
		<li class="tab-link" data-tab="tab-2">PW 찾기</li>
	</ul>

	<div id="tab-1" class="tab-content current">
	<div class="outer" align="center">
		<form action="find_id.do" method="post" id="joinForm">
			<table width="500" cellspacing="5">
				<tr>
					<td align="center"><input type="text" name="members_name" placeholder="이름" required></input></td>
				</tr>
				<tr>
					<td align="center"><input type="email" name="members_email" placeholder="이메일" required></td>
				</tr>		
				<tr>
					<td align="center">
						<input type="submit" value="다음">
					</td>
				</tr>
				<br>
			</table>
		</form>
	</div>
	</div>
	<div id="tab-2" class="tab-content">
	<div class="outer" align="center">
		<form action="find_pw.do" method="post" id="joinForm">
			<table width="500" cellspacing="5">
				<tr>
					<td align="center"><input type="text" name="members_name" placeholder="이름" required></input></td>
				</tr>
				<tr>
					<td align="center"><input type="email" name="members_email" placeholder="이메일" required></td>
				</tr>		
				<tr>
					<td align="center">
						<input type="submit" value="다음">
					</td>
				</tr>
				<br>
			</table>
		</form>
	</div>
</div>
</div>
</body>
</html>