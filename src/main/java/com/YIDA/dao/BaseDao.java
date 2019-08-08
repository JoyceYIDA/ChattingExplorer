package com.YIDA.dao;

import com.YIDA.Utils.CommUtils;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

/**
 * @author yida
 */
public class BaseDao {
    private static DataSource dataSource;
    static{
        Properties properties=CommUtils.loadProperties("dataSource.properties");
        try {
            dataSource=DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected static Connection getConnect(){
        try {
            return DriverManager.getConnection(String.valueOf("dataSource"));
        } catch (SQLException e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }
        return null;
    }
    protected static void closeConnection(PreparedStatement statement,
                                          Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    protected static void closeConnection(PreparedStatement statement,
                                          Connection connection,ResultSet resultSet){
        closeConnection(statement,connection);
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
