package day01.MySQL案例;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 08:42:10
 */

import day01.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class demo {
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
        // 5. 校验密码是否正确
        Connection connection = JdbcUtil.getConnection();
        Statement statement = connection.createStatement();
        // 可能会出现sql注入
        String sql = "select * from user where username = '" + username + "'and password ='" + password + "';";
        ResultSet rst = statement.executeQuery(sql);
//         6. 控制台输出成功或者失败
        if (rst.next()){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }
}
