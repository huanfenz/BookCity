package com.wangpeng.dao.impl;

import com.wangpeng.dao.OrderItemDao;
import com.wangpeng.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO t_order_item(`id`,`bookId`,`name`,`count`,`price`,`totalPrice`,`orderId`) " +
                "VALUE(?,?,?,?,?,?,?)";
        return update(sql, orderItem.getId(),orderItem.getBookId(),orderItem.getName(),orderItem.getCount(),
                orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryByOrderId(String orderId) {
        String sql = "SELECT `id`,`bookId`,`name`,`count`,`price`,`totalPrice`,`orderId` FROM t_order_item WHERE `orderId`=?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
