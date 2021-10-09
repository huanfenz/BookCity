package com.wangpeng.web;

import com.wangpeng.pojo.Order;
import com.wangpeng.pojo.OrderItem;
import com.wangpeng.pojo.User;
import com.wangpeng.service.OrderService;
import com.wangpeng.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MOrderServlet extends BaseServlet{

    OrderService orderService = new OrderServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Order> orders = orderService.getListAll();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    protected void itemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取订单编号
        String orderId = req.getParameter("orderId");
        //查询该订单的所有OrderItem对象
        List<OrderItem> orderItems = orderService.getOrderItem(orderId);
        //存
        req.setAttribute("orderItems",orderItems);
        req.setAttribute("orderId",orderId);
        //请求转发
        req.getRequestDispatcher("/pages/manager/order_manager_item.jsp").forward(req,resp);
    }

    protected void setStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取订单编号
        String orderId = req.getParameter("orderId");
        int status = Integer.parseInt(req.getParameter("status"));
        orderService.setStatus(orderId, status);
        List<Order> orders = orderService.getListAll();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }
}
