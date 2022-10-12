<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>
	<jsp:include page="./include/menu.jsp"/>
	<h3>商品一覧</h3>
	<c:forEach items="${items}" var="item">
		<form action="/OnlineShop/CartServlet?action=add" method="post">
			<input type="hidden" name="item_id" value="${item.id}">
			<tr>
				<td>商品${item.id} </td>
				<td><img src="image/${item.id}.jpg" height="64"></td>
				<td>${item.name}</td>
				<td>${item.price}</td>
				<td><input type="submit" value="カートに追加"></td>
			</tr>
		</form>
	</c:forEach>
	<br>
	<br>
	<a href="./index.jsp">トップへ戻る</a>
</body>
</html>