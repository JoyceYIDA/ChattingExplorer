package com.YIDA.Utils;

import junit.framework.TestCase;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author yida
 */
public class JDBCUtilsATest {

    @Test
    public void testQuery() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtilsA.getConnection();
            String sql = "SELECT * FROM user WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,2);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("id为"+id+",用户名为:"+userName+
                        " ，密码为"+password);
            }
        }catch (SQLException e) {
        }
//        }finally {
//            JDBCUtils.closeResources(connection,statement,resultSet);
//        }
    }

    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtilsA.getConnection();
            String sql = "INSERT INTO user(username, password) VALUES(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,"test1");
            statement.setString(2, DigestUtils.md5Hex("123"));
            int yingXiang = statement.executeUpdate();
            Assert.assertEquals(1,yingXiang);
        }catch (SQLException e) {

        }finally {
            JDBCUtilsA.closeResources(statement,connection);
        }
    }
}