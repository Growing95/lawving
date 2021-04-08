<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Home</title>
<style type="text/css">
html{
 background: linear-gradient( to right, #076585, #fff );
}
.law {
    width: 200px;
    height: 200px;
    float: left;
    color: white;
    background-color: #aed6f1;
    border-radius: 30px;
    font-size: 30;
    font-weight: bold;
    border-color: #b7e1fd;
}
button.law:hover {
    background-color: #2a9fef;
    color: white;
}
#selectlaw{margin: 0 auto; width: 800px; height: 200px;}
#category{padding-top: 300px;}

#search{
    width: 350px;
    text-align: center;
    border-radius: 20px;
}
</style>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
/* 	//최근 등록된 공지글 3개 자동 출력되게 함
	$.ajax({ 
		url: "${ pageContext.request.contextPath }/ntop3.do",
		type: "post",
		dataType: "json",
		success: function(data){
			console.log("success : " + data);
			
			//object ==> string
			var jsonStr = JSON.stringify(data);
			//string ==> json 
			var json = JSON.parse(jsonStr);
			
			var values = "";
			for(var i in json.list){
				values += "<tr><td>" + json.list[i].nid 
						+ "</td><td><a href='${ pageContext.request.contextPath }/ndetail.do?nid="
						+ json.list[i].nid + "'>" 
						+ decodeURIComponent(json.list[i].ntitle).replace(/\+/gi, " ")
						+ "</a></td><td>" + json.list[i].n_create_date
						+ "</td></tr>";
			} //for in
			
			$("#new_notice").html($("#new_notice").html() + values);
		},
		error: function(jqXHR, textstatus, errorthrown){
			console.log("error : " + jqXHR + ", " + textstatus + ", " 
					+ errorthrown);
		}
	});  //notice top3 ajax
	
	//조회수 많은 인기 게시글 3개 자동 출력되게 함
	$.ajax({
		url: "${ pageContext.request.contextPath }/btop3.do",
		type: "post",
		dataType: "json",
		success: function(data){
			console.log("success : " + data);
			
			//object ==> string
			var jsonStr = JSON.stringify(data);
			//string ==> json 
			var json = JSON.parse(jsonStr);
			
			var values = "";
			for(var i in json.list){
				values += "<tr><td>" + json.list[i].bid 
						+ "</td><td><a href='${ pageContext.request.contextPath }/bdetail.do?bid="
						+ json.list[i].bid + "'>" 
						+ decodeURIComponent(json.list[i].btitle).replace(/\+/gi, " ")
						+ "</a></td><td>" + json.list[i].bcount
						+ "</td></tr>";
			} //for in
			
			$("#top_board").html($("#top_board").html() + values);
		},
		error: function(jqXHR, textstatus, errorthrown){
			console.log("error : " + jqXHR + ", " + textstatus + ", " 
					+ errorthrown);
		}
	});
});  //jquery document ready */

$( document ).ready( function() {
    
    $(".law").click(function(){
	var law = $(this).attr('value');
	location.href="list_lawdata.do?law="+law;
    });
});

})
</script>
</head>

<body>
	<c:import url="header.jsp"/>
<%-- 	<hr style="clear:both;">
	<article>
	<section>
	최근 공지글 3개 자동 조회 출력 : ajax 사용
	<div style="float:left; border:1px solid navy; padding: 5px; margin-left: 150px;">
		<h4>새로운 공지글</h4>
		<table id="new_notice" border="1" cellspacing="0">
			<tr><th>번호</th><th>제목</th><th>날짜</th></tr>
		</table>
	</div>
	인기 게시글 3개 자동 조회 출력 : ajax 사용
	<div style="float:left; border:1px solid olive; padding: 5px; margin-left: 5px;">
		<h4>인기 게시글</h4>
		<table id="top_board" border="1" cellspacing="0">
			<tr><th>번호</th><th>제목</th><th>조회수</th></tr>
		</table>
	</div>
	</section>
	</article>
<br style="clear:both;">	 --%>
<article>
<center><img alt="sample" src="resources/images/sample.png" style="border: 1px solid black;"></center>
<div id="category">
<form action="search_lawdata.do" method="post">
<input type="text" id="search" name="search" placeholder="검색할법률을 입력하세요" width="400px;"><input type="submit" value="검색">
</form>
<div id="selectlaw">
<button class="law" value="부동산">부동산</button>
<button class="law" value="상속">상속</button>
<button class="law" value="임금">임금</button>
<button class="law" value="해고">해고</button>
</div>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</article>
</body>
</html>
