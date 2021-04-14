<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LAWVING_MYPAGE</title>
</head>
<style type="text/css">
#box {
	position: relative;
	width: 600px;
	height: auto;
	margin: auto;
}

#ltoolmenu {
	list-style-type: none;
	padding: 0;
	position: fixed;
	overflow: auto;
	left: 8%;
	bottom: 30%;
	z-index: 10000;
}

#m1, #m2, #m3 {
	width: 200px;
	height: 100px;
	font-size: 30px;
	border-radius: 10px;
	border: none;
	background-color: #36454F;
	font-family: 'Abril Fatface', cursive;
	color: white;
}
/* 오른쪽툴팁메뉴 */
#m1:hover {
	background-color: white;
	color: black;
}

#m2:hover {
	background-color: white;
	color: black;
}

#m3:hover {
	background-color: white;
	color: black;
}

/* 테이블 */
.tab {
	background-color: white;
	width: 600px;
	height: 500px;
	margin: auto;
	border-radius: 20px;
}

/* 북마크테이블 */
.bookmark {
	background-color: #27496b; width: 600px; height: auto; margin: 0 auto; border-radius: 20px; 
	border-collapse:collapse;
	 color: white; }
	


.bookmark td {
	font-weight: bold;
	border-top: 1px solid white;
}
.bookmark td a {color: white;}
/* 콘텐츠영역 */
#box div {
	width: auto;
	height: auto;
	margin: auto;
}

#box #menu3 {
	width: auto;
	height: auto;
	margin: auto;
}

caption {
	font-weight: bold;
	color: white;
	font-size: 40px;
}

article {
	height: 0 auto;
	background-color: #85929E;
	border-radius: 20px;
}

#box div {
	padding-top: 50px;
}
/* paging 영역*/
table tfoot ol.paging {
	list-style: none;
}

table tfoot ol.paging li {
	float: left;
	margin-right: 8px;
}

table tfoot ol.paging li a {
	display: block;
	padding: 3px 7px;
	border: 1px solid #00B3DC;
	color: #2f313e;
	font-weight: bold;
}

table tfoot ol.paging li a:hover {
	background: #00B3DC;
	color: white;
	font-weight: bold;
}

.disable {
	padding: 3px 7px;
	border: 1px solid silver;
	color: silver;
}

.now {
	padding: 3px 7px;
	border: 1px solid #ff4aa5;
	background: #ff4aa5;
	color: white;
	font-weight: bold;
}


</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script type="text/javascript">
	/* 툴팁메뉴버튼 제이쿼리는 추후 버튼누를시 정보 불러오기때문에 ajax로 변경해야함 */
	$(function() {

		$(document).on('click', '#m3', function() {
			$('.menu1').css('display', 'none');
			$('.menu2').css('display', 'none');
			$('.menu3').css('display', '');
			$('.menu4').css('display', 'none');
		});
		$(document).on('click', '#m2', function() {
			$('.menu1').css('display', 'none');
			$('.menu2').css('display', '');
			$('.menu3').css('display', 'none');
			$('.menu4').css('display', 'none');
		});
		$(document).on('click', '#m1', function() {
			$('.menu1').css('display', '');
			$('.menu2').css('display', 'none');
			$('.menu3').css('display', 'none');
			$('.menu4').css('display', 'none');
		});
	})
</script>
<script type="text/javascript">
	$(function() {
		$("#chkdelete")
				.click(
						function() {

							var chk_id = [];
							$("input[name='chk_id']:checked").each(function() {
								chk_id.push($(this).val());
							});
							if (chk_id.length == 0) {
								alert("하나이상 선택해주세요");
								return;
							} else {
								alert("선택항목을 삭제합니다.");
							}
							// 하나도 선택하지않았을때 오류발생
							$
									.ajax({
										url : "chk_del.do",
										method : "post",
										data : {
											"chk_id" : chk_id
										},
										traditional : true,//배열넘길땐 반드시 써줘야함
										dataType : "text",
										success : function(data) {
											if (data.trim() >= '1') {
												location.href = "list_bookmark.do?members_idx=${loginMember.members_idx}";
												alert("삭제성공");
											} else {
												alert("삭제실패");
											}
										},
										error : function() {
											alert("읽기실패");
										}
									});
						});
		var button = "${button}";
		if (button != null) {
			if (button == "m3") {
				$("#m3").trigger("click");
			}
		}
	})
</script>
<script type="text/javascript">
	function checkAll() {
		if ($("#all_chkdel").is(':checked')) {
			$("input[id=chk_id]").prop("checked", true);
		} else {
			$("input[id=chk_id]").prop("checked", false);
		}
	}
	function validate() {
		//암호와 암호확인이 일치하는지 검사
		var pwdValue = document.getElementById("members_pw").value;
		var pwdValue2 = document.getElementById("members_pw2").value;
		if (pwdValue !== pwdValue2) {
			alert("암호와 암호확인의 입력값이 일치하지 않습니다.");
			document.getElementById("members_pw2").select();
			return false;//전송취소처리
		}else{
		return true; //전송 처리
			
		}

	}
