package com.example.utils;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 19:12:40
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DruidUtil {
    private static DataSource dataSource;

    static {
        try {
            InputStream resourceAsStream = DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static DataSource getDataSource(){
        return dataSource;
    }
}
