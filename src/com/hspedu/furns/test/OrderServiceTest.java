package com.hspedu.furns.test;

import com.hspedu.furns.entity.Cart;
import com.hspedu.furns.entity.CartItem;
import com.hspedu.furns.service.OrderService;
import com.hspedu.furns.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author xiaowang
 * @creat 2024/7/10 10:14
 * @Description Java Lotus
 */
public class OrderServiceTest {

    private OrderService orderService = new OrderServiceImpl();


    @Test
    public void saveOrder(){
        //构建一个cart对象

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"北欧风格小桌子",new BigDecimal(200.00),2,new BigDecimal(400.00)));
        cart.addItem(new CartItem(2,"简约风格小椅子",new BigDecimal(180.00),1,new BigDecimal(180.00)));


        System.out.println(orderService.saveOrder(cart,1));





    }
}
