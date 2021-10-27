package com.example.dao;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-27 10:27:50
 */

import com.example.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountDao {
    private QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
    public void updateAccount(Double money, String name) throws SQLException {
        Connection connection = DruidUtil.getConnectionFromThreadLocal();
        String sql = "update account set money = money - ? where name = ?";
//        queryRunner.update(sql, name, money);
        queryRunner.update(connection, sql, money, name);
    }
}
