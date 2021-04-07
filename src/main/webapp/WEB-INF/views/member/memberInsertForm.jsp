<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	span.guide {display:none; font-size:12px; top:12px, right:10px;}
	span.ok{color:green;}
	span.error{color:red;}
</style>
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
//전송값 유효성 검사 체크용 함수
//validation(유효성 검사): 서버즉 컨트롤러로 전송할 값들이 요구한 조건을 모두 
//						   만족하였는지 검사하는 것
function validate() {
	//암호와 암호확인이 일치하는지 검사
	var pwdValue = document.getElementById("userpwd").value;
	var pwdValue2 = document.getElementById("userpwd2").value;
	
	if (pwdValue !== pwdValue2) {
		alert("암호와 암호확인의 입력값이 일치하지 않습니다.");
		document.getElementById("userpwd2").select();
		return false;//전송취소처리
	}
	return true; //전송 처리
}
//id중복확인용 함수
function dupidCheck() {
	$("#msg").empty();
	$.ajax({
		url:"idCheck.do",
		method:"post",
		data:{members_id:$("#userid").val() },
		success: function(data) {
			console.log("success:"+data);
			if (data=="ok") {
				$("#msg").append("<p style='color: lightgreen;'>사용가능 아이디입니다.</p>");
				$("#userpwd").focus();
			}else{
				$("#msg").append("<p style='color: red;'>사용중인 아이디입니다.</p>");
				$("#userid").select();
			}
		},
		error: function(jqXHR,textstatus,errorthrown) {
			console.log("error:"+jqXHR+","+textstatus+","+arrorthrown);
		}
		
	});//$.ajax();
	
	//클릭 이벤트가 전달되어서 submit이 동작되지 않게 함
	return false;//클릭 이벤트 취소되게 함
	
	//function dupidCheck()
}
</script>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	
	<h1 align="center">회원가입</h1>
	
	<div class="outer" align="center">
		<form  action="anroll.do" method="post" onsubmit="return validate()">
			<table width="500" cellspacing="5">
				<tr><td id="msg" width="150">*아이디</td><td><input type="text" name="members_id" id="userid" required> &nbsp;<input type="button" value="중복체크" onclick="return dupidCheck()"></td></tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="members_name" required ></input></td>
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
					<td><input type="email" name="members_email"></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="number" name="members_birth" placeholder="yyyymmdd"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="tel" name="members_tel" ></td>
				</tr>
				<!-- jQuery와 Postcodify를 로딩한다. -->
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원가입">
						&nbsp;
						<input type="reset" value="취소하기">
					</td>
				</tr>
			</table>
		</form>
		<br>
		<br>
		<a href="home.do">시작 페이지로 이동</a>
	</div>
	
	

</body>
</html>