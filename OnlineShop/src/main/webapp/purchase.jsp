<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="./include/menu.jsp"/>
	<h3>カートに入れた商品</h3>
	<c:if test="${empty sessionScope.customer}">
		<p>購入するにはログインしてください</p>
	</c:if>
	
	<c:if test="${not empty sessionScope.customer}">
		<h3></h3>
	    <c:forEach items="${cart.items}" var="item">
			<tr>
				<td>商品ID:${item.id}</td>
				<td><img src="image/${item.id}.jpg" height="96"></td>
				<td>${item.name}</td>
				<td>${item.price}円</td>
				<br>
			</tr>
		</c:forEach>
		<form action="/OnlineShop/PurchaseServlet?action=purchase" method="post">
			 <p>名前<input type="text" name="name"></p>
	         <p>住所<input type="text" name="address"></p>
	         <!-- <p><!-- e-mail<input type="email" name="email"></p> -->
	         <input type="submit" value="購入">
		</form>
	</c:if>

</body>
</html>