<%--
  Created by IntelliJ IDEA.
  User: wangpeng
  Date: 2021/8/10
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <%--页码大于1，才显示首页和上一页--%>
    <c:if test="${requestScope.page.pageNum > 1}">
        <%--首页按钮--%>
        <a href="${requestScope.page.url}&pageNum=1">首页</a>
        <%--上一页按钮--%>
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageNum-1}">上一页</a>
    </c:if>
    <%--总页码<=5的情况，页码范围：1-总页码--%>
    <c:if test="${requestScope.page.pageTotal <= 5}">
        <c:set var="begin" value="1"/>
        <c:set var="end" value="${requestScope.page.pageTotal}"/>
    </c:if>
    <%--总页码>5的情况，分成三种情况--%>
    <c:if test="${requestScope.page.pageTotal > 5}">
        <c:choose>
            <%--当前页面是前三个，页码范围1-5--%>
            <c:when test="${requestScope.page.pageNum <= 3}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="5"/>
            </c:when>
            <%--当前页面是后三个，页码范围total-4~total--%>
            <c:when test="${requestScope.page.pageNum >= requestScope.page.pageTotal - 2}">
                <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                <c:set var="end" value="${requestScope.page.pageTotal}"/>
            </c:when>
            <%--中间情况，范围num-2,num,num+2--%>
            <c:otherwise>
                <c:set var="begin" value="${requestScope.page.pageNum-2}"/>
                <c:set var="end" value="${requestScope.page.pageNum+2}"/>
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNum}">
            <span style="color: red">${i}</span>
        </c:if>
        <c:if test="${i != requestScope.page.pageNum}">
            <a href="${requestScope.page.url}&pageNum=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码<total，才显示末页和下一页--%>
    <c:if test="${requestScope.page.pageNum < requestScope.page.pageTotal}">
        <%--下一页按钮--%>
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageNum+1}">下一页</a>
        <%--末页按钮--%>
        <a href="${requestScope.page.url}&pageNum=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.itemsCount}条记录 到第<input value="${param.pageNum}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            // 跳到指定的页码
            $("#searchPageBtn").click(function () {
                var pageNum = $("#pn_input").val();
                var pageTotal = ${requestScope.page.pageTotal};
                if(pageNum < 1 || pageNum > pageTotal) return false;
                location.href = "${requestScope.page.url}&pageNum="+pageNum;
            });
        });
    </script>
</div>
