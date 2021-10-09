<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>幻风阅读会员注册页面</title>
<%@ include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<%--静态包含登陆成功菜单--%>
		<%@ include file="/pages/common/login_success_memu.jsp"%>
		
		<div id="main">
			<h1>注册成功! <a href="index.jsp">转到主页</a></h1>
		</div>
		
		<%@ include file="/pages/common/tail.jsp"%>
</body>
</html>