package com.hspedu.furns.test;

import com.hspedu.furns.utils.JDBCUtilsByDruid;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author xiaowang
 * @creat 2024/7/6 8:48
 * @Description Java Lotus
 */
public class JDBCUtilsByDruidTest {

    @Test
    public void getConnection() throws SQLException {

        Connection connection = JDBCUtilsByDruid.getConnection();
        System.out.println("connect= "+connection);
        JDBCUtilsByDruid.close(null,null,connection);
        

    }
}
