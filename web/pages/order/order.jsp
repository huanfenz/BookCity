<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%@ include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	<%--静态包含登陆成功菜单--%>
	<%@ include file="/pages/common/login_success_memu.jsp"%>
	
	<div id="main">
		
		<table>
			<tr>
				<td>订单编号</td>
				<td>日期</td>
				<td>金额</td>
				<td>商品数量</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.date}</td>
					<td>${order.price}</td>
					<td>${order.count}</td>
					<td>
						<c:if test="${order.status==0}">未发货</c:if>
						<c:if test="${order.status==1}">已发货</c:if>
						<c:if test="${order.status==2}">已签收</c:if>
					</td>
					<td><a href="orderServlet?action=itemList&orderId=${order.orderId}">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
		
	
	</div>

	<%@ include file="/pages/common/tail.jsp"%>
</body>
</html>