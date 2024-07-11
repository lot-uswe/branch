package com.hspedu.furns.dao;

import com.hspedu.furns.entity.Member;

/**
 * @author xiaowang
 * @creat 2024/7/6 9:12
 * @Description Java Lotus
 */
public interface MemberDAO {


    //提供一个用户名返回对应的Member

    public Member queryMemberByUsername(String username);

    //提供一个保存Member对象到数据库的member表

    //如果是1就成功

    public int saveMember(Member member);

    /**
     * 根据用户名和密码返回到Member
     * @param username
     * @param password
     * @return 返回的对象，如果不存在，就返回null
     */

    public Member queryMemberByUsernameAndPassword
            (String username,String password);




}
