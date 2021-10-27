package day01.Mysql进阶;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-08-31 18:18:46
 */

import day01.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class demo2 {
    public static void main(String[] args) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "insert into dept values (70, '学习部', '大连')";
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        JdbcUtil.close(statement, connection);
    }
}
