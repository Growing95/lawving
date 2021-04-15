<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
	span.guide {display:none; font-size:12px; top:12px, right:10px;}
	span.ok{color:green;}
	span.error{color:red;}
	/* 로딩바스타일 */
	#back{
position: absolute;
z-index: 100;
background-color: #000000;
display:none;
left:100%;
top:100%;
}
#loadingBar{
position:absolute;
left:43%;
top: 25%;
display:none;
z-index:200;
}
.mybtn{
  width:140px;
  height:40px;
  padding:0;
  display:inline; 
  border-radius: 4px; 
  background: #212529;
  color: #fff;
  border: solid 2px #212529; 
  transition: all 0.5s ease-in-out 0s;
}
.mybtn:hover .mybtn:focus {
  background: white;
  color: #212529;
  text-decoration: none;
}
#msg{display: block; margin-top: 20px;}
.w3-input{width: 300px; display: inline-block; margin-top: 20px;}
button{background: #212529;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
//전송값 유효성 검사 체크용 함수
//validation(유효성 검사): 서버즉 컨트롤러로 전송할 값들이 요구한 조건을 모두 
//						   만족하였는지 검사하는 것
function validate() {
	//암호와 암호확인이 일치하는지 검사
	var pwdValue = document.getElementById("userpwd").value;
	var pwdValue2 = document.getElementById("userpwd2").value;
	var idcheck = document.getElementById("idcheck").value;
	var emailcheck=document.getElementById("emailcheck").value;
	if (pwdValue !== pwdValue2) {
		alert("암호와 암호확인의 입력값이 일치하지 않습니다.");
		document.getElementById("userpwd2").select();
		return false;//전송취소처리
	}
	if(idcheck==1&&emailcheck==1){
	return true; //전송 처리
		
	}else if (idcheck!=1) {
		alert("아이디 중복확인이 필요합니다.");
		return false;
	}else if(emailcheck!=1){
		alert("이메일 인증확인이 필요합니다.");
		return false;
	}
	
}
//id중복확인용 함수
function dupidCheck(){
	$("#msg").empty();
	$.ajax({
		url:"idCheck.do",
		method:"post",
		data:{members_id: $("#userid").val()},
		success: function(data) {
			console.log("success:"+data);
			if (data=="ok") {
				$("#msg").append("<p style='color: lightgreen;'>사용가능 아이디입니다.</p>");
				$("#name").focus();
				$('#idcheck').val('1');
			}else{
				$("#msg").append("<p style='color: red;'>사용중인 아이디입니다.</p>");
				$('#userid').val('');
				$("#userid").focus();
			}
		},
		error: function(jqXHR,textstatus,errorthrown) {
			console.log("확인실패");
		}
		
	});//$.ajax();
	
	//클릭 이벤트가 전달되어서 submit이 동작되지 않게 함
	return false;//클릭 이벤트 취소되게 함
	
	//function dupidCheck()
}
</script>
<script type ="text/javascript" src="${ pageContext.request.contextPath }/resources/js/loadingbar.js"> </script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script>
$(function(){
	$("#sendmail").click(function(){
		$.ajax({
			url : "email.do",
			type : "POST",
			data : {
				members_email : $("#members_email").val()
			},
			beforeSend: function () {
				FunLoadingBarStart(); //로딩바 생성펑션
				},
			complete: function () {
				FunLoadingBarEnd(); //로딩바 제거
			},
			success : function(data) {
				if (data=="no") {
					alert("이미존재하는 이메일 입니다.");
				}else{
					alert("인증번호가 이메일로 전송되었습니다.")
					localStorage.setItem('emailcode', data);
				}
			},
		})
	});
	$("#codeok").click(function() {
		var code = $("#emailcode").val();
		if(code==(localStorage.getItem("emailcode"))){
			alert("인증성공");
			$('#emailcheck').val('1');
			localStorage.clear();
		}else{
			alert("인증실패");
			return;
		}
	});
})
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/header.jsp" />
<!-- 	
	<h1 align="center">회원가입</h1>
	
	<div class="outer" align="center">
		<form  action="anroll.do" method="post" onsubmit="return validate()">
			<table width="500" cellspacing="5">
				<tr><td id="msg" width="150">*아이디</td><td><input type="text" name="members_id" id="userid" required> &nbsp;<input type="button" value="중복체크"onclick="return dupidCheck();">
				<input type="hidden" id="idcheck" value="0">
				<input type="hidden" id="emailcheck" value="0">
				</td></tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="members_name" id="name" required ></input></td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="members_pw" id="userpwd" required></td>
				</tr>
				<tr>
					<td>* 비밀번호확인</td>
					<td><input type="password" name="pw2" id="userpwd2" required></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="members_email" id="members_email"><button type="button" id="sendmail">이메일인증</button></td>
				</tr>
				<tr>
					<td>인증코드</td>
					<td><input type="text" name="emailcode" id="emailcode"><button type="button" id="codeok">인증코드확인</button></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="number" name="members_birth" placeholder="yyyymmdd"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="tel" name="members_tel" id="members_tel"></td>
				</tr>
				jQuery와 Postcodify를 로딩한다.
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원가입">
						&nbsp;
						<input type="reset" value="취소하기">
					</td>
				</tr>
			</table>
		</form> -->
		<!--  -->
		<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4 w3-auto" style="width: 600px;height: auto;">
			<form  action="anroll.do" method="post" onsubmit="return validate()">
			<input type="hidden" id="idcheck" value="0">
				<input type="hidden" id="emailcheck" value="0">
			<div class="w3-center w3-large w3-margin-top">
				<h3>회원가입</h3>
			</div>
			<div style="padding: 50px;">
				<p>
					<label id="msg">*아이디</label>
					<input type="text" name="members_id" placeholder="아이디를 입력하세요" id="userid" class="w3-input" required>&nbsp;
					<input type="button" value="중복체크" class="w3-button w3-hover-white w3-ripple mybtn" onclick="return dupidCheck();">
				</p>
				<p>
					<label id="msg">* 이름</label>
					<input type="text" name="members_name" placeholder="이름을 입력하세요" class="w3-input" id="name" required ></input>
				</p>
				<p>
					<label id="msg">* 비밀번호</label>
					<input type="password" name="members_pw" placeholder="패스워드를 입력하세요" class="w3-input" id="userpwd" required>
				</p>
				<p>
					<label id="msg">* 비밀번호확인</label>
					<input type="password" name="pw2" placeholder="패스워드를 입력하세요" class="w3-input" id="userpwd2" required>
				</p>
				<p>
					<label id="msg">* 이메일</label>
					<input type="email" name="members_email" placeholder="이메일을 입력하세요" class="w3-input" id="members_email">&nbsp;
					<button type="button" class="w3-button w3-hover-white w3-ripple mybtn" id="sendmail">이메일인증</button>
				</p>
				<p>
					<label id="msg">* 인증코드</label>
					<input type="text" name="emailcode" placeholder="발송된 인증번호를 입력하세요" class="w3-input" id="emailcode">&nbsp;
					<button type="button" class="w3-button w3-hover-white w3-ripple mybtn" id="codeok">인증코드확인</button>
				</p>
				<p>
					<label id="msg">생년월일</label>
					<input type="number" placeholder="출생년도를 입력하세요" name="members_birth" class="w3-input" placeholder="yyyymmdd">
				</p>
				<p>
					<label id="msg">전화번호</label>
					<input type="tel" name="members_tel" placeholder="전화번호를 입력하세요" class="w3-input" id="members_tel">
				</p>
				</p>
					<label id="msg">우편번호</label>
					<input type="text" style="width:305px;" name="members_post" id="members_post" class="postcodify_postcode5 w3-input"   placeholder="우편번호를 입력하세요.">&nbsp;
					<button type="button" class="w3-button w3-hover-white w3-ripple mybtn" id="postcodify_search_button">검색</button>
				</p>
				<p>
					<label id="msg">도로명 주소</label>
					<input type="text" style="width:305px;" name="members_addr1" id="members_addr1" class="postcodify_address w3-input" placeholder="도로명 주소입력란">
				</p>
				<p>
					<label id="msg">상세 주소</label> 
					<input type="text" style="width:305px;" name="members_addr2" id="members_addr2" class="postcodify_extra_info w3-input" placeholder="상세주소 입력란">
				</p>
				<br>
				<p class="w3-center">
					<input type="submit" id="findBtn" class="w3-button w3-hover-white w3-ripple mybtn" value="회원가입">
					<input type="reset" class="w3-button w3-hover-white w3-ripple mybtn" value="취소하기">
				</p>
				<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
				<script>
					/*  검색 단추를 누르면 팝업 레이어가 열리도록 설정한다. */
					$(function(){
						$("#postcodify_search_button").postcodifyPopUp();
					});
				</script>
			</div>
				</form>
		</div>
	</div>
		<br>
		<br>
	</div>
</body>
</html>