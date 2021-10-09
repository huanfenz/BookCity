package com.wangpeng.dao;

import com.wangpeng.pojo.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 添加订单
     * @param order
     */
    public int addOrder(Order order);

    List<Order> queryByUserId(int userId);

    List<Order> queryAll();

    int updateStatus(int status, String orderId);
}
