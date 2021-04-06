<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
#box{position: relative; width: 500px; height: 500px; margin: auto;}
#rtoolmenu {
list-style-type: none;
padding: 0;
position: fixed;
overflow: auto;
left:10.2%;
bottom:50%;
z-index:10000;
}
#m1,#m2{width: 100px;height: 50px;}
.tab{background-color: white; margin-left: 50%;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	$(document).on('click','#m2',function() {
		$('.menu1').css('display','none');
	    $('.menu2').css('display','');
	});
	$(document).on('click','#m1',function() {
		$('.menu2').css('display','none');
	    $('.menu1').css('display','');
	});
})
</script>
<body>
<c:import url="/WEB-INF/views/header.jsp" />
<article>
<div id="box">
	<div class="menu1" style="display: block;">
	<table class="tab" style="width: 300px; float: left;">
	<caption>내정보</caption>
	<tbody>
	 <c:choose>
	<c:when test="${empty members}">
	<tr><td colspan="2">회원정보없음</td></tr>
	</c:when>
	<c:otherwise>
	<tr><td>이름 :</td><td></td></tr>
	<tr><td>ID :</td><td></td></tr>
	<tr><td>Email :</td><td></td></tr>
	<tr><td>생년월일</td><td></td></tr>
	<tr><td>휴대전화</td><td></td></tr>
	<tr><td>누적경고수횟수</td><td>0</td></tr>
	<tr><td><button id="signout">회원탈퇴</button></td></tr>
	 </c:otherwise>
	</c:choose>
	</tbody>
	</table>
	</div>
	<ul id="rtoolmenu"><li><button id="m1">메뉴1</button></li><li><button id="m2">메뉴2</button></li></ul>
	<div class="menu2" style="display: none;">
	<table class="tab" style="width: 300px; float: left;">
	<caption>내정보수정</caption>
	<thead><tr><th>이름</th><th>아이디</th><th>닉네임</th></tr></thead>
	<tr><td>홍길동2</td><td>adfs12</td><td>gogo</td></tr>
	<tr><td>홍길동</td><td>adfs12</td><td>gogo</td></tr>
	<tr><td>홍길동3</td><td>adfs12</td><td>gogo</td></tr>
	<tr><td>홍길동</td><td>adfs12</td><td>gogo</td></tr>
	<tr><td>홍길동</td><td>adfs12</td><td>gogo</td></tr>
	<tr><td>홍길동4</td><td>adfs12</td><td>gogo</td></tr>
	<tr><td>홍길동41</td><td>adfs12</td><td>gogo</td></tr>
	<tr><td>홍길동22</td><td>adfs12</td><td>gogo</td></tr>
	</table>
	</div>
</div>
</article>
</body>
</html>