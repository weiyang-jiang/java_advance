package day01.转账案例;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 10:30:11
 */

import day01.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class demo {
    public static void main(String[] args) throws SQLException {
        // 目标：zs向ls转账520元
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        try {
            String sql1 = "update account set money = money - ? where name = ?";
            String sql2 = "update account set money = money + ? where name = ?";
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false); // 开启事务
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement2 = connection.prepareStatement(sql2);

            preparedStatement1.setDouble(1, 100);
            preparedStatement2.setDouble(1, 100);
            preparedStatement1.setString(2, "zs");
            preparedStatement2.setString(2, "ls");

            int i1 = preparedStatement1.executeUpdate();
            int i2 = preparedStatement2.executeUpdate();

            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
        }finally {
            preparedStatement2.close();
            JdbcUtil.close(preparedStatement1, connection);
        }






    }
}
