package com.wangpeng.service.impl;

import com.wangpeng.dao.BookDao;
import com.wangpeng.dao.OrderDao;
import com.wangpeng.dao.OrderItemDao;
import com.wangpeng.dao.impl.BookDaoImpl;
import com.wangpeng.dao.impl.OrderDaoImpl;
import com.wangpeng.dao.impl.OrderItemDaoImpl;
import com.wangpeng.pojo.*;
import com.wangpeng.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    BookDao bookDao = new BookDaoImpl();

    @Override
    public void createOrder(String orderId, Cart cart, int userId) {
        Date date = new Date();
        Order order = new Order(orderId,date,cart.getPrice(),cart.getCount(),0,userId);
        //订单写进数据库
        orderDao.addOrder(order);
    }

    @Override
    public List<OrderItem> createOrderItem(String orderId, Cart cart) {
        List<OrderItem> res = new ArrayList<OrderItem>();
        // 遍历cart.items
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getId(),cartItem.getName(),
                    cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            res.add(orderItem);
            //订单项写进数据库
            orderItemDao.addOrderItem(orderItem);
        }
        return res;
    }

    @Override
    public List<Order> getList(int userId) {
        List<Order> orders = orderDao.queryByUserId(userId);
        return orders;
    }

    @Override
    public List<OrderItem> getOrderItem(String orderId) {
        return orderItemDao.queryByOrderId(orderId);
    }

    @Override
    public List<Order> getListAll() {
        List<Order> orders = orderDao.queryAll();
        return orders;
    }

    @Override
    public void setStatus(String orderId, int status) {
        orderDao.updateStatus(status, orderId);
    }

    @Override
    public void setSalesAndStock(List<OrderItem> orderItems) {
        //遍历所有订单项
        for(OrderItem orderItem : orderItems) {
            //查询该图书，获得Book对象
            Book book = bookDao.queryBookById(orderItem.getBookId());
            //获取该项的count
            int count = orderItem.getCount();
            //销量+count
            book.setSales(book.getSales() + count);
            //库存-count
            book.setStock(book.getStock() - count);
            //更新数据库
            bookDao.updateBook(book);
        }
    }
}
