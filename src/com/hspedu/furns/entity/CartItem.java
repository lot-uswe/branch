package com.hspedu.furns.entity;

import java.math.BigDecimal;

/**
 * CartItem表示购物车的一项就是家具数据
 * @author xiaowang
 * @creat 2024/7/9 13:32
 * @Description Java Lotus
 */

//原生的web


public class CartItem {

    //定义属性->根据需求或者是文档
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;

    public CartItem() {
    }

    public CartItem(Integer id, String name, BigDecimal price, Integer count, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
