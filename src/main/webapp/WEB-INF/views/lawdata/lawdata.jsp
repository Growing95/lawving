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
	
</style>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	
		$.ajax({
			url:"lawdata.do",
			method:"get",
			dataType:"xml",
			success: function(data) {	
				var law = "${law}";
				var table = "";
				if (($(data).text()).indexOf(law)!=-1) {
					$(data).find("item").each(function() {
						if(($(this)/* .find("mainCategory") */.text()).indexOf(law)!=-1){
							table+="<form id='post' method='post'>"
						table+="<table class='table'>";
					table+= "<tbody>";
					table+="<tr><td><h2>카테고리</h2>"+$(this).find("mainCategory").text()+"</td></tr>";
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
	
	})


</script>
</head>
<body>
<c:import url="/WEB-INF/views/header.jsp" />
<article>
<div id="result"></div>
</article>
</body>
</html>