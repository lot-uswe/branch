package com.hspedu.furns.test;

import com.hspedu.furns.entity.Member;
import com.hspedu.furns.service.MemberService;
import com.hspedu.furns.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * @author xiaowang
 * @creat 2024/7/6 9:46
 * @Description Java Lotus
 */
public class MemberServiceTest {

    private MemberService memberService = new MemberServiceImpl();

    @Test
    public void isExistUsername(){
        if(memberService.isExistUsername("admin")){
            System.out.println("用户名存在");
        }else{
            System.out.println("用户名不存在");
        }
    }
    @Test
    public void registerMember(){
        Member member = new Member(null, "mary", "mary", "mary@qq.com");
        if(memberService.registerMember(member)){
            System.out.println("注册用户成功");
        }else{
            System.out.println("注册用户失败");
        }
    }

    @Test
    public void login(){
        Member member = new Member(null, "admin", "admin", null);

        System.out.println(memberService.login(member));

    }
}
