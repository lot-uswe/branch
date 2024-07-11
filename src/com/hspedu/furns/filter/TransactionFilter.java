package com.hspedu.furns.filter;

import com.hspedu.furns.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * 控制事务的过滤器
 * @author xiaowang
 * @creat 2024/7/11 11:43
 * @Description Java Lotus
 */
public class TransactionFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            //放行
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtilsByDruid.commit();//统一提交，出现了异常
        } catch (Exception e) {
            //只有在前面的try中出现了异常，才回去回滚
            JDBCUtilsByDruid.rollback();//回滚
//            e.printStackTrace();
//            为了抛出异常，给tomcat，tomcat会根据errorpage来显示对应的
            throw new RuntimeException(e);
        }

    }

    @Override
    public void destroy() {

    }
}
