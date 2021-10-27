package day02.连接池;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 15:19:39
 */

import day02.util.JdbcUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class MyDataSource4 implements DataSource {
    // 使用动态代理
    private LinkedList<Connection> connectionPool = new LinkedList<>();

    public MyDataSource4() {
        for (int i = 0; i < 5; i++) {
            try {
                Connection conn = JdbcUtil.getConnection();
                Connection proxyConnection = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("close")){
                            connectionPool.addLast((Connection) proxy); // 必须将此时被代理的对象传入列表中，而不是传入没有被代理的对象
                            return null; // 如果使用的方法是close方法那么直接返回null，就不会再去执行原来接口的方法
                        }
                        return method.invoke(conn, args); //这句话的意思是执行原来接口的方法
                    }
                });
                connectionPool.add(proxyConnection);
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
