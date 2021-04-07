<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function loginOK(f) {
		f.action = "login.do";
		f.submit();
	}
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/header.jsp" />

	<c:set var="contextPath" value="${ pageContext.servletContext.contextPath }" scope="application"/>

	<h1 align="center">로그인</h1>
	<br>
	<br>
	<br>
	<br>
	
	<div class="outer" align="center">
		<form action="login.do" method="post" id="joinForm">
			<table width="500" cellspacing="5">
				<tr>
					<td align="center"><input type="text" name="id" id="userId" placeholder="ID" required></input></td>
				</tr>
				<tr>
					<td align="center"><input type="password" name="pw" placeholder="PASSWORD" required></td>
				</tr>		
				<tr>
					<td align="center">
						<input type="button" onclick="loginOK(this.form)" value="login">
					</td>
				</tr>
			</table>
		</form>
		<br>
		<br>
		<a href="home.do">시작 페이지로 이동</a>
	</div>
	
	<script type="text/javascript">
		function loginOK(){
			
		}
		
		$(function(){
			$("#userId").on("keyup",function(){
				var userId = $(this).val();
				
				if(userId.length < 5){
					$(".guide").hide();
					$("#idDuplicateCheck").val(0);
					
					return;
				}
				
				$.ajax({
					url:"idCheck.do",
					data:{id:userId},
					type:"post",
					success:function(data){
						console.log(data);
						
						if(data == "ok"){
							$(".error").hide();
							$(".ok").show();
							$("#idDuplicateCheck").val(1);
						}else{
							$(".ok").hide();
							$(".error").show();
							$("#idDuplicateCheck").val(0);
						}
						
					},error:function(jqxhr,textStatus,errorThrown){
						console.log("ajax 처리 실패");
						
						console.log(jqxhr);
						console.log(textStatus);
						console.log(errorThrown);
					}
				});
			});
		});
	
	</script>
</body>
</html>