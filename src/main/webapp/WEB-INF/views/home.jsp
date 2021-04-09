<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false" %>
<html>
<head>
<title>Home</title>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>

</head>

<body>

	<c:import url="header.jsp"/>

<article>
<form action="search_lawdata.do">
<input type="text" id="search"><input type="submit" value="검색">
</form>
<button class="law" value="부동산">부동산</button>
<button class="law" value="상속">상속</button>

</article>

</body>
</html>
