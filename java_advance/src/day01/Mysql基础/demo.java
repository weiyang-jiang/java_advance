package day01.Mysql基础;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-08-27 11:36:49
 */

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.sql.*;

public class demo {
    @Test
    public void test01() throws SQLException, ClassNotFoundException {
        // 注册驱动：只要Drier类加载了，加载进入内存行成字节码对象。
//        DriverManager.registerDriver(new Driver()); // 这句代码具有耦合性，不应该使用
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://127.0.0.1:3306/day3_1?characterEncoding=utf8&useSSL=false";
        String username = "weiyang";
        String password = "Hk#2012727";

        // 创建连接
        Connection connection = DriverManager.getConnection(url, username, password);

        // 创建一个状态，用来执行sql语句
        Statement stm = connection.createStatement();

        String sql = "SELECT * From emp";

        ResultSet rst = stm.executeQuery(sql);

        // 每次循环都会调用next方法, rst相当于游标
        while (rst.next()){
            String name = rst.getString("ename");
            System.out.println(name);
        }
        rst.close();
        connection.close();








    }
}
