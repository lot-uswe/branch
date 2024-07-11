package com.hspedu.furns.dao;

import com.hspedu.furns.entity.Order;

/**
 * @author xiaowang
 * @creat 2024/7/10 9:03
 * @Description Java Lotus
 */
public interface OrderDAO {

    /**
     * 将传入的order放入到db中
     * @param order
     * @return
     */
    public int saveOrder(Order order);
}
