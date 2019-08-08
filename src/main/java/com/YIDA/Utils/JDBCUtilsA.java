package com.YIDA.Utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 数据源
 */
public class JDBCUtilsA{
    private static DruidDataSource dataSource;
    static{
        try {
            Properties properties=CommUtils.loadProperties("datasource.properties");
            dataSource=(DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("数据源获取失败");
            e.printStackTrace();
        }
    }
    public static Connection connection(){

        try {
            return DriverManager.getConnection(String.valueOf(dataSource));
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
    public void closeConnections(Statement statement,Connection connection) {
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
