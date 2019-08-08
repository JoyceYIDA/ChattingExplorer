package com.YIDA.Utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 封装JDBC公共方法
 */
public class JDBCUtils{

    private static String driverName;
    private static String userName;
    private static String password;
    private static String url;
    //加载的时候，且仅加载一次；使用静态代码块
    static {
        Properties properties=CommUtils
                .loadProperties("db.properties");
        driverName=properties.getProperty("driverName");
        url=properties.getProperty("url");
        userName=properties.getProperty("userName");
        password=properties.getProperty("password");

        //1.加载驱动仅加载一次
        try {
            Class.forName("driverName");
        } catch (ClassNotFoundException e) {
            System.err.println("数据库驱动问题");
            e.printStackTrace();
        }
    }
    //2.每个用户连接是独有的
    public static Connection connection(){
        try {
            return DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            System.err.println("连接出错");
            e.printStackTrace();
        }
        return null;
    }
    public void closedConnections(Statement statement,
                       ResultSet resultSet, Connection connection){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void closeConnections(Statement statement,Connection connection){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
