<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("select.status").change(function () {
				$(this).parent().submit();
			});
		});
	</script>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" >
			<span class="wel_word">订单管理系统</span>
		<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>用户编号</td>
				<td>订单编号</td>
				<td>日期</td>
				<td>金额</td>
				<td>商品数量</td>
				<td>详情</td>
				<td>状态</td>
				
			</tr>
			<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.userId}</td>
					<td>${order.orderId}</td>
					<td>${order.date}</td>
					<td>${order.price}</td>
					<td>${order.count}</td>
					<td><a href="manager/orderServlet?action=itemList&orderId=${order.orderId}">查看详情</a></td>
					<td>
						<%--下拉框--%>
						<form class="status" action="manager/orderServlet" method="get">
							<input type="hidden" name="action" value="setStatus">
							<input type="hidden" name="orderId" value="${order.orderId}">
							<select class="status" name="status">
								<c:if test="${order.status==0}">
									<option value="0" selected="selected">未发货</option>
									<option value="1">已发货</option>
									<option value="2">已签收</option>
								</c:if>
								<c:if test="${order.status==1}">
									<option value="0">未发货</option>
									<option value="1" selected="selected">已发货</option>
									<option value="2">已签收</option>
								</c:if>
								<c:if test="${order.status==2}">
									<option value="0">未发货</option>
									<option value="1">已发货</option>
									<option value="2" selected="selected">已签收</option>
								</c:if>
							</select>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%@ include file="/pages/common/tail.jsp"%>
</body>
</html>