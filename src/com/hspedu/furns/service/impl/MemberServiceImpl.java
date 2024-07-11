package com.hspedu.furns.service.impl;

import com.hspedu.furns.dao.MemberDAO;
import com.hspedu.furns.dao.impl.MemberDAImpl;
import com.hspedu.furns.entity.Member;
import com.hspedu.furns.service.MemberService;

/**
 * @author xiaowang
 * @creat 2024/7/6 9:36
 * @Description Java Lotus
 */
public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO = new MemberDAImpl();
    @Override
    public boolean registerMember(Member member) {

        return memberDAO.saveMember(member) == 1 ? true : false;

    }



    @Override
    public boolean isExistUsername(String username) {

        //ctrl+alt+b是很详细的

        return memberDAO.queryMemberByUsername(username)==null?false:true;


    }

    @Override
    public Member login(Member member) {
        //返回一个对象
        return memberDAO.queryMemberByUsernameAndPassword
                (member.getUsername(), member.getPassword());
    }
}
