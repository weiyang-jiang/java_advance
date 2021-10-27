package com.example.service;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-27 10:23:22
 */

import com.example.dao.AccountDao;
import com.example.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountService {
    private AccountDao accountDao = new AccountDao();
    public void transfer(Double money, String fromName, String toName){
        //开启事务
        Connection connection = null;
        try {
            connection = DruidUtil.getConnectionFromThreadLocal();
            connection.setAutoCommit(false);
            accountDao.updateAccount(money, fromName);
            accountDao.updateAccount(-money, toName);
            connection.commit();
        } catch (Exception e){
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("转账失败");
        }


    }
}
