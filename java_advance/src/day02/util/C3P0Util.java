package day02.util;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 18:21:41
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class C3P0Util {
    private static DataSource dataSource;
    static {
        dataSource = new ComboPooledDataSource();
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
}
