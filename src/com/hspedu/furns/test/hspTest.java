package com.hspedu.furns.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author xiaowang
 * @creat 2024/7/11 8:52
 * @Description Java Lotus
 */


public class hspTest {
    //如果你自己不知道这个方法是否是正确的
    //可以自己去测试一下

    @Test
    public static void main(String[] args) {


        HashMap<Object,Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("1","1");

        System.out.println(objectObjectHashMap);

        objectObjectHashMap.clear();
        System.out.println(objectObjectHashMap.size()==0);
    }
}
