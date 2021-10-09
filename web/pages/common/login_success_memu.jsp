<%--
  Created by IntelliJ IDEA.
  User: wangpeng
  Date: 2021/8/8
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.png" >
    <span style="font-size: 50px; margin-left: 20px">幻风书城</span>
    <span class="wel_word"></span>
    <div>
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临幻风阅读</span>
        <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="orderServlet?action=list">我的订单</a>
        <a href="index.jsp">返回</a>
    </div>
</div>
