<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书管理</title>
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			// 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的dom对象。
			//给删除的a标签绑定单击事件，用于删除的确认提示操作
			$("a.deleteClass").click(function () {
				/**
				 * 确认提示框函数
				 * 参数是它的提示内容
				 * 返回true表示点击了确认，返回false表示点击取消。
				 */
				return confirm("你确定要删除[" + $(this).parent().parent().find("td:first").text() + "]?");
				//return false;	// 组织元素的默认行为===不提交请求
			});

		});
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" >
			<span class="wel_word">图书管理系统</span>
		<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getData&id=${book.id}&pageNum=${requestScope.page.pageNum}">修改</a></td>
					<td><a href="manager/bookServlet?action=delete&id=${book.id}&pageNum=${requestScope.page.pageNum}" class="deleteClass">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td><td></td><td></td><td></td><td></td><td></td><td>
				<a href="pages/manager/book_edit.jsp?pageNum=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>
		</table>

		<%@ include file="/pages/common/pageNav.jsp"%>
		
	</div>

	<%@ include file="/pages/common/tail.jsp"%>
</body>
</html>