package com.hspedu.furns.filter;

import com.hspedu.furns.entity.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 这是一个用于权限验证的过滤器，用于指定的url进行验证
 * 如果登陆过就放行，如果没有，就回到登录页面
 * @author xiaowang
 * @creat 2024/7/11 9:30
 * @Description Java Lotus
 */
public class AuthFilter implements Filter {

    //后面我们把要排除的url放入到excludedUrls
    private List<String> excludedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取到配置的excludedUrls

        String strExcludedUrls = filterConfig.getInitParameter("excludedUrls");
        String[] splitUrl = strExcludedUrls.split(",");
        //将splitUrl转成List
        //Java基础中常用类Arrays
        excludedUrls = Arrays.asList(splitUrl);
        System.out.println("excludedUrls="+excludedUrls);


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //得到请求的url
        String url = request.getServletPath();


        System.out.println("url"+url);

        //判断是否要去验证
        if (!excludedUrls.contains(url)) {

            //得到session中的member对象
            Member member = (Member) request.getSession().getAttribute("member");

            //没有登录过
            if (member == null) {
                //转发到登录页面
                request.getRequestDispatcher("/views/member/login.jsp")
                        .forward(servletRequest, servletResponse);
                //这里这个filter就不再进行了，直接结束
                return;
            }
        }

        //继续访问
        filterChain.doFilter(servletRequest,servletResponse);


        System.out.println("请求");
    }

    @Override
    public void destroy() {

    }
}
