package com.hspedu.furns.web;

import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.entity.Page;
import com.hspedu.furns.service.FurnService;
import com.hspedu.furns.service.impl.FurnServiceImpl;
import com.hspedu.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaowang
 * @creat 2024/7/8 17:56
 * @Description Java Lotus
 */
public class CustomerFurnServlet extends BasicServlet {

    //定义了一个furnService属性
    private FurnService furnService = new FurnServiceImpl();


    /**
     * 这里仍然是分页请求家具信息的api
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
            //这里的逻辑和我们后台显示家具的信息非常的相似
            int pageNo = DataUtils.parseInt(req.getParameter("pageNo"),1);

            int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

            //调用service方法，获取page对象

            Page<Furn> page = furnService.page(pageNo, pageSize);

//        System.out.println("page"+page);

            req.setAttribute("page",page);
            //请求转发

            req.getRequestDispatcher("/views/customer/index.jsp")
                    .forward(req,resp);


    }

    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //这里的逻辑和我们后台显示家具的信息非常的相似
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //如果参数有name但是没有值，这个地方接收到的就是一个空串
        //如果连参数都没有，就收到null
        String name = req.getParameter("name");
//        String name = req.getParameter("name");
        if (null==name){
            name="";
        }
        //调用service方法，获取page对象

        Page<Furn> page = furnService.pageByName(pageNo, pageSize,name);

        //根据
        StringBuilder url =
                new StringBuilder("customerFurnServlet?action=pageByName");

        if (!"".equals(name)){//如果name不为空串，那么就再次拼接name参数
            url.append("&name=").append(name);
        }

        page.setUrl(url.toString());
        

//        System.out.println("page"+page);

        req.setAttribute("page",page);
        //请求转发

        req.getRequestDispatcher("/views/customer/index.jsp")
                .forward(req,resp);


    }
}
