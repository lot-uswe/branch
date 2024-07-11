package com.hspedu.furns.test;

import com.hspedu.furns.dao.OrderDAO;
import com.hspedu.furns.dao.impl.OrderDAOImpl;
import com.hspedu.furns.entity.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaowang
 * @creat 2024/7/10 9:11
 * @Description Java Lotus
 */
public class OrderDAOTest {

    private OrderDAO orderDAO = new OrderDAOImpl();

    @Test
    public void saveOrder(){
        Order order =
                new Order("sn00002", new Date(), new BigDecimal(200), 0, 2);
        System.out.println(orderDAO.saveOrder(order));


    }
}
