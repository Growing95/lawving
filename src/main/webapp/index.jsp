<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#videobcg{
position: absolute;
     top: 0px;
     left: 0px;
     width: 100%;
     height: auto;
     z-index: -1000;
     overflow: hidden;
}
#gobtn{margin-top: 10%;}
#homebtn{margin:auto auto;  width:400px; height: 100px; border: 2px solid white; 
display: inline-block; text-align: center; font-size: 40px; 
line-height: 100px; text-decoration: none;
color: white;
}
img{width: 800px;}
</style>

</head>
<body>
<video id="videobcg" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0">
     <source src="resources/video/meeting.mp4" type="video/mp4">
</video>
<center><img alt="logo" src="resources/images/Lawving.png"></center>
<center id="gobtn"><a id="homebtn" href="home.do">Vist Now</a></center>

</body>
</html>