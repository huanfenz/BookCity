<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("input.in_count").change(function () {
				var count = $(this).attr("value");
				var bookId = $(this).attr("bookId");
				location.href="${basePath}cartServlet?action=update&id="
						+bookId+"&count="+count;
			});
			$("a.delete").click(function () {
				var bookName = $(this).attr("bookName");
				return confirm("你确定要删除【" + bookName + "】吗？");
			});
			$("#a_clear").click(function () {
				return confirm("你确定要清空购物车吗？");
			});
		});
	</script>
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
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.cart.items}" var="item">
				<tr>
					<td>${item.value.name}</td>
					<td><input type="text" class="in_count" bookId="${item.value.id}" value="${item.value.count}"></td>
					<td>${item.value.price}</td>
					<td>${item.value.totalPrice}</td>
					<td><a class="delete" bookName="${item.value.name}"
						   href="cartServlet?action=delete&id=${item.value.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.count}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.price}</span>元</span>
			<span class="cart_span"><a id="a_clear" href="cartServlet?action=clear">清空购物车</a></span>
			<span class="cart_span">
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.jsp">去结账</a>
				</c:if>
				<c:if test="${not empty sessionScope.user}">
					<a href="orderServlet?action=add">去结账</a>
				</c:if>
			</span>
		</div>
	
	</div>

	<%@ include file="/pages/common/tail.jsp"%>
</body>
</html>