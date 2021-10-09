package com.wangpeng.dao.impl;

import com.wangpeng.dao.OrderDao;
import com.wangpeng.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int addOrder(Order order) {
        String sql = "INSERT INTO t_order(`orderId`,`date`,`price`,`count`,`status`,`userId`) " +
                "VALUE(?,?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getDate(),order.getPrice(),
                order.getCount(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryByUserId(int UserId) {
        String sql = "SELECT `orderId`,`date`,`price`,`count`,`status`,`userId` FROM t_order WHERE `userId`=?";
        return queryForList(Order.class, sql, UserId);
    }

    @Override
    public List<Order> queryAll() {
        String sql = "SELECT `orderId`,`date`,`price`,`count`,`status`,`userId` FROM t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public int updateStatus(int status, String orderId) {
        String sql = "UPDATE t_order SET `status`=? WHERE orderId=?";
        return update(sql,status,orderId);
    }

}
