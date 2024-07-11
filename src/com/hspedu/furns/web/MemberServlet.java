package com.hspedu.furns.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import com.hspedu.furns.entity.Member;
import com.hspedu.furns.service.MemberService;
import com.hspedu.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author xiaowang
 * @creat 2024/7/7 10:41
 * @Description Java Lotus
 */

/**
 * 该servlet是要处理和Member相关的请求
 */
public class MemberServlet extends BasicServlet {

    private MemberService memberService = new MemberServiceImpl();


    /**
     * 验证某个用户名是否已经存在db中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void isExistUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //获取用户名
        String username = req.getParameter("username");

        //调用service
        boolean isExistUserName = memberService.isExistUsername(username);

        //如何返回一个json格式，【不要去乱写，要根据前端的格式来玩】
        //先要去定义一个格式，因为目前的前后端都是自己实现，所以我们去自己定义
        //{"isExist":false}

        //先去使用一个拼接形式

        //拓展性非常差
//        String resultJson = "{\"isExist\":"+isExistUserName+"}";

        //将要返回的数据先要去放回到map中去，转回到json

        Map<String,Object> resultMap=new HashMap<>();

        //如果将来我们要放进去更多的东西
        resultMap.put("isExist",isExistUserName);

        String resultJson = new Gson().toJson(resultMap);


        //返回
        resp.getWriter().write(resultJson);




    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁当前用户的session
        req.getSession().invalidate();
        //重定向,刷新页面
        resp.sendRedirect(req.getContextPath());
    }

    //    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String action = request.getParameter("action");
//
//        if("login".equals(action)){
//            login(request,response);
//        }else if("register".equals(action)){
//            register(request,response);
//        }else{
//            //提示信息
//            response.getWriter().write("请求参数action错误");
//        }
//
//    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("=============memberService register()==============");
        //接收用户的注册信息->一定要去看那个界面，一定要去看前端界面
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //获取用户提交的验证码
        String code = request.getParameter("code");

        //从session中获取到验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        //立即删除我们session中的验证码
        //防止该验证码被重复使用
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //比对一下,如果这个token不为空，并且这个验证码是和后台进行匹配的
        if (token!=null&&token.equalsIgnoreCase(code)) {

            //判断这个用户名是不是可用的
            if (!memberService.isExistUsername(username)) {
                //注册
                //System.out.println("用户名 " + username + " 不存在，可以注册");
                Member member = new Member(null, username, password, email);
                if (memberService.registerMember(member)) {
                    //System.out.println("注册成功");
                    request.getRequestDispatcher("/views/member/register_ok.jsp")
                            .forward(request, response);

                } else {
                    //System.out.println("注册失败");
                    request.getRequestDispatcher("/views/member/register_fail.jsp")
                            .forward(request, response);
                }


            } else {
                //返回注册页面
                //System.out.println("用户名 " + username + " 存在，不可以注册");
                request.getRequestDispatcher("/views/member/login.jsp")
                        .forward(request, response);
            }
        }else{//验证码不正确的时候
            request.setAttribute("msg","验证码不正确");
            //如果前端要回显某些数据
            request.setAttribute("username",username);
            request.setAttribute("email",email);
            //带回一个相关信息，要显示到注册的选项页
            request.setAttribute("active","register");
            request.getRequestDispatcher("/views/member/login.jsp")
                    .forward(request, response);
        }
    }



    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //把管理员也当作一个会员

        System.out.println("=============memberService register()==============");
//        System.out.println("LoginServlet 被调用");

        //如果（登录界面）输入框什么都没写，直接提交，后台接收的就是空串；

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //简单处理权限
        Member member = memberService.login(new Member(null, username, password, null));
        if (member == null){//用户不存在，不在数据库
//把登录错误信息放到request
            request.setAttribute("msg","用户名或者密码错误");
            request.setAttribute("username",username);


            //            System.out.println(member+" 登录失败 ");
            //页面转发
            request.getRequestDispatcher("/views/member/login.jsp")
                    .forward(request,response);
        }else{//存在，在数据库
//            System.out.println(member+" 登录成功 ");
            request.getSession().setAttribute("member",member);

            //先做一个简单处理
            //对于事务管理机制是十分复杂的，权限机制
            if ("admin".equals(member.getUsername())){
                request.getRequestDispatcher("/views/manage/manage_menu.jsp")
                        .forward(request,response);
            }else {
                request.getRequestDispatcher("/views/member/login_ok.jsp")
                        .forward(request, response);
            }

        }

    }

}
