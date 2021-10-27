package day01.解决sql注入;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 10:08:10
 */

import day01.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class demo {
    public static void main(String[] args) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        int id = 1;
        // 对要执行的sql语句进行占位符处理
        String sql = "select * from user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id); // 给有占位符的地方赋值。 第几个占位符赋值哪一个数据
        ResultSet rst = preparedStatement.executeQuery();
        while (rst.next()){
            String userId = rst.getString("username");
            System.out.println(userId);
        }
        JdbcUtil.close(rst, preparedStatement, connection);

    }
}
