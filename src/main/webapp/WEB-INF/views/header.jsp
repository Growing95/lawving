<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Abril+Fatface&family=Lobster&display=swap');
#loginUI{float:right;
margin-top:-150px;
z-index: 5000;
position: relative;
}
#toolmenu{list-style-type: none;
padding: 0;
position: fixed;
overflow: auto;
right:15.2%;
bottom:50%;
z-index:10000;
}
#toolmenu li{text-align: center;
display: list-item;
color: #000;
padding: 8px 15px 8px 15px;
font-weight: bold;
background-color: #AED6F1;
border-collapse: collapse;
border-top-left-radius: 20px;
border: 1px solid white;
}
article{
width: 70%;
margin: 0 auto;
text-align: center;
}
#toolmenu li a{text-decoration: none;
color: white;
font-weight: bold;
}
#toolmenu li:hover{background-color: #1E90FF; border-color:#1E90FF; }

#log a{text-decoration: none;color:black; font-weight: bold;}
</style>
</head>
<body>
<header>
<center><a href="home.do"><img alt="logo" src="resources/images/Lawving-color1.png"></a></center>
<div id="loginUI">
<c:choose>
<c:when test="${loginMember.members_lev=='2'}">
<div id="log"><span style="font-weight: bold;">관리자</span>&nbsp;${loginMember.members_name }님|<a href="logout.do">로그아웃</a><br>
<a href="memberslist.do" style="font-size:none;color: black;">회원정보관리</a>
</div>
</c:when>
<c:when test="${loginMember.members_lev=='1' }">
<div id="log">
${loginMember.members_name }님|&nbsp;
<span style="color: red;">누적신고수 : <%-- ${limit.limit_count} --%>0</span>회<br>
<a href="logout.do">로그아웃</a><br>
<a href="list_mypage.do">MY페이지</a>|<a href="#">MY북마크</a></div></c:when>
<c:otherwise>
<div id="log">
<a href="go_login.do">로그인</a>|<a href="go_signup.do">회원가입</a><br>
</div>
</c:otherwise>
</c:choose>
</div>
</header>
<c:url var="nlist" value="nlist.do" />		
<c:url var="llist" value="llist.do" />		
<ul id="toolmenu">

<li><a href="${ nlist }">공지사항</a></li>
<li><a href="${ llist }">자료실</a></li>
<li><a href="#">Q&A</a></li>
<li><a href="#">챗봇테스트</a></li>

</ul>
<br><br><br>
</body>
</html>