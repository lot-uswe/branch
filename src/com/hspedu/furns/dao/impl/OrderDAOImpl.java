package com.hspedu.furns.dao.impl;

import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.dao.OrderDAO;
import com.hspedu.furns.entity.Order;

/**
 * @author xiaowang
 * @creat 2024/7/10 9:04
 * @Description Java Lotus
 */
public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {
    @Override
    public int saveOrder(Order order) {
        String sql="INSERT INTO `order`(`id`,`create_time`,`price`,`status`,`member_id`) " +
                "VALUES(?,?,?,?,?)";
        //先去数据库中去编写测试
        return update(sql,order.getId(),order.getCreateTime(),
                order.getPrice(),order.getStatus(),order.getMemberId());
        //测试sql语句，一定要多去测试
    }
}
