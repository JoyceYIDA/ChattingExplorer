import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.sql.*;

public class JDBCTest1 {
    @Test
    public void test() throws ClassNotFoundException, SQLException {
//        加载驱动
        Class.forName("com.mysql.jdbc.Driver");
//        获取数据库连接
        Connection connection=DriverManager.getConnection("" +
                "jdbc:mysql://localhost:3306/jdbc","root",
                "7777");
//        执行sql语句
        String sql="select * from user";
//        Statement执行sql语句 PrepareStatement类--预编译sql类，会使用占位符？占位用户名密码需要外部输入
//        防止sql注入:Statement类存在SQL注入，使用PrepareStatement类（预处理SQL）
//        executeQuery
//        executeUpdate():int 数据库更新操作
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
//        遍历查询结果
        while(resultSet.next()){
            int id =resultSet.getInt("id");
            String userName=resultSet.getNString("userName");
            String password=resultSet.getNString("password");
            System.out.println(
                    "id为"+id+"，用户名为："+userName+"，密码为："+
                            password);
//            释放资源
            connection.close();
            statement.close();
            resultSet.close();
        }
    }
    @Test
    //insert
    public void test1(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("" +
                            "jdbc:mysql://localhost:3306/jdbc","root",
                    "7777");
            String sql="insert into user(userName,password)"+
                    "values('test','456')";
            Statement statement=connection.createStatement();
            int resultRows=statement.executeUpdate(sql,
                    statement.RETURN_GENERATED_KEYS);
            System.out.println(resultRows);
            //释放资源
            connection.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    //select
    public void test2(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("" +
                            "jdbc:mysql://localhost:3306/jdbc","root",
                    "7777");
            String sql="select * from user where userName='zs'"+"and password='123'";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            if(resultSet.next()){
                System.out.println("登陆成功");
            }else{
                System.out.println("登陆失败");
            }
            //释放资源
            connection.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    //select
    public void test3(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("" +
                            "jdbc:mysql://localhost:3306/jdbc","root",
                    "7777");
            String userName="zs";
            String password="ll";
            String sql="select * from user"+"where userName=? and password=?";

            //会预编译sql
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.getResultSet();
            if(resultSet.next()){
                System.out.println("登陆成功");
            }else{
                System.out.println("登陆失败");
            }
            //释放资源
            connection.close();
            preparedStatement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
