package com.hspedu.furns.web;

import com.google.gson.Gson;
import com.hspedu.furns.entity.Cart;
import com.hspedu.furns.entity.CartItem;
import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.service.FurnService;
import com.hspedu.furns.service.impl.FurnServiceImpl;
import com.hspedu.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author xiaowang
 * @creat 2024/7/9 13:55
 * @Description Java Lotus
 */
public class CartServlet extends BasicServlet {

    //添加一个furnService对象属性
    private FurnService furnService = new FurnServiceImpl();


    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session中的购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null!=cart){
            cart.clear();
        }

        //返回清空购物车的原页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void delItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (null!=cart){
            cart.delItem(id);
        }

        resp.sendRedirect(req.getHeader("Referer"));
    }

    //添加够无车的方法


    /**
     * 更新某个CartItem的数量，更新我们的购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"),0);
        //这里可以根据自己的业务来处理
        int count = DataUtils.parseInt(req.getParameter("count"), 1);

        //获取session中的购物车
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (null!=cart){
            cart.updateCount(id,count);
        }
        //回到请求更新购物车的这个界面
        //这个购物车可能是分页的，这样的话我们直接去重定向的话，就不能去定位保证
        resp.sendRedirect(req.getHeader("Referer"));

    }

    protected void addItemByAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //得到添加家具的id
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        //获取到id所对的Furn对象
        Furn furn = furnService.queryFurnById(id);
        //判断一下
        //先把正常的逻辑去走完，再去处理异常情况
        //todo
        if (furn==null){
            return;
        }

        //根据furn构建CartItem,目前的数量是一个
        CartItem item =
                new CartItem(furn.getId(), furn.getName(), furn.getPrice(), 1, furn.getPrice());

        //从session中获取这个cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (null==cart){//说明当前这个用户session中没有catr
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);

        }

        //将我们的cartItem，加入到cart

        cart.addItem(item);
        System.out.println("cart="+cart);

        //添加完毕后，需要返回到添加家居的页面
        //也不是一个白板
        //也可以先获取一次
        //将cartItem加入到cart

//        String referer = req.getHeader("Referer");
//        resp.sendRedirect(req.getHeader("Referer"));

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("cartTotalcount",cart.getTotalCount());

        String resultJson = new Gson().toJson(resultMap);

        resp.getWriter().write(resultJson);
    }

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //得到添加家具的id
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        //获取到id所对的Furn对象
        Furn furn = furnService.queryFurnById(id);
        //判断一下
        //先把正常的逻辑去走完，再去处理异常情况
        //todo
        if (furn==null){
            return;
        }

        //根据furn构建CartItem,目前的数量是一个
        CartItem item =
                new CartItem(furn.getId(), furn.getName(), furn.getPrice(), 1, furn.getPrice());

        //从session中获取这个cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (null==cart){//说明当前这个用户session中没有catr
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);

        }

        //将我们的cartItem，加入到cart

        cart.addItem(item);
//        System.out.println("cart="+cart);


        //添加完毕后，需要返回到添加家居的页面
        //也不是一个白板
        //也可以先获取一次
        resp.sendRedirect(req.getHeader("Referer"));

    }
}
