package day01.解决sql注入;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 10:19:47
 */

import day01.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class demo_login {
    public static void main(String[] args) throws SQLException {
        // 登录案例
        // 1. 控制台提示输出用户名
        System.out.println("请输入用户名:");
        // 2. 获取用户输入的用户名
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        // 3. 控制台提示输入密码
        System.out.println("请输入密码:");
        // 4. 获取用户输入的密码
        String password = sc.nextLine();
        Connection connection = JdbcUtil.getConnection();
        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql); // 预编译会固定好格式，后面sql注入无法改变前面的格式
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }
}
