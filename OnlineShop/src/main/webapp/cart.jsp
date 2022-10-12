<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
</head>
<body>
	<jsp:include page="./include/menu.jsp"/>
	<h3>カートに入れた商品</h3>
	<br>
	<c:if test="${empty cart.items}">
		<p>カートは空です</p>
	</c:if>
	
	<c:if test="${not empty cart.items}">
	    <c:forEach items="${cart.items}" var="item">
			<tr>
				<td>商品ID:${item.id}</td>
				<td><img src="image/${item.id}.jpg" height="96"></td>
				<td>${item.name}</td>
				<td>${item.price}円</td>
				<form action="/OnlineShop/CartServlet?action=delete" method="post">
					<td><input type="submit" value="削除" /></td>
					<input type="hidden" name="item_id" value="${item.id}">
				</form>
			</tr>
		</c:forEach>
	</c:if>
</body>
</html>