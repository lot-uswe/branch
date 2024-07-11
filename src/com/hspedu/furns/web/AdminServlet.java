package com.hspedu.furns.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaowang
 * @creat 2024/7/7 16:03
 * @Description Java Lotus
 */
public class AdminServlet extends BasicServlet {
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/manage/manage_menu.jsp")
                .forward(request,response);

    }

}
