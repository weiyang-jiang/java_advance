package day01.Mysql基础;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-08-31 17:22:15
 */

import org.junit.Test;

import java.sql.*;

public class demo_insert {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://127.0.0.1:3306/day3_1?characterEncoding=utf8&useSSL=false";
        String username = "weiyang";
        String password = "Hk#2012727";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        String sql = "INSERT into dept values (50, '学习部', '大连')";
        int i = statement.executeUpdate(sql);
        System.out.println(i);


        statement.close();
        connection.close();
        // 获取结果

    }
    @Test
    public void test_update() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://127.0.0.1:3306/day3_1?characterEncoding=utf8&useSSL=false";
        String username = "weiyang";
        String password = "Hk#2012727";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        String sql = "UPDATE dept set loc='宁波' where id = 50";
        int i = statement.executeUpdate(sql);
        System.out.println(i);


        statement.close();
        connection.close();
    }

    @Test
    public void test_delete() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://127.0.0.1:3306/day3_1?characterEncoding=utf8&useSSL=false";
        String username = "weiyang";
        String password = "Hk#2012727";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();

        String sql = "delete from dept where loc = '宁波'";
        int i = statement.executeUpdate(sql);
        System.out.println(i);


        statement.close();
        connection.close();
    }
}
