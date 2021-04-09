<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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

</head>

<body>

	<c:import url="header.jsp"/>

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
