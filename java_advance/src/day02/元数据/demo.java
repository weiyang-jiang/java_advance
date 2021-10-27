package day02.元数据;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-02 09:18:32
 */

import day02.util.DruidUtil;
import org.junit.Test;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class demo {

    @Test
    public void test_dataMetaSource() throws SQLException {
        // 里面包含数据库元数据。驱动名称啥的
        DataSource dataSource = DruidUtil.getDataSource();
        Connection connection = dataSource.getConnection();

        DatabaseMetaData metaData = connection.getMetaData();
        String driverName = metaData.getDriverName();
        System.out.println(driverName);
    }

    @Test
    public void test_paramMetaSource() throws SQLException {
        DataSource dataSource = DruidUtil.getDataSource();
        Connection connection = dataSource.getConnection();

        String sql = "select * from user where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

        System.out.println(parameterMetaData.getParameterCount());
    }

    @Test
    public void test_ResultMetaSource() throws SQLException {
        DataSource dataSource = DruidUtil.getDataSource();
        Connection connection = dataSource.getConnection();

        String sql = "select * from user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSetMetaData metaData = preparedStatement.getMetaData();

        int columnCount = metaData.getColumnCount(); // 获取列数
        System.out.println(columnCount);
    }

    public <T> List<T> queryList(String sql, Class<T> tClass, Object... params) throws Exception {
        DataSource dataSource = DruidUtil.getDataSource();
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        int parameterCount = parameterMetaData.getParameterCount();
        for (int i = 1; i <= parameterCount; i++) {
            preparedStatement.setObject(i,params[i-1]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<T> ts = new ArrayList<>();

        while (resultSet.next()){
            T t = tClass.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(columnName);
                int i1 = columnName.indexOf(1);
                Method method = tClass.getMethod("set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1));
                method.invoke(t, columnValue);
            }
            ts.add(t);
        }
        return ts;
    }
}
