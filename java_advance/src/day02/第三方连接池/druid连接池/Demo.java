package day02.第三方连接池.druid连接池;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 18:03:22
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Demo {
    public static void main(String[] args) throws Exception {
        InputStream resourceAsStream = Demo.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        resourceAsStream.close();
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
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
