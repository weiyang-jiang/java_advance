package day02.第三方连接池.C3P0连接池;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 17:20:47
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class demo_c3p0 {
    public static void main(String[] args) throws SQLException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        Connection connection1 = dataSource.getConnection();
        String sql = "SELECT * from user";
        PreparedStatement preparedStatement = connection1.prepareStatement(sql);
        ResultSet rst = preparedStatement.executeQuery();
        while (rst.next()){
            String username = rst.getString("username");
            System.out.println(username);
        }
        rst.close();
        connection1.close();

    }
}
