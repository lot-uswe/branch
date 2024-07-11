package com.hspedu.furns.test;

import com.hspedu.furns.dao.MemberDAO;
import com.hspedu.furns.dao.impl.MemberDAImpl;
import com.hspedu.furns.entity.Member;
import org.junit.jupiter.api.Test;

/**
 * @author xiaowang
 * @creat 2024/7/6 9:27
 * @Description Java Lotus
 */
public class MemberDAOTest {

    private MemberDAO memberDAO=new MemberDAImpl();

    @Test
    public void queryMemberByUsername(){

        if(memberDAO.queryMemberByUsername("admin")==null){
            System.out.println("该用户名不存在...");
        }else{
            System.out.println("该用户名存在...");
        }
    }

    @Test
    public void saveMember(){

        Member member =
                new Member(null, "king", "king","king@shouhu.com");

        if(memberDAO.saveMember(member)==1){
            System.out.println("添加OK");
        }else{
            System.out.println("添加失败了");
        }
    }

    @Test
    public void queryMemberByUsernameAndPassword(){

        Member member = memberDAO.queryMemberByUsernameAndPassword("admin","admin");

        System.out.println("member = "+member);
    }
}
