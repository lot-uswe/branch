package com.hspedu.furns.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

/**
 * cart就是一个购物车，包含着我们的很多数据
 * 包含多个cartItem对象
 * @author xiaowang
 * @creat 2024/7/9 13:35
 * @Description Java Lotus
 */
public class Cart {


    //定义属性
    private HashMap<Integer,CartItem> items = new HashMap<>();

    public boolean isEmpty(){
        return items.size()==0;
    }

    public void clear(){
        items.clear();
    }
    /**
     * 根据传入的id删除指定的购物车项
     * @param id
     */
    public void delItem(int id){

        items.remove(id);
    }
    //购物车商品的总数量
    //属性保留价值没有意义
//    private Integer totalCount = 0;

    /**
     * 修改指定的CartItem的数量和总价，根据传入的id和count
     * @param id
     * @param count
     */
    public void updateCount(int id,int count){

        CartItem item = items.get(id);
        if(null!=item){//如果得到这个CartItem
            //先更新数量
            //既然我们set了，就去使用get的方式去获取
            item.setCount(count);
            //这个地方我们都是去使用面向对象的方法去get一个值的属性方法，
            //而不是去把这个所产生的值直接去产生出来，因为我们之后还可以去
            //做一些相关的校验工作
            //在更新总价
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }


    public HashMap<Integer, CartItem> getItems() {
        return items;
    }

    /**
     * 返回购物车的总价
     * @return
     */
    public BigDecimal getCartTotalPrice(){

        BigDecimal cartTotalPrice = new BigDecimal(0);
        //遍历items
        Set<Integer> keys = items.keySet();

        for (Integer id : keys) {
            CartItem item = items.get(id);
            //一定要把这个add后的值，再重新去接收这个值
            cartTotalPrice = cartTotalPrice.add(item.getTotalPrice());

        }
        return cartTotalPrice;
    }

    public Integer getTotalCount() {
        int totalCount=0;//每次取值我们就去清零
        //遍历items 统计totalCount
        //Java基础，如何遍历hashMap
        Set<Integer> keys = items.keySet();
        for (Integer id : keys) {
            totalCount += ((CartItem) items.get(id)).getCount();

        }
        return totalCount;
    }

    //添加家具到Cart

    public void  addItem(CartItem cartItem){

        //可以去画一个内存图
        CartItem item = items.get(cartItem.getId());
        if (null==item){
            items.put(cartItem.getId(),cartItem);
        }else{//购物车中已经存在了这个
            item.setCount(item.getCount()+1);//数量增加
            //修改总价
            //item.getPrice()返回的bigDecimal
            //但是这个getCount返回的却是这个Integer
            //这两个不可以相乘
//            item.setTotalPrice(item.getPrice()
//                    .multiply(new BigDecimal(item.getCount())));

            item.setTotalPrice(item.getTotalPrice().add(item.getPrice()));

        }



    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
