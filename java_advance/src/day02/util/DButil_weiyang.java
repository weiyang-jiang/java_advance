package day02.util;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-02 10:27:02
 */

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DButil_weiyang {
    private DataSource dataSource;

    public DButil_weiyang(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int update(String sql, Object... params) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        int parameterCount = parameterMetaData.getParameterCount();
        for (int i = 1; i <= parameterCount; i++) {
            preparedStatement.setObject(i, params[i-1]);
        }
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return i;
    }

    public <T> List<T> queryList(String sql, Class<T> tClass, Object... params) throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        int parameterCount = parameterMetaData.getParameterCount();
        for (int i = 1; i <= parameterCount; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<T> ts = new ArrayList<>();
//        Method[] declaredMethods = tClass.getDeclaredMethods();
        while (resultSet.next()) {
            T t = tClass.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(columnName);

//                for (Method declaredMethod : declaredMethods) {
//                    String name = declaredMethod.getName();
//                    if (name.equalsIgnoreCase("set"+columnName)){
//                        declaredMethod.invoke(t, columnValue);
//                    }
//                }
                // 内省，操作get和set方法。
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, tClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(t, columnValue);
            }
            ts.add(t);
        }
        preparedStatement.close();
        connection.close();
        return ts;
    }
}
