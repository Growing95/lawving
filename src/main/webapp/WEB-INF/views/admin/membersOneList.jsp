<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
a{text-decoration: none; color:orange;}
#box{position: relative; width: 600px; height: auto; margin: 0 auto;}
#ltoolmenu {
list-style-type: none;
padding: 0;
position: fixed;
overflow: auto;
left:8%;
bottom:40%;
z-index:10000;
}
#m1,#m2,#m3,#m4{width: 200px;height: 100px; font-size: 30px; 
 border-radius: 10px;border: none;
background-color: #36454F;
font-family: 'Abril Fatface', cursive;
color: white;
}
/* 오른쪽툴팁메뉴 */
#m1:hover{background-color: white; color: black;}
#m2:hover{background-color: white; color: black;}
#m3:hover{background-color: white; color: black;}
#m4:hover{background-color: white; color: black;}
/* 테이블 */
.tab{background-color: white; width: 600px; height: 500px; margin: auto; border-radius: 20px; }
.tab table th, .tab table td {text-align: center;border: 1px solid black;padding: 4px 10px;}

/* 북마크테이블 */
.bookmark{background-color: white; width: 600px; height: 500px; margin: auto; border-radius: 20px;border-collapse: collapse; }
.bookmark td { font-weight: bold; border-bottom: 1px solid black;
border-top: 1px solid black;
}
/* 콘텐츠영역 */
#box div{
width: 600px;
height: auto;
margin: auto;}
caption{font-weight: bold; color: white; font-size: 40px;}
article{height: 0 auto; background-color: #85929E;
 border-radius: 20px;
}
/* paging 영역*/
table tfoot ol.paging {list-style: none;}
table tfoot ol.paging li {float: left;margin-right: 8px;}
table tfoot ol.paging li a {display: block;padding: 3px 7px;border: 1px solid #00B3DC;color: #2f313e;font-weight: bold;}
table tfoot ol.paging li a:hover {background: #00B3DC;color: white;font-weight: bold;}
.disable {padding: 3px 7px;border: 1px solid silver;color: silver;}
.now {	padding: 3px 7px;border: 1px solid #ff4aa5;background: #ff4aa5;color: white;font-weight: bold;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
/* 툴팁메뉴버튼 제이쿼리는 추후 버튼누를시 정보 불러오기때문에 ajax로 변경해야함 */

</script>
<script type="text/javascript">
function checkAll() {
	if( $("#all_chkdel").is(':checked') ){
        $("input[id=chkdel]").prop("checked", true);
      }else{
        $("input[id=chkdel]").prop("checked", false);
      }
}
</script>
<body>
<c:import url="/WEB-INF/views/header.jsp" />
<article>
<div id="box">
	<!-- 오른쪽툴팁메뉴 -->
	<ul id="ltoolmenu">
	<li><button id="m1"><a href="memberslist.do" style="width: 200px; border: 1px soild purple;" >회원정보</a></button></li>
	<li><button id="m2"><a href="blacklist.do" >사용제한회원</a></button></li>
	<li><button id="m3"><a href="repotlist.do" >신고접수</a></button></li>
	<li><button id="m4"><a href="limitlist.do" >제제회원</a></button></li>
	</ul>
	<!-- 회원상세정보 -->
	<div class="menu1" style="display: block;">
	<table class="tab">
	<caption>회원 정보</caption>
	<tbody>
	<tr><td>이름 : ${mvo.members_name }</td></tr>
	<tr><td>ID : ${mvo.members_id }</td></tr>
	<tr><td>Email :${mvo.members_email }</td></tr>
	<tr><td>생년월일 : ${mvo.members_birth}</td></tr>
	<tr><td>휴대전화 : ${mvo.members_tel }</td></tr>
	<tr><td>주소 : ${mvo.members_address }</td></tr>
	<tr><td>가입날짜 : ${mvo.members_reg }</td></tr>
	<tr><td colspan="2"><button id="signout" style="width: 100px; height: 50px; 
	background-color:#85929E; color: white; font-weight: bold;border-radius: 20px;" onclick="javascript:history.go(-1);">목록으로</button></td></tr>
	</tbody>
	</table>
	</div>
</div>
<br><br><br><br>
</article>
</body>
</html>