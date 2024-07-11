package com.hspedu.furns.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author xiaowang
 * @creat 2024/7/7 20:56
 * @Description Java Lotus
 */
public class DataUtils {

    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    //将字符串转换成为一个整数，否则是一个默认值
    public static int parseInt(String val,int defaultVal){
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            System.out.println(val+"格式不正确。。。。");
        }
        return defaultVal;
    }
}
