package com.hspedu.furns.web;

import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.entity.Page;
import com.hspedu.furns.service.FurnService;
import com.hspedu.furns.service.impl.FurnServiceImpl;
import com.hspedu.furns.utils.DataUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiaowang
 * @creat 2024/7/7 15:05
 * @Description Java Lotus
 */
public class FurnServlet extends BasicServlet {


    //定义一个属性
    private FurnService furnService = new FurnServiceImpl();


    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"),1);

        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //调用service方法，获取page对象

        Page<Furn> page = furnService.page(pageNo, pageSize);

//        System.out.println("page"+page);

        req.setAttribute("page",page);
        //请求转发

        req.getRequestDispatcher("/views/manage/furn_manage.jsp")
                .forward(req,resp);

    }

    //在后端拿到了修改好的信息
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将提交修改的家具信息封装成Furn对象
        Furn furn = DataUtils.copyParamToBean(req.getParameterMap(), new Furn());
        furnService.updateFurn(furn);
        //重定向到显示页面
//        resp.sendRedirect(req.getContextPath()
//                +"/manage/furnServlet?action=list");

        resp.sendRedirect(req.getContextPath()
                +"/manage/furnServlet?action=page&pageNo="+req.getParameter("pageNo"));


//        System.out.println("furn=> "+furn);
    }

    /**
     * 处理回显家具信息的任务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void showFurn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        //将furn放入到request域中
        req.setAttribute("furn",furn);
        //将pageNo保存到request域中
        //如果请求所带来的pageNo=1，而且是请求转发到下一个页面param
        req.setAttribute("pageNo",req.getParameter("pageNo"));

//        System.out.println("furn=>" +furn);
        //请求转发
        req.getRequestDispatcher("/views/manage/furn_update.jsp")
                .forward(req,resp);


    }

    protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //为了防止接收的id不是一个数字型的字符串，我们可以做一个处理
        //我们就使用一个工具方法，转换成为数字
        int id = DataUtils.parseInt(req.getParameter("id"),0);
        furnService.deleteFurnById(id);
        //重定向到家居列表页，刷新数据，看到最新数据
        //不要去请求转发
//        resp.sendRedirect(req.getContextPath()
//                +"/manage/furnServlet?action=list");

        resp.sendRedirect(req.getContextPath()
        +"/manage/furnServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * 处理添加家具的请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取到添加家具的信息
//        String name = req.getParameter("name");
//        String maker = req.getParameter("maker");
//        String price = req.getParameter("price");
//        String sales = req.getParameter("sales");

//        try {
//            int i = Integer.parseInt(sales);
//        } catch (NumberFormatException e) {
////            System.out.println("转换异常。。。");
//
//            req.setAttribute("msg","销量数据格式不对。。。");
//
//            req.getRequestDispatcher("/views/manage/furn_add.jsp")
//                    .forward(req,resp);
//        }



//        String stock = req.getParameter("stock");
        //图片路径 imgPath 使用默认的即可

//
//        Furn furn = null;
//        try {
//            furn = new Furn(null, name, maker, new BigDecimal(price),
//                    new Integer(sales), new Integer(stock), "assets/images/product-image/default.jpg");
//        } catch (NumberFormatException e) {
//            req.setAttribute("msg","添加数据格式不对。。。");
//            req.getRequestDispatcher("/views/manage/furn_add.jsp")
//                    .forward(req,resp);
//            return;
//        }

//        Furn furn = new Furn(null, name, maker, new BigDecimal(price),
//                new Integer(sales), new Integer(stock), "assets/images/product-image/default.jpg");
        //后面我们会学习springMVC->专门用于校验的规则/框架JSR...Hibernate Valiator

        //使用BeanUtils完成JavaBean对象的自动封装
        //这里我们去使用第二种方式，完成将前端提交的数据

//        Furn furn = new Furn();
//        try {
//            //将req.getParameterMap()封装到furn对象中
//            BeanUtils.populate(furn,req.getParameterMap());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        System.out.println("furn="+ furn);


        //自动将提交的数据封装到Furn对象
        Furn furn =
                DataUtils.copyParamToBean(req.getParameterMap(),new Furn());
        furnService.addFurn(furn);


        //请求转发到家具显示页面,即需要重新走一下我们的Furn-Servlet list方法



//        req.getRequestDispatcher("/manage/furnServlet?action=list")
//        .forward(req,resp);

        //因为这个使用了请求转发，当用户刷新页面的时候
        //会重新发出一次添加请求
        //会造成数据的重复提交
        //解决方法：使用我们的重定向即可
        //重定向是让浏览器重新的发出一个请求，所以我们所回送的url是完整的是安全的
//        resp.sendRedirect(req.getContextPath()
//                +"/manage/furnServlet?action=list");

        resp.sendRedirect(req.getContextPath()
            +"/manage/furnServlet?action=page&pageNo="
            + req.getParameter("pageNo"));



    }

    /**
     * 这里我们使用前面的模板设计模式+反射+动态绑定
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);

//        System.out.println("FurnService list 的方法被调用");
        List<Furn> furns = furnService.queryFurns();
//        for (Furn furn : furns) {
//            System.out.println(furn);
//
//        }
        //把拿到的furns集合放入到request域

        req.setAttribute("furns",furns);

        //请求转发

        req.getRequestDispatcher("/views/manage/furn_manage.jsp")
        .forward(req,resp);
    }

}
