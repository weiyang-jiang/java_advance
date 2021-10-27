package day02.第三方连接池.C3P0连接池;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 19:04:05
 */

import day02.util.C3P0Util;

import javax.sql.DataSource;

public class demo1 {
    public static void main(String[] args) {
        DataSource dataSource = C3P0Util.getDataSource();


    }
}
