package com.wangpeng.service;

import com.wangpeng.pojo.Cart;
import com.wangpeng.pojo.Order;
import com.wangpeng.pojo.OrderItem;

import java.util.List;

public interface OrderService {

    /**
     * 通过购物车对象生成Order对象
     */
    public void createOrder(String orderId, Cart cart, int userId);

    /**
     * 通过购物车对象生成OrderItem对象
     * @param orderId
     * @param cart
     */
    public List<OrderItem> createOrderItem(String orderId, Cart cart);

    /**
     * 获得该用户的订单信息
     * @return
     */
    List<Order> getList(int userId);

    List<OrderItem> getOrderItem(String orderId);

    List<Order> getListAll();

    void setStatus(String orderId, int status);

    void setSalesAndStock(List<OrderItem> orderItems);
}
