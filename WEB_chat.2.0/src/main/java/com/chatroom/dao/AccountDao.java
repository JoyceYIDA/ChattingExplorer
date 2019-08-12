package com.chatroom.dao;

import com.chatroom.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;

/**
 * @author yida
 */
public class AccountDao extends BaseDao{
    // 用户登录 select
    public User userLogin(String userName,String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = getConnect();
            String sql = "SELECT * FROM user WHERE username = ? AND " +
                    " password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,userName);
            statement.setString(2, DigestUtils.md5Hex(password));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = getUserInfo(resultSet);
            }
        }catch (Exception e) {
            System.err.println("查询用户信息出错");
            e.printStackTrace();
        }finally {
            closeConnection(statement,connection,resultSet);
        }
        return user;
    }

    // 用户注册 insert
    public boolean userRegister(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isSuccess = false;
        try {
            connection = super.getConnect();
            String sql = "INSERT INTO user(username, password)" +
                    " VALUES(?,?) ";
            statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,userName);
            statement.setString(2,DigestUtils.md5Hex(password));
            isSuccess = (statement.executeUpdate() == 1);
        }catch (Exception e) {
            System.err.println("用户注册失败");
            e.printStackTrace();
        }finally {
            closeConnection(statement,connection);
        }
        return isSuccess;
    }

    // 将数据表信息封装到User类中
    public User getUserInfo(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setID(resultSet.getInt("id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

}
