<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>书城首页</title>
	<%@ include file="/pages/common/head.jsp"%>
	<meta name="referrer" content="no-referrer">

	<script type="text/javascript">
		$(function () {
			$("button.addCart").click(function () {
				var bookId = $(this).attr("bookId");
				$.getJSON("${basePath}cartServlet","action=ajaxAdd&id="+bookId,function (data) {
					$("#cartCount").text("您的购物车中有" + data.count + "件商品");
					$("#cartlastName").text("您刚刚将【" + data.lastName + "】加入到了购物车中");
				});
				
			});
		})
	</script>

</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" >
<!--			<span class="wel_word">网上书城</span>-->
		<a href="index.jsp" style="text-decoration: none">
			<span style="font-size: 50px; margin-left: 20px">幻风书城</span>
		</a>
			<div>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a>
				</c:if>
				<c:if test="${not empty sessionScope.user}">
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临幻风阅读</span>
					<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
					<a href="orderServlet?action=list">我的订单</a>
				</c:if>
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${not empty sessionScope.cart.count}">
					<span id="cartCount">您的购物车中有${sessionScope.cart.count}件商品</span>
				</c:if>
				<c:if test="${empty sessionScope.cart.count}">
					<span id="cartCount">您的购物车为空!</span>
				</c:if>
				<div>
					<c:if test="${not empty sessionScope.lastName}">
						<span style="color: red" id="cartlastName">您刚刚将【${sessionScope.lastName}】加入到了购物车中</span>
					</c:if>
					<c:if test="${empty sessionScope.lastName}">
						<span style="color: red" id="cartlastName">您还没有添加商品，请快选购吧!</span>
					</c:if>
				</div>
			</div>

			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="图书图片" src="${book.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button class="addCart" bookId="${book.id}" pageNum="${param.pageNum}">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		<%@ include file="/pages/common/pageNav.jsp"%>
	</div>

	<%@ include file="/pages/common/tail.jsp"%>
</body>
</html>