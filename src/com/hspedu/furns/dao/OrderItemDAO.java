package com.hspedu.furns.dao;

import com.hspedu.furns.entity.OrderItem;

/**
 * OrderItemDAO表示一个订单项
 * @author xiaowang
 * @creat 2024/7/10 9:16
 * @Description Java Lotus
 */
public interface OrderItemDAO {
    /**
     * 将传入的orderLtem对象保存到数据表中+
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);
}
