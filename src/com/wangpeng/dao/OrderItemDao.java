package com.wangpeng.dao;

import com.wangpeng.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {

    /**
     * 添加订单项
     * @param orderItem
     */
    public int addOrderItem(OrderItem orderItem);

    List<OrderItem> queryByOrderId(String orderId);
}
