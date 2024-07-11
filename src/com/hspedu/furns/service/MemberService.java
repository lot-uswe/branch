package com.hspedu.furns.service;

import com.hspedu.furns.entity.Member;

/**
 * @author xiaowang
 * @creat 2024/7/6 9:34
 * @Description Java Lotus
 */
public interface MemberService {

    //注册用户的

    public boolean registerMember(Member member);

    //判断用户是否存在

    public boolean isExistUsername(String username);

    /**
     * 根据登录传入的member对象信息，返回对应的在DB中的member对象
     * @param member
     * @return 返回的是对应的DB中的对象，如果不存在就是null
     */
    public Member login(Member member);
}
