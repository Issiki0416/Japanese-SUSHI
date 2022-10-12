<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online Shop</title>
</head>
<body>
	<h1>オンラインショップへようこそ</h1>
	<%@include file="./include/menu.jsp" %>
	<p>${message}</p>
	<%@include file="./include/footer.html" %>
</body>
</html>