</script>
<body>
	<c:import url="/WEB-INF/views/header.jsp" />
	<article>
		<div id="box">
			<script type="text/javascript">
				
			</script>
			<!-- 오른쪽툴팁메뉴 -->
			<ul id="ltoolmenu">
				<li><button id="m1">INFO</button></li>
				<li><button id="m2">SET INFO</button></li>
				<li><button id="m3">BookMark</button></li>
			</ul>
			<!-- 내정보 -->
			<div class="menu1" style="display: none;">
				<table class="tab">
					<caption>MY Information</caption>
					<tbody>
						<c:choose>
							<c:when test="${empty sessionScope.loginMember}">
								<tr>
									<td colspan="2">회원정보없음</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:set var="m" value="${sessionScope.loginMember}" />
								<tr>
									<td>이름 :</td>
									<td><input type="text" disabled value="${m.members_name }"></td>
								</tr>
								<tr>
									<td>ID :</td>
									<td><input type="text" disabled value="${m.members_id }"></td>
								</tr>
								<tr>
									<td>Email :</td>
									<td><input type="text" disabled
										value="${m.members_email }"></td>
								</tr>
								<tr>
									<td>생년월일</td>
									<td><input type="text" disabled value="${m.members_birth}"></td>
								</tr>
								<tr>
									<td>휴대전화</td>
									<td><input type="text" disabled value="${m.members_tel }"></td>
								</tr>
								<tr>
									<td>가입날짜</td>
									<td><input type="text" disabled value="${m.members_reg }"></td>
								</tr>
								<tr>
									<td colspan="2"><button id="signout"
											style="width: 100px; height: 50px; background-color: #85929E; color: white; font-weight: bold; border-radius: 20px;"
											onclick="location.href='delete_members.do?members_idx=${m.members_idx}'">회원탈퇴</button></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<!-- 내정보수정 -->
			<div class="menu2" style="display: none;">
				<table class="tab">
					<caption>Set Information</caption>
					<tbody>
						<c:choose>
							<c:when test="${empty sessionScope.loginMember}">
								<tr>
									<td colspan="2">회원정보없음</td>
								</tr>
							</c:when>
							<c:otherwise>
								<form action="update_members.do" method="post" id="mupdateForm">
									<tr>
										<td>이름 :</td>
										<td><input type="text" disabled value="${m.members_name}"></td>
									</tr>
									<tr>
										<td>ID :</td>
										<td><input type="text" disabled value="${m.members_id }"></td>
									</tr>
									<input type="hidden" name="members_id" value="${m.members_id }">
									<tr>
										<td>Email :</td>
										<td><input type="text" id="members_email"
											name="members_email" value="${m.members_email }" required></td>
									</tr>
									<tr>
										<td>생년월일</td>
										<td><input type="text" id="members_birth"
											name="members_birth" value="${m.members_birth}" required></td>
									</tr>
									<tr>
										<td>휴대전화</td>
										<td><input type="text" id="members_tel"
											name="members_tel" value="${m.members_tel }" required></td>
									</tr>

									<tr>
										<td colspan="2"><button id="update" type="submit"
												style="width: 100px; height: 50px; background-color: #85929E; color: white; font-weight: bold; border-radius: 40px;">수정하기</button></td>
									</tr>
								</form>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<br><br><br><br>
				<table class="tab" style="height: 350px;">
					<caption>NEW PASSWORD</caption>
					<tbody>
						<c:choose>
							<c:when test="${empty sessionScope.loginMember}">
								<tr>
									<td colspan="2">회원정보없음</td>
								</tr>
							</c:when>
							<c:otherwise>
								<form action="update_pw.do" method="post" id="pwdataForm" onsubmit="return validate()">
									<tr>
										<td>변경할 패스워드</td>
										<td><input type="password" id="members_pw"
											name="members_pw" placeholder="변경할 패스워드" required></td>
									</tr>
									<tr>
										<td>변경할 패스워드확인</td>
										<td><input type="password" id="members_pw2"
											name="members_pw2" placeholder="변경할 패스워드 확인" required>
									</tr>
									<tr>
										<td colspan="2">
											<button id="update" type="submit"
												style="width: 100px; height: 50px; background-color: #85929E; color: white; font-weight: bold; border-radius: 40px;">변경하기</button>
										</td>
										<input type="hidden" id="members_id" name="members_id" value="${m.members_id}">
									</tr>
									</from>
								</form>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<!-- 북마크 -->
			<div class="menu3" style="display: none;">
				<table class="bookmark">
					<caption>MY BOOKMARK</caption>
					<tbody>
						<c:choose>
							<c:when test="${empty blist}">
								<tr>
									<td colspan="2"
										style="border-bottom: none; border-top: none; height: 500px; width: 600px;">북마크정보없음</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td style="border: none; width: 90px;"><input
										type="checkbox" id="all_chkdel" onclick="checkAll()"
										class="all_chkdel">전체선택</td>
									<td style="border: none;" colspan="2"></td>
									<td style="border: none; width: 50px;"><button
											id="chkdelete">선택삭제</button></td>
								</tr>
								<tr>
									<td></td>
									<td style="width: 70px;">카테고리</td>
									<td>질문</td>
									<td style="width: 80px;">북마크날짜</td>
								</tr>
								<c:forEach items="${blist }" var="b">
									<tr>
										<td><input type="checkbox" name="chk_id" id="chk_id"
											value="${b.bookmark_idx }"></td>
										<td>${b.bookmark_category }</td>
										<td><a
											href="onelist_bookmark.do?bookmark_idx=${b.bookmark_idx}">${b.bookmark_question}</a></td>
										<td width="100">(${b.bookmark_reg.substring(0,10)})</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div class="menu4" style="display: block;">
				<center>
					<div
						style="width: 600px; height: 500px; background-color: #2c3e50;; border-radius: 50px; font-weight: bold; font-size: 40px; line-height: 100px;">
						<h1 style="line-height: 5; color: aliceblue;">MY PAGE</h1>
					</div>
					<center>
			</div>
		</div>
		<br> <br> <br> <br>
	</article>

</body>
</html>