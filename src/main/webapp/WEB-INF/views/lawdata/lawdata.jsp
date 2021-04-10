<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보조회</title>
<style type="text/css">
	.table{background-color: white; width: 600px; height: auto; margin: 0 auto; border-radius: 20px; border: 2px solid black; }
	td{
	text-align: center;
	}
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
top: 50%;
display:none;
z-index:200;
}


</style>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){

		$.ajax({
			url:"resources/lawdata/dblaw.xml",
			method:"get",
			dataType:"xml",
			beforeSend: function () {
				FunLoadingBarStart(); //로딩바 생성펑션
				},
			complete: function () {
				FunLoadingBarEnd(); //로딩바 제거
			},

			success: function(data) {	
				var law = "${law}";
				var table = "";
				if (($(data).text()).indexOf(law)!=-1) {
					$(data).find("item").each(function() {
						if(($(this)/* .find("mainCategory") */.text()).indexOf(law)!=-1){
							table+="<form id='post' method='post'>"
						table+="<table class='table'>";
					table+= "<tbody>";
					table+="<tr><td colspan='2'><h2>카테고리</td></tr>";
					table+="<tr><td>"+$(this).find("mainCategory").text()+"</td></tr>";
					table+="<tr><td colspan='2'><h2>질문</td></tr>";
					table+="<tr><td>"+$(this).find("question").text()+"</td></tr>";
					table+="<tr><td colspan='2'><h2>답변</td></tr>";
					table+="<tr><td>"+$(this).find("answer").text()+"</td></tr>";
					table+="<tr><td><input type='button' id='btn' value='저장' /></td></tr>";
				table += "</tbody></table>";
				table+="</form>"
				table +="<br><br>";
						} 
					});
				}else{
					table+="<table>"
					table+= "<tbody>";
					table+="<tr><td><h2>관련된 자료가없습니다.</h2></td></tr>";
				table += "</tbody></table>";
				table +="<br><br>";
				}
				
			$("#result").append(table);
			},
			error: function() {
				alert("읽기실패")
			}
			
		});
		//북마크기능
		
		$(document).on('click','#btn',function() {
			if (${empty sessionScope.loginMember}) {
				alert("로그인해주세요");
				return;
			}else if(${!empty sessionScope.loginMember}){
	 		var save = $(this);
	 		var tbody = save.parent().parent().parent(); 
	 		var tr = tbody.children();
	 		var c = tr.eq(1).text();
	 		var q = tr.eq(3).text();
	 		var a= tr.eq(5).text();
	 		var u = ${sessionScope.loginMember.members_idx};
	 		location.href="insert_bookmark.do?bookmark_answer="+a+"&bookmark_category="+c+"&bookmark_question="+q+"&members_idx="+u;
			}
				
		});
	
	})


</script>
<script type="text/javascript">

/* 로딩바생성펑션 */
function FunLoadingBarStart() {
	var backHeight = $("article").height(); //뒷 배경의 상하 폭
	var backWidth = window.document.body.clientWidth; //뒷 배경의 좌우 폭
	var backGroundCover = "<div id='back'></div>"; //뒷 배경을 감쌀 커버
	var loadingBarImage = ''; //가운데 띄워 줄 이미지
	loadingBarImage += "<div id='loadingBar'>";
	loadingBarImage += " <img src='resources/images/loding.gif'/>"; //로딩 바 이미지
	loadingBarImage += "</div>";
	$('body').append(backGroundCover).append(loadingBarImage);
	$('#back').css({ 'width': backWidth, 'height': backHeight, 'opacity': '0.3' });
	$('#back').show();
	$('#loadingBar').show();
	}
	/* 로딩바없애줄펑션 */
function FunLoadingBarEnd() {
	$('#back, #loadingBar').hide();
	$('#back, #loadingBar').remove();
	}

</script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp" />
<article>
<div id="result"></div>
</article>
</body>
</html>
