package day02.DButil学习;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 20:23:49
 */

import day02.util.DruidUtil;
import day02.util.user_DButil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class demo1 {
    public static void main(String[] args) throws SQLException {
//        insert_method();
//        select_single();
//        BeanHandler_method();
        String sql = "select * from user where username=? and password=?";
        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
        user_DButil query = queryRunner.query(sql, new BeanHandler<>(user_DButil.class), "admin", "root");
        System.out.println(query);
    }

    private static void BeanHandler_method() throws SQLException {
        String sql = "select * from user where id = ?";

        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
        user_DButil user = queryRunner.query(sql, new BeanHandler<>(user_DButil.class), 3);
        // 需要自己新建一个Bean
        System.out.println(user.getUsername());
    }

    private static void select_single() throws SQLException {
        String sql = "select username from user where id = ?";
        // ResultHandler 接口
        // ScalaHandler 查询单个数据
        // BeanHandler 查询一行数据放到类里面
        // MapHandler 查询一行数据放到map里面
        // BeanListHandler 查询多行数据封装到类里面
        // MapListHandler 查询多行数据封装到Map里面
        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

        String username = (String) queryRunner.query(sql, new ScalarHandler(), 1);
        System.out.println(username);
    }

    /**
     *
     * @throws SQLException
     */
    private static void insert_method() throws SQLException {
        String sql = "insert into user values (null, ?, ?)";
        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
        queryRunner.update(sql, "xiaoji", "991215");
    }
}
