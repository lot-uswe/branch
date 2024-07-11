package com.hspedu.furns.dao.impl;

import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.dao.MemberDAO;
import com.hspedu.furns.entity.Member;

/**
 * @author xiaowang
 * @creat 2024/7/6 9:16
 * @Description Java Lotus
 */
public class MemberDAImpl extends BasicDAO<Member> implements MemberDAO {
    //通过用户名返回对应的member
    //如果没有一个member，就返回一个空
    @Override
    public Member queryMemberByUsername(String username) {

        String sql = "SELECT `id`,`username`,`password`,`email` FROM `member`\n" +
                " WHERE `username`=?";

        return querySingle(sql, Member.class, username);

    }

    //保存一个会员，传入一个member对象，返回-1就是一个失败，其他数字就是受影响的行数
    @Override
    public int saveMember(Member member) {
        String sql = " INSERT INTO `member`(`username`,`password`,`email`) " +
                " VALUES(?,MD5(?), ?)";
        return update(sql, member.getUsername(),
                member.getPassword(), member.getEmail());


    }

    @Override
    public Member queryMemberByUsernameAndPassword(String username, String password) {

        String sql = "SELECT `id`,`username`,`password`,`email` FROM `member`" +
                " WHERE `username`=? and password = md5(?)";

        return querySingle(sql,Member.class,username,password);
    }
}
