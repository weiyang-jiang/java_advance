package day01.Mysql进阶;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-08-31 17:38:58
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class demo1 {
    public static void main(String[] args) throws Exception {
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

        String sql = "SELECT * From dept";

        ResultSet rst = stm.executeQuery(sql);

        // 新建一个list对象存储查询出来的信息
        ArrayList<user> dept_list = new ArrayList<>();
        // 每次循环都会调用next方法, rst相当于游标
        while (rst.next()){
            int id = rst.getInt("id");
            String name = rst.getString("dname");
            String loc = rst.getString("loc");

            user u = new user();
            u.setId(id);
            u.setEname(name);
            u.setLoc(loc);
            dept_list.add(u);
        }

        rst.close();
        connection.close();

        for (user u : dept_list) {
            System.out.println(u);
        }

    }
}
