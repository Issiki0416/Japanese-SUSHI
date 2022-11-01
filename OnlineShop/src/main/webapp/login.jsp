<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<jsp:include page="./include/menu.jsp"/>
	<h1>ログイン</h1>
	<p>${message}</p>
	<c:if test="${empty sessionScope.customer}">
	<form action="/OnlineShop/LoginServlet" method="post">
		ユーザーID <input type="text" name="name"><br>
		パスワード<input type="password" name="password"><br>
		<input type="hidden" name="action" value="login">
		<input type="submit" value="ログイン">
	</form>
	</c:if>
	<c:if test="${not empty sessionScope.customer}">
	<p>ログイン済です</p>
	</c:if>
	<hr>
	<a href="./index.jsp">トップへ戻る</a>
	<%@include file="./include/footer.html" %>
</body>
</html>