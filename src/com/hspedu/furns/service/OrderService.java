package com.hspedu.furns.service;

import com.hspedu.furns.entity.Cart;

/**
 * @author xiaowang
 * @creat 2024/7/10 9:27
 * @Description Java Lotus
 */
public interface OrderService {

    //当我们在设计这个层面的时候，我们是要去知道的
    //这个数据类型适合我们的不论是数据库还是之前设计的类
    //都是要去相关的
    //所以在我们不知道的前提下，我们可以先去设计一个void
    //分析
    //1.生成订单的
    //2.订单的来源，根据我们的购物车来生成的，我们购买的东西是放在这个cart里面的
    //3。cart对象是在session，是要在web层才可以去获取的
    //4.订单是和每个会员相关连的
    public String saveOrder(Cart cart,int memberId);
}
