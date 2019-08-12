package com.YIDA.Utils;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 基于DruidDataSource
 * 数据源加载驱动
 * 只是加载策略不一样
 */
public class JDBCUtilsA{
    private static DruidDataSource dataSource;
    //数据源只需要加载一次
    static{
        try {
            Properties properties=CommUtils.loadProperties("datasource.properties");
            //注册驱动
            dataSource=(DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("数据源获取失败");
            e.printStackTrace();
        }
    }
    public static DruidPooledConnection getConnection(){

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("连接出错");
            e.printStackTrace();
        }
        return null;
    }
    public static void closeResources(Statement statement,
                                  ResultSet resultSet, Connection connection){
        closeResources(statement,connection);
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeResources(Statement statement,Connection connection) {
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
