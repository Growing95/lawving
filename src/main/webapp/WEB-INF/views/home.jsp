<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Home</title>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	//최근 등록된 공지글 3개 자동 출력되게 함
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
});  //jquery document ready
</script>
</head>

<body>
	<c:import url="common/menubar.jsp"/>
	<hr style="clear:both;">
	<section>
	<%-- 최근 공지글 3개 자동 조회 출력 : ajax 사용 --%>
	<div style="float:left; border:1px solid navy; padding: 5px; margin-left: 150px;">
		<h4>새로운 공지글</h4>
		<table id="new_notice" border="1" cellspacing="0">
			<tr><th>번호</th><th>제목</th><th>날짜</th></tr>
		</table>
	</div>
	<%-- 인기 게시글 3개 자동 조회 출력 : ajax 사용 --%>
	<div style="float:left; border:1px solid olive; padding: 5px; margin-left: 5px;">
		<h4>인기 게시글</h4>
		<table id="top_board" border="1" cellspacing="0">
			<tr><th>번호</th><th>제목</th><th>조회수</th></tr>
		</table>
	</div>
	</section>
<br style="clear:both;">	
	
	
</body>
</html>
