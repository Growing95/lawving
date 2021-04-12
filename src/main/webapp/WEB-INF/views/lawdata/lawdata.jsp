<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보조회</title>
<script type ="text/javascript" src="${ pageContext.request.contextPath }/resources/js/loadingbar.js"> </script>
<style type="text/css">
.table a{text-decoration: none;
    font-size: 10px;}
/* TOP버튼css */
a#MOVE_TOP_BTN {
    position: fixed;
    right: 30%;
    bottom: 50px;
    display: none;
    z-index: 999;
}
	.table{background-color: #27496b; width: 600px; height: auto; margin: 0 auto; border-radius: 20px; border: 2px solid black; color: white; }
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
#save{float: left;}
h2{background: darkgray;
    border-radius: 20px;}

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
					table+="<tr><td colspan='2'><a id='save' height='32' width='32' style='cursor: pointer;' ><img alt='bookmark' src='resources/images/bookmarks.png'><br>북마크추가</a><h2>카테고리</td></tr>";
					table+="<tr><td style='font-size: 30px;'>"+$(this).find("mainCategory").text()+"</td></tr>";
					table+="<tr><td colspan='2'><h2>질문</td></tr>";
					table+="<tr><td style='font-weight: bold;'>"+$(this).find("question").text()+"</td></tr>";
					table+="<tr><td colspan='2'><h2>답변</td></tr>";
					table+="<tr><td style='color:;font-weight: bold; '>"+$(this).find("answer").text()+"</td></tr>";
				table += "</tbody></table>";
				table+="</form>"
				table +="<br><br>";
						} 
					});
				}else{
					table+="<table class='table'>";
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
		
		$(document).on('click','#save',function() {
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
	 		var u = ${loginMember.members_idx}
	 		//location.href="insert_bookmark.do?bookmark_answer="+a+"&bookmark_category="+c+"&bookmark_question="+q+"&members_idx="+u;
	 		$.ajax({
				url:"insert_bookmark.do",
				method:"post",
				data:"bookmark_answer="+a+"&bookmark_category="+c+"&bookmark_question="+q+"&members_idx="+u,
				dataType:"text",
				beforeSend: function () {
					FunLoadingBarStart(); //로딩바 생성펑션
					},
				complete: function () {
					FunLoadingBarEnd(); //로딩바 제거
				},
				success: function(data) {	
					if (data.trim() >= '1') {
						alert("북마크가저장되었습니다.");
					} else {
						alert("북마크저장을 실패하였습니다.");
					}
				},
				error: function() {
					alert("북마크저장에러")
				}
				
			});
			}
		});
	})
</script>
<!-- <script type="text/javascript">

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

</script> -->
</head>
<body>

<c:import url="/WEB-INF/views/header.jsp" />
<article>
<div id="result"></div>
<a id="MOVE_TOP_BTN" href="#"><img alt="top" src="resources/images/top.png"> </a>
</article>
<a height="32" width="32" ></a>
</body>
<script>
//탑버튼 스크립트
    $(function() {
        $(window).scroll(function() {
            if ($(this).scrollTop() > 500) {
                $('#MOVE_TOP_BTN').fadeIn();
            } else {
                $('#MOVE_TOP_BTN').fadeOut();
            }
        });
        
        $("#MOVE_TOP_BTN").click(function() {
            $('html, body').animate({
                scrollTop : 0
            }, 400);
            return false;
        });
    });
 
</script>
</html>
