package com.hspedu.furns.entity;

/**
 * @author xiaowang
 * @creat 2024/7/6 8:35
 * @Description Java Lotus
 */
public class Member {
    private Integer id;
    private String username;
    private String password;
    private String email;

    public Member() {
        //一定要提供一个无参构造器
        //底层反射的时候会使用到一个无参构造器

    }

    public Member(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    
}
