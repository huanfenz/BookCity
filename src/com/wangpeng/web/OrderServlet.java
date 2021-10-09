package com.wangpeng.web;

import com.wangpeng.pojo.Cart;
import com.wangpeng.pojo.Order;
import com.wangpeng.pojo.OrderItem;
import com.wangpeng.pojo.User;
import com.wangpeng.service.BookService;
import com.wangpeng.service.OrderService;
import com.wangpeng.service.impl.BookServiceImpl;
import com.wangpeng.service.impl.OrderServiceImpl;
import com.wangpeng.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{

    OrderService orderService = new OrderServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户信息
        User user = (User) req.getSession().getAttribute("user");
        //生成订单编号
        String orderId = System.currentTimeMillis() + "" + user.getId();
        //获取购物车信息
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //创建订单
        orderService.createOrder(orderId, cart, user.getId());
        //创建订单项
        List<OrderItem> orderItems = orderService.createOrderItem(orderId,cart);
        //处理销量和库存
        orderService.setSalesAndStock(orderItems);
        //req带下id
        req.setAttribute("orderId",orderId);
        //请求转发
        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Order> orders = orderService.getList(user.getId());
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
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
        req.getRequestDispatcher("/pages/order/order_item.jsp").forward(req,resp);
    }
}
