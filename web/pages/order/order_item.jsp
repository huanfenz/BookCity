<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
	<%@ include file="/pages/common/head.jsp"%>
</head>

<body>
	<%--静态包含登陆成功菜单--%>
	<%@ include file="/pages/common/login_success_memu.jsp"%>

	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
			</tr>
			<c:forEach items="${requestScope.orderItems}" var="item">
				<tr>
					<td>${item.name}</td>
					<td>${item.count}</td>
					<td>${item.price}</td>
					<td>${item.totalPrice}</td>
				</tr>
			</c:forEach>
		</table>

		<div class="cart_info">
			<span class="cart_span">当前订单编号为：${requestScope.orderId}</span>&nbsp&nbsp
			<a href="orderServlet?action=list">返回我的订单</a>
		</div>
	
	</div>

	<%@ include file="/pages/common/tail.jsp"%>
</body>
</html>
