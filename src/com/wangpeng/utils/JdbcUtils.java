package com.wangpeng.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null，说明获取连接失败，有值就是获取连接成功
     */
    public static Connection getConnection() {
        Connection conn = conns.get();
        if(conn == null) {
            try {
                conn = dataSource.getConnection();  //从数据库连接池中获取连接
                conns.set(conn);    //保存到ThreadLocal对象中，供后买你的jdbc操作使用
                conn.setAutoCommit(false);  //设置为手动管理事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并关闭释放连接
     */
    public static void commitAndClose() {
        Connection connection = conns.get();
        if(connection != null) {    //如果不等于null，说明之前使用过连接，操作过数据库
            try {
                connection.commit();    //提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close(); //关闭连接，释放资源
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove(); //ThradLocal的解绑（因为Tomcat服务器底层使用了线程池技术）
    }

    /**
     * 回滚事务并关闭释放连接
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if(connection != null) {    //如果不等于null，说明之前使用过连接，操作过数据库
            try {
                connection.rollback();    //回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close(); //关闭连接，释放资源
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove(); //ThradLocal的解绑，否则就会出错（因为Tomcat服务器底层使用了线程池技术）
    }

    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
/*    public static void close(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }*/

}
