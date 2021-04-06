<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#toolmenu{list-style-type: none;
padding: 0;
position: fixed;
overflow: auto;
right:15.2%;
bottom:50%;
z-index:10000;
}
#toolmenu li{text-align: center;
display: block;
color: #000;
padding: 8px 15px 8px 15px;
font-weight: bold;
background-color: black;
border-collapse: collapse;
border-top-left-radius: 20px;
border: 1px solid white;
}
article{
width: 70%;
height: auto;
background-color: gray;
margin: 0 auto;
text-align: center;
}
#toolmenu li a{text-decoration: none;
color: white;
font-weight: bold;

}
#log{float: right;
margin-right: 20%;
}
#log a{text-decoration: none; color: blue; font-weight: bold; font-size: 20px;}
</style>
</head>
<body>
<header>
<center><img alt="logo" src="resources/images/Lawving-color1.png"></center>
<c:choose>
<c:when test="${members.members_lev=='2'}">
관리자님|<div id="log"><a href="#">로그아웃</a><br>
<a href="#">회원정보관리</a>
</div>
</c:when>
<c:when test="${members.members_lev=='1' }"><div id="log">${members.members_name }님<br>
<a href="#">로그아웃</a><br>
<a href="#">MY페이지</a>|<a href="#">MY북마크</a></div></c:when>
<c:otherwise>
<div id="log">
<a href="#">로그인</a>|<a href="#">회원가입</a><br>
</div>
</c:otherwise>
</c:choose>
</header>
<ul id="toolmenu">
<li><a href="#">공지사항</a></li>
<li><a href="#">자료실</a></li>
<li><a href="#">Q&A</a></li>
<li><a href="#">챗봇</a></li>
</ul>
<br><br><br>
</body>
</html>