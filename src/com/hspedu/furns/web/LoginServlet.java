//package com.hspedu.furns.web;
//
//import com.hspedu.furns.entity.Member;
//import com.hspedu.furns.service.MemberService;
//import com.hspedu.furns.service.impl.MemberServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author xiaowang
// * @creat 2024/7/6 20:37
// * @Description Java Lotus
// */
//public class LoginServlet extends HttpServlet {
//
//    private MemberService memberService = new MemberServiceImpl();
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        System.out.println("LoginServlet 被调用");
//
//        //如果（登录界面）输入框什么都没写，直接提交，后台接收的就是空串；
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        Member member = memberService.login(new Member(null, username, password, null));
//        if (member == null){//用户不存在，不在数据库
////把登录错误信息放到request
//            request.setAttribute("msg","用户名或者密码错误");
//            request.setAttribute("username",username);
//
//
//            //            System.out.println(member+" 登录失败 ");
//            //页面转发
//            request.getRequestDispatcher("/views/member/login.jsp")
//                    .forward(request,response);
//        }else{//存在，在数据库
////            System.out.println(member+" 登录成功 ");
//
//            request.getRequestDispatcher("/views/member/login_ok.jsp")
//                    .forward(request,response);
//
//
//        }
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
//    }
//}
