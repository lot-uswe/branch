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
// * @creat 2024/7/6 14:38
// * @Description Java Lotus
// */
//public class RegisterServlet extends HttpServlet {
//
//    //定义一个属性
//    private MemberService memberService = new MemberServiceImpl();
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //接收用户的注册信息->一定要去看那个界面，一定要去看前端界面
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//
//        //判断这个用户名是不是可用的
//        if (!memberService.isExistUsername(username)) {
//            //注册
//            //System.out.println("用户名 " + username + " 不存在，可以注册");
//            Member member = new Member(null, username, password, email);
//            if(memberService.registerMember(member)){
//                //System.out.println("注册成功");
//                request.getRequestDispatcher("/views/member/register_ok.jsp")
//                        .forward(request,response);
//
//            }else{
//                //System.out.println("注册失败");
//                request.getRequestDispatcher("/views/member/register_fail.jsp")
//                        .forward(request,response);
//            }
//
//
//        } else {
//            //返回注册页面
//            //System.out.println("用户名 " + username + " 存在，不可以注册");
//            request.getRequestDispatcher("/view/member/login.jsp")
//                    .forward(request,response);
//        }
//
//
////        System.out.println("RegisterServlet 被调用。。。");
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
//    }
//}
