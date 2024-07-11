package com.hspedu.furns.test;

import com.hspedu.furns.entity.Cart;
import com.hspedu.furns.entity.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author xiaowang
 * @creat 2024/7/9 13:50
 * @Description Java Lotus
 */
public class CartTest {

    private Cart cart = new Cart();
    @Test
    public void addItem(){
        cart.addItem(new CartItem(1,"沙发",new BigDecimal(10),2,new BigDecimal(20)));
        cart.addItem(new CartItem(2,"小椅子",new BigDecimal(20),2,new BigDecimal(40)));

        System.out.println("cart="+cart);
    }
}
