<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
</head>
<body>
	<h3>ログアウト</h3>
	<%@include file="./include/menu.jsp" %>
	<c:if test="${empty sessionScope.customer}">
	 	<p>ログインしていません</p>
	 	<a href="/OnlineShop/LoginServlet"></a>
	</c:if>
	 
	<c:if test="${not empty sessionScope.customer }">
	 	<p>ログアウトしますか？<p>
	 <form action="/OnlineShop/LogoutServlet?action=logout" method="post">
	 	<input type="submit" value="ログアウト">
	 </form>
	</c:if>
</body>
</html>