package day02.连接池;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 15:19:39
 */

import day02.util.JdbcUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class MyDataSource2 implements DataSource {

    private LinkedList<Connection> connectionPool = new LinkedList<>();

    public MyDataSource2() {
        for (int i = 0; i < 5; i++) {
            try {
                Connection connection = JdbcUtil.getConnection();
                connectionPool.add(connection);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        if (connectionPool.size() > 0){
            conn = connectionPool.removeFirst(); // 这个方法是删除连接并返回连接， 这种可以保证连接池中有固定连接数， 不会重复拿。
        }else {
            conn = JdbcUtil.getConnection();
        }
        return conn;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
