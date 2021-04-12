<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<html>
<head>
<title>LAWVING</title>
<style type="text/css">
article{ background-color: gainsboro;}
.law {
    width: 200px;
    height: 200px;
    float: left;
    color: white;
    background-color: #2c3e50;
    border-radius: 30px;
    border:2px solid;
    font-size: 30;
    font-weight: bold;
    border-color: #ab9e9e;
}
button.law:hover {
    background-color: #808B96;
    color: white;
}
#selectlaw{margin: 0 auto; width: 800px; height: 200px;}
#category{padding-top: 100px;}

#search{
    width: 350px;
    text-align: center;
    border-radius: 20px;
}
.slider div img {width: 100%;}
</style>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {
	 $(".law").click(function(){
	        var law =$(this).val();
	        location.href="list_lawdata.do?law="+law;
	    });
})
</script>
<!--배너관련 스크립트  -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script>
    $(document).ready(function(){
      $('.slider').bxSlider();
    });
</script>
</head>



<body>

	<c:import url="header.jsp"/>

<article>
<!-- 슬라이더 배너부분 이미지 규격은1320*420로 한다. -->
 <div class="slider">
    <div><img alt="banner" src="resources/images/banner3.png"></div>
    <div><img alt="banner" src="resources/images/banner4.png"></div>
    <div><img alt="banner" src="resources/images/banner2.png"></div>
 </div>
  <!-- 법률데이터조회부분 -->
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
<br><br><br><br><br><br>
</article>
</body>
</html>
