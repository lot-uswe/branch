package com.hspedu.furns.web;

import com.hspedu.furns.entity.Cart;
import com.hspedu.furns.entity.Member;
import com.hspedu.furns.service.OrderService;
import com.hspedu.furns.service.impl.OrderServiceImpl;
import com.hspedu.furns.utils.JDBCUtilsByDruid;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaowang
 * @creat 2024/7/11 8:23
 * @Description Java Lotus
 */
public class OrderServlet extends BasicServlet {
    //先打通前端

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //内容
        Cart cart = (Cart) req.getSession().getAttribute("cart");


        //如果cart为空，说明用户没有购买任何商品，转发到首页
        if (null==cart||cart.isEmpty()){
            //那么我们就回到首页
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            return;
        }

        //获取到当前登录的memberId
        Member member = (Member)req.getSession().getAttribute("member");

        if (null==member){
            //该用户没有登录，将其转发到登录页面

            req.getRequestDispatcher("/views/member/login.jsp").forward(req,resp);
            return;
        }

        //这两个判断都已经成功，我们就可以去生成订单了
        //1.如果我们只是希望对这个orderService进行事务控制
        //2。我们可以不使用过滤器
//        String orderId = null;
//        try {
//            orderId = orderService.saveOrder(cart, member.getId());
//            JDBCUtilsByDruid.commit();
//        } catch (Exception e) {
//            JDBCUtilsByDruid.rollback();
//            e.printStackTrace();
//        }


        String orderId = orderService.saveOrder(cart, member.getId());
        req.getSession().setAttribute("orderId",orderId);

        //使用重定向方式，到达页面
        resp.sendRedirect(req.getContextPath()+"/views/order/checkout.jsp");
    }
}
