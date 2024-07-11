package com.hspedu.furns.test;

import com.hspedu.furns.dao.FurnDAO;
import com.hspedu.furns.dao.impl.FurnDAOImpl;
import com.hspedu.furns.entity.Furn;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiaowang
 * @creat 2024/7/7 14:49
 * @Description Java Lotus
 */
public class FurnDAOTest {

    private FurnDAO furnDAO = new FurnDAOImpl();
    @Test
    public void queryFurns(){
        List<Furn> furns = furnDAO.queryFurns();
        for (Furn furn : furns) {
            System.out.println(furn);


        }

    }
    @Test
    public void add(){
        Furn furn = new Furn(null, "可爱的小沙发", "顺平家居", new BigDecimal(999.99), 100, 10, "assets/images/product-image/default.jpg");

        System.out.println("执行结果: "+furnDAO.addFurn(furn));
    }

    @Test
    public void getPageItems(){
        List<Furn> pageItem = furnDAO.getPageItems(1,3);

        for (Furn furn : pageItem) {
            System.out.println(furn);
        }
    }

    @Test
    public void getTotalRow(){
        System.out.println(furnDAO.getTotalRow());
    }

    @Test
    public void getTotalRowByName(){
        System.out.println("getTotalRowByName:"+furnDAO.getTotalRowByName("沙发"));
    }

    @Test
    public void getPageItemsByName(){
        List<Furn> furns = furnDAO.getPageItemsByName(0, 3, "沙发");
        System.out.println(furns);
    }
}
