package day02.元数据;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-02 10:18:02
 */

import day02.util.DButil_weiyang;
import day02.util.DruidUtil;
import day02.util.user_DButil;

import java.sql.SQLException;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {
//        update_method();
        String sql = "select * from user where id > ?";
        DButil_weiyang dButil_weiyang = new DButil_weiyang(DruidUtil.getDataSource());
        List<user_DButil> user_dButils = dButil_weiyang.queryList(sql, user_DButil.class, 1);
        for (user_DButil user_dButil : user_dButils) {
            System.out.println(user_dButil);
        }
    }

    private static void update_method() throws SQLException {
        String sql = "insert into user values (null, ?, ?)";
        DButil_weiyang dButil_weiyang = new DButil_weiyang(DruidUtil.getDataSource());
        int update = dButil_weiyang.update(sql, "zs", "182003");
        System.out.println(update);
    }
}
