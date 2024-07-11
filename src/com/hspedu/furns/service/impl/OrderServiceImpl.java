package com.hspedu.furns.service.impl;

import com.hspedu.furns.dao.FurnDAO;
import com.hspedu.furns.dao.OrderDAO;
import com.hspedu.furns.dao.OrderItemDAO;
import com.hspedu.furns.dao.impl.FurnDAOImpl;
import com.hspedu.furns.dao.impl.OrderDAOImpl;
import com.hspedu.furns.dao.impl.OrderItemDAOImpl;
import com.hspedu.furns.entity.*;
import com.hspedu.furns.service.OrderService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaowang
 * @creat 2024/7/10 9:34
 * @Description Java Lotus
 */
public class OrderServiceImpl implements OrderService {


    private OrderDAO orderDAO = new OrderDAOImpl();

    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    private FurnDAO furnDAO = new FurnDAOImpl();

    //javaee分层结构和mvc的分层的好处，
    //在service层，通过组合多个dao的方法，完成某个业务
    //完成某个业务的时候，ssm，springboot很多地方都要去使用

    @Override
    public String saveOrder(Cart cart, int memberId) {

        //这里的业务相对复杂
        //完成任务的时候cart购物车的数据以order和orderItem形式保存到db

        //通过cart对象，得到这个对应的order对象
        //先生成uuid，表示当前的订单号，订单号要保证是唯一的
        String orderId = System.currentTimeMillis()+""+memberId;
        //0代表的是未发货
        Order order
                = new Order(orderId, new Date(),
                cart.getCartTotalPrice(), 0, memberId);

        orderDAO.saveOrder(order);

        //对多张表的操作，生成订单，多表事务的问题
        //ThreadLocal+Mysql事务机制，加过滤器

        //通过我们的cart对象，遍历出cartItem，构建OrderItem对象
        //并保存到这个order_item这张表中
//
//        第一种方式
//        HashMap<Integer, CartItem> items = cart.getItems();
//
//        //Java基础，遍历hashmap
//        Set<Integer> keys = items.keySet();
//        for (Integer id : keys) {
//
//            //购物车里面有多少个cartItem也会就i有orderItem
//            CartItem item = items.get(id);
//
//            //构建出cartItem对象构建了orderItem对象
//            OrderItem orderItem = new OrderItem(null, item.getName(), item.getPrice(),
//                    item.getCount(), item.getTotalPrice(), orderId);
//
//            //保存
//            orderItemDAO.saveOrderItem(orderItem);
//
//            //更新furn表中的sales销量,stock存量
//            //首先要拿到furn对象
//
//            Furn furn = furnDAO.queryFurnById(id);
//
//            //更新furn的销量和存量
//            furn.setSales(furn.getSales()+item.getCount());
//            furn.setStock(furn.getStock()-item.getCount());
//
//            //更新数据表
//            furnDAO.updateFurn(furn);
//
//        }

        //第二种通过entrySet方式
        for (Map.Entry<Integer,CartItem> entry : cart.getItems().entrySet()) {

            //购物车里面有多少个cartItem也会就i有orderItem
            CartItem item = entry.getValue();

            //构建出cartItem对象构建了orderItem对象
            OrderItem orderItem = new OrderItem(null, item.getName(), item.getPrice(),
                    item.getCount(), item.getTotalPrice(), orderId);

            //保存
            orderItemDAO.saveOrderItem(orderItem);

            //更新furn表中的sales销量,stock存量
            //首先要拿到furn对象

            Furn furn = furnDAO.queryFurnById(item.getId());

            //更新furn的销量和存量
            furn.setSales(furn.getSales()+item.getCount());
            furn.setStock(furn.getStock()-item.getCount());

            //更新数据表
            furnDAO.updateFurn(furn);

        }

        //清空购物车
        cart.clear();


        return orderId;
    }
}
