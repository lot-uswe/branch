package com.hspedu.furns.test;

import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.entity.Page;
import com.hspedu.furns.service.FurnService;
import com.hspedu.furns.service.impl.FurnServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiaowang
 * @creat 2024/7/7 15:01
 * @Description Java Lotus
 */
public class FurnServiceTest {

    private FurnService furnService = new FurnServiceImpl();


    @Test
    public void queryFurns(){
        List<Furn> furns = furnService.queryFurns();
        for (Furn furn : furns) {
            System.out.println(furn);


        }
    }

    @Test
    public void addFurn(){


        Furn furn = new Furn(null, "可爱的小沙发~~~", "顺平家居", new BigDecimal(999.99), 100, 10, "assets/images/product-image/default.jpg");
        furnService.addFurn(furn);


    }

    @Test
    public void deleteFurnById(){

        System.out.println(furnService.deleteFurnById(17));

    }

    @Test
    public void queryFurnById(){
        Furn furn = furnService.queryFurnById(1);
        System.out.println("furn="+furn);
    }

    @Test
    public void updateFurn(){
        Furn furn = new Furn(13, "可爱的小沙发~~~", "顺平家居", new BigDecimal(999.99), 100, 10, "assets/images/product-image/default.jpg");
        System.out.println(furnService.updateFurn(furn));

    }

    @Test
    public void page(){

        //如果我们看一个对象很麻烦，那么我们可以用debug
        Page<Furn> page = furnService.page(1, 3);

        System.out.println("page= "+ page);
    }

    @Test
    public void pageByName(){
        Page<Furn> page = furnService.pageByName(1, 3, "沙发");

        System.out.println("page="+page);
    }
}
