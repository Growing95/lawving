<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	<meta charset="UTF-8"/>
	<style type="text/css">


	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="resources/js/sockjs.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#chatForm").submit(function(event){
				event.preventDefault();
				sock.send($("#message").val());
				$("#message").val('').focus();
			});
		});
		var sock = new SockJS("/echo");
		sock.onmessage = function(e){
			$("#chat").append(e.data + "<br/>");
		} 
		
		/*
		sock.onclose = function(){
			$("#chat").append("연결 종료");
		} */
		$(function() {
			$("#btn1").click(function(){
				$('html, body').scrollTop( $('#chat').height() ); 
			});
			
		})

	</script>

</head>
<body>
	<div id="chat" style="margin-bottom:0px; padding-bottom:0px;background-color: white;">
	
	</div>
	<br><br><br><br><br><br><br>
	<div id="chatForm" style="position:fixed;bottom:20px;width: 490px; height:30px; left:10px;">
	<hr>
		<form>  
			<input type="text" style="width:300px;height: 30px;" id="message" />
			<button id="btn1" style="width: 100px; height: 30px;">send!</button>
		</form>
	</div>
</body>
</html>