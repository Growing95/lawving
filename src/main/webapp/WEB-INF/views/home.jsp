<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<html>
<head>
<title>LAWVING</title>
<style type="text/css">
article{ background-color: gainsboro;}
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
        height: 30px;
}
.slider div img {width: 100%; height: 100%}
.bx-wrapper{    height: 30%;
   }
#lawbtn{    
	 width: 200px;
    height: 200px;
     font-weight: bold;
     font-size:30px;
    display: inline;
    float:left;
    border-radius: 30px;
    background: #2c3e50;
    color: #fff;
    margin-top: 20px;
    border: solid 2px #212529;
    transition: all 0.5s ease-in-out 0s;}
</style>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {
	 $(".w3-button").click(function(){
	        var law =$(this).val();
	        location.href="list_lawdata.do?law="+law;
	    });
})
</script>
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
    <div><img alt="banner" src="resources/images/banner1.png"></div>
        <div><img alt="banner" src="resources/images/banner3.png"></div>
        <div><img alt="banner" src="resources/images/banner2.png"></div>
 </div>
  <!-- 법률데이터조회부분 -->
<div id="category">
<form action="search_lawdata.do" method="post">
<input type="text" id="search" name="search" placeholder="검색할법률을 입력하세요" width="400px;"><input type="submit" value="검색" style="    height: 30px;
    border-radius: 20px;
    width: 80px;">
</form>
<div id="selectlaw">
<button class="w3-button w3-hover-white w3-ripple mybtn" value="부동산" id="lawbtn">부동산</button>
<button class="w3-button w3-hover-white w3-ripple mybtn" value="상속" id="lawbtn">상속</button>
<button class="w3-button w3-hover-white w3-ripple mybtn" value="임금" id="lawbtn">임금</button>
<button class="w3-button w3-hover-white w3-ripple mybtn" value="해고" id="lawbtn">해고</button>
</div>
</div>
<br><br><br><br><br><br>

</article>

</body>
</html>
