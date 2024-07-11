package com.hspedu.furns.dao.impl;

import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.dao.OrderItemDAO;
import com.hspedu.furns.entity.OrderItem;

/**
 * @author xiaowang
 * @creat 2024/7/10 9:18
 * @Description Java Lotus
 */
public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="INSERT INTO `order_item`(`id`,`name`,`price`,`count`,`total_price`,`order_id`)" +
                "VALUES(?,?,?,?,?,?)";
        //写代码不要急，一定要小心
        return update(sql,orderItem.getId(),orderItem.getName(),orderItem.getPrice(),
                orderItem.getCount(),orderItem.getTotalPrice(),orderItem.getOrderId());

    }
}
