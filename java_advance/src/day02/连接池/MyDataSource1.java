package day02.连接池;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-09-01 13:35:15
 */

import day02.util.JdbcUtil;

import java.sql.Connection;
import java.util.LinkedList;

public class MyDataSource1 {
    /*
       目前存在的问题
            1. 频繁创建连接和关闭，消耗资源。
            2. 在高并发的环境下，要同时创建非常多的连接，可能会导致服务器崩溃
            3. 执行sql语句的代码太繁琐。
            4. 遍历结果集，封装太麻烦
       连接池
       1. 初始化会创建几个连接放置到连接池中
       2. 执行sql语句的时候，如果需要连接，连接池中有连接就获取连接
       3. 如果有sql语句需要连接，连接池没有连接，那么进入等待， 等待有超时限定
     */
    /*
    1. 继承DataSource接口
    2. 创建一个容器，存放连接
    3. 存放五个连接
    4. 提供一个方法，获取连接
    5. 归还连接
     */
    private LinkedList<Connection> connectionPool = new LinkedList<>();

    public MyDataSource1() {
        for (int i = 0; i < 5; i++) {
            try {
                Connection connection = JdbcUtil.getConnection();
                connectionPool.add(connection);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConn(){
        Connection conn = null;
        if (connectionPool.size() > 0){
            conn = connectionPool.removeFirst(); // 这个方法是删除连接并返回连接， 这种可以保证连接池中有固定连接数， 不会重复拿。
        }else {
            conn = JdbcUtil.getConnection();
        }
        return conn;
    }

    public void returnConn(Connection conn){
        // 新创建的连接不能加到连接池中
        connectionPool.addLast(conn);
    }
}
