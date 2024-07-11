package com.hspedu.furns.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author xiaowang
 * @creat 2024/7/7 11:15
 * @Description Java Lotus
 */
public abstract class BasicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决接收到的数据的中文乱码问题
        req.setCharacterEncoding("utf-8");


//        super.doPost(req, resp);
        //System.out.println("BasicServlet doPost()");
        //先获取到action的值
        String action = req.getParameter("action");
//        System.out.println("action="+action);

        //使用反射获取到当前对象的方法

        //this 就是请求的真实的Servlet，由tomcat所创造的
        //declaredMethod方法对象，就是当前所请求的servlet对应的action名字的方法
        //这个方法对象是动态变化的，用反射获取当前对象的某个方法，根据用户的请求去变化的
        //反射+模板+动态绑定机制 这样就可以简化了多个if else 分支
        try {
            Method declaredMethod =
                    this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println("declaredMethod="+declaredMethod);
            //使用方法对象，进行反射的调用
            declaredMethod.invoke(this,req,resp);

        } catch (Exception e) {
            //将发生的异常抛出去
            //Java基础的异常机制
            //异常机制是可以去参与业务逻辑的
            //源码中有很多使用异常机制
            throw new RuntimeException(e);
//            e.printStackTrace();
        }



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
