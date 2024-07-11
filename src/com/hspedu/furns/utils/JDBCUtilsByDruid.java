package com.hspedu.furns.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 韩顺平
 * @version 1.0
 * 基于druid数据库连接池的工具类
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;

    //定义一个属性ThreadLocal，这里存放了一个Connection
    private static ThreadLocal<Connection> threadLocalConn =
            new ThreadLocal<>();


    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            //因为我们目前是web项目，他的工作目录是在out，文件的加载，需要去使用类加载
            properties.load(JDBCUtilsByDruid.class.getClassLoader()
                    .getResourceAsStream("druid.properties"));
            //properties.load(new FileInputStream("src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 在ThreadLocal获取connection，保证在同一个线程中，
     * 获取的是同一个connection
     * @return
     * @throws SQLException
     */

    //编写getConnection方法
    public static Connection getConnection() {
//        return ds.getConnection();//在连接池直接获取
        Connection connection = threadLocalConn.get();
        if (connection==null){
            //当前的ThreadLocalConn没有连接
            //就从数据库连接池中取出一个连接放入到ThreadLocalConn
            try {
                connection = ds.getConnection();
                connection.setAutoCommit(false);//不要去自动提交了
                //禁用自动提交！！！即将连接设置为手动提交
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            threadLocalConn.set(connection);

        }
        return connection;
    }

    /**
     * 提交事务 mysql 线程 过滤器Filter threadLocalConn
     */
    public static void commit(){

        Connection connection = threadLocalConn.get();
        if (connection!=null){//确保连接是有效的
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //tomcat底层是线程工作，当提交完毕后，需要那这个连接
        //从我的threadLocalConn清除掉
        //不然会造成我们的threadLocalConn长期持有这个连接
        //会影响效率
        //也因为我们的tomcat的底层使用的线程池技术
        threadLocalConn.remove();
    }

    //重写rollback方法

    /**
     * 所谓回滚就是撤销的意思
     * he和connection管理的操作删掉，修改，添加
     * 各一个连接相关的
     */
    public static void rollback(){

        Connection connection = threadLocalConn.get();
        if (connection!=null){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        threadLocalConn.remove();

    }


    //关闭连接, 老师再次强调： 在数据库连接池技术中，close 不是真的断掉连接
    //而是把使用的Connection对象放回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
