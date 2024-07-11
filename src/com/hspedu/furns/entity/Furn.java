package com.hspedu.furns.entity;

import java.math.BigDecimal;

/**
 * @author xiaowang
 * @creat 2024/7/7 14:35
 * @Description Java Lotus
 */
public class Furn {
    /*
 create table `furn`(
`id` int unsigned primary key auto_increment,
`name` varchar(64) not null,
`maker` varchar(64) not null,
`price` decimal(11,2) not null,
`sales` int unsigned not null,
`stock` int unsigned not null,
`img_path` varchar(256) not null
 )charset utf8 engine innodb
 */

    //表的字段如何和我们的Javabean映射
    //防止空指针，空值，这样的话就可以自动装箱
    private Integer id;
    private String name;
    private String maker;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private String imgPath="assets/images/product-image/default.jpg";


    public Furn() {
    }



    public Furn(Integer id, String name, String maker, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        if (!(null == imgPath||"".equals(imgPath))){
            this.imgPath = imgPath;
        }
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

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Furn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

    
}
