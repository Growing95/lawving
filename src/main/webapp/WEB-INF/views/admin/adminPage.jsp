<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
/* 툴팁메뉴버튼 제이쿼리는 추후 버튼누를시 정보 불러오기때문에 ajax로 변경해야함 */
$(function() {
	$(document).on('click','#m4',function() {
		$('.menu1').css('display','none');
	    $('.menu2').css('display','none');
	    $('.menu3').css('display','none');
	    $('.menu4').css('display','');
	    
	});
	$(document).on('click','#m3',function() {
		$('.menu1').css('display','none');
	    $('.menu2').css('display','none');
	    $('.menu3').css('display','');
	    $('.menu4').css('display','none');
	    
	});
	$(document).on('click','#m2',function() {
		$('.menu1').css('display','none');
	    $('.menu2').css('display','');
	    $('.menu3').css('display','none');
	    $('.menu4').css('display','none');
	});
	$(document).on('click','#m1',function() {
	    $('.menu1').css('display','');
		$('.menu2').css('display','none');
	    $('.menu3').css('display','none');
	    $('.menu4').css('display','none');
	});
})
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
	<li><button id="m1">회원정보</button></li>
	<li><button id="m2">사용제한회원</button></li>
	<li><button id="m3">신고접수</button></li>
	<li><button id="m4">제제회원</button></li>
	
	</ul>
	<!-- 회원정보 -->
	<div class="menu1" style="display: block;">
	<table class="tab">
		<thead>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>가입날짜</th>
			</tr>
		</thead>
	<caption>회원 정보</caption>
	<tbody>
	 <c:choose>
	<c:when test="${empty memberslist}">
	<tr><td colspan="4">회원정보없음</td></tr>
	</c:when>
	<c:otherwise>
		<c:forEach var="k" items="${memberslist }" varStatus="vs">
		<tr>
			<td>${k.members_idx }</td>
			<td>${k.members_id }</td>
			<td>${k.members_name }</td>
			<td>${k.members_reg }</td>
		</tr>
		</c:forEach>
	</c:otherwise>
	</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="4">
				<ol class="paging">
				<!-- 이전 -->
					<c:choose>
						<c:when test="${paging.beginBlock <= paging.pagePerBlock }">
							<li class="disable">이전으로</li>
						</c:when>
						<c:otherwise>
							<li><a href="memberslist.do?cPage=${paging.beginBlock-paging.pagePerBlock }">이전으로</a></li>
								</c:otherwise>
							</c:choose>
							<!-- 블록안에 들어간 페이지번호들 -->
							<c:forEach begin="${paging.beginBlock }" end="${paging.endBlock}"
								step="1" var="k">
								<%--현재 페이지와 현재 페이지가 아니것으로 구분 --%>
								<c:choose>
									<c:when test="${k==paging.nowPage }">
										<li class="now">${k}</li>
									</c:when>
									<c:otherwise>
										<li><a href="memberslist.do?cPage=${k}">${k}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- 다음 -->
							<c:choose>
								<c:when test="${paging.endBlock >= paging.totalPage }">
									<li class="disable">다음으로</li>
								</c:when>
								<c:otherwise>
									<li><a
										href="memberslist.do?cPage=${paging.beginBlock+paging.pagePerBlock }">다음으로</a></li>
								</c:otherwise>
							</c:choose>
						</ol>
					</td>
				</tr>
			</tfoot>
	</table>
	</div>
	<!-- 사용제한회원 -->
	<div class="menu2" style="display: none;">
	<table class="tab" >
	<caption>Set Information</caption>
	<tbody>
	 <c:choose>
	<c:when test="${!empty members}">
	<tr><td colspan="2">회원정보없음</td></tr>
	</c:when>
	<c:otherwise>
	<tr><td>이름 :</td><td><input type="text" disabled value="${members.members_name}" ></td></tr>
	<tr><td>ID :</td><td><input type="text" disabled value="${members.members_id }"></td></tr>
	<tr><td>Email :</td><td><input type="text" id="members_email" value="${members.members_email }"></td></tr>
	<tr><td>생년월일</td><td><input type="text" id="members_birth" value="${members.members_birth}"></td></tr>
	<tr><td>휴대전화</td><td><input type="text" id="members_tel" value="${members.members_tel }" ></td></tr>
	<tr><td colspan="2"><button id="signout" style="width: 100px; height: 50px; background-color:#85929E; color: white;
	 font-weight: bold;border-radius: 40px;">수정하기</button></td></tr>
	 </c:otherwise>
	</c:choose>
	</tbody>
	</table>
	</div>
	<!-- 신고접수 -->
	<div class="menu3" style="display: none;">
	<table class="bookmark">
	<caption>MY BOOKMARK</caption>
	<tbody>
	 <c:choose>
	<c:when test="${!empty bookmark}">
	<tr><td colspan="2">북마크정보없음</td></tr>
	</c:when>
	<c:otherwise>
	<tr><td style="border: none;"><input type="checkbox" id="all_chkdel" onclick="checkAll()" class="all_chkdel">전체선택</td><td style="border: none;"></td><td style="border: none;"><button>선택삭제</button></td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">부동산임대법</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">상속세</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">탈세법위반</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">탈세법위반</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">탈세법위반</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">탈세법위반</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">탈세법위반</a></td><td>2021-04-06</td></tr>
	 </c:otherwise>
	</c:choose>
	</tbody>
	</table>
	</div>
	<!-- 제제회원 -->
	<div class="menu4" style="display: none;">
	<table class="bookmark">
	<caption>MY BOOKMARK</caption>
	<tbody>
	 <c:choose>
	<c:when test="${!empty bookmark}">
	<tr><td colspan="2">북마크정보없음</td></tr>
	</c:when>
	<c:otherwise>
	<tr><td style="border: none;"><input type="checkbox" id="all_chkdel" onclick="checkAll()" class="all_chkdel">전체선택</td><td style="border: none;"></td><td style="border: none;"><button>선택삭제</button></td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">부동산임대법</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">상속세</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">탈세법위반</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">탈세법위반</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">탈세법위반</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">탈세법위반</a></td><td>2021-04-06</td></tr>
	<tr><td><input type="checkbox" id="chkdel" class="chkdel"></td><td><a href="#">탈세법위반</a></td><td>2021-04-06</td></tr>
	 </c:otherwise>
	</c:choose>
	</tbody>
	</table>
	</div>
</div>
<br><br><br><br>
</article>
</body>
</html>