
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-28 11:29:40
 */

import com.example.pojo.User;
import com.example.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class demo1 {
    public static void main(String[] args) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

        String sql = "select * from account";
        User user = queryRunner.query(sql, new BeanHandler<>(User.class));

    }
}
